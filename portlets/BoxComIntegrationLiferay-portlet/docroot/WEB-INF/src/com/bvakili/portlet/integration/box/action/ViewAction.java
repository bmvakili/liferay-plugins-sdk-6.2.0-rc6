package com.bvakili.portlet.integration.box.action;

import com.box.boxjavalibv2.BoxClient;
import com.box.boxjavalibv2.dao.BoxOAuthToken;
import com.bvakili.portlet.integration.box.NoActiveTokensFoundException;
import com.bvakili.portlet.integration.box.service.BoxTokenLocalServiceUtil;
import com.bvakili.portlet.integration.box.util.BoxUtil;
import com.liferay.portal.NoSuchRepositoryException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Repository;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.RepositoryLocalServiceUtil;
import com.liferay.portal.service.persistence.RepositoryEntryUtil;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
public class ViewAction extends BaseStrutsPortletAction {

	@Override
	public void processAction(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		originalStrutsPortletAction.processAction(
				originalStrutsPortletAction, portletConfig, actionRequest,
				actionResponse);
	}

	public String render(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse) throws Exception {

		String retVal = "";
		try {
			String origForward =  originalStrutsPortletAction.render(
					originalStrutsPortletAction, portletConfig, renderRequest, renderResponse);
			retVal = origForward;
			checkRepository(renderRequest);

		} catch(Exception e) {
			if (e instanceof NoActiveTokensFoundException) {
				long repositoryId = ParamUtil.getLong(renderRequest, "repositoryId");
				renderRequest.setAttribute("repositoryId", repositoryId);
				retVal = "/portlet/document_library/integration/box/edit_repository.jsp";
			} else {
				throw e;
			}
		}
	

		return retVal;
	}

	private void checkRepository(RenderRequest renderRequest) throws NoActiveTokensFoundException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
				com.liferay.portal.kernel.util.WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		long repositoryId = ParamUtil.getLong(renderRequest, "repositoryId");

		String className = ParamUtil.getString(renderRequest, "className");

		long classNameId = PortalUtil.getClassNameId(className);

		long folderId = ParamUtil.getLong(renderRequest, "folderId");
		String name = ParamUtil.getString(renderRequest, "name");
		String code = ParamUtil.getString(renderRequest, "code");
		String callbackURL = ParamUtil.getString(renderRequest, "callbackURL");
		String description = ParamUtil.getString(renderRequest, "description");

		if (repositoryId >= 0) {
			Repository repo = null;
			try {
				repo = RepositoryLocalServiceUtil.getRepository(repositoryId);
				long dlFolderId = repo.getDlFolderId();
				DLFolder folder = DLFolderLocalServiceUtil.getFolder(dlFolderId);
				if (folder == null) {
					throw new NullPointerException("No such repository folder exists: repositoryId=" + repositoryId + " dlFolderId=" + dlFolderId);
				}
			} catch (Exception e) {
				throw new NoActiveTokensFoundException();
			}
		}

	}

	@Override
	public void serveResource(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws Exception {
		originalStrutsPortletAction.serveResource(
				originalStrutsPortletAction, portletConfig, resourceRequest,
				resourceResponse);
	} 
}