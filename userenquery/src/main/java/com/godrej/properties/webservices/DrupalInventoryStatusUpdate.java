package com.godrej.properties.webservices;

import java.io.IOException;

import javax.ws.rs.core.HttpHeaders;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/*import org.apache.log4j.Logger;*/
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godrej.properties.dto.SysConfigEnum;
import com.godrej.properties.master.service.SysConfigService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
 
@Component
public class DrupalInventoryStatusUpdate {
	
	/*static Logger logger = Logger.getLogger(DrupalInventoryStatusUpdate.class);*/
	private Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private SysConfigService sysConfigService;
	
	public String inventoryStatusUpdate (String unitsfid, String holdStatus) {
		//public static void main(String[] args) {	
		try {
			ClientConfig config = new DefaultClientConfig();
	        Client client = Client.create(config);
	        
	        String drupalAPIEndpoint = sysConfigService.getValue(SysConfigEnum.DRUPAL_API_ENDPOINT, null);
	        
	        String constUrl= drupalAPIEndpoint+"gpl-api/update_inventory_hold_status.json";
	        
	        String jsonBody = "{" + 
		        		" \"external_inventory_id\": \""+unitsfid+"\"," + 
		                "\"d4u_hold_status\": \""+holdStatus+"\"  " + 
	                "}";
	        //System.out.println("jsonBody ::: " + jsonBody); 
	        
	        StringEntity postingString = new StringEntity(jsonBody);
	           
	        HttpPost httpost = new HttpPost(constUrl);
	            
	        httpost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
	        httpost.setEntity(postingString);
			//System.out.println(postingString.toString());
			CloseableHttpClient  httpclient = HttpClients.createDefault();
			CloseableHttpResponse  closeableresponse = httpclient.execute(httpost);
			String getResult = null;
			
			try {
				getResult = EntityUtils.toString(closeableresponse.getEntity());
				logger.info("Update Drupal inventory : "+jsonBody);
				logger.info("Update Drupal inventory - getResult : "+getResult);
				//System.out.println("Pushtopic contact getResult :" + getResult);
			} catch (IOException ioException) {
				logger.info("Update Drupal inventory - jsonBody : "+jsonBody+" ioException - "+ioException+"");
			}
	          
			return getResult;
	        
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Update Drupal inventory - unitsfid : "+unitsfid+" AND holdStatus - "+holdStatus+" Exception - "+e+"");
		}
		return "";
	}
}