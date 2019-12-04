package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.nv_cc_gateway_payment_resp")
public class CCGatewayPaymentResp {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="payment_resp_id") private  int payment_resp_id;
	@Column(name="tracking_id") private  long tracking_id;
	@Column(name="order_id") private  String order_id;
	@Column(name="bank_ref_no") private  String bank_ref_no;
	@Column(name="order_status") private  String order_status;
	@Column(name="failure_message") private  String failure_message;
	@Column(name="payment_mode") private  String payment_mode;
	@Column(name="card_name") private  String card_name;
	@Column(name="status_code") private  int status_code;
	@Column(name="status_message") private  String status_message;
	@Column(name="currency") private  String currency;
	@Column(name="amount") private  double amount;
	@Column(name="billing_name") private  String billing_name;
	@Column(name="billing_address") private  String billing_address;
	@Column(name="billing_city") private  String billing_city;
	@Column(name="billing_state") private  String billing_state;
	@Column(name="billing_zip") private  String billing_zip;
	@Column(name="billing_country") private  String billing_country;
	@Column(name="billing_tel") private  String billing_tel;
	@Column(name="billing_email") private  String billing_email;
	@Column(name="delivery_name") private  String delivery_name;
	@Column(name="delivery_address") private  String delivery_address;
	@Column(name="delivery_city") private  String delivery_city;
	@Column(name="delivery_state") private  String delivery_state;
	@Column(name="delivery_zip") private  String delivery_zip;
	@Column(name="delivery_country") private  String delivery_country;
	@Column(name="delivery_tel") private  String delivery_tel;
	@Column(name="merchant_param1") private  String merchant_param1;
	@Column(name="merchant_param2") private  String merchant_param2;
	@Column(name="merchant_param3") private  String merchant_param3;
	@Column(name="merchant_param4") private  String merchant_param4;
	@Column(name="merchant_param5") private  String merchant_param5;
	@Column(name="vault") private  String vault;
	@Column(name="offer_type") private  String offer_type;
	@Column(name="offer_code") private  String offer_code;
	@Column(name="discount_value") private  double discount_value;
	@Column(name="paymentdate") private  Timestamp paymentdate;
	public int getPayment_resp_id() {
		return payment_resp_id;
	}
	public void setPayment_resp_id(int payment_resp_id) {
		this.payment_resp_id = payment_resp_id;
	}

	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
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
	public int getStatus_code() {
		return status_code;
	}
	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}
	public String getStatus_message() {
		return status_message;
	}
	public void setStatus_message(String status_message) {
		this.status_message = status_message;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getBilling_name() {
		return billing_name;
	}
	public void setBilling_name(String billing_name) {
		this.billing_name = billing_name;
	}
	public String getBilling_address() {
		return billing_address;
	}
	public void setBilling_address(String billing_address) {
		this.billing_address = billing_address;
	}
	public String getBilling_city() {
		return billing_city;
	}
	public void setBilling_city(String billing_city) {
		this.billing_city = billing_city;
	}
	public String getBilling_state() {
		return billing_state;
	}
	public void setBilling_state(String billing_state) {
		this.billing_state = billing_state;
	}
	public String getBilling_zip() {
		return billing_zip;
	}
	public void setBilling_zip(String billing_zip) {
		this.billing_zip = billing_zip;
	}
	public String getBilling_country() {
		return billing_country;
	}
	public void setBilling_country(String billing_country) {
		this.billing_country = billing_country;
	}
	public String getBilling_tel() {
		return billing_tel;
	}
	public void setBilling_tel(String billing_tel) {
		this.billing_tel = billing_tel;
	}
	public String getBilling_email() {
		return billing_email;
	}
	public void setBilling_email(String billing_email) {
		this.billing_email = billing_email;
	}
	public String getDelivery_name() {
		return delivery_name;
	}
	public void setDelivery_name(String delivery_name) {
		this.delivery_name = delivery_name;
	}
	public String getDelivery_address() {
		return delivery_address;
	}
	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}
	public String getDelivery_city() {
		return delivery_city;
	}
	public void setDelivery_city(String delivery_city) {
		this.delivery_city = delivery_city;
	}
	public String getDelivery_state() {
		return delivery_state;
	}
	public void setDelivery_state(String delivery_state) {
		this.delivery_state = delivery_state;
	}
	public String getDelivery_zip() {
		return delivery_zip;
	}
	public void setDelivery_zip(String delivery_zip) {
		this.delivery_zip = delivery_zip;
	}
	public String getDelivery_country() {
		return delivery_country;
	}
	public void setDelivery_country(String delivery_country) {
		this.delivery_country = delivery_country;
	}
	public String getDelivery_tel() {
		return delivery_tel;
	}
	public void setDelivery_tel(String delivery_tel) {
		this.delivery_tel = delivery_tel;
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
	public String getVault() {
		return vault;
	}
	public void setVault(String vault) {
		this.vault = vault;
	}
	public String getOffer_type() {
		return offer_type;
	}
	public void setOffer_type(String offer_type) {
		this.offer_type = offer_type;
	}
	public String getOffer_code() {
		return offer_code;
	}
	public void setOffer_code(String offer_code) {
		this.offer_code = offer_code;
	}
	public double getDiscount_value() {
		return discount_value;
	}
	public void setDiscount_value(double discount_value) {
		this.discount_value = discount_value;
	}
	public Timestamp getPaymentdate() {
		return paymentdate;
	}
	public void setPaymentdate(Timestamp paymentdate) {
		this.paymentdate = paymentdate;
	}
	public long getTracking_id() {
		return tracking_id;
	}
	public void setTracking_id(long tracking_id) {
		this.tracking_id = tracking_id;
	}

	

}
