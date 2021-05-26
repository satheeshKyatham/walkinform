package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.dto.CPEngmntDto;
import com.godrej.properties.service.CPEngmntService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class CPEngmntController {
	
	@Autowired
	ServletContext context;
	 
	@Autowired
	private CPEngmntService cPEngmntService;
	
	@RequestMapping(value = "/insertCPEngmnt", method = RequestMethod.POST, produces = "application/json")
	public String insertAuditLog(@RequestBody CPEngmntDto auditLogDto) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String response = "";
		try {
			cPEngmntService.insertAuditLog(auditLogDto);
			response="{\"status\":\"STATUS_OK\",\"error_msg\":\"\",\"errors_id\":null}";
			return response;
		} catch (Exception e) {
			response="{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Getting error at the time of insert CP Engagement\",\"errors_id\":null}";
			return response;
		}
	}
	
	@RequestMapping(value = "/getCPEngmntReport", method = RequestMethod.POST)
	public String getEOIReportDtl(@RequestParam("reportSource") String reportSource, @RequestParam("projectSfid") String projectSfid, 
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("userid") String userid) {
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String whereCondition = "";
		
		try {
			String [] multiProjectid= projectSfid.split(",");
			
			StringBuilder modifiedProid = new StringBuilder();
			String finalProjectid = "";
			
			for (int i=0;i<multiProjectid.length;i++){
				modifiedProid.append("'"+multiProjectid[i]+"'");
				modifiedProid.append(",");
			}
			
			finalProjectid = modifiedProid.toString();
			
			if (finalProjectid != null && finalProjectid.length() > 0 && finalProjectid.charAt(finalProjectid.length() - 1) == ',') {
				finalProjectid = finalProjectid.substring(0, finalProjectid.length() - 1);
			} 
			
			if(reportSource.equals("MISROLE") && projectSfid != null && projectSfid.length()>0 && (fromDate != null && fromDate.length() > 0) && (toDate != null && toDate.length() > 0)) {
				whereCondition = " a.project_sfid in ("+finalProjectid+") AND a.isactive = 'Y' AND Date(a.visit_date) between '"+fromDate+"' AND '"+toDate+"' ";
			} else if(reportSource.equals("CLOSINGROLE") && projectSfid != null && projectSfid.length()>0 && (fromDate != null && fromDate.length() > 0) && (toDate != null && toDate.length() > 0)) {
				whereCondition = " a.project_sfid in ("+finalProjectid+") AND a.isactive = 'Y' AND a.createdby = '"+userid+"' AND Date(a.visit_date) between '"+fromDate+"' AND '"+toDate+"' ";
			} else {
				return gson.toJson(null);
			}
			 
			return gson.toJson(cPEngmntService.getReportDtl(whereCondition));
		} catch (Exception e) {
			return gson.toJson(null);
		}
	}
}