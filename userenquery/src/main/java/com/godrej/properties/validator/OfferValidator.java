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
		}else if("CONFIGAMT".equalsIgnoreCase(validationSource)) {
			validateFromConfigAmount(paymentRequest, errors, paymentRequest.getProjectSfid());
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
		
		BigDecimal amount  = priceBD.multiply(new BigDecimal(value)).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
		if("Y".equalsIgnoreCase(doIncludeGST)) {
			BigDecimal taxPercentage =  getTaxPercentage(paymentRequest);
			BigDecimal taxAmount =  (amount.multiply(taxPercentage)).divide(BigDecimal.valueOf(100d),2,RoundingMode.HALF_UP);
			amount = amount.add(taxAmount);
		}
		PaymentDto []payments =  paymentRequest.getPayments();
		BigDecimal paymentAmount = validatePayments(payments, errors);
		if(errors.getErrorCount()>0) {
			return;
		}
		

		if(paymentAmount.doubleValue() < amount.doubleValue()) {
			
			//double difference = amount.doubleValue() - paymentAmount.doubleValue();
			BigDecimal difference = amount.subtract(paymentAmount);
			
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
	
	private BigDecimal getTaxPercentage(PaymentRequestDto paymentRequest) {
		
		BigDecimal zero, hundred;
		zero   = new BigDecimal("0"); 
		hundred = new BigDecimal("100");
		
		Double amount = paymentRequest.getPlannedPayment();
		Double taxAmount =  paymentRequest.getTokenTax();

		if(taxAmount ==null || amount==null || amount.doubleValue() <=0) {
			return new BigDecimal(0);
		}
		
		BigDecimal amountBD = BigDecimal.valueOf(amount);
		BigDecimal taxAmountBD = BigDecimal.valueOf(taxAmount);
		
		BigDecimal taxPer = (taxAmountBD.multiply(hundred)).divide(amountBD,6, RoundingMode.HALF_UP);
		
		//return BigDecimal.valueOf(taxAmount*100).divide(new BigDecimal(amount),4);
		return taxPer;
	}
	
	private BigDecimal validatePayments(PaymentDto []payments, Errors errors) {
		BigDecimal zero;
		zero   = new BigDecimal("0"); 
		
		if(payments ==null || payments.length<=0) {
			validator.reject(errors, INVALID_PAYMENTS, "Invalid Payment Details");
			return zero;
		}
		//Double totalPayment = 0d;
		
		BigDecimal totalPayment = new BigDecimal("0");
		
		try {
			for(PaymentDto payment : payments) {
				String trxAmount = payment.getTransactionAmount();
				if(trxAmount == null) {
					continue;
				}
				
				totalPayment = totalPayment.add(new BigDecimal(trxAmount));
			}
			return totalPayment;
		}catch (Exception e) {
			log.error("Error", e);
			validator.reject(errors, INVALID_PAYMENTS, "Payment amount is not valid - " + totalPayment);
		}
		return zero;
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
		BigDecimal paymentAmount = validatePayments(payments, errors);
		if(errors.getErrorCount()>0) {
			return;
		}
		if(paymentAmount.doubleValue() < price.doubleValue()) {
			//double difference = price.doubleValue() - paymentAmount.doubleValue();
			BigDecimal difference = BigDecimal.valueOf(price).subtract(paymentAmount);
			
			
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

	private void validateFromConfigAmount(PaymentRequestDto paymentRequest, Errors errors, String projectSfid) {
		
		Double configAmount =sysConfigService.getValueAsDouble(SysConfigEnum.OFFER_CREATION_CONFIG_AMOUNT, projectSfid);
		PaymentDto []payments =  paymentRequest.getPayments();
		BigDecimal paymentAmount = validatePayments(payments, errors);
		if(errors.getErrorCount()>0) {
			return;
		}
		if(paymentAmount.doubleValue() < configAmount) {
			//double difference = configAmount - paymentAmount.doubleValue();
			BigDecimal difference = BigDecimal.valueOf(configAmount).subtract(paymentAmount);
			
			StringBuilder message = new StringBuilder();
			message.append("Payment entered (Rs. ")
			.append(paymentAmount)
			.append("/-) is less than the booking amount (Rs. ")
			.append(configAmount)
			.append("/-). Please add remaining amount difference (Rs. ")
			.append(difference)
			.append("/-)");
			validator.reject(errors, INVALID_PAYMENTS, message.toString());
		}
	}

	
}
