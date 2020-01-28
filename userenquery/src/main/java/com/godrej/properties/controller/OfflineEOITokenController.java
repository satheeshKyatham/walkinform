package com.godrej.properties.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godrej.properties.dto.UserTokenDto;
import com.godrej.properties.service.UserTokenService;

@Controller
public class OfflineEOITokenController {

 	@Autowired
 	private UserTokenService userTokenService;
 	
	@GetMapping(value ="/generateWalkinTokenOffline")
	public @ResponseBody UserTokenDto success(@RequestParam("enquiryid") String enquiryId,
				@RequestParam("mobileno") String mobileNo,
				@RequestParam("projectSFID") String projectSFID,
				@RequestParam("projectName") String projectName,
				@RequestParam("countryCode") String countryCode,
				HttpServletRequest request
				) {
		
			HttpSession session = request.getSession(false);
			String userId =	(String) session.getAttribute("USERID");		
			return userTokenService.sendToken(enquiryId, mobileNo, projectSFID, projectName, countryCode,userId);

	}	
}
