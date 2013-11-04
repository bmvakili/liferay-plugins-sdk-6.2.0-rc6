package com.bvakili.portlet.integration.box.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

public class Constants {

	protected static final String KEY = GetterUtil.getString(PortletProps.get("com.box.api.key"));

	protected static final String SECRET = GetterUtil.getString(PortletProps.get("com.box.api.secret"));

	public static final long REFRESH_TOKEN_EXPIRATION_DURATION_MILLISECONDS = 1209600000l; // 14 * 24 * 60 * 60 * 1000;
	public static final String BOX_ACCOUNT_ROOT_FOLDER_ID = "0";
}
