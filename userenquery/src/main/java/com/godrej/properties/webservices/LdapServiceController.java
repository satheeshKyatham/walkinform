package com.godrej.properties.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.dto.LdapUserDetailsDto;
import com.godrej.properties.service.LdapService;

@CrossOrigin(origins="*")
@RestController
public class LdapServiceController {
	@Autowired
	LdapService ldapService;
	
	@RequestMapping(value = "/getLdapUserDetails", method = RequestMethod.POST,produces = "application/json")
	public LdapUserDetailsDto getLdapUserDetails(@RequestBody LdapUserDetailsDto dto ) {	
		return ldapService.getldapUserData(dto);
	}
	  
}
