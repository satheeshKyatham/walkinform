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
/*import org.apache.log4j.Logger;*/
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
public class ParkingStatusController {
	
	
	private static Logger logger = LogManager.getLogger(ParkingStatusController.class);
	//static Logger logger = Logger.getLogger(ParkingStatusController.class);
	
	@Autowired
	@Qualifier("userContactService")
	private UserContactService userContactService;
	 
	static String USERNAME = KeyConstants.SFDC_USERNAME; 
	static String PASSWORD = KeyConstants.SFDC_PASSWORD;
	static String LOGINURL = KeyConstants.SFDC_LOGINURL;
	static String GRANTSERVICE =KeyConstants.SFDC_GRANTSERVICE;
	static String CLIENTID = KeyConstants.SFDC_CLIENTID;
	static String CLIENTSECRET = KeyConstants.SFDC_CLIENTSECRET;
	
	//public static void main(String[] args) {
	public String parkingStatus (String parkingsfid) throws JRException, IOException{
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		 
		String loginURL = LOGINURL +
				GRANTSERVICE +
		"&client_id=" + CLIENTID +
		"&client_secret=" + CLIENTSECRET +
		"&username=" + USERNAME +
		"&password=" + PASSWORD;
		 
		HttpPost httpPost = new HttpPost(loginURL);
		HttpResponse response = null;
		
		try {
			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException cpException) {
		} catch (IOException ioException) {
		}
		 
		final int statusCode =
		response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			logger.info("Parking Status Check Error authenticating to Force.com:"+statusCode);
			
			return "";
		}
		
		String getResult = null;
		try {
			getResult = EntityUtils.toString(response.getEntity());
		} catch (IOException ioException) {
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
			logger.error("Parking Status Check Error :",jsonException);
		}
		
		
		logger.info(response.getStatusLine());
		logger.info("Successful login");
		logger.info(" instance URL: "+loginInstanceUrl);
		logger.info(" access token/session ID:"+loginAccessToken);
		String testVal="";
		try {
			
			testVal = checkParking(loginAccessToken, parkingsfid);
			//testVal = checkParking(loginAccessToken, projectSFID, property15SFID);
			
			logger.info("Parking Status Check testVal :::: " + testVal);
			
			httpPost.releaseConnection();
			
			return testVal;
			
		} catch (ServletException e) {
			logger.error("Parking Status Check Error ",e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Parking Status Check Error ",e);
			e.printStackTrace();
		}
		
		return testVal;
	}

 

	private static String checkParking(String loginAccessToken, String parkingsfid) throws ServletException, IOException {
		String accountId = null;
		CloseableHttpClient  httpclient = HttpClients.createDefault();
		//JSONArray account = new JSONArray();
		
		String account = "";
		
		try {
			LocalDate currentDate = new LocalDate();
			Date date = currentDate.toDateTimeAtStartOfDay().toDate();
			Date dateAdd7 = currentDate.toDateTimeAtStartOfDay().toDate();
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = sm.format(date);
			
			//account.put("projid",projectSFID);
			//account.put("carSFId" + ":" + "a182s000000CiJlAAK");
			
			
			account = "[{" + 
	        		" \"carSFId\": \""+parkingsfid+"\"" + 
                "}]";
			
			
			logger.info("Parking Status Check Final:-"+account);
		
		}
		/*catch (JSONException e) {
			logger.error("Inventory Status Check Error ",e);
			e.printStackTrace();
			throw new ServletException(e);
		}*/
		catch (Exception e) {
			logger.error("Parking Status Check Error ",e);
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		//HttpPost httpost = new HttpPost("https://godrej.my.salesforce.com/services/apexrest/api/Restpropupdate");
		HttpPost httpost = new HttpPost("https://godrej.my.salesforce.com/services/apexrest/api/CheckParkingStatus/");
		
		httpost.addHeader("Authorization", "OAuth " + loginAccessToken);
		//StringEntity messageEntity = new StringEntity(account.toString(), ContentType.create("application/json"));
		StringEntity messageEntity = new StringEntity(account, ContentType.create("application/json"));
		httpost.setEntity(messageEntity);
	
		CloseableHttpResponse  closeableresponse = httpclient.execute(httpost);
		logger.info("Parking Status Check Response Status line :" + closeableresponse.getStatusLine());
	
		String getResult = null;
        try {
			getResult = EntityUtils.toString(closeableresponse.getEntity());
			logger.info("Parking Status Check getResult :" + getResult);
        } catch (IOException ioException) {
        	logger.info("Parking Status Check Error "+ioException);
        }
	        
        JSONObject jsonObject = null;
        String loginInstanceUrl = null;
        logger.info("Parking Status Check ID:---**************");
        logger.info("Parking Status Check jsonObjectsssss:---"+jsonObject);
        
        logger.info(closeableresponse.getStatusLine());
        logger.info("Parking Status Check Successful login");
        logger.info("Parking Status Check  instance URL: "+loginInstanceUrl);
        logger.info("Parking Status Check  access token/session ID: "+loginAccessToken);
		
		try {
			logger.info("Parking Status Check HTTP status " + closeableresponse.getStatusLine().getStatusCode() + " Agent Status Updated\n\n");
		}
		finally {
			httpclient.close();
		}
		
		logger.info("Parking Status Check getResult ::: " + getResult);
		
		
		return getResult;
		} 
}