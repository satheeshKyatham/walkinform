package com.godrej.properties.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godrej.properties.model.HoldInventoryEntry;
import com.godrej.properties.service.ContactReportService;
import com.godrej.properties.service.EnquiryReportService;
import com.godrej.properties.service.HoldInventoryEntryService;
import com.godrej.properties.service.PushEnquiryDataService;

/*@Configuration
@EnableScheduling
@Controller*/
public class SyncSchedular {
	private Logger LOG=LoggerFactory.getLogger(getClass());
	@Autowired
	private PushEnquiryDataService pushEnqDataService;
	@Autowired
	private ContactReportService contactReportService;
	@Autowired
	private EnquiryReportService enquiryReportService;
	
	@Autowired
	private HoldInventoryEntryService holdInventoryEntryService;
	
	/*@Scheduled(fixedRate=60*60*1000)*/	
	/*@RequestMapping("/syncContactAndEnquiry")*/
	@ResponseBody
	@Scheduled(cron="0 0/2 * * * ?")/* 2 min schedular time*/
	public void hourlySchedular(){
		LOG.info("hourlySchedular ::*************");
		pushEnqDataService.syncContactSfidToEnquiry();
	}
	@ResponseBody
	@Scheduled(cron="0 0/2 * * * ?")/* 2 min schedular time*/
	@RequestMapping("/updateCustomContactAndEnquiry")
	public void customUpdateSchedular(){
		LOG.info("customUpdateSchedular ::*************");
		contactReportService.updateContactSfidToCustomContact();
		enquiryReportService.updateEnquirySfidToCustomEnquiry();
	}
	
	
	@ResponseBody
	@Scheduled(cron = "0/30 * * * * ?")//@Scheduled(cron = "0/20 * * * * ?")/* 30 Sec schedular time*///"0/15 * * * * *" //--20 Sec "*/20 * * * * *"
	@RequestMapping("/updateBulkInventoryStatus")
	public void updateBulkInventoryStatus(){
		LOG.info("updateBulkInventoryStatus ::*************");
		HoldInventoryEntry updateHold = new HoldInventoryEntry ();
		updateHold.setStatusai("I");
		updateHold.setHoldstatusyn("N");
		holdInventoryEntryService.updatePreviousHold(updateHold);//20
	}
}
