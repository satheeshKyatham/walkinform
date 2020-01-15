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
@Table(name="salesforce.gpl_cs_hold_unit_uat") 
public class InventorySalesHoldReport implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpl_cs_hold_unit_id") 
	private int id;
	  
	@Column(name = "project_id")
	private String project_id;
	
	@Column(name = "holdstatusyn")
	private String holdstatusyn;

	@Column(name = "statusai")
	private String statusai;
	
	@Column(name = "tower_code__c")
	private String tower_code__c;
	
	@Column(name = "tower_name__c")
	private String tower_name__c;
	
	@Column(name = "floor_number__c")
	private String floor_number__c;
	
	@Column(name = "propstrength__unit_type__c")
	private String propstrength__unit_type__c;
	
	@Column(name = "propstrength__house_unit_no__c")
	private String propstrength__house_unit_no__c;
	
	@Column(name = "held_by_name")
	private String held_by_name;
	
	@Column(name = "held_by_email")
	private String held_by_email;
	
	@Column(name = "created_at")
	private Timestamp created_at;
	
	@Transient
	private int holdMin;
	
	@Transient
	private int holdSec;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getHoldstatusyn() {
		return holdstatusyn;
	}

	public void setHoldstatusyn(String holdstatusyn) {
		this.holdstatusyn = holdstatusyn;
	}

	public String getStatusai() {
		return statusai;
	}

	public void setStatusai(String statusai) {
		this.statusai = statusai;
	}

	public String getTower_code__c() {
		return tower_code__c;
	}

	public void setTower_code__c(String tower_code__c) {
		this.tower_code__c = tower_code__c;
	}

	public String getTower_name__c() {
		return tower_name__c;
	}

	public void setTower_name__c(String tower_name__c) {
		this.tower_name__c = tower_name__c;
	}

	public String getFloor_number__c() {
		return floor_number__c;
	}

	public void setFloor_number__c(String floor_number__c) {
		this.floor_number__c = floor_number__c;
	}

	public String getPropstrength__unit_type__c() {
		return propstrength__unit_type__c;
	}

	public void setPropstrength__unit_type__c(String propstrength__unit_type__c) {
		this.propstrength__unit_type__c = propstrength__unit_type__c;
	}

	public String getPropstrength__house_unit_no__c() {
		return propstrength__house_unit_no__c;
	}

	public void setPropstrength__house_unit_no__c(String propstrength__house_unit_no__c) {
		this.propstrength__house_unit_no__c = propstrength__house_unit_no__c;
	}

	public String getHeld_by_name() {
		return held_by_name;
	}

	public void setHeld_by_name(String held_by_name) {
		this.held_by_name = held_by_name;
	}

	public String getHeld_by_email() {
		return held_by_email;
	}

	public void setHeld_by_email(String held_by_email) {
		this.held_by_email = held_by_email;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public int getHoldMin() {
		return holdMin;
	}

	public void setHoldMin(int holdMin) {
		this.holdMin = holdMin;
	}

	public int getHoldSec() {
		return holdSec;
	}

	public void setHoldSec(int holdSec) {
		this.holdSec = holdSec;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}