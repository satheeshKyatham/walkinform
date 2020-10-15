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
@Table(name = "salesforce.vw_project_inventory_uat") 
public class AllUnitData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="row_no") private int  row_number;
	@Column(name="propstrength__project_name__c") private String propstrength__project_name__c;
	@Column(name="project_name__c") private String project_name__c;
	@Column(name="propstrength__property_name__c") private String propstrength__property_name__c;
	@Column(name="tower_name__c") private String tower_name__c;
	@Column(name="propstrength__house_unit_no__c") private String propstrength__house_unit_no__c;
	@Column(name="wing_block__c") private String wing_block__c;
	@Column(name="inventory_category__c") private String inventory_category__c;
	@Column(name="propstrength__rate_per_unit_area__c", precision = 10, scale = 2)  private BigDecimal propstrength__rate_per_unit_area__c;
	@Column(name="propstrength__property_alloted_through_offer__c") private Boolean propstrength__property_alloted_through_offer__c;
	@Column(name="propstrength__active__c") private Boolean propstrength__active__c;
	@Column(name="propstrength__property_on_hold_for_reallocation__c") private Boolean propstrength__property_on_hold_for_reallocation__c;
	@Column(name="hold_reason") private String hold_reason;
	@Column(name="enq_name") private String enq_name;
	@Column(name="rera_net_carpet_sqft", precision = 10, scale = 2)  private BigDecimal rera_net_carpet_sqft;
	@Column(name="rera_exclusive_sqft", precision = 10, scale = 2)  private BigDecimal rera_exclusive_sqft;
	@Column(name="saleable_area__c", precision = 10, scale = 2)  private BigDecimal saleable_area__c;
	@Column(name="super_area", precision = 10, scale = 2)  private BigDecimal super_area;
	@Column(name="propstrength__allotted__c") private Boolean propstrength__allotted__c;
	@Column(name="hold_status") private Boolean hold_status;
	@Column(name="cop", precision = 10, scale = 2)  private BigDecimal cop;
	
	@Column(name="property_facing__c") private String property_facing__c;
	
	@Column(name="verticle__c") private String verticle__c;
	@Column(name="propstrength__tower__c") private String propstrength__tower__c;
	
	@Column(name="propstrength__unit_type__c") private String propstrength__unit_type__c;
	
	@Transient private String qry_count;
	@Transient private String qry_msg;
	
	public int getRow_number() {
		return row_number;
	}
	public void setRow_number(int row_number) {
		this.row_number = row_number;
	}
	public String getPropstrength__project_name__c() {
		return propstrength__project_name__c;
	}
	public void setPropstrength__project_name__c(String propstrength__project_name__c) {
		this.propstrength__project_name__c = propstrength__project_name__c;
	}
	public String getTower_name__c() {
		return tower_name__c;
	}
	public void setTower_name__c(String tower_name__c) {
		this.tower_name__c = tower_name__c;
	}
	public String getPropstrength__house_unit_no__c() {
		return propstrength__house_unit_no__c;
	}
	public void setPropstrength__house_unit_no__c(String propstrength__house_unit_no__c) {
		this.propstrength__house_unit_no__c = propstrength__house_unit_no__c;
	}
	public String getWing_block__c() {
		return wing_block__c;
	}
	public void setWing_block__c(String wing_block__c) {
		this.wing_block__c = wing_block__c;
	}
	public String getInventory_category__c() {
		return inventory_category__c;
	}
	public void setInventory_category__c(String inventory_category__c) {
		this.inventory_category__c = inventory_category__c;
	}
	public BigDecimal getPropstrength__rate_per_unit_area__c() {
		return propstrength__rate_per_unit_area__c;
	}
	public void setPropstrength__rate_per_unit_area__c(BigDecimal propstrength__rate_per_unit_area__c) {
		this.propstrength__rate_per_unit_area__c = propstrength__rate_per_unit_area__c;
	}
	public Boolean getPropstrength__property_alloted_through_offer__c() {
		return propstrength__property_alloted_through_offer__c;
	}
	public void setPropstrength__property_alloted_through_offer__c(
			Boolean propstrength__property_alloted_through_offer__c) {
		this.propstrength__property_alloted_through_offer__c = propstrength__property_alloted_through_offer__c;
	}
	 
	public Boolean getPropstrength__active__c() {
		return propstrength__active__c;
	}
	public void setPropstrength__active__c(Boolean propstrength__active__c) {
		this.propstrength__active__c = propstrength__active__c;
	}
	public Boolean getPropstrength__property_on_hold_for_reallocation__c() {
		return propstrength__property_on_hold_for_reallocation__c;
	}
	public void setPropstrength__property_on_hold_for_reallocation__c(
			Boolean propstrength__property_on_hold_for_reallocation__c) {
		this.propstrength__property_on_hold_for_reallocation__c = propstrength__property_on_hold_for_reallocation__c;
	}
	public String getHold_reason() {
		return hold_reason;
	}
	public void setHold_reason(String hold_reason) {
		this.hold_reason = hold_reason;
	}
	public String getEnq_name() {
		return enq_name;
	}
	public void setEnq_name(String enq_name) {
		this.enq_name = enq_name;
	}
	public BigDecimal getRera_net_carpet_sqft() {
		return rera_net_carpet_sqft;
	}
	public void setRera_net_carpet_sqft(BigDecimal rera_net_carpet_sqft) {
		this.rera_net_carpet_sqft = rera_net_carpet_sqft;
	}
	public BigDecimal getRera_exclusive_sqft() {
		return rera_exclusive_sqft;
	}
	public void setRera_exclusive_sqft(BigDecimal rera_exclusive_sqft) {
		this.rera_exclusive_sqft = rera_exclusive_sqft;
	}
	public BigDecimal getSaleable_area__c() {
		return saleable_area__c;
	}
	public void setSaleable_area__c(BigDecimal saleable_area__c) {
		this.saleable_area__c = saleable_area__c;
	}
	public BigDecimal getSuper_area() {
		return super_area;
	}
	public void setSuper_area(BigDecimal super_area) {
		this.super_area = super_area;
	}
	public String getProject_name__c() {
		return project_name__c;
	}
	public void setProject_name__c(String project_name__c) {
		this.project_name__c = project_name__c;
	}
	public String getPropstrength__property_name__c() {
		return propstrength__property_name__c;
	}
	public void setPropstrength__property_name__c(String propstrength__property_name__c) {
		this.propstrength__property_name__c = propstrength__property_name__c;
	}
	public Boolean getPropstrength__allotted__c() {
		return propstrength__allotted__c;
	}
	public void setPropstrength__allotted__c(Boolean propstrength__allotted__c) {
		this.propstrength__allotted__c = propstrength__allotted__c;
	}
	public Boolean getHold_status() {
		return hold_status;
	}
	public void setHold_status(Boolean hold_status) {
		this.hold_status = hold_status;
	}
	public BigDecimal getCop() {
		return cop;
	}
	public void setCop(BigDecimal cop) {
		this.cop = cop;
	}
	public String getProperty_facing__c() {
		return property_facing__c;
	}
	public void setProperty_facing__c(String property_facing__c) {
		this.property_facing__c = property_facing__c;
	}
	public String getVerticle__c() {
		return verticle__c;
	}
	public void setVerticle__c(String verticle__c) {
		this.verticle__c = verticle__c;
	}
	public String getPropstrength__tower__c() {
		return propstrength__tower__c;
	}
	public void setPropstrength__tower__c(String propstrength__tower__c) {
		this.propstrength__tower__c = propstrength__tower__c;
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
	public String getPropstrength__unit_type__c() {
		return propstrength__unit_type__c;
	}
	public void setPropstrength__unit_type__c(String propstrength__unit_type__c) {
		this.propstrength__unit_type__c = propstrength__unit_type__c;
	}	
}