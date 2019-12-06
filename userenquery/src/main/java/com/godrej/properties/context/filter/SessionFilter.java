package com.godrej.properties.context.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.mail.Session;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public class SessionFilter implements Filter {
	
	
	private ArrayList<String> urlList;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest httpReq = (HttpServletRequest) request;
		String path = ((HttpServletRequest) request).getRequestURI();
		/* HttpSession session = ; */
		if (path.contains("/UserEnqury/saleslogin")) {
			res.addHeader("Server", "xyz");
			res.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
			res.addHeader("Pragma", "no-cache");
			res.addDateHeader("Expires", 0);
			chain.doFilter(httpReq, res);
			/*System.out.println("test"+res.encodeUrl(path));*/
			
		} else {
			if (httpReq.getSession(false) == null) {
				/*logoutUser1();*/
				String url=httpReq.getContextPath()+"/saleslogin";
				res.sendRedirect(url);
				
			} else {
				chain.doFilter(httpReq, response);
			}
		}

	}

	/*@RequestMapping(value = { "/saleslogin" }, method = RequestMethod.GET)
	public ModelAndView logoutUser1() {
		ModelAndView model = new ModelAndView();
		model.setViewName("saleslogin");
		return model;
	}*/


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
