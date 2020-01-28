package com.godrej.properties.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.godrej.kyc.util.StringEncDec;
import com.godrej.properties.common.dto.CustomResponseDto;
import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.constants.MessageConstants;
import com.godrej.properties.dto.ContactDto;
import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.dto.UserTokenDto;
import com.godrej.properties.master.dto.ReferenceListDto;
import com.godrej.properties.master.service.ReferenceListService;
import com.godrej.properties.model.Contact;
import com.godrej.properties.model.ProjectLaunch;
import com.godrej.properties.model.Token;
import com.godrej.properties.service.EnquiryReportService;
import com.godrej.properties.service.EnquiryRequestService;
import com.godrej.properties.service.ProjectLaunchService;
import com.godrej.properties.service.TokenService;
import com.godrej.properties.service.UserContactService;
import com.godrej.properties.util.CommonUtil;
import com.godrej.properties.util.SendSMS;

@Controller
//@RestController
public class EnquiryRequestController {
	
	private Logger LOG=LoggerFactory.getLogger(EnquiryRequestController.class);
	
	@Autowired
	private EnquiryRequestService enquiryRequestService;
	
	@Autowired
	private UserContactService userContactService;
	
	@Autowired
	private ReferenceListService referenceListService;
	@Autowired
	private EnquiryReportService enquiryReportService;
	
	@Autowired
 	private ProjectLaunchService projectLaunchService;
	

 	@Autowired
	private	TokenService tokenService; 
	
	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public String login(ModelMap model,HttpServletRequest request) {
		model.addAttribute("contact", new Contact());
		boolean hasParam=false;
		model.addAttribute("hasParam",hasParam);
		/*model.addAttribute("viewType","basicInfo");*/
		System.out.println("Started Query:-"+new Date());
		List<ReferenceListDto> communcationMediumList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_COMMUNICATION_MEDIUM);
		System.out.println("Query 1 Completed:-"+new Date());
		/*List<ReferenceListDto> ageGroupList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_AGE_GROUP);
		System.out.println("Query 2 Completed:-"+new Date());*/
		/*List<ReferenceListDto> indusrtyList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_INDUSTRY);
		System.out.println("Query 3 Completed:-"+new Date());*/
		/*List<ReferenceListDto> requirementList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_REQUIREMENT_TYPE);
		System.out.println("Query 4 Completed:-"+new Date());*/
		model.addAttribute("communcationMediumList",communcationMediumList);
		/*model.addAttribute("indusrtyList",indusrtyList);*/
		/*model.addAttribute("ageGroupList",ageGroupList);*/
		/*model.addAttribute("requirementList",requirementList);*/
		return "enquiryInfo";
		 
	}
	
	@RequestMapping(value ="/success", method = RequestMethod.GET)
	public ModelAndView success(@RequestParam("enquiryid") String enquiryId,
				@RequestParam("mobileno") String mobileNo,
				@RequestParam("projectSFID") String projectSFID,
				@RequestParam("projectName") String projectName) {
		//TODO GENERATE TOKEN 

		/*Token token = new Token();
		token.setCreated(new Timestamp(new Date().getTime()));
		
		if(mobileNo.contains("+91"))
			token.setMobileno(mobileNo);
		else
			token.setMobileno("+91"+mobileNo);
		token.setProjectname(projectSFID);
		token.setEnquiry_18(String.valueOf(enquiryId));
		token.setType("W");
		token.setUniqe_no("999999");
		token.setUniqe_str("999999");
		token.setAmount("");
		token.setDocNo("");
		token.setIsKYCFilled("N");
		token.setIsactive("Y");
		token=	tokenService.generateToken(token);
	
		// TODO SEND SMS  
		StringEncDec enc = new StringEncDec();
		String encStr = enc.encrypt(mobileNo);
		token.setEncStr(encStr);
		
		try {
			send_sms(mobileNo,token.getType()+token.getQueue(),projectName);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/


		 return new ModelAndView("success");		 
	}
	@RequestMapping(value = "/userinfo/{countrycode}/{mobileno}/{projectid}/{projectname}/{userid}/{enquiryType}", method = RequestMethod.GET)
	public ModelAndView login(@PathVariable("countrycode") String countrycode,@PathVariable("mobileno") String mobileno,
			@PathVariable("projectid") String projectid,@PathVariable("projectname") String projectname,
			@PathVariable("userid") Integer userid,@PathVariable("enquiryType")String enquiryType){
		ModelAndView view=new ModelAndView("enquiryInfo");
		boolean hasParam=true;
		view.addObject("hasParam",hasParam);
		/*view.addObject("viewType","basicInfoWithParam");*/
		view.addObject("mobileNo",mobileno);
		view.addObject("countryCode",countrycode);
		view.addObject("projectSfid",projectid);
		view.addObject("userId",userid);
		view.addObject("projectName",projectname);
		view.addObject("enquiryType",enquiryType);
		view.addObject("recordTypeProspect",KeyConstants.RECORD_TYPE_PROSPECT);
		view.addObject("recordTypeCustomer",KeyConstants.RECORD_TYPE_CUSTOMER);
		ProjectLaunch projectLaunch=projectLaunchService.getProjectSaleMgrID(projectid);
		if(projectLaunch!=null) {
			view.addObject("AssignTO",projectLaunch.getSalesmanager_sfid());
		}
		else {
			// TODO RETURN Error Page 
		}
		List<ReferenceListDto> communcationMediumList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_COMMUNICATION_MEDIUM);
		view.addObject("communcationMediumList",communcationMediumList);
		
		return view;		 
	}
	
	@RequestMapping(value="/getDropDowns",method=RequestMethod.GET)
	@ResponseBody
	public CustomResponseDto getDropDowns(){
//		List<ReferenceListDto> budgetList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_BUDGET);
//		List<ReferenceListDto> purposeList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_PURPOSE);
//		
//		List<ReferenceListDto> countryList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_COUNTRY);
//		List<ReferenceListDto> occupationList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_OCCUPATION);
//		List<ReferenceListDto> possessionTimeLineList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_POSSESION_TIME);
//		List<ReferenceListDto> salutationList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_SALUTATION);
		//System.out.println(new Date());
		List<ReferenceListDto> communcationMediumList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_COMMUNICATION_MEDIUM);
		//System.out.println(new Date());
		/*List<ReferenceListDto> ageGroupList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_AGE_GROUP);*/
		//System.out.println(new Date());
		/*List<ReferenceListDto> indusrtyList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_INDUSTRY);
		System.out.println(new Date());*/
		List<ReferenceListDto> requirementList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_REQUIREMENT_TYPE);
		//System.out.println(new Date());
		/*	List<ReferenceListDto> currentResidenceList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_CURRENT_RESIDENCE_TYPE);*/
		//List<ReferenceListDto> typologicalAreaList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_TYPOLOGY_AREA);
		
		CustomResponseDto resp=new CustomResponseDto();
//		resp.addObject("budgetList", budgetList);
//		resp.addObject("purposeList", purposeList);
//		resp.addObject("countryList", countryList);
//		resp.addObject("occupationList", occupationList);
//		resp.addObject("possessionTimeLineList", possessionTimeLineList);
//		resp.addObject("salutationList", salutationList);
//		
		resp.addObject("communcationMediumList", communcationMediumList);
		/*resp.addObject("ageGroupList", ageGroupList);*/
		/*resp.addObject("indusrtyList", indusrtyList);*/
		resp.addObject("requirementList", requirementList);
		/*resp.addObject("currentResidenceList", currentResidenceList);*/
//		resp.addObject("typologicalAreaList", typologicalAreaList);
		return resp;
	}
	
	@PostMapping("/saveBaseInfo")
	@ResponseBody
	public CustomResponseDto saveEnquiryRequest(@ModelAttribute("EnquiryRequest")EnquiryDto enq){
		CustomResponseDto resp=new CustomResponseDto();
		try{
			String projectName=enq.getProjectName();
			enq.setIsActive("Y");
			if(null!=enq.getContact()){
				enq.getContact().setIsActive("Y");
			}
			
			//Save enquiry and contact
			if(null==enq.getEnquiryId()){
				enq=enquiryRequestService.save(enq);
				resp.setMessage(MessageConstants.ENQUIRY_SAVE_SUCCCESS);
			}//Update enquiry and contact Details
			else{
				EnquiryDto dest=enquiryRequestService.findById(enq);
				enq=enquiryRequestService.updateBaseInfo(enq, dest);
				resp.setMessage(MessageConstants.ENQUIRY_UPDATE_SUCCCESS);
			}
			if(null==enq.getEnquiryReport().getEnquiryId())	{
				enquiryReportService.updateById(enq);
			}
	
					resp.setSuccess(true);
		
			LOG.info(MessageConstants.ENQUIRY_UPDATE_SUCCCESS);
		}catch(Exception ex){
			LOG.error(ex.getMessage());
			LOG.error(MessageConstants.ENQUIRY_SAVE_FAILED);
			ex.printStackTrace();
			resp.setMessage(MessageConstants.ENQUIRY_SAVE_FAILED);
			resp.setSuccess(false);
		}
		resp.addObject("EnquiryRequest", enq);
		return resp;
	}

	@GetMapping("/getExistingRecords/{countryCode}/{mobileNo}")
	@ResponseBody
	public CustomResponseDto getExistingRecords(@PathVariable("countryCode") String countryCode,@PathVariable("mobileNo") String mobileNo){
		CustomResponseDto resp=new CustomResponseDto();
		try{
			System.out.println("enquiry query started:"+new Date());
			/*EnquiryDto enquiry=enquiryRequestService.getEnquiryByMobileNo(mobileNo);*/
			List<EnquiryDto> enquiryList=enquiryRequestService.getEnquiriesByMobileNo(countryCode,mobileNo);
			System.out.println("enquiry query ended:"+new Date());
			ContactDto contact=null;
			if(CommonUtil.isCollectionEmpty(enquiryList)){
			   System.out.println("contact query started:" +new Date());
			   contact=userContactService.findMobileNoExist(countryCode,mobileNo);
			   System.out.println("contact query ended:" +new Date());
			}
			
			resp.addObject("enquiryList", enquiryList);
			resp.addObject("contact", contact);
			resp.setSuccess(true);
			resp.setMessage(MessageConstants.ENQUIRY_GET_SUCCESS);
			LOG.info(MessageConstants.ENQUIRY_GET_SUCCESS);
		}catch(Exception ex){
			LOG.error(ex.getMessage());
			LOG.error(MessageConstants.ENQUIRY_GET_FAILED);
			ex.printStackTrace();
			resp.setSuccess(false);
			resp.setMessage(MessageConstants.ENQUIRY_GET_FAILED);
		}
		return resp;
	}
	
	@GetMapping("/getExistingRecords/{countryCode}/{mobileno}/{projectid}")
	@ResponseBody
	public CustomResponseDto getExistingEnquiry(@PathVariable("countryCode") String countryCode,@PathVariable("mobileno") String mobileNo,@PathVariable("projectid") String projectSfid){
		CustomResponseDto resp=new CustomResponseDto();
		try{
			List<EnquiryDto> enquiryList=enquiryRequestService.getEnquiriesByMobileNo(countryCode,mobileNo,projectSfid,"");
			ContactDto contact=null;
			EnquiryDto enquiryDto =null;
			if(CommonUtil.isCollectionEmpty(enquiryList)){
			  contact=userContactService.findMobileNoExist(countryCode,mobileNo);			  
			}else {
				contact=enquiryList.get(0).getContact();
				enquiryDto =  enquiryList.get(0);
			}
			
			String enquirySfid = null;
			if(enquiryDto !=null) {
				enquirySfid= String.valueOf(enquiryDto.getEnquiryId());
			}
			Token token = tokenService.getTokenByEnquiry(enquirySfid);
			UserTokenDto userToken = getUserToken(token);
			resp.addObject("enquiryList", enquiryList);
			resp.addObject("contact", contact);
			resp.addObject("userToken", userToken);
			resp.setSuccess(true);
			resp.setMessage(MessageConstants.ENQUIRY_GET_SUCCESS);
			LOG.info(MessageConstants.ENQUIRY_GET_SUCCESS);
		}catch(Exception ex){
			LOG.error(ex.getMessage());
			LOG.error(MessageConstants.ENQUIRY_GET_FAILED);
			ex.printStackTrace();
			resp.setSuccess(false);
			resp.setMessage(MessageConstants.ENQUIRY_GET_FAILED);
		}
		return resp;
	}
	
	private UserTokenDto getUserToken(Token token) {
		UserTokenDto userToken =  new UserTokenDto();
		if(token==null) {
			return null;
		}
		userToken.setTokenId(String.valueOf(token.getNv_token_id()));
		userToken.setTokenNo(token.getType()+token.getQueue());
		return userToken;
	}
	private void send_sms(String mobile ,String token,String projName) throws UnsupportedEncodingException {
		
	 	String msg=	"Thank you for your interest in "+projName+". Your token no is "+ token+	". Regards, Godrej Properties.";
	 	msg = URLEncoder.encode(msg, "UTF-8");
		String value = null;
		try {
			value = new String(msg.getBytes(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SendSMS.SMSSend(mobile,msg);
	}
	//Added By Satheesh Kyahtam for CP
	@PostMapping("/saveCPBaseInfo")
	@ResponseBody
	public CustomResponseDto saveCPEnquiryRequest(@ModelAttribute("EnquiryRequest")EnquiryDto enq){
		CustomResponseDto resp=new CustomResponseDto();
		try{
			String projectName=enq.getProjectName();
			enq.setIsActive("Y");
			if(null!=enq.getContact()){
				enq.getContact().setIsActive("Y");
			}
			
			//Save enquiry and contact
			if(null==enq.getEnquiryId()){
				enq=enquiryRequestService.save(enq);
				resp.setMessage(MessageConstants.ENQUIRY_SAVE_SUCCCESS);
			}//Update enquiry and contact Details
			else{
				EnquiryDto dest=enquiryRequestService.findById(enq);
				enq=enquiryRequestService.updateBaseInfo(enq, dest);
				resp.setMessage(MessageConstants.ENQUIRY_UPDATE_SUCCCESS);
			}
			if(null==enq.getEnquiryReport().getEnquiryId())	{
				enquiryReportService.updateById(enq);
			}
	
					resp.setSuccess(true);
		
			LOG.info(MessageConstants.ENQUIRY_UPDATE_SUCCCESS);
		}catch(Exception ex){
			LOG.error(ex.getMessage());
			LOG.error(MessageConstants.ENQUIRY_SAVE_FAILED);
			ex.printStackTrace();
			resp.setMessage(MessageConstants.ENQUIRY_SAVE_FAILED);
			resp.setSuccess(false);
		}
		resp.addObject("EnquiryRequest", enq);
		return resp;
	}
	/* Added By Sathish Kyatham - CP*/
	@RequestMapping(value ="/cpsuccess", method = RequestMethod.GET)
	public ModelAndView cpsuccess() {
		 return new ModelAndView("cpsuccess");		 
	}
}
