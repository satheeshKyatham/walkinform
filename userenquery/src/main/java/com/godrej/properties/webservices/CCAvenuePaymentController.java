package com.godrej.properties.webservices;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godrej.properties.model.CCAvenueGatewayRequest;
import com.godrej.properties.service.GeneratePaymentService;
import com.godrej.properties.service.ProjectLaunchService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class CCAvenuePaymentController {
	private Logger Log = LoggerFactory.getLogger(getClass());
	

	@Autowired
	private GeneratePaymentService generatePaymentService;
	
	@Autowired
	private ProjectLaunchService projectLaunchService;
	
	
	@RequestMapping(value = { "/ccAvenue"}, method = RequestMethod.GET)
	public String ccAvenue(ModelMap model,HttpServletRequest request) {
		 return "ccAvenue";
	}
	
	@RequestMapping(value = { "/ccAvenueLogin"}, method = RequestMethod.GET)
	public String ccAvenueLogin(ModelMap model,HttpServletRequest request) {
		
		 return "ccAvenueLogin";
	}
	
	@RequestMapping(value = "/getPaymentReqRecord", method = RequestMethod.GET)
	public @ResponseBody String getPaymentReqRecord(@RequestParam("enqSfid") String enqSfid, @RequestParam("projectid") String projectSFID) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		try {
			if(!enqSfid.equals("")  && !projectSFID.equals(""))  { 
				return gson.toJson(generatePaymentService.getPaymentDetails(enqSfid, projectSFID));
			} else {
				String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"ER1001\"}";
				return response;
			}
		} catch (Exception e) {
			Log.info("Payment Request - Getting error while fetching data Error:- ",e);
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Getting error while fetching data\",\"error_id\":\"ER1001\"}";
			return response;
		}
	}



	@GetMapping(value = { "/ccavRequestHandler"})
	public String test(ModelMap model,HttpServletRequest request) {
		 return "ccavRequestHandler";
	}
	@PostMapping(value = { "/ccavResponseHandler"})
	public String ccavResponseHandlerPost(ModelMap model,HttpServletRequest request) {
//		return "ccAvenueLogin";//merchant_param4
		Log.info("Query String : "+request.getQueryString());
		//Log.info("Query merchant_param4 : "+request.getParameter("merchant_param4"));
		Log.info("orderNo : "+request.getParameter("orderNo"));
		CCAvenueGatewayRequest ccResp = generatePaymentService.getCCAvenueRequest(request.getParameter("orderNo"));
		/*Set<String> paramNames = request.getParameterMap().keySet();
        // iterating over parameter names and get its value
        for (String name : paramNames) {
            String value = request.getParameter(name);
            Log.info(name + ": " + value);
        }*/
		String resp = generatePaymentService.getwayResponseHandler(request.getParameter("encResp"),request.getParameter("projectsfid"),ccResp.getMerchant_param4().toString());
		return "redirect:/ccAvenue?"+resp;
	}
	
	
	
}
