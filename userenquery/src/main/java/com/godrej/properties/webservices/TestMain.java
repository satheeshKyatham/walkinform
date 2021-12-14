package com.godrej.properties.webservices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*import org.slf4j.Logger;*/
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestMain {

	private static Logger logger = LogManager.getLogger(TestMain.class);
	/*private static Logger logger = LoggerFactory.getLogger(TestMain.class);*/
	public static void main(String[] args) {
		
		String json="{'offerid':'a1X2s0000004MZnEAM','message':'Success'}";
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject)jsonParser.parse(json);
		System.out.println(jo.get("offerid").getAsString());
		logger.info("currentTpm: info");
		logger.debug("currentTpm:dubug ");
		logger.trace("HeLoo:trace");

	}

}
