/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.bvakili.portlet.integration.box.model;

import com.bvakili.portlet.integration.box.service.BoxTokenLocalServiceUtil;
import com.bvakili.portlet.integration.box.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bijan Vakili
 */
public class BoxTokenClp extends BaseModelImpl<BoxToken> implements BoxToken {
	public BoxTokenClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return BoxToken.class;
	}

	@Override
	public String getModelClassName() {
		return BoxToken.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _boxTokenId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBoxTokenId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _boxTokenId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("boxTokenId", getBoxTokenId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accessToken", getAccessToken());
		attributes.put("refreshToken", getRefreshToken());
		attributes.put("accessTokenExpiration", getAccessTokenExpiration());
		attributes.put("refreshTokenExpiration", getRefreshTokenExpiration());
		attributes.put("callbackURL", getCallbackURL());
		attributes.put("expired", getExpired());
		attributes.put("repositoryId", getRepositoryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long boxTokenId = (Long)attributes.get("boxTokenId");

		if (boxTokenId != null) {
			setBoxTokenId(boxTokenId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String accessToken = (String)attributes.get("accessToken");

		if (accessToken != null) {
			setAccessToken(accessToken);
		}

		String refreshToken = (String)attributes.get("refreshToken");

		if (refreshToken != null) {
			setRefreshToken(refreshToken);
		}

		Long accessTokenExpiration = (Long)attributes.get(
				"accessTokenExpiration");

		if (accessTokenExpiration != null) {
			setAccessTokenExpiration(accessTokenExpiration);
		}

		Long refreshTokenExpiration = (Long)attributes.get(
				"refreshTokenExpiration");

		if (refreshTokenExpiration != null) {
			setRefreshTokenExpiration(refreshTokenExpiration);
		}

		String callbackURL = (String)attributes.get("callbackURL");

		if (callbackURL != null) {
			setCallbackURL(callbackURL);
		}

		Boolean expired = (Boolean)attributes.get("expired");

		if (expired != null) {
			setExpired(expired);
		}

		Long repositoryId = (Long)attributes.get("repositoryId");

		if (repositoryId != null) {
			setRepositoryId(repositoryId);
		}
	}

	@Override
	public long getBoxTokenId() {
		return _boxTokenId;
	}

	@Override
	public void setBoxTokenId(long boxTokenId) {
		_boxTokenId = boxTokenId;

		if (_boxTokenRemoteModel != null) {
			try {
				Class<?> clazz = _boxTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setBoxTokenId", long.class);

				method.invoke(_boxTokenRemoteModel, boxTokenId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_boxTokenRemoteModel != null) {
			try {
				Class<?> clazz = _boxTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_boxTokenRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_boxTokenRemoteModel != null) {
			try {
				Class<?> clazz = _boxTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_boxTokenRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_boxTokenRemoteModel != null) {
			try {
				Class<?> clazz = _boxTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_boxTokenRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_boxTokenRemoteModel != null) {
			try {
				Class<?> clazz = _boxTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_boxTokenRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_boxTokenRemoteModel != null) {
			try {
				Class<?> clazz = _boxTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_boxTokenRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAccessToken() {
		return _accessToken;
	}

	@Override
	public void setAccessToken(String accessToken) {
		_accessToken = accessToken;

		if (_boxTokenRemoteModel != null) {
			try {
				Class<?> clazz = _boxTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setAccessToken", String.class);

				method.invoke(_boxTokenRemoteModel, accessToken);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getRefreshToken() {
		return _refreshToken;
	}

	@Override
	public void setRefreshToken(String refreshToken) {
		_refreshToken = refreshToken;

		if (_boxTokenRemoteModel != null) {
			try {
				Class<?> clazz = _boxTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setRefreshToken", String.class);

				method.invoke(_boxTokenRemoteModel, refreshToken);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAccessTokenExpiration() {
		return _accessTokenExpiration;
	}

	@Override
	public void setAccessTokenExpiration(long accessTokenExpiration) {
		_accessTokenExpiration = accessTokenExpiration;

		if (_boxTokenRemoteModel != null) {
			try {
				Class<?> clazz = _boxTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setAccessTokenExpiration",
						long.class);

				method.invoke(_boxTokenRemoteModel, accessTokenExpiration);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getRefreshTokenExpiration() {
		return _refreshTokenExpiration;
	}

	@Override
	public void setRefreshTokenExpiration(long refreshTokenExpiration) {
		_refreshTokenExpiration = refreshTokenExpiration;

		if (_boxTokenRemoteModel != null) {
			try {
				Class<?> clazz = _boxTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setRefreshTokenExpiration",
						long.class);

				method.invoke(_boxTokenRemoteModel, refreshTokenExpiration);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCallbackURL() {
		return _callbackURL;
	}

	@Override
	public void setCallbackURL(String callbackURL) {
		_callbackURL = callbackURL;

		if (_boxTokenRemoteModel != null) {
			try {
				Class<?> clazz = _boxTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCallbackURL", String.class);

				method.invoke(_boxTokenRemoteModel, callbackURL);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getExpired() {
		return _expired;
	}

	@Override
	public boolean isExpired() {
		return _expired;
	}

	@Override
	public void setExpired(boolean expired) {
		_expired = expired;

		if (_boxTokenRemoteModel != null) {
			try {
				Class<?> clazz = _boxTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setExpired", boolean.class);

				method.invoke(_boxTokenRemoteModel, expired);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getRepositoryId() {
		return _repositoryId;
	}

	@Override
	public void setRepositoryId(long repositoryId) {
		_repositoryId = repositoryId;

		if (_boxTokenRemoteModel != null) {
			try {
				Class<?> clazz = _boxTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setRepositoryId", long.class);

				method.invoke(_boxTokenRemoteModel, repositoryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getBoxTokenRemoteModel() {
		return _boxTokenRemoteModel;
	}

	public void setBoxTokenRemoteModel(BaseModel<?> boxTokenRemoteModel) {
		_boxTokenRemoteModel = boxTokenRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _boxTokenRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_boxTokenRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			BoxTokenLocalServiceUtil.addBoxToken(this);
		}
		else {
			BoxTokenLocalServiceUtil.updateBoxToken(this);
		}
	}

	@Override
	public BoxToken toEscapedModel() {
		return (BoxToken)ProxyUtil.newProxyInstance(BoxToken.class.getClassLoader(),
			new Class[] { BoxToken.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		BoxTokenClp clone = new BoxTokenClp();

		clone.setBoxTokenId(getBoxTokenId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAccessToken(getAccessToken());
		clone.setRefreshToken(getRefreshToken());
		clone.setAccessTokenExpiration(getAccessTokenExpiration());
		clone.setRefreshTokenExpiration(getRefreshTokenExpiration());
		clone.setCallbackURL(getCallbackURL());
		clone.setExpired(getExpired());
		clone.setRepositoryId(getRepositoryId());

		return clone;
	}

	@Override
	public int compareTo(BoxToken boxToken) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), boxToken.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BoxTokenClp)) {
			return false;
		}

		BoxTokenClp boxToken = (BoxTokenClp)obj;

		long primaryKey = boxToken.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{boxTokenId=");
		sb.append(getBoxTokenId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", accessToken=");
		sb.append(getAccessToken());
		sb.append(", refreshToken=");
		sb.append(getRefreshToken());
		sb.append(", accessTokenExpiration=");
		sb.append(getAccessTokenExpiration());
		sb.append(", refreshTokenExpiration=");
		sb.append(getRefreshTokenExpiration());
		sb.append(", callbackURL=");
		sb.append(getCallbackURL());
		sb.append(", expired=");
		sb.append(getExpired());
		sb.append(", repositoryId=");
		sb.append(getRepositoryId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.bvakili.portlet.integration.box.model.BoxToken");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>boxTokenId</column-name><column-value><![CDATA[");
		sb.append(getBoxTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessToken</column-name><column-value><![CDATA[");
		sb.append(getAccessToken());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>refreshToken</column-name><column-value><![CDATA[");
		sb.append(getRefreshToken());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessTokenExpiration</column-name><column-value><![CDATA[");
		sb.append(getAccessTokenExpiration());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>refreshTokenExpiration</column-name><column-value><![CDATA[");
		sb.append(getRefreshTokenExpiration());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>callbackURL</column-name><column-value><![CDATA[");
		sb.append(getCallbackURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>expired</column-name><column-value><![CDATA[");
		sb.append(getExpired());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>repositoryId</column-name><column-value><![CDATA[");
		sb.append(getRepositoryId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _boxTokenId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _accessToken;
	private String _refreshToken;
	private long _accessTokenExpiration;
	private long _refreshTokenExpiration;
	private String _callbackURL;
	private boolean _expired;
	private long _repositoryId;
	private BaseModel<?> _boxTokenRemoteModel;
}