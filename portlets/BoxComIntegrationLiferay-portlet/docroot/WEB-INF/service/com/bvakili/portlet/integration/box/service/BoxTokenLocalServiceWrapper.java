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

package com.bvakili.portlet.integration.box.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BoxTokenLocalService}.
 *
 * @author Bijan Vakili
 * @see BoxTokenLocalService
 * @generated
 */
public class BoxTokenLocalServiceWrapper implements BoxTokenLocalService,
	ServiceWrapper<BoxTokenLocalService> {
	public BoxTokenLocalServiceWrapper(
		BoxTokenLocalService boxTokenLocalService) {
		_boxTokenLocalService = boxTokenLocalService;
	}

	/**
	* Adds the box token to the database. Also notifies the appropriate model listeners.
	*
	* @param boxToken the box token
	* @return the box token that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.bvakili.portlet.integration.box.model.BoxToken addBoxToken(
		com.bvakili.portlet.integration.box.model.BoxToken boxToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _boxTokenLocalService.addBoxToken(boxToken);
	}

	/**
	* Creates a new box token with the primary key. Does not add the box token to the database.
	*
	* @param boxTokenId the primary key for the new box token
	* @return the new box token
	*/
	@Override
	public com.bvakili.portlet.integration.box.model.BoxToken createBoxToken(
		long boxTokenId) {
		return _boxTokenLocalService.createBoxToken(boxTokenId);
	}

	/**
	* Deletes the box token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param boxTokenId the primary key of the box token
	* @return the box token that was removed
	* @throws PortalException if a box token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.bvakili.portlet.integration.box.model.BoxToken deleteBoxToken(
		long boxTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _boxTokenLocalService.deleteBoxToken(boxTokenId);
	}

	/**
	* Deletes the box token from the database. Also notifies the appropriate model listeners.
	*
	* @param boxToken the box token
	* @return the box token that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.bvakili.portlet.integration.box.model.BoxToken deleteBoxToken(
		com.bvakili.portlet.integration.box.model.BoxToken boxToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _boxTokenLocalService.deleteBoxToken(boxToken);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _boxTokenLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _boxTokenLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.bvakili.portlet.integration.box.model.impl.BoxTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _boxTokenLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.bvakili.portlet.integration.box.model.impl.BoxTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _boxTokenLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _boxTokenLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _boxTokenLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.bvakili.portlet.integration.box.model.BoxToken fetchBoxToken(
		long boxTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _boxTokenLocalService.fetchBoxToken(boxTokenId);
	}

	/**
	* Returns the box token with the primary key.
	*
	* @param boxTokenId the primary key of the box token
	* @return the box token
	* @throws PortalException if a box token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.bvakili.portlet.integration.box.model.BoxToken getBoxToken(
		long boxTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _boxTokenLocalService.getBoxToken(boxTokenId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _boxTokenLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the box tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.bvakili.portlet.integration.box.model.impl.BoxTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of box tokens
	* @param end the upper bound of the range of box tokens (not inclusive)
	* @return the range of box tokens
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> getBoxTokens(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _boxTokenLocalService.getBoxTokens(start, end);
	}

	/**
	* Returns the number of box tokens.
	*
	* @return the number of box tokens
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getBoxTokensCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _boxTokenLocalService.getBoxTokensCount();
	}

	/**
	* Updates the box token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param boxToken the box token
	* @return the box token that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.bvakili.portlet.integration.box.model.BoxToken updateBoxToken(
		com.bvakili.portlet.integration.box.model.BoxToken boxToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _boxTokenLocalService.updateBoxToken(boxToken);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _boxTokenLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_boxTokenLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _boxTokenLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> getActiveTokens() {
		return _boxTokenLocalService.getActiveTokens();
	}

	@Override
	public java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> getActiveTokens(
		long repositoryId) {
		return _boxTokenLocalService.getActiveTokens(repositoryId);
	}

	@Override
	public void createNewToken(long companyId, java.lang.String fullName,
		long userId, java.lang.String callbackURL,
		com.liferay.portal.model.Repository repo,
		com.box.boxjavalibv2.dao.BoxOAuthToken bToken) {
		_boxTokenLocalService.createNewToken(companyId, fullName, userId,
			callbackURL, repo, bToken);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public BoxTokenLocalService getWrappedBoxTokenLocalService() {
		return _boxTokenLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedBoxTokenLocalService(
		BoxTokenLocalService boxTokenLocalService) {
		_boxTokenLocalService = boxTokenLocalService;
	}

	@Override
	public BoxTokenLocalService getWrappedService() {
		return _boxTokenLocalService;
	}

	@Override
	public void setWrappedService(BoxTokenLocalService boxTokenLocalService) {
		_boxTokenLocalService = boxTokenLocalService;
	}

	private BoxTokenLocalService _boxTokenLocalService;
}