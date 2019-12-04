package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.vw_orderdatamapping")
public class OrderDataMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "row_number")
	private int row_number;
	@Column(name = "name")
	private String name;
	@Column(name = "propstrength__status__c")
	private String propstrength__status__c;
	@Column(name = "propstrength__booking_cancellation_reason__c")
	private String propstrength__booking_cancellation_reason__c;
	@Column(name = "sales_organisation__c")
	private String sales_organisation__c;
	@Column(name = "sales_district__c")
	private String sales_district__c;
	@Column(name = "distribution_channel__c")
	private String distribution_channel__c;
	@Column(name = "tower_code__c")
	private String tower_code__c;
	@Column(name = "old_tower_code__c")
	private String old_tower_code__c;
	@Column(name = "profit_center__c")
	private String profit_center__c;
	@Column(name = "propstrength__property_name__c")
	private String propstrength__property_name__c;
	@Column(name = "old_property_name__c")
	private String old_property_name__c;
	@Column(name = "propstrength__booking_date__c")
	private String propstrength__booking_date__c;
	@Column(name = "propstrength__booking_cancelled_date__c")
	private String propstrength__booking_cancelled_date__c;
	@Column(name = "sap_customer_code__c")
	private String sap_customer_code__c;
	@Column(name = "propstrength__super_area__c")
	private String propstrength__super_area__c;
	@Column(name = "propstrength__rate_per_unit_area__c")
	private String propstrength__rate_per_unit_area__c;
	@Column(name = "propstrength__total_basic_sales_price__c")
	private String propstrength__total_basic_sales_price__c;
	@Column(name = "bsp_value")
	private String bsp_value;
	@Column(name = "legacy_data_no_sap_posting__c")
	private String legacy_data_no_sap_posting__c;
	@Column(name = "application_booking_payment_plan_code__c")
	private String application_booking_payment_plan_code__c;
	@Column(name = "propstrength__outstanding_balance_excluding_waiver__c")
	private String propstrength__outstanding_balance_excluding_waiver__c;
	@Column(name = "saleable_area__c")
	private String saleable_area__c;
	@Column(name = "propstrength__revised_agreement_amount__c")
	private String propstrength__revised_agreement_amount__c;
	@Column(name = "propstrength__total_input_tax_credit__c")
	private String propstrength__total_input_tax_credit__c;
	@Column(name = "enquiry18digit__c")
	private String enquiry18digit__c;
	@Column(name = "propstrength__broker_name__c")
	private String propstrength__broker_name__c;
	@Column(name = "proejct_id")
	private String proejct_id;
	@Column(name = "project_name__c")
	private String project_name__c;
	@Column(name = "customer_status__c")
	private String customer_status__c;
	@Column(name = "lastmodifieddate")
	private Timestamp lastmodifieddate;
	
	@Column(name = "propstrength__total_agreement_amount__c")
	private String propstrength__total_agreement_amount__c;
	
	@Column(name = "broker_category__c")
	private String broker_category__c;
	
	
	@Column(name = "old_Aggrement_value")
	private String old_Aggrement_value;
	
	@Column(name = "parentid")
	private String parentid;
 	
	@Column(name = "aggrement_changed_date")
	private Timestamp aggrement_changed_date;
 
	
	
	
	
	public String getOld_Aggrement_value() {
		return old_Aggrement_value;
	}
	public void setOld_Aggrement_value(String old_Aggrement_value) {
		this.old_Aggrement_value = old_Aggrement_value;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public Timestamp getAggrement_changed_date() {
		return aggrement_changed_date;
	}
	public void setAggrement_changed_date(Timestamp aggrement_changed_date) {
		this.aggrement_changed_date = aggrement_changed_date;
	}
	public String getPropstrength__total_agreement_amount__c() {
		return propstrength__total_agreement_amount__c;
	}
	public void setPropstrength__total_agreement_amount__c(String propstrength__total_agreement_amount__c) {
		this.propstrength__total_agreement_amount__c = propstrength__total_agreement_amount__c;
	}
	public String getBroker_category__c() {
		return broker_category__c;
	}
	public void setBroker_category__c(String broker_category__c) {
		this.broker_category__c = broker_category__c;
	}
	public int getRow_number() {
		return row_number;
	}
	public void setRow_number(int row_number) {
		this.row_number = row_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPropstrength__status__c() {
		return propstrength__status__c;
	}
	public void setPropstrength__status__c(String propstrength__status__c) {
		this.propstrength__status__c = propstrength__status__c;
	}
	public String getPropstrength__booking_cancellation_reason__c() {
		return propstrength__booking_cancellation_reason__c;
	}
	public void setPropstrength__booking_cancellation_reason__c(String propstrength__booking_cancellation_reason__c) {
		this.propstrength__booking_cancellation_reason__c = propstrength__booking_cancellation_reason__c;
	}
	public String getSales_organisation__c() {
		return sales_organisation__c;
	}
	public void setSales_organisation__c(String sales_organisation__c) {
		this.sales_organisation__c = sales_organisation__c;
	}
	public String getSales_district__c() {
		return sales_district__c;
	}
	public void setSales_district__c(String sales_district__c) {
		this.sales_district__c = sales_district__c;
	}
	public String getDistribution_channel__c() {
		return distribution_channel__c;
	}
	public void setDistribution_channel__c(String distribution_channel__c) {
		this.distribution_channel__c = distribution_channel__c;
	}
	public String getTower_code__c() {
		return tower_code__c;
	}
	public void setTower_code__c(String tower_code__c) {
		this.tower_code__c = tower_code__c;
	}
	public String getOld_tower_code__c() {
		return old_tower_code__c;
	}
	public void setOld_tower_code__c(String old_tower_code__c) {
		this.old_tower_code__c = old_tower_code__c;
	}
	public String getProfit_center__c() {
		return profit_center__c;
	}
	public void setProfit_center__c(String profit_center__c) {
		this.profit_center__c = profit_center__c;
	}
	public String getPropstrength__property_name__c() {
		return propstrength__property_name__c;
	}
	public void setPropstrength__property_name__c(String propstrength__property_name__c) {
		this.propstrength__property_name__c = propstrength__property_name__c;
	}
	public String getOld_property_name__c() {
		return old_property_name__c;
	}
	public void setOld_property_name__c(String old_property_name__c) {
		this.old_property_name__c = old_property_name__c;
	}
	public String getPropstrength__booking_date__c() {
		return propstrength__booking_date__c;
	}
	public void setPropstrength__booking_date__c(String propstrength__booking_date__c) {
		this.propstrength__booking_date__c = propstrength__booking_date__c;
	}
	public String getPropstrength__booking_cancelled_date__c() {
		return propstrength__booking_cancelled_date__c;
	}
	public void setPropstrength__booking_cancelled_date__c(String propstrength__booking_cancelled_date__c) {
		this.propstrength__booking_cancelled_date__c = propstrength__booking_cancelled_date__c;
	}
	public String getSap_customer_code__c() {
		return sap_customer_code__c;
	}
	public void setSap_customer_code__c(String sap_customer_code__c) {
		this.sap_customer_code__c = sap_customer_code__c;
	}
	public String getPropstrength__super_area__c() {
		return propstrength__super_area__c;
	}
	public void setPropstrength__super_area__c(String propstrength__super_area__c) {
		this.propstrength__super_area__c = propstrength__super_area__c;
	}
	public String getPropstrength__rate_per_unit_area__c() {
		return propstrength__rate_per_unit_area__c;
	}
	public void setPropstrength__rate_per_unit_area__c(String propstrength__rate_per_unit_area__c) {
		this.propstrength__rate_per_unit_area__c = propstrength__rate_per_unit_area__c;
	}
	public String getPropstrength__total_basic_sales_price__c() {
		return propstrength__total_basic_sales_price__c;
	}
	public void setPropstrength__total_basic_sales_price__c(String propstrength__total_basic_sales_price__c) {
		this.propstrength__total_basic_sales_price__c = propstrength__total_basic_sales_price__c;
	}
	public String getBsp_value() {
		return bsp_value;
	}
	public void setBsp_value(String bsp_value) {
		this.bsp_value = bsp_value;
	}
	public String getLegacy_data_no_sap_posting__c() {
		return legacy_data_no_sap_posting__c;
	}
	public void setLegacy_data_no_sap_posting__c(String legacy_data_no_sap_posting__c) {
		this.legacy_data_no_sap_posting__c = legacy_data_no_sap_posting__c;
	}
	public String getApplication_booking_payment_plan_code__c() {
		return application_booking_payment_plan_code__c;
	}
	public void setApplication_booking_payment_plan_code__c(String application_booking_payment_plan_code__c) {
		this.application_booking_payment_plan_code__c = application_booking_payment_plan_code__c;
	}
	public String getPropstrength__outstanding_balance_excluding_waiver__c() {
		return propstrength__outstanding_balance_excluding_waiver__c;
	}
	public void setPropstrength__outstanding_balance_excluding_waiver__c(
			String propstrength__outstanding_balance_excluding_waiver__c) {
		this.propstrength__outstanding_balance_excluding_waiver__c = propstrength__outstanding_balance_excluding_waiver__c;
	}
	public String getSaleable_area__c() {
		return saleable_area__c;
	}
	public void setSaleable_area__c(String saleable_area__c) {
		this.saleable_area__c = saleable_area__c;
	}
	public String getPropstrength__revised_agreement_amount__c() {
		return propstrength__revised_agreement_amount__c;
	}
	public void setPropstrength__revised_agreement_amount__c(String propstrength__revised_agreement_amount__c) {
		this.propstrength__revised_agreement_amount__c = propstrength__revised_agreement_amount__c;
	}
	public String getPropstrength__total_input_tax_credit__c() {
		return propstrength__total_input_tax_credit__c;
	}
	public void setPropstrength__total_input_tax_credit__c(String propstrength__total_input_tax_credit__c) {
		this.propstrength__total_input_tax_credit__c = propstrength__total_input_tax_credit__c;
	}
	public String getEnquiry18digit__c() {
		return enquiry18digit__c;
	}
	public void setEnquiry18digit__c(String enquiry18digit__c) {
		this.enquiry18digit__c = enquiry18digit__c;
	}
	public String getPropstrength__broker_name__c() {
		return propstrength__broker_name__c;
	}
	public void setPropstrength__broker_name__c(String propstrength__broker_name__c) {
		this.propstrength__broker_name__c = propstrength__broker_name__c;
	}
	public String getProejct_id() {
		return proejct_id;
	}
	public void setProejct_id(String proejct_id) {
		this.proejct_id = proejct_id;
	}
	public String getProject_name__c() {
		return project_name__c;
	}
	public void setProject_name__c(String project_name__c) {
		this.project_name__c = project_name__c;
	}
	public String getCustomer_status__c() {
		return customer_status__c;
	}
	public void setCustomer_status__c(String customer_status__c) {
		this.customer_status__c = customer_status__c;
	}
	public Timestamp getLastmodifieddate() {
		return lastmodifieddate;
	}
	public void setLastmodifieddate(Timestamp lastmodifieddate) {
		this.lastmodifieddate = lastmodifieddate;
	}
	
	
	

}
