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

package com.bvakili.portlet.integration.box.service.persistence;

import com.bvakili.portlet.integration.box.model.BoxToken;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the box token service. This utility wraps {@link BoxTokenPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Bijan Vakili
 * @see BoxTokenPersistence
 * @see BoxTokenPersistenceImpl
 * @generated
 */
public class BoxTokenUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(BoxToken boxToken) {
		getPersistence().clearCache(boxToken);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<BoxToken> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BoxToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BoxToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static BoxToken update(BoxToken boxToken) throws SystemException {
		return getPersistence().update(boxToken);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static BoxToken update(BoxToken boxToken,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(boxToken, serviceContext);
	}

	/**
	* Returns all the box tokens where companyId = &#63; and expired = &#63;.
	*
	* @param companyId the company ID
	* @param expired the expired
	* @return the matching box tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> findByC_E(
		long companyId, boolean expired)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_E(companyId, expired);
	}

	/**
	* Returns a range of all the box tokens where companyId = &#63; and expired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.bvakili.portlet.integration.box.model.impl.BoxTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param expired the expired
	* @param start the lower bound of the range of box tokens
	* @param end the upper bound of the range of box tokens (not inclusive)
	* @return the range of matching box tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> findByC_E(
		long companyId, boolean expired, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_E(companyId, expired, start, end);
	}

	/**
	* Returns an ordered range of all the box tokens where companyId = &#63; and expired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.bvakili.portlet.integration.box.model.impl.BoxTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param expired the expired
	* @param start the lower bound of the range of box tokens
	* @param end the upper bound of the range of box tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching box tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> findByC_E(
		long companyId, boolean expired, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_E(companyId, expired, start, end, orderByComparator);
	}

	/**
	* Returns the first box token in the ordered set where companyId = &#63; and expired = &#63;.
	*
	* @param companyId the company ID
	* @param expired the expired
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching box token
	* @throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException if a matching box token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken findByC_E_First(
		long companyId, boolean expired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_E_First(companyId, expired, orderByComparator);
	}

	/**
	* Returns the first box token in the ordered set where companyId = &#63; and expired = &#63;.
	*
	* @param companyId the company ID
	* @param expired the expired
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching box token, or <code>null</code> if a matching box token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken fetchByC_E_First(
		long companyId, boolean expired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_E_First(companyId, expired, orderByComparator);
	}

	/**
	* Returns the last box token in the ordered set where companyId = &#63; and expired = &#63;.
	*
	* @param companyId the company ID
	* @param expired the expired
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching box token
	* @throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException if a matching box token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken findByC_E_Last(
		long companyId, boolean expired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_E_Last(companyId, expired, orderByComparator);
	}

	/**
	* Returns the last box token in the ordered set where companyId = &#63; and expired = &#63;.
	*
	* @param companyId the company ID
	* @param expired the expired
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching box token, or <code>null</code> if a matching box token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken fetchByC_E_Last(
		long companyId, boolean expired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_E_Last(companyId, expired, orderByComparator);
	}

	/**
	* Returns the box tokens before and after the current box token in the ordered set where companyId = &#63; and expired = &#63;.
	*
	* @param boxTokenId the primary key of the current box token
	* @param companyId the company ID
	* @param expired the expired
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next box token
	* @throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException if a box token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken[] findByC_E_PrevAndNext(
		long boxTokenId, long companyId, boolean expired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_E_PrevAndNext(boxTokenId, companyId, expired,
			orderByComparator);
	}

	/**
	* Removes all the box tokens where companyId = &#63; and expired = &#63; from the database.
	*
	* @param companyId the company ID
	* @param expired the expired
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_E(long companyId, boolean expired)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_E(companyId, expired);
	}

	/**
	* Returns the number of box tokens where companyId = &#63; and expired = &#63;.
	*
	* @param companyId the company ID
	* @param expired the expired
	* @return the number of matching box tokens
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_E(long companyId, boolean expired)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_E(companyId, expired);
	}

	/**
	* Returns all the box tokens where repositoryId = &#63; and expired = &#63;.
	*
	* @param repositoryId the repository ID
	* @param expired the expired
	* @return the matching box tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> findByR_E(
		long repositoryId, boolean expired)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_E(repositoryId, expired);
	}

	/**
	* Returns a range of all the box tokens where repositoryId = &#63; and expired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.bvakili.portlet.integration.box.model.impl.BoxTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param expired the expired
	* @param start the lower bound of the range of box tokens
	* @param end the upper bound of the range of box tokens (not inclusive)
	* @return the range of matching box tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> findByR_E(
		long repositoryId, boolean expired, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_E(repositoryId, expired, start, end);
	}

	/**
	* Returns an ordered range of all the box tokens where repositoryId = &#63; and expired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.bvakili.portlet.integration.box.model.impl.BoxTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param expired the expired
	* @param start the lower bound of the range of box tokens
	* @param end the upper bound of the range of box tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching box tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> findByR_E(
		long repositoryId, boolean expired, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_E(repositoryId, expired, start, end,
			orderByComparator);
	}

	/**
	* Returns the first box token in the ordered set where repositoryId = &#63; and expired = &#63;.
	*
	* @param repositoryId the repository ID
	* @param expired the expired
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching box token
	* @throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException if a matching box token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken findByR_E_First(
		long repositoryId, boolean expired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_E_First(repositoryId, expired, orderByComparator);
	}

	/**
	* Returns the first box token in the ordered set where repositoryId = &#63; and expired = &#63;.
	*
	* @param repositoryId the repository ID
	* @param expired the expired
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching box token, or <code>null</code> if a matching box token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken fetchByR_E_First(
		long repositoryId, boolean expired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_E_First(repositoryId, expired, orderByComparator);
	}

	/**
	* Returns the last box token in the ordered set where repositoryId = &#63; and expired = &#63;.
	*
	* @param repositoryId the repository ID
	* @param expired the expired
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching box token
	* @throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException if a matching box token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken findByR_E_Last(
		long repositoryId, boolean expired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_E_Last(repositoryId, expired, orderByComparator);
	}

	/**
	* Returns the last box token in the ordered set where repositoryId = &#63; and expired = &#63;.
	*
	* @param repositoryId the repository ID
	* @param expired the expired
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching box token, or <code>null</code> if a matching box token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken fetchByR_E_Last(
		long repositoryId, boolean expired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_E_Last(repositoryId, expired, orderByComparator);
	}

	/**
	* Returns the box tokens before and after the current box token in the ordered set where repositoryId = &#63; and expired = &#63;.
	*
	* @param boxTokenId the primary key of the current box token
	* @param repositoryId the repository ID
	* @param expired the expired
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next box token
	* @throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException if a box token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken[] findByR_E_PrevAndNext(
		long boxTokenId, long repositoryId, boolean expired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_E_PrevAndNext(boxTokenId, repositoryId, expired,
			orderByComparator);
	}

	/**
	* Removes all the box tokens where repositoryId = &#63; and expired = &#63; from the database.
	*
	* @param repositoryId the repository ID
	* @param expired the expired
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_E(long repositoryId, boolean expired)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_E(repositoryId, expired);
	}

	/**
	* Returns the number of box tokens where repositoryId = &#63; and expired = &#63;.
	*
	* @param repositoryId the repository ID
	* @param expired the expired
	* @return the number of matching box tokens
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_E(long repositoryId, boolean expired)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_E(repositoryId, expired);
	}

	/**
	* Caches the box token in the entity cache if it is enabled.
	*
	* @param boxToken the box token
	*/
	public static void cacheResult(
		com.bvakili.portlet.integration.box.model.BoxToken boxToken) {
		getPersistence().cacheResult(boxToken);
	}

	/**
	* Caches the box tokens in the entity cache if it is enabled.
	*
	* @param boxTokens the box tokens
	*/
	public static void cacheResult(
		java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> boxTokens) {
		getPersistence().cacheResult(boxTokens);
	}

	/**
	* Creates a new box token with the primary key. Does not add the box token to the database.
	*
	* @param boxTokenId the primary key for the new box token
	* @return the new box token
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken create(
		long boxTokenId) {
		return getPersistence().create(boxTokenId);
	}

	/**
	* Removes the box token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param boxTokenId the primary key of the box token
	* @return the box token that was removed
	* @throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException if a box token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken remove(
		long boxTokenId)
		throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(boxTokenId);
	}

	public static com.bvakili.portlet.integration.box.model.BoxToken updateImpl(
		com.bvakili.portlet.integration.box.model.BoxToken boxToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(boxToken);
	}

	/**
	* Returns the box token with the primary key or throws a {@link com.bvakili.portlet.integration.box.NoSuchBoxTokenException} if it could not be found.
	*
	* @param boxTokenId the primary key of the box token
	* @return the box token
	* @throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException if a box token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken findByPrimaryKey(
		long boxTokenId)
		throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(boxTokenId);
	}

	/**
	* Returns the box token with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param boxTokenId the primary key of the box token
	* @return the box token, or <code>null</code> if a box token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.bvakili.portlet.integration.box.model.BoxToken fetchByPrimaryKey(
		long boxTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(boxTokenId);
	}

	/**
	* Returns all the box tokens.
	*
	* @return the box tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the box tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.bvakili.portlet.integration.box.model.impl.BoxTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of box tokens
	* @param end the upper bound of the range of box tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of box tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.bvakili.portlet.integration.box.model.BoxToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the box tokens from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of box tokens.
	*
	* @return the number of box tokens
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static BoxTokenPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (BoxTokenPersistence)PortletBeanLocatorUtil.locate(com.bvakili.portlet.integration.box.service.ClpSerializer.getServletContextName(),
					BoxTokenPersistence.class.getName());

			ReferenceRegistry.registerReference(BoxTokenUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(BoxTokenPersistence persistence) {
	}

	private static BoxTokenPersistence _persistence;
}