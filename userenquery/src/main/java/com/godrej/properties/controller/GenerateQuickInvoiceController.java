package com.godrej.properties.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.model.CCGatewayRequest;
import com.godrej.properties.model.CCGatewayResponse;
import com.godrej.properties.service.CCGatewayService;
import com.godrej.properties.util.CCGatewayEncDecUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class GenerateQuickInvoiceController {
	
	@Autowired
	CCGatewayService cCGatwayService;
	
	private final static String USER_AGENT = "Mozilla/5.0";
	CCGatewayEncDecUtil ccUtil = new CCGatewayEncDecUtil();
	@RequestMapping(value = "/quickInvoiceGenerator", method = RequestMethod.GET, produces = "application/json")
	public String quickInvoiceGenerator(@RequestParam("name") String customerName,@RequestParam("email") String customer_email_id,@RequestParam("amount") String amount,@RequestParam("mobileNo") String customer_mobile_no,@RequestParam("enq_name") String enq_name,@RequestParam("enq_sfid") String enq_sfid) {
		//insert GivenRequest
		CCGatewayRequest req= new CCGatewayRequest();
		
		String respDesc="";
		String merchant_reference_no="123";
		
		/*String ccaRequest = "{\"customer_name\":\""+customerName+"\",\"customer_email_id\":\""+customer_email_id+"\",\"customer_email_subject\":\"Test Invoice API\","
		 		+ "\"valid_for\":\"2\",\"valid_type\":\"days\",\"currency\":\"INR\",\"amount\":\""+amount+"\",\"sms_content\":\"Pls call 022-2121212121 to pay your LegalEntity_Name bill # Invoice_ID for" + 
		 		"Invoice_Currency Invoice_Amount or pay online at Pay_Link.\",\"customer_mobile_no\":\""+customer_mobile_no+"\",\"bill_delivery_type\":\"SMS\",\"merchant_reference_no\":\""+merchant_reference_no+"\",\"terms_and_conditions\":\"terms and condition\"}";*/
		
		String ccaRequest = "{\"customer_name\":\""+customerName+"\",\"customer_email_id\":\""+customer_email_id+"\",\"customer_email_subject\":\"Godrej Properties Invoice API\","
		 		+ "\"valid_for\":\"2\",\"valid_type\":\"days\",\"currency\":\"INR\",\"amount\":\""+amount+"\",\"sms_content\":\"Pls call 022-2121212121 to pay your LegalEntity_Name bill # Invoice_ID for" + 
		 		"Invoice_Currency Invoice_Amount or pay online at Pay_Link.\",\"customer_mobile_no\":\""+customer_mobile_no+"\",\"bill_delivery_type\":\"BOTH\",\"merchant_reference_no\",\"terms_and_conditions\":\"terms and condition\",\"billing_country\":\"india\"}";
		
		
		req.setAmount(new Double(amount));
		req.setEnq_id(enq_name);
		req.setEnq_sfid(enq_sfid);
		req.setReq_json_txt(ccaRequest);
		req.setCust_mobile(customer_mobile_no);
		req.setCust_email(customer_email_id);
		req.setCust_name(customerName);
		req.setCreateddate(new Timestamp(System.currentTimeMillis()));
		req.setUpdateddate(new Timestamp(System.currentTimeMillis()));
		CCGatewayRequest getReq = cCGatwayService.insertRequest(req);
	    ccaRequest = ccaRequest.replace("merchant_reference_no", "merchant_reference_no\":\""+getReq.getMerchant_reference_no()+"");
		@SuppressWarnings("static-access")
		String des = ccUtil.encriptStr(ccaRequest,KeyConstants.WORKINGKEY);
		String finalReq ="request_type=JSON&access_code="+KeyConstants.ACCESSCODE+"&command=generateQuickInvoice&version=1.1&response_type=JSON&enc_request="+des;
		try {
			String resp = getQuickInvoiceURL(finalReq);
			respDesc = ccUtil.dcriptStr(resp,KeyConstants.WORKINGKEY);
			CCGatewayResponse ccResp = new CCGatewayResponse();
			try {
				JSONObject json = new JSONObject(respDesc);
				ccResp.setError_desc(json.get("error_desc").toString());
				ccResp.setInvoice_id(Integer.parseInt(json.get("invoice_id").toString()));
				ccResp.setTiny_url(json.get("tiny_url").toString());
				ccResp.setInvoice_status(json.get("invoice_status").toString());
				ccResp.setError_code(json.get("error_code").toString());
				ccResp.setResp_json_txt(respDesc);
				ccResp.setMerchant_reference_no(getReq.getMerchant_reference_no());
				ccResp.setEnq_sfid(getReq.getEnq_sfid());
				ccResp.setEnq_name(getReq.getEnq_id());
				ccResp.setGenerated_date(new Timestamp(System.currentTimeMillis()));
				cCGatwayService.insertResponse(ccResp);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Call Response Table data
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return respDesc;
	}
	
	@RequestMapping(value = "/quickInvoiceStatusCheck", method = RequestMethod.GET, produces = "application/json")
	public String quickInvoiceStatusCheck(@RequestParam("reference_no") String reference_no,@RequestParam("order_no") String order_no) {
		String respDesc="";
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		QuickInvoiceResponseController responce = new QuickInvoiceResponseController();
		
		String des = responce.encriptStr(reference_no,order_no);//reference_no//order_no
		String finalReq ="request_type=JSON&access_code="+KeyConstants.ACCESSCODE+"&command=orderStatusTracker&response_type=JSON&enc_request="+des;
		
		try {
			String resp = responce.getQuickInvoiceURL(finalReq);
			responce.dcriptStr(resp);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return respDesc;
	}
	
	@RequestMapping(value = "/resendInvoiceLink", method = RequestMethod.GET, produces = "application/json")
	public String resendInvoiceLink(@RequestParam("order_no") String orderno) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(cCGatwayService.getTinyURL(orderno));
	}
	
	@RequestMapping(value = "/ccpaymentsuccess", method = RequestMethod.POST, produces = "application/json")
	public String ccGaywayPaymentSuccess(@RequestParam("encResp") String enc_response) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		System.out.println("enc_response:-"+enc_response);
		String respDesc = ccUtil.dcriptStr(enc_response,KeyConstants.WORKINGKEY);
		return gson.toJson(respDesc);
	}
	@RequestMapping(value = "/ccpaymentsuccess", method = RequestMethod.GET)
	public String ccGaywayPaymentSuccessGET(@RequestParam("encResp") String enc_response) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		System.out.println("enc_response:-"+enc_response);
		String respDesc = ccUtil.dcriptStr(enc_response,KeyConstants.WORKINGKEY);
		return gson.toJson(respDesc);
	}
	
	public static void main(String[] args) {
		GenerateQuickInvoiceController s = new GenerateQuickInvoiceController();
		s.quickInvoiceGenerator("Satheesh K","sathish.kyatham@godrejproperties.com","10","9987677726","ENQ - 13232","23123DDADF"); 
		
		/*String des = encriptStr();
		//String desc = "4259dac656c51d019a99736ccefdda518d46cf8de04d0b277cc5a433313d2275c89a6f633644ab34438bee9a8a02f2634bd6458b46ae5e4ee178011facf18dcbbfea93dba36cd51de803ad0b09d39ba19a6095ab8416307d22d971e1bf24c789e439c4bd53cd7ff34e4170f33b3cd522c10dfd7e023ee8fa3a6229ce5ba650f8604884113d966fa450aa93d90653c87cff8ba6096a2a6a1b8286008d2cc926b59adeeedb63ff9e2d5dff51768712e5ef562782e9a080aac9e0d117edc046ab4863a87b244f2702947f6ce5eed667ee2df5b6abe1246735aae1f1d860ddde8798fca968e55cf893ca99f3a48748d803361a25a1559cc6bb6b26477bc1e7ca45222b10f228f45a287da1d2028d8aa5337088619cbf13deb28294699455636d956f5f1b873868e155d02fcc8f2f0751683d2fc2658144f03d3d805af01bbd1e9976f76bd2df4b63783d21d4d9976666f4c5956a2d833ace16dc9465c10b5e8e2f62d1ec6d93de2fb227bda00c41a83e62386ba018c6bbcea136fd0da9f66e2e705df0a8871ebcd2fe02dd86186b6fc6a8cdfc8055d654c4d2514509e240e39b72f829a8c6b137e5bf11a1057a33565da1a810877079e4f403c2fb8767fc485861a9fb8dd0ca118c7531a3f9933886978e14ddf3bad11c9033c7946ec12a1eed49dac38b6164b6b5641f8d1292926f7aca1b0fd932058bcca1bea72bd83b1eb0fe21b2e6f7323fa7e492fd1e8197a9baf1eca2a19035c54d4069578e64a670b48697cbd6c7f405eed38b656a9adb82b430fffe43a85abd4f036bfd35be64effb6731464c228bdfdaedbf61a638144fee66cd7804a8099892cb6c506f150c17d6337282042475595a518a49e5d067bed82126ad1579348d39f35dc137c90409b59496a6dbaca68dcb713f711078c9959d93840fa6120c8ba4ecb02188b698cecdfc91e0e51f997c5817bcb5f35751405614817938a0a06cd89a1bcf7baa668adbb7bfff1931ea8d3486d1ae63e6830a767e98eb2b00bccf0e49b5b31de5411ed0bf1bd20b7bf337ef7e7444831ecfd4cb4f3f5a565b100280c0e748c365772a4250f2f56f76308818578e56679905f2b8fc820f6b243268ccefa302a8de2dff8025bf3c11904c1a5d58deae1ea90e0a744a25dd35a55c1d38a119c66d6e9b2bf8db4db43a86b5311d8e1486c201cee46befe77669fc204f84255ee0238cb6c3f3f153"; 
		//dcriptStr(desc);
		String finalReq ="request_type=JSON&access_code=AVWH85GE35BD30HWDB&command=generateQuickInvoice&version=1.1&response_type=JSON&enc_request="+des;
		
		try {
			String resp = getQuickInvoiceURL(finalReq);
			System.out.println("resp:-"+resp);
			dcriptStr(resp);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//
	}

	/*public static String encriptStr(String ccaRequest)
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
	}*/
	
	
	/*public static String dcriptStr(String decriptStr)
	{
		 AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
		 String decRequest = aesUtil.decrypt(decriptStr);
		 System.out.println("Decription String"+decRequest);
		 return decRequest;
	}*/
	
	public static String getQuickInvoiceURL(String link) throws ServletException, ClientProtocolException, IOException
	{
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
//		String url = "https://api.ccavenue.com/apis/servlet/DoWebTrans";
		String url = KeyConstants.CCPRDAPI;
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
		System.out.println(response.toString());
		
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
