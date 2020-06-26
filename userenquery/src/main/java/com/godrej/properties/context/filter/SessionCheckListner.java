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
		filterUrlList.add("/getUserProjectList");
		filterUrlList.add("/authenticationDevice");
		filterUrlList.add("/android_generateTokenMobile");
		filterUrlList.add("/android_generateTokenMobileEOI");
		filterUrlList.add("/generateWalkinToken");
		filterUrlList.add("/android_generateToken");
		filterUrlList.add("/getdeatails");
		filterUrlList.add("/userinfo");
		filterUrlList.add("/loginvalidator");
		filterUrlList.add("/error");
		filterUrlList.add("/ccAvenue");
		filterUrlList.add("/ccAvenueLogin");
		filterUrlList.add("/ccavResponseHandler");
		filterUrlList.add("/ccavRequestHandler");
		filterUrlList.add("/getpaymentplanlist_due");
		filterUrlList.add("/getLdapUserDetails");//added by satheesh K.
		filterUrlList.add("/d4upreofferAPI");//added by satheesh K.
		filterUrlList.add("/d4uEnquiryprotectionAPI");//added by satheesh K.
		
		
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		/*System.out.println("in interceptor" + request.getServletPath());*/

		HttpSession session = request.getSession(false);
		String url = request.getServletPath();

		/*if (isXMLHttpRequest(request) && !(isFilteredUrl(url))
				&& (null == session )) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // status code is 401
			return false;
		} else if (!(isFilteredUrl(url)) && (null == session || session.getAttribute("UserSession") == null)) {
			try {
				response.sendRedirect(request.getContextPath()+"/sessiontimeout");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		} else {
			return true;
		}*/
		
		if (isXMLHttpRequest(request) && !(isFilteredUrl(url)) && (null == session)) {
			/// ||session.getAttribute("context") == null
			
			/* * response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //
			 * status code is 401*/
			  try {
				 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
				response.sendRedirect(request.getContextPath() + "/sessiontimeout");
				System.out.println("ajax "+request.getContextPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
