package com.godrej.properties.validator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godrej.properties.core.dto.Errors;
import com.godrej.properties.core.validator.Validator;
import com.godrej.properties.dto.PaymentDto;
import com.godrej.properties.dto.PaymentRequestDto;
import com.godrej.properties.dto.SysConfigEnum;
import com.godrej.properties.master.service.SysConfigService;
import com.godrej.properties.util.ValidationUtil;

@Component
public class OfferValidator implements Validator{

	
	@Autowired
	private ValidationUtil validator;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	private static final String INVALID_PAYMENTS ="invalid.payments";
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(PaymentRequestDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		
		PaymentRequestDto paymentRequest = (PaymentRequestDto)object;
		validatePayment(paymentRequest, errors);
	}
	
	public void validatePayment(PaymentRequestDto paymentRequest,Errors errors) {
	
		if(paymentRequest == null) {
			validator.reject(errors, "invalid.data", "payment request can not be null");
			return;
		}
		
		String projectSfid = paymentRequest.getProjectSfid();
		if(projectSfid==null) {
			validator.reject(errors, "invalid.project", "project is required");
			return;
		}
		
		String validationSource = sysConfigService.getValue(SysConfigEnum.OFFER_CREATION_VALIDATION_SOURCE, projectSfid);
		
		processValidation(paymentRequest, errors, validationSource);
	}

	public void processValidation(PaymentRequestDto paymentRequest,Errors errors, String validationSource) {
		if(validationSource == null) {
			return;
		}
		PaymentDto []payments =  paymentRequest.getPayments();
		if(payments==null) {
			validator.reject(errors, "invalid.data", "At least on payment is required");
			return;
		}

		if("CONFIG".equalsIgnoreCase(validationSource)) {
			 validateFromConfig(paymentRequest, errors, paymentRequest.getProjectSfid());
		}else if ("PAYPLAN".equalsIgnoreCase(validationSource)) {
			validateFromPlan(paymentRequest, errors, paymentRequest.getProjectSfid());
		}
		
	}
	
	private void validateFromConfig(PaymentRequestDto paymentRequest, Errors errors, String projectSfid) {
		Double value = sysConfigService.getValueAsDouble(SysConfigEnum.OFFER_CREATION_VALIDATION_PERC, projectSfid);
		if(value == null || value.doubleValue()==0) {
			return;
		}
		Double price = paymentRequest.getPrice();
		Double priceWithTax =  paymentRequest.getPriceWithTax();
		if(price == null || priceWithTax == null) {
			validator.reject(errors, "invalid.data", "Price cannot be null");
			return;	
		}
		String doIncludeGST =sysConfigService.getValue(SysConfigEnum.PREPAYMENT_INCLUDE_GST, projectSfid);
		BigDecimal priceBD = new BigDecimal(price);
		if("Y".equalsIgnoreCase(doIncludeGST)) {
			priceBD = new BigDecimal(priceWithTax);
		}
		BigDecimal amount  = priceBD.divide(new BigDecimal(value), 0, RoundingMode.UP);
		PaymentDto []payments =  paymentRequest.getPayments();
		Double paymentAmount = validatePayments(payments, errors);
		if(errors.getErrorCount()>0) {
			return;
		}
		if(paymentAmount.doubleValue() < amount.doubleValue()) {
			validator.reject(errors, INVALID_PAYMENTS, "Payment amount is not valid - " + paymentAmount + " - It shouble be -" +amount);
		}
	}
	
	private Double validatePayments(PaymentDto []payments, Errors errors) {
		if(payments ==null || payments.length<=0) {
			validator.reject(errors, INVALID_PAYMENTS, "Invalid Payment Details");
			return 0d;
		}
		String paymentAmount =payments[0].getTotalAmount();
		if(paymentAmount == null) {
			validator.reject(errors, INVALID_PAYMENTS, "Payment amount is not valid");
		}
		try {
			return Double.valueOf(paymentAmount);
		}catch (Exception e) {
			log.error("Error", e);
			validator.reject(errors, INVALID_PAYMENTS, "Payment amount is not valid - " + paymentAmount);
		}
		return 0d;
	}
	
	private void validateFromPlan(PaymentRequestDto paymentRequest, Errors errors, String projectSfid) {
		Double price = paymentRequest.getPrice();
		Double priceWithTax =  paymentRequest.getPriceWithTax();
		if(price == null || priceWithTax == null) {
			validator.reject(errors, "invalid.data", "Price cannot be null");
			return;	
		}
		String doIncludeGST =sysConfigService.getValue(SysConfigEnum.PREPAYMENT_INCLUDE_GST, projectSfid);
		if("Y".equalsIgnoreCase(doIncludeGST)) {
			price = priceWithTax;
		}

		PaymentDto []payments =  paymentRequest.getPayments();
		Double paymentAmount = validatePayments(payments, errors);
		if(errors.getErrorCount()>0) {
			return;
		}
		if(paymentAmount.doubleValue() < price.doubleValue()) {
			validator.reject(errors, INVALID_PAYMENTS, "Payment amount is not valid - " + paymentAmount + " - It shouble be -" +price);
		}
	}
	
	
}
