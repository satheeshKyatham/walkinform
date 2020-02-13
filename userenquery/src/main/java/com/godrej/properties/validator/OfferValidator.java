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
		
		BigDecimal amount  = priceBD.multiply(new BigDecimal(value)).divide(new BigDecimal(100), 0, RoundingMode.UP);
		if("Y".equalsIgnoreCase(doIncludeGST)) {
			BigDecimal taxPercentage =  getTaxPercentage(paymentRequest);
			BigDecimal taxAmount =  (amount.multiply(taxPercentage)).divide(BigDecimal.valueOf(100d),0,RoundingMode.UP);
			amount = amount.add(taxAmount);
		}
		PaymentDto []payments =  paymentRequest.getPayments();
		Double paymentAmount = validatePayments(payments, errors);
		if(errors.getErrorCount()>0) {
			return;
		}
		

		if(paymentAmount.doubleValue() < amount.doubleValue()) {
			validator.reject(errors, INVALID_PAYMENTS, "Payment amount is not valid - " + paymentAmount + " - It shouble be -" +amount);
		}
	}
	
	private BigDecimal getTaxPercentage(PaymentRequestDto paymentRequest) {
		Double amount = paymentRequest.getPlannedPayment();
	
		Double taxAmount =  paymentRequest.getTokenTax();

		if(taxAmount ==null || amount==null || amount.doubleValue() <=0) {
			return new BigDecimal(0);
		}
		return BigDecimal.valueOf(taxAmount*100).divide(new BigDecimal(amount),4);
	}
	
	private Double validatePayments(PaymentDto []payments, Errors errors) {
		if(payments ==null || payments.length<=0) {
			validator.reject(errors, INVALID_PAYMENTS, "Invalid Payment Details");
			return 0d;
		}
		Double totalPayment = 0d;
		try {
			for(PaymentDto payment : payments) {
				String trxAmount = payment.getTransactionAmount();
				if(trxAmount == null) {
					continue;
				}
				
				totalPayment = totalPayment + Double.valueOf(trxAmount);
			}
			return totalPayment;
		}catch (Exception e) {
			log.error("Error", e);
			validator.reject(errors, INVALID_PAYMENTS, "Payment amount is not valid - " + totalPayment);
		}
		return 0d;
	}
	
	private void validateFromPlan(PaymentRequestDto paymentRequest, Errors errors, String projectSfid) {
		Double price = paymentRequest.getPlannedPayment();
		Double priceWithTax =  paymentRequest.getPlannedPaymentWithTax();
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
			double difference = price.doubleValue() - paymentAmount.doubleValue();
			StringBuilder message = new StringBuilder();
			message.append("Payment entered (Rs. ")
			.append(paymentAmount)
			.append("/-) is less than the booking amount (Rs. ")
			.append(price)
			.append("/-). Please add remaining amount difference (Rs. ")
			.append(difference)
			.append("/-)");
			validator.reject(errors, INVALID_PAYMENTS, message.toString());
		}
	}
	
	
}
