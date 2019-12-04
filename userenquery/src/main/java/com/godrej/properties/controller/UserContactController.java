package com.godrej.properties.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godrej.properties.common.dto.CustomResponseDto;
import com.godrej.properties.dto.ContactDto;
import com.godrej.properties.service.UserContactService;

@Controller
public class UserContactController {

	@Autowired
	private UserContactService userContactService;
	
	@GetMapping("/getBrokerContactByCPSfid/{sfid}")
	@ResponseBody
	public CustomResponseDto getBrokerContactByCPSfid(@PathVariable("sfid")String partnerSfid){
		CustomResponseDto resp=new CustomResponseDto();
		List<ContactDto> brokerList=userContactService.getContactByPartnerSfid(partnerSfid);
		resp.addObject("brokerList", brokerList);
		return resp;
	}
	
}
