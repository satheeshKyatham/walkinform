package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.vw_inventory_dtl")
public class InventoryDtl implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="row_number") private  int id ;

	@Column(name="sfid") private String  sfid;
	@Column(name="propstrength__house_unit_no__c") private String  propstrength__house_unit_no__c;
	@Column(name="tower_code__c") private String  tower_code__c;
	@Column(name="propstrength__property_name__c") private String  propstrength__property_name__c;
	@Column(name="propstrength__active__c") private boolean  propstrength__active__c;
	@Column(name="propstrength__allotted__c") private boolean  propstrength__allotted__c;
	@Column(name="propstrength__property_on_hold_for_reallocation__c") private boolean  propstrength__property_on_hold_for_reallocation__c;
	@Column(name="propstrength__property_alloted_through_offer__c") private boolean  propstrength__property_alloted_through_offer__c;
	@Column(name="propstrength__project_name__c") private boolean  propstrength__project_name__c;
	@Column(name="eoi_unit_locked") private boolean  eoi_unit_locked;
	@Column(name="hold_status") private boolean  hold_status;
	 
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
	public String getTower_code__c() {
		return tower_code__c;
	}
	public void setTower_code__c(String tower_code__c) {
		this.tower_code__c = tower_code__c;
	}
	public String getPropstrength__property_name__c() {
		return propstrength__property_name__c;
	}
	public void setPropstrength__property_name__c(String propstrength__property_name__c) {
		this.propstrength__property_name__c = propstrength__property_name__c;
	}
	public boolean isPropstrength__active__c() {
		return propstrength__active__c;
	}
	public void setPropstrength__active__c(boolean propstrength__active__c) {
		this.propstrength__active__c = propstrength__active__c;
	}
	public boolean isPropstrength__allotted__c() {
		return propstrength__allotted__c;
	}
	public void setPropstrength__allotted__c(boolean propstrength__allotted__c) {
		this.propstrength__allotted__c = propstrength__allotted__c;
	}
	public boolean isPropstrength__property_on_hold_for_reallocation__c() {
		return propstrength__property_on_hold_for_reallocation__c;
	}
	public void setPropstrength__property_on_hold_for_reallocation__c(
			boolean propstrength__property_on_hold_for_reallocation__c) {
		this.propstrength__property_on_hold_for_reallocation__c = propstrength__property_on_hold_for_reallocation__c;
	}
	public boolean isPropstrength__property_alloted_through_offer__c() {
		return propstrength__property_alloted_through_offer__c;
	}
	public void setPropstrength__property_alloted_through_offer__c(
			boolean propstrength__property_alloted_through_offer__c) {
		this.propstrength__property_alloted_through_offer__c = propstrength__property_alloted_through_offer__c;
	}
	public boolean isPropstrength__project_name__c() {
		return propstrength__project_name__c;
	}
	public void setPropstrength__project_name__c(boolean propstrength__project_name__c) {
		this.propstrength__project_name__c = propstrength__project_name__c;
	}
	public boolean isEoi_unit_locked() {
		return eoi_unit_locked;
	}
	public void setEoi_unit_locked(boolean eoi_unit_locked) {
		this.eoi_unit_locked = eoi_unit_locked;
	}
	public boolean isHold_status() {
		return hold_status;
	}
	public void setHold_status(boolean hold_status) {
		this.hold_status = hold_status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}