package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.text.StyledEditorKit.BoldAction;

@Entity
@Table(name="salesforce.vw_payment_plan_and_other_charges_test" )
public class PaymentPlanWithOtherCharge {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="row_no") private int  row_number;
	@Column(name="id") private int  id;
	@Column(name="propstrength__amount_percent__c") private double propstrength__amount_percent__c;
	@Column(name="propstrength__type__c") private String propstrength__type__c;
	@Column(name="condition_type__c") private String condition_type__c;
	@Column(name="propstrength__amount__c") private double propstrength__amount__c;
	@Column(name="propstrength__payment_plan__c") private String propstrength__payment_plan__c;
	@Column(name="sfid") private String sfid;
	@Column(name="propstrength__payment_plan_line_item_name__c") private String propstrength__payment_plan_line_item_name__c;
	
	@Column(name="other_charge_percent") private double other_charge_percent;
	@Column(name="propstrength__payment_plan_line_item__c") private String propstrength__payment_plan_line_item__c;
	@Column(name="sfid_pp_other_charges") private String sfid_pp_other_charges;
	@Column(name="propstrength__other_charges__c") private String propstrength__other_charges__c;
	
	@Column(name="propstrength__fixed_charge__c") private double propstrength__fixed_charge__c;
	@Column(name="other_charge_type") private String other_charge_type;
	@Column(name="propstrength__rate_per_unit_area__c") private double propstrength__rate_per_unit_area__c;
	@Column(name="propstrength__property__c") private String propstrength__property__c;
	
	
	@Column(name="propstrength__pmay_abatement__c") private boolean propstrength__pmay_abatement__c;
	@Column(name="tower_sfid") private String tower_sfid;
	@Column(name="propstrength__gst_status__c") private String propstrength__gst_status__c;
	@Column(name="propstrength__pmay_gst__c") private double propstrength__pmay_gst__c;
	@Column(name="propstrength__new_pmay_gst__c") private double propstrength__new_pmay_gst__c;
	@Column(name="propstrength__tax_percentage__c") private double propstrength__tax_percentage__c;
	@Column(name="propstrength__new_tax_percentage__c") private double propstrength__new_tax_percentage__c;
	
	@Column(name="other_charges_name") private String other_charges_name;
	
	@Column(name="propstrength__st_on_completion_certificate__c") private boolean propstrength__st_on_completion_certificate__c;
	
	@Column(name="propstrength__sta_applicable__c") private boolean propstrength__sta_applicable__c;
	
	@Column(name="propstrength__completion_certificate_received__c") private boolean propstrength__completion_certificate_received__c;
	
	@Column(name="paymentplan_name") private String paymentplan_name;
	
	
	
	 @Column(name="propstrength__part_of_cop__c") private boolean propstrength__part_of_cop__c;
	
	
	@Column(name="sum") private double sum;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPropstrength__amount_percent__c() {
		return propstrength__amount_percent__c;
	}
	public void setPropstrength__amount_percent__c(double propstrength__amount_percent__c) {
		this.propstrength__amount_percent__c = propstrength__amount_percent__c;
	}
	public String getPropstrength__type__c() {
		return propstrength__type__c;
	}
	public void setPropstrength__type__c(String propstrength__type__c) {
		this.propstrength__type__c = propstrength__type__c;
	}
	public String getCondition_type__c() {
		return condition_type__c;
	}
	public void setCondition_type__c(String condition_type__c) {
		this.condition_type__c = condition_type__c;
	}
	public double getPropstrength__amount__c() {
		return propstrength__amount__c;
	}
	public void setPropstrength__amount__c(double propstrength__amount__c) {
		this.propstrength__amount__c = propstrength__amount__c;
	}
	public String getPropstrength__payment_plan__c() {
		return propstrength__payment_plan__c;
	}
	public void setPropstrength__payment_plan__c(String propstrength__payment_plan__c) {
		this.propstrength__payment_plan__c = propstrength__payment_plan__c;
	}
	public String getSfid() {
		return sfid;
	}
	public void setSfid(String sfid) {
		this.sfid = sfid;
	}
	public String getPropstrength__payment_plan_line_item_name__c() {
		return propstrength__payment_plan_line_item_name__c;
	}
	public void setPropstrength__payment_plan_line_item_name__c(String propstrength__payment_plan_line_item_name__c) {
		this.propstrength__payment_plan_line_item_name__c = propstrength__payment_plan_line_item_name__c;
	}
	public double getOther_charge_percent() {
		return other_charge_percent;
	}
	public void setOther_charge_percent(double other_charge_percent) {
		this.other_charge_percent = other_charge_percent;
	}
	public String getPropstrength__payment_plan_line_item__c() {
		return propstrength__payment_plan_line_item__c;
	}
	public void setPropstrength__payment_plan_line_item__c(String propstrength__payment_plan_line_item__c) {
		this.propstrength__payment_plan_line_item__c = propstrength__payment_plan_line_item__c;
	}
	public String getSfid_pp_other_charges() {
		return sfid_pp_other_charges;
	}
	public void setSfid_pp_other_charges(String sfid_pp_other_charges) {
		this.sfid_pp_other_charges = sfid_pp_other_charges;
	}
	public String getPropstrength__other_charges__c() {
		return propstrength__other_charges__c;
	}
	public void setPropstrength__other_charges__c(String propstrength__other_charges__c) {
		this.propstrength__other_charges__c = propstrength__other_charges__c;
	}
	public double getPropstrength__fixed_charge__c() {
		return propstrength__fixed_charge__c;
	}
	public void setPropstrength__fixed_charge__c(double propstrength__fixed_charge__c) {
		this.propstrength__fixed_charge__c = propstrength__fixed_charge__c;
	}
	public String getOther_charge_type() {
		return other_charge_type;
	}
	public void setOther_charge_type(String other_charge_type) {
		this.other_charge_type = other_charge_type;
	}
	public double getPropstrength__rate_per_unit_area__c() {
		return propstrength__rate_per_unit_area__c;
	}
	public void setPropstrength__rate_per_unit_area__c(double propstrength__rate_per_unit_area__c) {
		this.propstrength__rate_per_unit_area__c = propstrength__rate_per_unit_area__c;
	}
	public String getPropstrength__property__c() {
		return propstrength__property__c;
	}
	public void setPropstrength__property__c(String propstrength__property__c) {
		this.propstrength__property__c = propstrength__property__c;
	}

	/*
	 * public boolean isPropstrength__part_of_cop__c() { return
	 * propstrength__part_of_cop__c; } public void
	 * setPropstrength__part_of_cop__c(boolean propstrength__part_of_cop__c) {
	 * this.propstrength__part_of_cop__c = propstrength__part_of_cop__c; }
	 */
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public int getRow_number() {
		return row_number;
	}
	public void setRow_number(int row_number) {
		this.row_number = row_number;
	}
	public boolean isPropstrength__pmay_abatement__c() {
		return propstrength__pmay_abatement__c;
	}
	public void setPropstrength__pmay_abatement__c(boolean propstrength__pmay_abatement__c) {
		this.propstrength__pmay_abatement__c = propstrength__pmay_abatement__c;
	}
	public String getTower_sfid() {
		return tower_sfid;
	}
	public void setTower_sfid(String tower_sfid) {
		this.tower_sfid = tower_sfid;
	}
	public String getPropstrength__gst_status__c() {
		return propstrength__gst_status__c;
	}
	public void setPropstrength__gst_status__c(String propstrength__gst_status__c) {
		this.propstrength__gst_status__c = propstrength__gst_status__c;
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
	public String getOther_charges_name() {
		return other_charges_name;
	}
	public void setOther_charges_name(String other_charges_name) {
		this.other_charges_name = other_charges_name;
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
	public String getPaymentplan_name() {
		return paymentplan_name;
	}
	public void setPaymentplan_name(String paymentplan_name) {
		this.paymentplan_name = paymentplan_name;
	}
	public boolean isPropstrength__part_of_cop__c() {
		return propstrength__part_of_cop__c;
	}
	public void setPropstrength__part_of_cop__c(boolean propstrength__part_of_cop__c) {
		this.propstrength__part_of_cop__c = propstrength__part_of_cop__c;
	}
}