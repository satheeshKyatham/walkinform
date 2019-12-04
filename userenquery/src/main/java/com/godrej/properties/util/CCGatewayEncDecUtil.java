package com.godrej.properties.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.ccavenue.security.AesCryptUtil;

public class CCGatewayEncDecUtil {

	
	public static String dcriptStr(String decriptStr,String workingKey)
	{
		 AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
		 String decRequest = aesUtil.decrypt(decriptStr);
		 System.out.println("Decription String"+decRequest);
		 return decRequest;
	}
	
	public static String encriptStr(String ccaRequest,String workingKey)
	{	 
		String encRequest="";
		 
		
		 System.out.println("Json "+ccaRequest);
		 AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
		 try {
			JSONObject jsonObject = new JSONObject(ccaRequest);
			 encRequest = aesUtil.encrypt(jsonObject.toString());
				System.out.println("encRequest:-"+encRequest);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return encRequest;
	}
}
