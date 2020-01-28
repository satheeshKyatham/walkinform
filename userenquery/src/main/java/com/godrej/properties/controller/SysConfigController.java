package com.godrej.properties.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godrej.properties.master.service.SysConfigService;

@Controller
public class SysConfigController {

	@Autowired
	private SysConfigService sysConfigService;
	
	@GetMapping(value = "/reloadFull")
	public @ResponseBody String reloadFull() {
		return sysConfigService.load();
	}
	
	@GetMapping(value = "/reload")
	public @ResponseBody String reload(@RequestParam ("key") String key,
			@RequestParam ("recordId") String recordId) {
		return sysConfigService.reload(key, recordId);
	}
	
}
