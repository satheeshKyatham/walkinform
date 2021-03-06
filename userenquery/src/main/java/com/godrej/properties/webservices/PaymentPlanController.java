package com.godrej.properties.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godrej.properties.model.PaymentPlanDue;
import com.godrej.properties.model.ProjectLaunch;
import com.godrej.properties.model.ProjectPPRanking;
import com.godrej.properties.model.TowerPPExclusion;
import com.godrej.properties.service.PaymentPlanDueService;
import com.godrej.properties.service.PaymentPlanListService;
import com.godrej.properties.service.PaymentPlanRankingService;
import com.godrej.properties.service.ProjectLaunchService;
import com.godrej.properties.service.ProjectPPRankingService;
import com.godrej.properties.service.TowerPPExclusionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@Controller
@CrossOrigin(origins = "*")
public class PaymentPlanController {

	@Autowired
	private PaymentPlanDueService paymentPlanDueService;

	@Autowired
	private TowerPPExclusionService towerPPExclusionService;

	@Autowired
	private ProjectPPRankingService projectPPRankingService;

	@Autowired
	private PaymentPlanListService paymentPlanListService;

	@Autowired
	private PaymentPlanRankingService paymentPlanRankingService;
	
	@Autowired
	private ProjectLaunchService projectLaunchService;
	
	
	@GetMapping(value = { "/paymentPlanDue"})
	public String paymentPlanDue(ModelMap model,HttpServletRequest request) {
		 return "paymentPlanDue";

	}

	@GetMapping(value = { "/towerPPExclusion"})
	public String towerPPExclusion(ModelMap model,HttpServletRequest request) {
		 return "towerPPExclusion";

	}
	
	@GetMapping(value = { "/paymentPlanRanking"})
	public String paymentPlanRank(ModelMap model,HttpServletRequest request) {
		 return "paymentPlanRanking";

	}

	/* start get payment plan list with D4U and CIP active */
	@RequestMapping(value = "/getpaymentPlanWithCIP", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getpaymentPlanWithCIP(@RequestParam("projectcode") String projectcode) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(paymentPlanListService.getpaymentPlanWithCIPActive(projectcode));
	}


/*	 End get payment plan list with D4U and CIP active 
	@RequestMapping(value = "/savePaymentPlanWithDue", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody PaymentPlanDue savePaymentPlanWithDues(@RequestBody PaymentPlanDue data) {
		PaymentPlanDue duePaymentPlan = new PaymentPlanDue();
		if (data != null && data.getTowerid() != null && data.getProject_id() != null) {
			duePaymentPlan = paymentPlanDueService
					.addPaymentPlanDue(data);  add payment pLan with due 
			duePaymentPlan.setInsertStatus("Status_OK");
			return duePaymentPlan;
		} else {*/

	
	/*End get payment plan list with D4U and CIP active*/
	
	@PostMapping(value = "/savePaymentPlanWithDue",produces = "application/json")
	public @ResponseBody PaymentPlanDue savePaymentPlanWithDues(@RequestBody PaymentPlanDue data) 
	{	
		PaymentPlanDue duePaymentPlan=new PaymentPlanDue();
		if(data != null && data.getTowerid() != null && data.getProject_id() != null){
		String paymentPlanLineItems_str = paymentPlanDueService.getPaymentDueList(data.getProject_id(),data.getTowerid(),data.getPymt_plan_id());
		ProjectLaunch regionData=projectLaunchService.getprojectDetails(data.getProject_id());
		data.setRegion_id(regionData.getRegionname());
		data.setRegion_name(regionData.getRegionname());
			if(paymentPlanLineItems_str==null)
			{
				
				duePaymentPlan = paymentPlanDueService.addPaymentPlanDue(data);  /*add payment pLan with due*/
				duePaymentPlan.setInsertStatus("Status_OK");
				return duePaymentPlan;
			}
			else
			{
				return paymentPlanDueService.updatePaymentDue(data);
			}
		}else{

			duePaymentPlan.setInsertStatus("Status_NOTOK");
			return duePaymentPlan;
		}

	}
	/* END insert against Payment Plan with Due */

	/* Start get Payment Plan with Due */
	@GetMapping(value = "/getPymentPlanDueList")
	public @ResponseBody String getPymentPlanDueList() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		return  gson.toJson(paymentPlanDueService.getPaymentDueList("","","")); /*return json data*/

	}

	/* END */
	
	
	@PostMapping(value = "/updatePaymentPlanWithDue")
	public @ResponseBody PaymentPlanDue updatePaymentPlanWithDue(@RequestBody PaymentPlanDue data)  {	
		PaymentPlanDue duePaymentPlan=new PaymentPlanDue();
		if(data != null && data.getId() != 0 && data.getTowerid() != null){
		duePaymentPlan = paymentPlanDueService.updatePaymentDue(data);  /*add payment pLan with due*/
		duePaymentPlan.setInsertStatus("Status_OK");
		return duePaymentPlan;
		}else{

			duePaymentPlan.setInsertStatus("Status_NOTOK");
			return duePaymentPlan;
		}
	}

	
	
	@PostMapping(value = "/saveTowerPPExclusion",produces = "application/json")
	public @ResponseBody String saveTowerPPExclusion(@RequestBody TowerPPExclusion data) 
	{	
		TowerPPExclusion towerPP=new TowerPPExclusion();
		/*ProjectLaunch regionData=projectLaunchService.getprojectDetails(data.getProject_sfid());
		data.setRegion_id(regionData.getRegionname());
		data.setRegion_name(regionData.getRegionname());*/
		if(data != null && data.getTower_sfid() != null && data.getProject_sfid() != null){
			boolean towerExist=towerPPExclusionService.getTowerPP(data);
			if(towerExist){
				String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Already Exist\"}";
				return response;
			} else {
				towerPP = towerPPExclusionService.addTowerPPExclusion(
						data); /* add payment pLan with due */
				towerPP.setInsertStatus("Status_OK");
				String response = "{\"status\":\"STATUS_OK\",\"error_msg\":\"Successfully submitted\"}";
				return response;
			}
		} else {
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\"}";
			return response;
		}

	}

	/* Start get Tower PP */
	@GetMapping(value = "/getTowerPPExclusionList")
	public @ResponseBody String getTowerPPExclusionList() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		return gson.toJson(towerPPExclusionService
				.getTowerPPExclusiondData()); /* return json data */
	}
	/* END */
	
	@PostMapping(value="/deleteTowerPPExclusion")
	public @ResponseBody String deleteTowerPPExclusion(@RequestParam("Id") int id) 
	{
		if(id!=0){
			boolean deleteTowerPP=towerPPExclusionService.deleteTowerPPExclusionRecord(id);	
			if(deleteTowerPP){
				return "{\"status\":\"STATUS_OK\",\"error_msg\":\"Deleted\"}";
			}else{
				return "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\"}";

			}
		} else {
			return "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\"}";
		}
	}


	
	@PostMapping(value = "/savePaymentPlanRanking",produces = "application/json")
	public @ResponseBody String savePaymentPlanRanking(@RequestBody ProjectPPRanking data) 
	{	
	
		if(data != null  && data.getProject_sfid() != null){
			 projectPPRankingService.addPaymentPlanRanking(data);  /*add payment pLan with ranking*/
			return "{\"status\":\"STATUS_OK\",\"error_msg\":\"Successfully submitted\"}";
		}else{
			return "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\"}";
		}

	}


	@PostMapping(value = "/bulkInsertPaymentRanking", produces = "application/json")
	public @ResponseBody String bulkInsertSchemeMapping(@RequestParam("rankingJson") String rankingJson) // add parameter 
	{		
				GsonBuilder gsonBuilder = new GsonBuilder();
				Gson gson = gsonBuilder.create();
				
				String str=rankingJson;
				  
				Object object=null;
				JsonArray arrayObj=null;
				JsonParser jsonParser=new JsonParser();
				object=jsonParser.parse(str);
				arrayObj=(JsonArray) object;
				
				/*List<PaymentPlanRanking> charges1=new ArrayList<>();
				if(arrayObj!=null && arrayObj.size()>0)
				{
					for(int i=0;i<arrayObj.size();i++) {
						PaymentPlanRanking ecData1= new PaymentPlanRanking();
						ecData1= gson.fromJson(arrayObj.get(i), PaymentPlanRanking.class);
						charges1.add(ecData1);
					}
					String msg="";
					try{
						 msg=paymentPlanRankingService.insertBulkPaymentRanking(charges1);	
					}catch(Exception e){
						msg="STATUS_NOTOK";
						return gson.toJson(msg);	
					}
				}*/
				List<ProjectPPRanking> charges1=new ArrayList<>();
				if(arrayObj!=null && arrayObj.size()>0)
				{
					for(int i=0;i<arrayObj.size();i++) {
						ProjectPPRanking ecData1= new ProjectPPRanking();
						ecData1= gson.fromJson(arrayObj.get(i), ProjectPPRanking.class);
						charges1.add(ecData1);
					}
					String msg="";
					try{
						 msg=projectPPRankingService.insertBulkPaymentRanking(charges1);	
					}catch(Exception e){
						msg="STATUS_NOTOK";
						return gson.toJson(msg);	
					}
				}
			  	return gson.toJson("");

			}
	
	/* END Bulk insert for Payment Plan Ranking */

	/* Start get payment plan with ranking */
	@GetMapping(value = "/getProjectPPRanking", produces = "application/json")
	public @ResponseBody String getProjectPPRankingList(@RequestParam("projectcode") String projectcode) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(paymentPlanRankingService.getPaymentPlanRankingList(projectcode));
	}
	/* END */

}
