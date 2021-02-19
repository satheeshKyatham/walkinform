package com.godrej.properties.util;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.godrej.properties.daoimpl.HoldIntervalDaoImpl;

public class SendSMS {
	static Logger logger = Logger.getLogger(SendSMS.class);
//	public static void main(String[] args) {
//		 SMSSend("8898821453", " 123123 youe top kjs ajshASH ashAS . kajsh . as ASaASs. as ");
//	}
	public static String SMSSend(String mobileNo, String text) {
		String responce = "";
		String responceNew = "";
		try {
			//9987677726&from=GPLPLB&text=
//			String requestUrl = "http://www.myvaluefirst.com/smpp/sendsms?username=godrejhtptrn&password=godrj001&to="+mobileNo+"&from=GPLCHP&text="+text+"&dlr-mask=19&dlr-url";
			//String requestUrl = "http://203.212.70.200/smpp/sendsms?username=godrejhttp1&password=godrej12&to="+mobileNo+"&from=GPLPLB&text="+text;
			//String requestUrl = "http://ip.shreesms.net/smsserver/SMS10N.aspx?Userid=GODREJPROPERTIES&UserPassword=123456&PhoneNumber="+ mobileNo + "&Text=" + text + "&GSM=Godrej";
			String requestUrl = "https://buzzify.in/V2/http-api.php?apikey=tevF3ldEUBoXS8nd&senderid=GODREJ&number="+ mobileNo + "&message="+text+"";
			logger.info("request buzzify SMS Url:-"+requestUrl);
			URL url = new URL(requestUrl);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			responce = uc.getResponseMessage();
			uc.disconnect();

			
			
			/*//9987677726&from=GPLPLB&text=
			String requestUrlNew = "http://www.myvaluefirst.com/smpp/sendsms?username=godrejhtptrn&password=godrj001&to=9987677726&from=GPLCHP&text="+text+"&dlr-mask=19&dlr-url";
			//String requestUrl = "http://203.212.70.200/smpp/sendsms?username=godrejhttp1&password=godrej12&to="+mobileNo+"&from=GPLPLB&text="+text;
			//String requestUrl = "http://ip.shreesms.net/smsserver/SMS10N.aspx?Userid=GODREJPROPERTIES&UserPassword=123456&PhoneNumber="+ mobileNo + "&Text=" + text + "&GSM=Godrej";
			System.out.println("request My ValueFirst SMS Url:-"+requestUrlNew);
			URL urlNew = new URL(requestUrlNew);
			HttpURLConnection ucNew = (HttpURLConnection) urlNew.openConnection();
			responceNew = ucNew.getResponseMessage();
			ucNew.disconnect();*/
			
			
			
			return responce;

		} catch (Exception ex) {
			logger.error("Value First Erro 32:-"+ex.getMessage());
		}
		return null;
	}
	public static String ShreeSMSSend(String mobileNo, String text) {
		String responce = "";
		try {
//			String requestUrl = "http://ip.shreesms.net/smsserver/SMS10N.aspx?Userid=GODREJPROPERTIES&UserPassword=123456&PhoneNumber="+ mobileNo + "&Text=" + text + "&GSM=Godrej";
			
			String requestUrl = "https://buzzify.in/V2/http-api.php?apikey=tevF3ldEUBoXS8nd&senderid=GODREJ&number="+ mobileNo + "&message="+text+"";
			//String requestUrl = "http://ip.shreesms.net/smsserver/SMS10N.aspx?Userid=GODREJPROPERTIES&UserPassword=123456&PhoneNumber="+ mobileNo + "&Text=" + text + "&GSM=Godrej";
		
			logger.info("request Shree SMS Url:-"+requestUrl);
			URL url = new URL(requestUrl);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			responce = uc.getResponseMessage();
			uc.disconnect();

			return responce;

		} catch (Exception ex) {
			logger.error("Shree SMS 50:-"+ex.getMessage());
		}
		return null;
	}
	
	public static String internationalSMSSend(String mobileNo, String otp) {
		String responce = "";
		try {
			
			String requestUrl = "https://2factor.in/API/V1/d524e39b-853c-11ea-9fa5-0200cd936042/SMS/"+ mobileNo +"/"+otp+"/D4U_OTP";
			logger.info("2factor Request OTP SMS For International Url:-"+requestUrl);
			URL url = new URL(requestUrl);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			responce = uc.getResponseMessage();
			uc.disconnect();

			return responce;

		} catch (Exception ex) {
			logger.error("2factor International SMS 50:-"+ex.getMessage());
		}
		return null;
	}
}
