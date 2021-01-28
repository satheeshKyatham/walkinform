package com.godrej.properties.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godrej.properties.dto.SysConfigEnum;
import com.godrej.properties.master.service.SysConfigService;
import com.godrej.properties.model.UserMaster;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class ProjectDtlController {
	
	private Logger log =  LoggerFactory.getLogger(getClass());

	@Autowired
	private SysConfigService sysConfigService;
	
	@PostMapping(value = "/setSelectedProjectDtl", produces = "application/json")
	public @ResponseBody String userValidateLogin(@RequestParam("projectsfid") String projectsfid, HttpServletRequest req) {
		
	 GsonBuilder gsonBuilder = new GsonBuilder();
	 Gson gson = gsonBuilder.create(); 
	 UserMaster master= new UserMaster();
	 
	 try {
		 HttpSession session= req.getSession();
		
		/* Add Foyr API URL */
		String foyrUrl = sysConfigService.getValue(SysConfigEnum.FOYR_API_ENDPOINT, projectsfid);
		
		if (foyrUrl != null) {
			session.setAttribute("FOYRAPI",""+foyrUrl);
		} else {
			session.setAttribute("FOYRAPI",null);		
		}
		/* END Add Foyr API URL */
		 
		 session.setAttribute("PROJECTSFID",""+projectsfid);
		 
		 
		 master.setMsg("STATUS_OK");
	 } catch (Exception e) {
		 log.info("Error when set logged in selected project attribute in session : " + e);
		 master.setMsg("STATUS_NOTOK");
	 }	 
	 	
	 
	   return gson.toJson(master);
	}
	
}