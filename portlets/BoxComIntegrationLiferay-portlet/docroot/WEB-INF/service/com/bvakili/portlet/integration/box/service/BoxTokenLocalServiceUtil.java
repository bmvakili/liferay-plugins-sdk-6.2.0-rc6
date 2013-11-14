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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for BoxToken. This utility wraps
 * {@link com.bvakili.portlet.integration.box.service.impl.BoxTokenLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Bijan Vakili
 * @see BoxTokenLocalService
 * @see com.bvakili.portlet.integration.box.service.base.BoxTokenLocalServiceBaseImpl
 * @see com.bvakili.portlet.integration.box.service.impl.BoxTokenLocalServiceImpl
 * @generated
 */
public class BoxTokenLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.bvakili.portlet.integration.box.service.impl.BoxTokenLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the box token to the database. Also notifies the appropriate model listeners.
	*
	* @param boxToken the box token
	* @return the box token that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken addBoxToken(
		com.bvakili.portlet.integration.box.model.BoxToken boxToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addBoxToken(boxToken);
	}

	/**
	* Creates a new box token with the primary key. Does not add the box token to the database.
	*
	* @param boxTokenId the primary key for the new box token
	* @return the new box token
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken createBoxToken(
		long boxTokenId) {
		return getService().createBoxToken(boxTokenId);
	}

	/**
	* Deletes the box token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param boxTokenId the primary key of the box token
	* @return the box token that was removed
	* @throws PortalException if a box token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken deleteBoxToken(
		long boxTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteBoxToken(boxTokenId);
	}

	/**
	* Deletes the box token from the database. Also notifies the appropriate model listeners.
	*
	* @param boxToken the box token
	* @return the box token that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken deleteBoxToken(
		com.bvakili.portlet.integration.box.model.BoxToken boxToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteBoxToken(boxToken);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.bvakili.portlet.integration.box.model.BoxToken fetchBoxToken(
		long boxTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchBoxToken(boxTokenId);
	}

	/**
	* Returns the box token with the primary key.
	*
	* @param boxTokenId the primary key of the box token
	* @return the box token
	* @throws PortalException if a box token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken getBoxToken(
		long boxTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getBoxToken(boxTokenId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> getBoxTokens(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBoxTokens(start, end);
	}

	/**
	* Returns the number of box tokens.
	*
	* @return the number of box tokens
	* @throws SystemException if a system exception occurred
	*/
	public static int getBoxTokensCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBoxTokensCount();
	}

	/**
	* Updates the box token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param boxToken the box token
	* @return the box token that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken updateBoxToken(
		com.bvakili.portlet.integration.box.model.BoxToken boxToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateBoxToken(boxToken);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static void createNewToken(long companyId,
		java.lang.String fullName, long userId, java.lang.String callbackURL,
		com.liferay.portal.model.Repository repo,
		com.box.boxjavalibv2.dao.BoxOAuthToken bToken) {
		getService()
			.createNewToken(companyId, fullName, userId, callbackURL, repo,
			bToken);
	}

	public static java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> getActiveTokens(
		long repositoryId) {
		return getService().getActiveTokens(repositoryId);
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this interface directly. Always use {@link com.bvakili.portlet.integration.box.service.BoxTokenLocalServiceUtil} to access the box token local service.
	*/
	public static java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> getActiveTokens() {
		return getService().getActiveTokens();
	}

	public static void clearService() {
		_service = null;
	}

	public static BoxTokenLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					BoxTokenLocalService.class.getName());

			if (invokableLocalService instanceof BoxTokenLocalService) {
				_service = (BoxTokenLocalService)invokableLocalService;
			}
			else {
				_service = new BoxTokenLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(BoxTokenLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(BoxTokenLocalService service) {
	}

	private static BoxTokenLocalService _service;
}