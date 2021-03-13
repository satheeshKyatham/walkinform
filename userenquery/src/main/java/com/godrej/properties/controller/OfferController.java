package com.godrej.properties.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.core.dto.ErrorDto;
import com.godrej.properties.core.dto.Errors;
import com.godrej.properties.dao.TokenDao;
import com.godrej.properties.dto.PaymentDto;
import com.godrej.properties.dto.PaymentRequestDto;
import com.godrej.properties.model.BalanceDetails;
import com.godrej.properties.model.UnitDtl;
import com.godrej.properties.service.AdminUnitHoldStatusService;
import com.godrej.properties.service.BSPUpdateService;
import com.godrej.properties.service.BalanceDetailsService;
import com.godrej.properties.service.HoldInventoryEntryService;
import com.godrej.properties.service.InventoryService;
import com.godrej.properties.service.PropOtherChargesService;
import com.godrej.properties.service.SalesUnitHoldStatusService;
import com.godrej.properties.validator.OfferValidator;
import com.godrej.properties.webservices.CreateOffer;
import com.godrej.properties.webservices.InventoryStatusController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.sf.jasperreports.engine.JRException;

@Controller
public class OfferController {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
 	private BSPUpdateService bSPUpdateService;

	@Autowired
	private InventoryStatusController inventoryStatusController;	
	
	@Autowired
	private CreateOffer creatOffer;	


	@Autowired
	private PropOtherChargesService propOtherChargesService;

	@Autowired
	private BalanceDetailsService balanceDetailsService;

	@Autowired
	private OfferValidator offerValidator;
	
	@Autowired
 	private AdminUnitHoldStatusService adminUnitHoldStatusService;
	
	@Autowired
 	private SalesUnitHoldStatusService salesUnitHoldStatusService;

	@Autowired
	private HoldInventoryEntryService holdInventoryEntryService;
	
	@Autowired	
	private InventoryService inventoryService;
	
	@Autowired
	private TokenDao tokenDao;
	
	@PostMapping(value = { "/updateBSP" })
	public @ResponseBody String updateBSP (@RequestParam("salesConsiderationTotal") double salesConsiderationTotal,  @RequestParam("bspTaxGST") double bspTaxGST,   @RequestParam("bspDis") String bspDis, @RequestParam("token") String token, @RequestParam("projectsfid") String projectsfid,   @RequestParam("enquirysfid") String enquirysfid,  @RequestParam("primarycontactsfid") String primarycontactsfid,   @RequestParam("sentToCrmYN") String sentToCrmYN
			, @RequestParam("timeid") String timeid_str, @RequestParam("propid") String propid, @RequestParam("ppid") String ppid
			, @RequestParam("offerthrough") String offerthrough, @RequestParam("brokersfid") String brokersfid, @RequestParam("discount_Value") String discount_Value_str
			, @RequestParam("balance_amnt") String balanceAmnt,@RequestParam("balance_amnt_description") String balanceAmntDes
			, @RequestParam("car_park_type") String car_park_type,@RequestParam("scheme_rate") String scheme_rate_str,@RequestParam("scheme_name") String scheme_name
			,@RequestParam("userid") String userid,@RequestParam("enquiry_name") String enquiry_name,@RequestParam("costsheet_commitment") String costsheet_commitment
			,@RequestParam("prepaymentamt") String prepaymentamt
			,@RequestParam("bankname") String bankname
			,@RequestParam("trxdate") String trxdate
			,@RequestParam("trxno") String trxno
			,@RequestParam("paymentmode") String paymentmode
			,@RequestParam("tdsPaidBy") String tdsPaidBy
			,@RequestParam("isOthers") boolean isOthers
			
			,@RequestParam("costsheet_path") String costsheet_path
			,@RequestParam("cs_final_amount") double cs_final_amount
			,@RequestParam("bankGL") String bankGL
			,@RequestParam("paymentDetails") String paymentDetails
			,@RequestParam("plannedPayment") Double plannedPayment
			,@RequestParam("plannedPaymentWithTax") Double plannedPaymentWithTax
			,@RequestParam("price") Double price
			,@RequestParam("priceWithTax") Double priceWithTax
			,@RequestParam("tokenTax") Double tokenTax
			,@RequestParam("reraCarpetAreaSqm") double reraCarpetAreaSqm
			) throws JRException, IOException {
		

		String errorMsg1 = KeyConstants.ERROR_MSG_101; //This unit is no longer available please select another unit.
		String errorMsg2 = KeyConstants.ERROR_MSG_102; //Inventory is not activated
		String errorMsg3 = KeyConstants.ERROR_MSG_103; //Yes, There is some technical problem.
		String errorMsg4 = KeyConstants.ERROR_MSG_104;
		String successMsg1 = KeyConstants.SUCCESS_MSG_101; //Offer Successfully Created

		
		BalanceDetails action = new BalanceDetails ();
		
		ObjectMapper mapper =  new ObjectMapper();
		PaymentDto [] payments = mapper.readValue(paymentDetails.getBytes(), PaymentDto[].class);
		
		PaymentRequestDto paymentRequest = new PaymentRequestDto();
		paymentRequest.setPayments(payments);
		paymentRequest.setProjectSfid(projectsfid);
		paymentRequest.setPlannedPayment(plannedPayment);
		paymentRequest.setPlannedPaymentWithTax(plannedPaymentWithTax);
		paymentRequest.setPrice(price);
		paymentRequest.setPriceWithTax(priceWithTax);
		paymentRequest.setTokenTax(tokenTax);
		
		Errors errors=  new Errors();
		offerValidator.validate(paymentRequest, errors);

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		if(errors.getErrorCount()>0) {
			List<ErrorDto> errorList = errors.getErrorList();
			if(errorList != null && !errorList.isEmpty() && errorList.get(0)!=null) {
				ErrorDto error = errorList.get(0);
				String errorMsg = error.getErrorMessage()==null?"":error.getErrorMessage();
				action.setOffer_successMsg(errorMsg);
				return gson.toJson(action);
			}
			action.setOffer_successMsg(errorMsg4);
			return gson.toJson(action);

		}

		
		UnitDtl bspRate = new UnitDtl ();
		// Condtion and validation added for post parameter
		int scheme_rate = 0;
		double discount_Value = 0;
		int timeid = 0;
		if(!"".equals(scheme_rate_str) && !scheme_rate_str.equals("other"))
		{
			scheme_rate=Integer.valueOf(scheme_rate_str);
		}
		if(!"".equals(discount_Value_str))
		{
			 discount_Value = Double.parseDouble(discount_Value_str);
		}
		if(!"".equals(timeid_str))
			timeid=Integer.valueOf(timeid_str);
		
		bspRate.setSenttocrm(sentToCrmYN);
		bspRate.setTimeid(timeid);
		
		bSPUpdateService.updateBSP(bspRate);
		
		
		
		try {
			if (!"".equals(projectsfid) && !"".equals(propid)) {
				//Add by A for check unit status
				String propStatus = inventoryStatusController.inventoryStatus(projectsfid, propid);
				
				JsonElement root = new JsonParser().parse(propStatus);
				JsonArray  jsonArray = root.getAsJsonArray();
				 
				Boolean inventoryStatusCondition = false;
				
				//Boolean adminUnitStatus = adminUnitHoldStatusService.getAdminUnitHold(propid);
				Boolean salesUnitStatus = salesUnitHoldStatusService.getSalesUnitHold(propid, userid);
				
				if (jsonArray.size() > 0) {
					 JsonObject  jsonObject1 = jsonArray.get(0).getAsJsonObject();
					 String propertyoholdforreallocation = jsonObject1.get("propertyoholdforreallocation").getAsString();
					 String PropertyForWebsite = jsonObject1.get("PropertyForWebsite").getAsString();
					 String PropertyForSales = jsonObject1.get("PropertyForSales").getAsString();
					 String PropertyForCP = jsonObject1.get("PropertyForCP").getAsString();
					 String Propertyallotedthroughoffer = jsonObject1.get("Propertyallotedthroughoffer").getAsString();
					 String alloted = jsonObject1.get("alloted").getAsString();
					 String active = jsonObject1.get("active").getAsString();
					 
					 if (propertyoholdforreallocation != null && PropertyForWebsite != null && PropertyForSales != null && PropertyForCP != null && Propertyallotedthroughoffer != null && alloted != null && active != null) {
						 if (active.equals("true")) {
							 if (Propertyallotedthroughoffer.equals("false") && alloted.equals("false")  && salesUnitStatus == false) {
								 inventoryStatusCondition = true;
							 } else {
								 action.setOffer_successMsg(errorMsg1);
								 return gson.toJson(action);
							 }
						 } else {
							 action.setOffer_successMsg(errorMsg2);
							 return gson.toJson(action);
						 }
					 }
				} else {
					log.info(" Create Offer Controller - Yes, There is some technical problem (code:1) ");
					log.info(" Create Offer Controller - Yes, There is some technical problem (code:1) ");
					action.setOffer_successMsg(errorMsg3);
					return gson.toJson(action);
				}
				
				if(inventoryStatusCondition) {
					//Create Offer through SFDC API
					String offerId = creatOffer.PropOffer(bspDis,token,projectsfid,enquirysfid,primarycontactsfid,propid,ppid,offerthrough,brokersfid,discount_Value,enquiry_name,prepaymentamt,bankname,trxdate,trxno,paymentmode,tdsPaidBy,isOthers,bankGL);
					action.setApiError(offerId);		
					JsonObject jobj = new Gson().fromJson(offerId, JsonObject.class);
					String offerid = jobj.get("offerid").getAsString();
					String message = jobj.get("message").getAsString();
					
					//Offer Success
					//Check closing manager tagged or not, if not add no closing manager if yes no required changes
					
					/*JSONObject ob = new JSONObject(offerId);  
					JSONArray arr = ob.getJSONArray("offers");
					String offerid="";

					for(int i=0; i<arr.length(); i++){   
					  JSONObject o = arr.getJSONObject(i);  
					  offerid=o.get("offerId").toString(); 
					}*/
					 
					//Update offer created flag in sfdc property table through HEROKU
					if((offerid!=null && offerid.length()==18)) {
						if("a1l2s000000PKrrAAG".equals(projectsfid) || "a1l2s000000PK3IAAW".equals(projectsfid) || "a1l2s000000PJPmAAO".equals(projectsfid) || "a1l6F000002X6IOQA0".equals(projectsfid) || "a1l6F0000081xb4QAA".equals(projectsfid) || "a1l2s00000000X5AAI".equals(projectsfid) || "a1l2s00000003VlAAI".equals(projectsfid)  || "a1l6F000003TXloQAG".equals(projectsfid) || "a1l2s000000PGu3AAG".equals(projectsfid)  || "a1l2s000000PGu8AAG".equals(projectsfid) || "a1l2s000000PGuDAAW".equals(projectsfid) || "a1l2s000000PGuIAAW".equals(projectsfid) || "a1l2s000000PGuNAAW".equals(projectsfid) || "a1l2s000000PGuSAAW".equals(projectsfid) || "a1l2s000000XoezAAC".equals(projectsfid)) {
							boolean isPMAY = isUnderPMAY(offerid, projectsfid,salesConsiderationTotal, reraCarpetAreaSqm);
							propOtherChargesService.updatePropertyStatus(propid, isPMAY);
						} else {
							propOtherChargesService.updatePropertyStatus(propid);
						}
						tokenDao.updateClosingMangerOnOfferCreation(enquirysfid);
						//Call Update query for clsoing manager update
						
					}
						
					//Insert offer related details in custome table
					
					action.setAmount(balanceAmnt);
					action.setDescription(balanceAmntDes);
					action.setContact_sfid(primarycontactsfid);
					action.setEnquiry_sfid(enquirysfid);
					action.setOffer_sfid(offerid);
					action.setIsactive("A");
					action.setPaymentplan_sfid(ppid);
					action.setCar_park_type(car_park_type);
					action.setScheme_name(scheme_name);
					action.setScheme_rate(scheme_rate);
					action.setProject_sfid(projectsfid);
					action.setCostsheet_commitment(costsheet_commitment);
					action.setCostsheet_path(costsheet_path);
					action.setCs_final_amount(cs_final_amount);
					action.setGst_tax(bspTaxGST);
			
					
					if(userid!=null && userid.length()>0)
					{
						if(!userid.equals("null"))
							action.setUserid(Integer.valueOf(userid));
					}
					action.setOffer_successMsg(successMsg1);
					holdInventoryEntryService.releaseInventory(projectsfid, propid, null);
					inventoryService.releaseInventory(projectsfid, propid, userid, null);
					
					return gson.toJson(balanceDetailsService.insertBalanceDetails(action));
				} else {
					log.info(" Create Offer Controller - Yes, There is some technical problem (code:2) ");
					action.setOffer_successMsg(errorMsg3);
					return gson.toJson(action);
				}
			}
		}
		catch(Exception e){
			log.info("Error :-",e);
		}
		log.info(" Create Offer Controller - Yes, There is some technical problem (code:3) ");
		action.setOffer_successMsg(errorMsg3);
		return gson.toJson(action);

		
	}
	
	private boolean isUnderPMAY(String offerId, String projectSfid, double basicSalePrice, double reraCarpetAreaSqm) {
		if (projectSfid.equals("a1l2s000000PKrrAAG") || projectSfid.equals("a1l2s000000PK3IAAW") ||  projectSfid.equals("a1l2s00000000X5AAI") || projectSfid.equals("a1l6F000003TXloQAG") || projectSfid.equals("a1l2s000000XoezAAC")) {
			return (offerId!=null && offerId.length()==18 && basicSalePrice < 4500000  &&  reraCarpetAreaSqm < 90) ;
		}
		
		if (projectSfid.equals("a1l2s000000PJPmAAO") || projectSfid.equals("a1l6F000002X6IOQA0") || projectSfid.equals("a1l6F0000081xb4QAA") || projectSfid.equals("a1l2s00000003VlAAI") || projectSfid.equals("a1l2s000000PGu3AAG") || projectSfid.equals("a1l2s000000PGu8AAG") || projectSfid.equals("a1l2s000000PGuDAAW") || projectSfid.equals("a1l2s000000PGuIAAW") || projectSfid.equals("a1l2s000000PGuNAAW") || projectSfid.equals("a1l2s000000PGuSAAW")) {
			return (offerId!=null && offerId.length()==18 && basicSalePrice < 4500000  &&  reraCarpetAreaSqm < 60) ;
		}
		
		return false;
	}
}