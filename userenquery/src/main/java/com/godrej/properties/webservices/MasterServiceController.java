package com.godrej.properties.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.model.Vw_UserMaster;
import com.godrej.properties.model.Vw_UserProjectMapping;
import com.godrej.properties.model.Vw_UserTowerMapping;
import com.godrej.properties.service.ProjectLaunchService;
import com.godrej.properties.service.VW_UserMasterService;
import com.godrej.properties.service.VW_UserTowerMasterService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class MasterServiceController {

	@Autowired
 	private VW_UserMasterService vW_UserMasterService;
	
	@Autowired
 	private VW_UserTowerMasterService vW_UserTowerMasterService;
	
	@Autowired
	private ProjectLaunchService projectLaunchService;
	
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
		List<Vw_UserProjectMapping> adt = null;
		if(!userid.equals("null"))
		{
			adt=vW_UserMasterService.getProjectListUserWise(userid);
		}
		return gson.toJson(adt);
	} 
	
	@GetMapping(value = "/getTowerListUserWise", produces = "application/json")
	public String getTowerListUserWise(@RequestParam("userid") String userid, @RequestParam("region") String region) {
		
		
		// For multiple verticales
		String finalRegion = "";
		if (region != null && !region.equals("null") && !region.equals("")) {
			String [] multiVerticales= region.split(",");
			
			StringBuilder modifiedVer = new StringBuilder();
			
			
			for (int i=0;i<multiVerticales.length;i++){
				modifiedVer.append("'"+multiVerticales[i]+"'");
				modifiedVer.append(",");
			}
			
			finalRegion = modifiedVer.toString();
			
			if (finalRegion != null && finalRegion.length() > 0 && finalRegion.charAt(finalRegion.length() - 1) == ',') {
				finalRegion = finalRegion.substring(0, finalRegion.length() - 1);
			}
		} else {
			finalRegion = null;
		}
		// END For multiple verticales
		
		
		
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		List<Vw_UserTowerMapping> adt=vW_UserTowerMasterService.getProjectListUserWise(userid, finalRegion);
		return gson.toJson(adt);
	} 
	
	@GetMapping(value = "/getSelectedProjectMaster", produces = "application/json")
	public String getSelectedProjectMaster(@RequestParam("projectid") String projectid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(projectLaunchService.getprojectDetails(projectid));
	} 
	
}
