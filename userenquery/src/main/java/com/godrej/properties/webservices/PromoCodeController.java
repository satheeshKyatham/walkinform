package com.godrej.properties.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.master.service.SysConfigService;
import com.godrej.properties.service.EnqJourneyDtlService;
import com.godrej.properties.service.GeneratePaymentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class PromoCodeController {
	private Logger Log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GeneratePaymentService generatePaymentService;
	
	@Autowired
	private EnqJourneyDtlService enqJourneyDtlService;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@Autowired
	private DrupalCreatePromoCode drupalCreatePromoCode;
	
	@Autowired
	private DrupalFetchPromoCode drupalFetchPromoCode;
	
	@RequestMapping(value = "/createPromoCode", method = RequestMethod.POST)
	public String getPaymentReqRecord(@RequestParam("project_sfdc_id") String project_sfdc_id, 
			@RequestParam("promo_code") String promo_code,
			@RequestParam("promo_code_discount_type") String promo_code_discount_type, 
			@RequestParam("flat_discount_amount") String flat_discount_amount,
			@RequestParam("discount_percentage") String discount_percentage,
			@RequestParam("expiry_date") String expiry_date,
			@RequestParam("coupon_discount_upto") String coupon_discount_upto,
			@RequestParam("promocode_max_use_count") String promocode_max_use_count,
			@RequestParam("typology") String typology,
			@RequestParam("status") String status) {
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		try {
			return drupalCreatePromoCode.createUpdatePromoCode(project_sfdc_id, promo_code, promo_code_discount_type, flat_discount_amount, discount_percentage, expiry_date, coupon_discount_upto, promocode_max_use_count, typology, status);
		} catch (Exception e) {
			Log.info("Getting error while creating/updating promo code - Error:- ",e);
			String response = "{\"status\":\"500\",\"msg\":\"Getting error while creating/updating promo code\",\"error_id\":\"ER1001\"}";
			return response;
		}
	}
	
	@RequestMapping(value = "/fetchPromoCode", method = RequestMethod.POST)
	public String getPaymentReqRecord(@RequestParam("project_sfdc_id") String project_sfdc_id) {
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		try {
			return drupalFetchPromoCode.fetchPromoCode(project_sfdc_id);
		} catch (Exception e) {
			Log.info("Getting error while creating/updating promo code - Error:- ",e);
			String response = "[{\"status\":\"500\",\"msg\":\"Getting error while creating/updating promo code\",\"error_id\":\"ER1001\"}]";
			return response;
		}
	}
}