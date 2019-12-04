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
@Table(name="salesforce.vw_project_inventory_admin")
public class InventoryAdmin implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private int id;
	  
	@Column(name = "tower_code__c")
	private String tower_code__c;
	
	@Column(name = "PropStrength__Property_Alloted_Through_Offer__c")
	private String PropStrength__Property_Alloted_Through_Offer__c;
	
	@Column(name = "propstrength__property_type__C")
	private String propstrength__property_type__C;
	
	@Column(name = "floor_name__c")
	private String floor_name__c;
	
	@Column(name = "project_phases__c")
	private String project_phases__c;
	
	@Column(name = "floor_number__c")
	private String floor_number__c;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "propstrength__project_name__c")
	private String propstrength__project_name__c;
	
	@Column(name = "propstrength__unit_type__c")
	private String propstrength__unit_type__c;
	
	@Column(name = "propstrength__property_name__c")
	private String propstrength__property_name__c;
	
	@Column(name = "project_name__c")
	private String project_name__c;
	
	@Column(name = "sfid")
	private String sfid;
	
	@Column(name = "propstrength__house_unit_no__c")
	private String propstrength__house_unit_no__c;
	
	@Column(name = "propstrength__property_on_hold_for_reallocation__c")
	private String propstrength__property_on_hold_for_reallocation__c;
	
	@Column(name = "propstrength__allotted__c")
	private String propstrength__allotted__c;
	
	@Column(name = "holdstatusyn")
	private String holdstatusyn;
	
	@Column(name = "statusai")
	private String holdIntervalstatusAI;
	
	
	@Column(name = "created_at")
	private Timestamp created_at;
	
	@Transient
	private String flagForHold;
	
	@Column(name = "customer_id")
	private String customer_id;
	
	@Column(name = "hold_status")
	private String hold_status;
	
	@Column(name = "hold_reason")
	private String hold_reason;
	
	
	
	public String isHold_status() {
		return hold_status;
	}

	public void setHold_status(String hold_status) {
		this.hold_status = hold_status;
	}

	public String getHold_reason() {
		return hold_reason;
	}

	public void setHold_reason(String hold_reason) {
		this.hold_reason = hold_reason;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTower_code__c() {
		return tower_code__c;
	}

	public void setTower_code__c(String tower_code__c) {
		this.tower_code__c = tower_code__c;
	}

	public String getPropstrength__property_type__C() {
		return propstrength__property_type__C;
	}

	public void setPropstrength__property_type__C(String propstrength__property_type__C) {
		this.propstrength__property_type__C = propstrength__property_type__C;
	}

	public String getFloor_name__c() {
		return floor_name__c;
	}

	public void setFloor_name__c(String floor_name__c) {
		this.floor_name__c = floor_name__c;
	}

	public String getProject_phases__c() {
		return project_phases__c;
	}

	public void setProject_phases__c(String project_phases__c) {
		this.project_phases__c = project_phases__c;
	}

	public String getFloor_number__c() {
		return floor_number__c;
	}

	public void setFloor_number__c(String floor_number__c) {
		this.floor_number__c = floor_number__c;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPropstrength__project_name__c() {
		return propstrength__project_name__c;
	}

	public void setPropstrength__project_name__c(String propstrength__project_name__c) {
		this.propstrength__project_name__c = propstrength__project_name__c;
	}

	public String getPropstrength__unit_type__c() {
		return propstrength__unit_type__c;
	}

	public void setPropstrength__unit_type__c(String propstrength__unit_type__c) {
		this.propstrength__unit_type__c = propstrength__unit_type__c;
	}

	public String getPropstrength__property_name__c() {
		return propstrength__property_name__c;
	}

	public void setPropstrength__property_name__c(String propstrength__property_name__c) {
		this.propstrength__property_name__c = propstrength__property_name__c;
	}

	public String getProject_name__c() {
		return project_name__c;
	}

	public void setProject_name__c(String project_name__c) {
		this.project_name__c = project_name__c;
	}

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getPropstrength__house_unit_no__c() {
		return propstrength__house_unit_no__c;
	}

	public void setPropstrength__house_unit_no__c(String propstrength__house_unit_no__c) {
		this.propstrength__house_unit_no__c = propstrength__house_unit_no__c;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPropstrength__property_on_hold_for_reallocation__c() {
		return propstrength__property_on_hold_for_reallocation__c;
	}

	public void setPropstrength__property_on_hold_for_reallocation__c(
			String propstrength__property_on_hold_for_reallocation__c) {
		this.propstrength__property_on_hold_for_reallocation__c = propstrength__property_on_hold_for_reallocation__c;
	}

	public String getPropstrength__allotted__c() {
		return propstrength__allotted__c;
	}

	public void setPropstrength__allotted__c(String propstrength__allotted__c) {
		this.propstrength__allotted__c = propstrength__allotted__c;
	}

	public String getHoldstatusyn() {
		return holdstatusyn;
	}

	public void setHoldstatusyn(String holdstatusyn) {
		this.holdstatusyn = holdstatusyn;
	}

	
	public String getHoldIntervalstatusAI() {
		return holdIntervalstatusAI;
	}

	public void setHoldIntervalstatusAI(String holdIntervalstatusAI) {
		this.holdIntervalstatusAI = holdIntervalstatusAI;
	}

	public String getFlagForHold() {
		return flagForHold;
	}

	public void setFlagForHold(String flagForHold) {
		this.flagForHold = flagForHold;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getPropStrength__Property_Alloted_Through_Offer__c() {
		return PropStrength__Property_Alloted_Through_Offer__c;
	}

	public void setPropStrength__Property_Alloted_Through_Offer__c(String propStrength__Property_Alloted_Through_Offer__c) {
		PropStrength__Property_Alloted_Through_Offer__c = propStrength__Property_Alloted_Through_Offer__c;
	}
	
}