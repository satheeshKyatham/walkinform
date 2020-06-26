package com.godrej.properties.webservices;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.dto.GPLAppBookingAPIDto;
import com.godrej.properties.dto.GPLAppEnquiryReqAPIDto;
import com.godrej.properties.dto.GPLAppEnquiryRespAPIDto;
import com.godrej.properties.service.EnquiryRequestService;
import com.godrej.properties.serviceimpl.GPLAppsWebServiceImpl;

@RestController
public class GPLAppsWebServiceController {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GPLAppsWebServiceImpl gplAppsWebServiceImpl;
	
	@Autowired
	private EnquiryRequestService enquiryRequestService;
	
	@PostMapping(value = "/d4upreofferAPI", produces = "application/json")
	public String d4upreofferAPI(@RequestBody GPLAppBookingAPIDto bookingDto)
	{
        // Get the all field objects of User class 
        Field[] fields = GPLAppBookingAPIDto.class.getFields(); 
  
        for (int i = 0; i < fields.length; i++) { 
            // get value of the fields 
            Object value;
			try {
				value = fields[i].get(bookingDto);
				 // print result 
				log.info("Input Value of Field {} Value is {}",fields[i].getName(), value);
			} catch (IllegalArgumentException e) {
				log.error("Error IllegalArgumentException :-{}",e);
			} catch (IllegalAccessException e) {
				log.error("Error IllegalAccessException :-{}",e);
			} 
        }
		String returnMesg = "";
		//Call Enquiry SFID to get enquiry PK ID
		GPLAppBookingAPIDto preAPI = null;
		try {
			preAPI = gplAppsWebServiceImpl.insertGPLBookingData(bookingDto);//String timeStamp = new SimpleDateFormat("ddMMyyyy").format(new Date());
			String timeStamp = new SimpleDateFormat("ddMMyyyy").format(new Date());
			if(preAPI.getResp_mesg()!=null && preAPI.getResp_mesg().trim().length()>0)
			{
			//returnMesg="{\"status\":\"200 OK\",\"msg\":\"Success\",\"id\":\""+preAPI.getNv_token_type()+Calendar.getInstance().getTime()+"\"}";
				if(preAPI.getResp_mesg().contains("null"))
				{
					preAPI.setResp_mesg(preAPI.getResp_mesg().replace("null", ""));
				}
					
				returnMesg="{\"status\":\"STATUS_NOTOK\",\"inventory_id\":\""+preAPI.getPropertysfid()+"\",\"contact_id\":\""+preAPI.getContactSfid()+"\",\"offer_id\":\"\",\"sales_email_id\":\"\","
						+ "\"applicant_ids\":[],\"errors\":[{\"error_code\":\"\",\"error_msg\":\""+preAPI.getResp_mesg()+"\"}]}";
			}
			else
			{
				returnMesg="{\"status\":\"STATUS_OK\",\"inventory_id\":\""+preAPI.getPropertysfid()+"\",\"contact_id\":\""+preAPI.getContactSfid()+"\",\"offer_id\":\""+preAPI.getNv_token_type()+timeStamp+"\",\"sales_email_id\":\""+preAPI.getSiteheadEmail()+"\","
						+ "\"applicant_ids\":{\"1st Applicant\":\""+preAPI.getContactSfid()+"\"},\"errors\":\"null\"}";
			}
			log.info("Success Response Mesg {}",returnMesg);
			log.info("Response Mesg {}",returnMesg);
		}
		catch (Exception e) {
			returnMesg="{\"status\":\"STATUS_NOTOK\",\"inventory_id\":\""+preAPI.getPropertysfid()+"\",\"contact_id\":\""+preAPI.getContactSfid()+"\",\"offer_id\":\"\",\"sales_email_id\":\"\","
					+ "\"applicant_ids\":[],\"errors\":[{\"error_code\":\"\",\"error_msg\":\""+preAPI.getResp_mesg()+"\"}]}";
			log.info("Error Response Mesg {}",returnMesg);
			log.error("GPLAppsWebServiceController {}",e);
		}
		return returnMesg;
		//Insert NV_Token table with site head id
		//Site HEad ID pick from Nv_project table
		//Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		//return gson.toJson(auditLogService.insertAuditLog(auditLogDto));	
	}
	
	@PostMapping(value = "/d4uEnquiryprotectionAPI", produces = "application/json")
	public GPLAppEnquiryRespAPIDto d4uEnquiryprotectionAPI(@RequestBody GPLAppEnquiryReqAPIDto enqReqDto)
	{
		
		log.info("Input Value d4uEnquiryprotectionAPI of projectsfid is {} countrycode is {} mobileno is {}",enqReqDto.getProjectsfid(),enqReqDto.getCountrycode(),enqReqDto.getMobileno());
		GPLAppEnquiryRespAPIDto dto = new GPLAppEnquiryRespAPIDto();
		
		if((enqReqDto.getCountrycode()!=null && enqReqDto.getCountrycode().length()>0) && (enqReqDto.getMobileno()!=null && enqReqDto.getMobileno().length()>0)&& (enqReqDto.getProjectsfid()!=null && enqReqDto.getProjectsfid().length()>0))
		{
			try
			{
				List<EnquiryDto> enquiryListDto = enquiryRequestService.getEnquiriesByMobileNo(enqReqDto.getCountrycode(), enqReqDto.getMobileno(), enqReqDto.getProjectsfid(), "");
				
				
				if(enquiryListDto.size()>0)
				{
					//convert to GPLAPPS Format
					try {
						return getGPLAppEnquiryRespDto(enquiryListDto.get(0));
					}
					catch (Exception e) {
						log.error("Error : {}",e);
						dto.setSrc_protection_flag("ERROR");
						dto.setComments("Data Mapping Issue");
						return dto;
					}
				}
				else
				{
					log.info("No Enquiry Found with Given Prameters: Country Code is {}, Mobile No is {}, projectsfid {}",enqReqDto.getCountrycode(),enqReqDto.getMobileno(),enqReqDto.getProjectsfid());
					dto.setSrc_protection_flag("NEW");
					return dto;
				}
			}
			catch (Exception e) {
				log.info("Enquiry Fetching issue with Given Prameters: Country Code is {}, Mobile No is {}, projectsfid {}",enqReqDto.getCountrycode(),enqReqDto.getMobileno(),enqReqDto.getProjectsfid());
				log.error("Error : {}",e);
				dto.setSrc_protection_flag("ERROR");
				dto.setComments("Enquiry Fetching issue");
				return dto;
			}
		}
		else
		{
			dto.setSrc_protection_flag("ERROR");
			dto.setComments("Invalid Input Parameters...");
			return dto;
		}
			
	}
	
	public GPLAppEnquiryRespAPIDto getGPLAppEnquiryRespDto(EnquiryDto enquiryDto)
	{
		GPLAppEnquiryRespAPIDto enqResp = new GPLAppEnquiryRespAPIDto();
		if(enquiryDto.getChannelPartner()!=null)
		{
			enqResp.setBroker_account_sfid(enquiryDto.getChannelPartner().getSfid());
			enqResp.setSource_name(enquiryDto.getChannelPartner().getName());
		}
		else
		{
			enqResp.setBroker_account_sfid("");
			enqResp.setSource_name("");
		}
		enqResp.setClosing_manager_name(enquiryDto.getClosing_manager_name__c());
		enqResp.setComments("");
		if(enquiryDto.getContact()!=null)
			enqResp.setContact_sfid(enquiryDto.getContact().getSfid());
		else
			enqResp.setContact_sfid("");
		enqResp.setDate_of_enquiry(enquiryDto.getDateOfEnquiry().toString());
		enqResp.setDate_of_sitevisit(enquiryDto.getDateOfSiteVisit().toString());
		enqResp.setEmployee_referral_name(enquiryDto.getEmployee_Referral__c());
		enqResp.setEmployee_referral_sfid("");
		enqResp.setEnquiry_name(enquiryDto.getName());
		enqResp.setEnquiry_sfid(enquiryDto.getSfid());
		enqResp.setEnquiry_source(enquiryDto.getEnquirySource());
		enqResp.setEnquiry_type(enquiryDto.getIsReferredByChannelPartner());
		enqResp.setEoi_flag(enquiryDto.getEoi_enquiry__c());
		enqResp.setLoyalty_name(enquiryDto.getContact_Loyalty__c());
		enqResp.setLoyalty_sfid("");
		enqResp.setOther_cp_name(enquiryDto.getOtherChannelPartner());
		enqResp.setProject_name(enquiryDto.getProject().getName());
		enqResp.setProject_sfid(enquiryDto.getProject().getSfid());
		enqResp.setReferral_name(enquiryDto.getContact_referral__c());
		enqResp.setReferral_sfid("");
		enqResp.setReferred_partner_flag(enquiryDto.getIsReferredByChannelPartnerFlag());
		
		if(enquiryDto.getIsReferredByChannelPartnerFlag()!=null && enquiryDto.getIsReferredByChannelPartnerFlag().equals("NSP"))
		{
			enqResp.setSrc_protection_flag("Edit");
		}
		else
			enqResp.setSrc_protection_flag("Source");
		enqResp.setWalkin_source(enquiryDto.getWalkInSource());
		
		if((enquiryDto.getWalkInSource().equals("Digital") || enquiryDto.getWalkInSource().equals("Exhibition") || enquiryDto.getWalkInSource().equals("Newspaper") || enquiryDto.getWalkInSource().equals("Hoarding") || enquiryDto.getWalkInSource().equals("Radio") || enquiryDto.getWalkInSource().equals("Word of mouth") 
				|| enquiryDto.getWalkInSource().equals("SMS")  || enquiryDto.getWalkInSource().equals("Corporate") || enquiryDto.getWalkInSource().equals("Other BTL activities") || enquiryDto.getWalkInSource().equals("Telemarketing")))
		{
			enqResp.setWalkin_source_mobile("Direct");
		}
		else
			enqResp.setWalkin_source_mobile(enquiryDto.getWalkInSource());
			
		return enqResp;
		
	}
}
