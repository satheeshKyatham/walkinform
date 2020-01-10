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
@Table(name = "salesforce.vw_project_inventory_uat") 
public class InventoryReport implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="gpl_cs_hold_admin_unit_id") private  int id ;
	@Column(name="unit_sfid") private String	unit_sfid;
	@Column(name="admin_userid") private String	admin_userid;
	@Column(name="project_id") private String	project_id;
	@Column(name="hold_reason") private String	hold_reason;
	@Column(name="hold_status") private String	hold_status;
	@Column(name="hold_description") private String	hold_description;
	@Column(name="hold_behalf_username") private String	hold_behalf_username;
	@Column(name="floor_number__c") private double	floor_number__c;
	@Column(name="propstrength__house_unit_no__c") private String	propstrength__house_unit_no__c;
	@Column(name="tower_code__c") private String	tower_code__c;
	@Column(name="propstrength__unit_type__c") private String	propstrength__unit_type__c;
	@Column(name="admin_name") private String	admin_name;
	@Column(name="admin_emailid") private String	admin_emailid;
	@Column(name="propstrength__active__c") private boolean	propstrength__active__c;
	@Column(name="tower_name__c") private String tower_name__c;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUnit_sfid() {
		return unit_sfid;
	}
	public void setUnit_sfid(String unit_sfid) {
		this.unit_sfid = unit_sfid;
	}
	public String getAdmin_userid() {
		return admin_userid;
	}
	public void setAdmin_userid(String admin_userid) {
		this.admin_userid = admin_userid;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getHold_reason() {
		return hold_reason;
	}
	public void setHold_reason(String hold_reason) {
		this.hold_reason = hold_reason;
	}
	public String getHold_status() {
		return hold_status;
	}
	public void setHold_status(String hold_status) {
		this.hold_status = hold_status;
	}
	public String getHold_description() {
		return hold_description;
	}
	public void setHold_description(String hold_description) {
		this.hold_description = hold_description;
	}
	public String getHold_behalf_username() {
		return hold_behalf_username;
	}
	public void setHold_behalf_username(String hold_behalf_username) {
		this.hold_behalf_username = hold_behalf_username;
	}
	public double getFloor_number__c() {
		return floor_number__c;
	}
	public void setFloor_number__c(double floor_number__c) {
		this.floor_number__c = floor_number__c;
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
	public String getPropstrength__unit_type__c() {
		return propstrength__unit_type__c;
	}
	public void setPropstrength__unit_type__c(String propstrength__unit_type__c) {
		this.propstrength__unit_type__c = propstrength__unit_type__c;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_emailid() {
		return admin_emailid;
	}
	public void setAdmin_emailid(String admin_emailid) {
		this.admin_emailid = admin_emailid;
	}
	public boolean isPropstrength__active__c() {
		return propstrength__active__c;
	}
	public void setPropstrength__active__c(boolean propstrength__active__c) {
		this.propstrength__active__c = propstrength__active__c;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTower_name__c() {
		return tower_name__c;
	}
	public void setTower_name__c(String tower_name__c) {
		this.tower_name__c = tower_name__c;
	}
}