package com.godrej.properties.webservices;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.model.VWTokenScreen;
import com.godrej.properties.service.TokenService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class TokenScreenController {
	private Logger Log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = { "/getUpcomingTokenScreen" }, method = RequestMethod.GET)
	public String getTokenEntrys(@RequestParam("tokenType") String tokenType,
			@RequestParam("ProjectId") String projectId) {
		Date date = new Date();
		String todayDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		List<VWTokenScreen> tokens = tokenService.getUpcomingToken(tokenType, projectId, todayDate, todayDate);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(tokens);
	}
	
	@RequestMapping(value = { "/getAssignedTokenScreen" }, method = RequestMethod.GET)
	public String getTokenAssignEntrys(@RequestParam("tokenType") String tokenType,
			@RequestParam("projectid") String projectid) {
		Date date = new Date();
		String todayDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		List<VWTokenScreen> tokens = tokenService.getAssignedList(tokenType, projectid, todayDate, todayDate);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(tokens);
	}
}