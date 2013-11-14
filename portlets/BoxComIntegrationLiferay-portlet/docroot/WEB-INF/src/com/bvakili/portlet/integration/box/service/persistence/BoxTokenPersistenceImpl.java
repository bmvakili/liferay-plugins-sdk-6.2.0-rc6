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

import com.bvakili.portlet.integration.box.NoSuchBoxTokenException;
import com.bvakili.portlet.integration.box.model.BoxToken;
import com.bvakili.portlet.integration.box.model.impl.BoxTokenImpl;
import com.bvakili.portlet.integration.box.model.impl.BoxTokenModelImpl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the box token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Bijan Vakili
 * @see BoxTokenPersistence
 * @see BoxTokenUtil
 * @generated
 */
public class BoxTokenPersistenceImpl extends BasePersistenceImpl<BoxToken>
	implements BoxTokenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BoxTokenUtil} to access the box token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BoxTokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
			BoxTokenModelImpl.FINDER_CACHE_ENABLED, BoxTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
			BoxTokenModelImpl.FINDER_CACHE_ENABLED, BoxTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
			BoxTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_E = new FinderPath(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
			BoxTokenModelImpl.FINDER_CACHE_ENABLED, BoxTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_E",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_E = new FinderPath(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
			BoxTokenModelImpl.FINDER_CACHE_ENABLED, BoxTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_E",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			BoxTokenModelImpl.COMPANYID_COLUMN_BITMASK |
			BoxTokenModelImpl.EXPIRED_COLUMN_BITMASK |
			BoxTokenModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_E = new FinderPath(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
			BoxTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_E",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the box tokens where companyId = &#63; and expired = &#63;.
	 *
	 * @param companyId the company ID
	 * @param expired the expired
	 * @return the matching box tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BoxToken> findByC_E(long companyId, boolean expired)
		throws SystemException {
		return findByC_E(companyId, expired, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<BoxToken> findByC_E(long companyId, boolean expired, int start,
		int end) throws SystemException {
		return findByC_E(companyId, expired, start, end, null);
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
	@Override
	public List<BoxToken> findByC_E(long companyId, boolean expired, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_E;
			finderArgs = new Object[] { companyId, expired };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_E;
			finderArgs = new Object[] {
					companyId, expired,
					
					start, end, orderByComparator
				};
		}

		List<BoxToken> list = (List<BoxToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BoxToken boxToken : list) {
				if ((companyId != boxToken.getCompanyId()) ||
						(expired != boxToken.getExpired())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_BOXTOKEN_WHERE);

			query.append(_FINDER_COLUMN_C_E_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_E_EXPIRED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BoxTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(expired);

				if (!pagination) {
					list = (List<BoxToken>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BoxToken>(list);
				}
				else {
					list = (List<BoxToken>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public BoxToken findByC_E_First(long companyId, boolean expired,
		OrderByComparator orderByComparator)
		throws NoSuchBoxTokenException, SystemException {
		BoxToken boxToken = fetchByC_E_First(companyId, expired,
				orderByComparator);

		if (boxToken != null) {
			return boxToken;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", expired=");
		msg.append(expired);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBoxTokenException(msg.toString());
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
	@Override
	public BoxToken fetchByC_E_First(long companyId, boolean expired,
		OrderByComparator orderByComparator) throws SystemException {
		List<BoxToken> list = findByC_E(companyId, expired, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public BoxToken findByC_E_Last(long companyId, boolean expired,
		OrderByComparator orderByComparator)
		throws NoSuchBoxTokenException, SystemException {
		BoxToken boxToken = fetchByC_E_Last(companyId, expired,
				orderByComparator);

		if (boxToken != null) {
			return boxToken;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", expired=");
		msg.append(expired);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBoxTokenException(msg.toString());
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
	@Override
	public BoxToken fetchByC_E_Last(long companyId, boolean expired,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_E(companyId, expired);

		if (count == 0) {
			return null;
		}

		List<BoxToken> list = findByC_E(companyId, expired, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public BoxToken[] findByC_E_PrevAndNext(long boxTokenId, long companyId,
		boolean expired, OrderByComparator orderByComparator)
		throws NoSuchBoxTokenException, SystemException {
		BoxToken boxToken = findByPrimaryKey(boxTokenId);

		Session session = null;

		try {
			session = openSession();

			BoxToken[] array = new BoxTokenImpl[3];

			array[0] = getByC_E_PrevAndNext(session, boxToken, companyId,
					expired, orderByComparator, true);

			array[1] = boxToken;

			array[2] = getByC_E_PrevAndNext(session, boxToken, companyId,
					expired, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BoxToken getByC_E_PrevAndNext(Session session, BoxToken boxToken,
		long companyId, boolean expired, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BOXTOKEN_WHERE);

		query.append(_FINDER_COLUMN_C_E_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_E_EXPIRED_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(BoxTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(expired);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(boxToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BoxToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the box tokens where companyId = &#63; and expired = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param expired the expired
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_E(long companyId, boolean expired)
		throws SystemException {
		for (BoxToken boxToken : findByC_E(companyId, expired,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(boxToken);
		}
	}

	/**
	 * Returns the number of box tokens where companyId = &#63; and expired = &#63;.
	 *
	 * @param companyId the company ID
	 * @param expired the expired
	 * @return the number of matching box tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_E(long companyId, boolean expired)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_E;

		Object[] finderArgs = new Object[] { companyId, expired };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BOXTOKEN_WHERE);

			query.append(_FINDER_COLUMN_C_E_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_E_EXPIRED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(expired);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_E_COMPANYID_2 = "boxToken.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_E_EXPIRED_2 = "boxToken.expired = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_E = new FinderPath(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
			BoxTokenModelImpl.FINDER_CACHE_ENABLED, BoxTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_E",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_E = new FinderPath(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
			BoxTokenModelImpl.FINDER_CACHE_ENABLED, BoxTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_E",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			BoxTokenModelImpl.REPOSITORYID_COLUMN_BITMASK |
			BoxTokenModelImpl.EXPIRED_COLUMN_BITMASK |
			BoxTokenModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_E = new FinderPath(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
			BoxTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_E",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the box tokens where repositoryId = &#63; and expired = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param expired the expired
	 * @return the matching box tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BoxToken> findByR_E(long repositoryId, boolean expired)
		throws SystemException {
		return findByR_E(repositoryId, expired, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<BoxToken> findByR_E(long repositoryId, boolean expired,
		int start, int end) throws SystemException {
		return findByR_E(repositoryId, expired, start, end, null);
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
	@Override
	public List<BoxToken> findByR_E(long repositoryId, boolean expired,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_E;
			finderArgs = new Object[] { repositoryId, expired };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_E;
			finderArgs = new Object[] {
					repositoryId, expired,
					
					start, end, orderByComparator
				};
		}

		List<BoxToken> list = (List<BoxToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BoxToken boxToken : list) {
				if ((repositoryId != boxToken.getRepositoryId()) ||
						(expired != boxToken.getExpired())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_BOXTOKEN_WHERE);

			query.append(_FINDER_COLUMN_R_E_REPOSITORYID_2);

			query.append(_FINDER_COLUMN_R_E_EXPIRED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BoxTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(repositoryId);

				qPos.add(expired);

				if (!pagination) {
					list = (List<BoxToken>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BoxToken>(list);
				}
				else {
					list = (List<BoxToken>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public BoxToken findByR_E_First(long repositoryId, boolean expired,
		OrderByComparator orderByComparator)
		throws NoSuchBoxTokenException, SystemException {
		BoxToken boxToken = fetchByR_E_First(repositoryId, expired,
				orderByComparator);

		if (boxToken != null) {
			return boxToken;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("repositoryId=");
		msg.append(repositoryId);

		msg.append(", expired=");
		msg.append(expired);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBoxTokenException(msg.toString());
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
	@Override
	public BoxToken fetchByR_E_First(long repositoryId, boolean expired,
		OrderByComparator orderByComparator) throws SystemException {
		List<BoxToken> list = findByR_E(repositoryId, expired, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public BoxToken findByR_E_Last(long repositoryId, boolean expired,
		OrderByComparator orderByComparator)
		throws NoSuchBoxTokenException, SystemException {
		BoxToken boxToken = fetchByR_E_Last(repositoryId, expired,
				orderByComparator);

		if (boxToken != null) {
			return boxToken;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("repositoryId=");
		msg.append(repositoryId);

		msg.append(", expired=");
		msg.append(expired);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBoxTokenException(msg.toString());
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
	@Override
	public BoxToken fetchByR_E_Last(long repositoryId, boolean expired,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByR_E(repositoryId, expired);

		if (count == 0) {
			return null;
		}

		List<BoxToken> list = findByR_E(repositoryId, expired, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public BoxToken[] findByR_E_PrevAndNext(long boxTokenId, long repositoryId,
		boolean expired, OrderByComparator orderByComparator)
		throws NoSuchBoxTokenException, SystemException {
		BoxToken boxToken = findByPrimaryKey(boxTokenId);

		Session session = null;

		try {
			session = openSession();

			BoxToken[] array = new BoxTokenImpl[3];

			array[0] = getByR_E_PrevAndNext(session, boxToken, repositoryId,
					expired, orderByComparator, true);

			array[1] = boxToken;

			array[2] = getByR_E_PrevAndNext(session, boxToken, repositoryId,
					expired, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BoxToken getByR_E_PrevAndNext(Session session, BoxToken boxToken,
		long repositoryId, boolean expired,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BOXTOKEN_WHERE);

		query.append(_FINDER_COLUMN_R_E_REPOSITORYID_2);

		query.append(_FINDER_COLUMN_R_E_EXPIRED_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(BoxTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(repositoryId);

		qPos.add(expired);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(boxToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BoxToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the box tokens where repositoryId = &#63; and expired = &#63; from the database.
	 *
	 * @param repositoryId the repository ID
	 * @param expired the expired
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByR_E(long repositoryId, boolean expired)
		throws SystemException {
		for (BoxToken boxToken : findByR_E(repositoryId, expired,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(boxToken);
		}
	}

	/**
	 * Returns the number of box tokens where repositoryId = &#63; and expired = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param expired the expired
	 * @return the number of matching box tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByR_E(long repositoryId, boolean expired)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_E;

		Object[] finderArgs = new Object[] { repositoryId, expired };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BOXTOKEN_WHERE);

			query.append(_FINDER_COLUMN_R_E_REPOSITORYID_2);

			query.append(_FINDER_COLUMN_R_E_EXPIRED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(repositoryId);

				qPos.add(expired);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_R_E_REPOSITORYID_2 = "boxToken.repositoryId = ? AND ";
	private static final String _FINDER_COLUMN_R_E_EXPIRED_2 = "boxToken.expired = ?";

	public BoxTokenPersistenceImpl() {
		setModelClass(BoxToken.class);
	}

	/**
	 * Caches the box token in the entity cache if it is enabled.
	 *
	 * @param boxToken the box token
	 */
	@Override
	public void cacheResult(BoxToken boxToken) {
		EntityCacheUtil.putResult(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
			BoxTokenImpl.class, boxToken.getPrimaryKey(), boxToken);

		boxToken.resetOriginalValues();
	}

	/**
	 * Caches the box tokens in the entity cache if it is enabled.
	 *
	 * @param boxTokens the box tokens
	 */
	@Override
	public void cacheResult(List<BoxToken> boxTokens) {
		for (BoxToken boxToken : boxTokens) {
			if (EntityCacheUtil.getResult(
						BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
						BoxTokenImpl.class, boxToken.getPrimaryKey()) == null) {
				cacheResult(boxToken);
			}
			else {
				boxToken.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all box tokens.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(BoxTokenImpl.class.getName());
		}

		EntityCacheUtil.clearCache(BoxTokenImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the box token.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BoxToken boxToken) {
		EntityCacheUtil.removeResult(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
			BoxTokenImpl.class, boxToken.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<BoxToken> boxTokens) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BoxToken boxToken : boxTokens) {
			EntityCacheUtil.removeResult(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
				BoxTokenImpl.class, boxToken.getPrimaryKey());
		}
	}

	/**
	 * Creates a new box token with the primary key. Does not add the box token to the database.
	 *
	 * @param boxTokenId the primary key for the new box token
	 * @return the new box token
	 */
	@Override
	public BoxToken create(long boxTokenId) {
		BoxToken boxToken = new BoxTokenImpl();

		boxToken.setNew(true);
		boxToken.setPrimaryKey(boxTokenId);

		return boxToken;
	}

	/**
	 * Removes the box token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param boxTokenId the primary key of the box token
	 * @return the box token that was removed
	 * @throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException if a box token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BoxToken remove(long boxTokenId)
		throws NoSuchBoxTokenException, SystemException {
		return remove((Serializable)boxTokenId);
	}

	/**
	 * Removes the box token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the box token
	 * @return the box token that was removed
	 * @throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException if a box token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BoxToken remove(Serializable primaryKey)
		throws NoSuchBoxTokenException, SystemException {
		Session session = null;

		try {
			session = openSession();

			BoxToken boxToken = (BoxToken)session.get(BoxTokenImpl.class,
					primaryKey);

			if (boxToken == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBoxTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(boxToken);
		}
		catch (NoSuchBoxTokenException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected BoxToken removeImpl(BoxToken boxToken) throws SystemException {
		boxToken = toUnwrappedModel(boxToken);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(boxToken)) {
				boxToken = (BoxToken)session.get(BoxTokenImpl.class,
						boxToken.getPrimaryKeyObj());
			}

			if (boxToken != null) {
				session.delete(boxToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (boxToken != null) {
			clearCache(boxToken);
		}

		return boxToken;
	}

	@Override
	public BoxToken updateImpl(
		com.bvakili.portlet.integration.box.model.BoxToken boxToken)
		throws SystemException {
		boxToken = toUnwrappedModel(boxToken);

		boolean isNew = boxToken.isNew();

		BoxTokenModelImpl boxTokenModelImpl = (BoxTokenModelImpl)boxToken;

		Session session = null;

		try {
			session = openSession();

			if (boxToken.isNew()) {
				session.save(boxToken);

				boxToken.setNew(false);
			}
			else {
				session.merge(boxToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !BoxTokenModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((boxTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_E.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						boxTokenModelImpl.getOriginalCompanyId(),
						boxTokenModelImpl.getOriginalExpired()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_E, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_E,
					args);

				args = new Object[] {
						boxTokenModelImpl.getCompanyId(),
						boxTokenModelImpl.getExpired()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_E, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_E,
					args);
			}

			if ((boxTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_E.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						boxTokenModelImpl.getOriginalRepositoryId(),
						boxTokenModelImpl.getOriginalExpired()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_E, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_E,
					args);

				args = new Object[] {
						boxTokenModelImpl.getRepositoryId(),
						boxTokenModelImpl.getExpired()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_E, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_E,
					args);
			}
		}

		EntityCacheUtil.putResult(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
			BoxTokenImpl.class, boxToken.getPrimaryKey(), boxToken);

		return boxToken;
	}

	protected BoxToken toUnwrappedModel(BoxToken boxToken) {
		if (boxToken instanceof BoxTokenImpl) {
			return boxToken;
		}

		BoxTokenImpl boxTokenImpl = new BoxTokenImpl();

		boxTokenImpl.setNew(boxToken.isNew());
		boxTokenImpl.setPrimaryKey(boxToken.getPrimaryKey());

		boxTokenImpl.setBoxTokenId(boxToken.getBoxTokenId());
		boxTokenImpl.setCompanyId(boxToken.getCompanyId());
		boxTokenImpl.setUserId(boxToken.getUserId());
		boxTokenImpl.setUserName(boxToken.getUserName());
		boxTokenImpl.setCreateDate(boxToken.getCreateDate());
		boxTokenImpl.setModifiedDate(boxToken.getModifiedDate());
		boxTokenImpl.setAccessToken(boxToken.getAccessToken());
		boxTokenImpl.setRefreshToken(boxToken.getRefreshToken());
		boxTokenImpl.setAccessTokenExpiration(boxToken.getAccessTokenExpiration());
		boxTokenImpl.setRefreshTokenExpiration(boxToken.getRefreshTokenExpiration());
		boxTokenImpl.setCallbackURL(boxToken.getCallbackURL());
		boxTokenImpl.setExpired(boxToken.isExpired());
		boxTokenImpl.setRepositoryId(boxToken.getRepositoryId());

		return boxTokenImpl;
	}

	/**
	 * Returns the box token with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the box token
	 * @return the box token
	 * @throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException if a box token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BoxToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBoxTokenException, SystemException {
		BoxToken boxToken = fetchByPrimaryKey(primaryKey);

		if (boxToken == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBoxTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return boxToken;
	}

	/**
	 * Returns the box token with the primary key or throws a {@link com.bvakili.portlet.integration.box.NoSuchBoxTokenException} if it could not be found.
	 *
	 * @param boxTokenId the primary key of the box token
	 * @return the box token
	 * @throws com.bvakili.portlet.integration.box.NoSuchBoxTokenException if a box token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BoxToken findByPrimaryKey(long boxTokenId)
		throws NoSuchBoxTokenException, SystemException {
		return findByPrimaryKey((Serializable)boxTokenId);
	}

	/**
	 * Returns the box token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the box token
	 * @return the box token, or <code>null</code> if a box token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BoxToken fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		BoxToken boxToken = (BoxToken)EntityCacheUtil.getResult(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
				BoxTokenImpl.class, primaryKey);

		if (boxToken == _nullBoxToken) {
			return null;
		}

		if (boxToken == null) {
			Session session = null;

			try {
				session = openSession();

				boxToken = (BoxToken)session.get(BoxTokenImpl.class, primaryKey);

				if (boxToken != null) {
					cacheResult(boxToken);
				}
				else {
					EntityCacheUtil.putResult(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
						BoxTokenImpl.class, primaryKey, _nullBoxToken);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(BoxTokenModelImpl.ENTITY_CACHE_ENABLED,
					BoxTokenImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return boxToken;
	}

	/**
	 * Returns the box token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param boxTokenId the primary key of the box token
	 * @return the box token, or <code>null</code> if a box token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BoxToken fetchByPrimaryKey(long boxTokenId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)boxTokenId);
	}

	/**
	 * Returns all the box tokens.
	 *
	 * @return the box tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BoxToken> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<BoxToken> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
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
	@Override
	public List<BoxToken> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<BoxToken> list = (List<BoxToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_BOXTOKEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BOXTOKEN;

				if (pagination) {
					sql = sql.concat(BoxTokenModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BoxToken>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BoxToken>(list);
				}
				else {
					list = (List<BoxToken>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the box tokens from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (BoxToken boxToken : findAll()) {
			remove(boxToken);
		}
	}

	/**
	 * Returns the number of box tokens.
	 *
	 * @return the number of box tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BOXTOKEN);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the box token persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.bvakili.portlet.integration.box.model.BoxToken")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<BoxToken>> listenersList = new ArrayList<ModelListener<BoxToken>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<BoxToken>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(BoxTokenImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_BOXTOKEN = "SELECT boxToken FROM BoxToken boxToken";
	private static final String _SQL_SELECT_BOXTOKEN_WHERE = "SELECT boxToken FROM BoxToken boxToken WHERE ";
	private static final String _SQL_COUNT_BOXTOKEN = "SELECT COUNT(boxToken) FROM BoxToken boxToken";
	private static final String _SQL_COUNT_BOXTOKEN_WHERE = "SELECT COUNT(boxToken) FROM BoxToken boxToken WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "boxToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BoxToken exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BoxToken exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(BoxTokenPersistenceImpl.class);
	private static BoxToken _nullBoxToken = new BoxTokenImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<BoxToken> toCacheModel() {
				return _nullBoxTokenCacheModel;
			}
		};

	private static CacheModel<BoxToken> _nullBoxTokenCacheModel = new CacheModel<BoxToken>() {
			@Override
			public BoxToken toEntityModel() {
				return _nullBoxToken;
			}
		};
}