package com.godrej.properties.webservices;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.model.CarparkTypeEOI;
import com.godrej.properties.model.CarparkTypeMst;
import com.godrej.properties.model.TowerBand;
import com.godrej.properties.service.CarparkTypeEOIService;
import com.godrej.properties.service.CarparkTypeMstService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class CarparkTypeMstController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private CarparkTypeMstService carparkTypeMstService;
	
	@Autowired
	private CarparkTypeEOIService carparkTypeEOIService;
	
	
	@RequestMapping(value = "/getCarparkTypeMst", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getCarparkType(@RequestParam("projectSFID") String projectSFID) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		List<CarparkTypeMst> carparkTypeList=carparkTypeMstService.getCarparkType(projectSFID);
		
		return gson.toJson(carparkTypeList);
	}
	
	
	@RequestMapping(value = "/getCarparkEOIMst", method = RequestMethod.GET, produces = "application/json")
	public String getCarparkEOI(@RequestParam("project_sfid") String project_sfid) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		List<CarparkTypeEOI> plans=carparkTypeEOIService.getTowerBand(project_sfid);
		
		return gson.toJson(plans);
	}
	
	
	@RequestMapping(value = "/insertCarparkType", method = RequestMethod.POST)
	public String addSchemePromotional(@RequestParam("region") String region,
			@RequestParam("projectName") String projectName,
			@RequestParam("projectid") String projectid,
			@RequestParam("carparkName") String carparkName,
			@RequestParam("carparkAlias") String carparkAlias, 	
			@RequestParam("totalCarpark") String totalCarpark) {
		
		CarparkTypeMst carparkDtl = new CarparkTypeMst();
		
		
		if (!totalCarpark.equals("null") && !totalCarpark.equals("") && !totalCarpark.equals(" ")) {
			carparkDtl.setTotal_carpark(Integer.parseInt(totalCarpark) );
		} else {
			carparkDtl.setTotal_carpark(0);
		}
		
		if (!carparkAlias.equals("null") && !carparkAlias.equals("") && !carparkAlias.equals(" ")) {
			carparkDtl.setCarpark_alias(carparkAlias);
		} else {
			carparkDtl.setCarpark_alias(null);
		}
		
		carparkDtl.setRegion_name(region);
		carparkDtl.setProject_name(projectName);
		carparkDtl.setProject_id(projectid);
		carparkDtl.setCarpark_type(carparkName);
		carparkDtl.setCreatedby("9999");
		carparkDtl.setUpdatedby("9999");
		carparkDtl.setIsactive("A");
		carparkTypeMstService.insertCarparkDtl(carparkDtl);
		return ("inserted");
	}
	
	
}