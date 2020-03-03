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
@Table(name="salesforce.vw_project_floorplan")
public class PaymentPlan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sfid") private String  sfid;
	@Column(name="tower_code__c") private String  tower_code__c;
	@Column(name="tower_name__c") private String  tower_name__c;
	@Column(name="propstrength__floor_number__c") private String  propstrength__floor_number__c;
	@Column(name="floor_name__c") private String  floor_name__c;
	@Column(name="propstrength__house_unit_no__c") private String  propstrength__house_unit_no__c;
	@Column(name="propstrength__unit_type__c") private String  propstrength__unit_type__c;
	@Column(name="propstrength__rate_per_unit_area__c") private String  propstrength__rate_per_unit_area__c;
	@Column(name="propstrength__carpet_area__c") private String  propstrength__carpet_area__c;
	@Column(name="terrace_area_sq_ft__c") private String  terrace_area_sq_ft__c;
	@Column(name="saleable_area__c") private String  saleable_area__c;
	@Transient  private String  generator_charges="60";
	@Transient  private String  gas_bank_charges="30";
	@Transient  private String  bescom_water_supply="150";
	@Transient  private String  legal_and_khata_charges="60";
	@Column(name="appurtenant_area_sq_mt__c") private double  appurtenant_area_sq_mt__c;
	@Column(name="carpet_area__c") private String  carpet_area__c;
	@Column(name="exclusive_area__c") private String  exclusive_area__c;
	@Column(name="common_area__c") private String  common_area__c;
	@Column(name="balcony_area_sq_ft__c") private String  balcony_area_sq_ft__c;
	@Column(name="balcony_area_sq_mt__c") private String  balcony_area_sq_mt__c;
	@Column(name="terrace_area_sq_mt__c") private String  terrace_area_sq_mt__c;
	
	//@Column(name="carpet_area_converted__c") private String  carpet_area_converted__c;
	
	@Column(name="open_balc_sq_mt__c") private String  carpet_area_converted__c;
	
	@Column(name="propstrength__property_name__c") private String  propstrength__property_name__c;
	@Column(name="propstrength__allotted__c") private boolean  propstrength__allotted__c;
 
	
	@Column(name="pmay") private boolean  pmay;
	@Column(name="new_tax") private String  new_tax;
	@Column(name="old_tax") private String  old_tax;
	@Column(name="propstrength__gst_status__c") private String  propstrength__gst_status__c;
	
	@Column(name="bsp_amount") private String  bsp_amount;
	
	@Column(name="propstrength__completion_certificate_received__c") private boolean  propstrength__completion_certificate_received__c;
	@Column(name="propstrength__category__c") private String  propstrength__category__c;
	@Column(name="propstrength__pmay_abatement__c") private boolean  propstrength__pmay_abatement__c;
	@Column(name="bank__c") private String  bank__c;
	
	@Column(name="property_facing__c") private String  property_facing__c;

	@Column(name="d4u_active__c") private boolean  d4u_active__c;
	
	
	/* @Column(name="tower_sfid") private String tower_sfid; */
	
	public boolean isPropstrength__allotted__c() {
		return propstrength__allotted__c;
	}
	public void setPropstrength__allotted__c(boolean propstrength__allotted__c) {
		this.propstrength__allotted__c = propstrength__allotted__c;
	}
 
	
 
	public String getPropstrength__property_name__c() {
		return propstrength__property_name__c;
	}
	public void setPropstrength__property_name__c(String propstrength__property_name__c) {
		this.propstrength__property_name__c = propstrength__property_name__c;
	}
	public String getBalcony_area_sq_ft__c() {
		return balcony_area_sq_ft__c;
	}
	public void setBalcony_area_sq_ft__c(String balcony_area_sq_ft__c) {
		this.balcony_area_sq_ft__c = balcony_area_sq_ft__c;
	}
	public String getBalcony_area_sq_mt__c() {
		return balcony_area_sq_mt__c;
	}
	public void setBalcony_area_sq_mt__c(String balcony_area_sq_mt__c) {
		this.balcony_area_sq_mt__c = balcony_area_sq_mt__c;
	}
	public String getTerrace_area_sq_mt__c() {
		return terrace_area_sq_mt__c;
	}
	public void setTerrace_area_sq_mt__c(String terrace_area_sq_mt__c) {
		this.terrace_area_sq_mt__c = terrace_area_sq_mt__c;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSfid() {
		return sfid;
	}
	public void setSfid(String sfid) {
		this.sfid = sfid;
	}
	public String getTower_code__c() {
		return tower_code__c;
	}
	public void setTower_code__c(String tower_code__c) {
		this.tower_code__c = tower_code__c;
	}
	public String getTower_name__c() {
		return tower_name__c;
	}
	public void setTower_name__c(String tower_name__c) {
		this.tower_name__c = tower_name__c;
	}
	public String getPropstrength__floor_number__c() {
		return propstrength__floor_number__c;
	}
	public void setPropstrength__floor_number__c(String propstrength__floor_number__c) {
		this.propstrength__floor_number__c = propstrength__floor_number__c;
	}
	public String getFloor_name__c() {
		return floor_name__c;
	}
	public void setFloor_name__c(String floor_name__c) {
		this.floor_name__c = floor_name__c;
	}
	public String getPropstrength__house_unit_no__c() {
		return propstrength__house_unit_no__c;
	}
	public void setPropstrength__house_unit_no__c(String propstrength__house_unit_no__c) {
		this.propstrength__house_unit_no__c = propstrength__house_unit_no__c;
	}
	public String getPropstrength__unit_type__c() {
		return propstrength__unit_type__c;
	}
	public void setPropstrength__unit_type__c(String propstrength__unit_type__c) {
		this.propstrength__unit_type__c = propstrength__unit_type__c;
	}
	public String getPropstrength__rate_per_unit_area__c() {
		return propstrength__rate_per_unit_area__c;
	}
	public void setPropstrength__rate_per_unit_area__c(String propstrength__rate_per_unit_area__c) {
		this.propstrength__rate_per_unit_area__c = propstrength__rate_per_unit_area__c;
	}
	public String getPropstrength__carpet_area__c() {
		return propstrength__carpet_area__c;
	}
	public void setPropstrength__carpet_area__c(String propstrength__carpet_area__c) {
		this.propstrength__carpet_area__c = propstrength__carpet_area__c;
	}
	public String getTerrace_area_sq_ft__c() {
		return terrace_area_sq_ft__c;
	}
	public void setTerrace_area_sq_ft__c(String terrace_area_sq_ft__c) {
		this.terrace_area_sq_ft__c = terrace_area_sq_ft__c;
	}
	public String getSaleable_area__c() {
		return saleable_area__c;
	}
	public void setSaleable_area__c(String saleable_area__c) {
		this.saleable_area__c = saleable_area__c;
	}
	public String getGenerator_charges() {
		return generator_charges;
	}
	public void setGenerator_charges(String generator_charges) {
		this.generator_charges = generator_charges;
	}
	public String getGas_bank_charges() {
		return gas_bank_charges;
	}
	public void setGas_bank_charges(String gas_bank_charges) {
		this.gas_bank_charges = gas_bank_charges;
	}
	 
	public String getBescom_water_supply() {
		return bescom_water_supply;
	}
	public void setBescom_water_supply(String bescom_water_supply) {
		this.bescom_water_supply = bescom_water_supply;
	}
	public String getLegal_and_khata_charges() {
		return legal_and_khata_charges;
	}
	public void setLegal_and_khata_charges(String legal_and_khata_charges) {
		this.legal_and_khata_charges = legal_and_khata_charges;
	}
	public String getCarpet_area_converted__c() {
		return carpet_area_converted__c;
	}
	public void setCarpet_area_converted__c(String carpet_area_converted__c) {
		this.carpet_area_converted__c = carpet_area_converted__c;
	}
	public String getCarpet_area__c() {
		return carpet_area__c;
	}
	public void setCarpet_area__c(String carpet_area__c) {
		this.carpet_area__c = carpet_area__c;
	}
	public String getExclusive_area__c() {
		return exclusive_area__c;
	}
	public void setExclusive_area__c(String exclusive_area__c) {
		this.exclusive_area__c = exclusive_area__c;
	}
	public String getCommon_area__c() {
		return common_area__c;
	}
	public void setCommon_area__c(String common_area__c) {
		this.common_area__c = common_area__c;
	}
	
	public boolean isPmay() {
		return pmay;
	}
	public void setPmay(boolean pmay) {
		this.pmay = pmay;
	}
	public String getNew_tax() {
		return new_tax;
	}
	public void setNew_tax(String new_tax) {
		this.new_tax = new_tax;
	}
	public String getOld_tax() {
		return old_tax;
	}
	public void setOld_tax(String old_tax) {
		this.old_tax = old_tax;
	}
	public String getPropstrength__gst_status__c() {
		return propstrength__gst_status__c;
	}
	public void setPropstrength__gst_status__c(String propstrength__gst_status__c) {
		this.propstrength__gst_status__c = propstrength__gst_status__c;
	}
	public String getBsp_amount() {
		return bsp_amount;
	}
	public void setBsp_amount(String bsp_amount) {
		this.bsp_amount = bsp_amount;
	}
	public double getAppurtenant_area_sq_mt__c() {
		return appurtenant_area_sq_mt__c;
	}
	public void setAppurtenant_area_sq_mt__c(double appurtenant_area_sq_mt__c) {
		this.appurtenant_area_sq_mt__c = appurtenant_area_sq_mt__c;
	}
	public boolean isPropstrength__completion_certificate_received__c() {
		return propstrength__completion_certificate_received__c;
	}
	public void setPropstrength__completion_certificate_received__c(
			boolean propstrength__completion_certificate_received__c) {
		this.propstrength__completion_certificate_received__c = propstrength__completion_certificate_received__c;
	}
	public String getPropstrength__category__c() {
		return propstrength__category__c;
	}
	public void setPropstrength__category__c(String propstrength__category__c) {
		this.propstrength__category__c = propstrength__category__c;
	}
	public boolean isPropstrength__pmay_abatement__c() {
		return propstrength__pmay_abatement__c;
	}
	public void setPropstrength__pmay_abatement__c(boolean propstrength__pmay_abatement__c) {
		this.propstrength__pmay_abatement__c = propstrength__pmay_abatement__c;
	}
	public String getBank__c() {
		return bank__c;
	}
	public void setBank__c(String bank__c) {
		this.bank__c = bank__c;
	}
	public String getProperty_facing__c() {
		return property_facing__c;
	}
	public void setProperty_facing__c(String property_facing__c) {
		this.property_facing__c = property_facing__c;
	}
	public boolean isD4u_active__c() {
		return d4u_active__c;
	}
	public void setD4u_active__c(boolean d4u_active__c) {
		this.d4u_active__c = d4u_active__c;
	}
}