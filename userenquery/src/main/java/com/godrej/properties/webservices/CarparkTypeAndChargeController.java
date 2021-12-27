package com.godrej.properties.webservices;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.dto.SoldCarParkDTO;
import com.godrej.properties.model.CarParkingMapping;
import com.godrej.properties.model.CarparkCount;
import com.godrej.properties.model.CarparkTypeAndCharge;
import com.godrej.properties.service.CarparkTypeAndChargeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class CarparkTypeAndChargeController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private CarparkTypeAndChargeService carparkTypeAndChargeService;
	
	
	@RequestMapping(value = "/getCarparkNameCS", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getCarparkType(@RequestParam("projectSFID") String projectSFID) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		List<CarparkTypeAndCharge> carparkTypeList=carparkTypeAndChargeService.getCarparkType(projectSFID);
		
		return gson.toJson(carparkTypeList);
	}
	
	
	@GetMapping(value = "/getCarparkCount", produces = "application/json")
	public String getCarparkCount(@RequestParam("projectSFID") String projectSFID) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		
		List<CarparkCount> carparkTypeList=carparkTypeAndChargeService.getCarparkCount(projectSFID);
		
		return gson.toJson(carparkTypeList); 
	}
	
	@GetMapping(value = "/getCarParkingCombination", produces = "application/json")
	public String getCarParkingCombination(@RequestParam("towersfid") String towersfid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		List<CarParkingMapping> carparkTypeList=carparkTypeAndChargeService.getCarParkingCombination(towersfid);
		return gson.toJson(carparkTypeList); 
	}
	
	
	@PostMapping(value = "/insertCarParkingCombination", produces = "application/json")
	public @ResponseBody CarParkingMapping insertCarParkingCombination(@RequestBody List<CarParkingMapping> carParkingMapping) {
		System.out.println("Tower SFID:-"+carParkingMapping.get(0).getTower_sfid());
		List<CarParkingMapping> carparkTypeList=carparkTypeAndChargeService.insertCarParkingCombination(carParkingMapping);
		CarParkingMapping cap = new CarParkingMapping();
		return cap;
	}
	
	@GetMapping(value = "/inActiveParkingCombination", produces = "application/json")
	public String inActiveParkingCombination(@RequestParam("property_type_sfid") String property_type_sfid,@RequestParam("parking_category") String parking_category,@RequestParam("isactive") String isactive) {
		return carparkTypeAndChargeService.inActiveCarParkingCombination(property_type_sfid,parking_category,isactive);
		
	}
	
	@GetMapping(value = "/getParkingCount_D4U_Enabled", produces = "application/json")
	public @ResponseBody List<SoldCarParkDTO> getParkingCount(@RequestParam("projectsfid") String projectsfid) {
		return carparkTypeAndChargeService.getCarParkingCount(projectsfid);
	}
	
	/*@RequestMapping(value = "/getCarparkCount", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getCarparkCount(@RequestParam("projectSFID") String projectSFID) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		List<CarparkCount> carparkTypeList=carparkTypeAndChargeService.getCarparkCount(projectSFID);
		
		return gson.toJson(carparkTypeList);
	}*/
	
	
	
}