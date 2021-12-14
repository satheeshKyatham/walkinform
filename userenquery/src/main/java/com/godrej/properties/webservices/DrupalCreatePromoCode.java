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

import com.godrej.properties.master.service.SysConfigService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
 
@Component
public class DrupalCreatePromoCode {
	
	/*static Logger logger = Logger.getLogger(DrupalCreatePromoCode.class);*/
	static final Logger logger = LogManager.getLogger(DrupalCreatePromoCode.class.getName());
	@Autowired
	private SysConfigService sysConfigService;
	
	public String createUpdatePromoCode (String project_sfdc_id, String promo_code, String promo_code_discount_type, String flat_discount_amount, String discount_percentage, String expiry_date, String coupon_discount_upto, String promocode_max_use_count, String typology, String status) {
	//public static void main(String[] args) {	
		try {
			ClientConfig config = new DefaultClientConfig();
	        Client client = Client.create(config);
	        
	        //String drupalAPIEndpoint = sysConfigService.getValue(SysConfigEnum.DRUPAL_API_ENDPOINT, null);
	        
	        //String constUrl= drupalAPIEndpoint+"gpl-sfdc-api/update_promo_code ";
	        
	        String constUrl= "http://43.242.212.209/gpl-project/gpl-sfdc-api/update_promo_code/";
	        
	        String jsonBody = "{"+ 
		        		" \"project_sfdc_id\": \""+project_sfdc_id+"\"," + 
		        		" \"promo_code\": \""+promo_code+"\"," + 
		        		" \"promo_code_discount_type\": \""+promo_code_discount_type+"\"," + 
		        		" \"flat_discount_amount\": \""+flat_discount_amount+"\"," + 
		        		" \"discount_percentage\": \""+discount_percentage+"\"," + 
		        		" \"expiry_date\": \""+expiry_date+"\"," + 
		        		" \"coupon_discount_upto\": \""+coupon_discount_upto+"\"," + 
		        		" \"promocode_max_use_count\": \""+promocode_max_use_count+"\"," + 
		        		" \"typology\": \""+typology+"\"," + 
		        		" \"status\":" + status +
	                "}";
	        System.out.println("jsonBody ::: " + jsonBody); 
	        
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
				logger.info("Create/Update Drupal Promo Code - getBody : "+jsonBody);
				logger.info("Create/Update Drupal Promo Code - getResult : "+getResult);
				//System.out.println("Pushtopic contact getResult :" + getResult);
			} catch (IOException ioException) {
				logger.info("Create/Update Drupal Promo Code - jsonBody : "+jsonBody+" ioException - "+ioException+"");
			}
	          
			return getResult;
	        
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Create/Update Drupal Promo Code - projectsfid : "+project_sfdc_id+" - "+e+"");
		}
		return "";
	}
}