package com.godrej.properties.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godrej.properties.dto.LdapUserDetailsDto;
import com.godrej.properties.model.ADLoginPass;
import com.godrej.properties.model.UserMaster;
import com.godrej.properties.model.Vw_UserMaster;
import com.godrej.properties.service.AdLoginUserService;
import com.godrej.properties.service.VW_UserMasterService;
import com.godrej.properties.validator.UserValidator;
import com.godrej.properties.webservices.LdapServiceController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class AuthenticationController {
	
	private Logger log =  LoggerFactory.getLogger(getClass());

	@Autowired
	private LdapServiceController ldapServiceController;

	@Autowired
	private AdLoginUserService adUserLoginPassService;

	@Autowired
 	private VW_UserMasterService vW_UserMasterService;

	@Autowired
	private UserValidator userValidator;
	
	@PostMapping(value = "/validateLogin", produces = "application/json")
	public @ResponseBody String userValidateLogin(@RequestParam("emailid") String emailid,
			@RequestParam("password") String password,
			HttpServletRequest req) {
	 GsonBuilder gsonBuilder = new GsonBuilder();
	 Gson gson = gsonBuilder.create(); 
	 UserMaster master= new UserMaster();
	 
	 if(emailid== null || "".equals(emailid.trim())) {
		 master.setMsg("Credential can not be empty.");
		return gson.toJson(master);
	 }
	 emailid=emailid.toLowerCase();
	 master.setUser_name(emailid);
	 master.setEmailid(emailid);
	 master.setPassword(password);
	 if(emailid.contains("@godrejproperties.com")) {
		 //changes required here
		 LdapUserDetailsDto dto = new LdapUserDetailsDto();
		 dto.setEmail(emailid);
		 dto.setPassword(password);
		 LdapUserDetailsDto ldap= ldapServiceController.getLdapUserDetails(dto);
		 boolean isAD=ldap.isIsvalid();
		 if(isAD) {
			List<ADLoginPass> user= adUserLoginPassService.getUserList(emailid);
			if(user!=null && !user.isEmpty()) {
				isAD=true;
			}
		 }
		
		 if(isAD) {
			 master= userValidator.validateUser(master,"A");
		 }else {
			 master.setMsg("Please check your username and password. If you still can't login , your account might be locked, please send email to servicedesk@godrejinds.com or reset your password with https://passwordreset.microsoftonline.com");
			 return gson.toJson(master);
		 }
	 }else {
		 master= userValidator.validateUser(master,"S");
	 }
	 //Changes By Satheesh
	 Vw_UserMaster userMaster=vW_UserMasterService.getUserDetails(master.getUser_id());//,projectId
	 master.setPassword("");
	 if(userMaster!=null) {
		 master.setProjectId( userMaster.getProjectName());
		 master.setProjectName( userMaster.getProjectId());
		 master.setRole( userMaster.getRole());
		 
		 HttpSession session= req.getSession();
		 session.setAttribute("USERNAME",""+userMaster.getUser_name());
		 session.setAttribute("USERMOBILENO",""+userMaster.getMobileNo());
		 session.setAttribute("USERID",""+ master.getUser_id());
		 session.setAttribute("USEREMAIL", master.getEmailid());
		 session.setAttribute("ROLE",""+ master.getMst_user_rolemaster_id());
		 session.setAttribute("PRONAME", userMaster.getProjectName());
		 session.setAttribute("PROID",""+ userMaster.getProjectId());
		 session.setAttribute("Assignto","");
		 session.setAttribute("Closingmgr",""+ userMaster.getClosingmgr());
		 
	 }else {
		 log.info("You are not a user in D4U system.Please contact your site head for getting permission.");
		 master.setMsg("You are not a user in D4U system.Please contact your site head for getting permission.");
	 }
	   return gson.toJson(master);
	}
	
}
