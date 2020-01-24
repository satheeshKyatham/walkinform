package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.InventoryDtlService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class InventoryDtlController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private InventoryDtlService inventoryDtlService;
	
 	/*@RequestMapping(value = "/getInventoryRecords", method = RequestMethod.POST)
	public String getHouseUnit(@RequestParam("project_code") String project_code ,@RequestParam("tower_code") String tower_code,@RequestParam("floor_code") String floor_code,@RequestParam("unit") String unit) {
 		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		//List<InventoryDtl> plans=inventoryDtlService.getHouseUnit(project_code,tower_code,floor_code,unit);
		return gson.toJson(inventoryDtlService.getHouseUnit(project_code,tower_code,floor_code,unit));
	}*/
 	
 	
 	
 	
 	@RequestMapping(value = "/getInventoryRecords", method = RequestMethod.GET, produces = "application/json")
	public String getHouseUnitList(@RequestParam("project_code") String project_code ,@RequestParam("tower_code") String tower_code,@RequestParam("floor_code") String floor_code,@RequestParam("unit") String unit) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition = "";
		
		whereCondition = "  propstrength__project_name__c='"+project_code+"'  and tower_code__c='"+tower_code+"' "; 
		
		//whereCondition = "propstrength__project__c=" + "'"+projectSfid+"'";
		
		return gson.toJson(inventoryDtlService.getHouseUnit(whereCondition));
	}
 	
}