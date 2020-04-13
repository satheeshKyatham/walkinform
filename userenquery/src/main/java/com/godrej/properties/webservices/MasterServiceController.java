package com.godrej.properties.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.model.Vw_UserMaster;
import com.godrej.properties.model.Vw_UserProjectMapping;
import com.godrej.properties.service.VW_UserMasterService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class MasterServiceController {

	@Autowired
 	private VW_UserMasterService vW_UserMasterService;
	
	@GetMapping(value = "/getUserProjectMapping", produces = "application/json")
	public String getUserProjectMapping(@RequestParam("projectid") String projectid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		List<Vw_UserProjectMapping> adt=vW_UserMasterService.getUserProjectMapping(projectid);
		return gson.toJson(adt);
	} 
	
	@GetMapping(value = "/getUserProjectMappingCT", produces = "application/json")
	public String getUserProjectMappingCT(@RequestParam("projectid") String projectid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String condition = " and isctlead='Y'";
		List<Vw_UserProjectMapping> adt=vW_UserMasterService.getUserProjectMappingTeamLead(projectid, condition);
		return gson.toJson(adt);
	} 
	
	@GetMapping(value = "/getUserProjectMappingST", produces = "application/json")
	public String getUserProjectMappingST(@RequestParam("projectid") String projectid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String condition = " and isstlead='Y' ";
		List<Vw_UserProjectMapping> adt=vW_UserMasterService.getUserProjectMappingTeamLead(projectid, condition);
		return gson.toJson(adt);
	} 
	
	@GetMapping(value = "/getUserProjectList", produces = "application/json")
	public String getUserProjectList(@RequestParam("userId") String userid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		List<Vw_UserMaster> adt=vW_UserMasterService.getUserProjectList(userid);
		return gson.toJson(adt);
	}
	
	@GetMapping(value = "/getUserListProjectWise", produces = "application/json")
	public String getUserListProjectWise(@RequestParam("projectid") String projectid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		List<Vw_UserMaster> adt=vW_UserMasterService.getUserListProjectWise(projectid);
		return gson.toJson(adt);
	} 
	
	@GetMapping(value = "/getProjectListUserWise", produces = "application/json")
	public String getProjectListUserWise(@RequestParam("userid") String userid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		List<Vw_UserProjectMapping> adt=vW_UserMasterService.getProjectListUserWise(userid);
		return gson.toJson(adt);
	} 
}
