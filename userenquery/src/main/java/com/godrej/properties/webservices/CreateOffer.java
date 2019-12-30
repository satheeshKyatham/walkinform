package com.godrej.properties.webservices;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.service.UserContactService;

import net.sf.jasperreports.engine.JRException;
@Component
public class CreateOffer {

	static Logger logger = Logger.getLogger(CreateOffer.class);
	
	@Autowired
	@Qualifier("userContactService")
	private UserContactService userContactService;
	//Sandbox Credentials
	/*static final String USERNAME = "sachin_more@magicsoftware.com";//pass@12345fp0D4yeOj49FYAowsRSgDm0H
	static final String PASSWORD = "Godrej@2018cVsYTP80dGI8lHM1kqzahnj6W";
	static final String LOGINURL = "https://test.salesforce.com";
	static final String GRANTSERVICE ="/services/oauth2/token?grant_type=password";
	static final String CLIENTID = "3MVG9Nvmjd9lcjRnoT4GG3E8o7ZbQcp3HqKaX6KsWkBg77OzU6SN.6oqr00W1pLR_P50oeF8xzGIk7RWT9TTA";
	static final String CLIENTSECRET = "8491910721028248323";*/

	//Production Credentials	
	static String USERNAME = KeyConstants.SFDC_USERNAME;//pass@12345fp0D4yeOj49FYAowsRSgDm0H
	static String PASSWORD = KeyConstants.SFDC_PASSWORD;
	static String LOGINURL = KeyConstants.SFDC_LOGINURL;
	static String GRANTSERVICE =KeyConstants.SFDC_GRANTSERVICE;
	static String CLIENTID = KeyConstants.SFDC_CLIENTID;
	static String CLIENTSECRET = KeyConstants.SFDC_CLIENTSECRET;
	
	//public static void main(String[] args) {
	public String PropOffer (String bspDis, String token, String projectsfid, String enquirysfid, String primarycontactsfid,String propid,String ppid,String offerthrough,String brokersfid,double discount_Value,String enquiry_name,String prepaymentamt,String bankname,String trxdate,String trxno,String paymentmode, String tdsPaidBy,boolean isothers,String bankGL) throws JRException, IOException{
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		// Assemble the login request URL
		String loginURL = LOGINURL +
				GRANTSERVICE +
		"&client_id=" + CLIENTID +
		"&client_secret=" + CLIENTSECRET +
		"&username=" + USERNAME +
		"&password=" + PASSWORD;
		//Login requests must be POSTs
		HttpPost httpPost = new HttpPost(loginURL);
		HttpResponse response = null;
		
		try {
			//Execute the login POST request
			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException cpException) {
			//Handle protocol exception
		} catch (IOException ioException) {
			//Handle system IO exception
		}
		//verify response is HTTP OK
		final int statusCode =
		response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			logger.info("Error authenticating to Force.com:"+statusCode);
			//Error is in EntityUtils.toString(response.getEntity())
			return "";
		}
		
		String getResult = null;
		try {
			getResult = EntityUtils.toString(response.getEntity());
		} catch (IOException ioException) {
			//Handle system IO exception
		}
		
		JSONObject jsonObject = null;
		
		String loginAccessToken = null;
		String loginInstanceUrl = null;
		
		try {
			jsonObject = (JSONObject) new
			JSONTokener(getResult).nextValue();
			loginAccessToken = jsonObject.getString("access_token");
			loginInstanceUrl = jsonObject.getString("instance_url");
		} catch (JSONException jsonException) {
			logger.error("Error :",jsonException);
		}
		
		
		logger.info(response.getStatusLine());
		logger.info("Successful login");
		logger.info(" instance URL: "+loginInstanceUrl);
		logger.info(" access token/session ID:"+loginAccessToken);
		//https://godrej--test.cs5.my.salesforce.com/services/apexrest/api/createcontact
		String testVal="";
		try {
			testVal = createAccount(loginAccessToken,bspDis,token,projectsfid,enquirysfid,primarycontactsfid,propid,ppid,offerthrough,brokersfid,discount_Value,enquiry_name,prepaymentamt,bankname,trxdate,trxno,paymentmode,tdsPaidBy,isothers,bankGL);
			
			logger.info("testVal :::: " + testVal);
			
			//release connection
			httpPost.releaseConnection();
			
			return testVal;
			
		} catch (ServletException e) {
			logger.error("Error ",e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Error ",e);
			e.printStackTrace();
		}
		return testVal;
	}

 
//	@RequestMapping(value = { "/createOffer"}, method = RequestMethod.POST)
	private String createAccount(String loginAccessToken, String bspDis, String token, String projectsfid, String enquirysfid, String primarycontactsfid,String propid,String ppid,String offerthrough,String brokersfid,double discount_Value,String enquiry_name,String prepaymentamt,String bankname,String trxdate,String trxno,String paymentmode, String tdsPaidBy,boolean isothers,String bankGL) throws ServletException, IOException {
		String accountId = null;
		CloseableHttpClient  httpclient = HttpClients.createDefault();
		JSONObject account = new JSONObject();
		
		try {
			
			logger.info("token ZZZ ::: " + token);
			logger.info("projectsfid ZZZ ::: " + projectsfid);
			logger.info("enquirysfid ZZZ ::: " + enquirysfid);
			logger.info("primarycontactsfid ZZZ ::: " + primarycontactsfid);
			
			LocalDate currentDate = new LocalDate();
			Date date = currentDate.toDateTimeAtStartOfDay().toDate();
			Date dateAdd7 = currentDate.toDateTimeAtStartOfDay().toDate();
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = sm.format(date);
			
			account.put("enquiryid",enquirysfid);
			account.put("offerValidTillDate",sm.format(dateAdd7));
			account.put("ProjId", projectsfid);
			account.put("Propid",propid);
			account.put("PPid",ppid);
			account.put("primaryContactId",primarycontactsfid);
			//Call the all KYC Approval Data ID's
			//getKycApprovalData
			/*EOIEnquiryServiceImpl serive = new EOIEnquiryServiceImpl();
			List<CoApplicant> coappList = serive.getCoapplicantData(enquiry_name);
			if(coappList.size()>1)*/
			//account.put("SecondApplicant","");
			account.put("ThirdApplicant","");
			account.put("FourthApplicant","");
			account.put("FifthApplicant","");
			account.put("Staus","Closed Won");
			account.put("offerSource","Walk-in");
			account.put("offerthrough",offerthrough);
			account.put("firstApplicantSharingRatio","100.00");
			account.put("secondApplicantSharingRatio","");
			account.put("InstrumentDate",trxdate);//trx date
			//Trx Number
			account.put("PrepaymentAmount",prepaymentamt);
			account.put("thirdApplicantSharingRatio","");
			account.put("fourthApplicantSharingRatio","");
			account.put("fifthApplicantSharingRatio","");
			//account.put("SecondApplicant","003O000001DWTm4");
//			account.put("ThirdApplicant","");
//			account.put("FourthApplicant","");
//			account.put("FifthApplicant","");
			account.put("BrokerAccount",brokersfid);
			/* Query for Broker Account */
			/* BrokerContact pick through  BrokerAccount */
			if(brokersfid!=null && brokersfid.length()>0)
			{
				account.put("BrokerContact",userContactService.getBrokerContact(brokersfid));
			}
			else
				account.put("BrokerContact","");
			
			account.put("DiscountAmount",discount_Value);
			account.put("DiscountType","Amount");
			account.put("Discountpercent","");
			//System.out.println("Date"+strDate);
			account.put("BookingDate",strDate);
			account.put("BankName",bankname);//Bank Name
			if(bankGL!=null && bankGL.length()>0)
				account.put("BankGL",bankGL);
			else
				account.put("BankGL","");
			
			if(paymentmode.equals("NEFT") || paymentmode.equals("Swipe") || paymentmode.equals("Wire Transfer"))
			{
				account.put("RTGS_NEFT",trxno);
			}
			else
				account.put("Cheque_Demand_Number",trxno);
				
			account.put("PaymentMode",paymentmode);
			
			/*Commented on 12-11-2019 - Satheesh Kyatham - In presense of Prakash and Vineet Sir - 
			 * Due to error at the time of converting offer to booking */
			/* ----------- Start -------------*/
			/*if(isothers)
				account.put("Discountperunitarea","");
			else
				account.put("Discountperunitarea",bspDis);*/
			/* ----------- End -------------*/
			
			account.put("Tdspaidby",tdsPaidBy);
			
			logger.info("Final Creat Offer Data:-"+account);
		
		}
		catch (JSONException e) {
			logger.error("Error ",e);
			e.printStackTrace();
			throw new ServletException(e);
		}
//		HttpPost httpost = new HttpPost("https://godrej.my.salesforce.com/services/apexrest/api/CreateOfferPrepayment");
		HttpPost httpost = new HttpPost(KeyConstants.SFDC_OFFERAPI);
//		HttpPost httpost = new HttpPost(KeyConstants.SFDC_OFFERAPI_BULKY);
		
		httpost.addHeader("Authorization", "OAuth " + loginAccessToken);
		StringEntity messageEntity = new StringEntity("{\"ei\":"+account.toString()+"}", ContentType.create("application/json"));
		httpost.setEntity(messageEntity);
	
		CloseableHttpResponse  closeableresponse = httpclient.execute(httpost);
		logger.info("Response Status line :" + closeableresponse.getStatusLine());
	
		String getResult = null;
        try {
			getResult = EntityUtils.toString(closeableresponse.getEntity());
			logger.info("getResult :" + getResult);
        } catch (IOException ioException) {
        	logger.info("Error "+ioException);
        }
	        
        JSONObject jsonObject = null;
        String loginInstanceUrl = null;
        logger.info("ID:---**************");
        logger.info("jsonObjectsssss:---"+jsonObject);
		 /*JSONArray jsonArr = new JSONArray(getResult);
		 for (int i = 0; i < jsonArr.length(); i++)
		 {
		     JSONObject jsonObj = jsonArr.getJSONObject(i);
		     System.out.println("ds"+jsonObj.get("Name"));//Name
		     //Update Inventory Status.
		     //createNativeQuery("sql2").executeUpdate();
		     
		 }*/
        
        logger.info(closeableresponse.getStatusLine());
        logger.info("Successful login");
        logger.info("  instance URL: "+loginInstanceUrl);
        logger.info("  access token/session ID: "+loginAccessToken);
		
		try {
			logger.info("HTTP status " + closeableresponse.getStatusLine().getStatusCode() + " Agent Status Updated\n\n");
		}
		finally {
			httpclient.close();
		}
		
		
		logger.info("getResult ::: " + getResult);
		
		
		return getResult;
		} 
}