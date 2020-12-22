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
@Table(name="salesforce.gpl_cs_carpark_type_mst")
public class UnitFacingCount implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  private int id;
	 
	@Column(name = "propstrength__unit_type__c") private String propstrength__unit_type__c;
	@Column(name = "property_facing__c") private String property_facing__c;
	@Column(name = "sold") private Integer sold;
	@Column(name = "total") private Integer total;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPropstrength__unit_type__c() {
		return propstrength__unit_type__c;
	}
	public void setPropstrength__unit_type__c(String propstrength__unit_type__c) {
		this.propstrength__unit_type__c = propstrength__unit_type__c;
	}
	public String getProperty_facing__c() {
		return property_facing__c;
	}
	public void setProperty_facing__c(String property_facing__c) {
		this.property_facing__c = property_facing__c;
	}
	public Integer getSold() {
		return sold;
	}
	public void setSold(Integer sold) {
		this.sold = sold;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}