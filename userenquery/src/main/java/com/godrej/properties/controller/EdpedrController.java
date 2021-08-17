package com.godrej.properties.controller;

import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.EdpedrService;

@CrossOrigin(origins = "*")
@RestController
public class EdpedrController {
	
	@Autowired
	EdpedrService edpedrService;
	 
	@RequestMapping(value = "/updateEDP", method = RequestMethod.POST)
	public String updatePPData(
			@RequestParam("projectsfid") String projectsfid,
			@RequestParam("floors") String floors,
			@RequestParam("tower") String tower,
			@RequestParam("edpDate") String edpDate,
			HttpServletRequest request, HttpServletResponse response
			) {
			
			String result = "";
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
			try {
				formatter.parse(edpDate);
				System.out.println("Valid Date");
			} catch (Exception e) {
				result = "{\"status\":\"STATUS_NOTOK\",\"edpUpdateCount\":\"0\", \"errorid\": \"E5\",\"error_msg\":\"Invalid date format\"}";
				return result;
			}
			
			HttpSession session = request.getSession();
			if (session.getAttribute("USERID") == null) {
				result = "{\"status\":\"STATUS_NOTOK\",\"edpUpdateCount\":\"0\", \"errorid\": \"E1\",\"error_msg\":\"Session expired kindly relogin and try again\"}";
				return result;
			}   
			
			if (projectsfid != null && projectsfid.length() > 0 && floors != null && floors.length() > 0 && edpDate != null && edpDate.length() > 0) {
				try {
					/*String [] data= floors.split(",");
					 
					StringBuilder successUnitUpdate = new StringBuilder();
					String multiFloor = "";
					 
					for (int i=0;i<data.length;i++){
						successUnitUpdate.append("'"+data[i]+"'");
						successUnitUpdate.append(",");
					}
					 
					 multiFloor = successUnitUpdate.toString();
					 if (multiFloor != null && multiFloor.length() > 0
							 && multiFloor.charAt(multiFloor.length() - 1) == ',') {
						 multiFloor = multiFloor.substring(0, multiFloor.length() - 1);
					 }*/
					 
					String userid = session.getAttribute("USERID").toString();
					
					edpedrService.insertData(floors, projectsfid, edpDate, userid, tower);	
						
					 Integer edpUpdate = edpedrService.updateData(floors, projectsfid, edpDate);
					 
					 result = "{\"status\":\"STATUS_OK\",\"edpUpdateCount\":\"0\", \"errorid\": \"E2\",\"error_msg\":\""+edpUpdate+" record(s) are updated successfully\"}";
					 return result;
						
				} catch (Exception e) {
					result = "{\"status\":\"STATUS_NOTOK\",\"edpUpdateCount\":\"0\", \"errorid\": \"E3\",\"error_msg\":\"Problem in updating the Expected date of possession\"}";
					return result;
				}
			} else {
				result = "{\"status\":\"STATUS_NOTOK\",\"edpUpdateCount\":\"0\", \"errorid\": \"E4\",\"error_msg\":\"Please fill all required fields\"}";
				return result;
			} 
	}
	
	
	
	@RequestMapping(value = "/updateEDR", method = RequestMethod.POST)
	public String updateEDR(
			@RequestParam("projectsfid") String projectsfid,
			@RequestParam("bookingsfid") String bookingsfid,
			@RequestParam("edrDate") String edrDate,
			@RequestParam("selectedTower") String selectedTower,
			@RequestParam("selectedFloor") String selectedFloor,
			HttpServletRequest request, HttpServletResponse response
			) {
			
			String result = "";
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
			try {
				formatter.parse(edrDate);
				System.out.println("Valid Date");
			} catch (Exception e) {
				result = "{\"status\":\"STATUS_NOTOK\",\"edpUpdateCount\":\"0\", \"errorid\": \"E5\",\"error_msg\":\"Invalid date format\"}";
				return result;
			}
			
			HttpSession session = request.getSession();
			if (session.getAttribute("USERID") == null) {
				result = "{\"status\":\"STATUS_NOTOK\",\"edpUpdateCount\":\"0\", \"errorid\": \"E1\",\"error_msg\":\"Session expired kindly relogin and try again\"}";
				return result;
			}  
			
			if (projectsfid != null && projectsfid.length() > 0 && bookingsfid != null && bookingsfid.length() > 0 && edrDate != null && edrDate.length() > 0) {
				try {
					String userid = session.getAttribute("USERID").toString();
					
					edpedrService.insertDataEDR(selectedFloor, projectsfid, edrDate, userid, selectedTower, bookingsfid);
					
					 Integer edpUpdate = edpedrService.updateEDRData(bookingsfid, projectsfid, edrDate);
					 
					 result = "{\"status\":\"STATUS_OK\",\"edpUpdateCount\":\"0\", \"errorid\": \"E2\",\"error_msg\":\""+edpUpdate+" record(s) are updated successfully\"}";
					 return result;
						
				} catch (Exception e) {
					result = "{\"status\":\"STATUS_NOTOK\",\"edpUpdateCount\":\"0\", \"errorid\": \"E3\",\"error_msg\":\"Problem in updating the Expected date of registration\"}";
					return result;
				}
			} else {
				result = "{\"status\":\"STATUS_NOTOK\",\"edpUpdateCount\":\"0\", \"errorid\": \"E4\",\"error_msg\":\"Please fill all required fields\"}";
				return result;
			} 
	}
	
	
	
}