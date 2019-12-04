package com.godrej.properties.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godrej.properties.common.dto.CustomResponseDto;
import com.godrej.properties.dto.ChannelPartnerDto;
import com.godrej.properties.service.ChannelPartnerService;

@Controller
public class ChannelPartnerController {

	@Autowired
	private ChannelPartnerService channelPartnerService;
	
	@GetMapping("/getChannelPartnerList/{name}")
	@ResponseBody
	public CustomResponseDto getChannelPartnerList(@PathVariable("name") String name){
		CustomResponseDto resp =new CustomResponseDto();
		List<ChannelPartnerDto> channelPartnerList=channelPartnerService.getChannelPartners(name+"%");
		resp.addObject("channelPartnerList", channelPartnerList);
		return resp;
	}

}
