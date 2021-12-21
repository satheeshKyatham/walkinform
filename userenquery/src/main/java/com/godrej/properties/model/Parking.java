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
@Table(name="salesforce.propstrength__car_parking__c")
public class Parking implements Serializable{
	
	private static final long serialVersionUID = 1L; 
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  private int id;
	
	@Column(name = "sfid") private String sfid;
	
	@Column(name = "propstrength__car_parking_name__c") private String propstrength__car_parking_name__c;
	@Column(name = "propstrength__parking_type__c") private String propstrength__parking_type__c;
	@Column(name = "propstrength__category_of_parking__c") private String propstrength__category_of_parking__c;
	@Column(name = "propstrength__parking_size__c") private String propstrength__parking_size__c;
	@Column(name = "property__c") private String property__c;
	
	@Column(name = "location_of_parking__c") private String location_of_parking__c;
	
	@Column(name = "propstrength__tower_code__c") private String propstrength__tower_code__c;
	
	//@Column(name = "car_parking_18_digit__c") private String car_parking_18_digit__c;
	
	@Column(name = "propstrength__project__c") private String propstrength__project__c;
	@Column(name = "propstrength__tower_name__c") private String propstrength__tower_name__c;
	              
	@Column(name = "propstrength__is_released__c") private Boolean propstrength__is_released__c;
	@Column(name = "propstrength__is_parking_blocked__c") private Boolean propstrength__is_parking_blocked__c;
	@Column(name = "propstrength__allotted__c") private Boolean propstrength__allotted__c;
	@Column(name = "propstrength__active__c") private Boolean propstrength__active__c;
	@Column(name = "absolute_amount") private Double absolute_amount;
	
	//@Transient private String location_of_parking__c;
	@Transient private String car_parking_18_digit__c;
	
	@Column(name = "created_at") private Timestamp created_at;
	@Column(name = "holdstatusyn") private String holdstatusyn;
	@Column(name = "statusai") private String holdIntervalstatusAI;
	@Transient private String flagForHold;
	@Column(name = "hold_for_time")  private Integer holdForTime;
	@Transient private Integer holdMin;
	@Transient private Integer holdSec;
	@Column(name = "hold_user_id")  private Integer hold_user_id;
	
	@Column(name = "hold_status")  private Boolean hold_status;
	@Column(name = "hold_reason") private String hold_reason;
	
	@Column(name = "sfdc_propstrength__allotted__c")  private Boolean sfdc_propstrength__allotted__c;
	@Column(name = "flatsfid") private String flatsfid;
	
	@Column(name = "allotted_through_offer__c")  private Boolean allotted_through_offer__c;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSfid() {
		return sfid;
	}
	public void setSfid(String sfid) {
		this.sfid = sfid;
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
	public String getProperty__c() {
		return property__c;
	}
	public void setProperty__c(String property__c) {
		this.property__c = property__c;
	}
	public String getPropstrength__project__c() {
		return propstrength__project__c;
	}
	public void setPropstrength__project__c(String propstrength__project__c) {
		this.propstrength__project__c = propstrength__project__c;
	}
	public String getPropstrength__tower_name__c() {
		return propstrength__tower_name__c;
	}
	public void setPropstrength__tower_name__c(String propstrength__tower_name__c) {
		this.propstrength__tower_name__c = propstrength__tower_name__c;
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
	public Boolean getPropstrength__allotted__c() {
		return propstrength__allotted__c;
	}
	public void setPropstrength__allotted__c(Boolean propstrength__allotted__c) {
		this.propstrength__allotted__c = propstrength__allotted__c;
	}
	public Boolean getPropstrength__active__c() {
		return propstrength__active__c;
	}
	public void setPropstrength__active__c(Boolean propstrength__active__c) {
		this.propstrength__active__c = propstrength__active__c;
	}
	public Double getAbsolute_amount() {
		return absolute_amount;
	}
	public void setAbsolute_amount(Double absolute_amount) {
		this.absolute_amount = absolute_amount;
	}
	public String getLocation_of_parking__c() {
		return location_of_parking__c;
	}
	public void setLocation_of_parking__c(String location_of_parking__c) {
		this.location_of_parking__c = location_of_parking__c;
	}
	public String getCar_parking_18_digit__c() {
		return car_parking_18_digit__c;
	}
	public void setCar_parking_18_digit__c(String car_parking_18_digit__c) {
		this.car_parking_18_digit__c = car_parking_18_digit__c;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public String getHoldstatusyn() {
		return holdstatusyn;
	}
	public void setHoldstatusyn(String holdstatusyn) {
		this.holdstatusyn = holdstatusyn;
	}
	public String getHoldIntervalstatusAI() {
		return holdIntervalstatusAI;
	}
	public void setHoldIntervalstatusAI(String holdIntervalstatusAI) {
		this.holdIntervalstatusAI = holdIntervalstatusAI;
	}
	public String getFlagForHold() {
		return flagForHold;
	}
	public void setFlagForHold(String flagForHold) {
		this.flagForHold = flagForHold;
	}
	public Integer getHoldForTime() {
		return holdForTime;
	}
	public void setHoldForTime(Integer holdForTime) {
		this.holdForTime = holdForTime;
	}
	public Integer getHoldMin() {
		return holdMin;
	}
	public void setHoldMin(Integer holdMin) {
		this.holdMin = holdMin;
	}
	public Integer getHoldSec() {
		return holdSec;
	}
	public void setHoldSec(Integer holdSec) {
		this.holdSec = holdSec;
	}
	public Integer getHold_user_id() {
		return hold_user_id;
	}
	public void setHold_user_id(Integer hold_user_id) {
		this.hold_user_id = hold_user_id;
	}
	public Boolean getHold_status() {
		return hold_status;
	}
	public void setHold_status(Boolean hold_status) {
		this.hold_status = hold_status;
	}
	public String getHold_reason() {
		return hold_reason;
	}
	public void setHold_reason(String hold_reason) {
		this.hold_reason = hold_reason;
	}
	public Boolean getSfdc_propstrength__allotted__c() {
		return sfdc_propstrength__allotted__c;
	}
	public void setSfdc_propstrength__allotted__c(Boolean sfdc_propstrength__allotted__c) {
		this.sfdc_propstrength__allotted__c = sfdc_propstrength__allotted__c;
	}
	public String getFlatsfid() {
		return flatsfid;
	}
	public void setFlatsfid(String flatsfid) {
		this.flatsfid = flatsfid;
	}
	public String getPropstrength__tower_code__c() {
		return propstrength__tower_code__c;
	}
	public void setPropstrength__tower_code__c(String propstrength__tower_code__c) {
		this.propstrength__tower_code__c = propstrength__tower_code__c;
	}
	public Boolean getAllotted_through_offer__c() {
		return allotted_through_offer__c;
	}
	public void setAllotted_through_offer__c(Boolean allotted_through_offer__c) {
		this.allotted_through_offer__c = allotted_through_offer__c;
	}
}