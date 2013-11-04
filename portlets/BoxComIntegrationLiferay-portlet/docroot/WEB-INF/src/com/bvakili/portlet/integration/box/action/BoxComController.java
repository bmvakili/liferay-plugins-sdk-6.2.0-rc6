
package com.bvakili.portlet.integration.box.action;

import com.liferay.portal.util.PortalUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

@Component
@RequestMapping(value = "VIEW")
public class BoxComController {

	@ResourceMapping
	public void catchAll(ResourceRequest request, ResourceResponse response) {

		try {
			System.out.println("resource mapping");
			PrintWriter writer = response.getWriter();
			writer.append("p");
		}
		catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}

	@ResourceMapping("poll")
	public void poll(
		ResourceRequest request, ResourceResponse response,
		@RequestParam("poll")
		final String poll) {

		try {
			System.out.println("poll resource");

			if (requestList.containsKey(poll)) {
				String[] pollStatus = requestList.get(poll);

				if (pollStatus[0].equalsIgnoreCase("done")) {
					String code = pollStatus[1];
					PrintWriter writer = response.getWriter();
					writer.append("o," + code);
					writer.close();
				}
				else {
					PrintWriter writer = response.getWriter();
					writer.append("p");
					writer.close();
				}
			}
			else {
				String time = "" + System.currentTimeMillis();
				String[] values = {
					"pending", time
				};
				requestList.put(poll, values);
				PrintWriter writer = response.getWriter();
				writer.append("p");
				writer.close();
			}

			// clean up old requests
		}
		catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}

	@ResourceMapping("register")
	public void registered(
		ResourceRequest request, ResourceResponse response,
		@RequestParam("registered")
		final String registered) {

		try {
			System.out.println("receveived registration request: " + registered);
			String[] values = requestList.get(registered);
			boolean bodyRead = true;
			StringBuffer sb = new StringBuffer();
			String line = null;
			try {
				BufferedReader reader = request.getReader();
				line = reader.readLine();
				while (line != null) {
					sb.append(line);
					line = reader.readLine();
				}

				reader.close();
			}
			catch (Exception e) {
				bodyRead = false;
				e.printStackTrace();
			}

			System.out.println("bodyRead: " + bodyRead);

			if (bodyRead) {
				HttpServletRequest convertReq =
					PortalUtil.getHttpServletRequest(request);
				HttpServletRequest originalReq =
					PortalUtil.getOriginalServletRequest(convertReq);
				String code = originalReq.getParameter("code");

				if (code != null && code.length() > 0) {
					System.out.println("code: " + code);

					values[0] = "done";
					values[1] = code;

				}
				else {
					PrintWriter writer = response.getWriter();
					writer.append("f");
					writer.close();
				}
			}
			else {
				PrintWriter writer = response.getWriter();
				writer.append("f");
				writer.close();
			}
		}
		catch (IOException e) {
 
			e.printStackTrace();
		}
	}

	@RequestMapping
	public String request(RenderRequest request, RenderResponse response) {

		request.setAttribute("hi", "hello! !!!! 123  ");
		return "view";
	}

	private static Map<String, String[]> requestList =
		new ConcurrentHashMap<String, String[]>();

}
