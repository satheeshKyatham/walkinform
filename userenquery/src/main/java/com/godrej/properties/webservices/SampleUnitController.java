package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.SampleUnitService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class SampleUnitController<MultipartFormDataInput> {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private SampleUnitService sampleUnitService;
	
	@RequestMapping(value = "/getSampleUnit", method = RequestMethod.POST)
	public String getEnqDtl(@RequestParam("projectSFID") String projectSFID, @RequestParam("towerCode") String towerCode, @RequestParam("typology") String typology, @RequestParam("floorBand") String floorBand ) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();	
			
		String condition = " project_sfid = '"+projectSFID+"' AND tower_code = '"+towerCode+"' AND typology = '"+typology+"' AND floor_range_name = '"+floorBand+"' AND isactive = 'A' ";
		
		return gson.toJson(sampleUnitService.geteoiSampleUnit(condition));
	}
}