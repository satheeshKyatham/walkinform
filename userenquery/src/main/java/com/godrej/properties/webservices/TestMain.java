package com.godrej.properties.webservices;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestMain {

	public static void main(String[] args) {
		
		String json="{'offerid':'a1X2s0000004MZnEAM','message':'Success'}";
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject)jsonParser.parse(json);
		System.out.println(jo.get("offerid").getAsString());

	}

}
