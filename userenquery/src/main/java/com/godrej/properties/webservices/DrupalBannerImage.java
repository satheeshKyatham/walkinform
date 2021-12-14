package com.godrej.properties.webservices;

import java.io.IOException;

import javax.ws.rs.core.HttpHeaders;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/*import org.apache.log4j.Logger;*/
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
 
@Component
public class DrupalBannerImage {
	
	/*static Logger logger = Logger.getLogger(DrupalBannerImage.class);*/
	private Logger logger = LogManager.getLogger(getClass());
	public String drupalProjectBanner (String projectsfid) {
		try {
			ClientConfig config = new DefaultClientConfig();
	        Client client = Client.create(config);
	        
	        String constUrl= "https://www.godrejproperties.com/gpl-api/banner/fetch/details?project_id="+projectsfid;
	        
	        HttpPost httpost = new HttpPost(constUrl);
	            
	        httpost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
	        httpost.setHeader("X_API_KEY", "aFTAN1ZPxTYaDhINeUXGLXYSfyA");
	        
			CloseableHttpClient  httpclient = HttpClients.createDefault();
			CloseableHttpResponse  closeableresponse = httpclient.execute(httpost);
			String getResult = null;
			
			try {
				getResult = EntityUtils.toString(closeableresponse.getEntity());
				
				logger.info("Get drupal banner image - getResult : "+getResult);
			} catch (IOException ioException) {
				logger.info("Get drupal banner image - getResult ioException : "+ioException);
			}
	          
			return getResult;
	        
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Get drupal banner image - getResult Exception : "+e);
		}
		return null;
	}
}