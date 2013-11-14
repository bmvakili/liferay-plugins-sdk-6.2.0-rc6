package com.bvakili.portlet.integration.box.model;

import com.bvakili.portlet.integration.box.repository.BoxRepository;
import com.bvakili.portlet.integration.box.util.BoxUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class BoxFolder extends BoxModel implements Folder {

	public BoxFolder(BoxRepository _boxRepository, String _uuid,
			long _folderId, com.box.boxjavalibv2.dao.BoxFolder _boxFolder) {
		super();
		this._folderId = _folderId;
		this._uuid = _uuid;
		this._boxRepository = _boxRepository;
		this._boxFolder = _boxFolder;
	}

	@Override
	public Object clone() {
		BoxFolder newFolder = new BoxFolder(_boxRepository, _uuid, _folderId, _boxFolder);
		newFolder.setCompanyId(getCompanyId());
		newFolder.setFolderId(getFolderId());
		newFolder.setGroupId(getGroupId());

		try {
			newFolder.setParentFolder(getParentFolder());
		} catch (Exception e) {
		}

		newFolder.setPrimaryKeyObj(getPrimaryKeyObj());

		return newFolder;
	}

	@Override
	public boolean containsPermission(PermissionChecker permissionChecker,
			String actionId) throws PortalException, SystemException {

		// TODO researh box-liferay permissions integration/mapping

		return true;
	}

	@Override
	public List<Long> getAncestorFolderIds() throws PortalException,
			SystemException {
		List<Long> folderIds = new ArrayList<Long>();

		Folder folder = this;

		while (!folder.isRoot()) {
			folder = folder.getParentFolder();

			folderIds.add(folder.getFolderId());
		}

		return folderIds;
	}

	@Override
	public List<Folder> getAncestors() throws PortalException, SystemException {
		List<Folder> folders = new ArrayList<Folder>();

		Folder folder = this;

		while (!folder.isRoot()) {
			folder = folder.getParentFolder();

			folders.add(folder);
		}

		return folders;
	}

	@Override
	public Map<String, Serializable> getAttributes() {
		return new HashMap<String, Serializable>();
	}

	@Override
	public long getCompanyId() {
		return _boxRepository.getCompanyId();
	}

	@Override
	public Date getCreateDate() {
		return _boxFolder.dateCreatedAt();
	}

	@Override
	public long getFolderId() {
		return _folderId;
	}

	@Override
	public long getGroupId() {
		return _boxRepository.getGroupId();
	}

	@Override
	public Date getLastPostDate() {
		return getModifiedDate();
	}

	@Override
	public Object getModel() {
		return _boxFolder;
	}

	@Override
	public Class<?> getModelClass() {
		return BoxFolder.class;
	}

	@Override
	public String getModelClassName() {
		return BoxFolder.class.getName();
	}

	@Override
	public Date getModifiedDate() {
		return _boxFolder.dateModifiedAt();
	}

	@Override
	public String getName() {
		if (isRoot()) {
			try {
				Folder folder = DLAppLocalServiceUtil.getMountFolder(
						getRepositoryId());
				return folder.getName();
			} catch (Exception e) {
				_log.error(e, e);
			}
		}

		return _boxFolder.getName();
	}

	@Override
	public Folder getParentFolder() throws PortalException, SystemException {
		Folder parentFolder = null;
		try {
			parentFolder = super.getParentFolder();

			if (parentFolder != null) {
				return parentFolder;
			}
		}
		catch (Exception e) {
		}

		if (isRoot()) {
			Folder folder = DLAppLocalServiceUtil.getMountFolder(
				getRepositoryId());

			parentFolder = folder.getParentFolder();
		} else {
			com.box.boxjavalibv2.dao.BoxFolder targetFolder = BoxUtil.findParentFolder(_boxFolder);

			if (targetFolder != null) {
				parentFolder = _boxRepository.toFolder(targetFolder);
			}
		}

		return parentFolder;
	}

	@Override
	public long getParentFolderId() {
		try {
			Folder parentFolder = getParentFolder();

			if (parentFolder != null) {
				return parentFolder.getFolderId();
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
	}

	@Override
	public long getPrimaryKey() {
		return _folderId;
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return getPrimaryKey();
	}

	@Override
	public long getRepositoryId() {
		return _boxRepository.getRepositoryId();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(Folder.class);
	}

	@Override
	public long getUserId() {
		return 0; /**
		String emailAddress = _boxFolder.getCreatedBy().getLogin();
		User user = getUser(emailAddress);

		if (user == null) {
			return 0;
		}
		else {
			return user.getUserId();
		}**/
	}

	@Override
	public String getUserName() {
		return StringPool.BLANK; /**
		String emailAddress = _boxFolder.getCreatedBy().getLogin();
		User user = getUser(emailAddress);

		if (user == null) {
			return StringPool.BLANK;
		}
		else {
			return user.getFullName();
		}**/
	}

	@Override
	public String getUserUuid() throws SystemException {
		return StringPool.BLANK; /**
		String emailAddress = _boxFolder.getCreatedBy().getLogin();
		User user = getUser(emailAddress);
		try {
			return user.getUserUuid();
		}
		catch (Exception e) {
		}

		return StringPool.BLANK;**/
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public boolean hasInheritableLock() {
		return false;
	}

	@Override
	public boolean hasLock() {
		return false;
	}

	@Override
	public boolean isDefaultRepository() {
		return false;
	}

	@Override
	public boolean isEscapedModel() {
		return false;
	}

	@Override
	public boolean isLocked() {
		return false;
	}

	@Override
	public boolean isMountPoint() {
		return false;
	}

	@Override
	public boolean isRoot() {
//		BoxCollection pathCollection = _boxFolder.getPathCollection();
//		List<BoxTypedObject> entries = pathCollection.getEntries();
//		if (entries == null || entries.size() == 0) {
//			return true;
//		}
//		return false;

		if (getParentFolderId() == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isSupportsLocking() {
		return false;
	}

	@Override
	public boolean isSupportsMetadata() {
		return false;
	}

	@Override
	public boolean isSupportsMultipleUpload() {
		return false;
	}

	@Override
	public boolean isSupportsShortcuts() {
		return false;
	}

	@Override
	public boolean isSupportsSocial() {
		return false;
	}

	@Override
	public boolean isSupportsSubscribing() {
		return false;
	}

	@Override
	public void setCompanyId(long companyId) {
		_boxRepository.setCompanyId(companyId);
	}

	@Override
	public void setCreateDate(Date date) {
	}

	public void setFolderId(long _folderId) {
		this._folderId = _folderId;
	}

	@Override
	public void setGroupId(long groupId) {
		_boxRepository.setGroupId(groupId);
	}

	@Override
	public void setModifiedDate(Date date) {
	}

	public void setPrimaryKey(long primaryKey) {
		setFolderId(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public void setUserId(long userId) {
	}

	@Override
	public void setUserName(String userName) {
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public void setUuid(String uuid) {
	}

	@Override
	public Folder toEscapedModel() {
		return this;
	}

	@Override
	public Folder toUnescapedModel() {
		return this;
	}

	@Override
	protected BoxRepository getBoxRepository() {
		return _boxRepository;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -7101726932865332773L;

	private static Log _log = LogFactoryUtil.getLog(BoxFolder.class);

	private com.box.boxjavalibv2.dao.BoxFolder _boxFolder;
	private BoxRepository _boxRepository;
	private long _folderId;
	private String _uuid;

}