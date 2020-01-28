package com.godrej.properties.model;

import java.io.Serializable;

public class EOIPaymentModel  implements Serializable{

private static final long serialVersionUID = 1L;
	
	private int id;	
	
	private String enq_sfid;
	private String project_sfid;
	private int userid;
	private String payment_type;
	private String bank_name;
	private String branch;
	private String transaction_id;
	private String transaction_date;
	private String transaction_amount;
	private String description;
	private String total_amount;
	private String isactive;
	private String cheque_attach;
	private String pan_attach;
	private String user_name;
	private String user_email;
	private String project_name;
	private int gpl_cs_balance_details_id;
	private String isfromcp;
	private String offerid;
	private String name;
	private String customerName;
	private String mobileNo;
	
	private String context= System.getProperty("catalina.home");

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

	public int getGpl_cs_balance_details_id() {
		return gpl_cs_balance_details_id;
	}

	public void setGpl_cs_balance_details_id(int gpl_cs_balance_details_id) {
		this.gpl_cs_balance_details_id = gpl_cs_balance_details_id;
	}

	public String getIsfromcp() {
		return isfromcp;
	}

	public void setIsfromcp(String isfromcp) {
		this.isfromcp = isfromcp;
	}

	public String getOfferid() {
		return offerid;
	}

	public void setOfferid(String offerid) {
		this.offerid = offerid;
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

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
