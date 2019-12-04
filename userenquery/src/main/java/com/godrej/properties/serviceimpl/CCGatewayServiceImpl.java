package com.godrej.properties.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.CCGatewayDao;
import com.godrej.properties.dao.CCGatewayPaymentSuccessDao;
import com.godrej.properties.dao.CCGatewayResponseDao;
import com.godrej.properties.model.CCGatewayPaymentResp;
import com.godrej.properties.model.CCGatewayRequest;
import com.godrej.properties.model.CCGatewayResponse;
import com.godrej.properties.service.CCGatewayService;

@Service("cCGatwayService")
@Transactional
public class CCGatewayServiceImpl implements CCGatewayService{

	@Autowired
	CCGatewayDao cCGatwayDao;
	
	@Autowired
	CCGatewayResponseDao cCGatwayRequestDao;
	
	@Autowired
	CCGatewayPaymentSuccessDao cCGatewayPaymentSuccessDao;
	
	@Override
	public CCGatewayRequest insertRequest(CCGatewayRequest ccRequest) {
		// TODO Auto-generated method stub
		return cCGatwayDao.insertRequest(ccRequest);
	}

	@Override
	public CCGatewayResponse insertResponse(CCGatewayResponse ccResponse) {
		// TODO Auto-generated method stub
		return cCGatwayRequestDao.insertResponse(ccResponse);
	}

	@Override
	public CCGatewayResponse getTinyURL(String orderID) {
		// TODO Auto-generated method stub
		return cCGatwayRequestDao.getTinyURL(orderID);
	}

	@Override
	public String setPaymentSuccessResponse(String paymentResp) {
		String[] params = paymentResp.split("&");
	    Map<String, String> map = new HashMap<String, String>();
	    for (String param : params)
	    {
	        String name = param.split("=")[0];
	        String value="";
	        if(param.split("=").length>1)
	        {
	        	 value = param.split("=")[1];
	        }
	        map.put(name, value);
	    }
		
			//JSONObject successJson = new JSONObject(paymentResp);
			CCGatewayPaymentResp respInsert = new CCGatewayPaymentResp();
			
			respInsert.setOrder_id(map.get("order_id"));
			respInsert.setTracking_id(new Long(map.get("tracking_id")));
			respInsert.setBank_ref_no(map.get("bank_ref_no"));
			respInsert.setOrder_status(map.get("order_status"));
			respInsert.setFailure_message(map.get("failure_message"));
			respInsert.setPayment_mode(map.get("payment_mode"));
			respInsert.setCard_name(map.get("card_name"));
			if(map.get("status_code")!=null)
				respInsert.setStatus_code(Integer.parseInt(map.get("status_code")==""?"0":map.get("status_code")));
			else
				respInsert.setStatus_code(0);
			respInsert.setStatus_message(map.get("status_message"));
			respInsert.setCurrency(map.get("currency"));
			respInsert.setAmount(new Double(map.get("amount")));
			respInsert.setBilling_name(map.get("billing_name"));
			respInsert.setBilling_address(map.get("billing_address"));
			respInsert.setBilling_city(map.get("billing_city"));
			respInsert.setBilling_state(map.get("billing_state"));
			respInsert.setBilling_zip(map.get("billing_zip"));
			respInsert.setBilling_country(map.get("billing_country"));
			respInsert.setBilling_tel(map.get("billing_tel"));
			respInsert.setBilling_email(map.get("billing_email"));
			respInsert.setDelivery_name(map.get("delivery_name"));
			respInsert.setDelivery_address(map.get("delivery_address"));
			respInsert.setDelivery_city(map.get("delivery_city"));
			respInsert.setDelivery_state(map.get("delivery_state"));
			respInsert.setDelivery_zip(map.get("delivery_zip"));
			respInsert.setDelivery_country(map.get("delivery_country"));
			respInsert.setDelivery_tel(map.get("delivery_tel"));
			respInsert.setMerchant_param1(map.get("merchant_param1"));
			respInsert.setMerchant_param2(map.get("merchant_param2"));
			respInsert.setMerchant_param3(map.get("merchant_param3"));
			respInsert.setMerchant_param4(map.get("merchant_param4"));
			respInsert.setMerchant_param5(map.get("merchant_param5"));
			respInsert.setVault(map.get("vault"));
			respInsert.setOffer_type(map.get("offer_type"));
			respInsert.setOffer_code(map.get("offer_code"));
			respInsert.setDiscount_value(new Double(map.get("discount_value")));
			cCGatewayPaymentSuccessDao.setPaymentSuccessResponse(respInsert);
		
		
		return null;
	}

	public String checkNull(String inputVal)
	{
		if(inputVal!=null && inputVal.length()>0)
			return inputVal;
		else
			return "";
	}
	
}
