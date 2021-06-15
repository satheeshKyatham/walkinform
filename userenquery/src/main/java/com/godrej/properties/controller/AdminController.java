package com.godrej.properties.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.master.dto.TemplateDto;
import com.godrej.properties.master.service.TemplateService;
import com.godrej.properties.model.BalanceDetails;
import com.godrej.properties.model.Token;
import com.godrej.properties.service.BalanceDetailsService;
import com.godrej.properties.service.CCGatewayService;
import com.godrej.properties.service.ProjectLaunchService;
import com.godrej.properties.service.TokenService;
import com.godrej.properties.service.UserMasterService;
import com.godrej.properties.service.VW_UserMasterService;
import com.godrej.properties.util.CCGatewayEncDecUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class AdminController {
	
	@Autowired
 	private ProjectLaunchService projectLaunchService;
	
	@Autowired
 	private VW_UserMasterService vW_UserMasterService;
	
	@Autowired
	UserMasterService userMasterService;
	
	@Autowired
	private	TokenService tokenService;
	
	@Autowired
	CCGatewayService cCGatwayService;
	
	@Autowired
	private BalanceDetailsService balanceDetailsService;
	
	@Autowired
	private TemplateService templateService;
	
	@RequestMapping(value = { "/usermaster"}, method = RequestMethod.GET)
	public String userMaster(ModelMap model,HttpServletRequest request) {
	//	model.addAttribute("Projects", projectLaunchService.getActiveProjectList());
		 return "usermaster";
	}
	@RequestMapping(value = { "/createdoffers"}, method = RequestMethod.GET)
	public String createdoffers(ModelMap model,HttpServletRequest request) {
		String projectid = request.getParameter("projectid");
		List<BalanceDetails> offers =balanceDetailsService.getCreatedOffersList(projectid);
		model.addAttribute("createdoffers",offers );
		 return "createdoffers";
	}
	
	@RequestMapping(value = { "/misreportview"}, method = RequestMethod.GET)
	public String misReportPage(ModelMap model,HttpServletRequest request,@RequestParam("projectid") String projectid,@RequestParam("projectname") String projectname) {
		
		 return "misreport";
	}
	
	@RequestMapping(value = { "/assigntoken"}, method = RequestMethod.GET)
	public String assignToken(ModelMap model,HttpServletRequest request) {
		model.addAttribute("Projects", projectLaunchService.getActiveProjectList());
		List<Token> tokens=tokenService.getuniqeTypes();
		model.addAttribute("tokens",tokens );
		 return "assigntoken";
	}
	

	@RequestMapping(value = { "/userprojectmap"}, method = RequestMethod.GET)
	public String userProjectMaster(ModelMap model,HttpServletRequest request) {
		/*model.addAttribute("Projects", projectLaunchService.getActiveProjectList());*/
		;
		System.out.println("Region Name:-"+request.getParameter("regionid"));
		model.addAttribute("Projects", projectLaunchService.getProjectList(request.getParameter("regionid")));
		//model.addAttribute("userlist",vW_UserMasterService.getUserListProjectWise(projectid));
		 return "userprojectmap";
	}
	
	@RequestMapping(value = { "/projectassign"}, method = RequestMethod.GET)
	public String userProjectAssign(ModelMap model,HttpServletRequest request,@RequestParam("regionid") String regionname) {
	 
		model.addAttribute("Projects", projectLaunchService.getProjectList(regionname));//projectLaunchService.getActiveProjectList()
		model.addAttribute("userlist",userMasterService.getActiveUserList()); //vW_UserMasterService.getUserListProjectWise(projectid)
		 return "projectassign";
	}
	@RequestMapping(value = { "/paymentsuccess"}, method = RequestMethod.POST)
	public String paymentsuccess(ModelMap model,HttpServletRequest request) {
		CCGatewayEncDecUtil ccUtil = new CCGatewayEncDecUtil();
		Gson gson = new GsonBuilder().serializeNulls().create();
		System.out.println("enc_response:-"+request.getParameter("encResp"));
		String respDesc = ccUtil.dcriptStr(request.getParameter("encResp").toString(),KeyConstants.WORKINGKEY);
		cCGatwayService.setPaymentSuccessResponse(respDesc);
		 return "paymentsuccess";
	}
	@RequestMapping(value = "/ccpaymentsuccessEcho", method = RequestMethod.POST, headers = {"content-type=application/x-www-form-urlencoded"})
	public String ccGaywayPaymentSuccess(HttpServletRequest request, @RequestParam(required=false,value="idNumber") String idNumber) {
		String enc_response = request.getParameter("encResp");
		Gson gson = new GsonBuilder().serializeNulls().create();//HttpServletRequest request,
		System.out.println("enc_response Echo:-"+enc_response);
		CCGatewayEncDecUtil ccUtil = new CCGatewayEncDecUtil();
		String respDesc = ccUtil.dcriptStr(enc_response,KeyConstants.WORKINGKEY);
		return gson.toJson(respDesc);
	}
	
	@RequestMapping(value = "/ccpaymentsuccessEcho", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String ccGaywayPaymentSuccessformdata(HttpServletRequest request,@RequestParam(required=false,value="idNumber") String idNumber) {
		System.out.println("request:-"+request);
		System.out.println("request:-"+request.getParameter("encResp"));
		Gson gson = new GsonBuilder().serializeNulls().create();//HttpServletRequest request,
		System.out.println("enc_response Echo Form-data:-"+request.getParameter("encResp"));
		CCGatewayEncDecUtil ccUtil = new CCGatewayEncDecUtil();
		String respDesc = ccUtil.dcriptStr(request.getParameter("encResp"),KeyConstants.WORKINGKEY);
		return gson.toJson(respDesc);
	}
/*	@RequestMapping(value = "/ccpaymentsuccessEcho", method = RequestMethod.POST)
	public String ccGaywayPaymentSuccess(HttpServletRequest request,@RequestParam("encResp") String enc_response) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		System.out.println("enc_response Echo:-"+enc_response);
		CCGatewayEncDecUtil ccUtil = new CCGatewayEncDecUtil();
		String respDesc = ccUtil.dcriptStr(enc_response,KeyConstants.WORKINGKEY);
		return gson.toJson(respDesc);
	}*/
	
	@RequestMapping(value = { "/paymentsuccess"}, method = RequestMethod.GET)
	public String paymentsuccessGET(ModelMap model,HttpServletRequest request) {
		CCGatewayEncDecUtil ccUtil = new CCGatewayEncDecUtil();
		Gson gson = new GsonBuilder().serializeNulls().create();
		System.out.println("enc_response:-"+request.getParameter("encResp"));
		String respDesc = ccUtil.dcriptStr(request.getParameter("encResp").toString(),KeyConstants.WORKINGKEY);
		cCGatwayService.setPaymentSuccessResponse(respDesc);
		 return "paymentsuccess";
	}
	@RequestMapping(value = { "/sessiontimeout"}, method = RequestMethod.GET)
	public String sessiontimeout(HttpServletRequest request) {
		
		 return "sessiontimeout";
	}
	
	@RequestMapping(value = { "/kycrole"}, method = RequestMethod.GET)
	public String kycrole(ModelMap model,HttpServletRequest request) {
		String projectSfid = request.getParameter("projectid");
		TemplateDto template = templateService.getTemplate(projectSfid);
		if(template !=null) {
			String templateText =  template.getBigText()==null?"":template.getBigText();
			templateText= templateText.replace("'","\\'");
			model.addAttribute("offerTemplate", templateText);
		}
		return "kycrole";
	}
	@RequestMapping(value = { "/paymentapproval"}, method = RequestMethod.GET)
	public String paymentApproval(ModelMap model,HttpServletRequest request) {
		 return "paymentapproval";
	}

	@RequestMapping(value = { "/chromeerror"}, method = RequestMethod.GET)
	public String chromeerror(ModelMap model,HttpServletRequest request) {
		 return "chromeerror";
	}
	@RequestMapping(value = { "/urlredirect"}, method = RequestMethod.GET)
	public String urlredirect(ModelMap model,HttpServletRequest request) {
		 return "urlredirect";
	}
	
}
