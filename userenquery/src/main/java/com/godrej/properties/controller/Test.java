package com.godrej.properties.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;

public class Test {
	/*public float ab= 1.1f;
	void add(float a){
		System.out.println(".."+a);
	}
	void add(int a){
		
	}
	public static void main(String a[]){
		Test t=new Test();
		
	}*/
	
	public static void main(String[] args) throws JSONException {
		/*StringBuffer s = new StringBuffer("status=0&enc_response=4259dac656c51d019a99736ccefdda518d46cf8de04d0b277cc5a433313d2275f7924794cc5562d34168a1bdbf5753b90a55a988bf5341b154488478744c6e59abbc11b267c6fcae6767c7ba0bc4a0c1238e6fec0b7ba06cf807cc4221888dc0805e60157be7cbed13e0d43a878c33ffdfefd81ba90c050f3a056952146e0de706978abe77a357cef64608234086dfee0a25bd4787882c987a33d862614512f1b6a1c6fd8fbdbfd4b5f862d5cb793b512a833cb09a4d7d51c036fa0c8588df66dafcf3eab049baddb5d55aa887736f3378b2d0961b97f43e69db84c1693f63ba48909293c06295a0c11cae4498843b0be7f74ae3586bcd1ad77c2f1ce22c9675a390821b1e14379d46fd904f0c6c0a9ff1b2576e591a86c54eca71904afa8d575ccd1a0b4ba82949672ecb67a0648fe1bca3a818030324df877b869f6df5360a91588ca805b902a0a87b1b866e1c31ba1d178e3fca638b8e45e39fd81a7e0d5875f79fae8642f0863a06c70077b2f294f144f452b35925b2ef69f85256e566e581c4c8f7237696c5f0e197e840322482b2e0441210e7fdcd28ff875121cb803edb39af257e1e632c14e658722893abe2681ea92cb09a915710d1b3baccb055188e7405ae465e7320c5af43914229a2f84181b49d58a307d884d3cfcb5b9cb9e63ef63b7ae6ee8a29f0731f803156372a68177ff8450482f6f7bdb1c65fdbdc9a67e576fbce0b02e3105c550d20b955fff12b1f37bc8fe66d8680a9d0d231643f8fbc9e99c71dc9b839cc2cbd4afa63d3cde237c7ae813e6970831fb5ff9ffbad88d2207e181f4d9cdb0debebbd2989e4cafab2d8d6351337bb1f5e8b7cc12ca7ed6a9eae367173da4e746e6830448429322956687606cf6364faea187d8f79fb6282e4c7922511f13ddd56488c5a84d853495b50ea02c4ce567dad2a8f4a5df271c994dcb2dca907e3a82b0ada7e35ab4c9d0d0683e9193db307e4fce1311df241ed8db54405bc2f38fe0afc4eddb99a8a6e11fff0575faa5a2e04a90f0a248181f678b86834763a94b4b119458170eb99e8b6164a89aaf1e6e6e3feae25d82fa092f81bcdd359ce6351a613ff7a0d486afc02954c20d5db860ff02b2ac878d76aca952f87cedac8761f691817f6bcb8ecd1fb543d41d449df1b877f0d2ed52b602b168b9278463478dc3b3c8b4c4fbc");
		String[] params = s.toString().split("&");
	    Map<String, String> map = new HashMap<String, String>();
	    for (String param : params)
	    {
	        String name = param.split("=")[0];
	        String value = param.split("=")[1];
	        map.put(name, value);
	    }
	    System.out.println("Va"+map.get("enc_response"));*/
		
		/*Timestamp time = new Timestamp(System.currentTimeMillis());
		System.out.println("time:-"+time);
		
		System.out.println(new Date(System.currentTimeMillis()+330*60*1000));*/
		
		
		/*StringBuffer s = new StringBuffer("order_id=39256898&tracking_id=108603404057&bank_ref_no=916420429910&order_status=Success&failure_message=&payment_mode=Unified Payments&card_name=UPI&status_code=&status_message=Transaction Successful&currency=INR&amount=1.00&billing_name=Satheesh K&billing_address=test&billing_city=Mumbai&billing_state=Maharashtra&billing_zip=400018&billing_country=India&billing_tel=9987677726&billing_email=sathish.kyatham@godrejproperties.com&delivery_name=null&delivery_address=null&delivery_city=null&delivery_state=null&delivery_zip=null&delivery_country=null&delivery_tel=null&merchant_param1=9971&merchant_param2=null&merchant_param3=null&merchant_param4=null&merchant_param5=null&vault=N&offer_type=null&offer_code=null&discount_value=0.0&mer_amount=1.00&eci_value=&retry=N&response_code=&billing_notes=&trans_date=2019-06-13 20:18:06.593&bin_country=&inv_mer_reference_no=null");
		
		String[] params = s.toString().split("&");
	    Map<String, String> map = new HashMap<String, String>();
	    for (String param : params)
	    {
	        String name = param.split("=")[0];
	        String value="";
	        if(param.split("=").length>1)
	        {
	        	 value = param.split("=")[1];
	        }
	        map.put(name, value);
	    }
	    System.out.println("order_id:-"+map.get("order_id"));*/
		
		/*LocalDate currentDate = new LocalDate();
		Date date = currentDate.toDateTimeAtStartOfDay().toDate();
		date.setHours(168);
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sm.format(date);
		System.out.println("Date"+strDate);*/
		/*Date date1 = new Date(2016, 11, 20);
        Date date2 = new Date(2016, 11, 21);
 
        boolean isAfter = date1.after(date2);
        System.out.println("date 1 is after date 2 ? :- " + isAfter);
 
        date2 = new Date(2016, 11, 18);
 
        isAfter = date1.after(date2);
        System.out.println("date 1 is after date 2 ? :- " + isAfter);*/
		/*String offerId="{'Sucess':true,'errorMessage':'','offers':[{'offerId':'a1X2s0000004gVPEAY','offerErrorMessage':''}]}";
		
				JSONObject ob = new JSONObject(offerId);  
				org.json.JSONArray arr = ob.getJSONArray("offers");
				System.out.println(ob.get("errorMessage")); 
				for(int i=0; i<arr.length(); i++){   
				  JSONObject o = arr.getJSONObject(i);  
				  System.out.println(o); 
				  System.out.println(o.get("offerId")); 
				}*/
		 /*String str = "4434";
	      if(str.matches("[0-9]+")) {
	         System.out.println("String contains only digits!");
	      }
	      else
	    	  System.out.println("String contains Alpha Numeric!");*/
		
		/*String str ="f9099318246ff300e6c53fea0272e6c3237481fb3d7989b10e62b4a1c3ee6f047fc4eb8eda9c06c81c6fe20cb1179dbd5defe5150b9745f549e6205eab79c69784758222724f4f8c061fff5075997b87df7f3f2a023c2a23e7153067d07aefd8f32ba25c517d0092e1e774cf5b520b8785cb58b05b6efb2d5714b45bb9fde9b67c666b7907977937266c996e38d129295ad33da677b9118fff299bba7fce70a807d7e001dae24ec00b82b6d4cfb836d6ebe441b65dffeb61496478a321c7fb5038b664485229b5c8790e8042850910929c86a0954610b03b20df6f40790f1e08721ae9686a70e9b4537c28ddf85a482de1e0d7bfc3d9aacc44e598e2bf4a3eba54a7a3e015ae9364820b1bc9cca35b94662c3a0cefbe22bca2108d6d9c4501079ed03c5995c4855004c387574a4d2b395b5109b6f1dc8e8d4e17d8e6c862200eeade820cbfb608454f264e7a185f114459fa0f0c0f9c4eae308e8649f3b736d38ed1f9b14a578d388fe50b902e3c19adac1bf7ec64eb55187a0eb8f35b0211aace76adb5fc1023fb4e52aa1722ba095fcc9826c543e7ea824735464050fb9688173cdc45c4ffdc847164d9b29af3b524eb9f3c9cdf4f68bf3860ea25a4b742b6df718f2018b30436207a8c8f40250bab5ba7a0c656162e6ef6533b426e98e86ede3727689389a7254301458f0ba7484e037a8a30e77acd69d8c37e2def2119bba6a1cba499ecf534109f391a4617317b553de4a6d8e94afdf075c51fe27646e1ebb65807815e6f697336efb1a65eb1d98657352a71de06188386c64f436378945d7499c289ba7ae44d2755d995c1f97f13a69876a7186fca4fdaac292f47343c753a6f49003acb9e0e5f79f8f113b028a8468ff32737734d4c80ea6be4afa64ffbbf6d0de784c68f80349ab6389af42b992686e6458e1d174529553ca05b93eed982afe709dd88d373c96026212fa32d74e7ae1ce18c0c960c03393fcc4b904cc676913a822fc0403cbfa077fa9a7fe93264d95aacf39b153b5095a2d9adf51af2556687b3532578152731636f6a17119da8e2e1f58ac46ccced3ea23d7bef88274ae925f8832ce91a4444b2b52a3c9ae57baefdd77b5e3e99ed5f04bffb6a8f";
		CCAvenueResponseModel respModel = new CCAvenueResponseModel();
		String workingKey = "AC52E9A706E2D7938203D4D554B61E2E";
		AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
		String decResp = aesUtil.decrypt(str);
		System.out.println(decResp);
		respModel.setGateway_response(decResp);
		StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
		String pair=null, pname=null, pvalue=null;
		while (tokenizer.hasMoreTokens()) {
			pair = (String)tokenizer.nextToken();
			if(pair!=null) {
				StringTokenizer strTok=new StringTokenizer(pair, "=");
				pname=""; pvalue="";
				if(strTok.hasMoreTokens()) {
					pname=(String)strTok.nextToken();
					if(pname.contains("order_id"))
					{
						if(strTok.hasMoreTokens())
							respModel.setOrder_id((String)strTok.nextToken());
					}
					if(pname.contains("tracking_id"))
					{
						if(strTok.hasMoreTokens())
							respModel.setTracking_id(Long.parseLong(strTok.nextToken()));
					}
					if(pname.contains("bank_ref_no"))
					{
						if(strTok.hasMoreTokens())
							respModel.setBank_ref_no((String)strTok.nextToken());
					}
					
					if(pname.contains("order_status"))
					{
						if(strTok.hasMoreTokens())
							respModel.setOrder_status((String)strTok.nextToken());
					}
					if(pname.contains("failure_message"))
					{
						if(strTok.hasMoreTokens())
							respModel.setFailure_message((String)strTok.nextToken());
					}
					if(pname.contains("payment_mode"))
					{
						if(strTok.hasMoreTokens())
							respModel.setPayment_mode((String)strTok.nextToken());
					}
					if(pname.contains("card_name"))
					{
						if(strTok.hasMoreTokens())
							respModel.setCard_name((String)strTok.nextToken());
					}
					
					if(pname.contains("status_code"))
					{
						if(strTok.hasMoreTokens() && strTok.nextToken()!=null)
							respModel.setStatus_code(Integer.parseInt(strTok.nextToken()));
					}
					if(pname.contains("status_message"))
					{
						if(strTok.hasMoreTokens())
							respModel.setStatus_message((String)strTok.nextToken());
					}
					if(pname.contains("response_currency"))
					{
						if(strTok.hasMoreTokens())
							respModel.setResponse_currency((String)strTok.nextToken());
					}
					if(pname.contains("response_amount"))
					{
						if(strTok.hasMoreTokens())
							respModel.setResponse_amount(new Double(strTok.nextToken()));
					}
					if(pname.contains("vault"))
					{
						if(strTok.hasMoreTokens())
							respModel.setVault((String)strTok.nextToken());
					}
					if(pname.contains("response_code"))
					{
						if(strTok.hasMoreTokens())
							respModel.setResponse_code(Integer.parseInt(strTok.nextToken()));
					}
					if(pname.contains("trans_date"))
					{
						//if(strTok.hasMoreTokens())
							//respModel.setTrans_date(strTok.nextToken());
					}
					if(pname.contains("merchant_param2"))
					{
						if(strTok.hasMoreTokens())
							respModel.setMerchant_param2((String)strTok.nextToken());
					}
					if(pname.contains("merchant_param3"))
					{
						if(strTok.hasMoreTokens())
							respModel.setMerchant_param3((String)strTok.nextToken());
					}
				}
			}
		}
		System.out.println("respModel:-"+respModel);
		System.out.println("respModel:-"+respModel.getTracking_id());
		System.out.println("respModel:-"+respModel.getResponse_amount());*/
		
		/*long i = Long.parseLong("9987677726");
		System.out.println(i);*/
		
		 //public static Date getUKDateTime(Date date) {
			/* Calendar cal = Calendar.getInstance();
			// remove next line if you're always using the current time.
			cal.setTime(new Date());
			cal.add(Calendar.HOUR, -5);
			cal.add(Calendar.MINUTE, -30);
			Date hourback = cal.getTime();*/
			//return hourback;
		 //}
		/*String timeStamp = new SimpleDateFormat("ddMMyyyy").format(new Date());
		System.out.println(timeStamp);
		System.out.println(Calendar.getInstance().getTimeInMillis());*/
		
		/*if(90>120 && ("Appointment Proposed".equals("Appointment Proposed")))
		{
			System.out.println("Qualify........");
			
		}
		else
			System.out.println("Else........");*/
	
		/*JSONArray jsonArray= new JSONArray();
		
		 JSONObject obj = new JSONObject();
	      obj.put("merchant_id", "foo");
	      obj.put("accesscode", "foo");
	      obj.put("workingkey", "foo");
	      obj.put("towercode", "foo");
	      obj.put("towersfid", "foo");
	     // jsonArray.
	      JSONObject obj1 = new JSONObject();
	      obj1.put("merchant_id", "foo");
	      obj1.put("accesscode", "foo");
	      obj1.put("workingkey", "foo");
	      obj1.put("towercode", "foo");
	      obj1.put("towersfid", "foo");
	      jsonArray.add(obj1);
	      System.out.println(jsonArray.toString());
	      JSONParser parser = new JSONParser();
	      try {
			JSONArray objNew = (JSONArray) parser.parse(jsonArray.toString());
			System.out.println("Count:"+objNew.length());
			for(int i=0;i<objNew.length();i++)
			{
				JSONObject objEx = objNew.getJSONObject(i)k
				//JSONObject objEx = new JSONObject(objNew.get(i));
				System.out.println("Data:"+objEx);
			}
			
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	     
	}

	
	public static java.sql.Date getYYYYMMDD(String inputDate) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       // String dateInString = inputDate;

        try {
        	Date dtDob = new Date(inputDate);
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    		     String newDate = sdf.format(dtDob);

           Date myDate = formatter.parse(newDate);
            return new java.sql.Date(myDate.getTime()); 
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return null; 

	}
}
