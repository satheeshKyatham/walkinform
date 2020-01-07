package com.godrej.properties.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.godrej.properties.common.dto.CustomResponseDto;
import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.constants.MessageConstants;
import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.master.dto.ReferenceListDto;
import com.godrej.properties.master.service.ReferenceListService;
import com.godrej.properties.model.ProjectLaunch;
import com.godrej.properties.service.EnquiryReportService;
import com.godrej.properties.service.EnquiryRequestService;
import com.godrej.properties.service.ProjectLaunchService;

@Controller
public class OfflineEOIController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
 	private ProjectLaunchService projectLaunchService;

	@Autowired
	private ReferenceListService referenceListService;

	@Autowired
	private EnquiryRequestService enquiryRequestService;
	
	@Autowired
	private EnquiryReportService enquiryReportService;

	
	@GetMapping(value = "/offlineEOI/{countrycode}/{mobileno}/{projectid}/{projectname}/{userid}/{enquiryType}")
	public ModelAndView offlineEOI(@PathVariable("countrycode") String countrycode,@PathVariable("mobileno") String mobileno,
			@PathVariable("projectid") String projectid,@PathVariable("projectname") String projectname,
			@PathVariable("userid") Integer userid,@PathVariable("enquiryType")String enquiryType){
		ModelAndView view=new ModelAndView("offlineeoi");
		boolean hasParam=true;
		view.addObject("hasParam",hasParam);
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
			/* show error page*/
		}
		List<ReferenceListDto> communcationMediumList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_COMMUNICATION_MEDIUM);
		view.addObject("communcationMediumList",communcationMediumList);
		
		return view;		 
	}
	
	@GetMapping(value="/getOEDropDowns")
	@ResponseBody
	public CustomResponseDto getDropDowns(){
		List<ReferenceListDto> communcationMediumList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_COMMUNICATION_MEDIUM);
		List<ReferenceListDto> requirementList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_REQUIREMENT_TYPE);
		CustomResponseDto resp=new CustomResponseDto();
		resp.addObject("communcationMediumList", communcationMediumList);
		resp.addObject("requirementList", requirementList);
		return resp;
	}


	/*
	 * private EnquiryUserDto getEnquiryUser(HttpServletRequest request) {
	 * EnquiryUserDto enquiryUser = new EnquiryUserDto(); HttpSession session =
	 * request.getSession(false); String userName = (String)
	 * session.getAttribute("USERNAME"); String userMobile = (String)
	 * session.getAttribute("USERMOBILENO"); String userId = (String)
	 * session.getAttribute("USERID"); String userEmail = (String)
	 * session.getAttribute("USEREMAIL"); String role = (String)
	 * session.getAttribute("ROLE"); String projectName = (String)
	 * session.getAttribute("PRONAME"); String projectId = (String)
	 * session.getAttribute("PROID"); String closingMgr = (String)
	 * session.getAttribute("Closingmgr");
	 * 
	 * return enquiryUser; }
	 */
		
	
	@PostMapping("/saveOfflineEnquiry")
	@ResponseBody
	public CustomResponseDto saveEnquiryRequest(@ModelAttribute("EnquiryRequest")EnquiryDto enq){
		CustomResponseDto resp=new CustomResponseDto();
		try{
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
		
			log.info(MessageConstants.ENQUIRY_UPDATE_SUCCCESS);
		}catch(Exception ex){
			log.error("Error",  ex);
			log.error(MessageConstants.ENQUIRY_SAVE_FAILED);
			resp.setMessage(MessageConstants.ENQUIRY_SAVE_FAILED);
			resp.setSuccess(false);
		}
		resp.addObject("EnquiryRequest", enq);
		return resp;
	}

}
