package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.vw_payment_plan_and_other_charges_test" )
public class WithoutOtherChargesPP {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id") private int  id;
	@Column(name="propstrength__payment_plan_line_item_name__c") private String propstrength__payment_plan_line_item_name__c;
	@Column(name="propstrength__amount__c") private double propstrength__amount__c;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPropstrength__payment_plan_line_item_name__c() {
		return propstrength__payment_plan_line_item_name__c;
	}
	public void setPropstrength__payment_plan_line_item_name__c(String propstrength__payment_plan_line_item_name__c) {
		this.propstrength__payment_plan_line_item_name__c = propstrength__payment_plan_line_item_name__c;
	}
	public double getPropstrength__amount__c() {
		return propstrength__amount__c;
	}
	public void setPropstrength__amount__c(double propstrength__amount__c) {
		this.propstrength__amount__c = propstrength__amount__c;
	}
}