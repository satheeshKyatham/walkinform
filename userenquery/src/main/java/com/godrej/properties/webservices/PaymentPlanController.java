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
import com.godrej.properties.model.PaymentPlanRanking;
import com.godrej.properties.model.TowerPPExclusion;
import com.godrej.properties.service.PaymentPlanDueService;
import com.godrej.properties.service.PaymentPlanRankingService;
import com.godrej.properties.service.TowerPPExclusionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@Controller
@CrossOrigin(origins="*")
public class PaymentPlanController {
	
	@Autowired
	private PaymentPlanDueService paymentPlanDueService;
	
	@Autowired
	private TowerPPExclusionService towerPPExclusionService;

	@Autowired
	private PaymentPlanRankingService paymentPlanRankingService;
	
	@RequestMapping(value = { "/paymentPlanDue"}, method = RequestMethod.GET)
	public String paymentPlanDue(ModelMap model,HttpServletRequest request) {
		 return "paymentPlanDue";
	}
	@RequestMapping(value = { "/towerPPExclusion"}, method = RequestMethod.GET)
	public String towerPPExclusion(ModelMap model,HttpServletRequest request) {
		 return "towerPPExclusion";
	}
	
	@RequestMapping(value = { "/paymentPlanRanking"}, method = RequestMethod.GET)
	public String paymentPlanRanking(ModelMap model,HttpServletRequest request) {
		 return "paymentPlanRanking";
	}
	@RequestMapping(value = "/savePaymentPlanWithDue", method = RequestMethod.POST,produces = "application/json")
	public @ResponseBody PaymentPlanDue savePaymentPlanWithDues(@RequestBody PaymentPlanDue data) 
	{	
		PaymentPlanDue duePaymentPlan=new PaymentPlanDue();
		if(data != null && data.getTowerid() != null && data.getProject_id() != null){
		duePaymentPlan = paymentPlanDueService.addPaymentPlanDue(data);  /*add payment pLan with due*/
		duePaymentPlan.setInsertStatus("Status_OK");
		return duePaymentPlan;
		}else{
			duePaymentPlan.setInsertStatus("Status_NOTOK");
			return duePaymentPlan;
		}
		
	}
	/* END insert against Payment Plan with Due */
	
	/* Start get Payment Plan with Due*/
	@GetMapping(value = "/getPymentPlanDueList")
	public @ResponseBody String getPymentPlanDueList() 
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		return  gson.toJson(paymentPlanDueService.getPaymentDueList()); /*return json data*/
	}
	/* END  */
	
	
	@RequestMapping(value = "/updatePaymentPlanWithDue", method = RequestMethod.POST)
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
	
	
	@RequestMapping(value = "/saveTowerPPExclusion", method = RequestMethod.POST,produces = "application/json")
	public @ResponseBody String saveTowerPPExclusion(@RequestBody TowerPPExclusion data) 
	{	
		TowerPPExclusion towerPP=new TowerPPExclusion();
		if(data != null && data.getTower_sfid() != null && data.getProject_sfid() != null){
			towerPP = towerPPExclusionService.addTowerPPExclusion(data);  /*add payment pLan with due*/
			towerPP.setInsertStatus("Status_OK");
			String response = "{\"status\":\"STATUS_OK\",\"error_msg\":\"Successfully submitted\"}";
			return response;
		}else{
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\"}";
			return response;
		}
		
	}
	
	/* Start get Tower PP*/
	@GetMapping(value = "/getTowerPPExclusionList")
	public @ResponseBody String getTowerPPExclusionList() 
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		return  gson.toJson(towerPPExclusionService.getTowerPPExclusiondData()); /*return json data*/
	}
	/* END  */
	
	/* Start get Tower PP*/
	@PostMapping(value = "/deleteTowerPPExclusion")
	public @ResponseBody String deleteTowerPPExclusion(@RequestParam("Id") int id) 
	{
		if(id!=0){
			boolean deleteTowerPP=towerPPExclusionService.deleteTowerPPExclusionRecord(id);	
			if(deleteTowerPP==true){
				String response = "{\"status\":\"STATUS_OK\",\"error_msg\":\"Deleted\"}";
				return response;
			}else{
				String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\"}";
				return response;
			}
			 
		}else{
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\"}";
			return response; 
		}
		
		/*return json data*/
	}
	/* END  */
	
	@RequestMapping(value = "/savePaymentPlanRanking", method = RequestMethod.POST,produces = "application/json")
	public @ResponseBody String savePaymentPlanRanking(@RequestBody PaymentPlanRanking data) 
	{	
		PaymentPlanRanking ppRanking=new PaymentPlanRanking();
		if(data != null  && data.getProject_sfid() != null){
			ppRanking = paymentPlanRankingService.addPaymentPlanRanking(data);  /*add payment pLan with ranking*/
			
			String response = "{\"status\":\"STATUS_OK\",\"error_msg\":\"Successfully submitted\"}";
			return response;
		}else{
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\"}";
			return response;
		}
		
	}
	
	//Bulk insert for Payment Plan Ranking
			@RequestMapping(value = "/bulkInsertPaymentRanking", method = RequestMethod.POST, produces = "application/json")
			public String bulkInsertSchemeMapping(@RequestParam("rankingJson") String rankingJson) // add parameter 
			{		
				GsonBuilder gsonBuilder = new GsonBuilder();
				Gson gson = gsonBuilder.create();
				
				String str=rankingJson;
				  
				Object object=null;
				JsonArray arrayObj=null;
				JsonParser jsonParser=new JsonParser();
				object=jsonParser.parse(str);
				arrayObj=(JsonArray) object;
				
				List<PaymentPlanRanking> charges1=new ArrayList<>();
				if(arrayObj!=null && arrayObj.size()>0)
				{
					for(int i=0;i<arrayObj.size();i++) {
						PaymentPlanRanking ecData1= new PaymentPlanRanking();
						ecData1= gson.fromJson(arrayObj.get(i), PaymentPlanRanking.class);
						
						charges1.add(ecData1);
					}
					
					
					//paymentDtlService.insertPaymentDtl(charges1);
					
					paymentPlanRankingService.insertBulkPaymentRanking(charges1);
					
				}
			  	return gson.toJson("");
			}
			//END Bulk insert for Payment Plan Ranking

}
