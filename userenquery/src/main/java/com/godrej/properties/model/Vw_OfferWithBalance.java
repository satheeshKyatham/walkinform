package com.godrej.properties.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "salesforce.vw_offer_with_balance_details_uat") 
public class Vw_OfferWithBalance implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="row_number") private  int row_number ;
	
	
	@Column(name="enquiryname") private String	enquiryname;
	@Column(name="enquiry_sfid") private  String enquiry_sfid ;
	@Column(name="contactname") private String	contactname;
	@Column(name="contact_sfid") private String	contact_sfid;
	@Column(name="payment_plan") private String	payment_plan;
	@Column(name="paymentplan_sfid") private String	paymentplan_sfid;
	@Column(name="scheme_name") private String	scheme_name;
	@Column(name="scheme_rate") private String	scheme_rate;
	@Column(name="amount") private String	amount;
	@Column(name="description") private String	description;
	@Column(name="car_park_type") private String	car_park_type;
	@Column(name="offer_sfid") private String	offer_sfid;
	@Column(name="projectname") private String	projectname;
	@Column(name="projectsfid") private String	projectsfid;
	@Column(name="userid") private String	userid;
	@Column(name="status") private String	status;
	@Column(name="booking_id") private String	booking_id;
	@Column(name="created") private String	created;
	@Column(name="offername") private String	offername;
	@Column(name="isactive") private String	isactive;
	@Column(name="costsheet_path") private String	costsheet_path;
	@Column(name="cs_final_amount") private double	cs_final_amount;
	@Column(name="propstrength__property__c") private String propstrength__property__c;
	@Column(name="createddate") private Date createddate;
	@Transient private String qry_count;
	@Transient private String qry_msg;
	@Column(name="verticle__c") private String	verticle__c;
	@Column(name="propstrength__property_name__c") private String propstrength__property_name__c;
	@Column(name="closing_manager_name__c") private String closing_manager_name__c;
	@Column(name="sourcing_manager_name__c") private String sourcing_manager_name__c;
	
	
	@Column(name="carpark_name") private String carpark_name;
	@Column(name="PropStrength__Car_Parking_Name__c") private String propStrength__Car_Parking_Name__c;
	@Column(name="PropStrength__Parking_Type__c") private String propStrength__Parking_Type__c;
	@Column(name="PropStrength__Category_of_Parking__c") private String propStrength__Category_of_Parking__c;
	@Column(name="Location_of_Parking__c") private String location_of_Parking__c;
	@Column(name="Parking_Area_Sq_Ft__c") private String parking_Area_Sq_Ft__c;
	@Column(name="Dimensions__c") private String dimensions__c;
	
	
	public int getRow_number() {
		return row_number;
	}
	public void setRow_number(int row_number) {
		this.row_number = row_number;
	}
	public String getEnquiryname() {
		return enquiryname;
	}
	public void setEnquiryname(String enquiryname) {
		this.enquiryname = enquiryname;
	}

	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public String getContact_sfid() {
		return contact_sfid;
	}
	public void setContact_sfid(String contact_sfid) {
		this.contact_sfid = contact_sfid;
	}
	public String getPayment_plan() {
		return payment_plan;
	}
	public void setPayment_plan(String payment_plan) {
		this.payment_plan = payment_plan;
	}
	public String getPaymentplan_sfid() {
		return paymentplan_sfid;
	}
	public void setPaymentplan_sfid(String paymentplan_sfid) {
		this.paymentplan_sfid = paymentplan_sfid;
	}
	public String getScheme_name() {
		return scheme_name;
	}
	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}
	public String getScheme_rate() {
		return scheme_rate;
	}
	public void setScheme_rate(String scheme_rate) {
		this.scheme_rate = scheme_rate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCar_park_type() {
		return car_park_type;
	}
	public void setCar_park_type(String car_park_type) {
		this.car_park_type = car_park_type;
	}
	public String getOffer_sfid() {
		return offer_sfid;
	}
	public void setOffer_sfid(String offer_sfid) {
		this.offer_sfid = offer_sfid;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getProjectsfid() {
		return projectsfid;
	}
	public void setProjectsfid(String projectsfid) {
		this.projectsfid = projectsfid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getOffername() {
		return offername;
	}
	public void setOffername(String offername) {
		this.offername = offername;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public String getEnquiry_sfid() {
		return enquiry_sfid;
	}
	public void setEnquiry_sfid(String enquiry_sfid) {
		this.enquiry_sfid = enquiry_sfid;
	}
	public String getCostsheet_path() {
		return costsheet_path;
	}
	public void setCostsheet_path(String costsheet_path) {
		this.costsheet_path = costsheet_path;
	}
	public double getCs_final_amount() {
		return cs_final_amount;
	}
	public void setCs_final_amount(double cs_final_amount) {
		this.cs_final_amount = cs_final_amount;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
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
	public String getVerticle__c() {
		return verticle__c;
	}
	public void setVerticle__c(String verticle__c) {
		this.verticle__c = verticle__c;
	}
	public String getPropstrength__property_name__c() {
		return propstrength__property_name__c;
	}
	public void setPropstrength__property_name__c(String propstrength__property_name__c) {
		this.propstrength__property_name__c = propstrength__property_name__c;
	}
	public String getClosing_manager_name__c() {
		return closing_manager_name__c;
	}
	public void setClosing_manager_name__c(String closing_manager_name__c) {
		this.closing_manager_name__c = closing_manager_name__c;
	}
	public String getSourcing_manager_name__c() {
		return sourcing_manager_name__c;
	}
	public void setSourcing_manager_name__c(String sourcing_manager_name__c) {
		this.sourcing_manager_name__c = sourcing_manager_name__c;
	}
	public String getPropstrength__property__c() {
		return propstrength__property__c;
	}
	public void setPropstrength__property__c(String propstrength__property__c) {
		this.propstrength__property__c = propstrength__property__c;
	}
	public String getCarpark_name() {
		return carpark_name;
	}
	public void setCarpark_name(String carpark_name) {
		this.carpark_name = carpark_name;
	}
	public String getPropStrength__Car_Parking_Name__c() {
		return propStrength__Car_Parking_Name__c;
	}
	public void setPropStrength__Car_Parking_Name__c(String propStrength__Car_Parking_Name__c) {
		this.propStrength__Car_Parking_Name__c = propStrength__Car_Parking_Name__c;
	}
	public String getPropStrength__Parking_Type__c() {
		return propStrength__Parking_Type__c;
	}
	public void setPropStrength__Parking_Type__c(String propStrength__Parking_Type__c) {
		this.propStrength__Parking_Type__c = propStrength__Parking_Type__c;
	}
	public String getPropStrength__Category_of_Parking__c() {
		return propStrength__Category_of_Parking__c;
	}
	public void setPropStrength__Category_of_Parking__c(String propStrength__Category_of_Parking__c) {
		this.propStrength__Category_of_Parking__c = propStrength__Category_of_Parking__c;
	}
	public String getLocation_of_Parking__c() {
		return location_of_Parking__c;
	}
	public void setLocation_of_Parking__c(String location_of_Parking__c) {
		this.location_of_Parking__c = location_of_Parking__c;
	}
	public String getParking_Area_Sq_Ft__c() {
		return parking_Area_Sq_Ft__c;
	}
	public void setParking_Area_Sq_Ft__c(String parking_Area_Sq_Ft__c) {
		this.parking_Area_Sq_Ft__c = parking_Area_Sq_Ft__c;
	}
	public String getDimensions__c() {
		return dimensions__c;
	}
	public void setDimensions__c(String dimensions__c) {
		this.dimensions__c = dimensions__c;
	}
	
}