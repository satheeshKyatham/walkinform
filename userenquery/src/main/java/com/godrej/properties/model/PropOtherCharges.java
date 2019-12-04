package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="salesforce.vw_prop_other_charges")
public class PropOtherCharges implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="row_number") private int row_number;
	@Column(name="propstrength__rate_per_unit_area__c") private double propstrength__rate_per_unit_area__c;
	@Column(name="propstrength__fixed_charge__c") private double propstrength__fixed_charge__c;
	@Column(name="propstrength__type__c") private String propstrength__type__c;
	@Column(name="propstrength__other_charge_code__c") private String propstrength__other_charge_code__c;
	@Column(name="sum") private double sum;
	
	@Column(name="propstrength__Property__c") private String propstrength__Property__c;
	
	@Column(name="propstrength__part_of_cop__c") private boolean propstrength__part_of_cop__c;
	
	
	@Column(name="propstrength__tax_percentage__c") private double propstrength__tax_percentage__c;
	@Column(name="propstrength__pmay_gst__c") private double propstrength__pmay_gst__c;
	
	@Column(name="propstrength__new_pmay_gst__c") private double propstrength__new_pmay_gst__c;
	@Column(name="propstrength__new_tax_percentage__c") private double propstrength__new_tax_percentage__c;
	
	@Column(name="propstrength__pmay_abatement__c") private boolean propstrength__pmay_abatement__c;
	@Column(name="propstrength__gst_status__c") private String propstrength__gst_status__c;
	
	@Column(name="propstrength__st_on_completion_certificate__c") private boolean propstrength__st_on_completion_certificate__c;
	
	
	@Column(name="propstrength__sta_applicable__c") private boolean propstrength__sta_applicable__c;
	
	
	@Column(name="propstrength__completion_certificate_received__c") private boolean propstrength__completion_certificate_received__c;
	
	
	
	
	
	public double getPropstrength__rate_per_unit_area__c() {
		return propstrength__rate_per_unit_area__c;
	}
	public void setPropstrength__rate_per_unit_area__c(double propstrength__rate_per_unit_area__c) {
		this.propstrength__rate_per_unit_area__c = propstrength__rate_per_unit_area__c;
	}
	public double getPropstrength__fixed_charge__c() {
		return propstrength__fixed_charge__c;
	}
	public void setPropstrength__fixed_charge__c(double propstrength__fixed_charge__c) {
		this.propstrength__fixed_charge__c = propstrength__fixed_charge__c;
	}
	public String getPropstrength__type__c() {
		return propstrength__type__c;
	}
	public void setPropstrength__type__c(String propstrength__type__c) {
		this.propstrength__type__c = propstrength__type__c;
	}
	public String getPropstrength__other_charge_code__c() {
		return propstrength__other_charge_code__c;
	}
	public void setPropstrength__other_charge_code__c(String propstrength__other_charge_code__c) {
		this.propstrength__other_charge_code__c = propstrength__other_charge_code__c;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getRow_number() {
		return row_number;
	}
	public void setRow_number(int row_number) {
		this.row_number = row_number;
	}
	public String getPropstrength__Property__c() {
		return propstrength__Property__c;
	}
	public void setPropstrength__Property__c(String propstrength__Property__c) {
		this.propstrength__Property__c = propstrength__Property__c;
	}
	public boolean isPropstrength__part_of_cop__c() {
		return propstrength__part_of_cop__c;
	}
	public void setPropstrength__part_of_cop__c(boolean propstrength__part_of_cop__c) {
		this.propstrength__part_of_cop__c = propstrength__part_of_cop__c;
	}
	public double getPropstrength__tax_percentage__c() {
		return propstrength__tax_percentage__c;
	}
	public void setPropstrength__tax_percentage__c(double propstrength__tax_percentage__c) {
		this.propstrength__tax_percentage__c = propstrength__tax_percentage__c;
	}
	public double getPropstrength__pmay_gst__c() {
		return propstrength__pmay_gst__c;
	}
	public void setPropstrength__pmay_gst__c(double propstrength__pmay_gst__c) {
		this.propstrength__pmay_gst__c = propstrength__pmay_gst__c;
	}
	public double getPropstrength__new_pmay_gst__c() {
		return propstrength__new_pmay_gst__c;
	}
	public void setPropstrength__new_pmay_gst__c(double propstrength__new_pmay_gst__c) {
		this.propstrength__new_pmay_gst__c = propstrength__new_pmay_gst__c;
	}
	public double getPropstrength__new_tax_percentage__c() {
		return propstrength__new_tax_percentage__c;
	}
	public void setPropstrength__new_tax_percentage__c(double propstrength__new_tax_percentage__c) {
		this.propstrength__new_tax_percentage__c = propstrength__new_tax_percentage__c;
	}
	public boolean isPropstrength__pmay_abatement__c() {
		return propstrength__pmay_abatement__c;
	}
	public void setPropstrength__pmay_abatement__c(boolean propstrength__pmay_abatement__c) {
		this.propstrength__pmay_abatement__c = propstrength__pmay_abatement__c;
	}
	public String getPropstrength__gst_status__c() {
		return propstrength__gst_status__c;
	}
	public void setPropstrength__gst_status__c(String propstrength__gst_status__c) {
		this.propstrength__gst_status__c = propstrength__gst_status__c;
	}
	public boolean isPropstrength__st_on_completion_certificate__c() {
		return propstrength__st_on_completion_certificate__c;
	}
	public void setPropstrength__st_on_completion_certificate__c(boolean propstrength__st_on_completion_certificate__c) {
		this.propstrength__st_on_completion_certificate__c = propstrength__st_on_completion_certificate__c;
	}
	public boolean isPropstrength__sta_applicable__c() {
		return propstrength__sta_applicable__c;
	}
	public void setPropstrength__sta_applicable__c(boolean propstrength__sta_applicable__c) {
		this.propstrength__sta_applicable__c = propstrength__sta_applicable__c;
	}
	public boolean isPropstrength__completion_certificate_received__c() {
		return propstrength__completion_certificate_received__c;
	}
	public void setPropstrength__completion_certificate_received__c(
			boolean propstrength__completion_certificate_received__c) {
		this.propstrength__completion_certificate_received__c = propstrength__completion_certificate_received__c;
	}
	
	/*
	 * @Column(name="propstrength__custom_plan__c") private boolean
	 * propstrength__custom_plan__c;
	 * 
	 * @Column(name="createddate") private Timestamp createddate;
	 * 
	 * @Column(name="propstrength__active__c") private boolean
	 * propstrength__active__c;
	 * 
	 * @Column(name="propstrength__total_percent__c") private Double
	 * propstrength__total_percent__c;
	 * 
	 * @Column(name="propstrength__payment_plan_code__c") private String
	 * propstrength__payment_plan_code__c;
	 * 
	 * @Column(name="sfid") private String sfid;
	 */
}