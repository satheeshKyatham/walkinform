package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

@SuppressWarnings("serial")
@Entity
@Table(name="salesforce.gpl_cs_scheme_mapping")
public class SchemeMapping implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")private int id;
	@Column(name = "region_name") private String region_name;
	@Column(name = "project_name") private String project_name;
	@Column(name = "project_id") private String project_id;
	
	@Column(name = "scheme_id") private int scheme_id;
	
	@Column(name = "scheme_source_id") private int scheme_source_id;
	@Column(name = "scheme_site_id") private int scheme_site_id;
	@Column(name = "scheme_promotional_id") private int scheme_promotional_id;
	
	@Column(name = "isactive") private String isactive;
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
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public int getScheme_id() {
		return scheme_id;
	}
	public void setScheme_id(int scheme_id) {
		this.scheme_id = scheme_id;
	}
	public int getScheme_source_id() {
		return scheme_source_id;
	}
	public void setScheme_source_id(int scheme_source_id) {
		this.scheme_source_id = scheme_source_id;
	}
	public int getScheme_site_id() {
		return scheme_site_id;
	}
	public void setScheme_site_id(int scheme_site_id) {
		this.scheme_site_id = scheme_site_id;
	}
	public int getScheme_promotional_id() {
		return scheme_promotional_id;
	}
	public void setScheme_promotional_id(int scheme_promotional_id) {
		this.scheme_promotional_id = scheme_promotional_id;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
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