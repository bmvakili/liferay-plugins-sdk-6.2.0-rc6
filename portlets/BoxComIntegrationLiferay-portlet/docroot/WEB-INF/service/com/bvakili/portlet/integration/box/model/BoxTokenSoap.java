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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Bijan Vakili
 * @generated
 */
public class BoxTokenSoap implements Serializable {
	public static BoxTokenSoap toSoapModel(BoxToken model) {
		BoxTokenSoap soapModel = new BoxTokenSoap();

		soapModel.setBoxTokenId(model.getBoxTokenId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccessToken(model.getAccessToken());
		soapModel.setRefreshToken(model.getRefreshToken());
		soapModel.setAccessTokenExpiration(model.getAccessTokenExpiration());
		soapModel.setRefreshTokenExpiration(model.getRefreshTokenExpiration());
		soapModel.setCallbackURL(model.getCallbackURL());
		soapModel.setExpired(model.getExpired());
		soapModel.setRepositoryId(model.getRepositoryId());

		return soapModel;
	}

	public static BoxTokenSoap[] toSoapModels(BoxToken[] models) {
		BoxTokenSoap[] soapModels = new BoxTokenSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BoxTokenSoap[][] toSoapModels(BoxToken[][] models) {
		BoxTokenSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BoxTokenSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BoxTokenSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BoxTokenSoap[] toSoapModels(List<BoxToken> models) {
		List<BoxTokenSoap> soapModels = new ArrayList<BoxTokenSoap>(models.size());

		for (BoxToken model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BoxTokenSoap[soapModels.size()]);
	}

	public BoxTokenSoap() {
	}

	public long getPrimaryKey() {
		return _boxTokenId;
	}

	public void setPrimaryKey(long pk) {
		setBoxTokenId(pk);
	}

	public long getBoxTokenId() {
		return _boxTokenId;
	}

	public void setBoxTokenId(long boxTokenId) {
		_boxTokenId = boxTokenId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getAccessToken() {
		return _accessToken;
	}

	public void setAccessToken(String accessToken) {
		_accessToken = accessToken;
	}

	public String getRefreshToken() {
		return _refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		_refreshToken = refreshToken;
	}

	public long getAccessTokenExpiration() {
		return _accessTokenExpiration;
	}

	public void setAccessTokenExpiration(long accessTokenExpiration) {
		_accessTokenExpiration = accessTokenExpiration;
	}

	public long getRefreshTokenExpiration() {
		return _refreshTokenExpiration;
	}

	public void setRefreshTokenExpiration(long refreshTokenExpiration) {
		_refreshTokenExpiration = refreshTokenExpiration;
	}

	public String getCallbackURL() {
		return _callbackURL;
	}

	public void setCallbackURL(String callbackURL) {
		_callbackURL = callbackURL;
	}

	public boolean getExpired() {
		return _expired;
	}

	public boolean isExpired() {
		return _expired;
	}

	public void setExpired(boolean expired) {
		_expired = expired;
	}

	public long getRepositoryId() {
		return _repositoryId;
	}

	public void setRepositoryId(long repositoryId) {
		_repositoryId = repositoryId;
	}

	private long _boxTokenId;
	private long _companyId;
	private long _userId;
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
}