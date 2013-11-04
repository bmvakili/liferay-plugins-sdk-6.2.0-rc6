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

import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.dao.BoxFolder;

import com.bvakili.portlet.integration.box.repository.BoxRepository;
import com.bvakili.portlet.integration.box.repository.BoxRepositoryHandler;
import com.bvakili.portlet.integration.box.service.base.BoxRepositoryLocalServiceBaseImpl;

import com.liferay.portal.kernel.bean.ClassLoaderBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.repository.proxy.BaseRepositoryProxyBean;

/**
 * The implementation of the box repository local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.bvakili.portlet.integration.box.service.BoxRepositoryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Bijan Vakili
 * @see com.bvakili.portlet.integration.box.service.base.BoxRepositoryLocalServiceBaseImpl
 * @see com.bvakili.portlet.integration.box.service.BoxRepositoryLocalServiceUtil
 */
public class BoxRepositoryLocalServiceImpl
	extends BoxRepositoryLocalServiceBaseImpl {
	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.bvakili.portlet.integration.box.service.BoxRepositoryLocalServiceUtil} to access the box repository local service.
	 */

	@Override
	public FileEntry toFileEntry(long repositoryId, Object object)
		throws PortalException, SystemException {

		BoxRepository cmisRepository = getBoxRepository(repositoryId);

		BoxFile document = (BoxFile)object;

		return cmisRepository.toFileEntry(document);
	}

	@Override
	public FileVersion toFileVersion(long repositoryId, Object object)
		throws PortalException, SystemException {

		BoxRepository cmisRepository = getBoxRepository(repositoryId);

		BoxFile document = (BoxFile)object;

		return cmisRepository.toFileVersion(document);
	}

	@Override
	public Folder toFolder(long repositoryId, Object object)
		throws PortalException, SystemException {

		BoxRepository boxRepository = getBoxRepository(repositoryId);

		BoxFolder boxFolder = (BoxFolder)object;

		return boxRepository.toFolder(boxFolder);
	}

	protected BoxRepository getBoxRepository(long repositoryId)
		throws PortalException, SystemException {

		Repository repositoryImpl = repositoryLocalService.getRepositoryImpl(
			repositoryId);

		BoxRepositoryHandler repo = null;
		BoxRepository retVal = null;
//		CMISRepositoryHandler cmisRepositoryHandler = null;
//
//		if (repositoryImpl instanceof CMISRepositoryHandler) {
//			cmisRepositoryHandler = (CMISRepositoryHandler)repositoryImpl;
//		}
//		else if (repositoryImpl instanceof BaseRepositoryProxyBean) {
//			BaseRepositoryProxyBean baseRepositoryProxyBean =
//				(BaseRepositoryProxyBean)repositoryImpl;
//
//			ClassLoaderBeanHandler classLoaderBeanHandler =
//				(ClassLoaderBeanHandler)ProxyUtil.getInvocationHandler(
//					baseRepositoryProxyBean.getProxyBean());
//
//			Object bean = classLoaderBeanHandler.getBean();
//
//			if (bean instanceof CMISRepositoryHandler) {
//				cmisRepositoryHandler = (CMISRepositoryHandler)bean;
//			}
//		}
//
//		BoxRepository cmisRepository =
//			(BoxRepository)cmisRepositoryHandler.getCmisRepository();
//
//		return cmisRepository;

		if (repositoryImpl instanceof BoxRepository) {
			repo = (BoxRepositoryHandler)repositoryImpl;
		} else if (repositoryImpl instanceof BaseRepositoryProxyBean) {
			BaseRepositoryProxyBean baseRepositoryProxyBean =
				(BaseRepositoryProxyBean)repositoryImpl;

			ClassLoaderBeanHandler classLoaderBeanHandler =
				(ClassLoaderBeanHandler)ProxyUtil.getInvocationHandler(
					baseRepositoryProxyBean.getProxyBean());

			Object bean = classLoaderBeanHandler.getBean();

			if (bean instanceof BoxRepositoryHandler) {
				repo = (BoxRepositoryHandler)bean;
			}
		}

		retVal = (BoxRepository)repo.getBoxRepository();
		return retVal;
	}

}