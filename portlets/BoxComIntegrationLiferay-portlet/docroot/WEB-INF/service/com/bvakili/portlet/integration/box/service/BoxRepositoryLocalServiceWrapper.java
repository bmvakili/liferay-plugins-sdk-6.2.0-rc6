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
 * Provides a wrapper for {@link BoxRepositoryLocalService}.
 *
 * @author Bijan Vakili
 * @see BoxRepositoryLocalService
 * @generated
 */
public class BoxRepositoryLocalServiceWrapper
	implements BoxRepositoryLocalService,
		ServiceWrapper<BoxRepositoryLocalService> {
	public BoxRepositoryLocalServiceWrapper(
		BoxRepositoryLocalService boxRepositoryLocalService) {
		_boxRepositoryLocalService = boxRepositoryLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _boxRepositoryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_boxRepositoryLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _boxRepositoryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry toFileEntry(
		long repositoryId, java.lang.Object object)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _boxRepositoryLocalService.toFileEntry(repositoryId, object);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.FileVersion toFileVersion(
		long repositoryId, java.lang.Object object)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _boxRepositoryLocalService.toFileVersion(repositoryId, object);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder toFolder(
		long repositoryId, java.lang.Object object)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _boxRepositoryLocalService.toFolder(repositoryId, object);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public BoxRepositoryLocalService getWrappedBoxRepositoryLocalService() {
		return _boxRepositoryLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedBoxRepositoryLocalService(
		BoxRepositoryLocalService boxRepositoryLocalService) {
		_boxRepositoryLocalService = boxRepositoryLocalService;
	}

	@Override
	public BoxRepositoryLocalService getWrappedService() {
		return _boxRepositoryLocalService;
	}

	@Override
	public void setWrappedService(
		BoxRepositoryLocalService boxRepositoryLocalService) {
		_boxRepositoryLocalService = boxRepositoryLocalService;
	}

	private BoxRepositoryLocalService _boxRepositoryLocalService;
}