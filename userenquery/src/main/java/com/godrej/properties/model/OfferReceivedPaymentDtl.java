package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.gpl_cs_payment_details")
public class OfferReceivedPaymentDtl implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="gpl_cs_payment_details_id") private int id;
	
	@Column(name="payment_type") private String propstrength__payment_type__c;
	@Column(name="bank_name") private String propstrength__bank_name_auto__c;
	@Column(name="branch") private String propstrength__crn_no__c;
	
	@Column(name="transaction_id") private String propstrength__cheque_demand_draft_number__c;
	//@Column(name="propstrength__ifsc_rtgs_code__c") private String propstrength__ifsc_rtgs_code__c;
	
	//@Column(name="recordtypeid") private String recordtypeid;
	@Column(name="transaction_amount") private double propstrength__amount__c;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPropstrength__payment_type__c() {
		return propstrength__payment_type__c;
	}

	public void setPropstrength__payment_type__c(String propstrength__payment_type__c) {
		this.propstrength__payment_type__c = propstrength__payment_type__c;
	}

	public String getPropstrength__bank_name_auto__c() {
		return propstrength__bank_name_auto__c;
	}

	public void setPropstrength__bank_name_auto__c(String propstrength__bank_name_auto__c) {
		this.propstrength__bank_name_auto__c = propstrength__bank_name_auto__c;
	}

	public String getPropstrength__crn_no__c() {
		return propstrength__crn_no__c;
	}

	public void setPropstrength__crn_no__c(String propstrength__crn_no__c) {
		this.propstrength__crn_no__c = propstrength__crn_no__c;
	}

	public String getPropstrength__cheque_demand_draft_number__c() {
		return propstrength__cheque_demand_draft_number__c;
	}

	public void setPropstrength__cheque_demand_draft_number__c(String propstrength__cheque_demand_draft_number__c) {
		this.propstrength__cheque_demand_draft_number__c = propstrength__cheque_demand_draft_number__c;
	}

	public double getPropstrength__amount__c() {
		return propstrength__amount__c;
	}

	public void setPropstrength__amount__c(double propstrength__amount__c) {
		this.propstrength__amount__c = propstrength__amount__c;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}