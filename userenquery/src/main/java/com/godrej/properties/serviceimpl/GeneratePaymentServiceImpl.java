package com.godrej.properties.serviceimpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.controller.CCGatewayRequestController;
import com.godrej.properties.dao.CCAvenueGatewayRequestDao;
import com.godrej.properties.dao.GeneratePaymentDao;
import com.godrej.properties.model.CCAvenueGatewayRequest;
import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.model.GeneratePayment;
import com.godrej.properties.service.GeneratePaymentService;

@Service("generatePaymentService")
@Transactional
public class GeneratePaymentServiceImpl implements GeneratePaymentService{
	private Logger Log = LoggerFactory.getLogger(getClass());
	@Autowired
	private GeneratePaymentDao dao;

	@Autowired
	CCGatewayRequestController ccGatewayRequestController;
	
	@Autowired
	CCAvenueGatewayRequestDao ccAvenueGatewayRequestDao;
	
	@Override
	public void updatePaymentReq(List<GeneratePayment> payReq) {
		  dao.updatePaymentReq(payReq);
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
			
			String ccRequestFormat = createCCRequestFormat(payment);
			respReq = ccGatewayRequestController.CCGatewayRequestPost(ccRequestFormat);
			
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
	public String createCCRequestFormat(GeneratePayment payment)
	{
		long tiddate = new Date().getTime();
		CCAvenueGatewayRequest data = new CCAvenueGatewayRequest();
		data.setTid(tiddate);
		data.setMerchant_id(218829);
		data.setOrder_id(String.valueOf(payment.getId()));
		data.setCurrency("INR");
		data.setAmount(payment.getAmount().doubleValue());
		data.setRedirect_url(KeyConstants.REDIRECT_URL);
		data.setCancel_url(KeyConstants.CANCEL_URL);
		data.setLanguage("EN");
		//billing_name
		//billing_tel
		//billing_email
		data.setMerchant_param1(payment.getEnquiry_name());
		data.setMerchant_param2(payment.getEnquiry_sfid());
		data.setMerchant_param3(payment.getProject_sfid());
		//merchant_param4
		//merchant_param5
		
		
		//tid=1585208745371&merchant_id=218829&order_id=123845&currency=INR&amount=1.00&redirect_url=http://kyc.gplapps.com:8084/CCAvenue/jsp/ccavResponseHandler.jsp&cancel_url=http://kyc.gplapps.com:8084/CCAvenue/jsp/ccavResponseHandler.jsp&language=EN&billing_name=Satheesh&billing_address=Worli &billing_city=Mumbai&billing_state=MH&billing_zip=400018&billing_country=India&billing_tel=9987677726&billing_email=sathish.kyatham@godrejproperties.com&delivery_name=Satheesh&delivery_address=Worli&delivery_city=Mumbai&delivery_state=Maharashtra&delivery_zip=400018&delivery_country=India&delivery_tel=9987677726&merchant_param1=0010002345&merchant_param2=GCVRND1208&merchant_param3=1123455&merchant_param4=9987677726&merchant_param5=sathish.kyatham@godrejproperties.com&promo_code=&
		
		String format = "tid="+tiddate+"&merchant_id=218829&order_id="+payment.getId()+"&currency=INR&amount="+payment.getAmount()+"&redirect_url=http://kyc.gplapps.com:8084/CCAvenue/jsp/ccavResponseHandler.jsp"
				+ "&cancel_url=http://kyc.gplapps.com:8084/CCAvenue/jsp/ccavResponseHandler.jsp"
				+ "&language=EN&billing_name=Satheesh&billing_address=&billing_city=&billing_state=&billing_zip=&billing_country=&billing_tel=9987677726"
				+ "&billing_email=sathish.kyatham@godrejproperties.com&delivery_name=&delivery_address=&delivery_city=&delivery_state=&delivery_zip=&delivery_country=&delivery_tel="
				+ "&merchant_param1="+payment.getEnquiry_name()+"&merchant_param2="+payment.getEnquiry_sfid()+"&merchant_param3="+payment.getProject_sfid()+"&merchant_param4=&merchant_param5=&promo_code=&";
		
		data.setGateway_request(format);
		ccAvenueGatewayRequestDao.insertCCAvenueGatewayRequest(data);
		return format;
	}

	@Override
	public List<GeneratePayment> getPaymentDetails(String enqSfid, String projectSFID) {
		return dao.getPaymentDetailQuery(enqSfid, projectSFID);
		
	}
}