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
@Table(name="salesforce.gpl_cs_hold_parking") 
public class HoldParkingEntry implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") private int id;
	@Column(name = "parkingsfid") private String parkingsfid;
	@Column(name = "holdstatusyn") private String holdstatusyn;
	@Column(name = "statusai") private String statusai;
	@Column(name = "created_at") private Timestamp created_at;
	@Column(name = "project_id") private String project_id;
	@Column(name = "user_id") private int user_id;
	@Column(name="version") private int version;
	@Column(name="hold_for_time") private Long holdForTime;
	@Column(name = "flatsfid") private String flatsfid;
	
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
	public String getParkingsfid() {
		return parkingsfid;
	}
	public void setParkingsfid(String parkingsfid) {
		this.parkingsfid = parkingsfid;
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
	public Long getHoldForTime() {
		return holdForTime;
	}
	public void setHoldForTime(Long holdForTime) {
		this.holdForTime = holdForTime;
	}
	public String getFlatsfid() {
		return flatsfid;
	}
	public void setFlatsfid(String flatsfid) {
		this.flatsfid = flatsfid;
	}
}