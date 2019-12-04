package com.godrej.properties.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="salesforce.gpl_cs_eoi_header_dtl")

public class EOIPreferenceDtl implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpl_cs_eoi_header_dtl_id")
	private int id;	
	
	@Column(name = "request_id")
	private int request_id;
	
	@Column(name = "enq_sfid")
	private String enq_sfid;
	
	@Column(name = "project_sfid")
	private String project_sfid;
	
	@Column(name = "project_name")
	private String project_name;
	
	@Column(name = "userid")
	private int userid;
	
	@Column(name = "user_email")
	private String user_email;
	
	@Column(name = "user_name")
	private String user_name;
	
	@Column(name = "tower_id")
	private String tower_id;
	
	@Column(name = "tower_name")
	private String tower_name;
	
	@Column(name = "typology_id")
	private String typology_id;
	
	@Column(name = "typology_name")
	private String typology_name;
	
	@Column(name = "unit_id")
	private String unit_id;
	
	@Column(name = "unit_name")
	private String unit_name;
	
	@Column(name = "floor_band")
	private String floor_band;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "eoi_date")
	private Date eoi_date;
	
	@Column(name = "createdby")
	private String createdby;
	
	@Column(name = "updatedby")
	private String updatedby;
	
	@Column(name = "isactive")
	private String isactive;
	
	@Column(name = "token_no")
	private int token_no;
	
	@Transient
	private String eoi_date_string;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRequest_id() {
		return request_id;
	}

	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}

	public String getEnq_sfid() {
		return enq_sfid;
	}

	public void setEnq_sfid(String enq_sfid) {
		this.enq_sfid = enq_sfid;
	}

	public String getProject_sfid() {
		return project_sfid;
	}

	public void setProject_sfid(String project_sfid) {
		this.project_sfid = project_sfid;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getTower_id() {
		return tower_id;
	}

	public void setTower_id(String tower_id) {
		this.tower_id = tower_id;
	}

	public String getTower_name() {
		return tower_name;
	}

	public void setTower_name(String tower_name) {
		this.tower_name = tower_name;
	}

	public String getTypology_id() {
		return typology_id;
	}

	public void setTypology_id(String typology_id) {
		this.typology_id = typology_id;
	}

	public String getTypology_name() {
		return typology_name;
	}

	public void setTypology_name(String typology_name) {
		this.typology_name = typology_name;
	}

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getFloor_band() {
		return floor_band;
	}

	public void setFloor_band(String floor_band) {
		this.floor_band = floor_band;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEoi_date() {
		return eoi_date;
	}

	public void setEoi_date(Date eoi_date) {
		this.eoi_date = eoi_date;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public int getToken_no() {
		return token_no;
	}

	public void setToken_no(int token_no) {
		this.token_no = token_no;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getEoi_date_string() {
		return eoi_date_string;
	}

	public void setEoi_date_string(String eoi_date_string) {
		this.eoi_date_string = eoi_date_string;
	}
}