package com.godrej.properties.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="salesforce.vw_inventory_other_charges")
public class InventoryOtherCharges implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="id") private  int id ;
	
	//@Column(name="propstrength__fixed_charge__c") private Double propstrength__fixed_charge__c;
	@Column(name="propstrength__fixed_charge__c", precision = 10, scale = 2) private BigDecimal propstrength__fixed_charge__c;
	
	@Column(name="propstrength__type__c") private String propstrength__type__c;
	
	//@Column(name="propstrength__rate_per_unit_area__c") private Double propstrength__rate_per_unit_area__c;
	@Column(name="propstrength__rate_per_unit_area__c", precision = 10, scale = 2) private BigDecimal propstrength__rate_per_unit_area__c;
	
	@Column(name="name") private String name;
	@Column(name="propstrength__part_of_cop__c") private boolean propstrength__part_of_cop__c;
	@Column(name="propstrength__property__c") private String propstrength__property__c;
	
	@Column(name="isdeleted") private boolean isdeleted;
	@Column(name="propstrength__active__c") private boolean propstrength__active__c;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPropstrength__type__c() {
		return propstrength__type__c;
	}
	public void setPropstrength__type__c(String propstrength__type__c) {
		this.propstrength__type__c = propstrength__type__c;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isPropstrength__part_of_cop__c() {
		return propstrength__part_of_cop__c;
	}
	public void setPropstrength__part_of_cop__c(boolean propstrength__part_of_cop__c) {
		this.propstrength__part_of_cop__c = propstrength__part_of_cop__c;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPropstrength__property__c() {
		return propstrength__property__c;
	}
	public void setPropstrength__property__c(String propstrength__property__c) {
		this.propstrength__property__c = propstrength__property__c;
	}
	public boolean isIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	public boolean isPropstrength__active__c() {
		return propstrength__active__c;
	}
	public void setPropstrength__active__c(boolean propstrength__active__c) {
		this.propstrength__active__c = propstrength__active__c;
	}
	public BigDecimal getPropstrength__fixed_charge__c() {
		return propstrength__fixed_charge__c;
	}
	public void setPropstrength__fixed_charge__c(BigDecimal propstrength__fixed_charge__c) {
		this.propstrength__fixed_charge__c = propstrength__fixed_charge__c;
	}
	public BigDecimal getPropstrength__rate_per_unit_area__c() {
		return propstrength__rate_per_unit_area__c;
	}
	public void setPropstrength__rate_per_unit_area__c(BigDecimal propstrength__rate_per_unit_area__c) {
		this.propstrength__rate_per_unit_area__c = propstrength__rate_per_unit_area__c;
	}
}