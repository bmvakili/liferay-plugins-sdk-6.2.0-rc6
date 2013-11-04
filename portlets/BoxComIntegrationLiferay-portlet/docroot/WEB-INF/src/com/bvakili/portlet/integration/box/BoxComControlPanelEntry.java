package com.bvakili.portlet.integration.box;

import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.BaseControlPanelEntry;

/**
 * Control panel entry class BoxComControlPanelEntry
 */

public class BoxComControlPanelEntry extends BaseControlPanelEntry {

	@Override
	public boolean isVisible(PermissionChecker permissionChecker, Portlet portlet)
			throws Exception {
		return false;
	}

}