package com.godrej.properties.webservices;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.godrej.properties.common.dto.CustomCPResponseDto;
import com.godrej.properties.common.dto.CustomResponseDto;
import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.constants.MessageConstants;
import com.godrej.properties.controller.EnquiryRequestController;
import com.godrej.properties.dto.CPContactEnquiryCreateUpdateReqDto;
import com.godrej.properties.dto.CPEOIRespAPIDto;
import com.godrej.properties.dto.CPEnquiryReqAPIDto;
import com.godrej.properties.dto.CPEnquiryRespAPIDto;
import com.godrej.properties.dto.CP_Contact_Enquery_Response;
import com.godrej.properties.dto.ContactDto;
import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.dto.GPLAppBookingAPIDto;
import com.godrej.properties.dto.GPLAppEnquiryReqAPIDto;
import com.godrej.properties.dto.GPLAppEnquiryRespAPIDto;
import com.godrej.properties.master.dto.ReferenceListDto;
import com.godrej.properties.master.service.ReferenceListService;
import com.godrej.properties.model.CPAPP_EnquiryRequest;
import com.godrej.properties.model.Contact;
import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.model.GeneratePayment;
import com.godrej.properties.model.ProjectLaunch;
import com.godrej.properties.service.CPAPP_Service;
import com.godrej.properties.service.EOIReportService;
import com.godrej.properties.service.EnquiryRequestService;
import com.godrej.properties.service.GeneratePaymentService;
import com.godrej.properties.service.ProjectLaunchService;
import com.godrej.properties.service.UserContactService;
import com.godrej.properties.serviceimpl.GPLAppsWebServiceImpl;
import com.godrej.properties.util.CommonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@CrossOrigin(origins = "*")
@RestController
public class CPWebServices {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ServletContext context;

	

	@Autowired
	private GeneratePaymentService generatePaymentService;
	
	@Autowired
	private EnquiryRequestService enquiryRequestService;

	@Autowired
	private UserContactService userContactService;

	@Autowired
	private ProjectLaunchService projectLaunchService;

	@Autowired
	private ReferenceListService referenceListService;

	@Autowired
	private CPAPP_Service cpapp_Service;

	@Autowired
	private EOIReportService eOIReportService;
	
	@Autowired
	private GPLAppsWebServiceImpl gplAppsWebServiceImpl;
	
	@RequestMapping(value = "/validateEnquiryExist", method = RequestMethod.GET, produces = "application/json")
	public String validateEnquiryExist(@RequestParam("countryCode") String countryCode,
			@RequestParam("mobileno") String mobileNo, @RequestParam("projectid") String projectSfid) {
		CustomResponseDto resp = new CustomResponseDto();
		CustomResponseDto customResponseDto = resp;
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		List<EnquiryDto> enquiryList = null;
		try {
			enquiryList = enquiryRequestService.getEnquiriesByMobileNo(countryCode, mobileNo, projectSfid, "");// countryCode,mobileNo,projectSfid
			ContactDto contact = null;
			if (CommonUtil.isCollectionEmpty(enquiryList)) {
				contact = userContactService.findMobileNoExist(countryCode, mobileNo);
			}
			resp.addObject("enquiryList", enquiryList);
			resp.addObject("contact", contact);
			resp.setSuccess(true);
			resp.setMessage(MessageConstants.ENQUIRY_GET_SUCCESS);
			log.info(MessageConstants.ENQUIRY_GET_SUCCESS);
			if (enquiryList.size() == 0) {

			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			log.error(MessageConstants.ENQUIRY_GET_FAILED);
			ex.printStackTrace();
			resp.setSuccess(false);
			resp.setMessage(MessageConstants.ENQUIRY_GET_FAILED);
		}

		return gson.toJson(customResponseDto);

	}

	@RequestMapping(value = "/validateCPEnquiryExist", method = RequestMethod.GET, produces = "application/json")
	public String validateCPEnquiryExist(@RequestParam("custname") String custname,
			@RequestParam("countryCode") String countryCode, @RequestParam("mobileno") String mobileNo,
			@RequestParam("projectid") String projectSfid, @RequestParam("emailid") String emailid,
			@RequestParam("brokeraccid") String brokeraccid, HttpServletRequest request) {
		CustomCPResponseDto resp = new CustomCPResponseDto();
		CustomCPResponseDto customResponseDto = resp;
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		List<EnquiryDto> enquiryList = null;
		try {
			// enquiryList=enquiryRequestService.getEnquiriesByMobileNo("+"+mobileNo,projectSfid);
			enquiryList = enquiryRequestService.getEnquiriesByCPAppWithParam(custname, "+" + countryCode, mobileNo,
					projectSfid, emailid);
			if (!enquiryList.isEmpty()) {
				if (enquiryList.get(0).getChannelPartner().getSfid().equals(brokeraccid)) {
					System.out.println("Entry found");
					resp.setSuccess(true);
					resp.setMessage(MessageConstants.ENQUIRY_GET_SUCCESS);
					String resultPath = request.getScheme() + "://" + request.getHeader("Host")
							+ request.getContextPath();
					resp.setUrl(resultPath + "/usercpinfo/+91/" + enquiryList.get(0).getContact().getMobile() + "/"
							+ enquiryList.get(0).getProject().getSfid() + "/"
							+ enquiryList.get(0).getProject().getPname() + "/42/D");
				} else {
					System.out.println("The LEAD IS ALREADY CREATED");
					resp.setSuccess(false);
					resp.setMessage("The LEAD IS ALREADY CREATED");
					resp.setUrl("");
				}
			} else {
				System.out.println("The LEAD IS ALREADY CREATED");
				resp.setSuccess(false);
				resp.setMessage("The LEAD IS ALREADY CREATED");
				resp.setUrl("");
			}
			ContactDto contact = null;
			if (CommonUtil.isCollectionEmpty(enquiryList)) {
				contact = userContactService.findMobileNoExist(countryCode, mobileNo);
			}
			resp.addObject("enquiryList", enquiryList);
			resp.addObject("contact", contact);

			log.info(MessageConstants.ENQUIRY_GET_SUCCESS);
			if (enquiryList.size() == 0) {

			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			log.error(MessageConstants.ENQUIRY_GET_FAILED);
			ex.printStackTrace();
			resp.setSuccess(false);
			resp.setMessage(MessageConstants.ENQUIRY_GET_FAILED);
		}

		return gson.toJson(customResponseDto);

	}

	@RequestMapping(value = "/usercpinfo/{countrycode}/{mobileno}/{projectid}/{projectname}/{userid}/{enquiryType}", method = RequestMethod.GET)
	public ModelAndView login(@PathVariable("countrycode") String countrycode,
			@PathVariable("mobileno") String mobileno, @PathVariable("projectid") String projectid,
			@PathVariable("projectname") String projectname, @PathVariable("userid") Integer userid,
			@PathVariable("enquiryType") String enquiryType) {
		ModelAndView view = new ModelAndView("enquiryCPInfo");
		boolean hasParam = true;
		view.addObject("hasParam", hasParam);
		/* view.addObject("viewType","basicInfoWithParam"); */
		view.addObject("mobileNo", mobileno);
		view.addObject("countryCode", countrycode);
		view.addObject("projectSfid", projectid);
		view.addObject("userId", userid);
		view.addObject("projectName", projectname);
		view.addObject("enquiryType", enquiryType);
		view.addObject("recordTypeProspect", KeyConstants.RECORD_TYPE_PROSPECT);
		view.addObject("recordTypeCustomer", KeyConstants.RECORD_TYPE_CUSTOMER);
		ProjectLaunch projectLaunch = projectLaunchService.getProjectSaleMgrID(projectid);
		if (projectLaunch != null) {
			view.addObject("AssignTO", projectLaunch.getSalesmanager_sfid());
		} else {
			// TODO RETURN Error Page
		}
		List<ReferenceListDto> communcationMediumList = referenceListService
				.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_COMMUNICATION_MEDIUM);
		view.addObject("communcationMediumList", communcationMediumList);

		return view;
	}

	@GetMapping("/getExistingCPRecords/{countryCode}/{mobileno}/{projectid}")
	@ResponseBody
	public CustomResponseDto getExistingEnquiry(@PathVariable("countryCode") String countryCode,
			@PathVariable("mobileno") String mobileNo, @PathVariable("projectid") String projectSfid) {
		CustomResponseDto resp = new CustomResponseDto();
		try {
			List<EnquiryDto> enquiryList = enquiryRequestService.getEnquiriesByMobileNo(countryCode, mobileNo,
					projectSfid, "");
			ContactDto contact = null;
			if (CommonUtil.isCollectionEmpty(enquiryList)) {
				contact = userContactService.findMobileNoExist(countryCode, mobileNo);
			} else {
				contact = enquiryList.get(0).getContact();
			}
			resp.addObject("enquiryList", enquiryList);
			resp.addObject("contact", contact);
			resp.setSuccess(true);
			resp.setMessage(MessageConstants.ENQUIRY_GET_SUCCESS);
			log.info(MessageConstants.ENQUIRY_GET_SUCCESS);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			log.error(MessageConstants.ENQUIRY_GET_FAILED);
			ex.printStackTrace();
			resp.setSuccess(false);
			resp.setMessage(MessageConstants.ENQUIRY_GET_FAILED);
		}
		return resp;
	}

	// Balram Asati

	@PostMapping(value = "/getCPSourceProtection", produces = "application/json")
	public CPEnquiryRespAPIDto getCPSourceProtection(@RequestBody CPEnquiryReqAPIDto enqReqDto) {

		log.info("Input Value getCPSourceProtection of projectsfid is {} countrycode is {} mobileno is {}",
				enqReqDto.getProjectsfid(), enqReqDto.getCountrycode(), enqReqDto.getMobileno());
		CPEnquiryRespAPIDto dto = new CPEnquiryRespAPIDto();

		if ((enqReqDto.getCountrycode() != null && enqReqDto.getCountrycode().length() > 0)
				&& (enqReqDto.getMobileno() != null && enqReqDto.getMobileno().length() > 0)
				&& (enqReqDto.getProjectsfid() != null && enqReqDto.getProjectsfid().length() > 0)) {
			try {
				List<EnquiryDto> enquiryListDto = enquiryRequestService.getEnquiriesByMobileNo(
						enqReqDto.getCountrycode(), enqReqDto.getMobileno(), enqReqDto.getProjectsfid(), "");

				if (enquiryListDto.size() > 0) {
					// convert to GPLAPPS Format
					try {
						return getGPLAppEnquiryRespDto(enquiryListDto.get(0), enqReqDto.getBrokerId());

					} catch (Exception e) {
						log.error("Error : {}", e);
						dto.setSrc_protection_flag("Error");
						dto.setComments("Data Mapping Issue");
						return dto;
					}
				} else {
					log.info(
							"No Enquiry Found with Given Prameters: Country Code is {}, Mobile No is {}, projectsfid {}",
							enqReqDto.getCountrycode(), enqReqDto.getMobileno(), enqReqDto.getProjectsfid());
					dto.setSrc_protection_flag("New");
					return dto;
				}
			} catch (Exception e) {
				log.info(
						"Enquiry Fetching issue with Given Prameters: Country Code is {}, Mobile No is {}, projectsfid {}",
						enqReqDto.getCountrycode(), enqReqDto.getMobileno(), enqReqDto.getProjectsfid());
				log.error("Error : {}", e);
				dto.setSrc_protection_flag("Error");
				dto.setComments("Enquiry Fetching issue");
				return dto;
			}
		} else {
			dto.setSrc_protection_flag("Error");
			dto.setComments("Invalid Input Parameters...");
			return dto;
		}

	}

	public CPEnquiryRespAPIDto getGPLAppEnquiryRespDto(EnquiryDto enquiryDto, String brokerId) {
		CPEnquiryRespAPIDto enqResp = new CPEnquiryRespAPIDto();
		if (enquiryDto.getChannelPartner() != null) {
			enqResp.setBroker_account_sfid(enquiryDto.getChannelPartner().getSfid());
			enqResp.setSource_name(enquiryDto.getChannelPartner().getName());
		} else {
			enqResp.setBroker_account_sfid("");
			enqResp.setSource_name("");
		}
		enqResp.setClosing_manager_name(enquiryDto.getClosing_manager_name__c());
		enqResp.setComments("");
		if (enquiryDto.getContact() != null) {
			enqResp.setContact_sfid(enquiryDto.getContact().getSfid());
			enqResp.setContact_firstname(enquiryDto.getContact().getFirstName());
			enqResp.setContact_emailid(enquiryDto.getContact().getEmail());
			enqResp.setContact_countrycode(enquiryDto.getContact().getCountryCode());
			enqResp.setContact_id("" + enquiryDto.getContact().getContactId());
			enqResp.setContact_lastname(enquiryDto.getContact().getLastName());
			enqResp.setContact_mobileno(enquiryDto.getContact().getMobileNo());
		} else {
			enqResp.setContact_sfid("");
		}
		if (enquiryDto.getDateOfEnquiry() != null)
			enqResp.setDate_of_enquiry(enquiryDto.getDateOfEnquiry().toString());
		else
			enqResp.setDate_of_enquiry("");
		if (enquiryDto.getDateOfSiteVisit() != null)
			enqResp.setDate_of_sitevisit(enquiryDto.getDateOfSiteVisit().toString());
		else
			enqResp.setDate_of_sitevisit("");
		enqResp.setEmployee_referral_name("");
		enqResp.setEmployee_referral_sfid(enquiryDto.getEmployee_Referral__c());
		enqResp.setEnquiry_name(enquiryDto.getName());
		enqResp.setEnquiry_sfid(enquiryDto.getSfid());
		enqResp.setEnquiry_source(enquiryDto.getEnquirySource());
		enqResp.setEnquiry_type(enquiryDto.getIsReferredByChannelPartner());
		enqResp.setEoi_flag(enquiryDto.getEoi_enquiry__c());
		if (enquiryDto.getContact_Loyalty__c() != null) {
			ContactDto contactLoyalty = userContactService.getContactBySfid(enquiryDto.getContact_Loyalty__c());
			enqResp.setLoyalty_name(contactLoyalty.getFirstName() + " " + contactLoyalty.getLastName());
		}
		enqResp.setLoyalty_sfid(enquiryDto.getContact_Loyalty__c());
		enqResp.setOther_cp_name(enquiryDto.getOtherChannelPartner());
		enqResp.setProject_name(enquiryDto.getProject().getName());
		enqResp.setProject_sfid(enquiryDto.getProject().getSfid());
		if (enquiryDto.getContact_referral__c() != null) {
			ContactDto contactReferral = userContactService.getContactBySfid(enquiryDto.getContact_referral__c());
			enqResp.setReferral_name(contactReferral.getFirstName() + " " + contactReferral.getLastName());

		}
		enqResp.setReferral_sfid(enquiryDto.getContact_referral__c());
		enqResp.setReferred_partner_flag(enquiryDto.getIsReferredByChannelPartnerFlag());

		if ((enquiryDto.getIsReferredByChannelPartnerFlag() != null
				&& enquiryDto.getIsReferredByChannelPartnerFlag().equals("NSP")) || enquiryDto.getWalkInSource() == null
		/*
		 * || (enquiryDto.getEmployee_Referral__c()!=null &&
		 * enquiryDto.getEmployee_Referral__c().equals(KeyConstants.
		 * ENQUIRY_EMPLOYEE_SFID)) || (enquiryDto.getContact_referral__c()!=null &&
		 * enquiryDto.getContact_referral__c().equals(KeyConstants.ENQUIRY_REFERRAL_SFID
		 * )) || (enquiryDto.getContact_Loyalty__c()!=null &&
		 * enquiryDto.getContact_Loyalty__c().equals(KeyConstants.
		 * ENQUIRY_EXISTINGCUSTOMER_SFID))
		 */

		/*
		 * || (enquiryDto.getWalkInSource()!=null &&
		 * enquiryDto.getWalkInSource().equals("Referral")) ||
		 * (enquiryDto.getWalkInSource()!=null &&
		 * enquiryDto.getWalkInSource().equals("Godrej Employee")) ||
		 * (enquiryDto.getWalkInSource()!=null &&
		 * enquiryDto.getWalkInSource().equals("Existing Customer"))
		 */

		) {
			enqResp.setSrc_protection_flag("Edit");
		} else
			enqResp.setSrc_protection_flag("Source");
		enqResp.setWalkin_source(enquiryDto.getWalkInSource());

		if (enquiryDto.getWalkInSource() != null) {
			if ((enquiryDto.getWalkInSource().equals("Digital") || enquiryDto.getWalkInSource().equals("Exhibition")
					|| enquiryDto.getWalkInSource().equals("Newspaper")
					|| enquiryDto.getWalkInSource().equals("Hoarding") || enquiryDto.getWalkInSource().equals("Radio")
					|| enquiryDto.getWalkInSource().equals("Word of mouth")
					|| enquiryDto.getWalkInSource().equals("SMS") || enquiryDto.getWalkInSource().equals("Corporate")
					|| enquiryDto.getWalkInSource().equals("Other BTL activities")
					|| enquiryDto.getWalkInSource().equals("Telemarketing")
					|| enquiryDto.getWalkInSource().contains("Affiliate Sales")
					|| enquiryDto.getWalkInSource().contains("Employee")
					|| enquiryDto.getWalkInSource().contains("VDNB Referral"))) {
				enqResp.setWalkin_source_mobile("Direct");
			} else if (enquiryDto.getWalkInSource().equals("Existing Customer")) {
				enqResp.setWalkin_source_mobile("Loyalty");
			} else
				enqResp.setWalkin_source_mobile(enquiryDto.getWalkInSource());
		} else
			enqResp.setWalkin_source_mobile("");

		// Condition Add for broker id same as input
		if (brokerId.equals(enqResp.getBroker_account_sfid())) {
			enqResp.setIs_same_broker("True");
		} else {
			enqResp.setIs_same_broker("False");
		}

		return enqResp;

	}

	@PostMapping(value = "CPContactEnquiryCreateUpdate", produces = "application/json")
	public String CPContactEnquiryCreateUpdate(
			@RequestBody CPContactEnquiryCreateUpdateReqDto cpContactEnquiryCreateUpdateReqDto) {
		CPEnquiryRespAPIDto enqResp = new CPEnquiryRespAPIDto();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		try {
			Contact contact = new Contact();
			CPAPP_EnquiryRequest cpapp_EnquiryRequest = new CPAPP_EnquiryRequest();
			// validate contact if
			if (cpContactEnquiryCreateUpdateReqDto.getContact_sfid() != null
					&& !"".equals(cpContactEnquiryCreateUpdateReqDto.getContact_sfid())) {
				// Existing Contact
				cpapp_EnquiryRequest
						.setPropstrength__primary_contact__c(cpContactEnquiryCreateUpdateReqDto.getContact_sfid());
			} else {
				// Create Contact

				contact.setMobileNo(cpContactEnquiryCreateUpdateReqDto.getCust_mobile());
				contact.setFirstName(cpContactEnquiryCreateUpdateReqDto.getCust_first_name());
				contact.setLastName(cpContactEnquiryCreateUpdateReqDto.getCust_last_name());
				contact.setEmail(cpContactEnquiryCreateUpdateReqDto.getCust_emailId());
				contact.setAddressLine1(cpContactEnquiryCreateUpdateReqDto.getCommunication_address1());
				contact.setAddressLine2(cpContactEnquiryCreateUpdateReqDto.getCommunication_address2());
				contact.setAddressLine3(cpContactEnquiryCreateUpdateReqDto.getCommunication_address3());
				contact.setCity(cpContactEnquiryCreateUpdateReqDto.getCommunication_city());
				contact.setPinCode(cpContactEnquiryCreateUpdateReqDto.getCommunication_pin());
				contact.setResidentialCountry(cpContactEnquiryCreateUpdateReqDto.getCommunication_country());
				cpapp_Service.createContact(contact);
				cpapp_EnquiryRequest.setExternal_contact_id__c(contact.getContactId());
			}

			cpapp_EnquiryRequest.setDate_of_Site_Visit__c(new Date());
			cpapp_EnquiryRequest
					.setPropStrength__Broker_Account__c(cpContactEnquiryCreateUpdateReqDto.getBroker_sfid());
			cpapp_EnquiryRequest.setPropstrength__enquiry_type__c("Partner");
			cpapp_EnquiryRequest.setPropstrength__request_source__c("Digital");
			cpapp_EnquiryRequest.setWalk_In_Source__c("Channel Partner");

			if (cpContactEnquiryCreateUpdateReqDto.getEnquiry_sfid() != null
					&& !"".equals(cpContactEnquiryCreateUpdateReqDto.getEnquiry_sfid())) {
				// Existing Enquiry
				// Update Enquiry
				cpapp_EnquiryRequest.setSfid(cpContactEnquiryCreateUpdateReqDto.getEnquiry_sfid());
				cpapp_Service.updateEnquery_CPAPP(cpapp_EnquiryRequest);
			} else {
				// Create New Enquiry
				cpapp_Service.createEnquery_CPAPP(cpapp_EnquiryRequest);
			}

			CP_Contact_Enquery_Response contact_Enquery_Response = new CP_Contact_Enquery_Response();
			contact_Enquery_Response.setContact(contact);
			contact_Enquery_Response.setCpapp_EnquiryRequest(cpapp_EnquiryRequest);
			return gson.toJson(contact_Enquery_Response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("Error : {}", e);
		}
		return "";

	}

	@PostMapping(value = "CPD4UGeneratePaymentLink", produces = "application/json")
	public String CP_Update_Enquery_Status(
			@RequestBody CPContactEnquiryCreateUpdateReqDto cpContactEnquiryCreateUpdateReqDto,
			HttpServletRequest request) {
		CPEnquiryRespAPIDto enqResp = new CPEnquiryRespAPIDto();
		try {

			// this json generate default date in generate link
			// below method consume which is used in d4u ,generate payment like using below
			// parameters
			String json = "[{\"amount\":\"0.0\",\"transaction_date_string\":\"  \",\"description\":\"\"}]";
			String status = insertPaymentRequest(json,
					cpContactEnquiryCreateUpdateReqDto.getLoginUser_id(),
					cpContactEnquiryCreateUpdateReqDto.getEnquiry_sfid(),
					cpContactEnquiryCreateUpdateReqDto.getEnquiry_name(),
					cpContactEnquiryCreateUpdateReqDto.getProject_sfid(),
					cpContactEnquiryCreateUpdateReqDto.getProject_name(),
					cpContactEnquiryCreateUpdateReqDto.getCust_mobile(),
					cpContactEnquiryCreateUpdateReqDto.getLoginemail_id(),
					cpContactEnquiryCreateUpdateReqDto.getLoginUser_name(),
					cpContactEnquiryCreateUpdateReqDto.getCust_name(),
					cpContactEnquiryCreateUpdateReqDto.getCust_emailId(),
					cpContactEnquiryCreateUpdateReqDto.getTowercode(),
					cpContactEnquiryCreateUpdateReqDto.getTowersfid(),
					cpContactEnquiryCreateUpdateReqDto.getRequestsource(), request);
			
			
			
			 			
			log.info("Status : {}", status);

			if (!status.contains("STATUS_OK"))
				return "{status:failed}";

			if (cpContactEnquiryCreateUpdateReqDto.getEnquiry_sfid() != null
					&& !"".equals(cpContactEnquiryCreateUpdateReqDto.getEnquiry_sfid())) {

				// Update Enquiry
				CPAPP_EnquiryRequest cpapp_EnquiryRequest = new CPAPP_EnquiryRequest();
				cpapp_EnquiryRequest.setPropStrength__Request_Status__c("Transaction in Progress");
				cpapp_EnquiryRequest.setSfid(cpContactEnquiryCreateUpdateReqDto.getEnquiry_sfid());
				cpapp_Service.updateEnquery_status_CPAPP(cpapp_EnquiryRequest);
				return "{status:success}";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("Error : {}", e);
			return "{status:failed}";

		}
		return "{status:success}";

	}

	@PostMapping(value = "CPGetPastEOISubmissions", produces = "application/json")
	public String CPGetPastEOISubmissions(
			@RequestBody CPContactEnquiryCreateUpdateReqDto cpContactEnquiryCreateUpdateReqDto) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String whereCondition = "";
		if ((cpContactEnquiryCreateUpdateReqDto.getFrom_date() != null
				&& cpContactEnquiryCreateUpdateReqDto.getFrom_date().length() > 0)
				&& (cpContactEnquiryCreateUpdateReqDto.getTo_date() != null
						&& cpContactEnquiryCreateUpdateReqDto.getTo_date().length() > 0)) {
			whereCondition = "  broker_sfid_id='" + cpContactEnquiryCreateUpdateReqDto.getBroker_sfid()
					//+ "' and project_sfid= '" + cpContactEnquiryCreateUpdateReqDto.getProject_sfid()
					+ "' and Date(date_of_eoi__c) between '" + cpContactEnquiryCreateUpdateReqDto.getFrom_date()
					+ "' and '" + cpContactEnquiryCreateUpdateReqDto.getTo_date() + "'  order by date_of_eoi__c desc ";
		}
		return gson.toJson(eOIReportService.getEOIReportDtl(whereCondition));
		
		/*  
		  CPEOIRespAPIDto enqResp = new CPEOIRespAPIDto(); try {
		  
		  if(cpContactEnquiryCreateUpdateReqDto.getEnquiry_sfid()!=null &&
		  !"".equals(cpContactEnquiryCreateUpdateReqDto.getEnquiry_sfid())) { //fetch
		  details
		  
		  List<CPEOIRespAPIDto>apiDtos=
		  cpapp_Service.getPastEOIData(cpContactEnquiryCreateUpdateReqDto); return
		  gson.toJson(apiDtos); } }catch (Exception e) { // TODO: handle exception
		  e.printStackTrace(); log.error("Error : {}",e); }
		 */
//			return gson.toJson(new ArrayList<String>());

	}

	@PostMapping(value = "/EOIPaymentCPApp", produces = "application/json")
	public String EOIPaymentCPApp(@RequestBody GPLAppBookingAPIDto bookingDto)
	{		
		String returnMesg = "";
		//Call Enquiry SFID to get enquiry PK ID
		GPLAppBookingAPIDto preAPI = null;
		try {
			preAPI = gplAppsWebServiceImpl.insertCPBookingData(bookingDto);//String timeStamp = new SimpleDateFormat("ddMMyyyy").format(new Date());
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
	}
	
	public String insertPaymentRequest(String paymentDtlJson, 
			String userid,
			String enq_sfid,
			String enquiry_name,
			String project_sfid,
			String project_name,
			String customer_mobile, 
			String user_email,
			String user_name,
			String customer_name,
			String customer_email,
			String towercode,
			String towersfid,
			String requestSource, HttpServletRequest request) throws UnsupportedEncodingException {	
		
		if(    !enq_sfid.equals("")  
			&& !enquiry_name.equals("")  
			&& !project_sfid.equals("")  
			&& !project_name.equals("")  
			&& !customer_mobile.equals("")  
			&& !userid.equals("")  
			&& !user_email.equals("")
			&& !user_name.equals("")
			&& !customer_name.equals("")
			&& !customer_email.equals("")) 
		{
			try {
				GsonBuilder gsonBuilder = new GsonBuilder();
				Gson gson = gsonBuilder.create();
				
				int useridInt = Integer.parseInt(userid);
				//int customerMobileInt = Integer.parseInt(customer_mobile);
				
				String strMobile =new GeneratePaymentController().encriptString(customer_mobile);
				/*String strEnq_sfid = encriptString(enq_sfid);*/
				String resultPath = request.getHeader("Host") + request.getContextPath();
				String paymentRequest= "https://"+resultPath+"/ccAvenueLogin?num="+strMobile.replaceAll("\"", "")+"&projectid="+URLEncoder.encode(project_sfid, "UTF-8")+"&enqsfid="+URLEncoder.encode(enq_sfid, "UTF-8")+"&projectname="+URLEncoder.encode(project_name, "UTF-8");
				
				String str=paymentDtlJson;
				  
				Object object=null;
				JsonArray arrayObj=null;
				JsonParser jsonParser=new JsonParser();
				object=jsonParser.parse(str);
				arrayObj=(JsonArray) object;
				
				List<GeneratePayment> charges1=new ArrayList<>();
				
				if(arrayObj!=null && arrayObj.size()>0)
				{
					Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
					
					for(int i=0;i<arrayObj.size();i++) {
						JsonObject jobj = new Gson().fromJson(arrayObj.get(i), JsonObject.class);
						
						GeneratePayment ecData1= new GeneratePayment();
						ecData1= gson.fromJson(arrayObj.get(i), GeneratePayment.class);
						
						if (!jobj.get("amount").getAsString().equals("") && jobj.get("amount") != null
							&& !jobj.get("transaction_date_string").getAsString().equals("") && jobj.get("transaction_date_string") != null) 
						{
							//JsonObject jobj = new Gson().fromJson(arrayObj.get(i), JsonObject.class);
							try {
								if (!(jobj.get("transaction_date_string").getAsString().trim()).isEmpty()) {
									Date date  =  df.parse(jobj.get("transaction_date_string").getAsString());
									ecData1.setTransaction_date(date); 
								} else {
									Date date  =  df.parse("1999-09-09");
									ecData1.setTransaction_date(date);
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
							ecData1.setEnquiry_sfid(enq_sfid);
							ecData1.setEnquiry_name(enquiry_name);
							ecData1.setProject_sfid(project_sfid);
							ecData1.setProject_name(project_name);
							ecData1.setCreatedby(useridInt);
							ecData1.setUpdatedby(useridInt);
							ecData1.setCustomer_email(customer_email);
							ecData1.setCustomer_mobile(customer_mobile);
							ecData1.setCustomer_name(customer_name);
							ecData1.setIspayment_status("N");
							ecData1.setIsactive("Y");
							ecData1.setCreated_date(currentTimestamp);
							ecData1.setUpdate_date(currentTimestamp);
							ecData1.setRequest_url(paymentRequest);
							ecData1.setTowercode(towercode);
							ecData1.setTowersfid(towersfid);
							ecData1.setRequestsource(requestSource);;
							charges1.add(ecData1);
						
						} else {
							String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"ER1001\"}";
							return response;
						}
					}
					boolean isInserted = generatePaymentService.insertPaymentDtl(charges1);
					
					if (isInserted) {
						try {
							//String status = new GeneratePaymentController().mailLinkSend(project_sfid,enq_sfid,user_email,userid,user_name,request);
							String status="STATUS_OK";
							if (status.equals("STATUS_OK")) {
								String response = "{\"status\":\"STATUS_OK\",\"error_msg\":\"Successfully submitted & Link sent to customer\",\"error_id\":null}";
								return response;
							} else {
								String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is successfully submitted & getting error while link mail to customer\",\"error_id\":\"ER1005\"}";
								return response;
							}
						} catch (Exception e) {
							log.info("Payment Request - Getting error while call method mailLinkSend Error:- ",e);
							String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is successfully submitted & getting error while link mail to customer\",\"error_id\":\"ER1005\"}";
							return response;
						}
					} else {
						String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not submitted on portal\",\"error_id\":\"ER1006\"}";
						return response;
					}
				} else {
					String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Data Not Found\",\"error_id\":\"ER1002\"}";
					return response;
				}
			} catch(Exception e) {
				log.info("Payment Request is not submitted Error:- ",e);				
				String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not submitted on portal, please try again later\",\"error_id\":\"ER1003\"}";
				return response;
			}
		} else {
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"ER1004\"}";
			return response;
		}
	}	
	
}
