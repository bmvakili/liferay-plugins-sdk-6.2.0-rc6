package com.bvakili.portlet.integration.box.model;

import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.restclientv2.exceptions.BoxRestException;

import com.bvakili.portlet.integration.box.repository.BoxRepository;
import com.bvakili.portlet.integration.box.service.BoxRepositoryLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.service.DLAppHelperLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.InputStream;
import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class BoxFileVersion extends BoxModel implements FileVersion {

	public BoxFileVersion(BoxRepository boxRepository, String uuid,
			long fileVersionId, BoxFile boxFile) {
		_boxRepository = boxRepository;
		_boxFile = boxFile;
		_fileVersionId = fileVersionId;
		_uuid = uuid;
	}

	@Override
	public Object clone() {
		BoxFileVersion boxFileEntry = new BoxFileVersion(
				_boxRepository, _uuid, _fileVersionId, _boxFile);

			boxFileEntry.setCompanyId(getCompanyId());
			boxFileEntry.setFileVersionId(getFileEntryId());
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
	public Map<String, Serializable> getAttributes() {
		return new HashMap<String, Serializable>();
	}

	public String getChangeLog() {
		return ""; // TODO: look up comments / change log handling in Box.com
	}

	@Override
	public long getCompanyId() {
		return _boxRepository.getCompanyId();
	}

	@Override
	public InputStream getContentStream(boolean incrementCounter)
			throws PortalException, SystemException {
		InputStream inputStream = null;
		try {
			DLAppHelperLocalServiceUtil.getFileAsStream(
				PrincipalThreadLocal.getUserId(), getFileEntry(),
				incrementCounter);
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
	public Date getCreateDate() {
		return _boxFile.dateCreatedAt();
	}

	@Override
	public String getDescription() {
		return _boxFile.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return null;
	}

	@Override
	public String getExtension() {
		return FileUtil.getExtension(getTitle());
	}

	@Override
	public String getExtraSettings() {
		return null;
	}

	@Override
	public FileEntry getFileEntry() throws PortalException, SystemException {
		BoxFile boxFile = null;

		try {
			List<BoxFile> files = _boxRepository.getAllFileVersions(_boxFile.getId());

			if (files == null || files.size() == 0) {
				boxFile = files.get(0);
			} else {
				boxFile = _boxFile;
			}
		} catch (BoxRestException e) {
			e.printStackTrace();
		} catch (BoxServerException e) {
			e.printStackTrace();
		} catch (AuthFatalFailureException e) {
			e.printStackTrace();
		}

		return BoxRepositoryLocalServiceUtil.toFileEntry(_boxRepository.getRepositoryId(), boxFile);
	}

	@Override
	public long getFileEntryId() {
		try {
			return getFileEntry().getFileEntryId();
		}
		catch (NoSuchFileEntryException nsfee) {
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return 0;
	}

	@Override
	public long getFileVersionId() {
		return _fileVersionId;
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
	public String getMimeType() {

		// unsupported; box.com doesn't currently expose mime types

		return MimeTypesUtil.getContentType(getTitle());
	}

	@Override
	public Object getModel() {
		return _boxFile;
	}

	@Override
	public Class<?> getModelClass() {
		return BoxFileVersion.class;
	}

	@Override
	public String getModelClassName() {
		return BoxFileVersion.class.getName();
	}

	@Override
	public Date getModifiedDate() {
		return _boxFile.dateModifiedAt();
	}

	@Override
	public long getPrimaryKey() {
		return _fileVersionId;
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
	public long getSize() {
		return _boxFile.getSize().longValue();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(FileVersion.class);
	}

	@Override
	public int getStatus() {
		return 0;
	}

	@Override
	public long getStatusByUserId() {
		return 0;
	}

	@Override
	public String getStatusByUserName() {
		return null;
	}

	@Override
	public String getStatusByUserUuid() throws SystemException {
		return null;
	}

	@Override
	public Date getStatusDate() {
		return getModifiedDate();
	}

	@Override
	public String getTitle() {
		return _boxFile.getName();
	}

	@Override
	public long getUserId() {
		try {
			return UserLocalServiceUtil.getDefaultUserId(getCompanyId());
		}
		catch (Exception e) {
			return 0;
		}
	}

	@Override
	public String getUserName() {
		//return _boxFile.getCreatedBy().getName();
		String emailAddress = _boxFile.getCreatedBy().getLogin();
		User user = getUser(emailAddress);

		if (user == null) {
			return StringPool.BLANK;
		}
		else {
			return user.getFullName();
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		try {
			User user = UserLocalServiceUtil.getDefaultUser(getCompanyId());

			return user.getUserUuid();
		}
		catch (Exception e) {
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

	@Override
	public boolean isApproved() {
		return false;
	}

	@Override
	public boolean isDefaultRepository() {
		return false;
	}

	@Override
	public boolean isDraft() {
		return false;
	}

	@Override
	public boolean isEscapedModel() {
		return false;
	}

	@Override
	public boolean isExpired() {
		return false;
	}

	@Override
	public boolean isPending() {
		return false;
	}

	@Override
	public void setCompanyId(long companyId) {
		_boxRepository.setCompanyId(companyId);
	}

	@Override
	public void setCreateDate(Date date) {
	}

	public void setFileVersionId(long fileVersionId) {
		_fileVersionId = fileVersionId;
	}

	@Override
	public void setGroupId(long groupId) {
		_boxRepository.setGroupId(groupId);
	}

	@Override
	public void setModifiedDate(Date date) {
	}

	public void setPrimaryKey(long primaryKey) {
		setFileVersionId(primaryKey);
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
	public FileVersion toEscapedModel() {
		return this;
	}

	@Override
	public FileVersion toUnescapedModel() {
		return this;
	}

	@Override
	protected BoxRepository getBoxRepository() {
		return _boxRepository;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 4088377659115621593L;

	private static Log _log = LogFactoryUtil.getLog(BoxFileVersion.class);

	private BoxFile _boxFile;
	private BoxRepository _boxRepository;
	private long _fileVersionId;
	private String _uuid;

}