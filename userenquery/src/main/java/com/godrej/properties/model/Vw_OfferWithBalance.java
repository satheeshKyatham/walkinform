package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.vw_offer_with_balance_details") 
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
}