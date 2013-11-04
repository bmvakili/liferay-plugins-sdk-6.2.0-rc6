package com.bvakili.portlet.integration.box.action;

import com.box.boxjavalibv2.BoxClient;
import com.box.boxjavalibv2.dao.BoxOAuthToken;

import com.bvakili.portlet.integration.box.service.BoxTokenLocalServiceUtil;
import com.bvakili.portlet.integration.box.util.BoxUtil;

import com.liferay.portal.NoSuchRepositoryException;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Repository;
import com.liferay.portal.service.RepositoryLocalServiceUtil;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
public class EditRepositoryAction extends BaseStrutsPortletAction {

	@Override
	public void processAction(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		originalStrutsPortletAction.processAction(
				originalStrutsPortletAction, portletConfig, actionRequest,
				actionResponse);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
			updateRepository(actionRequest);
		}
	}

	public String render(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse) throws Exception {

		return originalStrutsPortletAction.render(
				originalStrutsPortletAction, portletConfig, renderRequest, renderResponse);
	}

	@Override
	public void serveResource(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws Exception {
		originalStrutsPortletAction.serveResource(
				originalStrutsPortletAction, portletConfig, resourceRequest,
				resourceResponse);
	} 
	

	protected void updateRepository(ActionRequest actionRequest)
			throws Exception {

			ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
					com.liferay.portal.kernel.util.WebKeys.THEME_DISPLAY);

			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			long repositoryId = ParamUtil.getLong(actionRequest, "repositoryId");

			String className = ParamUtil.getString(actionRequest, "className");

			long classNameId = PortalUtil.getClassNameId(className);

			long folderId = ParamUtil.getLong(actionRequest, "folderId");
			String name = ParamUtil.getString(actionRequest, "name");
			String code = ParamUtil.getString(actionRequest, "code");
			String callbackURL = ParamUtil.getString(actionRequest, "callbackURL");
			String description = ParamUtil.getString(actionRequest, "description");

			if (repositoryId <= 0) {

				//Repository repo = RepositoryLocalServiceUtil.getRepository(themeDisplay.getScopeGroupId(), portletDisplay.getId());
				Repository repo = null;

				try {
					repo = RepositoryLocalServiceUtil.getRepository(themeDisplay.getScopeGroupId(), name, "20");
				} catch (Exception e) {
					try {
						repo = RepositoryLocalServiceUtil.getRepository(themeDisplay.getScopeGroupId(), name, "");
					} catch (Exception ex) {
						repo = RepositoryLocalServiceUtil.getRepository(themeDisplay.getScopeGroupId(), name, null);
					}
				}

				if (repo == null) {
					throw new NoSuchRepositoryException();
				}

				BoxClient client = BoxUtil.getAuthenticatedClient(code, callbackURL);
				BoxOAuthToken bToken = client.getAuthData();
				long companyId = themeDisplay.getCompanyId();
				String fullName = themeDisplay.getUser().getFullName();
				long userId = themeDisplay.getUserId();

				BoxTokenLocalServiceUtil.createNewToken(companyId, fullName, userId, callbackURL, repo, bToken);;
			}
	}

}