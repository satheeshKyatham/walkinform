package com.godrej.properties.webservices;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.model.PaymentDtl;

import net.sf.jasperreports.engine.JRException;

public class OfferPrePayments {
	/*static Logger logger = Logger.getLogger(OfferPrePayments.class);*/
	private static Logger logger = LogManager.getLogger(OfferPrePayments.class);
	
	static String USERNAME = KeyConstants.SFDC_USERNAME;//pass@12345fp0D4yeOj49FYAowsRSgDm0H
	static String PASSWORD = KeyConstants.SFDC_PASSWORD;
	static String LOGINURL = KeyConstants.SFDC_LOGINURL;
	static String GRANTSERVICE =KeyConstants.SFDC_GRANTSERVICE;
	static String CLIENTID = KeyConstants.SFDC_CLIENTID;
	static String CLIENTSECRET = KeyConstants.SFDC_CLIENTSECRET;
	

	public static String createOfferPrePayments(List<PaymentDtl> ecData1) throws JRException, IOException{
		
		
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
			logger.error("Error :",cpException);
			//Handle protocol exception
		} catch (IOException ioException) {
			logger.error("Error :",ioException);
		}
		//verify response is HTTP OK
		final int statusCode =
		response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			logger.error("Error authenticating to Force.com:"+statusCode);
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
			//Handle JSON exception
		}
		
		
		logger.info(response.getStatusLine());
		logger.info("Successful login");
		logger.info(" instance URL: "+loginInstanceUrl);
		logger.info(" access token/session ID:"+loginAccessToken);
		//https://godrej--test.cs5.my.salesforce.com/services/apexrest/api/createcontact
		String testVal="";
		try {
			testVal = createOfferPrePaymentsJson(loginAccessToken,ecData1);
			
			logger.info("Response of Prepayemnt :::: " + testVal);
			
			//release connection
			httpPost.releaseConnection();
			
			return testVal;
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			logger.error("Error of Servlet: ",e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error of IO : ",e);
		}
		return testVal;
	}

 

	private static String createOfferPrePaymentsJson(String loginAccessToken,List<PaymentDtl> ecData1) throws ServletException, IOException {
		String accountId = null;
		CloseableHttpClient  httpclient = HttpClients.createDefault();
		JSONArray ja = new JSONArray();
		
		
		try {
				LocalDate currentDate = new LocalDate();
				Date date = currentDate.toDateTimeAtStartOfDay().toDate();
				Date dateAdd7 = currentDate.toDateTimeAtStartOfDay().toDate();
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				String strDate = sm.format(date);
				for(int i=0;i<ecData1.size();i++) 
				{
					JSONObject account = new JSONObject();
					account.put("offerid",ecData1.get(i).getOffer_sfid());
					account.put("InstrumentDate",ecData1.get(i).getTransaction_date_string());//sm.format(dateAdd7)
					
					account.put("PrepaymentAmount",ecData1.get(i).getTransaction_amount());
					account.put("BankName",ecData1.get(i).getBank_name());
					if(ecData1.get(i).getBankGL()!=null)
						account.put("BankGL",ecData1.get(i).getBankGL());
					else
						account.put("BankGL","");
					
					if(ecData1.get(i).getPayment_type().equals("NEFT") || ecData1.get(i).getPayment_type().equals("Swipe") || ecData1.get(i).getPayment_type().equals("Wire Transfer"))
					{
						account.put("RTGSNEFT",ecData1.get(i).getTransaction_id());
					}
					else
						account.put("ChequeDemandNumber", ecData1.get(i).getTransaction_id());
					account.put("PaymentMode",ecData1.get(i).getPayment_type());
					account.put("CRNNo","");//12347112
					ja.put(account);
				}
				logger.info("Final Creat Offer Data:-"+ja);
		}
		catch (JSONException e) {
			logger.error("Error 165: ",e);
			throw new ServletException(e);
		}
			HttpPost httpost = new HttpPost(KeyConstants.SFDC_OFFERPREPAYMENTAPI);
	
		httpost.addHeader("Authorization", "OAuth " + loginAccessToken);
		StringEntity messageEntity = new StringEntity(ja.toString(), ContentType.create("application/json"));
		httpost.setEntity(messageEntity);
	
		CloseableHttpResponse  closeableresponse = httpclient.execute(httpost);
		logger.info("Response Status line :" + closeableresponse.getStatusLine());
	
		String getResult = null;
        try {
			getResult = EntityUtils.toString(closeableresponse.getEntity());
			logger.info("getResult of Prepayment API Response:" + getResult);
        } catch (IOException ioException) {
        	logger.error("Error : ",ioException);
        }
	        
        JSONObject jsonObject = null;
        String loginInstanceUrl = null;
        try {
        	logger.info("ID:---**************");
        	logger.info("jsonObjectsssss:---"+jsonObject);
             JSONArray jsonArr = new JSONArray(getResult);
             for (int i = 0; i < jsonArr.length(); i++)
             {
                 JSONObject jsonObj = jsonArr.getJSONObject(i);
                 logger.info("ds"+jsonObj.get("Name"));//Name
             }
        } catch (JSONException jsonException) {
        	logger.error("Error : ",jsonException);
        }
        
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
		
		
		logger.info("getResult of Prepayment API ::: " + getResult);
		
		
		return getResult;
		} 
}