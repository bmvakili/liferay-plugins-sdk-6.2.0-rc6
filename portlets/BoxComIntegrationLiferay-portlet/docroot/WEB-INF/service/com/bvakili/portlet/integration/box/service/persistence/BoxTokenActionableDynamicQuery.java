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
import com.bvakili.portlet.integration.box.service.BoxTokenLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Bijan Vakili
 * @generated
 */
public abstract class BoxTokenActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public BoxTokenActionableDynamicQuery() throws SystemException {
		setBaseLocalService(BoxTokenLocalServiceUtil.getService());
		setClass(BoxToken.class);

		setClassLoader(com.bvakili.portlet.integration.box.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("boxTokenId");
	}
}