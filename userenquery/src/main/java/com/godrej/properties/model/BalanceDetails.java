package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="salesforce.gpl_cs_balance_details")
public class BalanceDetails implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpl_cs_balance_details_id") 
	private int id;
	  
	@Column(name = "amount")
	private String amount;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "createdby")
	private String createdby;
	
	@Column(name = "updatedby")
	private String updatedby;
	
	@Column(name = "isactive")
	private String isactive;

	@Column(name = "enquiry_sfid")
	private String enquiry_sfid;
	@Column(name = "contact_sfid")
	private String contact_sfid;
	@Column(name = "offer_sfid")
	private String offer_sfid;
	
	@Column(name = "scheme_name")
	private String scheme_name;
	@Column(name = "car_park_type")
	private String car_park_type;
	
	@Column(name = "paymentplan_sfid")
	private String paymentplan_sfid;
	@Column(name = "scheme_rate")
	private int scheme_rate;
	
	@Column(name = "project_sfid")
	private String project_sfid;
	
	@Column(name = "userid")
	private int userid;
	
	@Column(name = "costsheet_commitment")
	private String costsheet_commitment;
	
	@Column(name = "costsheet_path")
	private String costsheet_path;
	
	@Column(name = "cs_final_amount")
	private double cs_final_amount;
	@Transient
	private String offer_successMsg;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
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

	public String getEnquiry_sfid() {
		return enquiry_sfid;
	}

	public void setEnquiry_sfid(String enquiry_sfid) {
		this.enquiry_sfid = enquiry_sfid;
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

	public String getScheme_name() {
		return scheme_name;
	}

	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}

	public String getCar_park_type() {
		return car_park_type;
	}

	public void setCar_park_type(String car_park_type) {
		this.car_park_type = car_park_type;
	}

	public String getPaymentplan_sfid() {
		return paymentplan_sfid;
	}

	public void setPaymentplan_sfid(String paymentplan_sfid) {
		this.paymentplan_sfid = paymentplan_sfid;
	}

	public void setOffer_sfid(String offer_sfid) {
		this.offer_sfid = offer_sfid;
	}

	public int getScheme_rate() {
		return scheme_rate;
	}

	public void setScheme_rate(int scheme_rate) {
		this.scheme_rate = scheme_rate;
	}

	public String getProject_sfid() {
		return project_sfid;
	}

	public void setProject_sfid(String project_sfid) {
		this.project_sfid = project_sfid;
	}

	public int getUserid() {
		if(String.valueOf(userid) !=null && String.valueOf(userid).length()>0)
		{
			return userid;
		}
		else
			return 0;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getCostsheet_commitment() {
		return costsheet_commitment;
	}

	public void setCostsheet_commitment(String costsheet_commitment) {
		this.costsheet_commitment = costsheet_commitment;
	}

	public String getCostsheet_path() {
		return costsheet_path;
	}

	public void setCostsheet_path(String costsheet_path) {
		this.costsheet_path = costsheet_path;
	}

	public double getCs_final_amount() {
		return cs_final_amount;
	}

	public void setCs_final_amount(double cs_final_amount) {
		this.cs_final_amount = cs_final_amount;
	}

	public String getOffer_successMsg() {
		return offer_successMsg;
	}

	public void setOffer_successMsg(String offer_successMsg) {
		this.offer_successMsg = offer_successMsg;
	}
	
}