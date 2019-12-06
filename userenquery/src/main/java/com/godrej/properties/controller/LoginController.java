package com.godrej.properties.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.LocalDate;
import org.ksoap2.serialization.SoapObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.godrej.properties.model.RoleMst;
import com.godrej.properties.service.RoleMstService;
import com.godrej.properties.util.CallWebServices;

@SuppressWarnings({"unused"})
@Controller
@RequestMapping("/")
 
public class LoginController {
	String userId=null;
	String userFunction = null;
	
	
	String body = null;
	String subject = null;
	String to = null;
	String cc = null;
	String bc = null;
	
	
	
	 
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private RoleMstService roleMstService;
	
	
	
	/*@Autowired
	EmployeeService service;*/
	public LoginController() {
		
	}

	/*@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("loginPage");
		return model;
	}*/
	/*@RequestMapping(value = { "/loginPage"}, method = RequestMethod.POST)
	public ModelAndView loginPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("loginPage");
		return model;
	}*/
	
	
	/*Logout*/
	@RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
	public @ResponseBody ModelAndView logoutUser(HttpServletRequest request,HttpServletResponse response) {		
		request.getParameter("logout");			
		HttpSession session=request.getSession();		
		session.setAttribute("UserData", null);	
		session.invalidate();		
		ModelAndView model = new ModelAndView("redirect:/saleslogin");
		return model;
	}
	
	
	
	
	/*@RequestMapping(value = {"/ajaxSendRequest"}, method = RequestMethod.POST  )
	public @ResponseBody ModelAndView getTermsRequest(HttpServletRequest request,HttpServletResponse response) {		 

		RequestDtl requestDtl= new RequestDtl();
		requestDtl.setRequest_user_id(request.getParameter("userId"));
		requestService.saveRequest(requestDtl);
		ModelAndView model = new ModelAndView();
		model.setViewName("UserDashBoard");
		
		return model;
	}*/
	
	@RequestMapping(value = { "/loginform"}, method = RequestMethod.POST)
	public ModelAndView loginSubmit(@RequestParam String username,@RequestParam String password,HttpServletRequest request,HttpServletResponse response, ModelAndView retmodel) { //ModelAndView retmodel
		
		HttpSession httpsession=request.getSession();
		
		if(username!=null && password!=null  && !username.equals("")  &&  !password.equals("")){
				
				if(!callLoginWebservice(username,password,request,httpsession, retmodel)) { //, retmodel
					ModelAndView model = new ModelAndView();
					model.addObject("error", "Invalid Credentials provided.");
					model.setViewName("index");
					return model;}
				else {	
					
					List<RoleMst> roleMst = roleMstService.roleDtl(userId);
							
				if (roleMst != null) {
					if (roleMst.size() > 0) {
						if (roleMst.get(0).getRole_name().toLowerCase().equals("admin")) {
							System.out.println("ADMIN :::::: ");

							ModelAndView model = new ModelAndView("adminRqst");
							return model;

						} else if (roleMst.get(0).getRole_name().toLowerCase().equals("sales")) {
							System.out.println("SALES :::::: ");

							ModelAndView model = new ModelAndView("costsheet");
							return model;
						}
					}
				} else {
					System.out.println("You don't have permission to access");

					ModelAndView model = new ModelAndView("loginPage");
					model.addObject("error", "You don't have permission to access");
					return model;
				}
					
					
					
					
					LocalDate currentDate = new LocalDate();
					Date date = currentDate.toDateTimeAtStartOfDay().toDate();
					
					SimpleDateFormat sm = new SimpleDateFormat("dd-MMM-YYYY");
					String strDate = sm.format(date);
					
					//	System.out.println(strDate);
				    
					
					
				
				}
				
				
			}
		
			else{
				ModelAndView model = new ModelAndView("loginPage");
				model.addObject("error", "Invalid Credentials provided.");
				return model;
			}
		return null;
		
		
		
	}
	private boolean callLoginWebservice(String username,String password,HttpServletRequest request,HttpSession httpsession, ModelAndView model ) { //ModelAndView model
		  CallWebServices webService;
		  boolean logins=false;
		  HashMap<String, String> UserData = new HashMap<String, String>();
		try{
			webService= new CallWebServices("MobileService.asmx?wsdl","GodrejiteLogin");
			webService.getProperties().add(webService.new RequestProperty("strUserName", username));
			webService.getProperties().add(webService.new RequestProperty("strPassword", password));
			Object resultString = webService.CallWS();
			SoapObject result = (SoapObject)resultString;
			if (result != null) {
				if(result.getProperty("IsEmp").toString().equals("true") && result.getProperty("IsValid").toString().equals("true")){
				SoapObject so1 = (SoapObject) result.getProperty("DSusers");
				so1 = (SoapObject) so1.getProperty("diffgram");
				if(so1.getPropertyCount() > 0){
					so1 = (SoapObject) so1.getProperty("NewDataSet");
					for(int i = 0; i < so1.getPropertyCount();){
						so1 = (SoapObject) so1.getProperty(i);
						if(so1.toString().contains("UserID")){
							UserData.put("Designation",so1.getPropertyAsString("Designation").toString());
							UserData.put("LocationName",so1.getPropertyAsString("LocationName").toString());
							UserData.put("EmailID",so1.getPropertyAsString("EmailID").toString());
							UserData.put("userID", so1.getPropertyAsString("UserID").toString());
							UserData.put("usrName", so1.getPropertyAsString("FirstName").toString()+" "+so1.getPropertyAsString("LastName").toString());
							
							/*UserData.put("role_name",so1.getPropertyAsString("role_name").toString());
							UserData.put("email_id",so1.getPropertyAsString("email_id").toString());
							*/
							
							httpsession.setAttribute("UserData", UserData);
							httpsession = request.getSession(true);
							userId=so1.getPropertyAsString("UserID").toString();							
							
							logins=true;																	
						}
						break;
						}
				 
					}
				else{ 
					logins=false;
				}
				}
			else{
				logins=false;
			}
			}
				
			else{
				logins=false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return logins;
		}
}
