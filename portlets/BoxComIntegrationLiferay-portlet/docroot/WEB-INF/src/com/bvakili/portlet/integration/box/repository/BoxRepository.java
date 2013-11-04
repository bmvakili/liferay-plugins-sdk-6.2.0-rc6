package com.bvakili.portlet.integration.box.repository;

import static com.bvakili.portlet.integration.box.util.Constants.BOX_ACCOUNT_ROOT_FOLDER_ID;

import com.box.boxjavalibv2.BoxClient;
import com.box.boxjavalibv2.dao.BoxCollection;
import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.dao.BoxFileVersion;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.dao.BoxItem;
import com.box.boxjavalibv2.dao.BoxOAuthToken;
import com.box.boxjavalibv2.dao.BoxTypedObject;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxJSONException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.exceptions.BoxUnexpectedHttpStatusException;
import com.box.boxjavalibv2.resourcemanagers.BoxFilesManager;
import com.box.boxjavalibv2.resourcemanagers.BoxFoldersManager;
import com.box.boxjavalibv2.resourcemanagers.BoxSearchManager;
import com.box.restclientv2.exceptions.BoxRestException;

import com.bvakili.portlet.integration.box.NotAuthenticatedToBoxException;
import com.bvakili.portlet.integration.box.model.BoxFileEntry;
import com.bvakili.portlet.integration.box.model.BoxToken;
import com.bvakili.portlet.integration.box.service.BoxTokenLocalServiceUtil;
import com.bvakili.portlet.integration.box.util.BoxUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.RepositoryEntry;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.RepositoryEntryLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.persistence.RepositoryEntryUtil;
import com.liferay.portlet.documentlibrary.FileNameException;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.persistence.DLFolderUtil;
import com.liferay.portlet.documentlibrary.util.comparator.RepositoryModelCreateDateComparator;
import com.liferay.portlet.documentlibrary.util.comparator.RepositoryModelModifiedDateComparator;
import com.liferay.portlet.documentlibrary.util.comparator.RepositoryModelNameComparator;
import com.liferay.portlet.documentlibrary.util.comparator.RepositoryModelSizeComparator;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bvakili.portlet.integration.box.util.Constants.REFRESH_TOKEN_EXPIRATION_DURATION_MILLISECONDS;
public class BoxRepository extends BaseBoxRepository {

	protected BoxClient _client;

	protected static Log _log = LogFactoryUtil.getLog(BoxRepository.class);

	protected static ThreadLocal<Map<Long, List<FileEntry>>> _fileEntriesCache =
			new AutoResetThreadLocal<Map<Long, List<FileEntry>>>(
				BoxRepository.class + "._fileEntriesCache",
				new HashMap<Long, List<FileEntry>>());
	protected static ThreadLocal<Map<Long, List<Object>>>
	_foldersAndFileEntriesCache =
		new AutoResetThreadLocal<Map<Long, List<Object>>>(
			BoxRepository.class + "._foldersAndFileEntriesCache",
			new HashMap<Long, List<Object>>());

	protected static ThreadLocal<Map<Long, List<Folder>>> _foldersCache =
		new AutoResetThreadLocal<Map<Long, List<Folder>>>(
			BoxRepository.class + "._foldersCache",
			new HashMap<Long, List<Folder>>());
	protected BoxRepositoryHandler _boxRepositoryHandler;

	public BoxRepository(BoxRepositoryHandler boxRepositoryHandler) {
		_boxRepositoryHandler = boxRepositoryHandler;
		_boxRepositoryHandler.setBaseBoxRepository((BaseBoxRepository)this);
	}

	@Override
	public FileEntry addFileEntry(long folderId, String sourceFileName,
			String mimeType, String title, String description, String changeLog,
			InputStream is, long size,
			ServiceContext serviceContext) throws PortalException,
			SystemException {

		if (Validator.isNull(title)) {
			if (size == 0) {
				throw new FileNameException();
			}
			else {
				title = sourceFileName;
			}
		}

		try {
			BoxClient client = getBoxClient();
			validateTitle(client, folderId, title);

		} catch (Exception e) {
			e.printStackTrace();
		}
		//TODO: finish this
		return super.addFileEntry(folderId, sourceFileName, mimeType, title,
				description, changeLog, is, size, serviceContext);
	}

	protected void validateTitle(BoxClient client, long folderId, String title) throws AuthFatalFailureException, BoxRestException, BoxServerException, PortalException, SystemException {
		String objectId = getObjectId(client, folderId, true, title);
	}

	protected String getObjectId(BoxClient client, long folderId, boolean fileEntry,
			String name) throws AuthFatalFailureException, BoxRestException, BoxServerException, PortalException, SystemException {
		String objectId = toFolderId(client, folderId);

		if (fileEntry) {
			BoxSearchManager bs = client.getSearchManager();
			BoxCollection col = bs.search(name, null);
			List<BoxTypedObject> list = col.getEntries();

			for (BoxTypedObject bt : list) {
				if (bt instanceof BoxItem) {
					if (name.equalsIgnoreCase(((BoxItem)bt).getName())) {
					}
				}
			}
		} else {
		}

		return null;
	}

	protected String toFolderId(BoxClient client, long folderId) throws PortalException, SystemException {
		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByPrimaryKey(folderId);

		if (repositoryEntry != null) {
			return repositoryEntry.getMappedId();
		}

		DLFolder dlFolder = DLFolderUtil.fetchByPrimaryKey(folderId);

		if (dlFolder == null) {
			throw new NoSuchFolderException(
				"No Box folder with {folderId=" + folderId + "}");
		}
		else if (!dlFolder.isMountPoint()) {
			throw new RepositoryException(
				"Box repository should not be used with {folderId=" +
					folderId + "}");
		}

		repositoryEntry = RepositoryEntryUtil.fetchByR_M(getRepositoryId(), BOX_ACCOUNT_ROOT_FOLDER_ID);

		if (repositoryEntry == null) {
			repositoryEntry = RepositoryEntryLocalServiceUtil.addRepositoryEntry(dlFolder.getUserId(), getGroupId(), getRepositoryId(), BOX_ACCOUNT_ROOT_FOLDER_ID, new ServiceContext());
		}

		return repositoryEntry.getMappedId();
	}

	public List<BoxFile> getAllFileVersions(String fileId) throws AuthFatalFailureException, BoxRestException, BoxServerException, NotAuthenticatedToBoxException, SystemException {
		List<BoxFile> boxFiles = new ArrayList<BoxFile>();
		List<BoxFileVersion> boxFileVersions = getBoxClient().getFilesManager().getFileVersions(fileId, null);

		for (BoxFileVersion boxFileVersion : boxFileVersions) {
			boxFiles.add(getFile(boxFileVersion.getId()));
		}

		return boxFiles;
	}

	public BoxClient getBoxClient() throws NotAuthenticatedToBoxException,
			BoxRestException, BoxServerException, AuthFatalFailureException,
			SystemException {
		if (_client != null && _client.isAuthenticated()) {
			return _client;
		}

		List<BoxToken> tokens = BoxTokenLocalServiceUtil.getActiveTokens(getRepositoryId());
		BoxToken token = tokens.get(0);
		BoxClient client = null;
		String accessToken = token.getAccessToken();
		String refreshToken = token.getRefreshToken();
		String callbackURL = token.getCallbackURL();

		if (System.currentTimeMillis() > token.getAccessTokenExpiration()) {
			if (System.currentTimeMillis() > token.getRefreshTokenExpiration()) {
				throw new NotAuthenticatedToBoxException();
			}
		}

		client = BoxUtil.refreshToken(refreshToken, callbackURL);
		BoxOAuthToken newToken = client.getAuthData();
		token.setAccessToken(newToken.getAccessToken());
		token.setAccessTokenExpiration(System.currentTimeMillis() + (newToken.getExpiresIn() * 1000));
		token.setRefreshToken(newToken.getRefreshToken());
		token.setRefreshTokenExpiration(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_DURATION_MILLISECONDS);
		BoxTokenLocalServiceUtil.updateBoxToken(token);
		_client = client;
		return client;
	}

	public BoxFile getFile(String fileId) throws AuthFatalFailureException, BoxRestException, BoxServerException, NotAuthenticatedToBoxException, SystemException {
		return getBoxClient().getFilesManager().getFile(fileId, null);
	}

	@Override
	public FileEntry getFileEntry(long fileEntryId) throws PortalException,
			SystemException {
		try {
			BoxClient client = getBoxClient();
			BoxFile file = getBoxFile(client, fileEntryId);
			return toFileEntry(file);
		} catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public FileEntry getFileEntry(long folderId, String title)
			throws PortalException, SystemException {
		try {
			BoxClient client = getBoxClient();
			BoxFile file = getBoxFile(client, folderId, true, title);
			return toFileEntry(file);
		} catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public List<Object> getFoldersAndFileEntries(long folderId, int start,
			int end, OrderByComparator obc) throws SystemException {
		List<Object> foldersAndFileEntries = null;
		foldersAndFileEntries = getFoldersAndFileEntries(folderId);

		return subList(foldersAndFileEntries, start, end, obc);
	}

	@Override
	public List<Object> getFoldersAndFileEntries(long folderId,
			String[] mimeTypes, int start, int end, OrderByComparator obc)
			throws PortalException, SystemException {

		return getFoldersAndFileEntries(folderId, start, end, obc);
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId)
			throws SystemException {
		List<Object> foldersAndFileEntries = getFoldersAndFileEntries(folderId);
		return foldersAndFileEntries.size();
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId, String[] mimeTypes)
			throws PortalException, SystemException {
		if (ArrayUtil.isNotEmpty(mimeTypes)) {
			List<Folder> folders = getFolders(folderId);
			BoxClient client;
			try {
				client = getBoxClient();
				List<String> documentIds = getDocumentIds(
						client, folderId, mimeTypes);
				return folders.size() + documentIds.size();
			} catch (BoxRestException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();
			} catch (BoxServerException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();
			} catch (AuthFatalFailureException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();
			}
		}

		List<Object> foldersAndFileEntries = getFoldersAndFileEntries(folderId);

		return foldersAndFileEntries.size();
	}

	protected List<Folder> getFolders(long parentFolderId) {
		Map<Long, List<Folder>> foldersCache = _foldersCache.get();

		List<Folder> folders = foldersCache.get(parentFolderId);
		BoxClient client = null;

		try {
			client = getBoxClient();

			if (folders == null) {
				List<String> folderIds = getBoxFolderIds(client, parentFolderId);
				folders = new ArrayList<Folder>(folderIds.size());

				for (String folderId : folderIds) {
					folders.add(toFolder(client, folderId));
				}

				foldersCache.put(parentFolderId, folders);
			}
		} catch (NotAuthenticatedToBoxException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (BoxRestException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (BoxServerException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (AuthFatalFailureException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (SystemException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (PortalException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
		}

		return folders;
	}

	protected Folder toFolder(BoxClient client, String folderId) throws PrincipalException, SystemException {
		try {
			BoxFolder folder = client.getFoldersManager().getFolder(folderId, null);
			return toFolder(folder);
		} catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	protected List<String> getBoxFolderIds(BoxClient client,
			long parentFolderId) throws AuthFatalFailureException, BoxRestException, BoxServerException, PortalException, SystemException {
		String objectId = toFolderId(client, parentFolderId);
		BoxFoldersManager fom = client.getFoldersManager();
		BoxFilesManager fm = client.getFilesManager();
		BoxFolder root = fom.getFolder(objectId, null);
		BoxFile retVal = null;
		List<BoxFolder> foldersToSearch = new ArrayList<BoxFolder>();
		foldersToSearch.add(root);
		List<String> boxFolderIds = new ArrayList<String>();
		while (!foldersToSearch.isEmpty()) {
			BoxFolder top = foldersToSearch.remove(0);
			BoxCollection items = top.getItemCollection();

			for (BoxTypedObject item : items.getEntries()) {
				if (item instanceof BoxFolder) {
					boxFolderIds.add(((BoxFolder)item).getId());
				} else if (item instanceof BoxFile) {

					// do nothing

				}
			}
		}

		return boxFolderIds;
	}
//	protected BoxFolder getBoxFolder(BoxClient client, String path, String name) {
//
//	}

	protected List<Object> getFoldersAndFileEntries(long folderId) throws RepositoryException {
		cacheFoldersAndFileEntries(folderId);

		Map<Long, List<Object>> foldersAndFileEntriesCache =
				_foldersAndFileEntriesCache.get();

			return foldersAndFileEntriesCache.get(folderId);
	}

	protected void cacheFoldersAndFileEntries(long folderId) throws RepositoryException {
		Map<Long, List<Object>> foldersAndFileEntriesCache =
				_foldersAndFileEntriesCache.get();
			List<Object> foldersAndFileEntries = new ArrayList<Object>();
			List<Folder> folders = new ArrayList<Folder>();
			List<FileEntry> fileEntries = new ArrayList<FileEntry>();
			boolean isRoot = false;
			try {
				BoxClient client = getBoxClient();
				BoxFoldersManager fom = client.getFoldersManager();
				BoxFilesManager fm = client.getFilesManager();
				long folderIdOrig = folderId;
				RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByPrimaryKey(folderId);

				if (repositoryEntry == null) {
					DLFolder dlFolder = DLFolderUtil.fetchByPrimaryKey(folderId);

					if (dlFolder == null) {
						throw new NoSuchFolderException(
							"No Box folder with {folderId=" + folderId + "}");
					}
					else if (!dlFolder.isMountPoint()) {
						throw new RepositoryException(
							"Box repository should not be used with {folderId=" +
								folderId + "}");
					}

					System.out.println(dlFolder.getName());
					//begin serach for folder

					if (dlFolder.getParentFolderId() == 0) {
						folderId = Long.parseLong(BOX_ACCOUNT_ROOT_FOLDER_ID);
						isRoot = true;
					}

				} else {

					System.out.println(repositoryEntry.getMappedId());
					folderId = Long.parseLong(repositoryEntry.getMappedId());
				}

				System.out.println(folderId);

				BoxFolder boxParentFolder = null;
				try {
					boxParentFolder = fom.getFolder(String.valueOf(folderId), null);
				} catch (Exception e) {
					e.printStackTrace();
				}

				Folder parentFolder = null;

				if (boxParentFolder.getId().equalsIgnoreCase(BOX_ACCOUNT_ROOT_FOLDER_ID)) {
					Object[] ids = getRepositoryEntryIds(String.valueOf(folderIdOrig));
					long folderId2 = (Long)ids[0];
					String uuid = (String)ids[1];
					parentFolder = new com.bvakili.portlet.integration.box.model.BoxFolder(this, uuid, folderId2, boxParentFolder);

				} else {
					toFolder(boxParentFolder);
				}

				List<BoxTypedObject> items = boxParentFolder.getItemCollection().getEntries();

				for (BoxTypedObject item : items) {
					if (item instanceof BoxFolder) {
						com.bvakili.portlet.integration.box.model.BoxFolder boxFolder = (com.bvakili.portlet.integration.box.model.BoxFolder)toFolder(((BoxFolder)item));
						boxFolder.setParentFolder(parentFolder);
						foldersAndFileEntries.add(boxFolder);
						folders.add(boxFolder);
					} else if (item instanceof BoxFile) {
						BoxFileEntry boxFileEntry = (BoxFileEntry)toFileEntry((BoxFile)item);
						boxFileEntry.setParentFolder(parentFolder);
						foldersAndFileEntries.add(boxFileEntry);
						fileEntries.add(boxFileEntry);
					}
				}

				if (!isRoot) {
					foldersAndFileEntriesCache.put(folderId, foldersAndFileEntries);
				} else {
					foldersAndFileEntriesCache.put(folderIdOrig, foldersAndFileEntries);
				}

				Map<Long, List<Folder>> foldersCache = _foldersCache.get();

				if (!isRoot) {
					foldersCache.put(folderId, folders);
				} else {
					foldersCache.put(folderIdOrig, folders);
				}

				Map<Long, List<FileEntry>> fileEntriesCache =
					_fileEntriesCache.get();

				if (!isRoot) {
					fileEntriesCache.put(folderId, fileEntries);
				} else {
					fileEntriesCache.put(folderIdOrig, fileEntries);
				}
			} catch (SystemException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();
			}
			catch (Exception e) {
				throw new RepositoryException(e);
			}

	}

	@Override
	public FileEntry getFileEntryByUuid(String uuid) throws PortalException,
			SystemException {
		try {
			BoxClient client = getBoxClient();
			RepositoryEntry repositoryEntry = RepositoryEntryUtil.findByUUID_G(uuid, getGroupId());
			String objectId = repositoryEntry.getMappedId();
			return toFileEntry(client.getFilesManager().getFile(objectId, null));
		} catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	protected BoxFile getBoxFile(BoxClient client, long folderId, boolean b,
			String title) throws AuthFatalFailureException, BoxRestException, BoxServerException, PortalException, SystemException {
		String objectId = toFolderId(client, folderId);

		BoxFoldersManager fom = client.getFoldersManager();
		BoxFilesManager fm = client.getFilesManager();
		BoxFolder root = fom.getFolder("0", null);
		BoxFile retVal = null;
		List<BoxFolder> foldersToSearch = new ArrayList<BoxFolder>();
		foldersToSearch.add(root);

		while (!foldersToSearch.isEmpty()) {
			BoxFolder top = foldersToSearch.remove(0);
			BoxCollection items = top.getItemCollection();

			for (BoxTypedObject item : items.getEntries()) {
				if (item instanceof BoxFolder) {
					foldersToSearch.add(((BoxFolder)item));
				} else if (item instanceof BoxFile) {
					if (title.equalsIgnoreCase(((BoxFile)item).getName())) {
						retVal = ((BoxFile)item);
						break;
					}
				}
			}
		}

		return retVal;
	}

	protected BoxFile getBoxFile(BoxClient client, long fileEntryId) throws PortalException, SystemException {

		try {
			String versionSeriesId = toFileEntryId(fileEntryId);

			BoxFile file = client.getFilesManager().getFile(versionSeriesId, null);
			return file;
		}
		catch (Exception e) {
			processException(e);

			throw new RepositoryException(e);
		}
	}

	@Override
	public List<FileEntry> getFileEntries(long folderId, int start, int end,
			OrderByComparator obc) throws PortalException, SystemException {
		List<FileEntry> superFiles = super.getFileEntries(folderId, start, end, obc);
		superFiles.addAll(getFiles());
		return superFiles;
	}

	@Override
	public List<FileEntry> getFileEntries(long folderId, long fileEntryTypeId,
			int start, int end, OrderByComparator obc) throws PortalException,
			SystemException {
		return new ArrayList<FileEntry>();
	}

	@Override
	public List<FileEntry> getFileEntries(long folderId, String[] mimeTypes,
			int start, int end, OrderByComparator obc) throws PortalException,
			SystemException {
		System.out.println("getFileEntries....");
		BoxClient client;
		List<FileEntry> fileEntries = null;
		try {
			client = getBoxClient();
			Map<Long, List<FileEntry>> fileEntriesCache = _fileEntriesCache.get();
			fileEntries = fileEntriesCache.get(folderId);

			if ((fileEntries == null) || (mimeTypes != null)) {
				fileEntries = new ArrayList<FileEntry>();
				List<String> documentIds = getDocumentIds(
						client, folderId, mimeTypes);

				for (String documentId : documentIds) {
					FileEntry fileEntry = toFileEntry(client, documentId);

					fileEntries.add(fileEntry);
				}

				if (mimeTypes == null) {
					fileEntriesCache.put(folderId, fileEntries);
				}
			}
		} catch (BoxRestException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (BoxServerException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (AuthFatalFailureException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
		}

		return subList(fileEntries, start, end, obc);
	}

	protected <E> List<E> subList(List<E> list, int start,
			int end, OrderByComparator obc) {
			if ((obc != null) &&
				((obc instanceof RepositoryModelCreateDateComparator) ||
				 (obc instanceof RepositoryModelModifiedDateComparator) ||
				 (obc instanceof RepositoryModelNameComparator) ||
				 (obc instanceof RepositoryModelSizeComparator))) {
				list = ListUtil.sort(list, obc);
			}

		return ListUtil.subList(list, start, end);
	}

	protected FileEntry toFileEntry(BoxClient client, String objectId) throws AuthFatalFailureException, BoxRestException, BoxServerException, PortalException, SystemException {

		return toFileEntry(client, objectId, false);
	}

	protected FileEntry toFileEntry(BoxClient client, String fileId, boolean strict) throws AuthFatalFailureException, BoxRestException, BoxServerException, PortalException, SystemException {
		BoxFilesManager fm = client.getFilesManager();
		BoxFile bf = fm.getFile(fileId, null);
		Object[] ids = null;
		ids = getRepositoryEntryIds(fileId);
		long fileEntryId = (Long)ids[0];
		String uuid = (String)ids[1];
		FileEntry fileEntry = new BoxFileEntry(this, uuid, fileEntryId, bf);
		FileVersion fileVersion = null;
		try {
			fileVersion = fileEntry.getFileVersion();
		} catch (Exception e) {
			if (strict) {
				if ((Boolean)ids[2]) {
					RepositoryEntryUtil.remove(fileEntryId);
				}

				if (e instanceof SystemException) {
					throw (SystemException)e;
				} else {
					processException(e);
					throw new RepositoryException();
				}
			} else {
				_log.error("Unable to update asset", e);
			}

			dlAppHelperLocalService.checkAssetEntry(
					PrincipalThreadLocal.getUserId(), fileEntry, fileVersion);
		}

		return fileEntry;
	}

	protected List<String> getDocumentIds(BoxClient client, long folderId,
			String[] mimeTypes) throws AuthFatalFailureException, BoxRestException, BoxServerException {
		BoxFoldersManager fom = client.getFoldersManager();
		BoxFilesManager fm = client.getFilesManager();
		BoxFolder root = fom.getFolder("0", null);
		List<String> retVal = new ArrayList<String>();
		List<BoxFolder> foldersToSearch = new ArrayList<BoxFolder>();
		foldersToSearch.add(root);

		while (!foldersToSearch.isEmpty()) {
			BoxFolder top = foldersToSearch.remove(0);
			BoxCollection items = top.getItemCollection();

			for (BoxTypedObject item : items.getEntries()) {
				if (item instanceof BoxFolder) {
					foldersToSearch.add(((BoxFolder)item));
				} else if (item instanceof BoxFile) {
					String mimetype = MimeTypesUtil.getContentType(((BoxFile)item).getName());

					for (String mt : mimeTypes) {
						if (mt.equalsIgnoreCase(mimetype)) {
							if (!retVal.contains(mt)) {
								retVal.add(item.getId());
							}
						}
					}
				}
			}
		}

		return retVal;
	}

	public InputStream getFileInputStream(String fileId) throws AuthFatalFailureException, BoxRestException, BoxServerException, NotAuthenticatedToBoxException, SystemException {
		return getBoxClient().getFilesManager().downloadFile(fileId, null);
	}

	protected List<FileEntry> getFiles() throws NotAuthenticatedToBoxException, SystemException {
		try {
			List<FileEntry> fileEntries = new ArrayList<FileEntry>();
			BoxClient client = getBoxClient();

			// TODO: find a way to convert between Liferay folder and Box folder

			BoxFolder boxFolder = client.getFoldersManager().getFolder("0",null);
			ArrayList<BoxTypedObject> folderEntries = boxFolder.getItemCollection().getEntries();
			int folderSize = folderEntries.size();

			for (int i = 0; i <= folderSize-1; i++) {
				BoxTypedObject folderEntry = folderEntries.get(i);
				String name = (folderEntry instanceof BoxItem) ? ((BoxItem)folderEntry).getName() : "(unknown)";
				System.out.println("i:" + i + ", Type:" + folderEntry.getType() + ", Id:" + folderEntry.getId() + ", Name:" + name);
				//FileEntry x = FileEntry
			}
		} catch (AuthFatalFailureException e) {
			e.printStackTrace();
		} catch (BoxRestException e) {
			e.printStackTrace();
		} catch (BoxServerException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean isRefreshBeforePermissionCheck() {
		return false; //_cmisRepositoryHandler.isRefreshBeforePermissionCheck();
	}

	protected void processException(Exception e) throws PrincipalException {
		if ((e instanceof AuthFatalFailureException ||
			 e instanceof BoxJSONException ||
			 e instanceof BoxServerException ||
			 e instanceof BoxUnexpectedHttpStatusException)) {

				String message = e.getMessage();

				try {
					message = "Issue with connectivity to Box.com: " + message;
				}
				catch (Exception e2) {
				}

				throw new PrincipalException(message, e);
			}
	}

	public FileEntry toFileEntry(BoxFile boxFile) throws PortalException, SystemException {

		return toFileEntry(boxFile, false);
	}

	protected FileEntry toFileEntry(BoxFile boxFile, boolean strict) throws PortalException, SystemException {
		Object[] ids = getRepositoryEntryIds(boxFile.getId());
		long fileEntryId = (Long)ids[0];
		String uuid = (String)ids[1];
		FileEntry fileEntry = new BoxFileEntry(this, uuid, fileEntryId, boxFile);
		FileVersion fileVersion = null;
		try {
			fileVersion = fileEntry.getFileVersion();
		}
		catch (Exception e) {
			if (strict) {
				if ((Boolean)ids[2]) {
					RepositoryEntryUtil.remove(fileEntryId);
				}
				else if (e instanceof SystemException) {
					throw (SystemException)e;
				}
				else {
					processException(e);

					throw new RepositoryException(e);
				}
			} else {
				_log.error("Unable to update asset", e);
			}
		}

		dlAppHelperLocalService.checkAssetEntry(
				PrincipalThreadLocal.getUserId(), fileEntry, fileVersion);

		return fileEntry;
	}

	protected String toFileEntryId(long fileEntryId)
			throws PortalException, SystemException {

			RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByPrimaryKey(
				fileEntryId);

			if (repositoryEntry == null) {
				throw new NoSuchFileEntryException(
					"No Box.com file entry with {fileEntryId=" + fileEntryId + "}");
			}

			return repositoryEntry.getMappedId();
	}

	public FileVersion toFileVersion(BoxFile boxFile) throws SystemException {
		Object[] ids = getRepositoryEntryIds(boxFile.getId());

		long fileVersionId = (Long)ids[0];
		String uuid = (String)ids[1];

		return new com.bvakili.portlet.integration.box.model.BoxFileVersion(this, uuid, fileVersionId, boxFile);
	}

	public Folder toFolder(BoxFolder boxFolder) throws SystemException {
		Object[] ids = getRepositoryEntryIds(boxFolder.getId());
		long folderId = (Long)ids[0];
		String uuid = (String)ids[1];
		return new com.bvakili.portlet.integration.box.model.BoxFolder(this, uuid, folderId, boxFolder);
	}

}