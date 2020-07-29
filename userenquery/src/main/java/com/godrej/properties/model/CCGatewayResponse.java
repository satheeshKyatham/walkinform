package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.nv_cc_gateway_response")
public class CCGatewayResponse {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="nv_cc_gateway_response_id") private int nv_cc_gateway_response_id;
	@Column(name="merchant_reference_no") private int merchant_reference_no;
	@Column(name="resp_json_txt") private String resp_json_txt;
	@Column(name="invoice_id") private int invoice_id;
	@Column(name="tiny_url") private String tiny_url;
	@Column(name="error_desc") private String error_desc;
	@Column(name="invoice_status") private String invoice_status;
	@Column(name="error_code") private String error_code;
	@Column(name="ispayment_done") private String ispayment_done;
	@Column(name="enq_sfid") private String enq_sfid;
	@Column(name="enq_name") private String enq_name;
	@Column(name="generated_date") private Timestamp generated_date;
	@Column(name="payment_date") private Timestamp payment_date;
	
	public int getNv_cc_gateway_response_id() {
		return nv_cc_gateway_response_id;
	}
	public void setNv_cc_gateway_response_id(int nv_cc_gateway_response_id) {
		this.nv_cc_gateway_response_id = nv_cc_gateway_response_id;
	}
	public int getMerchant_reference_no() {
		return merchant_reference_no;
	}
	public void setMerchant_reference_no(int merchant_reference_no) {
		this.merchant_reference_no = merchant_reference_no;
	}
	public String getResp_json_txt() {
		return resp_json_txt;
	}
	public void setResp_json_txt(String resp_json_txt) {
		this.resp_json_txt = resp_json_txt;
	}
	public int getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}
	public String getTiny_url() {
		return tiny_url;
	}
	public void setTiny_url(String tiny_url) {
		this.tiny_url = tiny_url;
	}
	public String getError_desc() {
		return error_desc;
	}
	public void setError_desc(String error_desc) {
		this.error_desc = error_desc;
	}
	public String getInvoice_status() {
		return invoice_status;
	}
	public void setInvoice_status(String invoice_status) {
		this.invoice_status = invoice_status;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getIspayment_done() {
		return ispayment_done;
	}
	public void setIspayment_done(String ispayment_done) {
		this.ispayment_done = ispayment_done;
	}
	public String getEnq_sfid() {
		return enq_sfid;
	}
	public void setEnq_sfid(String enq_sfid) {
		this.enq_sfid = enq_sfid;
	}
	public String getEnq_name() {
		return enq_name;
	}
	public void setEnq_name(String enq_name) {
		this.enq_name = enq_name;
	}
	public Timestamp getGenerated_date() {
		return generated_date;
	}
	public void setGenerated_date(Timestamp generated_date) {
		this.generated_date = generated_date;
	}
	public Timestamp getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(Timestamp payment_date) {
		this.payment_date = payment_date;
	}
	
}
