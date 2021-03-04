package com.godrej.properties.webservices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.dto.TokenScreenConfigDto;
import com.godrej.properties.model.TokenScreenConfig;
import com.godrej.properties.model.VWTokenScreen;
import com.godrej.properties.service.TokenScreenConfigService;
import com.godrej.properties.service.TokenService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class TokenScreenController {
	private Logger Log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private TokenScreenConfigService tokenScreenConfigService;
	
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
	
	@RequestMapping(value = "/insertTokenScreenConfig", method = RequestMethod.POST, produces = "application/json")
	public String insertAuditLog(@RequestBody TokenScreenConfigDto auditLogDto) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		try {
			return gson.toJson(tokenScreenConfigService.insertConfig(auditLogDto));
		} catch (Exception e) {
			return gson.toJson(null);
		}
	}
	
	@RequestMapping(value = "/getScreenConfig", method = RequestMethod.POST)
	public String insertTnCForPP(@RequestParam("projectid") String projectid)  {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		TokenScreenConfig tnC = tokenScreenConfigService.getTncData(projectid);

		return gson.toJson(tnC);
	}
	
	
	@RequestMapping(value = "/updateTokenScreenConfig", method = RequestMethod.POST, produces = "application/json")
	public Boolean updateData(@RequestBody TokenScreenConfigDto auditLogDto) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		try {
			Boolean responseVal = tokenScreenConfigService.updateTokenScreen(auditLogDto);
			return responseVal;
		} catch (Exception e) {
			return false;
		}
	}
	
	 
}