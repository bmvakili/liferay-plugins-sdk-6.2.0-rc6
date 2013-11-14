package com.bvakili.portlet.integration.box.store;

import com.box.boxjavalibv2.BoxClient;
import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.dao.BoxTypedObject;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.requests.requestobjects.BoxFolderRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;

import com.bvakili.portlet.integration.box.NotAuthenticatedToBoxException;
import com.bvakili.portlet.integration.box.repository.BoxRepository;
import com.bvakili.portlet.integration.box.repository.BoxRepositoryHandler;

import com.liferay.portal.kernel.bean.ClassLoaderBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Repository;
import com.liferay.portal.repository.proxy.BaseRepositoryProxyBean;
import com.liferay.portal.service.RepositoryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileException;
import com.liferay.portlet.documentlibrary.store.BaseStore;
import com.liferay.portlet.documentlibrary.util.DLUtil;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import static com.bvakili.portlet.integration.box.util.Constants.BOX_ACCOUNT_ROOT_FOLDER_ID;
@Deprecated
public class BoxStore extends BaseStore {

	private static BoxFolder _systemRootDir;

	public BoxStore() {

		// folder with id 0 represents Box Account's root folder

//		_systemRootDir = getFolder()
//				BoxClient a;
	}

	@Override
	public void addDirectory(long companyId, long repositoryId, String dirName)
			throws PortalException, SystemException {
		//BoxRepository repository = BoxRepositoryLocalServiceUtil.getbox

		try {
			BoxClient client = getClient(repositoryId);
			com.box.boxjavalibv2.dao.BoxFolder folder = getRepositoryFolder(client, companyId, repositoryId);
			String[] dirNames = StringUtil.split(dirName, CharPool.SLASH);

			for (String curDirName : dirNames) {
				BoxFolder subFolder = getFolder(client, folder, curDirName);

				if (subFolder == null) {
					subFolder = createFolder(client, folder, curDirName);
				}

				folder = subFolder;
			}
		} catch (BoxRestException e) {
			e.printStackTrace();
		} catch (BoxServerException e) {
			e.printStackTrace();
		} catch (AuthFatalFailureException e) {
			e.printStackTrace();
		}
	}

	private BoxClient getClient(long repositoryId) throws PortalException,
			SystemException, NotAuthenticatedToBoxException, BoxRestException,
			BoxServerException, AuthFatalFailureException {
		BoxRepository repo = getBoxRepository(repositoryId);
		BoxClient client = repo.getBoxClient();
		return client;
	}

	private BoxRepository getBoxRepository(long repositoryId) throws PortalException,
			SystemException {
		Repository repo = RepositoryLocalServiceUtil.getRepository(repositoryId);
		BoxRepository boxRepo = null;

		if (repo instanceof BoxRepository) {
			boxRepo = (BoxRepository)repo;
		} else if (repo instanceof BoxRepositoryHandler) {
			boxRepo = (BoxRepository) ((BoxRepositoryHandler)repo).getBoxRepository();
		} else if (repo instanceof BaseRepositoryProxyBean) {
			BaseRepositoryProxyBean baseRepositoryProxyBean =
					(BaseRepositoryProxyBean)repo;

			ClassLoaderBeanHandler classLoaderBeanHandler =
				(ClassLoaderBeanHandler)ProxyUtil.getInvocationHandler(
					baseRepositoryProxyBean.getProxyBean());

			Object bean = classLoaderBeanHandler.getBean();

			if (bean instanceof BoxRepositoryHandler) {
				boxRepo = (BoxRepository) ((BoxRepositoryHandler)bean).getBoxRepository();
			}
		}

		return boxRepo;
	}

	private BoxFolder getRepositoryFolder(BoxClient client, long companyId, long repositoryId) {
		com.box.boxjavalibv2.dao.BoxFolder companyFolder = getCompanyFolder(client, companyId);
		String name = String.valueOf(repositoryId);
		BoxFolder repositoryFolder = getFolder(client, companyFolder, name);

		if (repositoryFolder == null) {
			repositoryFolder = createFolder(client, companyFolder, name);
		}

		return repositoryFolder;
	}

	@Override
	public void updateFile(long companyId, long repositoryId, String fileName,
			String versionLabel, InputStream is) throws PortalException,
			SystemException {

		// TODO Auto-generated method stub

	}

	private BoxFolder getFolder(BoxClient client, BoxFolder parentFolder,
			String name) {

			List< BoxTypedObject > list = parentFolder.getItemCollection().getEntries();

			for (BoxTypedObject obj : list) {
				if (obj.getType().equalsIgnoreCase("folder") || obj instanceof com.box.boxjavalibv2.dao.BoxFolder) {
					com.box.boxjavalibv2.dao.BoxFolder folder = (com.box.boxjavalibv2.dao.BoxFolder)obj;

					if (name.equalsIgnoreCase(folder.getName())) {
						return folder;
					}
				}
			}

		return null;
	}

	private List<BoxFolder> getFolders(BoxFolder folder) {
		List<BoxFolder> folders = new ArrayList<BoxFolder>();
		List< BoxTypedObject > list = folder.getItemCollection().getEntries();

		for (BoxTypedObject obj : list) {
			if (obj.getType().equalsIgnoreCase("folder") || obj instanceof com.box.boxjavalibv2.dao.BoxFolder) {
				folders.add((com.box.boxjavalibv2.dao.BoxFolder)obj);
			}
		}

		return folders;
	}

	private BoxFolder getCompanyFolder(BoxClient client, long companyId) {
		String name = String.valueOf(companyId);
		com.box.boxjavalibv2.dao.BoxFolder folder = getFolder(client, BOX_ACCOUNT_ROOT_FOLDER_ID, name);

		if (folder == null) {
			folder = createFolder(client, BOX_ACCOUNT_ROOT_FOLDER_ID, name);
		}

		return folder;
	}

	private BoxFolder createFolder(BoxClient client,
			String parentFolderId, String name) {
		BoxFolder retVal = null;
		try {
			BoxFolderRequestObject req = BoxFolderRequestObject.createFolderRequestObject(name, parentFolderId);
			retVal = client.getFoldersManager().createFolder(req);

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

		return retVal;
	}

	private BoxFolder getFolder(BoxClient client, String folderId, String name) {
		try {
			com.box.boxjavalibv2.dao.BoxFolder rootFolder = client.getFoldersManager().getFolder(folderId, null);
			List< BoxTypedObject > list = rootFolder.getItemCollection().getEntries();

			for (BoxTypedObject obj : list) {
				if (obj.getType().equalsIgnoreCase("folder") || obj instanceof com.box.boxjavalibv2.dao.BoxFolder) {
					com.box.boxjavalibv2.dao.BoxFolder folder = (com.box.boxjavalibv2.dao.BoxFolder)obj;

					if (name.equalsIgnoreCase(folder.getName())) {
						return folder;
					}
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

		return null;
	}

	@Override
	public void addFile(long companyId, long repositoryId, String fileName,
			InputStream is) throws PortalException, SystemException {
		updateFile(companyId, repositoryId, fileName, VERSION_DEFAULT, is);
	}

	@Override
	public void checkRoot(long companyId) throws SystemException {
	}
//
//	@Override
//	public void copyFileVersion(long companyId, long repositoryId,
//			String fileName, String fromVersionLabel, String toVersionLabel)
//			throws PortalException, SystemException {
//		BoxFolder versioningFolder =
//		super.copyFileVersion(companyId, repositoryId, fileName, fromVersionLabel,
//				toVersionLabel);
//	}
//
	@Override
	public void deleteDirectory(long companyId, long repositoryId,
			String dirName) throws PortalException, SystemException {
		BoxClient client;
		try {
			client = getClient(repositoryId);
			BoxFolder repositoryFolder = getRepositoryFolder(client, companyId, repositoryId);
			BoxFolder dir = getFolder(client, repositoryFolder, dirName);

			if (dir != null) {
				client.getFoldersManager().deleteFolder(dir.getId(), null);
			}
		} catch (BoxRestException e) {
			e.printStackTrace();
		} catch (BoxServerException e) {
			e.printStackTrace();
		} catch (AuthFatalFailureException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteFile(long companyId, long repositoryId, String fileName)
			throws PortalException, SystemException {
		BoxClient client;
		try {
			client = getClient(repositoryId);
			BoxFolder versioningFolder = getVersioningFolder(client, companyId, repositoryId, fileName, false);

			if (versioningFolder == null) {
				throw new NoSuchFileException();
			}

			client.getFoldersManager().deleteFolder(versioningFolder.getId(), null);
		} catch (BoxRestException e) {
			e.printStackTrace();
		} catch (BoxServerException e) {
			e.printStackTrace();
		} catch (AuthFatalFailureException e) {
			e.printStackTrace();
		}

	}

	private BoxFolder getVersioningFolder(BoxClient client, long companyId, long repositoryId,
			String fileName, boolean create) {
		BoxFolder repoFolder = getRepositoryFolder(client, companyId, repositoryId);
		BoxFolder versionFolder = repoFolder;
		String[] dirNames = StringUtil.split(fileName, CharPool.SLASH);

		for (String dirName : dirNames) {
			BoxFolder subFolder = getFolder(client, versionFolder, dirName);

			if (create && (subFolder == null)) {
				subFolder = createFolder(client, versionFolder, dirName);
			}

			versionFolder = subFolder;
		}

		return versionFolder;
	}

	@Override
	public void deleteFile(long companyId, long repositoryId, String fileName,
			String versionLabel) throws PortalException, SystemException {
		try {
			BoxClient client = getClient(repositoryId);
			BoxFile file = getVersionedDocument(client, companyId, repositoryId, fileName, versionLabel);
			client.getFilesManager().deleteFile(file.getId(), null);
		} catch (BoxRestException e) {
			e.printStackTrace();
		} catch (BoxServerException e) {
			e.printStackTrace();
		} catch (AuthFatalFailureException e) {
			e.printStackTrace();
		}

	}

	private BoxFile getVersionedDocument(BoxClient client, long companyId,
			long repositoryId, String fileName, String versionLabel) throws NoSuchFileException {
		BoxFolder versioningFolder = getVersioningFolder(client, companyId, repositoryId, fileName, false);

		if (versioningFolder == null) {
			throw new NoSuchFileException();
		}

		BoxFile file = getBoxFile(client, versioningFolder, versionLabel);

		if (file == null) {
			throw new NoSuchFileException();
		}

		return file;
	}

	private BoxFile getBoxFile(BoxClient client, BoxFolder parentFolder,
			String name) {

		List< BoxTypedObject > list = parentFolder.getItemCollection().getEntries();

		for (BoxTypedObject obj : list) {
			if (obj.getType().equalsIgnoreCase("file") || obj instanceof com.box.boxjavalibv2.dao.BoxFile) {
				com.box.boxjavalibv2.dao.BoxFile folder = (com.box.boxjavalibv2.dao.BoxFile)obj;

				if (name.equalsIgnoreCase(folder.getName())) {
					return folder;
				}
			}
		}

		return null;
	}

	@Override
	public InputStream getFileAsStream(long companyId, long repositoryId,
			String fileName, String versionLabel) throws PortalException,
			SystemException {
		BoxClient client;
		try {
			client = getClient(repositoryId);

			if (Validator.isNull(versionLabel)) {
				versionLabel = getHeadVersionLabel(
					client, companyId, repositoryId, fileName);
			}
		} catch (BoxRestException e) {
			e.printStackTrace();
		} catch (BoxServerException e) {
			e.printStackTrace();
		} catch (AuthFatalFailureException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String getHeadVersionLabel(BoxClient client, long companyId,
			long repositoryId, String fileName) throws NoSuchFileException {
		BoxFolder versioningFolder = getVersioningFolder(client, companyId, repositoryId, fileName, false);

		if (versioningFolder == null) {
			throw new NoSuchFileException();
		}

		List<BoxFolder> folders = getFolders(versioningFolder);

		String headVersionLabel = VERSION_DEFAULT;

		for (BoxFolder folder : folders) {
			String versionLabel = folder.getName();

			if (DLUtil.compareVersions(versionLabel, headVersionLabel) > 0) {
				headVersionLabel = versionLabel;
			}
		}

		return headVersionLabel;
	}

	@Override
	public String[] getFileNames(long companyId, long repositoryId)
			throws SystemException {
		try {
			BoxClient client = getClient(repositoryId);
			BoxFolder folder = getRepositoryFolder(client, companyId, repositoryId);
			List<BoxFolder> folders = getFolders(folder);
			String[] fileNames = new String[folders.size()];

			for (int i = 0; i < folders.size(); i++) {
				BoxFolder curFolder = folders.get(i);

				fileNames[i] = curFolder.getName();
			}

			return fileNames;
		} catch (NotAuthenticatedToBoxException e) {
			e.printStackTrace();
		} catch (PortalException e) {

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
		}

		return null;
	}

	@Override
	public String[] getFileNames(long companyId, long repositoryId,
			String dirName) throws PortalException, SystemException {
		try {
			BoxClient client = getClient(repositoryId);
			BoxFolder folder = getRepositoryFolder(client, companyId, repositoryId);

			String[] dirNames = StringUtil.split(dirName, CharPool.SLASH);

			for (String curDirName : dirNames) {
				BoxFolder subFolder = getFolder(client, folder, curDirName);

				if (subFolder == null) {
					subFolder = createFolder(client, folder, curDirName);
				}

				folder = subFolder;
			}

			List<BoxFolder> folders = getFolders(folder);

			String[] fileNames = new String[folders.size()];

			for (int i = 0; i < folders.size(); i++) {
				BoxFolder curFolder = folders.get(i);

				String fileName = curFolder.getName();

				fileNames[i] = dirName.concat(StringPool.SLASH).concat(fileName);
			}

			return fileNames;
		} catch (NotAuthenticatedToBoxException e) {
			e.printStackTrace();
		} catch (PortalException e) {

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
		}

		return null;
	}

	@Override
	public long getFileSize(long companyId, long repositoryId, String fileName)
			throws PortalException, SystemException {

		// TODO Auto-generated method stub

		return 0;
	}

	@Override
	public boolean hasDirectory(long companyId, long repositoryId,
			String dirName) throws PortalException, SystemException {

		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean hasFile(long companyId, long repositoryId, String fileName,
			String versionLabel) throws PortalException, SystemException {

		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public void move(String srcDir, String destDir) throws SystemException {

		// TODO Auto-generated method stub

	}

	@Override
	public void updateFile(long companyId, long repositoryId,
			long newRepositoryId, String fileName) throws PortalException,
			SystemException {

		// TODO Auto-generated method stub

	}

	@Override
	public void updateFile(long companyId, long repositoryId, String fileName,
			String newFileName) throws PortalException, SystemException {

		// TODO Auto-generated method stub

	}

	private BoxFolder createFolder(BoxClient client, BoxFolder parentFolder,
			String name) {
		BoxFolder retVal = null;
		try {
			BoxFolderRequestObject req = BoxFolderRequestObject.createFolderRequestObject(name, parentFolder.getId());
			retVal = client.getFoldersManager().createFolder(req);

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

		return retVal;
	}

}