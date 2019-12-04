package com.godrej.properties.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="salesforce.gpl_cs_payment_details")

public class PaymentDtl implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpl_cs_payment_details_id")
	private int id;	
	
	@Column(name = "enq_sfid")
	private String enq_sfid;
	
	@Column(name = "contact_sfid")
	private String contact_sfid;
	
	@Column(name = "offer_sfid")
	private String offer_sfid;
	
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
	private Date transaction_date;
	
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

	@Column(name = "gpl_cs_balance_details_id")
	private int gpl_cs_balance_details_id;
	
	@Column(name = "gpl_cs_eoi_payment_details_id")
	private int gpl_cs_eoi_payment_details_id;
	
	
	
	@Transient
	private String transaction_date_string;
	@Transient
	private String offerid;
	@Transient
	private String bankGL;
	
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

	public String getContact_sfid() {
		return contact_sfid;
	}

	public void setContact_sfid(String contact_sfid) {
		this.contact_sfid = contact_sfid;
	}

	public String getOffer_sfid() {
		return offer_sfid;
	}

	public void setOffer_sfid(String offer_sfid) {
		this.offer_sfid = offer_sfid;
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

	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	public String getTransaction_date_string() {
		return transaction_date_string;
	}

	public void setTransaction_date_string(String transaction_date_string) {
		this.transaction_date_string = transaction_date_string;
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

	public int getGpl_cs_eoi_payment_details_id() {
		return gpl_cs_eoi_payment_details_id;
	}

	public void setGpl_cs_eoi_payment_details_id(int gpl_cs_eoi_payment_details_id) {
		this.gpl_cs_eoi_payment_details_id = gpl_cs_eoi_payment_details_id;
	}

	public String getBankGL() {
		return bankGL;
	}

	public void setBankGL(String bankGL) {
		this.bankGL = bankGL;
	}
	
}