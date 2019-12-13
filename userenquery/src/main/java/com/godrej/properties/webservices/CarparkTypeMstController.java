package com.godrej.properties.webservices;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.model.CarparkTypeMst;
import com.godrej.properties.model.ProjectRegion;
import com.godrej.properties.model.SchemePromotional;
import com.godrej.properties.service.CarparkTypeMstService;
import com.godrej.properties.service.EnqAndOfferDtlService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class CarparkTypeMstController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private CarparkTypeMstService carparkTypeMstService;
	
	
	@RequestMapping(value = "/getCarparkTypeMst", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getCarparkType(@RequestParam("projectSFID") String projectSFID) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		List<CarparkTypeMst> carparkTypeList=carparkTypeMstService.getCarparkType(projectSFID);
		
		return gson.toJson(carparkTypeList);
	}
	
	
	
	@RequestMapping(value = "/insertCarparkType", method = RequestMethod.POST)
	public String addSchemePromotional(@RequestParam("region") String region,
			@RequestParam("projectName") String projectName,
			@RequestParam("projectid") String projectid,
			@RequestParam("carparkName") String carparkName) {	
		
		CarparkTypeMst carparkDtl = new CarparkTypeMst();
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