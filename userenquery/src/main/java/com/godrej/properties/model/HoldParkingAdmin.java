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
//@Table(name="salesforce.gpl_cs_hold_admin_unit")
@Table(name="salesforce.gpl_cs_hold_admin_parking")
public class HoldParkingAdmin implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  private int id;
	  
	@Column(name = "projectsfid") private String projectsfid;
	@Column(name = "parkingsfid") private String parkingsfid;
	@Column(name = "flatsfid") private String flatsfid;
	@Column(name = "hold_reason") private String hold_reason;
	@Column(name = "hold_status") private boolean hold_status;
	@Column(name = "hold_description") private String hold_description;
	@Column(name = "created_by") private String created_by;
	@Column(name = "created_at") private Timestamp created_at;
	@Column(name="version") private int version;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjectsfid() {
		return projectsfid;
	}
	public void setProjectsfid(String projectsfid) {
		this.projectsfid = projectsfid;
	}
	public String getParkingsfid() {
		return parkingsfid;
	}
	public void setParkingsfid(String parkingsfid) {
		this.parkingsfid = parkingsfid;
	}
	public String getFlatsfid() {
		return flatsfid;
	}
	public void setFlatsfid(String flatsfid) {
		this.flatsfid = flatsfid;
	}
	public String getHold_reason() {
		return hold_reason;
	}
	public void setHold_reason(String hold_reason) {
		this.hold_reason = hold_reason;
	}
	public boolean isHold_status() {
		return hold_status;
	}
	public void setHold_status(boolean hold_status) {
		this.hold_status = hold_status;
	}
	public String getHold_description() {
		return hold_description;
	}
	public void setHold_description(String hold_description) {
		this.hold_description = hold_description;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}     
}