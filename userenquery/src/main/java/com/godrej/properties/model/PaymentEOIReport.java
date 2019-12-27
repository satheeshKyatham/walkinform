package com.godrej.properties.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.vw_payment_eoi_report") 
public class PaymentEOIReport implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="id") private  int id ;
	@Column(name="region__c") private String	region__c;
	@Column(name="project_name") private String	project_name;
	@Column(name="enq_name") private String	enq_name;
	@Column(name="customer_name") private String customer_name;
	@Column(name="customer_mobile") private String	customer_mobile;
	@Column(name="customer_email") private String	customer_email;
	@Column(name="walkin_date") private Date	walkin_date;
	@Column(name="walk_in_source__c") private String walk_in_source__c;
	@Column(name="verticle__c") private String verticle__c;
	@Column(name="closing_manager_name__c") private String closing_manager_name__c;
	@Column(name="date_of_eoi__c") private Date date_of_eoi__c;
	@Column(name="payment_type") private String payment_type;
	@Column(name="bank_name") private String bank_name;
	@Column(name="branch") private String branch;
	@Column(name="transaction_id") private String transaction_id;
	@Column(name="transaction_date") private String transaction_date;
	@Column(name="transaction_amount") private String transaction_amount;
	@Column(name="description") private String description;
	@Column(name="payment_status") private String payment_status;
	@Column(name="project_sfid") private String project_sfid;
	
	@Column(name="userid") private  int userid ;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRegion__c() {
		return region__c;
	}
	public void setRegion__c(String region__c) {
		this.region__c = region__c;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getEnq_name() {
		return enq_name;
	}
	public void setEnq_name(String enq_name) {
		this.enq_name = enq_name;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_mobile() {
		return customer_mobile;
	}
	public void setCustomer_mobile(String customer_mobile) {
		this.customer_mobile = customer_mobile;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public Date getWalkin_date() {
		return walkin_date;
	}
	public void setWalkin_date(Date walkin_date) {
		this.walkin_date = walkin_date;
	}
	public String getWalk_in_source__c() {
		return walk_in_source__c;
	}
	public void setWalk_in_source__c(String walk_in_source__c) {
		this.walk_in_source__c = walk_in_source__c;
	}
	public String getVerticle__c() {
		return verticle__c;
	}
	public void setVerticle__c(String verticle__c) {
		this.verticle__c = verticle__c;
	}
	public String getClosing_manager_name__c() {
		return closing_manager_name__c;
	}
	public void setClosing_manager_name__c(String closing_manager_name__c) {
		this.closing_manager_name__c = closing_manager_name__c;
	}
	public Date getDate_of_eoi__c() {
		return date_of_eoi__c;
	}
	public void setDate_of_eoi__c(Date date_of_eoi__c) {
		this.date_of_eoi__c = date_of_eoi__c;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(String transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getProject_sfid() {
		return project_sfid;
	}
	public void setProject_sfid(String project_sfid) {
		this.project_sfid = project_sfid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
}