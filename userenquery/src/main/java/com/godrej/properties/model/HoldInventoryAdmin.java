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
@Table(name="salesforce.gpl_cs_hold_admin_unit")
public class HoldInventoryAdmin implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpl_cs_hold_admin_unit_id") 
	private int id;
	  
	@Column(name = "sfid")
	private String unitSfid;
	
	@Column(name = "holdstatusyn")
	private String holdstatusyn;

	@Column(name = "statusai")
	private String statusai;
	
	@Column(name = "customer_id")
	private String customer_id;

	@Column(name = "created_at")
	private Timestamp created_at;
	
	@Column(name = "project_id")
	private String project_id;
	
	@Column(name = "hold_status")
	private boolean hold_status;
	
	@Column(name = "hold_reason")
	private String hold_reason;
	
	@Column(name = "hold_description")
	private String hold_description;
	
	@Column(name = "hold_behalf_username")
	private String hold_behalf_username;
	
	@Column(name = "hold_behalf_userid")
	private int hold_behalf_userid;
	
	 
	public boolean isHold_status() {
		return hold_status;
	}

	public void setHold_status(boolean hold_status) {
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

	public String getUnitSfid() {
		return unitSfid;
	}

	public void setUnitSfid(String unitSfid) {
		this.unitSfid = unitSfid;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
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
	public int getHold_behalf_userid() {
		return hold_behalf_userid;
	}
	public void setHold_behalf_userid(int hold_behalf_userid) {
		this.hold_behalf_userid = hold_behalf_userid;
	}
}