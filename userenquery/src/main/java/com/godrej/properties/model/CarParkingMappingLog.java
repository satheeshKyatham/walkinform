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
@Table(name="salesforce.nv_parking_mapping_log")
public class CarParkingMappingLog implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  private int id;
	@Column(name = "project_sfid")  private String project_sfid;
	@Column(name = "tower_sfid")  private String tower_sfid;
	@Column(name = "parking_category")  private String parking_category;
	@Column(name = "property_type_sfid")  private String property_type_sfid;
	@Column(name = "absolute_amount")  private Double absolute_amount;
	@Column(name = "created")  private Timestamp created;
	@Column(name = "createdby")  private int createdby;
	@Column(name = "updated")  private Timestamp updated;
	@Column(name = "updatedby")  private int updatedby;
	@Column(name = "isactive")  private String isactive;
	@Column(name = "property_type_name")  private String property_type_name;
	@Column(name = "property_type_code")  private String property_type_code;
	@Column(name = "nv_parking_mapping_id")  private int nv_parking_mapping_id;
	
	@Transient
	private int row_count;
	
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
	public String getTower_sfid() {
		return tower_sfid;
	}
	public void setTower_sfid(String tower_sfid) {
		this.tower_sfid = tower_sfid;
	}
	public String getParking_category() {
		return parking_category;
	}
	public void setParking_category(String parking_category) {
		this.parking_category = parking_category;
	}
	public String getProperty_type_sfid() {
		return property_type_sfid;
	}
	public void setProperty_type_sfid(String property_type_sfid) {
		this.property_type_sfid = property_type_sfid;
	}
	public Double getAbsolute_amount() {
		return absolute_amount;
	}
	public void setAbsolute_amount(Double absolute_amount) {
		this.absolute_amount = absolute_amount;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	public int getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public String getProperty_type_name() {
		return property_type_name;
	}
	public void setProperty_type_name(String property_type_name) {
		this.property_type_name = property_type_name;
	}
	public int getRow_count() {
		return row_count;
	}
	public void setRow_count(int row_count) {
		this.row_count = row_count;
	}
	public String getProperty_type_code() {
		return property_type_code;
	}
	public void setProperty_type_code(String property_type_code) {
		this.property_type_code = property_type_code;
	}
	public int getNv_parking_mapping_id() {
		return nv_parking_mapping_id;
	}
	public void setNv_parking_mapping_id(int nv_parking_mapping_id) {
		this.nv_parking_mapping_id = nv_parking_mapping_id;
	}

	
}
