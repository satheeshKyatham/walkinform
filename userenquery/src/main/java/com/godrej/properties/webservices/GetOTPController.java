package com.godrej.properties.webservices;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.GetOTPService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class GetOTPController {
	private Logger Log = LoggerFactory.getLogger(getClass());
	 
	@Autowired
	private GetOTPService getOTPService;
	
	//@RequestMapping(value = "/getAdminOTP",method = RequestMethod.GET, produces = "application/json")
	@RequestMapping(value = "/getAdminOTP", method = RequestMethod.POST, produces = "application/json")
	public String getAdminOTP(
			@RequestParam("projectsfid") String projectsfid,
			@RequestParam("loggedinuserid") String loggedinuserid,
			@RequestParam("otprequesterid") String otprequesterid,
			@RequestParam("mobileno") String mobileno
			) throws ParseException {	
		
		
		if(    !projectsfid.equals("")  
				&& !loggedinuserid.equals("")
				&& !otprequesterid.equals("")
				&& !mobileno.equals(""))  {
				
				try {
					String responseVal = getOTPService.adminOTP(projectsfid, loggedinuserid, otprequesterid, mobileno);
					return responseVal;
				}  catch(Exception e) {
					Log.info("Error inserting data into table :- ",e);				
					String response = "{\"otp\":null,\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Error inserting data into table\",\"error_id\":\"ADMINOTP_ER1001\"}";
					return response;
				}
		} else {
			String response = "{\"otp\":null,\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"ADMINOTP_ER1002\"}";
			return response;
		}
	}
}