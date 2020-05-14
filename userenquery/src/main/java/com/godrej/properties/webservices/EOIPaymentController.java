package com.godrej.properties.webservices;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.godrej.properties.service.EOIPaymentDtlService;
import com.godrej.properties.service.EOIPreferenceDtlService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class EOIPaymentController {
	private Logger Log = LoggerFactory.getLogger(getClass());
	 
	@Autowired
	private EOIPaymentDtlService eOIPaymentDtlService;
	
	//--------------
	
	@RequestMapping(value = { "/updateEOIPayment" }, headers = "content-type=multipart/*", method = RequestMethod.POST) 
	//@RequestMapping(value = "/updateEOIPayment", method = RequestMethod.POST)
	public @ResponseBody String updateEOIPreference(
			@RequestParam(value = "receiptAttachEoi" , required=false) MultipartFile receiptAttach,
			@RequestParam("rowid") String rowid,
			
			@RequestParam("paymentDtlJson") String paymentDtlJson,
			@RequestParam("userid") String userid,
			@RequestParam("username") String username,
			@RequestParam("enq_sfid") String enq_sfid,
			@RequestParam("project_sfid") String project_sfid
			) throws ParseException {	
		
		
			if(!userid.equals("")  
				&& !username.equals("")
				&& !enq_sfid.equals("")  
				&& !project_sfid.equals("")
				&& receiptAttach!=null)  {
				
				try {
					
					String rootPath = System.getProperty("catalina.home");
					
					String responseVal = eOIPaymentDtlService.updateEOIPayment(paymentDtlJson, userid, enq_sfid, project_sfid, username);
					
					if(responseVal.equals("STATUS_OK")) {
						if(receiptAttach!=null) {
							File ad_dir = new File(rootPath + File.separator + "EOIbookingReference" + File.separator + enq_sfid + File.separator + rowid);
							String ad_path =ad_dir +File.separator+rowid+"Receipt"+"_"+receiptAttach.getOriginalFilename();
							if (!ad_dir.exists()) {
								ad_dir.mkdirs();	
							}
							byte[] abytes;
							abytes = receiptAttach.getBytes();
							File aserverFile = new File(ad_path);
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(aserverFile));
							stream.write(abytes);
							stream.close();
						}
					}
					return responseVal;
				}  catch(Exception e) {
					Log.info("EOI Details not updating UPDATE_EOI_ER1004 :- ",e);				
					String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not updated on portal, please try again later\",\"error_id\":\"UPDATE_EOI_PAYMENT_ER1004\"}";
					return response;
				}
			
		} else {
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"UPDATE_EOI_PAYMENT_ER1005\"}";
			return response;
		}
	}
	
	
	@RequestMapping(value = "/deleteEOIPayment", method = RequestMethod.POST)
	public String deleteEOIPayment(
			@RequestParam("paymentJson") String paymentJson,
			@RequestParam("userid") String userid,
			@RequestParam("enq_sfid") String enq_sfid,
			@RequestParam("project_sfid") String project_sfid
			) throws ParseException {	
		
		
		if(    !userid.equals("")  
				&& !enq_sfid.equals("")  
				&& !project_sfid.equals(""))  {
				
				try {
					String responseVal = eOIPaymentDtlService.deleteEOIPayment(paymentJson, userid, enq_sfid, project_sfid);
					return responseVal;
				}  catch(Exception e) {
					Log.info("EOI Details not updating UPDATE_EOI_ER1004 :- ",e);				
					String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not updated on portal, please try again later\",\"error_id\":\"DELETE_EOI_ER1004\"}";
					return response;
				}
		} else {
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"DELETE_EOI_ER1005\"}";
			return response;
		}
	}
	
}