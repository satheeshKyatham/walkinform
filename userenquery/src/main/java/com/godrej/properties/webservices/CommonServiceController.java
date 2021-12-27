package com.godrej.properties.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.TokenService;

@CrossOrigin(origins = "*")
@RestController
public class CommonServiceController {

	@Autowired
	private TokenService tokenService;

	@PostMapping(value = "/assignToEToken")
	public String updateAssignStatus(@RequestParam("id") String id, @RequestParam("assinedto") String assinedto) {
		tokenService.updateAssignStatus(id, assinedto);
		return "success";
		
	}
	
	@GetMapping(value = "/gcCall")
	public String gcCall() {
		System.gc();
		return "OK";
	}
	
}
