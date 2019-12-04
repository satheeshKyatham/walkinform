package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.nv_cc_gateway_request")
public class CCGatewayRequest {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="nv_cc_gateway_request_id") private int nv_cc_gateway_request_id;
	@Column(name="req_json_txt") private String req_json_txt;
	@Column(name="enq_id") private String enq_id;
	@Column(name="enq_sfid") private String enq_sfid;
	@Column(name="cust_name") private String cust_name;
	@Column(name="cust_email") private String cust_email;
	@Column(name="cust_mobile") private String cust_mobile;
	@Column(name="amount") private double amount;
	
	@Id
	@SequenceGenerator(name = "salesforce.nv_cc_gateway_request_merchant_reference_no_seq", sequenceName = "salesforce.nv_cc_gateway_request_merchant_reference_no_seq", initialValue = 1)
	@GeneratedValue(generator = "salesforce.nv_cc_gateway_request_merchant_reference_no_seq")
	@Column(name="merchant_reference_no") 
	public int merchant_reference_no;
	
	@Column(name="updateddate") private Timestamp updateddate;
	@Column(name="createdby") private int createdby;
	@Column(name="createddate") private Timestamp createddate;
	public int getNv_cc_gateway_request_id() {
		return nv_cc_gateway_request_id;
	}
	public void setNv_cc_gateway_request_id(int nv_cc_gateway_request_id) {
		this.nv_cc_gateway_request_id = nv_cc_gateway_request_id;
	}

	public String getEnq_id() {
		return enq_id;
	}
	public void setEnq_id(String enq_id) {
		this.enq_id = enq_id;
	}
	public String getEnq_sfid() {
		return enq_sfid;
	}
	public void setEnq_sfid(String enq_sfid) {
		this.enq_sfid = enq_sfid;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_email() {
		return cust_email;
	}
	public void setCust_email(String cust_email) {
		this.cust_email = cust_email;
	}
	public String getCust_mobile() {
		return cust_mobile;
	}
	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}

	public int getMerchant_reference_no() {
		return merchant_reference_no;
	}
	public void setMerchant_reference_no(int merchant_reference_no) {
		this.merchant_reference_no = merchant_reference_no;
	}
	public Timestamp getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(Timestamp updateddate) {
		this.updateddate = updateddate;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public String getReq_json_txt() {
		return req_json_txt;
	}
	public void setReq_json_txt(String req_json_txt) {
		this.req_json_txt = req_json_txt;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
