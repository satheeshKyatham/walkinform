package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "_temp_billing") 
public class BillingData {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="row_number") private  int row_number;
	@Column(name="bill_doc_no") private String	bill_doc_no;
	@Column(name="bill_line_item") private String bill_line_item;
	@Column(name="sales_org") private String sales_org;
	@Column(name="comp_code") private String comp_code;
	@Column(name="customer_code") private String customer_code;
	@Column(name="charge_type") private String charge_type;
	@Column(name="total_payable") private String total_payable;
	@Column(name="total_service_tax") private String total_service_tax;
	@Column(name="booking_status") private String booking_status;
	@Column(name="other_amount_type") private String other_amount_type;
	@Column(name="type_doc") private String type_doc;
	@Column(name="billing_date") private String billing_date;
	@Column(name="reversal_doc_no") private String reversal_doc_no;
	@Column(name="legacy_data") private String legacy_data;
	@Column(name="amount_type") private String amount_type;
	@Column(name="document_numer") private String document_numer;
	@Column(name="project") private String project;
	@Column(name="lastmodifieddate") private Timestamp lastmodifieddate;
	@Column(name="profit_centres__c") private String profit_centres_c;
	@Column(name="property__c") private String property_c;
	 
	public String getProfit_centres_c() {
		return profit_centres_c;
	}
	public void setProfit_centres_c(String profit_centres_c) {
		this.profit_centres_c = profit_centres_c;
	}
	 
	public String getProperty_c() {
		return property_c;
	}
	public void setProperty_c(String property_c) {
		this.property_c = property_c;
	}
	public int getRow_number() {
		return row_number;
	}
	public void setRow_number(int row_number) {
		this.row_number = row_number;
	}
	public String getBill_doc_no() {
		return bill_doc_no;
	}
	public void setBill_doc_no(String bill_doc_no) {
		this.bill_doc_no = bill_doc_no;
	}
	public String getBill_line_item() {
		return bill_line_item;
	}
	public void setBill_line_item(String bill_line_item) {
		this.bill_line_item = bill_line_item;
	}
	public String getSales_org() {
		return sales_org;
	}
	public void setSales_org(String sales_org) {
		this.sales_org = sales_org;
	}
	public String getComp_code() {
		return comp_code;
	}
	public void setComp_code(String comp_code) {
		this.comp_code = comp_code;
	}
	public String getCustomer_code() {
		return customer_code;
	}
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	public String getCharge_type() {
		return charge_type;
	}
	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}
	public String getTotal_payable() {
		return total_payable;
	}
	public void setTotal_payable(String total_payable) {
		this.total_payable = total_payable;
	}
	public String getTotal_service_tax() {
		return total_service_tax;
	}
	public void setTotal_service_tax(String total_service_tax) {
		this.total_service_tax = total_service_tax;
	}
	public String getBooking_status() {
		return booking_status;
	}
	public void setBooking_status(String booking_status) {
		this.booking_status = booking_status;
	}
	public String getOther_amount_type() {
		return other_amount_type;
	}
	public void setOther_amount_type(String other_amount_type) {
		this.other_amount_type = other_amount_type;
	}
	public String getType_doc() {
		return type_doc;
	}
	public void setType_doc(String type_doc) {
		this.type_doc = type_doc;
	}
	public String getBilling_date() {
		return billing_date;
	}
	public void setBilling_date(String billing_date) {
		this.billing_date = billing_date;
	}
	public String getReversal_doc_no() {
		return reversal_doc_no;
	}
	public void setReversal_doc_no(String reversal_doc_no) {
		this.reversal_doc_no = reversal_doc_no;
	}
	public String getLegacy_data() {
		return legacy_data;
	}
	public void setLegacy_data(String legacy_data) {
		this.legacy_data = legacy_data;
	}
	public String getAmount_type() {
		return amount_type;
	}
	public void setAmount_type(String amount_type) {
		this.amount_type = amount_type;
	}
	public String getDocument_numer() {
		return document_numer;
	}
	public void setDocument_numer(String document_numer) {
		this.document_numer = document_numer;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public Timestamp getLastmodifieddate() {
		return lastmodifieddate;
	}
	public void setLastmodifieddate(Timestamp lastmodifieddate) {
		this.lastmodifieddate = lastmodifieddate;
	}
	
	
}
