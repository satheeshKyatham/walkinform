package com.godrej.properties.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="salesforce.gpl_cs_eoi_payment_details")

public class EOIPaymentDtl implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpl_cs_eoi_payment_details_id")
	private int id;	
	
	@Column(name = "enq_sfid")
	private String enq_sfid;
	
	
	
	@Column(name = "project_sfid")
	private String project_sfid;
	
	@Column(name = "userid")
	private int userid;
	
	@Column(name = "payment_type")
	private String payment_type;
	
	@Column(name = "bank_name")
	private String bank_name;
	
	@Column(name = "branch")
	private String branch;
	
	@Column(name = "transaction_id")
	private String transaction_id;
	
	@Column(name = "transaction_date")
	private String transaction_date;
	
	@Column(name = "transaction_amount")
	private String transaction_amount;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "total_amount")
	private String total_amount;
	
	@Column(name = "isactive")
	private String isactive;
	
	
	@Column(name = "cheque_attach")
	private String cheque_attach;
	
	@Column(name = "pan_attach")
	private String pan_attach;
	
	@Column(name = "user_name")
	private String user_name;
	
	@Column(name = "user_email")
	private String user_email;
	
	@Column(name = "project_name")
	private String project_name;
	
	@Column(name = "eoi_form_path")
	private String eoi_form_path;
	
	@Column(name = "gpl_cs_balance_details_id") private int gpl_cs_balance_details_id;
	@Column(name = "isfromcp") private String isfromcp;
	@Column(name = "created") private Timestamp createdDate;
	@Column(name = "payment_mode") private String payment_mode;
	
	@Transient
	private String offerid;
	@Transient
	private String name;
	
	@Transient
	private String customerName;
	@Transient
	private String mobileNo;
	
	@Transient
	private String context= System.getProperty("catalina.home");
	
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnq_sfid() {
		return enq_sfid;
	}

	public void setEnq_sfid(String enq_sfid) {
		this.enq_sfid = enq_sfid;
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

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getGpl_cs_balance_details_id() {
		return gpl_cs_balance_details_id;
	}

	public void setGpl_cs_balance_details_id(int gpl_cs_balance_details_id) {
		this.gpl_cs_balance_details_id = gpl_cs_balance_details_id;
	}

	public String getCheque_attach() {
		return cheque_attach;
	}

	public void setCheque_attach(String cheque_attach) {
		this.cheque_attach = cheque_attach;
	}

	public String getPan_attach() {
		return pan_attach;
	}

	public void setPan_attach(String pan_attach) {
		this.pan_attach = pan_attach;
	}

	public String getOfferid() {
		return offerid;
	}

	public void setOfferid(String offerid) {
		this.offerid = offerid;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getIsfromcp() {
		return isfromcp;
	}

	public void setIsfromcp(String isfromcp) {
		this.isfromcp = isfromcp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEoi_form_path() {
		return eoi_form_path;
	}

	public void setEoi_form_path(String eoi_form_path) {
		this.eoi_form_path = eoi_form_path;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getPayment_mode() {
		return payment_mode;
	}

	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}


	
}