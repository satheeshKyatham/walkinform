package com.godrej.properties.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.master.dto.ReferenceListDto;
import com.godrej.properties.master.dto.TemplateDto;
import com.godrej.properties.master.service.TemplateService;
import com.godrej.properties.model.AssignedUser;
import com.godrej.properties.model.ProjectLaunch;
import com.godrej.properties.model.Token;
import com.godrej.properties.model.Vw_UserMaster;
import com.godrej.properties.service.AssignUserService;
import com.godrej.properties.service.ProjectLaunchService;
import com.godrej.properties.service.TokenService;
import com.godrej.properties.service.VW_UserMasterService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class SalesDeskController {

 	@Autowired
	private	TokenService tokenService;
 	
 	@Autowired
	private	AssignUserService assignUserService;
 	
 	@Autowired
 	private VW_UserMasterService vW_UserMasterService;

	@Autowired
 	private ProjectLaunchService projectLaunchService;
 	
	@Autowired
	private TemplateService templateService;

	@RequestMapping(value = { "/salesdesk"}, method = RequestMethod.GET)
	public String indexPost(ModelMap model,HttpServletRequest request,@RequestParam("id") String id) {
		List<Token> tokens=tokenService.getuniqeTypes();
		model.addAttribute("tokens",tokens );
		 return "salesdesk";
	}
	
	@RequestMapping(value = { "/project"}, method = RequestMethod.GET)
	public String desk(ModelMap model,HttpServletRequest request) {
		
		 return "project";
	}
	
	
	
	
	
	@RequestMapping(value = { "/saleslogin"}, method = RequestMethod.GET)
	public String salesLogin(ModelMap model,HttpServletRequest request) {
		model.addAttribute("Projects", projectLaunchService.getActiveProjectList());	
		 return "saleslogin";
	}
	
	@RequestMapping(value = { "/assigndesk"}, method = RequestMethod.GET)
	public String assigndesk(ModelMap model,HttpServletRequest request,@RequestParam("id") String id,@RequestParam("projectid") String projectid) {
		List<Token> tokens=tokenService.getuniqeTypes();
		//List<Vw_UserMaster> userList= vW_UserMasterService.getUserListProjectWise(projectid);
		model.addAttribute("Projects", projectLaunchService.getActiveProjectList());	
		model.addAttribute("tokens",tokens );
		//model.addAttribute("vw_usermaster",userList );
		 return "assigndesk";
	}
	 
	@RequestMapping(value = { "/assignedusers"}, method = RequestMethod.GET)
	public String assignedusers(ModelMap model,HttpServletRequest request,@RequestParam("userId") String user_id,@RequestParam("projectid")  String projectid) {
		TemplateDto template = templateService.getTemplate(projectid);
		if(template !=null) {
			String templateText =  template.getBigText()==null?"":template.getBigText();
			templateText= templateText.replace("'","\\'");
			model.addAttribute("offerTemplate", templateText);
		}
		 return "assignedusers";
	}
	
	 @RequestMapping(value = "/updateAssigndesk", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
		public String updateUserMaster(@RequestParam("tokenID") String tokenID) {
			//Gson gson = new GsonBuilder().serializeNulls().create();
			return "";
		}
	 @RequestMapping(value = "/admininventory/{projectid}/{projectname}/{userid}", method = RequestMethod.GET)
		public ModelAndView admininventory(
				@PathVariable("projectid") String projectid,@PathVariable("projectname") String projectname,
				@PathVariable("userid") Integer userid){
		 ModelAndView view=new ModelAndView("admininventory");

			view.addObject("projectSfid",projectid);
			view.addObject("userId",userid);
			view.addObject("projectName",projectname); 
			 
			return view;
		 
		}
	 
}
