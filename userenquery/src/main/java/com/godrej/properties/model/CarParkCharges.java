package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.gpl_cs_carpark_charges")

public class CarParkCharges implements Serializable{
	
	
private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpl_cs_carpark_charges_id")
	private int id;	
	
	@Column(name = "region_name")
	private String region_name;
	
	@Column(name = "region_id")
	private String region_id;
	
	@Column(name = "project_name")
	private String project_name;
	
	@Column(name = "project_id")
	private String project_id;
	
	@Column(name = "park_type")
	private String park_type;
	
	@Column(name = "amount")
	private String amount;
	
	@Column(name = "isactive")
	private String isactive;

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

	public String getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id = region_id;
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

	public String getPark_type() {
		return park_type;
	}

	public void setPark_type(String park_type) {
		this.park_type = park_type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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
}