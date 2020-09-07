package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.dto.CostsheetLogDto;
import com.godrej.properties.service.CostsheetLogService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class CostsheetLogController {
	
	@Autowired
	ServletContext context;
	 
	@Autowired
	private CostsheetLogService costsheetLogService;
	
	@RequestMapping(value = "/insertCostsheetLog", method = RequestMethod.POST, produces = "application/json")
	public String insertAuditLog(@RequestBody CostsheetLogDto auditLogDto) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		try {
			return gson.toJson(costsheetLogService.insertAuditLog(auditLogDto));
		} catch (Exception e) {
			return gson.toJson(null);
		}
		
	}
}