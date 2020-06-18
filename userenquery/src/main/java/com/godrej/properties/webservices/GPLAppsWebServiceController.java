package com.godrej.properties.webservices;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.dto.GPLAppBookingAPIDto;
import com.godrej.properties.serviceimpl.GPLAppsWebServiceImpl;

@RestController
public class GPLAppsWebServiceController {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GPLAppsWebServiceImpl gplAppsWebServiceImpl;
	
	
	@PostMapping(value = "/d4upreofferAPI", produces = "application/json")
	public String d4upreofferAPI(@RequestBody GPLAppBookingAPIDto bookingDto)
	{
        // Get the all field objects of User class 
        Field[] fields = GPLAppBookingAPIDto.class.getFields(); 
  
        for (int i = 0; i < fields.length; i++) { 
            // get value of the fields 
            Object value;
			try {
				value = fields[i].get(bookingDto);
				 // print result 
				log.info("Input Value of Field {} Value is {}",fields[i].getName(), value);
			} catch (IllegalArgumentException e) {
				log.error("Error IllegalArgumentException :-{}",e);
			} catch (IllegalAccessException e) {
				log.error("Error IllegalAccessException :-{}",e);
			} 
        }
		String returnMesg = "";
		//Call Enquiry SFID to get enquiry PK ID
		GPLAppBookingAPIDto preAPI = null;
		try {
			preAPI = gplAppsWebServiceImpl.insertGPLBookingData(bookingDto);//String timeStamp = new SimpleDateFormat("ddMMyyyy").format(new Date());
			String timeStamp = new SimpleDateFormat("ddMMyyyy").format(new Date());
			if(preAPI.getResp_mesg()!=null && preAPI.getResp_mesg().trim().length()>0)
			{
			//returnMesg="{\"status\":\"200 OK\",\"msg\":\"Success\",\"id\":\""+preAPI.getNv_token_type()+Calendar.getInstance().getTime()+"\"}";
				if(preAPI.getResp_mesg().contains("null"))
				{
					preAPI.setResp_mesg(preAPI.getResp_mesg().replace("null", ""));
				}
					
				returnMesg="{\"status\":\"STATUS_NOTOK\",\"inventory_id\":\""+preAPI.getPropertysfid()+"\",\"contact_id\":\""+preAPI.getContactSfid()+"\",\"offer_id\":\"\",\"sales_email_id\":\"\","
						+ "\"applicant_ids\":[],\"errors\":[{\"error_code\":\"\",\"error_msg\":\""+preAPI.getResp_mesg()+"\"}]}";
			}
			else
			{
				returnMesg="{\"status\":\"STATUS_OK\",\"inventory_id\":\""+preAPI.getPropertysfid()+"\",\"contact_id\":\""+preAPI.getContactSfid()+"\",\"offer_id\":\""+preAPI.getNv_token_type()+timeStamp+"\",\"sales_email_id\":\""+preAPI.getSiteheadEmail()+"\","
						+ "\"applicant_ids\":{\"1st Applicant\":\""+preAPI.getContactSfid()+"\"},\"errors\":\"null\"}";
			}
			log.info("Success Response Mesg {}",returnMesg);
			log.info("Response Mesg {}",returnMesg);
		}
		catch (Exception e) {
			returnMesg="{\"status\":\"STATUS_NOTOK\",\"inventory_id\":\""+preAPI.getPropertysfid()+"\",\"contact_id\":\""+preAPI.getContactSfid()+"\",\"offer_id\":\"\",\"sales_email_id\":\"\","
					+ "\"applicant_ids\":[],\"errors\":[{\"error_code\":\"\",\"error_msg\":\""+preAPI.getResp_mesg()+"\"}]}";
			log.info("Error Response Mesg {}",returnMesg);
			log.error("GPLAppsWebServiceController {}",e);
		}
		return returnMesg;
		//Insert NV_Token table with site head id
		//Site HEad ID pick from Nv_project table
		//Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		//return gson.toJson(auditLogService.insertAuditLog(auditLogDto));	
	}
}
