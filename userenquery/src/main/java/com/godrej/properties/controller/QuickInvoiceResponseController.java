package com.godrej.properties.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import com.ccavenue.security.AesCryptUtil;

public class QuickInvoiceResponseController {
	
	static String testURL ="https://apitest.ccavenue.com/apis/servlet/DoWebTrans";
	static String accessCode= "AVWH85GE35BD30HWDB";		//Put in the Access Code in quotes provided by CCAVENUES.
	static String workingKey = "89F76AC5DDB8C91A2D05F756DE1BF446"; 
	private final static String USER_AGENT = "Mozilla/5.0";
	public static void main(String[] args) {
		QuickInvoiceResponseController qrc = new QuickInvoiceResponseController();
		String order_no="34335191";//34335191
		String reference_no="";
		String des = qrc.encriptStr(reference_no,order_no);
		//String desc = "c1e6f2d78bf7b2c43a6da04fa968d6a0d2bf0771ccb6e7aa1627f4782b82282b76f1d84215599073cc03f258b4c29928"; 
		//qrc.dcriptStr(desc);
		String finalReq ="request_type=JSON&access_code="+accessCode+"&command=orderStatusTracker&response_type=JSON&enc_request="+des;
		
		try {
			String resp = qrc.getQuickInvoiceURL(finalReq);
			qrc.dcriptStr(resp);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
	}

	public String encriptStr(String reference_no,String order_no)
	{	 
		String encRequest="";
		 String ccaRequest = "{\"reference_no\":\""+reference_no+"\",\"order_no\":\""+order_no+"\"}";
		
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
	
	
	public String dcriptStr(String decriptStr)
	{
		 AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
		 String decRequest = aesUtil.decrypt(decriptStr);
		 System.out.println("Decription String"+decRequest);
		 return decRequest;
	}
	
	public String getQuickInvoiceURL(String link) throws ServletException, ClientProtocolException, IOException
	{
		//Test K
		/*CloseableHttpClient  httpclient = HttpClients.createDefault();
		HttpPost httpost = new HttpPost("https://api.ccavenue.com/apis/servlet/DoWebTrans");
		StringEntity messageEntity = new StringEntity( link, ContentType.create("application/json"));
		httpost.setEntity(messageEntity);
		//StringEntity messageEntity = new StringEntity(link);
		httpost.setEntity(messageEntity);
		CloseableHttpResponse  closeableresponse = httpclient.execute(httpost);
		System.out.println("90 : "+ closeableresponse.getStatusLine());
		try {
			System.out.println("93 : "+ closeableresponse);
			System.out.println("90 : "+ closeableresponse.getStatusLine().getStatusCode());
			if (closeableresponse.getStatusLine().getStatusCode()  == HttpStatus.SC_OK) {
				
			}
		}
		finally {
			httpclient.close();
		}*/
		String url = "https://api.ccavenue.com/apis/servlet/DoWebTrans";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(link);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + link);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println("Status API Response:-"+response.toString());
		
		String[] params = response.toString().split("&");
	    Map<String, String> map = new HashMap<String, String>();
	    for (String param : params)
	    {
	        String name = param.split("=")[0];
	        String value = param.split("=")[1];
	        map.put(name, value);
	    }
	    System.out.println("Va"+map.get("enc_response"));
		
		return map.get("enc_response");
	}
}
