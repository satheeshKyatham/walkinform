package com.godrej.properties.context.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionCheckListner extends HandlerInterceptorAdapter {

	private static List<String> filterUrlList = new ArrayList<>();

	public SessionCheckListner() {
		filterUrlList.add("/saleslogin");
		filterUrlList.add("/");
		filterUrlList.add("/userValidateLogin");
		filterUrlList.add("/10.21.48.21:389");
		filterUrlList.add("/sessiontimeout");

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		/*System.out.println("in interceptor" + request.getServletPath());*/

		HttpSession session = request.getSession(false);
		String url = request.getServletPath();

		if (isXMLHttpRequest(request) && !(isFilteredUrl(url)) && (null == session)) {
			/// ||session.getAttribute("context") == null
			/*
			 * response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //
			 * status code is 401
			  try {*/
				 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
				/*response.sendRedirect(request.getContextPath() + "/sessiontimeout");*/
				/*System.out.println("ajax "+request.getContextPath());*/
			/*} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			return false;
		} else if (!(isFilteredUrl(url)) && (null == session)) {// ||
																// session.getAttribute("context")
																// == null
			try {
				response.sendRedirect(request.getContextPath() + "/sessiontimeout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		} else {
			return true;
		}
	}

	private boolean isXMLHttpRequest(HttpServletRequest request) {
		return request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equals("XMLHttpRequest");
	}

	private boolean isFilteredUrl(String requestUrl) {

		if (requestUrl.equals("/")) {
			return true;
		} else if (requestUrl.contains("/js/")) {
			return true;
		} else if (requestUrl.contains("/css/")) {
			return true;
		} else if (requestUrl.contains("/img/")) {
			return true;
		} else {
			for (String url : filterUrlList) {
				if (!"/".equalsIgnoreCase(url))
					if (requestUrl.contains(url)) {
						return true;
					}
			}
		}

		return false;
	}
}
