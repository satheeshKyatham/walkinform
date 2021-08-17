package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.propstrength__floor__c")
public class FloorWiseBooking implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id") private int id;
	@Column(name = "sfid") private String sfid;
	@Column(name = "tower_name__c") private String tower_name__c;
	@Column(name = "floor_name__c") private String floor_name__c;
	@Column(name = "propstrength__primary_applicant_name__c") private String propstrength__primary_applicant_name__c;
	@Column(name = "sap_customer_code__c") private String sap_customer_code__c;
	@Column(name = "propstrength__floor__c") private String propstrength__floor__c; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSfid() {
		return sfid;
	}
	public void setSfid(String sfid) {
		this.sfid = sfid;
	}
	public String getTower_name__c() {
		return tower_name__c;
	}
	public void setTower_name__c(String tower_name__c) {
		this.tower_name__c = tower_name__c;
	}
	public String getFloor_name__c() {
		return floor_name__c;
	}
	public void setFloor_name__c(String floor_name__c) {
		this.floor_name__c = floor_name__c;
	}
	public String getPropstrength__primary_applicant_name__c() {
		return propstrength__primary_applicant_name__c;
	}
	public void setPropstrength__primary_applicant_name__c(String propstrength__primary_applicant_name__c) {
		this.propstrength__primary_applicant_name__c = propstrength__primary_applicant_name__c;
	}
	public String getSap_customer_code__c() {
		return sap_customer_code__c;
	}
	public void setSap_customer_code__c(String sap_customer_code__c) {
		this.sap_customer_code__c = sap_customer_code__c;
	}
	public String getPropstrength__floor__c() {
		return propstrength__floor__c;
	}
	public void setPropstrength__floor__c(String propstrength__floor__c) {
		this.propstrength__floor__c = propstrength__floor__c;
	}
}