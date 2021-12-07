package com.godrej.properties.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.model.HoldParkingAdmin;
import com.godrej.properties.model.Parking;
import com.godrej.properties.model.ParkingAdmin;
import com.godrej.properties.service.AllParkingDataService;
import com.godrej.properties.service.FlatDtlProjectWiseService;
import com.godrej.properties.service.HoldParkingEntryService;
import com.godrej.properties.service.ParkingReportService;
import com.godrej.properties.service.ParkingService;
import com.godrej.properties.service.SalesUnitHoldStatusService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins = "*")
@RestController
public class ParkingController {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private static final int HOLD_TIME=5*60*1000;
	
 	@Autowired
 	private ParkingService parkingService;
	
 	@Autowired
 	private SalesUnitHoldStatusService salesUnitHoldStatusService;
 	
 	@Autowired
 	private HoldParkingEntryService holdParkingEntryService;
 	
 	@Autowired
 	private FlatDtlProjectWiseService flatDtlProjectWiseService;
 	
 	@Autowired
 	private ParkingReportService parkingReportService;
 	
 	@Autowired
 	private AllParkingDataService allParkingDataService;
 	
 	
	/* Added for Get Inventory */
	@PostMapping(value = "/getParkingDetails")
	public @ResponseBody String getUnitDtl(
			@RequestParam("propertyTypeSfid") String propertyTypeSfid, 
			@RequestParam("projectId") String projectId, 
			@RequestParam("towerMst") String towerMst, 
			@RequestParam("unitCategory") String unitCategory,
			@RequestParam("parkingLocation") String parkingLocation) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();
						
			
			Boolean isPlot = true;
			
			/*if (projectId.equals("a1l2s00000003BMAAY") || projectId.equals("a1l2s000000XmaMAAS") || projectId.equals("a1l2s000000PJMJAA4") || projectId.equals("a1l2s000000PKjdAAG")) {
				isPlot = true;
			} else {
				isPlot = false;
			}*/
			
			List<Parking> plans=parkingService.getParkingDtl(propertyTypeSfid, projectId, towerMst, unitCategory, parkingLocation);
			//List<Parking> plans=parkingService.getParkingDtl(propertyTypeSfid, projectId, towerMst, typoMst, holdMst, soldMst,facing, unitAvailable, unitCategory);
			
			HashSet<String> floorName=new HashSet<>();
			HashSet<Integer> floor=new HashSet<>();
			
			ArrayList<ArrayList<Parking>> mainList = new ArrayList<>();
			if(plans !=null && !plans.isEmpty())
			{
				for(int i=0;i<plans.size();i++) {
					if (isPlot) {
						
						if (plans.get(i).getPropstrength__category_of_parking__c() != null) {
							floorName.add(plans.get(i).getPropstrength__category_of_parking__c());
						} else {
							floorName.add("-");
						}
						
						
					} else {
						floor.add(Integer.valueOf(plans.get(i).getPropstrength__category_of_parking__c()));
					}
					
				}
				
				List<String> list = new ArrayList<>();
				List<Integer> listInt = new ArrayList<>();
				 
				
				if (isPlot) {
					list = new ArrayList<>(floorName);
					Collections.sort(list); 
				} else {
					listInt = new ArrayList<>(floor);
					Collections.sort(listInt); 
					
					for (int i = 0; i < listInt.size(); i++) {
						list.add(""+listInt.get(i));
					}
					
				}
				
                String floorData = "";
              
				for(int j=0;j<list.size();j++) {
                	ArrayList<Parking> intList = new ArrayList<>(); 
                	
                	
                	for(int k=0;k<plans.size();k++) {
                		
                		
                		if (isPlot) {
                			if (plans.get(k).getPropstrength__category_of_parking__c() != null) {
                				floorData = plans.get(k).getPropstrength__category_of_parking__c();
                			} else {
                				floorData = "-";
                			}
                			
                		} else {
                			floorData = plans.get(k).getPropstrength__category_of_parking__c();
                		}
                		
                		if(list.get(j).toString().equals(floorData)) {
                			
                			plans.get(k).setPropstrength__category_of_parking__c(floorData);
                			
                			if (plans.get(k).getCreated_at() != null && !(plans.get(k).getHoldstatusyn().equals("N"))  && !(plans.get(k).getHoldIntervalstatusAI().equals("I"))  ) {
                    			log.info("Not null Value");
                        		Timestamp currentTpm = new Timestamp(System.currentTimeMillis());
                        		Calendar cal = Calendar.getInstance();
                        		cal.setTimeInMillis(currentTpm.getTime());

                        		cal.add(Calendar.SECOND, 98765);

                	    		int holdTime = plans.get(k).getHoldForTime();
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
	
	
	/* Added for Get Inventory */
	@PostMapping(value = "/getAdminParkingDetails")
	//@RequestMapping(value = "/getAdminParkingDetails")
	public  String getAdminParkingDetails(
			@RequestParam("propertyTypeSfid") String propertyTypeSfid, 
			@RequestParam("projectId") String projectId, 
			@RequestParam("towerMst") String towerMst, 
			@RequestParam("unitCategory") String unitCategory,
			@RequestParam("parkingLocation") String parkingLocation,
			@RequestParam("searchadminparking") String searchadminparking) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
					
		
		Boolean isPlot = true;
		
		/*if (projectId.equals("a1l2s00000003BMAAY") || projectId.equals("a1l2s000000XmaMAAS") || projectId.equals("a1l2s000000PJMJAA4") || projectId.equals("a1l2s000000PKjdAAG")) {
			isPlot = true;
		} else {
			isPlot = false;
		}*/
		
		
		List<ParkingAdmin> plans=parkingService.getAdminParkingDtl(propertyTypeSfid, projectId, towerMst, unitCategory, parkingLocation, searchadminparking);
		
		HashSet<String> floorName=new HashSet<>();
		HashSet<Integer> floor=new HashSet<>();
		
		ArrayList<ArrayList<ParkingAdmin>> mainList = new ArrayList<>();
		if(plans !=null && !plans.isEmpty())
		{
			for(int i=0;i<plans.size();i++) {
				if (isPlot) {
					
					if (plans.get(i).getPropstrength__category_of_parking__c() != null) {
						floorName.add(plans.get(i).getPropstrength__category_of_parking__c());
					} else {
						floorName.add("-");
					}
					
					
				} else {
					floor.add(Integer.valueOf(plans.get(i).getPropstrength__category_of_parking__c()));
				}
				
			}
			
			List<String> list = new ArrayList<>();
			List<Integer> listInt = new ArrayList<>();
			 
			
			if (isPlot) {
				list = new ArrayList<>(floorName);
				Collections.sort(list); 
			} else {
				listInt = new ArrayList<>(floor);
				Collections.sort(listInt); 
				
				for (int i = 0; i < listInt.size(); i++) {
					list.add(""+listInt.get(i));
				}
				
			}
			
            String floorData = "";
          
			for(int j=0;j<list.size();j++) {
            	ArrayList<ParkingAdmin> intList = new ArrayList<>(); 
            	
            	
            	for(int k=0;k<plans.size();k++) {
            		
            		
            		if (isPlot) {
            			if (plans.get(k).getPropstrength__category_of_parking__c() != null) {
            				floorData = plans.get(k).getPropstrength__category_of_parking__c();
            			} else {
            				floorData = "-";
            			}
            			
            		} else {
            			floorData = plans.get(k).getPropstrength__category_of_parking__c();
            		}
            		
            		if(list.get(j).toString().equals(floorData)) {
            			
            			plans.get(k).setPropstrength__category_of_parking__c(floorData);
            			
            			if (plans.get(k).getCreated_at() != null && !(plans.get(k).getHoldstatusyn().equals("N"))  && !(plans.get(k).getHoldIntervalstatusAI().equals("I"))  ) {
                			  
            				// System.out.println("Not null Value");
    						int holdTime = plans.get(k).getHoldForTime();
    						Timestamp timestampValue = new Timestamp(plans.get(k).getCreated_at().getTime() + holdTime);
    						Timestamp currentTpm = new Timestamp(System.currentTimeMillis());

    						if (timestampValue.compareTo(currentTpm) > 0) {
    							plans.get(k).setFlagForHold("Hold");
    							// System.out.println("Hold IF");
    						} else {
    							plans.get(k).setFlagForHold("Release");
    							// System.out.println("Release ELSE");
    						} 
                    		
                    		 
                			
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
	
	
	/* Added for Get Inventory */
	@PostMapping(value = "/parkingSelection")
	public @ResponseBody String getUnitDtl(@RequestParam("propid") String propid, 
			@RequestParam("parkingsfid") String parkingsfid,
			@RequestParam("projectsfid") String projectsfid,
			@RequestParam("userid") Integer userid) {
	
	//@RequestMapping(value = { "/parkingSelection" }, method = RequestMethod.POST)
	//public String getInventoryStatus(@RequestParam("propid") String propid)
	//		throws JRException, IOException { 
		
		//HoldInventoryEntry salesUnitStatus = salesUnitHoldStatusService.getUnitHoldDtl(propid);
		
		
		String result = parkingService.parkingHold(propid, parkingsfid, projectsfid, userid);
		
		System.out.println("Test");
		//System.out.println(salesUnitStatus.getHoldForTime());
		
		return result;
	}

	
	/* Manual release From Hold Unit */
	@PostMapping(value = { "/parkingReleaseFromHold" })
	public @ResponseBody  String releaseFromHold(@RequestParam("projectsfid") String projectsfid, @RequestParam("userid") Integer userid, @RequestParam("flatsfid") String flatsfid) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		holdParkingEntryService.releaseInventory(projectsfid, userid, flatsfid);
		return gson.toJson("");
	}
	
	/* Parking Hold */
	@RequestMapping(value = "/saveAdminParking", method = RequestMethod.POST)
	public String saveAdminUnit(@RequestParam("projectid") String projectId, @RequestParam("userId") String userId,
			@RequestParam("unitsfid") String unitsfid, @RequestParam("holdmsg") String holdmsg,
			@RequestParam("reasonInput") String reasonInput, 
			@RequestParam("flatsfid") String flatsfid) {

		try {
			parkingService.holdInventoryAdmin(projectId,userId,unitsfid,holdmsg,reasonInput,flatsfid);
		} catch (Exception e) {
			log.error("error", e);
			return "duplicateRecords";
		}
		return "success";
	}
	/* END Parking Hold */
	
	
	@RequestMapping(value = "/getFlatForAdminParkingHold", method = RequestMethod.POST)
	public String getEnqDtl(@RequestParam("flatName") String flatName, @RequestParam("projectSFID") String projectSFID) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();	
			
		return gson.toJson(flatDtlProjectWiseService.getFlatForAdminUnitHold(flatName, projectSFID));
	}
	
	// Admin Release 
	@RequestMapping(value = "/updateAdminParking", method = RequestMethod.POST)
	public String updateAdminUnit(@RequestParam("projectid") String projectId, @RequestParam("userId") String userId,
			@RequestParam("unitsfid") String unitsfid, @RequestParam("unitNames") String unitNames) {

		String[] data = unitsfid.split(",");
		String[] units = unitNames.split(",");
		StringBuilder error = new StringBuilder();

		for (int i = 0; i < data.length; i++) {
			try {
				HoldParkingAdmin parkingAdmin = new HoldParkingAdmin();
				parkingAdmin.setParkingsfid(data[i]);
				parkingAdmin.setProjectsfid(projectId);
				parkingAdmin.setCreated_at(new Timestamp(new Date().getTime()));
				parkingAdmin.setCreated_by(userId);
				parkingAdmin.setHold_reason("Release Admin");
				parkingAdmin.setHold_status(false);

				parkingService.updateHoldParkingAdmin(parkingAdmin);

				// -------------------------------------
				  
			} catch (Exception e) {
				log.error("error", e);
				error.append("/n Problem in releasing Unit - ").append(units[i]);
			}
		}
		String errorMessage = error.toString();
		if (errorMessage != null && !errorMessage.isEmpty()) {
			return errorMessage;
		}
		return "success";
	}
	// END Admin Release
	
	
	@RequestMapping(value = "/getParkingLocation", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getParkingLocation(@RequestParam("towersfid") String towersfid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(parkingService.getLocation(towersfid));
	}
	
	
	@RequestMapping(value = "/getParkingReport", method = RequestMethod.POST)
	public String getEOIReportDtl(@RequestParam("projectSfid") String projectSfid, @RequestParam("towerCode") String towerCode) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition = "";
		
		if(projectSfid!=null && projectSfid.length()>0  && towerCode != null && towerCode.length()>0 && !towerCode.equals("All")) {
			whereCondition = " a.projectsfid= '"+projectSfid+"' AND b.propstrength__tower_code__c = '"+towerCode+"'";
		} else if (projectSfid!=null && projectSfid.length()>0  && towerCode != null && towerCode.length()>0 && towerCode.equals("All") ) {
			whereCondition = " a.projectsfid= '"+projectSfid+"' ";
		}
		
		return gson.toJson(parkingReportService.getParkingReportDtl(whereCondition));
	}
	
	
	
	@RequestMapping(value = "/getAllParkingReport", method = RequestMethod.POST)
	public String getEOIReportDtl(@RequestParam("projectSfid") String projectSfid, @RequestParam("towerCode") String towerCode, @RequestParam("status") String status) {
		
		// For multiple project report
		String [] multiProjectid= towerCode.split(",");
		
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
		// END For multiple project report
		
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String whereCondition = "";
		String statusCondition = "";
		if (status.equals("All")) {
			statusCondition = "";
		}  
		else if (status.equals("SOLD")) {
			statusCondition = " AND a.allotted_through_offer__c = true  AND a.propstrength__allotted__c = true ";
		} else if (status.equals("AVAILABLE")) {
			statusCondition = " AND a.allotted_through_offer__c = false  AND a.propstrength__allotted__c = false   ";
		} else if (status.equals("OFFERED")) {
			statusCondition = " AND a.allotted_through_offer__c = true   AND a.propstrength__allotted__c = false  AND a.propstrength__is_parking_blocked__c = false ";
		} else if (status.equals("OFFEREDSFDCHOLD")) {
			statusCondition = " AND a.propstrength__is_parking_blocked__c = true   AND a.allotted_through_offer__c = true AND a.propstrength__allotted__c = false  ";
		}
		
		
		if(towerCode != null && towerCode.length()>0) {
			whereCondition = " a.propstrength__tower_code__c in ("+finalProjectid+")" + statusCondition;
		} else {
			return gson.toJson(null);
		} 
		
		return gson.toJson(allParkingDataService.getParkingReportDtl(whereCondition));
	}
	
}