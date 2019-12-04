package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.gpl_cs_other_charges")

public class OtherCharges implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpl_cs_other_charges_id")
	private int id;	
	
	@Column(name = "project_name")
	private String project_name;
	
	@Column(name = "project_id")
	private String project_id;
	
	@Column(name = "typology")
	private String typology;
	
	@Column(name = "isactive")
	private String isactive;
	
	@Column(name = "region")
	private String region;
	
	@Column(name = "tower")
	private String tower;
	
	@Column(name="propstrength__rate_per_unit_area__c") 
	private double propstrength__rate_per_unit_area__c;
	
	@Column(name="propstrength__fixed_charge__c") 
	private double propstrength__fixed_charge__c;
	
	@Column(name="propstrength__type__c") 
	private String propstrength__type__c;
	
	@Column(name="propstrength__other_charge_code__c") 
	private String propstrength__other_charge_code__c;
	
	@Column(name="sum") 
	private double sum;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getTypology() {
		return typology;
	}

	public void setTypology(String typology) {
		this.typology = typology;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTower() {
		return tower;
	}

	public void setTower(String tower) {
		this.tower = tower;
	}

	public double getPropstrength__rate_per_unit_area__c() {
		return propstrength__rate_per_unit_area__c;
	}

	public void setPropstrength__rate_per_unit_area__c(double propstrength__rate_per_unit_area__c) {
		this.propstrength__rate_per_unit_area__c = propstrength__rate_per_unit_area__c;
	}

	public double getPropstrength__fixed_charge__c() {
		return propstrength__fixed_charge__c;
	}

	public void setPropstrength__fixed_charge__c(double propstrength__fixed_charge__c) {
		this.propstrength__fixed_charge__c = propstrength__fixed_charge__c;
	}

	public String getPropstrength__type__c() {
		return propstrength__type__c;
	}

	public void setPropstrength__type__c(String propstrength__type__c) {
		this.propstrength__type__c = propstrength__type__c;
	}

	public String getPropstrength__other_charge_code__c() {
		return propstrength__other_charge_code__c;
	}

	public void setPropstrength__other_charge_code__c(String propstrength__other_charge_code__c) {
		this.propstrength__other_charge_code__c = propstrength__other_charge_code__c;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
}