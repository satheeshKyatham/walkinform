package com.godrej.properties.webservices;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.CancelEOIService;

@RestController
public class CancelEOIController {
	private Logger Log = LoggerFactory.getLogger(getClass());
	 
	@Autowired
	private CancelEOIService cancelEOIService;
	 
	@RequestMapping(value = "/deleteEOI", method = RequestMethod.POST)
	public String deleteEOIPreference(
			@RequestParam("preferenceJson") String preferenceJson,
			@RequestParam("paymentJson") String paymentJson,
			@RequestParam("unitsfidOldArray") String unitsfidOldArray,
			@RequestParam("userid") String userid,
			@RequestParam("enq_sfid") String enq_sfid,
			@RequestParam("project_sfid") String project_sfid,
			@RequestParam("username") String username,
			@RequestParam("enqid") String enqid
			) throws ParseException {	
		
		
		if(    !userid.equals("")  
				&& !username.equals("")
				&& !enq_sfid.equals("")  
				&& !project_sfid.equals("")
				&& !enqid.equals("")
				)  {
				
				try {
					String responseVal = cancelEOIService.deleteEOI(preferenceJson, paymentJson, unitsfidOldArray, userid, enq_sfid, project_sfid, username, enqid);
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