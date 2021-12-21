package com.godrej.properties.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "salesforce.vw_project_inventory_uat") 
public class CostsheetReport implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="row_no") private int  row_number;
	@Column(name="createddate") private Timestamp createddate;
	@Column(name="source") private String source;
	@Column(name="costsheet_type") private String costsheet_type;
	@Column(name="scheme_type") private String scheme_type;
	@Column(name="scheme_name") private String scheme_name;
	@Column(name="scheme_rate", precision = 10, scale = 2) private BigDecimal scheme_rate;
	@Column(name="scheme_absolute", precision = 10, scale = 2) private BigDecimal scheme_absolute;
	@Column(name="scheme_percentage", precision = 10, scale = 2) private BigDecimal scheme_percentage;
	@Column(name="scheme_zero_govt_charges") private Boolean scheme_zero_govt_charges;
	@Column(name="carpark_type") private String carpark_type;
	@Column(name="carpark_count" , precision = 10, scale = 2) private BigDecimal carpark_count;
	//---------
	@Column(name="discounted_bsp" , precision = 10, scale = 2) private BigDecimal discounted_bsp;
	@Column(name="og_bsp" , precision = 10, scale = 2) private BigDecimal og_bsp;
	@Column(name="carpet_area_sqft" , precision = 10, scale = 2) private BigDecimal carpet_area_sqft;
	@Column(name="saleable_area_sqft" , precision = 10, scale = 2) private BigDecimal saleable_area_sqft;
	@Column(name="property_name") private String property_name;
	@Column(name="carpet_area_rera_sqmt" , precision = 10, scale = 2) private BigDecimal carpet_area_rera_sqmt;
	@Column(name="exclusive_area_sqmt" , precision = 10, scale = 2) private BigDecimal exclusive_area_sqmt;
	@Column(name="total_area_sqmt" , precision = 10, scale = 2) private BigDecimal total_area_sqmt;
	@Column(name="carpet_area_amount" , precision = 10, scale = 2) private BigDecimal carpet_area_amount;
	@Column(name="exclusive_area_amount" , precision = 10, scale = 2) private BigDecimal exclusive_area_amount; 
	//---------
	@Column(name="flat_unit_cost" , precision = 10, scale = 2) private BigDecimal flat_unit_cost;
	@Column(name="total_a" , precision = 10, scale = 2) private BigDecimal total_a;
	@Column(name="total_b" , precision = 10, scale = 2) private BigDecimal total_b;
	@Column(name="stemp_duty_amount" , precision = 10, scale = 2) private BigDecimal stemp_duty_amount;
	@Column(name="registration_amount" , precision = 10, scale = 2) private BigDecimal registration_amount;
	@Column(name="gst_amount" , precision = 10, scale = 2) private BigDecimal gst_amount;
	@Column(name="total_c" , precision = 10, scale = 2) private BigDecimal total_c;
	@Column(name="total_abc" , precision = 10, scale = 2) private BigDecimal total_abc;
	@Column(name="total_discount" , precision = 10, scale = 2) private BigDecimal total_discount;
	@Column(name="paymentplan_total" , precision = 10, scale = 2) private BigDecimal paymentplan_total;
	@Column(name="cs_sales_comments") private String cs_sales_comments;
	//----------
	@Column(name="type") private String type;
	@Column(name="queue") private Integer queue;
	@Column(name="mobile__c") private String mobile__c;
	@Column(name="customer_name") private String customer_name;
	@Column(name="enq_name") private String enq_name;
	@Column(name="project_name") private String project_name;
	@Column(name="propstrength__tower_name__c") private String propstrength__tower_name__c;
	@Column(name="propstrength__property_name__c") private String propstrength__property_name__c;
	@Column(name="paymentplan_name") private String paymentplan_name;
	@Column(name="user_name") private String user_name;
	@Column(name="emailid") private String emailid;
	 
	@Transient private String qry_count;
	@Transient private String qry_msg;
	
	@Column(name="parking_selection") private String parking_selection;
	@Column(name="parking_name") private String parking_name;
	@Column(name="parking_sfid") private String parking_sfid; 
	@Column(name="parking_amount" , precision = 10, scale = 2) private BigDecimal parking_amount; 
	
	public int getRow_number() {
		return row_number;
	}
	public void setRow_number(int row_number) {
		this.row_number = row_number;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCostsheet_type() {
		return costsheet_type;
	}
	public void setCostsheet_type(String costsheet_type) {
		this.costsheet_type = costsheet_type;
	}
	public String getScheme_type() {
		return scheme_type;
	}
	public void setScheme_type(String scheme_type) {
		this.scheme_type = scheme_type;
	}
	public String getScheme_name() {
		return scheme_name;
	}
	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}
	public BigDecimal getScheme_rate() {
		return scheme_rate;
	}
	public void setScheme_rate(BigDecimal scheme_rate) {
		this.scheme_rate = scheme_rate;
	}
	public BigDecimal getScheme_absolute() {
		return scheme_absolute;
	}
	public void setScheme_absolute(BigDecimal scheme_absolute) {
		this.scheme_absolute = scheme_absolute;
	}
	public BigDecimal getScheme_percentage() {
		return scheme_percentage;
	}
	public void setScheme_percentage(BigDecimal scheme_percentage) {
		this.scheme_percentage = scheme_percentage;
	}
	public Boolean getScheme_zero_govt_charges() {
		return scheme_zero_govt_charges;
	}
	public void setScheme_zero_govt_charges(Boolean scheme_zero_govt_charges) {
		this.scheme_zero_govt_charges = scheme_zero_govt_charges;
	}
	public String getCarpark_type() {
		return carpark_type;
	}
	public void setCarpark_type(String carpark_type) {
		this.carpark_type = carpark_type;
	}
	public BigDecimal getCarpark_count() {
		return carpark_count;
	}
	public void setCarpark_count(BigDecimal carpark_count) {
		this.carpark_count = carpark_count;
	}
	public BigDecimal getDiscounted_bsp() {
		return discounted_bsp;
	}
	public void setDiscounted_bsp(BigDecimal discounted_bsp) {
		this.discounted_bsp = discounted_bsp;
	}
	public BigDecimal getOg_bsp() {
		return og_bsp;
	}
	public void setOg_bsp(BigDecimal og_bsp) {
		this.og_bsp = og_bsp;
	}
	public BigDecimal getCarpet_area_sqft() {
		return carpet_area_sqft;
	}
	public void setCarpet_area_sqft(BigDecimal carpet_area_sqft) {
		this.carpet_area_sqft = carpet_area_sqft;
	}
	public BigDecimal getSaleable_area_sqft() {
		return saleable_area_sqft;
	}
	public void setSaleable_area_sqft(BigDecimal saleable_area_sqft) {
		this.saleable_area_sqft = saleable_area_sqft;
	}
	public String getProperty_name() {
		return property_name;
	}
	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}
	public BigDecimal getCarpet_area_rera_sqmt() {
		return carpet_area_rera_sqmt;
	}
	public void setCarpet_area_rera_sqmt(BigDecimal carpet_area_rera_sqmt) {
		this.carpet_area_rera_sqmt = carpet_area_rera_sqmt;
	}
	public BigDecimal getExclusive_area_sqmt() {
		return exclusive_area_sqmt;
	}
	public void setExclusive_area_sqmt(BigDecimal exclusive_area_sqmt) {
		this.exclusive_area_sqmt = exclusive_area_sqmt;
	}
	public BigDecimal getTotal_area_sqmt() {
		return total_area_sqmt;
	}
	public void setTotal_area_sqmt(BigDecimal total_area_sqmt) {
		this.total_area_sqmt = total_area_sqmt;
	}
	public BigDecimal getCarpet_area_amount() {
		return carpet_area_amount;
	}
	public void setCarpet_area_amount(BigDecimal carpet_area_amount) {
		this.carpet_area_amount = carpet_area_amount;
	}
	public BigDecimal getExclusive_area_amount() {
		return exclusive_area_amount;
	}
	public void setExclusive_area_amount(BigDecimal exclusive_area_amount) {
		this.exclusive_area_amount = exclusive_area_amount;
	}
	public BigDecimal getFlat_unit_cost() {
		return flat_unit_cost;
	}
	public void setFlat_unit_cost(BigDecimal flat_unit_cost) {
		this.flat_unit_cost = flat_unit_cost;
	}
	public BigDecimal getTotal_a() {
		return total_a;
	}
	public void setTotal_a(BigDecimal total_a) {
		this.total_a = total_a;
	}
	public BigDecimal getTotal_b() {
		return total_b;
	}
	public void setTotal_b(BigDecimal total_b) {
		this.total_b = total_b;
	}
	public BigDecimal getStemp_duty_amount() {
		return stemp_duty_amount;
	}
	public void setStemp_duty_amount(BigDecimal stemp_duty_amount) {
		this.stemp_duty_amount = stemp_duty_amount;
	}
	public BigDecimal getRegistration_amount() {
		return registration_amount;
	}
	public void setRegistration_amount(BigDecimal registration_amount) {
		this.registration_amount = registration_amount;
	}
	public BigDecimal getGst_amount() {
		return gst_amount;
	}
	public void setGst_amount(BigDecimal gst_amount) {
		this.gst_amount = gst_amount;
	}
	public BigDecimal getTotal_c() {
		return total_c;
	}
	public void setTotal_c(BigDecimal total_c) {
		this.total_c = total_c;
	}
	public BigDecimal getTotal_abc() {
		return total_abc;
	}
	public void setTotal_abc(BigDecimal total_abc) {
		this.total_abc = total_abc;
	}
	public BigDecimal getTotal_discount() {
		return total_discount;
	}
	public void setTotal_discount(BigDecimal total_discount) {
		this.total_discount = total_discount;
	}
	public BigDecimal getPaymentplan_total() {
		return paymentplan_total;
	}
	public void setPaymentplan_total(BigDecimal paymentplan_total) {
		this.paymentplan_total = paymentplan_total;
	}
	public String getCs_sales_comments() {
		return cs_sales_comments;
	}
	public void setCs_sales_comments(String cs_sales_comments) {
		this.cs_sales_comments = cs_sales_comments;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getQueue() {
		return queue;
	}
	public void setQueue(Integer queue) {
		this.queue = queue;
	}
	public String getMobile__c() {
		return mobile__c;
	}
	public void setMobile__c(String mobile__c) {
		this.mobile__c = mobile__c;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getEnq_name() {
		return enq_name;
	}
	public void setEnq_name(String enq_name) {
		this.enq_name = enq_name;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getPropstrength__tower_name__c() {
		return propstrength__tower_name__c;
	}
	public void setPropstrength__tower_name__c(String propstrength__tower_name__c) {
		this.propstrength__tower_name__c = propstrength__tower_name__c;
	}
	public String getPropstrength__property_name__c() {
		return propstrength__property_name__c;
	}
	public void setPropstrength__property_name__c(String propstrength__property_name__c) {
		this.propstrength__property_name__c = propstrength__property_name__c;
	}
	public String getPaymentplan_name() {
		return paymentplan_name;
	}
	public void setPaymentplan_name(String paymentplan_name) {
		this.paymentplan_name = paymentplan_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
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
	public String getParking_selection() {
		return parking_selection;
	}
	public void setParking_selection(String parking_selection) {
		this.parking_selection = parking_selection;
	}
	public String getParking_name() {
		return parking_name;
	}
	public void setParking_name(String parking_name) {
		this.parking_name = parking_name;
	}
	public String getParking_sfid() {
		return parking_sfid;
	}
	public void setParking_sfid(String parking_sfid) {
		this.parking_sfid = parking_sfid;
	}
	public BigDecimal getParking_amount() {
		return parking_amount;
	}
	public void setParking_amount(BigDecimal parking_amount) {
		this.parking_amount = parking_amount;
	}
}