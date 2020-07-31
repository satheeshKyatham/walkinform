package com.godrej.properties.serviceimpl;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccavenue.security.AesCryptUtil;
import com.godrej.kyc.util.StringEncDec;
import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.controller.CCGatewayRequestController;
import com.godrej.properties.dao.CCAvenueGatewayRequestDao;
import com.godrej.properties.dao.EOIPaymentDtlDao;
import com.godrej.properties.dao.GeneratePaymentDao;
import com.godrej.properties.model.CCAvenueGatewayRequest;
import com.godrej.properties.model.CCAvenueResponseModel;
import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.model.GeneratePayment;
import com.godrej.properties.model.ProjectLaunch;
import com.godrej.properties.service.GeneratePaymentService;
import com.godrej.properties.service.ProjectLaunchService;
import com.godrej.properties.util.CommonUtil;

@Service("generatePaymentService")
@Transactional
public class GeneratePaymentServiceImpl implements GeneratePaymentService{
	private Logger Log = LoggerFactory.getLogger(getClass());
	@Autowired
	private GeneratePaymentDao dao;

	@Autowired
	private EOIPaymentDtlDao eOIPaymentDtlDao;
	
	@Autowired
	CCGatewayRequestController ccGatewayRequestController;
	
	@Autowired
	CCAvenueGatewayRequestDao ccAvenueGatewayRequestDao;
	
	@Autowired
 	private ProjectLaunchService projectLaunchService;
	
	@Override
	public Boolean updatePaymentReq(List<GeneratePayment> payReq) {
		  return dao.updatePaymentReq(payReq);
	}
	
	@Override
	public Boolean insertPaymentDtl(List<GeneratePayment> pymtDtl) {
		return dao.insertPaymentDtl(pymtDtl);
	}
	
	@Override
	public List<GeneratePayment> getPaymentRecord(String enqSfid, String projectSFID) {
		return dao.getPaymentRecord(enqSfid, projectSFID);
	}

	@Override
	public GeneratePayment getCCPaymentData(int id) {
		return dao.getCCPaymentData(id);
	}

	@Override
	public String createCCGatewayRequest(GeneratePayment payment) {
		String respReq="";
		
		try {
			//insert into request table 
			//call project data
			ProjectLaunch project = projectLaunchService.getProjectSaleMgrID(payment.getProject_sfid());
			//MerchantID and workingkey pick Preference logic to be develop here
			//Common Utility
			if(project.getTower_mid_access_code_json()!=null)
			{
				//payment.getTowercode().trim()
				project = CommonUtil.getTowerWiseCCAvenueDetails(project,(payment.getTowercode() == null) ? null : payment.getTowercode().trim());
			}
			
			String ccRequestFormat = createCCRequestFormat(payment,project);
			respReq = ccGatewayRequestController.CCGatewayRequestPost(ccRequestFormat,project);
			respReq="ccencrqst="+respReq+"&accesscode="+project.getCcavenue_accesscode();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//call for postData
		return respReq;
	}
	public String createCCRequestFormat(GeneratePayment payment,ProjectLaunch project)
	{
		long tiddate = new Date().getTime();
		CCAvenueGatewayRequest data = new CCAvenueGatewayRequest();
		data.setTid(tiddate);
		/*if(project.getTower_mid_access_code_json()!=null)
		{
			//follow 
		}else
		{*/
		data.setMerchant_id(project.getCcavenue_merchant_id());//218829
		/*}*/
		
		data.setOrder_id(String.valueOf(payment.getId()));
		data.setCurrency("INR");
		data.setAmount(payment.getAmount().doubleValue());
		data.setRedirect_url(KeyConstants.REDIRECT_URL);
		data.setCancel_url(KeyConstants.CANCEL_URL);
		data.setLanguage("EN");
		data.setBilling_name(payment.getCustomer_name());
		data.setBilling_email(payment.getCustomer_email());
		data.setBilling_tel(Long.parseLong(payment.getCustomer_mobile()));
		//billing_tel
		//billing_email
		data.setMerchant_param1(payment.getEnquiry_name());
		data.setMerchant_param2(payment.getEnquiry_sfid());
		data.setMerchant_param3(payment.getProject_sfid());
		data.setMerchant_param4(payment.getTowercode());
		//merchant_param5
		
		String towersfid=(payment.getTowercode() == null) ? null : payment.getTowercode().trim();
		//tid=1585208745371&merchant_id=218829&order_id=123845&currency=INR&amount=1.00&redirect_url=http://kyc.gplapps.com:8084/CCAvenue/jsp/ccavResponseHandler.jsp&cancel_url=http://kyc.gplapps.com:8084/CCAvenue/jsp/ccavResponseHandler.jsp&language=EN&billing_name=Satheesh&billing_address=Worli &billing_city=Mumbai&billing_state=MH&billing_zip=400018&billing_country=India&billing_tel=9987677726&billing_email=sathish.kyatham@godrejproperties.com&delivery_name=Satheesh&delivery_address=Worli&delivery_city=Mumbai&delivery_state=Maharashtra&delivery_zip=400018&delivery_country=India&delivery_tel=9987677726&merchant_param1=0010002345&merchant_param2=GCVRND1208&merchant_param3=1123455&merchant_param4=9987677726&merchant_param5=sathish.kyatham@godrejproperties.com&promo_code=&
		
		String format = "tid="+tiddate+"&merchant_id="+project.getCcavenue_merchant_id()+"&order_id="+payment.getId()+"&currency=INR&amount="+payment.getAmount()+"&redirect_url="+KeyConstants.REDIRECT_URL+"?projectsfid="+payment.getProject_sfid()+""
				+ "&cancel_url="+KeyConstants.CANCEL_URL+"?projectsfid="+payment.getProject_sfid()+""
				+ "&language=EN&billing_name="+payment.getCustomer_name()+"&billing_address=&billing_city=&billing_state=&billing_zip=&billing_country=&billing_tel="+payment.getCustomer_mobile()+""
				+ "&billing_email="+payment.getCustomer_email()+"&delivery_name=&delivery_address=&delivery_city=&delivery_state=&delivery_zip=&delivery_country=&delivery_tel="
				+ "&merchant_param1="+payment.getEnquiry_name()+"&merchant_param2="+payment.getEnquiry_sfid()+"&merchant_param3="+payment.getProject_sfid()+"&merchant_param4="+towersfid+"&merchant_param5=&promo_code=&";
		
		data.setGateway_request(format);
		ccAvenueGatewayRequestDao.insertCCAvenueGatewayRequest(data);
		return format;
	}

	@Override
	public List<GeneratePayment> getPaymentDetails(String enqSfid, String projectSFID) {
		return dao.getPaymentDetailQuery(enqSfid, projectSFID);
		
	}
	@Override
	public String getwayResponseHandler(String response,String projectsfid,String towerCode) {
		Log.info("Payment Response Encript Code:{}",response);
		
		CCAvenueResponseModel respModel = new CCAvenueResponseModel();
		ProjectLaunch project = projectLaunchService.getProjectSaleMgrID(projectsfid);
		
		if(project.getTower_mid_access_code_json()!=null)
		{
			project = CommonUtil.getTowerWiseCCAvenueDetails(project,towerCode);
		}
		//String workingKey = "AC52E9A706E2D7938203D4D554B61E2E";
		AesCryptUtil aesUtil=new AesCryptUtil(project.getCcavenue_workingkey());
		String decResp = aesUtil.decrypt(response);
		Log.info("Payment Response Descript Code:{}",decResp);
		respModel.setGateway_response(decResp);
		StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
		String pair=null, pname=null, pvalue=null;
		while (tokenizer.hasMoreTokens()) {
			pair = (String)tokenizer.nextToken();
			if(pair!=null) {
				StringTokenizer strTok=new StringTokenizer(pair, "=");
				pname=""; pvalue="";
				if(strTok.hasMoreTokens()) {
					pname=(String)strTok.nextToken();
					if(pname.contains("order_id"))
					{
						if(strTok.hasMoreTokens())
							respModel.setOrder_id((String)strTok.nextToken());
					}
					if(pname.contains("tracking_id"))
					{
						if(strTok.hasMoreTokens())
							respModel.setTracking_id(Long.parseLong(strTok.nextToken()));
					}
					if(pname.contains("bank_ref_no"))
					{
						if(strTok.hasMoreTokens())
							respModel.setBank_ref_no((String)strTok.nextToken());
					}
					
					if(pname.contains("order_status"))
					{
						if(strTok.hasMoreTokens())
							respModel.setOrder_status((String)strTok.nextToken());
					}
					if(pname.contains("failure_message"))
					{
						if(strTok.hasMoreTokens())
							respModel.setFailure_message((String)strTok.nextToken());
					}
					if(pname.contains("payment_mode"))
					{
						if(strTok.hasMoreTokens())
							respModel.setPayment_mode((String)strTok.nextToken());
					}
					if(pname.contains("card_name"))
					{
						if(strTok.hasMoreTokens())
							respModel.setCard_name((String)strTok.nextToken());
					}
					/*if(pname.contains("status_code"))
					{
						if(strTok.hasMoreTokens() && strTok.nextToken()!=null)
							respModel.setStatus_code(Integer.parseInt(strTok.nextToken()));
					}*/
					if(pname.contains("status_message"))
					{
						if(strTok.hasMoreTokens())
							respModel.setStatus_message((String)strTok.nextToken());
					}
					if(pname.contains("currency"))
					{
						if(strTok.hasMoreTokens())
							respModel.setResponse_currency((String)strTok.nextToken());
					}
					if(pname.contains("amount"))
					{
						if(strTok.hasMoreTokens())
							respModel.setResponse_amount(new Double(strTok.nextToken()));
					}
					if(pname.contains("vault"))
					{
						if(strTok.hasMoreTokens())
							respModel.setVault((String)strTok.nextToken());
					}
					if(pname.contains("response_code"))
					{
						if(strTok.hasMoreTokens())
							respModel.setResponse_code(Integer.parseInt(strTok.nextToken()));
					}
					if(pname.contains("trans_date"))
					{
						//if(strTok.hasMoreTokens())
							//respModel.setTrans_date(strTok.nextToken());
					}
					if(pname.contains("merchant_param2"))
					{
						if(strTok.hasMoreTokens())
							respModel.setMerchant_param2((String)strTok.nextToken());
					}
					if(pname.contains("merchant_param3"))
					{
						if(strTok.hasMoreTokens())
							respModel.setMerchant_param3((String)strTok.nextToken());
					}
					if(pname.contains("merchant_param4"))
					{
						if(strTok.hasMoreTokens())
							respModel.setMerchant_param4((String)strTok.nextToken());
					}
					if(pname.contains("billing_tel"))
					{
						if(strTok.hasMoreTokens())
							respModel.setBilling_tel((String)strTok.nextToken());
					}
				}
			}
		}
		respModel.setUpdateddate(new Timestamp(System.currentTimeMillis()));
		if(respModel.getOrder_status().equals("Success"))
		{
			EOIPaymentDtl pymtDtl = new EOIPaymentDtl();
			pymtDtl.setEnq_sfid(respModel.getMerchant_param2());
			pymtDtl.setPayment_type("Wire Transfer");
			pymtDtl.setBank_name(respModel.getCard_name());
			String respAmount="";
			if(respModel.getResponse_amount()!=null)
			{
				respAmount=respModel.getResponse_amount().toString();
			}
			else
				respAmount="0.00";
			pymtDtl.setTransaction_amount(respAmount);
			/*if(respModel.getTrans_date()!=null)
				pymtDtl.setTransaction_date(respModel.getTrans_date().toString());*/
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			pymtDtl.setTransaction_date(timeStamp);
			pymtDtl.setTransaction_id(String.valueOf(respModel.getTracking_id()));
			pymtDtl.setDescription(respModel.getPayment_mode());
			pymtDtl.setProject_sfid(respModel.getMerchant_param3());
			pymtDtl.setIsactive("Y");
			pymtDtl.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			pymtDtl.setPayment_mode("Online Payment");
			eOIPaymentDtlDao.insertOnePaymentDtl(pymtDtl);
		}
		/*else
		{
			//Update status in atul payment table
			
		}*/
		ccAvenueGatewayRequestDao.updateCCAvenueResponse(respModel);
		String respStatus ="";
		if(respModel.getOrder_status().equals("Success"))
			respStatus="1";
		else if(respModel.getOrder_status().equals("Failure"))
			respStatus="2";
		else if(respModel.getOrder_status().equals("Aborted"))
			respStatus="3";
		else if(respModel.getOrder_status().equals("Invalid"))
			respStatus="4";
		else
			respStatus="4";
		return "num="+StringEncDec.encrypt(respModel.getBilling_tel())+"&projectSFID="+respModel.getMerchant_param3()+"&enqSfid="+respModel.getMerchant_param2()+"&respid="+respStatus+"&amt="+respModel.getResponse_amount();
	}

	@Override
	public CCAvenueGatewayRequest getCCAvenueRequest(String orderid) {
		return ccAvenueGatewayRequestDao.getCCAvenueRequest(orderid);
	}
}