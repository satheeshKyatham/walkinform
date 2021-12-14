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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class InventoryStatusController {
	
	
	
	/*static Logger logger = Logger.getLogger(InventoryStatusController.class);*/
	private static Logger logger = LogManager.getLogger(InventoryStatusController.class);
	
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
	public String inventoryStatus (String projectSFID, String property15SFID) throws JRException, IOException{
		
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
			logger.info("Inventory Status Check Error authenticating to Force.com:"+statusCode);
			//Error is in EntityUtils.toString(response.getEntity())
			
			//A
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
			logger.error("Inventory Status Check Error :",jsonException);
		}
		
		
		logger.info(response.getStatusLine());
		logger.info("Successful login");
		logger.info(" instance URL: "+loginInstanceUrl);
		logger.info(" access token/session ID:"+loginAccessToken);
		//https://godrej--test.cs5.my.salesforce.com/services/apexrest/api/createcontact
		String testVal="";
		try {
			testVal = checkInventory(loginAccessToken, projectSFID, property15SFID);
			
			logger.info("Inventory Status Check testVal :::: " + testVal);
			
			//release connection
			httpPost.releaseConnection();
			
			//A
			return testVal;
			
		} catch (ServletException e) {
			logger.error("Inventory Status Check Error ",e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Inventory Status Check Error ",e);
			e.printStackTrace();
		}
		//A
		return testVal;
	}

 
//	@RequestMapping(value = { "/createOffer"}, method = RequestMethod.POST)
	private static String checkInventory(String loginAccessToken, String projectSFID, String property15SFID) throws ServletException, IOException {
		String accountId = null;
		CloseableHttpClient  httpclient = HttpClients.createDefault();
		JSONObject account = new JSONObject();
		
		try {
			
			//logger.info("token ZZZ ::: " + token);
			//logger.info("projectsfid ZZZ ::: " + projectsfid);
			//logger.info("enquirysfid ZZZ ::: " + enquirysfid);
			//logger.info("primarycontactsfid ZZZ ::: " + primarycontactsfid);
			
			LocalDate currentDate = new LocalDate();
			Date date = currentDate.toDateTimeAtStartOfDay().toDate();
			Date dateAdd7 = currentDate.toDateTimeAtStartOfDay().toDate();
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = sm.format(date);
			
			account.put("projid",projectSFID);
			account.put("Propid",property15SFID);
			
			
			logger.info("Inventory Status Check Final:-"+account);
		
		}
		catch (JSONException e) {
			logger.error("Inventory Status Check Error ",e);
			e.printStackTrace();
			throw new ServletException(e);
		}
		HttpPost httpost = new HttpPost("https://godrej.my.salesforce.com/services/apexrest/api/Restpropupdate");
	
		httpost.addHeader("Authorization", "OAuth " + loginAccessToken);
		StringEntity messageEntity = new StringEntity(account.toString(), ContentType.create("application/json"));
		httpost.setEntity(messageEntity);
	
		CloseableHttpResponse  closeableresponse = httpclient.execute(httpost);
		logger.info("Inventory Status Check Response Status line :" + closeableresponse.getStatusLine());
	
		String getResult = null;
        try {
			getResult = EntityUtils.toString(closeableresponse.getEntity());
			logger.info("Inventory Status Check getResult :" + getResult);
        } catch (IOException ioException) {
        	logger.info("Inventory Status Check Error "+ioException);
        }
	        
        JSONObject jsonObject = null;
        String loginInstanceUrl = null;
        logger.info("Inventory Status Check ID:---**************");
        logger.info("Inventory Status Check jsonObjectsssss:---"+jsonObject);
		 /*JSONArray jsonArr = new JSONArray(getResult);
		 for (int i = 0; i < jsonArr.length(); i++)
		 {
		     JSONObject jsonObj = jsonArr.getJSONObject(i);
		     System.out.println("ds"+jsonObj.get("Name"));//Name
		     //Update Inventory Status.
		     //createNativeQuery("sql2").executeUpdate();
		     
		 }*/
        
        logger.info(closeableresponse.getStatusLine());
        logger.info("Inventory Status Check Successful login");
        logger.info("Inventory Status Check  instance URL: "+loginInstanceUrl);
        logger.info("Inventory Status Check  access token/session ID: "+loginAccessToken);
		
		try {
			logger.info("Inventory Status Check HTTP status " + closeableresponse.getStatusLine().getStatusCode() + " Agent Status Updated\n\n");
		}
		finally {
			httpclient.close();
		}
		
		
		logger.info("Inventory Status Check getResult ::: " + getResult);
		
		
		return getResult;
		} 
}