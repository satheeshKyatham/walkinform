package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="salesforce.gpl_cs_tnc_against_pymtplan")

public class TnC implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpl_cs_tnc_against_pymtplan_id")
	private int id;	
	
	@Column(name = "tnc_text")
	private String tnc_text;
	
	@Column(name = "pymt_plan_id")
	private String pymt_plan_id;
	
	@Column(name = "pymt_plan_name")
	private String pymt_plan_name;
	
	@Column(name = "project_id")
	private String project_id;
	
	@Column(name = "project_name")
	private String project_name;
	
	@Column(name = "region_id")
	private String region_id;
	
	@Column(name = "region_name")
	private String region_name;
	
	@Column(name = "isactive")
	private String isactive;

	@Column(name = "createdby")
	private String createdby;

	@Column(name = "updatedby")
	private String updatedby;
	
	@Transient
	private String insertStatus;
	
	@Column(name = "tower_sfid")
	private String tower_sfid;
	@Column(name = "tower_name")	private String tower_name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTnc_text() {
		return tnc_text;
	}

	public void setTnc_text(String tnc_text) {
		this.tnc_text = tnc_text;
	}

	public String getPymt_plan_id() {
		return pymt_plan_id;
	}

	public void setPymt_plan_id(String pymt_plan_id) {
		this.pymt_plan_id = pymt_plan_id;
	}

	public String getPymt_plan_name() {
		return pymt_plan_name;
	}

	public void setPymt_plan_name(String pymt_plan_name) {
		this.pymt_plan_name = pymt_plan_name;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
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

	public String getInsertStatus() {
		return insertStatus;
	}

	public void setInsertStatus(String insertStatus) {
		this.insertStatus = insertStatus;
	}

	public String getTower_sfid() {
		return tower_sfid;
	}

	public void setTower_sfid(String tower_sfid) {
		this.tower_sfid = tower_sfid;
	}

	public String getTower_name() {
		return tower_name;
	}

	public void setTower_name(String tower_name) {
		this.tower_name = tower_name;
	}
	
}