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

package com.bvakili.portlet.integration.box.model.impl;

import com.bvakili.portlet.integration.box.model.BoxToken;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BoxToken in entity cache.
 *
 * @author Bijan Vakili
 * @see BoxToken
 * @generated
 */
public class BoxTokenCacheModel implements CacheModel<BoxToken>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{boxTokenId=");
		sb.append(boxTokenId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accessToken=");
		sb.append(accessToken);
		sb.append(", refreshToken=");
		sb.append(refreshToken);
		sb.append(", accessTokenExpiration=");
		sb.append(accessTokenExpiration);
		sb.append(", refreshTokenExpiration=");
		sb.append(refreshTokenExpiration);
		sb.append(", callbackURL=");
		sb.append(callbackURL);
		sb.append(", expired=");
		sb.append(expired);
		sb.append(", repositoryId=");
		sb.append(repositoryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BoxToken toEntityModel() {
		BoxTokenImpl boxTokenImpl = new BoxTokenImpl();

		boxTokenImpl.setBoxTokenId(boxTokenId);
		boxTokenImpl.setCompanyId(companyId);
		boxTokenImpl.setUserId(userId);

		if (userName == null) {
			boxTokenImpl.setUserName(StringPool.BLANK);
		}
		else {
			boxTokenImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			boxTokenImpl.setCreateDate(null);
		}
		else {
			boxTokenImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			boxTokenImpl.setModifiedDate(null);
		}
		else {
			boxTokenImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (accessToken == null) {
			boxTokenImpl.setAccessToken(StringPool.BLANK);
		}
		else {
			boxTokenImpl.setAccessToken(accessToken);
		}

		if (refreshToken == null) {
			boxTokenImpl.setRefreshToken(StringPool.BLANK);
		}
		else {
			boxTokenImpl.setRefreshToken(refreshToken);
		}

		boxTokenImpl.setAccessTokenExpiration(accessTokenExpiration);
		boxTokenImpl.setRefreshTokenExpiration(refreshTokenExpiration);

		if (callbackURL == null) {
			boxTokenImpl.setCallbackURL(StringPool.BLANK);
		}
		else {
			boxTokenImpl.setCallbackURL(callbackURL);
		}

		boxTokenImpl.setExpired(expired);
		boxTokenImpl.setRepositoryId(repositoryId);

		boxTokenImpl.resetOriginalValues();

		return boxTokenImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		boxTokenId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		accessToken = objectInput.readUTF();
		refreshToken = objectInput.readUTF();
		accessTokenExpiration = objectInput.readLong();
		refreshTokenExpiration = objectInput.readLong();
		callbackURL = objectInput.readUTF();
		expired = objectInput.readBoolean();
		repositoryId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(boxTokenId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (accessToken == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accessToken);
		}

		if (refreshToken == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(refreshToken);
		}

		objectOutput.writeLong(accessTokenExpiration);
		objectOutput.writeLong(refreshTokenExpiration);

		if (callbackURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(callbackURL);
		}

		objectOutput.writeBoolean(expired);
		objectOutput.writeLong(repositoryId);
	}

	public long boxTokenId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String accessToken;
	public String refreshToken;
	public long accessTokenExpiration;
	public long refreshTokenExpiration;
	public String callbackURL;
	public boolean expired;
	public long repositoryId;
}