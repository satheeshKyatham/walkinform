package com.godrej.properties.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="salesforce.propstrength__payment_plan__c")
public class PaymentPlanList implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") private int id;
	@Column(name="propstrength__type__c") private String propstrength__type__c;
	@Column(name="name") private String name;
	@Column(name="propstrength__project__c") private String propstrength__project__c;
	@Column(name="isdeleted") private boolean isdeleted;
	@Column(name="systemmodstamp") private Timestamp systemmodstamp;
	@Column(name="propstrength__custom_plan__c") private boolean propstrength__custom_plan__c;
	@Column(name="createddate") private Timestamp createddate;
	@Column(name="propstrength__active__c") private boolean propstrength__active__c;
	@Column(name="propstrength__total_percent__c") private Double propstrength__total_percent__c;
	@Column(name="propstrength__payment_plan_code__c") private String propstrength__payment_plan_code__c;
	@Column(name="sfid") private String sfid;
	@Column(name="d4u_active__c") private String d4u_active__c;
	@Column(name="cip_payment_plan__c") private String cip_payment_plan__c;
	@Column(name="cip_payment_plan_name__c") private String cip_payment_plan_name__c;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPropstrength__type__c() {
		return propstrength__type__c;
	}
	public void setPropstrength__type__c(String propstrength__type__c) {
		this.propstrength__type__c = propstrength__type__c;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPropstrength__project__c() {
		return propstrength__project__c;
	}
	public void setPropstrength__project__c(String propstrength__project__c) {
		this.propstrength__project__c = propstrength__project__c;
	}
	public boolean isIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	public Timestamp getSystemmodstamp() {
		return systemmodstamp;
	}
	public void setSystemmodstamp(Timestamp systemmodstamp) {
		this.systemmodstamp = systemmodstamp;
	}
	public boolean isPropstrength__custom_plan__c() {
		return propstrength__custom_plan__c;
	}
	public void setPropstrength__custom_plan__c(boolean propstrength__custom_plan__c) {
		this.propstrength__custom_plan__c = propstrength__custom_plan__c;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public boolean isPropstrength__active__c() {
		return propstrength__active__c;
	}
	public void setPropstrength__active__c(boolean propstrength__active__c) {
		this.propstrength__active__c = propstrength__active__c;
	}
	public Double getPropstrength__total_percent__c() {
		return propstrength__total_percent__c;
	}
	public void setPropstrength__total_percent__c(Double propstrength__total_percent__c) {
		this.propstrength__total_percent__c = propstrength__total_percent__c;
	}
	public String getPropstrength__payment_plan_code__c() {
		return propstrength__payment_plan_code__c;
	}
	public void setPropstrength__payment_plan_code__c(String propstrength__payment_plan_code__c) {
		this.propstrength__payment_plan_code__c = propstrength__payment_plan_code__c;
	}
	public String getSfid() {
		return sfid;
	}
	public String getD4u_active__c() {
		return d4u_active__c;
	}
	public void setD4u_active__c(String d4u_active__c) {
		this.d4u_active__c = d4u_active__c;
	}
	public void setSfid(String sfid) {
		this.sfid = sfid;
	}
	public String getCip_payment_plan__c() {
		return cip_payment_plan__c;
	}
	public void setCip_payment_plan__c(String cip_payment_plan__c) {
		this.cip_payment_plan__c = cip_payment_plan__c;
	}
	public String getCip_payment_plan_name__c() {
		return cip_payment_plan_name__c;
	}
	public void setCip_payment_plan_name__c(String cip_payment_plan_name__c) {
		this.cip_payment_plan_name__c = cip_payment_plan_name__c;
	}



}
