package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="salesforce.gpl_cs_scheme_source")
public class SchemeSource implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")private int id;
	@Column(name = "region_name") private String region_name;
	@Column(name = "project_name") private String project_name;
	@Column(name = "scheme") private String scheme;
	@Column(name = "isactive") private String isactive;
	@Column(name = "project_id") private String project_id;
	@Column(name = "cp_flag_yn") private String cp_flag_yn;
	
	@Column(name = "createdby") private String createdby;
	@Column(name = "updatedby") private String updatedby;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getCp_flag_yn() {
		return cp_flag_yn;
	}
	public void setCp_flag_yn(String cp_flag_yn) {
		this.cp_flag_yn = cp_flag_yn;
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
}