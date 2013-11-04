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

package com.bvakili.portlet.integration.box.service.base;

import com.bvakili.portlet.integration.box.service.BoxRepositoryLocalService;
import com.bvakili.portlet.integration.box.service.persistence.BoxTokenPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.persistence.RepositoryPersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the box repository local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.bvakili.portlet.integration.box.service.impl.BoxRepositoryLocalServiceImpl}.
 * </p>
 *
 * @author Bijan Vakili
 * @see com.bvakili.portlet.integration.box.service.impl.BoxRepositoryLocalServiceImpl
 * @see com.bvakili.portlet.integration.box.service.BoxRepositoryLocalServiceUtil
 * @generated
 */
public abstract class BoxRepositoryLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements BoxRepositoryLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.bvakili.portlet.integration.box.service.BoxRepositoryLocalServiceUtil} to access the box repository local service.
	 */

	/**
	 * Returns the box repository local service.
	 *
	 * @return the box repository local service
	 */
	public com.bvakili.portlet.integration.box.service.BoxRepositoryLocalService getBoxRepositoryLocalService() {
		return boxRepositoryLocalService;
	}

	/**
	 * Sets the box repository local service.
	 *
	 * @param boxRepositoryLocalService the box repository local service
	 */
	public void setBoxRepositoryLocalService(
		com.bvakili.portlet.integration.box.service.BoxRepositoryLocalService boxRepositoryLocalService) {
		this.boxRepositoryLocalService = boxRepositoryLocalService;
	}

	/**
	 * Returns the box token local service.
	 *
	 * @return the box token local service
	 */
	public com.bvakili.portlet.integration.box.service.BoxTokenLocalService getBoxTokenLocalService() {
		return boxTokenLocalService;
	}

	/**
	 * Sets the box token local service.
	 *
	 * @param boxTokenLocalService the box token local service
	 */
	public void setBoxTokenLocalService(
		com.bvakili.portlet.integration.box.service.BoxTokenLocalService boxTokenLocalService) {
		this.boxTokenLocalService = boxTokenLocalService;
	}

	/**
	 * Returns the box token persistence.
	 *
	 * @return the box token persistence
	 */
	public BoxTokenPersistence getBoxTokenPersistence() {
		return boxTokenPersistence;
	}

	/**
	 * Sets the box token persistence.
	 *
	 * @param boxTokenPersistence the box token persistence
	 */
	public void setBoxTokenPersistence(BoxTokenPersistence boxTokenPersistence) {
		this.boxTokenPersistence = boxTokenPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the repository local service.
	 *
	 * @return the repository local service
	 */
	public com.liferay.portal.service.RepositoryLocalService getRepositoryLocalService() {
		return repositoryLocalService;
	}

	/**
	 * Sets the repository local service.
	 *
	 * @param repositoryLocalService the repository local service
	 */
	public void setRepositoryLocalService(
		com.liferay.portal.service.RepositoryLocalService repositoryLocalService) {
		this.repositoryLocalService = repositoryLocalService;
	}

	/**
	 * Returns the repository remote service.
	 *
	 * @return the repository remote service
	 */
	public com.liferay.portal.service.RepositoryService getRepositoryService() {
		return repositoryService;
	}

	/**
	 * Sets the repository remote service.
	 *
	 * @param repositoryService the repository remote service
	 */
	public void setRepositoryService(
		com.liferay.portal.service.RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	/**
	 * Returns the repository persistence.
	 *
	 * @return the repository persistence
	 */
	public RepositoryPersistence getRepositoryPersistence() {
		return repositoryPersistence;
	}

	/**
	 * Sets the repository persistence.
	 *
	 * @param repositoryPersistence the repository persistence
	 */
	public void setRepositoryPersistence(
		RepositoryPersistence repositoryPersistence) {
		this.repositoryPersistence = repositoryPersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();
	}

	public void destroy() {
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = InfrastructureUtil.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.bvakili.portlet.integration.box.service.BoxRepositoryLocalService.class)
	protected com.bvakili.portlet.integration.box.service.BoxRepositoryLocalService boxRepositoryLocalService;
	@BeanReference(type = com.bvakili.portlet.integration.box.service.BoxTokenLocalService.class)
	protected com.bvakili.portlet.integration.box.service.BoxTokenLocalService boxTokenLocalService;
	@BeanReference(type = BoxTokenPersistence.class)
	protected BoxTokenPersistence boxTokenPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.RepositoryLocalService.class)
	protected com.liferay.portal.service.RepositoryLocalService repositoryLocalService;
	@BeanReference(type = com.liferay.portal.service.RepositoryService.class)
	protected com.liferay.portal.service.RepositoryService repositoryService;
	@BeanReference(type = RepositoryPersistence.class)
	protected RepositoryPersistence repositoryPersistence;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private BoxRepositoryLocalServiceClpInvoker _clpInvoker = new BoxRepositoryLocalServiceClpInvoker();
}