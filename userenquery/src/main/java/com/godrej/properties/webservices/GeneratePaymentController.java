package com.godrej.properties.webservices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

import com.godrej.kyc.util.StringEncDec;
import com.godrej.properties.model.EnqJourneyDtl;
import com.godrej.properties.model.GeneratePayment;
import com.godrej.properties.service.EnqJourneyDtlService;
import com.godrej.properties.service.GeneratePaymentService;
import com.godrej.properties.util.SendMailThreadUtil;
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
	
	@Autowired
	private EnqJourneyDtlService enqJourneyDtlService;
	
	@RequestMapping(value = "/encriptStr", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String encriptString(@RequestParam("mobileno") String mobileno) {
		//GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		StringEncDec enc = new StringEncDec();
		String encStr = enc.encrypt(mobileno);
		String str = gson.toJson(encStr);
		return gson.toJson(encStr);
	}
	
	private static String readContentFromFile(String fileName) throws UnsupportedEncodingException {
	    StringBuffer contents = new StringBuffer();
	    try {
	      //use buffering, reading one line at a time
	      BufferedReader reader =  new BufferedReader(new FileReader(fileName));
	      try {
	        String line = null; 
	        while (( line = reader.readLine()) != null){
	          contents.append(line);
	          contents.append(System.getProperty("line.separator"));
	        }
	      }
	      finally {
	          reader.close();
	      }
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
	    return contents.toString();
	}
	
	
	@RequestMapping(value = { "/ccAvenue"}, method = RequestMethod.GET)
	public String ccAvenue(ModelMap model,HttpServletRequest request) {
		 return "ccAvenue";
	}
	
	@RequestMapping(value = { "/ccAvenueLogin"}, method = RequestMethod.GET)
	public String ccAvenueLogin(ModelMap model,HttpServletRequest request) {
		 return "ccAvenueLogin";
	}
	
	@RequestMapping(value = "/insertPaymentRequest", method = RequestMethod.POST)
	public String insertPaymentRequest(@RequestParam("paymentDtlJson") String paymentDtlJson, 
			@RequestParam("userid") String userid,
			@RequestParam("enq_sfid") String enq_sfid,
			@RequestParam("enquiry_name") String enquiry_name,
			@RequestParam("project_sfid") String project_sfid,
			@RequestParam("project_name") String project_name,
			@RequestParam("customer_mobile") String customer_mobile, 
			@RequestParam("user_email") String user_email,
			@RequestParam("user_name") String user_name,
			@RequestParam("customer_name") String customer_name,
			@RequestParam("customer_email") String customer_email,
			
			HttpServletRequest request) throws UnsupportedEncodingException {	
		
		if(    !enq_sfid.equals("")  
			&& !enquiry_name.equals("")  
			&& !project_sfid.equals("")  
			&& !project_name.equals("")  
			&& !customer_mobile.equals("")  
			&& !userid.equals("")  
			&& !user_email.equals("")
			&& !user_name.equals("")
			&& !customer_name.equals("")
			&& !customer_email.equals("")) 
		{
			try {
				GsonBuilder gsonBuilder = new GsonBuilder();
				Gson gson = gsonBuilder.create();
				
				int useridInt = Integer.parseInt(userid);
				//int customerMobileInt = Integer.parseInt(customer_mobile);
				
				String strMobile = encriptString(customer_mobile);
				String strEnq_sfid = encriptString(enq_sfid);
				String resultPath = request.getHeader("Host") + request.getContextPath();
				String paymentRequest= "https://"+resultPath+"/ccAvenueLogin?num="+strMobile.replaceAll("\"", "")+"&projectid="+URLEncoder.encode(project_sfid, "UTF-8")+"&enqsfid="+strEnq_sfid.replaceAll("\"", "")+"&projectname="+URLEncoder.encode(project_name, "UTF-8");
				
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
						
						if (!jobj.get("amount").getAsString().equals("") && jobj.get("amount") != null
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
							ecData1.setEnquiry_sfid(enq_sfid);
							ecData1.setEnquiry_name(enquiry_name);
							ecData1.setProject_sfid(project_sfid);
							ecData1.setProject_name(project_name);
							ecData1.setCreatedby(useridInt);
							ecData1.setUpdatedby(useridInt);
							ecData1.setCustomer_email(customer_email);
							ecData1.setCustomer_mobile(customer_mobile);
							ecData1.setCustomer_name(customer_name);
							ecData1.setIspayment_status("N");
							ecData1.setIsactive("Y");
							ecData1.setCreated_date(currentTimestamp);
							ecData1.setUpdate_date(currentTimestamp);
							ecData1.setRequest_url(paymentRequest);
							charges1.add(ecData1);
						
						} else {
							String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"ER1001\"}";
							return response;
						}
					}
					boolean isInserted = generatePaymentService.insertPaymentDtl(charges1);
					
					if (isInserted) {
						try {
							String status = mailLinkSend(project_sfid,enq_sfid,user_email,userid,user_name,request);
							
							if (status.equals("STATUS_OK")) {
								String response = "{\"status\":\"STATUS_OK\",\"error_msg\":\"Successfully submitted & Link sent to customer\",\"error_id\":null}";
								return response;
							} else {
								String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is successfully submitted & getting error while link mail to customer\",\"error_id\":\"ER1005\"}";
								return response;
							}
						} catch (Exception e) {
							Log.info("Payment Request - Getting error while call method mailLinkSend Error:- ",e);
							String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is successfully submitted & getting error while link mail to customer\",\"error_id\":\"ER1005\"}";
							return response;
						}
					} else {
						String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not submitted on portal\",\"error_id\":\"ER1006\"}";
						return response;
					}
				} else {
					String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Data Not Found\",\"error_id\":\"ER1002\"}";
					return response;
				}
			} catch(Exception e) {
				Log.info("Payment Request is not submitted Error:- ",e);				
				String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not submitted on portal, please try again later\",\"error_id\":\"ER1003\"}";
				return response;
			}
		} else {
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"ER1004\"}";
			return response;
		}
	}	
	
	
	@RequestMapping(value = "/getPaymentReqRecord", method = RequestMethod.POST)
	public String getPaymentReqRecord(@RequestParam("enqSfid") String enqSfid, @RequestParam("projectSFID") String projectSFID) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		try {
			if(!enqSfid.equals("")  && !projectSFID.equals(""))  { 
				return gson.toJson(generatePaymentService.getPaymentRecord(enqSfid, projectSFID));
			} else {
				String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"ER1001\"}";
				return response;
			}
		} catch (Exception e) {
			Log.info("Payment Request - Getting error while fetching data Error:- ",e);
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Getting error while fetching data\",\"error_id\":\"ER1001\"}";
			return response;
		}
	}
	
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String mailLinkSend(
			@RequestParam("projectid") String projectid,
			@RequestParam("enqid") String enqid,
			@RequestParam("emailid") String emailid, 
			@RequestParam("userid") String userid, 
			@RequestParam("user_name") String user_name,
			HttpServletRequest request) throws UnsupportedEncodingException {
		
		String STATUS_NOTOK="STATUS_NOTOK";
		String STATUS_OK = "STATUS_OK";
		
		if(    !projectid.equals("")  
				&& !enqid.equals("")  
				&& !emailid.equals("")  
				&& !userid.equals("")  
				&& !user_name.equals("") )  {
			
			try {
				int useridInt = Integer.parseInt(userid);
				
				List<EnqJourneyDtl> enqList = enqJourneyDtlService.getAllData(projectid,enqid);
				
				if(enqList!=null && enqList.size()>0) {
					for(EnqJourneyDtl eoi: enqList)
					{
						if(eoi.getPhone_number()!=null && eoi.getPhone_number().length()>0)
						{
							String str = encriptString(eoi.getPhone_number());
							String resultPath = request.getHeader("Host") + request.getContextPath();
							String kyclink= "https://"+resultPath+"/ccAvenueLogin?num="+str.replaceAll("\"", "")+"&projectid="+URLEncoder.encode(eoi.getProjectsfid(), "UTF-8")+"&projectname="+URLEncoder.encode(eoi.getProjectname(), "UTF-8");
							//String text = readContentFromFile("D://atul_data//apache-tomcat-9.0.22//QR//payment_request.htm");
							String text = readContentFromFile("D://SW//apache-tomcat-9.0.0.M22//apache-tomcat-9.0.0.M22//QR//payment_request.htm");
							
							text = text.replaceAll("@CustomerName@", eoi.getApplication_name());
							text = text.replaceAll("@ProjectName@", eoi.getProjectname());
							text = text.replaceAll("@ClosingMName@", user_name);
							text = text.replaceAll("@ClosingMEmail@", emailid);
							text = text.replaceAll("@Link@", kyclink);
							
							String emailTempl = "Please find herewith the <a href=\"@Link@\">link</a> to update your KYC details. </br></br>Regards</br>Godrej Properties";
							emailTempl = emailTempl.replaceAll("@Link@", kyclink);
							
							if(eoi.getEmail_id()!=null && eoi.getEmail_id().length()>0) {
								String projectName = "Godrej Properties : Payment Requested for "+ eoi.getProjectname();
								SendMailThreadUtil mail =new SendMailThreadUtil(eoi.getEmail_id(),	"mail@atulbhanushali.com", projectName, text);
							} 
						}
					}
					return STATUS_OK;
				} else {
					return STATUS_NOTOK;
				}
			} catch (Exception e) {
				Log.info("Payment Request - Getting error while link mail to customer Error:- ",e);
				return STATUS_NOTOK;
			}
		} else {
			return STATUS_NOTOK;
		}
	}
	
	
	@PostMapping(value = "/requestToCCgateway")
	public @ResponseBody String requestCCgateway(@RequestParam("id") int id) {
		//call payment table and get the data
		GeneratePayment payment = generatePaymentService.getCCPaymentData(id);
		generatePaymentService.createCCGatewayRequest(payment);
		//and create format for gateway integration
		//generate the request doc number
		//and format store in table
		//and response capture and update in table
//		return gson.toJson(generatePaymentService.getPaymentRecord(enqSfid,projectid));
		return "";
	}
	
}