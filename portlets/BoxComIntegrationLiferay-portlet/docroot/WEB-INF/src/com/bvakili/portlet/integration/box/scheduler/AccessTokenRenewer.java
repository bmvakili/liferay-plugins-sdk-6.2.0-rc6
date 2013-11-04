package com.bvakili.portlet.integration.box.scheduler;

import com.box.boxjavalibv2.BoxClient;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.restclientv2.exceptions.BoxRestException;

import com.bvakili.portlet.integration.box.NotAuthenticatedToBoxException;
import com.bvakili.portlet.integration.box.model.BoxToken;
import com.bvakili.portlet.integration.box.service.BoxTokenLocalServiceUtil;
import com.bvakili.portlet.integration.box.util.BoxUtil;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

import java.util.List;
public class AccessTokenRenewer implements MessageListener {

	@Override
	public void receive(Message message) throws MessageListenerException {

		doJob(message);
	}

	private void doJob(Message message) {
		List<BoxToken> tokens = BoxTokenLocalServiceUtil.getActiveTokens();

		if (tokens == null) {
			return;
		}

		for (BoxToken token : tokens) {
			long curTime = System.currentTimeMillis();
			String refreshToken = token.getRefreshToken();
			String callbackURL = token.getCallbackURL();

			// If refresh token's expired, then skip

			if (curTime > token.getRefreshTokenExpiration()) {
				continue;
			}

			// this should never happen, but just in case

			if (token.getExpired()) {
				continue;
			}

			// this should never happen, but just in case

			if (refreshToken == null || callbackURL == null) {
				continue;
			}

			try {
				BoxClient client = BoxUtil.getAuthenticatedClient(refreshToken, callbackURL);

				if (!client.isAuthenticated()) {
					throw new NotAuthenticatedToBoxException();
				}
			} catch (BoxRestException e) {
				e.printStackTrace();
			} catch (BoxServerException e) {
				e.printStackTrace();
			} catch (AuthFatalFailureException e) {
				e.printStackTrace();
			} catch (NotAuthenticatedToBoxException e) {
				e.printStackTrace();
			}
		}
	}

}