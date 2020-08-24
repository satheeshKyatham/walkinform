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
//@Table(name="salesforce.gpl_cs_hold_unit") // Commented for testing, uncomment this and comment below line for production deployment (Vivek Birdi)
@Table(name="salesforce.gpl_cs_hold_unit_uat") 
public class HoldInventoryEntry implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpl_cs_hold_unit_id") 
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
	
	@Transient
	private int holdMin;
	
	@Transient
	private int holdSec;
	
	@Column(name = "tower_name")
	private String tower_name;
	
	@Column(name = "tower_code")
	private String tower_code;
	
	@Column(name = "floor_no")
	private String floor_no;
	
	@Column(name = "unit_no")
	private String unit_no;
	
	@Column(name = "user_id")
	private int user_id;
	
	@Column(name="version")
	private int version;
	
	@Column(name="hold_for_time")
	private int holdForTime;
	
	
	@Column(name = "source")
	private String source;
	
	@Column(name = "contact_sfid")
	private String contact_sfid;
	
	
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

	public String getTower_name() {
		return tower_name;
	}

	public void setTower_name(String tower_name) {
		this.tower_name = tower_name;
	}

	public String gettower_code() {
		return tower_code;
	}

	public void settower_code(String tower_code) {
		this.tower_code = tower_code;
	}

	public String getFloor_no() {
		return floor_no;
	}

	public void setFloor_no(String floor_no) {
		this.floor_no = floor_no;
	}

	public String getUnit_no() {
		return unit_no;
	}

	public void setUnit_no(String unit_no) {
		this.unit_no = unit_no;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getHoldForTime() {
		return holdForTime;
	}

	public void setHoldForTime(int holdForTime) {
		this.holdForTime = holdForTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getContact_sfid() {
		return contact_sfid;
	}

	public void setContact_sfid(String contact_sfid) {
		this.contact_sfid = contact_sfid;
	}
}