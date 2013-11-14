package com.bvakili.portlet.integration.box.repository;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.BaseRepository;
import com.liferay.portal.kernel.repository.BaseRepositoryImpl;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.RepositoryLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

import java.io.InputStream;

import java.util.List;
public abstract class BoxRepositoryHandler extends BaseRepositoryImpl {

	@Override
	public FileEntry addFileEntry(
			long folderId, String sourceFileName, String mimeType, String title,
			String description, String changeLog, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return _baseBoxRepository.addFileEntry(
			folderId, sourceFileName, mimeType, title, description, changeLog,
			is, size, serviceContext);
	}

	@Override
	public Folder addFolder(
			long parentFolderId, String title, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return _baseBoxRepository.addFolder(
			parentFolderId, title, description, serviceContext);
	}

	@Override
	public FileVersion cancelCheckOut(long fileEntryId)
		throws PortalException, SystemException {

		return _baseBoxRepository.cancelCheckOut(fileEntryId);
	}

	@Override
	public void checkInFileEntry(
			long fileEntryId, boolean major, String changeLog,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		_baseBoxRepository.checkInFileEntry(
			fileEntryId, major, changeLog, serviceContext);
	}

	@Override
	public void checkInFileEntry(
			long fileEntryId, String lockUuid, ServiceContext serviceContext)
		throws PortalException, SystemException {

		_baseBoxRepository.checkInFileEntry(
			fileEntryId, lockUuid, serviceContext);
	}

	@Override
	public FileEntry checkOutFileEntry(
			long fileEntryId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return _baseBoxRepository.checkOutFileEntry(
			fileEntryId, serviceContext);
	}

	@Override
	public FileEntry checkOutFileEntry(
			long fileEntryId, String owner, long expirationTime,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return _baseBoxRepository.checkOutFileEntry(
			fileEntryId, owner, expirationTime, serviceContext);
	}

	@Override
	public FileEntry copyFileEntry(
			long groupId, long fileEntryId, long destFolderId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return _baseBoxRepository.copyFileEntry(
			groupId, fileEntryId, destFolderId, serviceContext);
	}

	@Override
	public void deleteFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		_baseBoxRepository.deleteFileEntry(fileEntryId);
	}

	@Override
	public void deleteFolder(long folderId)
		throws PortalException, SystemException {

		_baseBoxRepository.deleteFolder(folderId);
	}

	public BaseBoxRepository getBaseBoxRepository() {
		return _baseBoxRepository;
	}

	public BaseRepository getBoxRepository() {
		return _baseBoxRepository;
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, int start, int end, OrderByComparator obc)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFileEntries(folderId, start, end, obc);
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, long fileEntryTypeId, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFileEntries(
			folderId, fileEntryTypeId, start, end, obc);
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFileEntries(
			folderId, mimeTypes, start, end, obc);
	}

	@Override
	public int getFileEntriesCount(long folderId)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFileEntriesCount(folderId);
	}

	@Override
	public int getFileEntriesCount(long folderId, long fileEntryTypeId)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFileEntriesCount(
			folderId, fileEntryTypeId);
	}

	@Override
	public int getFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFileEntriesCount(folderId, mimeTypes);
	}

	@Override
	public FileEntry getFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFileEntry(fileEntryId);
	}

	@Override
	public FileEntry getFileEntry(long folderId, String title)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFileEntry(folderId, title);
	}

	@Override
	public FileEntry getFileEntryByUuid(String uuid)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFileEntryByUuid(uuid);
	}

	@Override
	public FileVersion getFileVersion(long fileVersionId)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFileVersion(fileVersionId);
	}

	@Override
	public Folder getFolder(long folderId)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFolder(folderId);
	}

	@Override
	public Folder getFolder(long parentFolderId, String title)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFolder(parentFolderId, title);
	}

	@Override
	public List<Folder> getFolders(
			long parentFolderId, boolean includeMountfolders, int start,
			int end, OrderByComparator obc)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFolders(
			parentFolderId, includeMountfolders, start, end, obc);
	}

	@Override
	public List<Object> getFoldersAndFileEntries(
			long folderId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return _baseBoxRepository.getFoldersAndFileEntries(
			folderId, start, end, obc);
	}

	@Override
	public List<Object> getFoldersAndFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFoldersAndFileEntries(
			folderId, mimeTypes, start, end, obc);
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId)
		throws SystemException {

		return _baseBoxRepository.getFoldersAndFileEntriesCount(folderId);
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFoldersAndFileEntriesCount(
			folderId, mimeTypes);
	}

	@Override
	public int getFoldersCount(long parentFolderId, boolean includeMountfolders)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFoldersCount(
			parentFolderId, includeMountfolders);
	}

	@Override
	public int getFoldersFileEntriesCount(List<Long> folderIds, int status)
		throws PortalException, SystemException {

		return _baseBoxRepository.getFoldersFileEntriesCount(folderIds, status);
	}

//	public String getLatestVersionId(String objectId) throws SystemException {
//		return _baseBoxRepository.getLatestVersionId(objectId);
//	}

	public String getLogin() throws SystemException {
		String login = PrincipalThreadLocal.getName();

		if (Validator.isNull(login)) {
			return login;
		}

		try {
			Company company = companyLocalService.getCompany(getCompanyId());

			String authType = company.getAuthType();

			if (!authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				User user = userLocalService.getUser(GetterUtil.getLong(login));

				if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
					login = user.getEmailAddress();
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
					login = user.getScreenName();
				}
			}
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}

		return login;
	}

	@Override
	public List<Folder> getMountFolders(
			long parentFolderId, int start, int end, OrderByComparator obc)
		throws PortalException, SystemException {

		return _baseBoxRepository.getMountFolders(
			parentFolderId, start, end, obc);
	}

	@Override
	public int getMountFoldersCount(long parentFolderId)
		throws PortalException, SystemException {

		return _baseBoxRepository.getMountFoldersCount(parentFolderId);
	}

//	public String getObjectName(String objectId)
//		throws PortalException, SystemException {
//
//		return _baseBoxRepository.getObjectName(objectId);
//	}

//	public List<String> getObjectPaths(String objectId)
//		throws PortalException, SystemException {
//
//		return _baseBoxRepository.getObjectPaths(objectId);
//	}

//	public abstract Session getSession()
//		throws PortalException, SystemException;

	@Override
	public long getRepositoryId() {
		long repoId = super.getRepositoryId();

		if ( repoId == 0) {
			try {
				com.liferay.portal.model.Repository repo = RepositoryLocalServiceUtil.fetchRepository(getGroupId(), "Box", "");

				try {
					repo = RepositoryLocalServiceUtil.getRepository(getGroupId(), "Box", "20");
				} catch (Exception e) {
					try {
						repo = RepositoryLocalServiceUtil.getRepository(getGroupId(), "Box", "");
					} catch (Exception ex) {
						try {
							repo = RepositoryLocalServiceUtil.getRepository(getGroupId(), "Box", null);
						} catch (PortalException e1) {
							e1.printStackTrace();
						}
					}
				}

				repoId = repo.getRepositoryId();
			} catch (SystemException e) {
				e.printStackTrace();
			}
		}

		return repoId;
	}

	@Override
	public void getSubfolderIds(List<Long> folderIds, long folderId)
		throws PortalException, SystemException {

		_baseBoxRepository.getSubfolderIds(folderIds, folderId);
	}

	@Override
	public List<Long> getSubfolderIds(long folderId, boolean recurse)
		throws PortalException, SystemException {

		return _baseBoxRepository.getSubfolderIds(folderId, recurse);
	}

	@Override
	public void initRepository() throws PortalException, SystemException {
		_baseBoxRepository.initRepository();
	}

//	public boolean isCancelCheckOutAllowable(String objectId)
//		throws PortalException, SystemException {
//
//		return _baseBoxRepository.isCancelCheckOutAllowable(objectId);
//	}
//
//	public boolean isCheckInAllowable(String objectId)
//		throws PortalException, SystemException {
//
//		return _baseBoxRepository.isCheckInAllowable(objectId);
//	}
//
//	public boolean isCheckOutAllowable(String objectId)
//		throws PortalException, SystemException {
//
//		return _baseBoxRepository.isCheckOutAllowable(objectId);
//	}

	public boolean isDocumentRetrievableByVersionSeriesId() {
		return true;
	}

	public boolean isRefreshBeforePermissionCheck() {
		return false;
	}

	public boolean isSupportsMinorVersions(String productName) {

		// LPS-20509

		productName = StringUtil.toLowerCase(productName);

		if (productName.contains("filenet") && productName.contains("p8")) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public Lock lockFolder(long folderId)
		throws PortalException, SystemException {

		return _baseBoxRepository.lockFolder(folderId);
	}

	@Override
	public Lock lockFolder(
			long folderId, String owner, boolean inheritable,
			long expirationTime)
		throws PortalException, SystemException {

		return _baseBoxRepository.lockFolder(
			folderId, owner, inheritable, expirationTime);
	}

	@Override
	public FileEntry moveFileEntry(
			long fileEntryId, long newFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return _baseBoxRepository.moveFileEntry(
			fileEntryId, newFolderId, serviceContext);
	}

	@Override
	public Folder moveFolder(
			long folderId, long newParentFolderId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return _baseBoxRepository.moveFolder(
			folderId, newParentFolderId, serviceContext);
	}

	@Override
	public Lock refreshFileEntryLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException, SystemException {

		return _baseBoxRepository.refreshFileEntryLock(
			lockUuid, companyId, expirationTime);
	}

	@Override
	public Lock refreshFolderLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException, SystemException {

		return _baseBoxRepository.refreshFolderLock(
			lockUuid, companyId, expirationTime);
	}

	@Override
	public void revertFileEntry(
			long fileEntryId, String version, ServiceContext serviceContext)
		throws PortalException, SystemException {

		_baseBoxRepository.revertFileEntry(
			fileEntryId, version, serviceContext);
	}

	@Override
	public Hits search(long creatorUserId, int status, int start, int end)
		throws PortalException, SystemException {

		return _baseBoxRepository.search(creatorUserId, status, start, end);
	}

	@Override
	public Hits search(
			long creatorUserId, long folderId, String[] mimeTypes, int status,
			int start, int end)
		throws PortalException, SystemException {

		return _baseBoxRepository.search(
			creatorUserId, folderId, mimeTypes, status, start, end);
	}

	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		return _baseBoxRepository.search(searchContext);
	}

	@Override
	public Hits search(SearchContext searchContext, Query query)
		throws SearchException {

		return _baseBoxRepository.search(searchContext, query);
	}

//	public void setCmisRepository(BaseCmisRepository baseCmisRepository) {
//		_baseBoxRepository = baseCmisRepository;
//	}
//
//	public FileEntry toFileEntry(String objectId)
//		throws PortalException, SystemException {
//
//		return _baseBoxRepository.toFileEntry(objectId);
//	}
//
//	public Folder toFolder(String objectId)
//		throws PortalException, SystemException {
//
//		return _baseBoxRepository.toFolder(objectId);
//	}

	public void setBaseBoxRepository(BaseBoxRepository _baseBoxRepository) {
		this._baseBoxRepository = _baseBoxRepository;
	}

	@Override
	public void unlockFolder(long folderId, String lockUuid)
		throws PortalException, SystemException {

		_baseBoxRepository.unlockFolder(folderId, lockUuid);
	}

	@Override
	public FileEntry updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return _baseBoxRepository.updateFileEntry(
			fileEntryId, sourceFileName, mimeType, title, description,
			changeLog, majorVersion, is, size, serviceContext);
	}

//	public FileEntry updateFileEntry(
//			String objectId, String mimeType, Map<String, Object> properties,
//			InputStream is, String sourceFileName, long size,
//			ServiceContext serviceContext)
//		throws PortalException, SystemException {
//
//		return _baseBoxRepository.updateFileEntry(
//			objectId, mimeType, properties, is, sourceFileName, size,
//			serviceContext);
//	}

	@Override
	public Folder updateFolder(
			long folderId, String title, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return _baseBoxRepository.updateFolder(
			folderId, title, description, serviceContext);
	}

	@Override
	public boolean verifyFileEntryCheckOut(long fileEntryId, String lockUuid)
		throws PortalException, SystemException {

		return _baseBoxRepository.verifyFileEntryCheckOut(
			fileEntryId, lockUuid);
	}

	@Override
	public boolean verifyInheritableLock(long folderId, String lockUuid)
		throws PortalException, SystemException {

		return _baseBoxRepository.verifyInheritableLock(folderId, lockUuid);
	}

	BaseBoxRepository _baseBoxRepository;
//	BoxRepository _baseBoxRepository;

}