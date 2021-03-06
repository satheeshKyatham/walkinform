package com.godrej.properties.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.godrej.properties.common.dto.CustomResponseDto;
import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.constants.MessageConstants;
import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.dto.PhaseDto;
import com.godrej.properties.master.dto.ReferenceListDto;
import com.godrej.properties.master.service.PhaseListService;
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
	
	@Autowired
	private PhaseListService phaseListService;
	
	@GetMapping(value = {"/offlineEOI"})
	public ModelAndView offlineEOI(@RequestParam("countrycode") Optional<String> countrycode,@RequestParam("mobileno") Optional<String> mobileno,
			@RequestParam("projectid") Optional<String> projectid,@RequestParam("projectname") Optional<String> projectname,
			@RequestParam("userid") Optional<String> userid,@RequestParam("enquiryType")Optional<String> enquiryType
			,HttpServletRequest request){
		ModelAndView view=new ModelAndView("offlineeoi");
		boolean hasParam=true;
		String mobile  =null;
		String countryCode=null;
		String projectId=null;
		String userId =null;
		String projectName= null;
		String typeOfEnquiry =null;
		HttpSession session = request.getSession();
		if(session ==null) {
			return view;
		}
		
		if(mobileno.isPresent()) {
			mobile = mobileno.get();
		}
		if(countrycode.isPresent()) {
			countryCode = countrycode.get();
		}
		if(projectid.isPresent()) {
			projectId = projectid.get();
		}else {
			projectId = (String) session.getAttribute("PROID");
		}
		if(projectname.isPresent()) {
			projectName=projectname.get();
		}else {
			projectName  = (String) session.getAttribute("PRONAME");
		}
		if(enquiryType.isPresent()) {
			typeOfEnquiry = enquiryType.get();
		}
		if(userid.isPresent()) {
			userId= userid.get();
		}else {
			userId=(String) session.getAttribute("USERID");
		}
		String roleId = (String)session.getAttribute("ROLE");
		view.addObject("hasParam",hasParam);
		view.addObject("mobileNo",mobile);
		view.addObject("countryCode",countryCode);
		view.addObject("projectSfid",projectId);
		view.addObject("userId",userId);
		view.addObject("projectName",projectName);
		view.addObject("enquiryType",typeOfEnquiry);
		view.addObject("roleId", roleId);
		view.addObject("recordTypeProspect",KeyConstants.RECORD_TYPE_PROSPECT);
		view.addObject("recordTypeCustomer",KeyConstants.RECORD_TYPE_CUSTOMER);
		ProjectLaunch projectLaunch=projectLaunchService.getProjectSaleMgrID(projectId);
		if(projectLaunch!=null) {
			view.addObject("AssignTO",projectLaunch.getSalesmanager_sfid());
			view.addObject("etokenTab",projectLaunch.getIsallow_etokentab());
		}
		else {
			/* show error page*/
		}
		
		List<ReferenceListDto> communcationMediumList=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_COMMUNICATION_MEDIUM);	
		view.addObject("communcationMediumList",communcationMediumList);
		List<ReferenceListDto> visitTypes=referenceListService.getReferenceListByReferenceCode(KeyConstants.REFERENCE_CODE_VISIT_TYPE);
		view.addObject("visitTypes", visitTypes);
		List<PhaseDto> phaseList = phaseListService.getPhaseListByProjectCode(projectId);
		view.addObject("phaseList",phaseList);
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
		
	
	@PostMapping("/saveOfflineEnquiry")
	@ResponseBody
	public CustomResponseDto saveEnquiryRequest(@ModelAttribute("EnquiryRequest")EnquiryDto enq){
		CustomResponseDto resp=new CustomResponseDto();
		try{
			enq.setIsActive("Y");
			if(null!=enq.getContact()){
				enq.getContact().setIsActive("Y");
			}
			if(null==enq.getEnquiryId()){
				enq=enquiryRequestService.save(enq);
				resp.setMessage(MessageConstants.ENQUIRY_SAVE_SUCCCESS);
			}
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
