package com.godrej.kyc.util;

import java.net.HttpURLConnection;
import java.net.URL;

public class SendSMS {

//	public static void main(String[] args) {
//		 SMSSend("8898821453", " 123123 youe top kjs ajshASH ashAS . kajsh . as ASaASs. as ");
//	}
	public static String SMSSend(String mobileNo, String text) {
		String responce = "";
		HttpURLConnection uc = null;
		try {

			/*String requestUrl = "http://ip.shreesms.net/smsserver/SMS10N.aspx?Userid=GODREJPROPERTIES&UserPassword=123456&PhoneNumber="
					+ mobileNo + "&Text=" + text + "&GSM=Godrej";*/
			
			String requestUrl = "http://203.212.70.200/smpp/sendsms?username=godrejhttp1&password=godrej12&to="
					+mobileNo+"&from=GPLPLB&text="+text;
			System.out.println("requestUrl SMS:-"+requestUrl);
			URL url = new URL(requestUrl);
			uc = (HttpURLConnection) url.openConnection();
			responce = uc.getResponseMessage();
			

			return responce;

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}finally {
			if(uc!=null) {	
				uc.disconnect();
			}
		}
		return null;
	}
}
