package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.vw_payment_plan_line_item" )

public class BSPTaxRecord {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id") private int  id;
	@Column(name="propstrength__tax_percentage__c") private double  propstrength__tax_percentage__c;
	@Column(name="propstrength__new_tax_percentage__c") private double  propstrength__new_tax_percentage__c;
	@Column(name="propstrength__pmay_gst__c") private double  propstrength__pmay_gst__c;
	@Column(name="propstrength__new_pmay_gst__c") private double  propstrength__new_pmay_gst__c;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPropstrength__tax_percentage__c() {
		return propstrength__tax_percentage__c;
	}
	public void setPropstrength__tax_percentage__c(double propstrength__tax_percentage__c) {
		this.propstrength__tax_percentage__c = propstrength__tax_percentage__c;
	}
	public double getPropstrength__new_tax_percentage__c() {
		return propstrength__new_tax_percentage__c;
	}
	public void setPropstrength__new_tax_percentage__c(double propstrength__new_tax_percentage__c) {
		this.propstrength__new_tax_percentage__c = propstrength__new_tax_percentage__c;
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
}