package com.godrej.properties.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.propstrength__prepayment_received__c")
public class PropPrepaymentReceived implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") private int id;
	
	@Column(name="propstrength__cheque_demand_draft_number__c") private Double propstrength__cheque_demand_draft_number__c;
	@Column(name="propstrength__payment_mode__c") private String propstrength__payment_mode__c;
	@Column(name="propstrength__amount__c") private Double propstrength__amount__c;
	@Column(name="propstrength__offer__c") private String propstrength__offer__c;
	@Column(name="propstrength__ifsc_rtgs_code__c") private String propstrength__ifsc_rtgs_code__c;
	@Column(name="propstrength__bank_name__c") private String propstrength__bank_name__c;
	@Column(name="propstrength__crn_no__c") private String propstrength__crn_no__c;
	@Column(name="propstrength__cheque_demand_draft_no__c") private String propstrength__cheque_demand_draft_no__c;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getPropstrength__cheque_demand_draft_number__c() {
		return propstrength__cheque_demand_draft_number__c;
	}
	public void setPropstrength__cheque_demand_draft_number__c(Double propstrength__cheque_demand_draft_number__c) {
		this.propstrength__cheque_demand_draft_number__c = propstrength__cheque_demand_draft_number__c;
	}
	public String getPropstrength__payment_mode__c() {
		return propstrength__payment_mode__c;
	}
	public void setPropstrength__payment_mode__c(String propstrength__payment_mode__c) {
		this.propstrength__payment_mode__c = propstrength__payment_mode__c;
	}
	public Double getPropstrength__amount__c() {
		return propstrength__amount__c;
	}
	public void setPropstrength__amount__c(Double propstrength__amount__c) {
		this.propstrength__amount__c = propstrength__amount__c;
	}
	public String getPropstrength__offer__c() {
		return propstrength__offer__c;
	}
	public void setPropstrength__offer__c(String propstrength__offer__c) {
		this.propstrength__offer__c = propstrength__offer__c;
	}
	public String getPropstrength__ifsc_rtgs_code__c() {
		return propstrength__ifsc_rtgs_code__c;
	}
	public void setPropstrength__ifsc_rtgs_code__c(String propstrength__ifsc_rtgs_code__c) {
		this.propstrength__ifsc_rtgs_code__c = propstrength__ifsc_rtgs_code__c;
	}
	public String getPropstrength__bank_name__c() {
		return propstrength__bank_name__c;
	}
	public void setPropstrength__bank_name__c(String propstrength__bank_name__c) {
		this.propstrength__bank_name__c = propstrength__bank_name__c;
	}
	public String getPropstrength__crn_no__c() {
		return propstrength__crn_no__c;
	}
	public void setPropstrength__crn_no__c(String propstrength__crn_no__c) {
		this.propstrength__crn_no__c = propstrength__crn_no__c;
	}
	public String getPropstrength__cheque_demand_draft_no__c() {
		return propstrength__cheque_demand_draft_no__c;
	}
	public void setPropstrength__cheque_demand_draft_no__c(String propstrength__cheque_demand_draft_no__c) {
		this.propstrength__cheque_demand_draft_no__c = propstrength__cheque_demand_draft_no__c;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}