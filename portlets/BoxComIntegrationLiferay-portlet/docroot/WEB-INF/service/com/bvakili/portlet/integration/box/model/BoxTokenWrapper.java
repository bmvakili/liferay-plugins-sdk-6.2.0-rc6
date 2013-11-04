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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link BoxToken}.
 * </p>
 *
 * @author Bijan Vakili
 * @see BoxToken
 * @generated
 */
public class BoxTokenWrapper implements BoxToken, ModelWrapper<BoxToken> {
	public BoxTokenWrapper(BoxToken boxToken) {
		_boxToken = boxToken;
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

	/**
	* Returns the primary key of this box token.
	*
	* @return the primary key of this box token
	*/
	@Override
	public long getPrimaryKey() {
		return _boxToken.getPrimaryKey();
	}

	/**
	* Sets the primary key of this box token.
	*
	* @param primaryKey the primary key of this box token
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_boxToken.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the box token ID of this box token.
	*
	* @return the box token ID of this box token
	*/
	@Override
	public long getBoxTokenId() {
		return _boxToken.getBoxTokenId();
	}

	/**
	* Sets the box token ID of this box token.
	*
	* @param boxTokenId the box token ID of this box token
	*/
	@Override
	public void setBoxTokenId(long boxTokenId) {
		_boxToken.setBoxTokenId(boxTokenId);
	}

	/**
	* Returns the company ID of this box token.
	*
	* @return the company ID of this box token
	*/
	@Override
	public long getCompanyId() {
		return _boxToken.getCompanyId();
	}

	/**
	* Sets the company ID of this box token.
	*
	* @param companyId the company ID of this box token
	*/
	@Override
	public void setCompanyId(long companyId) {
		_boxToken.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this box token.
	*
	* @return the user ID of this box token
	*/
	@Override
	public long getUserId() {
		return _boxToken.getUserId();
	}

	/**
	* Sets the user ID of this box token.
	*
	* @param userId the user ID of this box token
	*/
	@Override
	public void setUserId(long userId) {
		_boxToken.setUserId(userId);
	}

	/**
	* Returns the user uuid of this box token.
	*
	* @return the user uuid of this box token
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _boxToken.getUserUuid();
	}

	/**
	* Sets the user uuid of this box token.
	*
	* @param userUuid the user uuid of this box token
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_boxToken.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this box token.
	*
	* @return the user name of this box token
	*/
	@Override
	public java.lang.String getUserName() {
		return _boxToken.getUserName();
	}

	/**
	* Sets the user name of this box token.
	*
	* @param userName the user name of this box token
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_boxToken.setUserName(userName);
	}

	/**
	* Returns the create date of this box token.
	*
	* @return the create date of this box token
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _boxToken.getCreateDate();
	}

	/**
	* Sets the create date of this box token.
	*
	* @param createDate the create date of this box token
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_boxToken.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this box token.
	*
	* @return the modified date of this box token
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _boxToken.getModifiedDate();
	}

	/**
	* Sets the modified date of this box token.
	*
	* @param modifiedDate the modified date of this box token
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_boxToken.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the access token of this box token.
	*
	* @return the access token of this box token
	*/
	@Override
	public java.lang.String getAccessToken() {
		return _boxToken.getAccessToken();
	}

	/**
	* Sets the access token of this box token.
	*
	* @param accessToken the access token of this box token
	*/
	@Override
	public void setAccessToken(java.lang.String accessToken) {
		_boxToken.setAccessToken(accessToken);
	}

	/**
	* Returns the refresh token of this box token.
	*
	* @return the refresh token of this box token
	*/
	@Override
	public java.lang.String getRefreshToken() {
		return _boxToken.getRefreshToken();
	}

	/**
	* Sets the refresh token of this box token.
	*
	* @param refreshToken the refresh token of this box token
	*/
	@Override
	public void setRefreshToken(java.lang.String refreshToken) {
		_boxToken.setRefreshToken(refreshToken);
	}

	/**
	* Returns the access token expiration of this box token.
	*
	* @return the access token expiration of this box token
	*/
	@Override
	public long getAccessTokenExpiration() {
		return _boxToken.getAccessTokenExpiration();
	}

	/**
	* Sets the access token expiration of this box token.
	*
	* @param accessTokenExpiration the access token expiration of this box token
	*/
	@Override
	public void setAccessTokenExpiration(long accessTokenExpiration) {
		_boxToken.setAccessTokenExpiration(accessTokenExpiration);
	}

	/**
	* Returns the refresh token expiration of this box token.
	*
	* @return the refresh token expiration of this box token
	*/
	@Override
	public long getRefreshTokenExpiration() {
		return _boxToken.getRefreshTokenExpiration();
	}

	/**
	* Sets the refresh token expiration of this box token.
	*
	* @param refreshTokenExpiration the refresh token expiration of this box token
	*/
	@Override
	public void setRefreshTokenExpiration(long refreshTokenExpiration) {
		_boxToken.setRefreshTokenExpiration(refreshTokenExpiration);
	}

	/**
	* Returns the callback u r l of this box token.
	*
	* @return the callback u r l of this box token
	*/
	@Override
	public java.lang.String getCallbackURL() {
		return _boxToken.getCallbackURL();
	}

	/**
	* Sets the callback u r l of this box token.
	*
	* @param callbackURL the callback u r l of this box token
	*/
	@Override
	public void setCallbackURL(java.lang.String callbackURL) {
		_boxToken.setCallbackURL(callbackURL);
	}

	/**
	* Returns the expired of this box token.
	*
	* @return the expired of this box token
	*/
	@Override
	public boolean getExpired() {
		return _boxToken.getExpired();
	}

	/**
	* Returns <code>true</code> if this box token is expired.
	*
	* @return <code>true</code> if this box token is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _boxToken.isExpired();
	}

	/**
	* Sets whether this box token is expired.
	*
	* @param expired the expired of this box token
	*/
	@Override
	public void setExpired(boolean expired) {
		_boxToken.setExpired(expired);
	}

	/**
	* Returns the repository ID of this box token.
	*
	* @return the repository ID of this box token
	*/
	@Override
	public long getRepositoryId() {
		return _boxToken.getRepositoryId();
	}

	/**
	* Sets the repository ID of this box token.
	*
	* @param repositoryId the repository ID of this box token
	*/
	@Override
	public void setRepositoryId(long repositoryId) {
		_boxToken.setRepositoryId(repositoryId);
	}

	@Override
	public boolean isNew() {
		return _boxToken.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_boxToken.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _boxToken.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_boxToken.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _boxToken.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _boxToken.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_boxToken.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _boxToken.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_boxToken.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_boxToken.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_boxToken.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new BoxTokenWrapper((BoxToken)_boxToken.clone());
	}

	@Override
	public int compareTo(BoxToken boxToken) {
		return _boxToken.compareTo(boxToken);
	}

	@Override
	public int hashCode() {
		return _boxToken.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<BoxToken> toCacheModel() {
		return _boxToken.toCacheModel();
	}

	@Override
	public BoxToken toEscapedModel() {
		return new BoxTokenWrapper(_boxToken.toEscapedModel());
	}

	@Override
	public BoxToken toUnescapedModel() {
		return new BoxTokenWrapper(_boxToken.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _boxToken.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _boxToken.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_boxToken.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BoxTokenWrapper)) {
			return false;
		}

		BoxTokenWrapper boxTokenWrapper = (BoxTokenWrapper)obj;

		if (Validator.equals(_boxToken, boxTokenWrapper._boxToken)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public BoxToken getWrappedBoxToken() {
		return _boxToken;
	}

	@Override
	public BoxToken getWrappedModel() {
		return _boxToken;
	}

	@Override
	public void resetOriginalValues() {
		_boxToken.resetOriginalValues();
	}

	private BoxToken _boxToken;
}