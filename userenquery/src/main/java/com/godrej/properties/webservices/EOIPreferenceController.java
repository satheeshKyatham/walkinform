package com.godrej.properties.webservices;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.EOIPreferenceDtlService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class EOIPreferenceController {
	private Logger Log = LoggerFactory.getLogger(getClass());
	 
	@Autowired
	private EOIPreferenceDtlService eOIPreferenceDtlService;

	
	@RequestMapping(value = "/updateEOIPreference", method = RequestMethod.POST)
	public String updateEOIPreference(
			@RequestParam("preferenceJson") String preferenceJson,
			@RequestParam("userid") String userid,
			@RequestParam("username") String username,
			@RequestParam("enq_sfid") String enq_sfid,
			@RequestParam("project_sfid") String project_sfid
			, @RequestParam("unitsfidOldArray") String unitsfidOldArray 
			, @RequestParam("newUnitsfidArray") String newUnitsfidArray
			) throws ParseException {	
		
		
		if(    !userid.equals("")  
				&& !username.equals("")
				&& !enq_sfid.equals("")  
				&& !project_sfid.equals(""))  {
				
				try {
					//GsonBuilder gsonBuilder = new GsonBuilder();
					//Gson gson = gsonBuilder.create();
					String responseVal = eOIPreferenceDtlService.updateEOIPreference(preferenceJson, userid, enq_sfid, project_sfid, unitsfidOldArray, username, newUnitsfidArray);
					return responseVal;
				}  catch(Exception e) {
					Log.info("EOI Details not updating UPDATE_EOI_ER1004 :- ",e);				
					String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not updated on portal, please try again later\",\"error_id\":\"UPDATE_EOI_ER1004\"}";
					return response;
				}
		} else {
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"UPDATE_EOI_ER1005\"}";
			return response;
		}
	}
	 
	 
	@RequestMapping(value = "/deleteEOIPreference", method = RequestMethod.POST)
	public String deleteEOIPreference(
			@RequestParam("preferenceJson") String preferenceJson,
			@RequestParam("userid") String userid,
			@RequestParam("username") String username,
			@RequestParam("enq_sfid") String enq_sfid,
			@RequestParam("project_sfid") String project_sfid
			, @RequestParam("unitsfidOldArray") String unitsfidOldArray 
			//, @RequestParam("newUnitsfidArray") String newUnitsfidArray
			) throws ParseException {	
		
		
		if(    !userid.equals("")  
				&& !username.equals("")
				&& !enq_sfid.equals("")  
				&& !project_sfid.equals(""))  {
				
				try {
					//GsonBuilder gsonBuilder = new GsonBuilder();
					//Gson gson = gsonBuilder.create();
					String responseVal = eOIPreferenceDtlService.deleteEOIPreference(preferenceJson, userid, enq_sfid, project_sfid, unitsfidOldArray, username);
					return responseVal;
				}  catch(Exception e) {
					Log.info("EOI Details not updating UPDATE_EOI_ER1004 :- ",e);				
					String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not updated on portal, please try again later\",\"error_id\":\"UPDATE_EOI_ER1004\"}";
					return response;
				}
		} else {
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"UPDATE_EOI_ER1005\"}";
			return response;
		}
	}
	
	
	
}