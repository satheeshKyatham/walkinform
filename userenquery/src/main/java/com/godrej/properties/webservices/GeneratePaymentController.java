package com.godrej.properties.webservices;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.model.GeneratePayment;
import com.godrej.properties.service.GeneratePaymentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class GeneratePaymentController {
	private Logger Log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GeneratePaymentService generatePaymentService;
	
	@RequestMapping(value = { "/ccAvenue"}, method = RequestMethod.GET)
	public String ccAvenue(ModelMap model,HttpServletRequest request) {
		 return "ccAvenue";
	}
	
	@RequestMapping(value = { "/ccAvenueLogin"}, method = RequestMethod.GET)
	public String ccAvenueLogin(ModelMap model,HttpServletRequest request) {
		 return "ccAvenueLogin";
	}
	
	@RequestMapping(value = "/insertPaymentRequest", method = RequestMethod.POST)
	public String insertPaymentRequest(@RequestParam("paymentDtlJson") String paymentDtlJson) {	
		
		
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();
			
			String str=paymentDtlJson;
			  
			Object object=null;
			JsonArray arrayObj=null;
			JsonParser jsonParser=new JsonParser();
			object=jsonParser.parse(str);
			arrayObj=(JsonArray) object;
			
			List<GeneratePayment> charges1=new ArrayList<>();
			
			if(arrayObj!=null && arrayObj.size()>0)
			{
				
				Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
				
				for(int i=0;i<arrayObj.size();i++) {
					JsonObject jobj = new Gson().fromJson(arrayObj.get(i), JsonObject.class);
					
					GeneratePayment ecData1= new GeneratePayment();
					ecData1= gson.fromJson(arrayObj.get(i), GeneratePayment.class);
					
					if (!jobj.get("enquiry_sfid").getAsString().equals("") && jobj.get("enquiry_sfid") != null
						&& !jobj.get("enquiry_name").getAsString().equals("") && jobj.get("enquiry_name") != null
						&& !jobj.get("project_sfid").getAsString().equals("") && jobj.get("project_sfid") != null
						&& !jobj.get("amount").getAsString().equals("") && jobj.get("amount") != null
						&& !jobj.get("createdby").getAsString().equals("") && jobj.get("createdby") != null
						&& !jobj.get("transaction_date_string").getAsString().equals("") && jobj.get("transaction_date_string") != null) 
					{
						//JsonObject jobj = new Gson().fromJson(arrayObj.get(i), JsonObject.class);
						try {
							if (!(jobj.get("transaction_date_string").getAsString()).isEmpty()) {
								Date date  =  df.parse(jobj.get("transaction_date_string").getAsString());
								ecData1.setTransaction_date(date); 
							} else {
								Date date  =  df.parse("1999-09-09");
								ecData1.setTransaction_date(date);
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
						ecData1.setIspayment_status("N");
						ecData1.setIsactive("Y");
						ecData1.setCreated_date(currentTimestamp);
						ecData1.setUpdate_date(currentTimestamp);
						charges1.add(ecData1);
					
					} else {
						String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"ER1001\"}";
						return response;
					}
				}
				generatePaymentService.insertPaymentDtl(charges1);
				
				String response = "{\"status\":\"STATUS_OK\",\"error_msg\":\"Successfully submitted\",\"error_id\":null}";
				return response;
			
			} else {
				String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Data Not Found\",\"error_id\":\"ER1002\"}";
				return response;
			}
		} catch(Exception e) {
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not submitted on portal, please try again later\",\"error_id\":\"ER1003\"}";
			return response;
		}
		 
	}	
	
	
	@RequestMapping(value = "/getPaymentReqRecord", method = RequestMethod.GET)
	public @ResponseBody String getPaymentReqRecord(@RequestParam("enqSfid") String enqSfid, @RequestParam("projectid") String projectid) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		return gson.toJson(generatePaymentService.getPaymentRecord(enqSfid,projectid));
	}
	
	@PostMapping(value = "/requestToCCgateway")
	public @ResponseBody String requestCCgateway(@RequestParam("id") int id) {
		//call payment table and get the data
		generatePaymentService.getCCPayment(id);
		//and create format for gateway integration
		//
//		return gson.toJson(generatePaymentService.getPaymentRecord(enqSfid,projectid));
		return "";
	}
	
}