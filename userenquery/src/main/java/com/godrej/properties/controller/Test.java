package com.godrej.properties.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
		String offerId="{'Sucess':true,'errorMessage':'','offers':[{'offerId':'a1X2s0000004gVPEAY','offerErrorMessage':''}]}";
		
				JSONObject ob = new JSONObject(offerId);  
				org.json.JSONArray arr = ob.getJSONArray("offers");
				System.out.println(ob.get("errorMessage")); 
				for(int i=0; i<arr.length(); i++){   
				  JSONObject o = arr.getJSONObject(i);  
				  System.out.println(o); 
				  System.out.println(o.get("offerId")); 
				}
	}

}
