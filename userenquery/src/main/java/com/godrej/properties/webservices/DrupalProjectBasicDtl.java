package com.godrej.properties.webservices;

import java.io.IOException;

import javax.ws.rs.core.HttpHeaders;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godrej.properties.dto.SysConfigEnum;
import com.godrej.properties.master.service.SysConfigService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
 
@Component
public class DrupalProjectBasicDtl {
	
	@Autowired
	private SysConfigService sysConfigService;
	
	static Logger logger = Logger.getLogger(DrupalProjectBasicDtl.class);
	 
	public String drupalProjectDtl (String projectsfid) {
		try {
			ClientConfig config = new DefaultClientConfig();
	        Client client = Client.create(config);
	        
	        String drupalAPIEndpoint = sysConfigService.getValue(SysConfigEnum.DRUPAL_API_ENDPOINT, null);
	        String constUrl= drupalAPIEndpoint+"get_project_details_info.json";
	        
	        String jsonBody = "{" + 
		        		" \"device_id\": \"d4u-AQWs43fdsg12KSKFG\"," + 
		        		" \"user_id\": \"\"," +
		        		" \"project_id\": \"\"," +
		                "\"project_sfid\": \""+projectsfid+"\"  " + 
	                "}"; 
	        
	       StringEntity postingString = new StringEntity(jsonBody);
	           
	        HttpPost httpost = new HttpPost(constUrl);
	            
	        httpost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
	        
	        httpost.setEntity(postingString);
			CloseableHttpClient  httpclient = HttpClients.createDefault();
			CloseableHttpResponse  closeableresponse = httpclient.execute(httpost);
			String getResult = null;
			
			try {
				getResult = EntityUtils.toString(closeableresponse.getEntity());
				 
				logger.info("Get drupal basic details of project - getResult : "+getResult);
			} catch (IOException ioException) {
				logger.info("Get drupal basic details of project : "+ioException);
			}
	          
			return getResult;
	        
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Get drupal basic details of project : "+e);
		}
		return null;
	}
}