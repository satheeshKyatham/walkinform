package com.godrej.properties.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godrej.properties.model.HoldInventoryEntry;
import com.godrej.properties.model.HoldParkingEntry;
import com.godrej.properties.service.BalanceDetailsService;
import com.godrej.properties.service.ContactReportService;
import com.godrej.properties.service.EnquiryReportService;
import com.godrej.properties.service.HoldInventoryEntryService;
import com.godrej.properties.service.HoldParkingEntryService;
import com.godrej.properties.service.PushEnquiryDataService;

/*@Configuration
@EnableScheduling*/
@Controller
@Transactional 
public class SyncSchedular {
	private Logger LOG=LoggerFactory.getLogger(getClass());
	@Autowired
	private PushEnquiryDataService pushEnqDataService;
	@Autowired
	private ContactReportService contactReportService;
	@Autowired
	private EnquiryReportService enquiryReportService;
	@Autowired
	private BalanceDetailsService balanceDetailsService;
	
	@Autowired
	private HoldInventoryEntryService holdInventoryEntryService;
	
	@Autowired
	private HoldParkingEntryService holdParkingEntryService;
	
	@ResponseBody
	@Scheduled(cron="0 0/2 * * * ?")
	public void hourlySchedular(){
		LOG.info("hourlySchedular ::*************");
		try {
			pushEnqDataService.syncContactSfidToEnquiry();
		}
		catch (Exception e) {
			LOG.error("hourlySchedular ::*************",e);
		}
	}
	@ResponseBody
	@Scheduled(cron="0 0/2 * * * ?")/* 2 min schedular time*/
	@RequestMapping("/updateCustomContactAndEnquiry")
	public void customUpdateSchedular(){
		LOG.info("customUpdateSchedular ::*************");
		try {
			contactReportService.updateContactSfidToCustomContact();
			enquiryReportService.updateEnquirySfidToCustomEnquiry();
		}
		catch (Exception e) {
			LOG.error("customUpdateSchedular ::*************",e);
		}
	}
	
	
	@ResponseBody
	@Scheduled(cron = "0/30 * * * * ?")//@Scheduled(cron = "0/20 * * * * ?")/* 30 Sec schedular time*///"0/15 * * * * *" //--20 Sec "*/20 * * * * *"
	@RequestMapping("/updateBulkInventoryStatus")
	public void updateBulkInventoryStatus(){
		LOG.info("updateBulkInventoryStatus ::*************");
		try {
			HoldInventoryEntry updateHold = new HoldInventoryEntry ();
			updateHold.setStatusai("I");
			updateHold.setHoldstatusyn("N");
			holdInventoryEntryService.updatePreviousHold(updateHold);//20 
		}
		catch (Exception e) {
			LOG.error("updateBulkInventoryStatus ::*************",e);
		}
		
		try {
			LOG.info("updateBulkParkingStatus ::*************");
			//Parking
			HoldParkingEntry updateParkingHold = new HoldParkingEntry ();
			updateParkingHold.setStatusai("I");
			updateParkingHold.setHoldstatusyn("N");
			holdParkingEntryService.updateParkingPreviousHold(updateParkingHold);//20
			// END Parking
		} catch (Exception e) {
			LOG.error("updateBulkParkingStatus ::*************",e);
		}
	}
	/*For SFDC Cancelled offer inactive in D4U*/
	@ResponseBody
	@Scheduled(cron = "0 0/55 * * * ?")//@Scheduled(cron = "0/20 * * * * ?")/* 30 Sec schedular time*///"0/15 * * * * *" //--20 Sec "*/20 * * * * *"
	@RequestMapping("/cancelledOfferInactive")
	public void cancelledOfferInactive(){
		LOG.info("cancelledOfferInactive ::*************");
		try
		{
			balanceDetailsService.eCRMCancelledOfferInactive();
		}
		catch (Exception e) {
			LOG.info("cancelledOfferInactive ::*************",e);
		}
	}
}
