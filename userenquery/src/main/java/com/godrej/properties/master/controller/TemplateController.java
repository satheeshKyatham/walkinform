package com.godrej.properties.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godrej.properties.common.dto.CustomResponseDto;
import com.godrej.properties.master.dto.TemplateDto;
import com.godrej.properties.master.service.SysConfigService;
import com.godrej.properties.master.service.TemplateService;

@Controller
public class TemplateController {

	@Autowired
	private TemplateService templateService;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@PostMapping(value = "/getTemplate")
	public @ResponseBody CustomResponseDto getTemplate(@RequestParam("code") String value, @RequestParam("projectId") String projectSfid) {
		
		TemplateDto template =  templateService.getTemplate(value);
		
		if(template == null) {
			return new CustomResponseDto(false, "No Template ID found.");
		}
		
		CustomResponseDto response =  new CustomResponseDto(true, "");
		response.addObject("template", template);
		return response;
	}
	
	@PostMapping(value = "/getTemplateForOffer")
	public @ResponseBody CustomResponseDto getTemplate(@RequestParam("projectId") String projectSfid) {
		
		
	
		TemplateDto template =  templateService.getTemplate(projectSfid);
		
		if(template == null) {
			return new CustomResponseDto(false, "No Template ID found.");
		}
		
		CustomResponseDto response =  new CustomResponseDto(true, "");
		response.addObject("template", template);
		return response;
	}
	
}
