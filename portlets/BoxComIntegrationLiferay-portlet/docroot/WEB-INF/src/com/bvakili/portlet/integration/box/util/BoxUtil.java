package com.bvakili.portlet.integration.box.util;

import com.box.boxjavalibv2.BoxClient;
import com.box.boxjavalibv2.dao.BoxItem;
import com.box.boxjavalibv2.dao.BoxOAuthToken;
import com.box.boxjavalibv2.dao.BoxTypedObject;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.requests.requestobjects.BoxOAuthRequestObject;
import com.box.boxjavalibv2.resourcemanagers.BoxOAuthManager;
import com.box.restclientv2.exceptions.BoxRestException;
import com.bvakili.portlet.integration.box.NoActiveTokensFoundException;

import java.util.List;

import static com.bvakili.portlet.integration.box.util.Constants.KEY;
import static com.bvakili.portlet.integration.box.util.Constants.SECRET;

public class BoxUtil {

	public static com.box.boxjavalibv2.dao.BoxFolder findParentFolder(com.box.boxjavalibv2.dao.BoxFile boxFile) {
		return findParentFolder(boxFile.getPathCollection());
	}

	public static com.box.boxjavalibv2.dao.BoxFolder findParentFolder(com.box.boxjavalibv2.dao.BoxFolder boxFolder) {
		return findParentFolder(boxFolder.getPathCollection());
	}

	public static BoxClient getAuthenticatedClient(String code, String callbackURL) throws AuthFatalFailureException, BoxRestException, BoxServerException {
		BoxClient client = new BoxClient(KEY, SECRET);
		BoxOAuthRequestObject obj = BoxOAuthRequestObject.createOAuthRequestObject(code, KEY, SECRET, callbackURL);
		BoxOAuthToken bt = client.getOAuthManager().createOAuth(obj);
		client.authenticate(bt);
		return client;
	}

	public static com.box.boxjavalibv2.dao.BoxFolder getFolderById(BoxClient client, int folderId) throws AuthFatalFailureException, BoxRestException, BoxServerException {
		com.box.boxjavalibv2.dao.BoxFolder folder = client.getFoldersManager().getFolder(""+folderId, null);
		return folder;
	}

	public static BoxClient refreshToken(String refreshToken, String callbackURL) throws AuthFatalFailureException, BoxRestException, BoxServerException, NoActiveTokensFoundException {
		BoxClient client = new BoxClient(KEY, SECRET);
		BoxOAuthRequestObject obj = BoxOAuthRequestObject.refreshOAuthRequestObject(refreshToken, KEY, SECRET);
		BoxOAuthManager manager = client.getOAuthManager();
		try {
			BoxOAuthToken bt = manager.createOAuth(obj);
			client.authenticate(bt);
		} catch (NullPointerException e) {
			// the response from server was null; refresh token is bad
			throw new NoActiveTokensFoundException();
		}
		return client;
	}

	private static com.box.boxjavalibv2.dao.BoxFolder findParentFolder(com.box.boxjavalibv2.dao.BoxCollection pathCollection) {
		List<BoxTypedObject> entries = pathCollection.getEntries();
		BoxTypedObject folderWithHighestIndex = null;
		int highestIndex = Integer.MIN_VALUE;

		for (BoxTypedObject boxTypedObject : entries) {
			if (boxTypedObject instanceof BoxItem) {
				BoxItem boxItem = (BoxItem)boxTypedObject;
				int seq = Integer.parseInt(boxItem.getSequenceId());

				if (seq > highestIndex) {
					highestIndex = seq;
					folderWithHighestIndex = boxItem;
				}
			}
		}

		com.box.boxjavalibv2.dao.BoxFolder targetFolder = null;

		if (folderWithHighestIndex instanceof com.box.boxjavalibv2.dao.BoxFolder) {
			targetFolder = (com.box.boxjavalibv2.dao.BoxFolder)folderWithHighestIndex;
		}

		return targetFolder;
	}

}