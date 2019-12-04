package com.godrej.properties.webservices;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.godrej.properties.dto.ContactDto;
import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.master.dto.ReferenceListDto;
import com.godrej.properties.master.service.ReferenceListService;
import com.godrej.properties.model.ProjectLaunch;
import com.godrej.properties.service.EnquiryRequestService;
import com.godrej.properties.service.ProjectLaunchService;
import com.godrej.properties.service.UserContactService;
import com.godrej.properties.util.CommonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class CPWebServices {
	
	@Autowired
	ServletContext context;
	
	private Logger LOG=LoggerFactory.getLogger(EnquiryRequestController.class);
	
	@Autowired
	private EnquiryRequestService enquiryRequestService;
	
	@Autowired
	private UserContactService userContactService;
	
	@Autowired
 	private ProjectLaunchService projectLaunchService;
	
	@Autowired
	private ReferenceListService referenceListService;
	
	@RequestMapping(value = "/validateEnquiryExist", method = RequestMethod.GET, produces = "application/json")
	public String validateEnquiryExist(@RequestParam("countryCode") String countryCode,@RequestParam("mobileno") String mobileNo,@RequestParam("projectid") String projectSfid){
		CustomResponseDto resp=new CustomResponseDto();
		CustomResponseDto customResponseDto=resp;
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		
		List<EnquiryDto> enquiryList = null;
		try{
			enquiryList=enquiryRequestService.getEnquiriesByMobileNo(countryCode,mobileNo,projectSfid,"");//countryCode,mobileNo,projectSfid
			ContactDto contact=null;
			if(CommonUtil.isCollectionEmpty(enquiryList)){
			  contact=userContactService.findMobileNoExist(countryCode,mobileNo);			  
			}
			resp.addObject("enquiryList", enquiryList);
			resp.addObject("contact", contact);
			resp.setSuccess(true);
			resp.setMessage(MessageConstants.ENQUIRY_GET_SUCCESS);
			LOG.info(MessageConstants.ENQUIRY_GET_SUCCESS);
			if(enquiryList.size()==0) {
				
			}
		}catch(Exception ex){
			LOG.error(ex.getMessage());
			LOG.error(MessageConstants.ENQUIRY_GET_FAILED);
			ex.printStackTrace();
			resp.setSuccess(false);
			resp.setMessage(MessageConstants.ENQUIRY_GET_FAILED);
		}
		
		
		 return gson.toJson(customResponseDto);
	 
	}
	
	@RequestMapping(value = "/validateCPEnquiryExist", method = RequestMethod.GET, produces = "application/json")
	public String validateCPEnquiryExist(@RequestParam("custname") String custname,@RequestParam("countryCode") String countryCode,@RequestParam("mobileno") String mobileNo,@RequestParam("projectid") String projectSfid,@RequestParam("emailid") String emailid
			,@RequestParam("brokeraccid") String brokeraccid,HttpServletRequest request){
		CustomCPResponseDto resp=new CustomCPResponseDto();
		CustomCPResponseDto customResponseDto=resp;
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		
		List<EnquiryDto> enquiryList = null;
		try{
			//enquiryList=enquiryRequestService.getEnquiriesByMobileNo("+"+mobileNo,projectSfid);
			enquiryList = enquiryRequestService.getEnquiriesByCPAppWithParam(custname,"+"+countryCode,mobileNo,projectSfid,emailid);
			if(!enquiryList.isEmpty())
			{
				if(enquiryList.get(0).getChannelPartner().getSfid().equals(brokeraccid))
				{
					System.out.println("Entry found");
					resp.setSuccess(true);
					resp.setMessage(MessageConstants.ENQUIRY_GET_SUCCESS);
					String resultPath = request.getScheme()+"://"+request.getHeader("Host") + request.getContextPath();
					resp.setUrl(resultPath+"/usercpinfo/+91/"+enquiryList.get(0).getContact().getMobile()+"/"+enquiryList.get(0).getProject().getSfid()+"/"+enquiryList.get(0).getProject().getPname()+"/42/D");
				}
				else
				{
					System.out.println("The LEAD IS ALREADY CREATED");
					resp.setSuccess(false);
					resp.setMessage("The LEAD IS ALREADY CREATED");
					resp.setUrl("");
				}
			}
			else
			{
				System.out.println("The LEAD IS ALREADY CREATED");
				resp.setSuccess(false);
				resp.setMessage("The LEAD IS ALREADY CREATED");
				resp.setUrl("");
			}
			ContactDto contact=null;
			if(CommonUtil.isCollectionEmpty(enquiryList)){
			  contact=userContactService.findMobileNoExist(countryCode,mobileNo);			  
			}
			resp.addObject("enquiryList", enquiryList);
			resp.addObject("contact", contact);
			
			LOG.info(MessageConstants.ENQUIRY_GET_SUCCESS);
			if(enquiryList.size()==0) {
				
			}
		}catch(Exception ex){
			LOG.error(ex.getMessage());
			LOG.error(MessageConstants.ENQUIRY_GET_FAILED);
			ex.printStackTrace();
			resp.setSuccess(false);
			resp.setMessage(MessageConstants.ENQUIRY_GET_FAILED);
		}
		
		
		 return gson.toJson(customResponseDto);
	 
	}
	@RequestMapping(value = "/usercpinfo/{countrycode}/{mobileno}/{projectid}/{projectname}/{userid}/{enquiryType}", method = RequestMethod.GET)
	public ModelAndView login(@PathVariable("countrycode") String countrycode,@PathVariable("mobileno") String mobileno,
			@PathVariable("projectid") String projectid,@PathVariable("projectname") String projectname,
			@PathVariable("userid") Integer userid,@PathVariable("enquiryType")String enquiryType){
		ModelAndView view=new ModelAndView("enquiryCPInfo");
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

	@GetMapping("/getExistingCPRecords/{countryCode}/{mobileno}/{projectid}")
	@ResponseBody
	public CustomResponseDto getExistingEnquiry(@PathVariable("countryCode") String countryCode,@PathVariable("mobileno") String mobileNo,@PathVariable("projectid") String projectSfid){
		CustomResponseDto resp=new CustomResponseDto();
		try{
			List<EnquiryDto> enquiryList=enquiryRequestService.getEnquiriesByMobileNo(countryCode,mobileNo,projectSfid,"");
			ContactDto contact=null;
			if(CommonUtil.isCollectionEmpty(enquiryList)){
			  contact=userContactService.findMobileNoExist(countryCode,mobileNo);			  
			}else {
				contact=enquiryList.get(0).getContact();
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
}
