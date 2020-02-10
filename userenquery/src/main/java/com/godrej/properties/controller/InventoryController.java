package com.godrej.properties.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godrej.properties.master.service.SysConfigService;
import com.godrej.properties.model.HoldInventoryEntry;
import com.godrej.properties.model.Inventory;
import com.godrej.properties.model.ZzholdTest;
import com.godrej.properties.service.HoldIntervalService;
import com.godrej.properties.service.HoldInventoryEntryService;
import com.godrej.properties.service.InventoryService;
import com.godrej.properties.service.ZZExistService;
import com.godrej.properties.service.ZZrequestProcessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class InventoryController {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private static final int HOLD_TIME=5*60*1000;
	
 	@Autowired
 	private HoldInventoryEntryService holdInventoryEntryService;
 	
 	@Autowired
 	private InventoryService inventoryService;

	@Autowired
 	private ZZrequestProcessService zZrequestProcessService;
	
	@Autowired
	private SysConfigService sysConfigService;
 	
 	@Autowired
 	private ZZExistService zZExistService;

 	@Autowired
 	private HoldIntervalService holdIntervalService;

 	
	@PostMapping(value = { "/holdExistData" })
	public @ResponseBody String holdExistData (@RequestParam("projectNameId") String projectNameId, @RequestParam("customerId") String customerId) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();		
		List<HoldInventoryEntry> plans = holdInventoryEntryService.holdDataExist(projectNameId, customerId);
		
		ArrayList<HoldInventoryEntry> intList = new ArrayList<>(); 
		
		if (plans == null || plans.isEmpty()) {
			return gson.toJson("");
		}
		for (int k = 0; k < plans.size(); k++) {

			Timestamp currentTpm = new Timestamp(System.currentTimeMillis());

			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(currentTpm.getTime());

			// add a bunch of seconds to the calendar
			cal.add(Calendar.SECOND, 98765);

			HoldInventoryEntry plan = plans.get(k);
			if(plan == null || plan.getCreated_at() == null ) {
				continue;
			}
			Timestamp createdAt  = plan.getCreated_at();
			long time = createdAt.getTime();
			// create a second time stamp
			int holdTime =  plan.getHoldForTime();
			Timestamp timestampValue = new Timestamp(time+ holdTime);

			long milliseconds = timestampValue.getTime() - currentTpm.getTime();
			int seconds = (int) milliseconds / 1000;

			int hours = seconds / 3600;
			int minutes = (seconds % 3600) / 60;
			seconds = (seconds % 3600) % 60;

			log.info("currentTpm: " + currentTpm);
			log.info("timestampValue: " + timestampValue);

			log.info("Difference: ");
			log.info(" Hours: " + hours);
			log.info(" Minutes: " + minutes);
			log.info(" Seconds: " + seconds);
			plans.get(k).setHoldMin(minutes);
			plans.get(k).setHoldSec(seconds);

			intList.add(plans.get(k));
		}
			
		return gson.toJson(intList);
		
	}
	
	
	/* Added HOLD interval 20190515 */
	@PostMapping(value = { "/holdInventoryRqst" })
	public @ResponseBody String holdInventoryRqst(@RequestParam("customerId") String customerId,
			@RequestParam("unitSfid") String unitSfid, @RequestParam("projectNameId") String projectNameId,
			@RequestParam("towerCode") String towerCode, @RequestParam("towerName") String towerName,
			@RequestParam("unitNo") String unitNo, @RequestParam("floorNo") String floorNo,
			@RequestParam("userid") int userid) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		/* Inventory bulk update - Commented By Satheesh Kyatham- 03-10-2019 */
		/* =======Start========== */
		/*
		 * HoldInventoryEntry updateHold = new HoldInventoryEntry ();
		 * 
		 * updateHold.setUnitSfid(unitSfid); updateHold.setCustomer_id(customerId);
		 * updateHold.setProject_id(projectNameId); updateHold.setStatusai("I");
		 * updateHold.setHoldstatusyn("N");
		 * holdInventoryEntryService.updatePreviousHold(updateHold);
		 */
		/* =========End======== */

		if(userid == 0) {
			log.info("No user info");
			return gson.toJson("No user session.");
		}


		HoldInventoryEntry holdUnitDtl = holdInventoryEntryService.getHolding(Integer.valueOf(userid));
		
				
		if (holdUnitDtl != null && !holdUnitDtl.equals("")) {
			log.info("You can not Hold more than One unit");
			return gson.toJson("You can not hold more than one unit");
		} else {
			Inventory uDtl = holdIntervalService.getHeldUnit(unitSfid);
			log.info("After Unit Exit Query************************* HOLD Issue");
			if (uDtl != null && !uDtl.equals("")) {
				log.info("Unit Exit Query Entry found************************* HOLD Issue");
				return gson.toJson("Sorry this unit is Held by someone else, Please Try again after some time");
			} else {
				log.info("Unit Exit Query No Entry found************************* HOLD Issue");
				
	    		int holdTime = sysConfigService.getValueAsInt(SysConfigService.HOLD_TIME, projectNameId);
	    		if(holdTime==0) {
	    			holdTime =HOLD_TIME;
	    		}

				
				HoldInventoryEntry action = new HoldInventoryEntry();
				action.setUnitSfid(unitSfid);
				action.setHoldstatusyn("Y");
				action.setStatusai("A");
				action.setCustomer_id(customerId);
				action.setProject_id(projectNameId);
				
				action.setTower_name(towerName);
				action.settower_code(towerCode);
				action.setFloor_no(floorNo);
				action.setUnit_no(unitNo);
				action.setUser_id(userid);
				action.setHoldForTime(holdTime);

				action.setCreated_at(new Timestamp(System.currentTimeMillis()));
				log.info("Before Insert************************* HOLD Issue");

				try {
					holdInventoryEntryService.insertHoldRqst(action);
				}catch (Exception e) {
					log.error("Exception while holding inventory , Only one person can hold the inventory at a time");
					return gson.toJson("Sorry this unit is Held by someone else, Please Try again after some time");
				}
				log.info("After Insert************************* HOLD Issue");
				return "inserted";
			}
		}
	}
	/* END Added for HOLD interval */

	/* Manual release From Hold Unit */
	@PostMapping(value = { "/releaseFromHold" })
	public @ResponseBody  String releaseFromHold(@RequestParam("customerId") String customerId,
			@RequestParam("unitSfid") String unitSfid, @RequestParam("projectNameId") String projectNameId) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		try
		{
				HoldInventoryEntry action = new HoldInventoryEntry();
		
				Integer version = holdInventoryEntryService.getCurrentVersion(projectNameId, unitSfid, customerId)+1;
				action.setUnitSfid(unitSfid);
				action.setCustomer_id(customerId);
				action.setProject_id(projectNameId);
				action.setStatusai("I");
				action.setHoldstatusyn("N");
				action.setVersion(version);	
				holdInventoryEntryService.updateForelease(action);
		}
		catch (Exception e) {
			log.info("Error :- ",e);
		}
		return gson.toJson("");
	}
	
	
	
	/* Added for Get Inventory */
	@PostMapping(value = "/getInventoryDetails")
	public @ResponseBody String getUnitDtl(@RequestParam("projectId") String projectId, @RequestParam("towerMst") String towerMst, @RequestParam("typoMst") String typoMst, @RequestParam("holdMst") String holdMst, @RequestParam("soldMst") String soldMst, @RequestParam("facing") String facing , @RequestParam("unitAvailable") String unitAvailable) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();
						
			
			List<Inventory> plans=inventoryService.getUnitDtl(projectId, towerMst, typoMst, holdMst, soldMst,facing, unitAvailable);
			
			HashSet<Integer> floor=new HashSet<>();
			ArrayList<ArrayList<Inventory>> mainList = new ArrayList<>();
			if(plans !=null && !plans.isEmpty())
			{
				for(int i=0;i<plans.size();i++) {
					floor.add(Integer.valueOf(plans.get(i).getFloor_number__c()));
				}
				
				List<Integer> list = new ArrayList<>(floor); 
				Collections.sort(list); 
                
                for(int j=0;j<list.size();j++) {
                	ArrayList<Inventory> intList = new ArrayList<>(); 
                	
                	
                	for(int k=0;k<plans.size();k++) {
                		if(list.get(j).toString().equals(plans.get(k).getFloor_number__c())) {
                			
                			if (plans.get(k).getCreated_at() != null && !(plans.get(k).getHoldstatusyn().equals("N"))  && !(plans.get(k).getHoldIntervalstatusAI().equals("I"))  ) {
                    			log.info("Not null Value");
                        		Timestamp currentTpm = new Timestamp(System.currentTimeMillis());
                        		Calendar cal = Calendar.getInstance();
                        		cal.setTimeInMillis(currentTpm.getTime());

                        		// add a bunch of seconds to the calendar
                        		cal.add(Calendar.SECOND, 98765);

                        		// create a second time stamp
                	    		int holdTime = plans.get(k).getHoldForTime();
							/* sysConfigService.getValueAsInt(SysConfigService.HOLD_TIME, projectId); */
                        		Timestamp timestampValue = new Timestamp(plans.get(k).getCreated_at().getTime()+ holdTime);

                        		long milliseconds = timestampValue.getTime() - currentTpm.getTime();
                        		int seconds = (int) milliseconds / 1000;

                        		int hours = seconds / 3600;
                        		int minutes = (seconds % 3600) / 60;
                        		seconds = (seconds % 3600) % 60;

                        		System.out.println("currentTpm: " + currentTpm);
                        		System.out.println("timestampValue: " + timestampValue);

                        		System.out.println("Difference: ");
                        		System.out.println(" Hours: " + hours);
                        		System.out.println(" Minutes: " + minutes);
                        		System.out.println(" Seconds: " + seconds);
                        		
                        		if(timestampValue.compareTo(currentTpm) > 0)
                        		{	
                        			plans.get(k).setFlagForHold("Hold");
                        			plans.get(k).setHoldMin(minutes);
                        			plans.get(k).setHoldSec(seconds);
                        			log.info("Hold IF");
                        		}
                        		else
                        		{
                        			plans.get(k).setFlagForHold("Release");
                        		}
                    			
                    		} else {
                    			
                    		}
                			intList.add(plans.get(k));
                		}	
        			}
                	 mainList.add(intList);
    			}
			}
			return gson.toJson(mainList);
	}
	 /* END Added for Get Inventory */
	
	
	@PostMapping(value = { "/zzholdTesting" })
	public @ResponseBody String rqstAction(@RequestParam("testSFID") String testSFID, @RequestParam("property_on_hold") String property_on_hold , @RequestParam("crm_user") String crm_user) {
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		ZzholdTest action = new ZzholdTest ();
		
		action.setSfid(testSFID);
		action.setPropstrength__property_on_hold_for_reallocation__c(property_on_hold);
		action.setRegistration_crm_user__c(crm_user);
		
				
		zZrequestProcessService.zzupdateRqst(action);
		
		return gson.toJson("");
	}
	
	
	
	@PostMapping(value = { "/zzExist" })
	public @ResponseBody String zzCheckReq (@RequestParam("zzsfid") String zzsfid, @RequestParam("property_on_hold") String property_on_hold , @RequestParam("crm_user") String crm_user) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		
		
		ZzholdTest uDtl = zZExistService.unitExist(zzsfid);
		
		
		if (uDtl != null && !uDtl.equals("")) {
			log.info("Not Null");
			return gson.toJson("Sorry this unit no longer available");
		}else {
			log.info("Null");
			
			
			ZzholdTest action = new ZzholdTest ();
			action.setSfid(zzsfid);
			action.setPropstrength__property_on_hold_for_reallocation__c(property_on_hold);
			action.setRegistration_crm_user__c(crm_user);
			zZrequestProcessService.zzupdateRqst(action);
			
			
			return gson.toJson("New Record");
			
		}

	}
	
	/* END Added For testing */
	

}
