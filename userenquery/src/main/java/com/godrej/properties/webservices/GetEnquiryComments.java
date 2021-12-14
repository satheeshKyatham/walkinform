package com.godrej.properties.webservices;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;

import com.godrej.properties.constants.KeyConstants;

import net.sf.jasperreports.engine.JRException;

@Component
public class GetEnquiryComments {
	
	
	/*static Logger logger = Logger.getLogger(GetEnquiryComments.class);*/
	
	private static Logger logger = LogManager.getLogger(GetEnquiryComments.class);
	//Production Credentials	
	static String USERNAME = KeyConstants.SFDC_USERNAME;//pass@12345fp0D4yeOj49FYAowsRSgDm0H
	static String PASSWORD = KeyConstants.SFDC_PASSWORD;
	static String LOGINURL = KeyConstants.SFDC_LOGINURL;
	static String GRANTSERVICE =KeyConstants.SFDC_GRANTSERVICE;
	static String CLIENTID = KeyConstants.SFDC_CLIENTID;
	static String CLIENTSECRET = KeyConstants.SFDC_CLIENTSECRET;
	
	//public static void main(String[] args) {
	public String enquiryData (String enqSFID) throws JRException, IOException{
		
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
		String enqDtl="";
		try {
			
			//String projectSFID = "a1u6F00000692Mz";
			
			enqDtl = getEnqDtl(loginAccessToken, enqSFID);
			
			logger.info("Inventory Status Check enqDtl :::: " + enqDtl);
			
			//release connection
			httpPost.releaseConnection();
			
			//A
			return enqDtl;
			
		} catch (ServletException e) {
			logger.error("Inventory Status Check Error ",e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Inventory Status Check Error ",e);
			e.printStackTrace();
		}
		
		
		//A
		return enqDtl;
	}

 
//	@RequestMapping(value = { "/createOffer"}, method = RequestMethod.POST)
	private static String getEnqDtl(String loginAccessToken, String enqSFID) throws ServletException, IOException {
		CloseableHttpClient  httpclient = HttpClients.createDefault();
		JSONObject account = new JSONObject();
		
		
		logger.info("Inventory Status Check Final:-"+account);
		HttpGet httget = new HttpGet("https://godrej.my.salesforce.com/services/apexrest/RestEnquiryShow/"+enqSFID);
		
		httget.addHeader("Authorization", "OAuth " + loginAccessToken);
		
		CloseableHttpResponse  closeableresponse = httpclient.execute(httget);
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