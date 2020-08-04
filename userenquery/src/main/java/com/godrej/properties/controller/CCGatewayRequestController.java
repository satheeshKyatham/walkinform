package com.godrej.properties.controller;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ccavenue.security.AesCryptUtil;
import com.godrej.properties.model.ProjectLaunch;

@Component
public class CCGatewayRequestController {

	static Logger logger = Logger.getLogger(CCGatewayRequestController.class);
	public String CCGatewayRequestPost(String ccaRequest,ProjectLaunch project) throws ClientProtocolException, IOException
	{
		//String accessCode= "AVHJ91HC26CC78JHCC";		
		//String workingKey = "AC52E9A706E2D7938203D4D554B61E2E";
		AesCryptUtil aesUtil=new AesCryptUtil(project.getCcavenue_workingkey_config());
		//System.out.println(aesUtil);
		String encRequest = aesUtil.encrypt(ccaRequest);
		/*CloseableHttpClient  httpclient = HttpClients.createDefault();
		
		HttpPost httpost = new HttpPost(KeyConstants.CCAVENUE_GATEWAT_URL);
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	    postParameters.add(new BasicNameValuePair("encRequest", encRequest));
	    postParameters.add(new BasicNameValuePair("access_code", accessCode));

	    httpost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));

	    
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
        logger.info(closeableresponse.getStatusLine());
		try {
			logger.info("HTTP status " + closeableresponse.getStatusLine().getStatusCode() + " Agent Status Updated\n\n");
		}
		finally {
			httpclient.close();
		}
		logger.info("getResult ::: " + getResult);
		return getResult;*/
		
		return encRequest;
		
	}
}
