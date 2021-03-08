package com.godrej.properties.webservices;

import java.io.IOException;

import javax.ws.rs.core.HttpHeaders;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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
public class DrupalFetchPromoCode {
	
	static Logger logger = Logger.getLogger(DrupalFetchPromoCode.class);
	 
	@Autowired
	private SysConfigService sysConfigService;
	
	public String fetchPromoCode (String project_sfdc_id) {
	//public static void main(String[] args) {	
		try {
			ClientConfig config = new DefaultClientConfig();
	        Client client = Client.create(config);
	        
	        //String drupalAPIEndpoint = sysConfigService.getValue(SysConfigEnum.DRUPAL_API_ENDPOINT, null);
	        
	        //String constUrl= drupalAPIEndpoint+"gpl-sfdc-api/update_promo_code ";
	        
	        String constUrl= "http://43.242.212.209/gpl-project/gpl-api/promo_list.json?project_sfdc_id="+project_sfdc_id;
	        
	        HttpGet httget = new HttpGet(constUrl);
	            
	        httget.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
			CloseableHttpClient  httpclient = HttpClients.createDefault();
			CloseableHttpResponse  closeableresponse = httpclient.execute(httget);
			String getResult = null;
			
			try {
				getResult = EntityUtils.toString(closeableresponse.getEntity());
				logger.info("Fetch Drupal Promo Code - getResult : "+getResult);
			} catch (IOException ioException) {
				logger.info("Fetch Drupal Promo Code - ioException : "+ioException);
			}
	          
			return getResult;
	        
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Fetch Drupal Promo Code - projectsfid : "+project_sfdc_id+" - "+e+"");
		}
		return "";
	}
}