package com.godrej.properties.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.vw_project_inventory_uat") 
public class ParkingReport implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="row_no") private int  row_number;
	@Column(name="id") private  int id ;
	@Column(name="created_at") private Timestamp created_at;
	@Column(name="hold_reason") private String	hold_reason;
	@Column(name="admin_emailid") private String admin_emailid;
	@Column(name="propstrength__tower_name__c") private String propstrength__tower_name__c;
	@Column(name="propstrength__house_unit_no__c") private String propstrength__house_unit_no__c;
	@Column(name="propstrength__property_name__c") private String propstrength__property_name__c;
	@Column(name="parking_tower") private String parking_tower;
	@Column(name="propstrength__car_parking_name__c") private String propstrength__car_parking_name__c;
	@Column(name="propstrength__parking_type__c") private String propstrength__parking_type__c;
	@Column(name="propstrength__category_of_parking__c") private String propstrength__category_of_parking__c;
	@Column(name="propstrength__parking_size__c") private String propstrength__parking_size__c;
	@Column(name="location_of_parking__c") private String location_of_parking__c;
	@Column(name="parking_area_sq_ft__c", precision = 10, scale = 2) private BigDecimal parking_area_sq_ft__c;
	@Column(name="parking_area_sq_mt__c", precision = 10, scale = 2) private BigDecimal parking_area_sq_mt__c;
	@Column(name="dimensions__c") private String dimensions__c;
	
	public int getRow_number() {
		return row_number;
	}
	public void setRow_number(int row_number) {
		this.row_number = row_number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public String getHold_reason() {
		return hold_reason;
	}
	public void setHold_reason(String hold_reason) {
		this.hold_reason = hold_reason;
	}
	public String getAdmin_emailid() {
		return admin_emailid;
	}
	public void setAdmin_emailid(String admin_emailid) {
		this.admin_emailid = admin_emailid;
	}
	public String getPropstrength__tower_name__c() {
		return propstrength__tower_name__c;
	}
	public void setPropstrength__tower_name__c(String propstrength__tower_name__c) {
		this.propstrength__tower_name__c = propstrength__tower_name__c;
	}
	public String getPropstrength__house_unit_no__c() {
		return propstrength__house_unit_no__c;
	}
	public void setPropstrength__house_unit_no__c(String propstrength__house_unit_no__c) {
		this.propstrength__house_unit_no__c = propstrength__house_unit_no__c;
	}
	public String getPropstrength__property_name__c() {
		return propstrength__property_name__c;
	}
	public void setPropstrength__property_name__c(String propstrength__property_name__c) {
		this.propstrength__property_name__c = propstrength__property_name__c;
	}
	public String getParking_tower() {
		return parking_tower;
	}
	public void setParking_tower(String parking_tower) {
		this.parking_tower = parking_tower;
	}
	public String getPropstrength__car_parking_name__c() {
		return propstrength__car_parking_name__c;
	}
	public void setPropstrength__car_parking_name__c(String propstrength__car_parking_name__c) {
		this.propstrength__car_parking_name__c = propstrength__car_parking_name__c;
	}
	public String getPropstrength__parking_type__c() {
		return propstrength__parking_type__c;
	}
	public void setPropstrength__parking_type__c(String propstrength__parking_type__c) {
		this.propstrength__parking_type__c = propstrength__parking_type__c;
	}
	public String getPropstrength__category_of_parking__c() {
		return propstrength__category_of_parking__c;
	}
	public void setPropstrength__category_of_parking__c(String propstrength__category_of_parking__c) {
		this.propstrength__category_of_parking__c = propstrength__category_of_parking__c;
	}
	public String getPropstrength__parking_size__c() {
		return propstrength__parking_size__c;
	}
	public void setPropstrength__parking_size__c(String propstrength__parking_size__c) {
		this.propstrength__parking_size__c = propstrength__parking_size__c;
	}
	public String getLocation_of_parking__c() {
		return location_of_parking__c;
	}
	public void setLocation_of_parking__c(String location_of_parking__c) {
		this.location_of_parking__c = location_of_parking__c;
	}
	public BigDecimal getParking_area_sq_ft__c() {
		return parking_area_sq_ft__c;
	}
	public void setParking_area_sq_ft__c(BigDecimal parking_area_sq_ft__c) {
		this.parking_area_sq_ft__c = parking_area_sq_ft__c;
	}
	public BigDecimal getParking_area_sq_mt__c() {
		return parking_area_sq_mt__c;
	}
	public void setParking_area_sq_mt__c(BigDecimal parking_area_sq_mt__c) {
		this.parking_area_sq_mt__c = parking_area_sq_mt__c;
	}
	public String getDimensions__c() {
		return dimensions__c;
	}
	public void setDimensions__c(String dimensions__c) {
		this.dimensions__c = dimensions__c;
	}
}