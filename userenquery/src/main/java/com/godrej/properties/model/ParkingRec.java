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
public class ParkingRec implements Serializable{
	
	private static final long serialVersionUID = 1L; 
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  private int id;
	
	@Column(name = "sfid") private String sfid;
	
	@Column(name = "propstrength__car_parking_name__c") private String propstrength__car_parking_name__c;
	@Column(name = "propstrength__parking_type__c") private String propstrength__parking_type__c;
	@Column(name = "propstrength__category_of_parking__c") private String propstrength__category_of_parking__c;
	@Column(name = "propstrength__parking_size__c") private String propstrength__parking_size__c;
	@Column(name = "location_of_parking__c") private String location_of_parking__c;
	@Column(name = "dimensions__c") private String dimensions__c;
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
	public String getLocation_of_parking__c() {
		return location_of_parking__c;
	}
	public void setLocation_of_parking__c(String location_of_parking__c) {
		this.location_of_parking__c = location_of_parking__c;
	}
	public String getDimensions__c() {
		return dimensions__c;
	}
	public void setDimensions__c(String dimensions__c) {
		this.dimensions__c = dimensions__c;
	}    
}