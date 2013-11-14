package com.bvakili.portlet.integration.box.model;

import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.restclientv2.exceptions.BoxRestException;
import com.bvakili.portlet.integration.box.NoActiveTokensFoundException;
import com.bvakili.portlet.integration.box.NotAuthenticatedToBoxException;
import com.bvakili.portlet.integration.box.repository.BoxRepository;
import com.bvakili.portlet.integration.box.service.BoxRepositoryLocalServiceUtil;
import com.bvakili.portlet.integration.box.util.BoxUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.CMISRepositoryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileVersionException;
import com.liferay.portlet.documentlibrary.service.DLAppHelperLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLUtil;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class BoxFileEntry extends BoxModel implements FileEntry {

	public BoxFileEntry(BoxRepository boxRepository, String uuid, long fileEntryId, BoxFile boxFile) {
		_boxRepository = boxRepository;
		_uuid = uuid;
		_fileEntryId = fileEntryId;
		_boxFile = boxFile;
	}

	@Override
	public Object clone() {
		BoxFileEntry boxFileEntry = new BoxFileEntry(
			_boxRepository, _uuid, _fileEntryId, _boxFile);

		boxFileEntry.setCompanyId(getCompanyId());
		boxFileEntry.setFileEntryId(getFileEntryId());
		boxFileEntry.setGroupId(getGroupId());

		try {
			boxFileEntry.setParentFolder(getParentFolder());
		}
		catch (Exception e) {
		}

		boxFileEntry.setPrimaryKey(getPrimaryKey());

		return boxFileEntry;
	}

	@Override
	public boolean containsPermission(PermissionChecker permissionChecker,
			String actionId) throws PortalException, SystemException {
		return containsPermission(_boxFile, actionId);
	}

	@Override
	public Map<String, Serializable> getAttributes() {
		return new HashMap<String, Serializable>();
	}

	public long getCompanyId() {
		return _boxRepository.getCompanyId();
	}

	@Override
	public InputStream getContentStream() throws PortalException,
			SystemException {
		InputStream inputStream = null;
		try {
			DLAppHelperLocalServiceUtil.getFileAsStream(
				PrincipalThreadLocal.getUserId(), this, true);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		try {
			inputStream = _boxRepository.getFileInputStream(_boxFile.getId());
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

		return inputStream;
	}

	@Override
	public InputStream getContentStream(String version) throws PortalException, SystemException {
		if (Validator.isNull(version)) {
			return getContentStream();
		}

		InputStream inputStream = null;

		try {
			for (BoxFile document : getAllVersions(_boxFile.getId())) {

				// TODO: does the version actually match the boxfile version?

				if (version.equals(document.getVersionNumber())) {
					try {
						DLAppHelperLocalServiceUtil.getFileAsStream(
							PrincipalThreadLocal.getUserId(), this, true);
					}
					catch (Exception e) {
						_log.error(e, e);
					}

					try {
						inputStream = _boxRepository.getFileInputStream(_boxFile.getId());
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

					return inputStream;
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

		throw new NoSuchFileVersionException(
			"No Box file version with {fileEntryId=" + getFileEntryId() +
				", version=" + version + "}");
	}

	@Override
	public Date getCreateDate() {
		return _boxFile.dateCreatedAt();
	}

	@Override
	public String getExtension() {
		return FileUtil.getExtension(getTitle());
	}

	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public FileVersion getFileVersion() throws PortalException, SystemException {
		return getLatestFileVersion();
	}

	@Override
	public FileVersion getFileVersion(String version) throws PortalException,
			SystemException {
		if (Validator.isNull(version)) {
			return getFileVersion();
		}

		try {
			for (BoxFile document : getAllVersions(_boxFile.getId())) {
				if (version.equals(document.getVersionNumber())) {
					return CMISRepositoryLocalServiceUtil.toFileVersion(
						getRepositoryId(), document);
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

		throw new NoSuchFileVersionException(
			"No CMIS file version with {fileEntryId=" + getFileEntryId() +
				", version=" + version + "}");
	}

	@Override
	public List<FileVersion> getFileVersions(int status) throws SystemException {

		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public Folder getFolder() {
		Folder parentFolder = null;

		try {
			parentFolder = super.getParentFolder();

			if (parentFolder != null) {
				return parentFolder;
			}
		}
		catch (Exception e) {
		}

		try {
			BoxUtil.findParentFolder(_boxFile);
			parentFolder = BoxRepositoryLocalServiceUtil.toFolder(getRepositoryId(), _boxFile);
		} catch (Exception e) {
			_log.error(e, e);
		}

		return parentFolder;
	}

	@Override
	public long getFolderId() {
		Folder folder = getFolder();

		return folder.getFolderId();
	}

	@Override
	public long getGroupId() {
		return _boxRepository.getGroupId();
	}

	@Override
	public String getIcon() {
		return DLUtil.getFileIcon(getExtension());
	}

	@Override
	public FileVersion getLatestFileVersion() throws PortalException,
			SystemException {
		if (_latestFileVersion != null) {
			return _latestFileVersion;
		}

		List<BoxFile> versions = new ArrayList<BoxFile>();
		try {
			versions = getAllVersions(_boxFile.getId());
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

		if (!versions.isEmpty()) {
			BoxFile latest = versions.get(0);
			_latestFileVersion = BoxRepositoryLocalServiceUtil.toFileVersion(getRepositoryId(), latest);
		} else {
			_latestFileVersion = BoxRepositoryLocalServiceUtil.toFileVersion(getRepositoryId(), _boxFile);
		}

		return _latestFileVersion;
	}

	@Override
	public Lock getLock() {
		return null;
	}

	@Override
	public String getMimeType() {
		return MimeTypesUtil.getContentType(getTitle());
	}

	@Override
	public String getMimeType(String version) {
		if (Validator.isNull(version)) {
			return getMimeType();
		}

		try {
			for (BoxFile file : getAllVersions(_boxFile.getId())) {
				if (!version.equalsIgnoreCase(file.getVersionNumber())) {
					continue;
				}

				return MimeTypesUtil.getContentType(file.getName());
			}
		} catch (PortalException pe) {
			_log.error(pe, pe);
		} catch (BoxRestException e) {
			_log.error(e, e);
		} catch (BoxServerException e) {
			_log.error(e, e);
		} catch (AuthFatalFailureException e) {
			_log.error(e, e);
		} catch (SystemException e) {
			_log.error(e, e);
		}

		return ContentTypes.APPLICATION_OCTET_STREAM;
	}

	@Override
	public Object getModel() {

		// TODO Auto-generated method stub

		return _boxFile;
	}

	@Override
	public Class<?> getModelClass() {
		return BoxFileEntry.class;
	}

	@Override
	public String getModelClassName() {
		return BoxFileEntry.class.getName();
	}

	@Override
	public Date getModifiedDate() {
		return _boxFile.dateContentModifieddAt();
	}

	@Override
	public long getPrimaryKey() {
		return _fileEntryId;
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return getPrimaryKey();
	}

	@Override
	public int getReadCount() {
		return 0;
	}

	@Override
	public long getRepositoryId() {
		return _boxRepository.getRepositoryId();
	}

	@Override
	public long getSize() {
		return _boxFile.getSize().longValue();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(FileEntry.class);
	}

	@Override
	public String getTitle() {
		return _boxFile.getName();
	}

	@Override
	public long getUserId() {
		try {
			String emailAddress = _boxFile.getCreatedBy().getLogin();
			User user = getUser(emailAddress);

			if (user == null) {
				return 0;
			}
			else {
				return user.getUserId();
			}
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public String getUserName() {
		try {
		String emailAddress = _boxFile.getCreatedBy().getLogin();
		User user = getUser(emailAddress);

		if (user == null) {
			return StringPool.BLANK;
		}
		else {
			return user.getFullName();
		}
		} catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		try {
		String emailAddress = _boxFile.getCreatedBy().getLogin();
		User user = getUser(emailAddress);
		try {
			return user.getUserUuid();
		}
		catch (Exception e) {
		}

		return StringPool.BLANK;
		} catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public String getVersion() {
		return GetterUtil.getString(_boxFile.getVersionNumber());
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link BoxFileVersion#getUserId()}
	 */
	@Override
	public long getVersionUserId() {
		long versionUserId = 0;

		try {
			FileVersion fileVersion = getFileVersion();

			versionUserId = fileVersion.getUserId();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return versionUserId;
	}

	@Override
	public String getVersionUserName() {

		// TODO Auto-generated method stub

		return null;
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link
	 *             BoxFileVersion#getUserName()}
	 */
	@Override
	public String getVersionUserUuid() throws SystemException {
		String versionUserName = StringPool.BLANK;

		try {
			FileVersion fileVersion = getFileVersion();

			versionUserName = fileVersion.getUserName();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return versionUserName;
	}

	@Override
	public boolean hasLock() {
		//unsupported
		return false;
	}

	@Override
	public boolean isCheckedOut() {
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
	public boolean isInTrash() {
		return false;
	}

	@Override
	public boolean isInTrashContainer() {
		return false;
	}

	@Override
	public boolean isManualCheckInRequired() {
		return false;
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
	public boolean isSupportsSocial() {
		return false;
	}

	@Override
	public void setCompanyId(long companyId) {
		_boxRepository.setCompanyId(companyId);;
	}

	@Override
	public void setCreateDate(Date date) {
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	@Override
	public void setGroupId(long groupId) {
		_boxRepository.setGroupId(groupId);
	}

	@Override
	public void setModifiedDate(Date date) {
	}

	public void setPrimaryKey(long primaryKey) {
		setFileEntryId(primaryKey);
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
	public FileEntry toEscapedModel() {
		return this;
	}

	@Override
	public FileEntry toUnescapedModel() {
		return this;
	}

	@Override
	protected BoxRepository getBoxRepository() {
		return _boxRepository;
	}

	private List<BoxFile> getAllVersions(String fileId) throws AuthFatalFailureException, BoxRestException, BoxServerException, NotAuthenticatedToBoxException, SystemException, NoActiveTokensFoundException {
		return _boxRepository.getAllFileVersions(fileId);
	}

	private static Log _log = LogFactoryUtil.getLog(BoxFileEntry.class);

	private List<BoxFile> _allVersions;
	private BoxFile _boxFile;
	private BoxRepository _boxRepository;
	private long _fileEntryId;
	private FileVersion _latestFileVersion;
	private String _uuid;

}