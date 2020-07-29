package com.godrej.properties.model;

import java.sql.Timestamp;

public class CCAvenueResponseModel {

	public String order_id;
	public String gateway_response;
	public long tracking_id;
	public String bank_ref_no;
	public String order_status;
	public String failure_message;
	public String payment_mode;
	public String card_name;
	public Integer status_code;
	public String status_message;
	public String response_currency;
	public Double response_amount;
	public String vault;
	public Integer response_code;
	public Timestamp trans_date;
	public Timestamp updateddate;
	public String isactive;
	public String merchant_param2;
	public String merchant_param3;
	public String merchant_param4;
	public String billing_tel;
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getGateway_response() {
		return gateway_response;
	}
	public void setGateway_response(String gateway_response) {
		this.gateway_response = gateway_response;
	}


	public long getTracking_id() {
		return tracking_id;
	}
	public void setTracking_id(long tracking_id) {
		this.tracking_id = tracking_id;
	}
	public String getBank_ref_no() {
		return bank_ref_no;
	}
	public void setBank_ref_no(String bank_ref_no) {
		this.bank_ref_no = bank_ref_no;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getFailure_message() {
		return failure_message;
	}
	public void setFailure_message(String failure_message) {
		this.failure_message = failure_message;
	}
	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	public String getCard_name() {
		return card_name;
	}
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	public Integer getStatus_code() {
		return status_code;
	}
	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}
	public String getStatus_message() {
		return status_message;
	}
	public void setStatus_message(String status_message) {
		this.status_message = status_message;
	}
	public String getResponse_currency() {
		return response_currency;
	}
	public void setResponse_currency(String response_currency) {
		this.response_currency = response_currency;
	}
	public Double getResponse_amount() {
		return response_amount;
	}
	public void setResponse_amount(Double response_amount) {
		this.response_amount = response_amount;
	}
	public String getVault() {
		return vault;
	}
	public void setVault(String vault) {
		this.vault = vault;
	}
	public Integer getResponse_code() {
		return response_code;
	}
	public void setResponse_code(Integer response_code) {
		this.response_code = response_code;
	}
	public Timestamp getTrans_date() {
		return trans_date;
	}
	public void setTrans_date(Timestamp trans_date) {
		this.trans_date = trans_date;
	}
	public Timestamp getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(Timestamp updateddate) {
		this.updateddate = updateddate;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public String getMerchant_param2() {
		return merchant_param2;
	}
	public void setMerchant_param2(String merchant_param2) {
		this.merchant_param2 = merchant_param2;
	}
	public String getMerchant_param3() {
		return merchant_param3;
	}
	public void setMerchant_param3(String merchant_param3) {
		this.merchant_param3 = merchant_param3;
	}
	public String getBilling_tel() {
		return billing_tel;
	}
	public void setBilling_tel(String billing_tel) {
		this.billing_tel = billing_tel;
	}
	public String getMerchant_param4() {
		return merchant_param4;
	}
	public void setMerchant_param4(String merchant_param4) {
		this.merchant_param4 = merchant_param4;
	}
	
}


