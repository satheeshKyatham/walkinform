package com.godrej.properties.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.gpl_cc_gateway_req_resp")
public class CCAvenueGatewayRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") private int id;	
	@Column(name = "tid") private long tid;
	@Column(name = "merchant_id") private Integer merchant_id;
	@Column(name = "order_id") private String order_id;
	@Column(name = "currency") private String currency;
	@Column(name = "amount") private Double amount;
	@Column(name = "redirect_url") private String redirect_url;
	@Column(name = "cancel_url") private String cancel_url;
	@Column(name = "language") private String language;
	@Column(name = "billing_name") private String billing_name;
	@Column(name = "billing_tel") private Integer billing_tel;
	@Column(name = "billing_email") private String billing_email;
	@Column(name = "merchant_param1") private String merchant_param1;
	@Column(name = "merchant_param2") private String merchant_param2;
	@Column(name = "merchant_param3") private String merchant_param3;
	@Column(name = "merchant_param4") private String merchant_param4;
	@Column(name = "merchant_param5") private String merchant_param5;
	@Column(name = "promo_code") private String promo_code;
	@Column(name = "customer_identifier") private String customer_identifier;
	@Column(name = "gateway_request") private String gateway_request;
	@Column(name = "gateway_response") private String gateway_response;
	@Column(name = "tracking_id") private Integer tracking_id;
	@Column(name = "bank_ref_no") private String bank_ref_no;
	@Column(name = "order_status") private String order_status;
	@Column(name = "failure_message") private String failure_message;
	@Column(name = "payment_mode") private String payment_mode;
	@Column(name = "card_name") private String card_name;
	@Column(name = "status_code") private Integer status_code;
	@Column(name = "status_message") private String status_message;
	@Column(name = "response_currency") private String response_currency;
	@Column(name = "response_amount") private Double response_amount;
	@Column(name = "vault") private String vault;
	@Column(name = "response_code") private Integer response_code;
	@Column(name = "trans_date") private Timestamp trans_date;
	@Column(name = "createddate") private Timestamp createddate;
	@Column(name = "updateddate") private Timestamp updateddate;
	@Column(name = "isactive") private String isactive;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public long getTid() {
		return tid;
	}
	public void setTid(long tid) {
		this.tid = tid;
	}
	public Integer getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(Integer merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getRedirect_url() {
		return redirect_url;
	}
	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}
	public String getCancel_url() {
		return cancel_url;
	}
	public void setCancel_url(String cancel_url) {
		this.cancel_url = cancel_url;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getBilling_name() {
		return billing_name;
	}
	public void setBilling_name(String billing_name) {
		this.billing_name = billing_name;
	}
	public Integer getBilling_tel() {
		return billing_tel;
	}
	public void setBilling_tel(Integer billing_tel) {
		this.billing_tel = billing_tel;
	}
	public String getBilling_email() {
		return billing_email;
	}
	public void setBilling_email(String billing_email) {
		this.billing_email = billing_email;
	}
	public String getMerchant_param1() {
		return merchant_param1;
	}
	public void setMerchant_param1(String merchant_param1) {
		this.merchant_param1 = merchant_param1;
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
	public String getMerchant_param4() {
		return merchant_param4;
	}
	public void setMerchant_param4(String merchant_param4) {
		this.merchant_param4 = merchant_param4;
	}
	public String getMerchant_param5() {
		return merchant_param5;
	}
	public void setMerchant_param5(String merchant_param5) {
		this.merchant_param5 = merchant_param5;
	}
	public String getPromo_code() {
		return promo_code;
	}
	public void setPromo_code(String promo_code) {
		this.promo_code = promo_code;
	}
	public String getCustomer_identifier() {
		return customer_identifier;
	}
	public void setCustomer_identifier(String customer_identifier) {
		this.customer_identifier = customer_identifier;
	}
	public String getGateway_request() {
		return gateway_request;
	}
	public void setGateway_request(String gateway_request) {
		this.gateway_request = gateway_request;
	}
	public String getGateway_response() {
		return gateway_response;
	}
	public void setGateway_response(String gateway_response) {
		this.gateway_response = gateway_response;
	}
	public Integer getTracking_id() {
		return tracking_id;
	}
	public void setTracking_id(Integer tracking_id) {
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
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
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

	
	
}
