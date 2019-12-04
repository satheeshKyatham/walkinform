package com.godrej.properties.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.godrej.properties.common.dto.CustomResponseDto;
import com.godrej.properties.constants.MessageConstants;
import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.service.EnquiryRequestService;

@Controller
public class BankDetailesController {
	@Autowired
	private EnquiryRequestService enquiryRequestService;
	private Logger LOG=LoggerFactory.getLogger(BankDetailesController.class);
	@RequestMapping(value = { "/bankpayment"}, method = RequestMethod.GET)
	public String login(ModelMap model,HttpServletRequest request) {
		 return "bankpayment";
		 
	}
	@PostMapping("/savePaymentInfo")
	@ResponseBody
	public CustomResponseDto savePaymentInfo(@ModelAttribute("EnquiryRequest")EnquiryDto enq){
		CustomResponseDto resp=new CustomResponseDto();
		try{
			
			enq=enquiryRequestService.savePaymentDetails(enq);
			resp.setMessage("Enquiry Updated.");
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
}
