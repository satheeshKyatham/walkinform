package com.godrej.properties.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "salesforce.propstrength__car_parking__c") 
public class AllParkingData implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="row_no") private int  row_number;
	@Column(name="project_name") private String project_name;
	@Column(name="tower_name") private String tower_name;
	@Column(name="name") private String name;
	@Column(name="propstrength__car_parking_name__c") private String propstrength__car_parking_name__c;
	@Column(name="propstrength__double_bay_parking__c") private String propstrength__double_bay_parking__c;
	@Column(name="propstrength__parking_size__c") private String propstrength__parking_size__c;
	@Column(name="propstrength__is_released__c") private Boolean propstrength__is_released__c;
	@Column(name="propstrength__is_parking_blocked__c") private Boolean propstrength__is_parking_blocked__c;
	@Column(name="propstrength__category_of_parking__c") private String propstrength__category_of_parking__c;
	@Column(name="location_of_parking__c") private String location_of_parking__c;
	@Column(name="parking_area_sq_ft__c", precision = 10, scale = 2)  private BigDecimal parking_area_sq_ft__c;
	@Column(name="parking_area_sq_mt__c", precision = 10, scale = 2)  private BigDecimal parking_area_sq_mt__c;
	@Column(name="dimensions__c") private String dimensions__c;
	@Column(name="propstrength__approvalstatus__c") private String propstrength__approvalstatus__c;
	@Column(name="allotted_through_offer__c") private Boolean allotted_through_offer__c;
	@Column(name="propstrength__allotted__c") private Boolean propstrength__allotted__c;
	@Column(name="hold_reason") private String hold_reason;
	@Column(name="propstrength__active__c") private Boolean propstrength__active__c;
	@Column(name="hold_status") private Boolean hold_status;
	@Transient private String qry_count;
	@Transient private String qry_msg;
	
	public int getRow_number() {
		return row_number;
	}
	public void setRow_number(int row_number) {
		this.row_number = row_number;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getTower_name() {
		return tower_name;
	}
	public void setTower_name(String tower_name) {
		this.tower_name = tower_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPropstrength__car_parking_name__c() {
		return propstrength__car_parking_name__c;
	}
	public void setPropstrength__car_parking_name__c(String propstrength__car_parking_name__c) {
		this.propstrength__car_parking_name__c = propstrength__car_parking_name__c;
	}
	public String getPropstrength__double_bay_parking__c() {
		return propstrength__double_bay_parking__c;
	}
	public void setPropstrength__double_bay_parking__c(String propstrength__double_bay_parking__c) {
		this.propstrength__double_bay_parking__c = propstrength__double_bay_parking__c;
	}
	public String getPropstrength__parking_size__c() {
		return propstrength__parking_size__c;
	}
	public void setPropstrength__parking_size__c(String propstrength__parking_size__c) {
		this.propstrength__parking_size__c = propstrength__parking_size__c;
	}
	public Boolean getPropstrength__is_released__c() {
		return propstrength__is_released__c;
	}
	public void setPropstrength__is_released__c(Boolean propstrength__is_released__c) {
		this.propstrength__is_released__c = propstrength__is_released__c;
	}
	public Boolean getPropstrength__is_parking_blocked__c() {
		return propstrength__is_parking_blocked__c;
	}
	public void setPropstrength__is_parking_blocked__c(Boolean propstrength__is_parking_blocked__c) {
		this.propstrength__is_parking_blocked__c = propstrength__is_parking_blocked__c;
	}
	public String getPropstrength__category_of_parking__c() {
		return propstrength__category_of_parking__c;
	}
	public void setPropstrength__category_of_parking__c(String propstrength__category_of_parking__c) {
		this.propstrength__category_of_parking__c = propstrength__category_of_parking__c;
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
	public String getPropstrength__approvalstatus__c() {
		return propstrength__approvalstatus__c;
	}
	public void setPropstrength__approvalstatus__c(String propstrength__approvalstatus__c) {
		this.propstrength__approvalstatus__c = propstrength__approvalstatus__c;
	}
	public Boolean getAllotted_through_offer__c() {
		return allotted_through_offer__c;
	}
	public void setAllotted_through_offer__c(Boolean allotted_through_offer__c) {
		this.allotted_through_offer__c = allotted_through_offer__c;
	}
	public Boolean getPropstrength__allotted__c() {
		return propstrength__allotted__c;
	}
	public void setPropstrength__allotted__c(Boolean propstrength__allotted__c) {
		this.propstrength__allotted__c = propstrength__allotted__c;
	}
	public String getHold_reason() {
		return hold_reason;
	}
	public void setHold_reason(String hold_reason) {
		this.hold_reason = hold_reason;
	}
	public Boolean getPropstrength__active__c() {
		return propstrength__active__c;
	}
	public void setPropstrength__active__c(Boolean propstrength__active__c) {
		this.propstrength__active__c = propstrength__active__c;
	}
	public String getQry_count() {
		return qry_count;
	}
	public void setQry_count(String qry_count) {
		this.qry_count = qry_count;
	}
	public String getQry_msg() {
		return qry_msg;
	}
	public void setQry_msg(String qry_msg) {
		this.qry_msg = qry_msg;
	}
	public Boolean getHold_status() {
		return hold_status;
	}
	public void setHold_status(Boolean hold_status) {
		this.hold_status = hold_status;
	}
}