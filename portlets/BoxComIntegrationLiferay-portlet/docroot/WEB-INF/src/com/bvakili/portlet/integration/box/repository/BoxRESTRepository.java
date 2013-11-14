package com.bvakili.portlet.integration.box.repository;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
public class BoxRESTRepository extends BoxRepositoryHandler {

	@Override
	public String[] getSupportedConfigurations() {

		return _SUPPORTED_CONFIGURATION;
	}

	@Override
	public String[][] getSupportedParameters() {

		// TODO Auto-generated method stub

		return _SUPPORTED_PARAMETERS;
	}

	@Override
	public void initRepository() throws PortalException, SystemException {
		BoxRepository baseRepository = new BoxRepository(this);
		baseRepository.setAssetEntryLocalService(assetEntryLocalService);
		baseRepository.setCompanyId(this.getCompanyId());
		baseRepository.setCompanyLocalService(companyLocalService);
		baseRepository.setCounterLocalService(counterLocalService);
		baseRepository.setDLAppHelperLocalService(dlAppHelperLocalService);
		baseRepository.setGroupId(this.getGroupId());
		baseRepository.setRepositoryId(this.getRepositoryId());
		baseRepository.setTypeSettingsProperties(
			this.getTypeSettingsProperties());
		baseRepository.setUserLocalService(userLocalService);
	}

	private static final String _CONFIGURATION_BOX = "BOXCOM";

	private static final String _REPOSITORY_ID = "REPOSITORY_ID";

	private static final String[] _SUPPORTED_CONFIGURATION = {
		_CONFIGURATION_BOX
	};

	private static final String[][] _SUPPORTED_PARAMETERS = {
		{_REPOSITORY_ID}
	};

}