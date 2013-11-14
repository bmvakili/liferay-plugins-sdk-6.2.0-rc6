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

import com.bvakili.portlet.integration.box.service.BoxRepositoryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Bijan Vakili
 * @generated
 */
public class BoxRepositoryLocalServiceClpInvoker {
	public BoxRepositoryLocalServiceClpInvoker() {
		_methodName24 = "getBeanIdentifier";

		_methodParameterTypes24 = new String[] {  };

		_methodName25 = "setBeanIdentifier";

		_methodParameterTypes25 = new String[] { "java.lang.String" };

		_methodName28 = "toFileEntry";

		_methodParameterTypes28 = new String[] { "long", "java.lang.Object" };

		_methodName29 = "toFileVersion";

		_methodParameterTypes29 = new String[] { "long", "java.lang.Object" };

		_methodName30 = "toFolder";

		_methodParameterTypes30 = new String[] { "long", "java.lang.Object" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			return BoxRepositoryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			BoxRepositoryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return BoxRepositoryLocalServiceUtil.toFileEntry(((Long)arguments[0]).longValue(),
				(java.lang.Object)arguments[1]);
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return BoxRepositoryLocalServiceUtil.toFileVersion(((Long)arguments[0]).longValue(),
				(java.lang.Object)arguments[1]);
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return BoxRepositoryLocalServiceUtil.toFolder(((Long)arguments[0]).longValue(),
				(java.lang.Object)arguments[1]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName24;
	private String[] _methodParameterTypes24;
	private String _methodName25;
	private String[] _methodParameterTypes25;
	private String _methodName28;
	private String[] _methodParameterTypes28;
	private String _methodName29;
	private String[] _methodParameterTypes29;
	private String _methodName30;
	private String[] _methodParameterTypes30;
}