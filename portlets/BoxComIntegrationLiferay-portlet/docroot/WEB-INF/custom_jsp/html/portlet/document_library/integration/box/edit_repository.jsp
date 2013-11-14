<%--
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
--%>

<%@ include file="/html/portlet/document_library/integration/box/init.jsp" %>


<script type="text/javascript">

var boxPortletCallbackURL;
var boxReferenceCode;
var boxComCallbackURL;

var boxKey;
var boxAuthorizationURL;
var boxComPortlet;

function boxComPolling() {

	//Liferay.Portlet.close("#p_p_id_boxComIntegration_WAR_BoxComIntegrationLiferayportlet_", true);
}
function boxComHandleSuccess(instance, portletURL, event, id, obj) {
	var message = instance.get("responseData");
	AUI().one("#p_p_id_boxComIntegration_WAR_BoxComIntegrationLiferayportlet_").html(message);
	if (!message) {

	} else {
		message = message.split(",");
		if ("p" == message) {
			setTimeout(function() { boxComPoll(portletURL); }, 1500 );
		} else if (message.length > 1 && "o" == message[0]) {
			//AUI().one("#_20_fm button[type=submit]").set('disabled', 'false');
			var callbackURL = boxComCallbackURL;
			var code = message[1];
			//var codeFragment = AUI().Node.create('<input id="_20_code" name="_20_code" value="' + code + '" type="hidden">');
			//var callbackURLFragment = AUI().Node.create('<input id="_20_callbackURL" name="_20_callbackURL" value="' + callbackURL + '" type="hidden">');
			//AUI().one("body").insert(codeFragment, AUI().one("#_20_name"));
			//AUI().one("body").insert(callbackURLFragment, AUI().one("#_20_name"));
			//AUI().one("#_20_fm button[type=submit]").removeAttribute('disabled');
			//AUI().one("#_20_fm button[type=submit]").simulate("click");
			alert(callbackURL + " " + code);
		}
	}

}
function boxComPoll(portletURL) {
	AUI().io.request(
			portletURL.toString(),
			{
				on: {
					success: function(event, id, obj) {
						var instance = this;
						boxComHandleSuccess(instance, portletURL, event, id, obj);
					}
				}
			}
	);
}
function boxComStartPolling() {
	var portletURL = Liferay.PortletURL.createResourceURL();
	portletURL.setPortletId("boxComIntegration_WAR_BoxComIntegrationLiferayportlet");
	portletURL.setParameter('poll', boxReferenceCode);
	portletURL.setResourceId('poll');
	boxComPoll(portletURL);
	window.open(boxAuthorizationURL, "_blank");
}

function addHiddenPortlet() {
	var cur = AUI().one("#p_p_id_boxComIntegration_WAR_BoxComIntegrationLiferayportlet_");
	if (!cur) {
		var portletId = "boxComIntegration_WAR_BoxComIntegrationLiferayportlet";
		var plid = Liferay.ThemeDisplay.getPlid();
		var onComplete = function(portlet, portletId) {
			boxComPortlet = portlet;
			boxComStartPolling();
		}
		var options = {plid: plid, portletId : portletId, onComplete : onComplete };
		var portlet = Liferay.Portlet.add(options);
	} else {
		boxComStartPolling();
	}
}
function generateReference() {
	//generate a random trackig ID
	return (new Date().getTime()) +  "" + (Math.floor(Math.random()*100+1));
}
function boxComConfirmation() {
	// Disable the Save button until successful callback from Box.com
	//AUI().one("#_20_fm button[type=submit]").set('disabled', 'true');

	boxPortletCallbackURL = Liferay.PortletURL.createResourceURL();
	boxPortletCallbackURL.setPortletId("boxComIntegration_WAR_BoxComIntegrationLiferayportlet");
	boxReferenceCode = generateReference();
	boxPortletCallbackURL.setParameter('registered', boxReferenceCode);
	boxPortletCallbackURL.setResourceId('register');
	boxComCallbackURL = window.location.protocol + "//" + window.location.host + boxPortletCallbackURL.toString();

	boxKey = "ks4oieep7gte2li57pz4lqbbv6u7qxib";
	boxAuthorizationURL = "https://www.box.com/api/oauth2/authorize?response_type=code&client_id=" + boxKey + "&redirect_uri=" + encodeURIComponent(boxComCallbackURL);
	boxComPortlet = null;
	addHiddenPortlet();
}

if(window.onload) {
    var curronload = window.onload;
    var newonload = function() {
        curronload();
        boxComConfirmation();
    };
    window.onload = newonload;
} else {
    window.onload = boxComConfirmation;
}
</script>

${requestScope.repositoryId}