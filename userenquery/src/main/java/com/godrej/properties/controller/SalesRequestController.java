package com.godrej.properties.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.godrej.properties.common.dto.CustomResponseDto;
import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.constants.MessageConstants;
import com.godrej.properties.dto.ContactDto;
import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.dto.UserSessionDto;
import com.godrej.properties.master.dto.ReferenceListDto;
import com.godrej.properties.master.service.ReferenceListService;
import com.godrej.properties.service.EnquiryRequestService;
import com.godrej.properties.service.TokenService;
import com.godrej.properties.service.UserContactService;
import com.godrej.properties.util.CommonUtil;
import com.godrej.properties.util.SendSMS;

/**
 * 
 * @author Varsha Patil
 *
 */
@Controller
public class SalesRequestController {
	
	private Logger LOG=LoggerFactory.getLogger(EnquiryRequestController.class);
	
	@Autowired
	private ReferenceListService referenceListService;
	@Autowired
	private EnquiryRequestService enquiryRequestService;
	@Autowired
	private UserContactService userContactService;
	
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = { "/salesDeskLogin"}, method = RequestMethod.GET)
	public ModelAndView salesDeskLogin() {
		ModelAndView view=new ModelAndView("salesLoginNew");
		return view;
	}
	@RequestMapping(value = { "/salesDeskSearch/{projectSfid}/{projectName}"}, method = RequestMethod.GET)
	public ModelAndView salesDeskSearch(@PathVariable("projectSfid")String projectSfid,@PathVariable("projectName")String projectName) {
		ModelAndView view=new ModelAndView("salesSearch");
		view.addObject("projectName",projectName);
		view.addObject("projectSfid",projectSfid);
		return view;
	}
	
	@RequestMapping(value = { "/salesDeskSearch"}, method = RequestMethod.POST)
	public ModelAndView salesDeskSearch(@ModelAttribute("userSession") UserSessionDto userSession,HttpServletRequest request) {
		ModelAndView view=new ModelAndView("salesSearch");
		HttpSession session=request.getSession();
		session.setAttribute("userSession",userSession);
		view.addObject("projectName",userSession.getProjectName());
		view.addObject("projectSfid",userSession.getProjectId());
		view.addObject("userName",userSession.getUserName());
		return view;
	}
	@RequestMapping(value = { "/salesinfo"}, method = RequestMethod.GET)
	public ModelAndView salesInfo(@RequestParam("token") String token,@RequestParam("countrycode") String countrycode,
			@RequestParam("mobileno") String mobileno,
			@RequestParam("projectSfid") String projectSfid,@RequestParam("projectName") String projectName,
			HttpServletRequest request) {
		ModelAndView view=new ModelAndView("salesInfo");
		boolean hasParam=false;
		List<ReferenceListDto> communcationMediumList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_COMMUNICATION_MEDIUM);
		/*List<ReferenceListDto> ageGroupList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_AGE_GROUP);*/
		/*List<ReferenceListDto> indusrtyList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_INDUSTRY);*/
		/*List<ReferenceListDto> requirementList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_REQUIREMENT_TYPE);*/
		/*List<ReferenceListDto> budgetList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_BUDGET);
		List<ReferenceListDto> purposeList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_PURPOSE);		
		*//*List<ReferenceListDto> countryList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_COUNTRY);*/
		/*List<ReferenceListDto> occupationList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_OCCUPATION);
		List<ReferenceListDto> possessionTimeLineList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_POSSESION_TIME);
		List<ReferenceListDto> currentResidenceList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_CURRENT_RESIDENCE_TYPE);
		*/
		/*view.addObject("currentResidenceList",currentResidenceList);
		view.addObject("budgetList", budgetList);
		view.addObject("purposeList", purposeList);
		view.addObject("occupationList", occupationList);
		view.addObject("possessionTimeLineList", possessionTimeLineList);
		*/
	    HttpSession session=request.getSession();
	    UserSessionDto userSession=(UserSessionDto) session.getAttribute("userSession");
		view.addObject("communcationMediumList",communcationMediumList);
		/*view.addObject("indusrtyList",indusrtyList);*/
		/*view.addObject("ageGroupList",ageGroupList);*/
		/*view.addObject("requirementList",requirementList);*/		
		view.addObject("hasParam",hasParam);
		view.addObject("mobileNo",mobileno);
		view.addObject("countryCode",countrycode);
		view.addObject("token",token);
		view.addObject("viewType","SALESVIEW");
		view.addObject("salesViewType","SALESVIEWSUBMITTED");
		view.addObject("loginRole","SALES");
		view.addObject("projectSfid",projectSfid);
		view.addObject("projectName",projectName);
		view.addObject("closingmanagers",(String) session.getAttribute("Closingmgr"));
		view.addObject("assignTo",(String) session.getAttribute("Assignto"));
		if(userSession!=null){
		  view.addObject("userName",userSession.getUserName());
		}
		return view;		 
	}	
	@RequestMapping(value = { "/salesDetails"}, method = RequestMethod.GET)
	public ModelAndView salesInfo(@RequestParam("tokenid") int tokenid,@RequestParam("countrycode") String countrycode,
			@RequestParam("mobileno") String mobileno,@RequestParam("projectSfid") String projectSfid,
			@RequestParam("projectName") String projectName,@RequestParam("token")String token
			,@RequestParam("isView")String isView,@RequestParam("salesViewType")String salesViewType,HttpServletRequest request) {
		ModelAndView view=new ModelAndView("salesInfo");
		boolean hasParam=false;
		
		List<ReferenceListDto> communcationMediumList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_COMMUNICATION_MEDIUM);
		
	    HttpSession session=request.getSession();
	    UserSessionDto userSession=(UserSessionDto) session.getAttribute("userSession");
		view.addObject("communcationMediumList",communcationMediumList);
		view.addObject("hasParam",hasParam);
		view.addObject("mobileNo",mobileno);
		view.addObject("countryCode",countrycode);
		view.addObject("token",token);
		view.addObject("viewType","SALESVIEW");
		if("Y".equals(salesViewType))
			view.addObject("salesViewType","SALESVIEWSUBMITTED");
		else
			view.addObject("salesViewType","");
		view.addObject("projectSfid",projectSfid);
		view.addObject("projectName",projectName);
		view.addObject("isView",isView);
		view.addObject("closingmanagers",(String) session.getAttribute("Closingmgr"));
		view.addObject("assignTo",(String) session.getAttribute("Assignto"));
		if(userSession!=null){
		  view.addObject("userName",userSession.getUserName());
			if(null!=userSession.getRole() && !"".equals(userSession.getRole())){
				view.addObject("loginRole",userSession.getRole());
			}else{
				view.addObject("loginRole","SALES");
			}
		}else {
			view.addObject("loginRole","SALES");
		}
		view.addObject("tokenId",tokenid);
		return view;		 
	}	
	/*@RequestMapping(value = { "/costsheet/{token}/{projectsfid}/{enquirysfid}/{primarycontactsfid}"}, method = RequestMethod.GET)
	public ModelAndView costsheet(@PathVariable("token") String token,@PathVariable("projectsfid") String projectsfid,
			@PathVariable("enquirysfid") String enquirysfid,@PathVariable("primarycontactsfid") String primarycontactsfid) {
		ModelAndView view=new ModelAndView("costsheet");
		return view;
	}*/
	@RequestMapping(value="/getSalesDropDowns",method=RequestMethod.GET)
	@ResponseBody
	public CustomResponseDto getSalesDropDowns(){
		List<ReferenceListDto> budgetList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_BUDGET);
		/*List<ReferenceListDto> purposeList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_PURPOSE);*/		
		/*List<ReferenceListDto> countryList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_COUNTRY);*/
		/*List<ReferenceListDto> occupationList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_OCCUPATION);
		List<ReferenceListDto> possessionTimeLineList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_POSSESION_TIME);
		List<ReferenceListDto> currentResidenceList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_CURRENT_RESIDENCE_TYPE);
		*/
		/*List<ReferenceListDto> salutationList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_SALUTATION);
        List<ReferenceListDto> typologicalAreaList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_TYPOLOGY_AREA);
		*/
		CustomResponseDto resp=new CustomResponseDto(); 
		resp.addObject("budgetList", budgetList);
		/*resp.addObject("purposeList", purposeList);*/
		/*resp.addObject("occupationList", occupationList);
		resp.addObject("possessionTimeLineList", possessionTimeLineList);
		resp.addObject("currentResidenceList", currentResidenceList);*/
		/*resp.addObject("countryList", countryList);*/
		/*resp.addObject("salutationList", salutationList);
		resp.addObject("typologicalAreaList", typologicalAreaList);*/
		return resp;
	}
	@PostMapping("/saveAddressInfo")
	@ResponseBody
	public CustomResponseDto saveAddressRequest(@ModelAttribute("EnquiryRequest")EnquiryDto enq,HttpServletRequest req){		
		CustomResponseDto resp=new CustomResponseDto();
		try{
			 HttpSession session=req.getSession();
			 String PRONAME=(String)session.getAttribute("PRONAME");
			 String USERMOBILENO=(String)session.getAttribute("USERMOBILENO");
			 String USERNAME=(String)session.getAttribute("USERNAME");
			 String USEREMAIL=(String)session.getAttribute("USEREMAIL");
			 
			EnquiryDto dest=enquiryRequestService.getEnquiryById(enq);
			if(dest==null) {
				dest=new EnquiryDto();
			}
			
			enq=enquiryRequestService.updateAddressInfo(enq, dest);
			resp.setMessage("Enquiry Updated.");
			resp.setSuccess(true);
			String userName= enq.getContact().getFirstName()+" "+enq.getContact().getFirstName();
			 
			String textsms = 
					"Thank you for visiting Godrej Properties. For any further queries, please contact your sales manager "
					+ ""+USERNAME+" at "+USERMOBILENO+" . " ;
					/*
					"Project Name="+PRONAME+"M="+USERMOBILENO
					+" with token no. ABC has been assigned to you at . Please login and update comments on portal, after the session.";*/
			String strencryptedText="";
			
			tokenService.updateEnqSalesTab(enq.getEnquiryId(), USEREMAIL);
			
			try {
				strencryptedText = URLEncoder.encode(textsms, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   SendSMS.SMSSend(enq.getContact().getMobile(), strencryptedText);
		
			
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
	@GetMapping("/getEnquiryForSales/{countryCode}/{mobileno}/{projectid}/{token}")
	@ResponseBody
	public CustomResponseDto getExistingEnquiry(@PathVariable("countryCode") String countryCode,@PathVariable("mobileno") String mobileNo,@PathVariable("projectid") String projectSfid,@PathVariable("token")String token){
		CustomResponseDto resp=new CustomResponseDto();
		try{
			/*EnquiryDto enquiry=enquiryRequestService.getEnquiryByMobileNo(mobileNo);*/
			List<EnquiryDto> enquiryList=enquiryRequestService.getEnquiriesByMobileNo(countryCode,mobileNo,projectSfid,token,"");
			ContactDto contact=null;
			if(CommonUtil.isCollectionEmpty(enquiryList)){
			  contact=userContactService.findMobileNoExist(countryCode,mobileNo);
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
	@GetMapping("/validateMobileAndToken/{mobileno}/{token}/{projectSfid}")
	@ResponseBody
	public CustomResponseDto validateMobileAndToken(@PathVariable("mobileno") String mobileNo,@PathVariable("token")String token,@PathVariable("projectSfid") String projectSfid){
		CustomResponseDto resp=new CustomResponseDto();
		try{
			boolean isValid=enquiryRequestService.validateMobileAndToken(mobileNo, token,projectSfid);
			resp.setSuccess(isValid);
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

	
}
