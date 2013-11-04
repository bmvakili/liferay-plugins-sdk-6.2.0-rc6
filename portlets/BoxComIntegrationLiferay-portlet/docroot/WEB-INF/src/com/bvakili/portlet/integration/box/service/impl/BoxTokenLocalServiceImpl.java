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

package com.bvakili.portlet.integration.box.service.impl;

import com.box.boxjavalibv2.dao.BoxOAuthToken;

import com.bvakili.portlet.integration.box.model.BoxToken;
import com.bvakili.portlet.integration.box.service.base.BoxTokenLocalServiceBaseImpl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The implementation of the box token local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.bvakili.portlet.integration.box.service.BoxTokenLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Bijan Vakili
 * @see com.bvakili.portlet.integration.box.service.base.BoxTokenLocalServiceBaseImpl
 * @see com.bvakili.portlet.integration.box.service.BoxTokenLocalServiceUtil
 */
public class BoxTokenLocalServiceImpl extends BoxTokenLocalServiceBaseImpl {
	public void createNewToken(long companyId, String fullName, long userId, String callbackURL,
			Repository repo, BoxOAuthToken bToken) {
		long id;
		try {
			id = CounterLocalServiceUtil.increment();
			BoxToken token = boxTokenPersistence.create(id);
			token.setAccessToken(bToken.getAccessToken());
			token.setAccessTokenExpiration(System.currentTimeMillis() + (bToken.getExpiresIn()*1000));
			token.setRefreshToken(bToken.getRefreshToken());
			token.setRefreshTokenExpiration(System.currentTimeMillis() + (14*24*60*60*1000));
			token.setRepositoryId(repo.getRepositoryId());
			token.setCompanyId(companyId);
			token.setCreateDate(new Date());
			token.setModifiedDate(new Date());
			token.setCallbackURL(callbackURL);
			token.setUserName(fullName);
			token.setExpired(false);
			token.setUserId(userId);
			token.setNew(true);
			try {
				boxTokenPersistence.update(token);
			} catch (Exception e) {
				try {
					boxTokenPersistence.updateImpl(token);
				} catch (Exception ex) {
					token.persist();
				}
			}
		} catch (SystemException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
		}

	} public List<BoxToken> getActiveTokens(long repositoryId) {
		List<BoxToken> retVal = new ArrayList<BoxToken>();

		try {
			boolean expired = false;
			retVal.addAll(boxTokenPersistence.findByR_E(repositoryId, expired));
		} catch (SystemException e) {
			e.printStackTrace();
		}

		return retVal;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.bvakili.portlet.integration.box.service.BoxTokenLocalServiceUtil} to access the box token local service.
	 */

	public List<BoxToken> getActiveTokens() {
		List<BoxToken> retVal = new ArrayList<BoxToken>();

		try {
			List<Company> companies = companyLocalService.getCompanies();
			boolean expired = false;

			for (Company company : companies) {
				long companyId = company.getCompanyId();
				retVal.addAll(boxTokenPersistence.findByC_E(companyId, expired));
			}
		} catch (SystemException e) {
			e.printStackTrace();
		}

		return retVal;
	}

}