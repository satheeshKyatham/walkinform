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
@Table(name="salesforce.nv_eoi_sample_unit")
public class SampleUnit implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") private int id;
	
	@Column(name="project_sfid") private String project_sfid;
	@Column(name="tower_code") private String tower_code;
	@Column(name="typology") private String typology;
	@Column(name="floor_range_name") private String floor_range_name;
	@Column(name="sample_unit_name") private String sample_unit_name;
	@Column(name="createdby") private String createdby;
	@Column(name="updatedby") private String updatedby;
	@Column(name="isactive") private String isactive;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProject_sfid() {
		return project_sfid;
	}
	public void setProject_sfid(String project_sfid) {
		this.project_sfid = project_sfid;
	}
	public String getTower_code() {
		return tower_code;
	}
	public void setTower_code(String tower_code) {
		this.tower_code = tower_code;
	}
	public String getTypology() {
		return typology;
	}
	public void setTypology(String typology) {
		this.typology = typology;
	}
	public String getFloor_range_name() {
		return floor_range_name;
	}
	public void setFloor_range_name(String floor_range_name) {
		this.floor_range_name = floor_range_name;
	}
	public String getSample_unit_name() {
		return sample_unit_name;
	}
	public void setSample_unit_name(String sample_unit_name) {
		this.sample_unit_name = sample_unit_name;
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
}