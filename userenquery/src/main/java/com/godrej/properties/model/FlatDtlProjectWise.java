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
@Table(name="salesforce.propstrength__property__c")
public class FlatDtlProjectWise implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") private int id;
	@Column(name="tower_name__c") private String tower_name__c;
	@Column(name="floor_name__c") private String floor_name__c;
	@Column(name="sfid") private String sfid;
	@Column(name="propstrength__property_name__c") private String propstrength__property_name__c;
	@Column(name="propstrength__house_unit_no__c") private String propstrength__house_unit_no__c;
	@Column(name="propstrength__unit_type__c") private String propstrength__unit_type__c;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getSfid() {
		return sfid;
	}
	public void setSfid(String sfid) {
		this.sfid = sfid;
	}
	public String getPropstrength__property_name__c() {
		return propstrength__property_name__c;
	}
	public void setPropstrength__property_name__c(String propstrength__property_name__c) {
		this.propstrength__property_name__c = propstrength__property_name__c;
	}
	public String getPropstrength__house_unit_no__c() {
		return propstrength__house_unit_no__c;
	}
	public void setPropstrength__house_unit_no__c(String propstrength__house_unit_no__c) {
		this.propstrength__house_unit_no__c = propstrength__house_unit_no__c;
	}
	public String getPropstrength__unit_type__c() {
		return propstrength__unit_type__c;
	}
	public void setPropstrength__unit_type__c(String propstrength__unit_type__c) {
		this.propstrength__unit_type__c = propstrength__unit_type__c;
	} 
}