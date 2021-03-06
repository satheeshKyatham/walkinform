package com.godrej.properties.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "salesforce.vw_allotment_report_uat") 
public class AllotmentReport {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="gpl_cs_balance_details_id") private  int id ;
	@Column(name="enqname") private String	enqname;
	@Column(name="custname") private String	custname;
	@Column(name="mobile__c") private String	mobile__c;
	@Column(name="email") private String email;
	@Column(name="verticle__c") private String	verticle__c;
	@Column(name="sourcing_manager_name__c") private String	sourcing_manager_name__c;
	
	
	@Column(name="closing_manager_name__c") private String	closing_manager_name__c;
	@Column(name="walk_in_source__c") private String	walk_in_source__c;
	@Column(name="propstrength__request_source__c") private String propstrength__request_source__c;
	@Column(name="propstrength__enquiry_type__c") private String	propstrength__enquiry_type__c;
	@Column(name="propstrength__broker_account__c") private String	propstrength__broker_account__c;
	@Column(name="brokername") private String	brokername;
	@Column(name="name") private String	offername;
	@Column(name="propstrength__super_area__c") private Double propstrength__super_area__c;
	@Column(name="propstrength__carpet_area__c") private Double	propstrength__carpet_area__c;
	@Column(name="propstrength__unit_type__c") private String	propstrength__unit_type__c;
	@Column(name="propstrength__total_sales_consideration__c") private String	propstrength__total_sales_consideration__c;
	@Column(name="propstrength__total_basic_sale_price__c") private String	propstrength__total_basic_sale_price__c;
	@Column(name="userid") private Integer userid;
	@Column(name="user_name") private String	user_name;
	@Column(name="priority_no__c") private String	priority_no__c;
	@Column(name="offeramount") private String	offeramount;
	@Column(name="offer_date__c") private Date	offer_date__c;
	@Column(name="project_sfid") private String	project_sfid;
	
	@Column(name="PropStrength__Property_Name__c") private String	propStrength__Property_Name__c;
	@Column(name="PropStrength__House_Unit_No__c") private String	propStrength__House_Unit_No__c;
	@Column(name="actula_5_per") private Double	actula_5_per;
	@Column(name="diffamount") private String diffamount;
	@Column(name="car_park_type") private String car_park_type;
	@Column(name="scheme_name") private String scheme_name;
	
	@Column(name="offer_date") private Date offer_date;
	@Column(name="booking_name") private String booking_name;
	@Column(name="booking_status") private String booking_status;
	@Column(name="kyc_approvedby") private String kyc_approvedby;
	@Column(name="Sourcing_Team_Lead_Name__c") private String Sourcing_Team_Lead_Name__c;
	@Column(name="Closing_Team_Lead_Name__c") private String Closing_Team_Lead_Name__c;
	
	
	@Transient private String qry_count;
	@Transient private String qry_msg;
	@Column(name="project_name__c") private String project_name__c;
	@Column(name="propstrength__total_agreement_amount__c") private String propstrength__total_agreement_amount__c;
	
	@Column(name="carpark_name") private String carpark_name;
	@Column(name="PropStrength__Car_Parking_Name__c") private String propStrength__Car_Parking_Name__c;
	@Column(name="PropStrength__Parking_Type__c") private String propStrength__Parking_Type__c;
	@Column(name="PropStrength__Category_of_Parking__c") private String propStrength__Category_of_Parking__c;
	@Column(name="Location_of_Parking__c") private String location_of_Parking__c;
	@Column(name="Parking_Area_Sq_Ft__c") private String parking_Area_Sq_Ft__c;
	@Column(name="Dimensions__c") private String dimensions__c;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEnqname() {
		return enqname;
	}
	public void setEnqname(String enqname) {
		this.enqname = enqname;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getMobile__c() {
		return mobile__c;
	}
	public void setMobile__c(String mobile__c) {
		this.mobile__c = mobile__c;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVerticle__c() {
		return verticle__c;
	}
	public void setVerticle__c(String verticle__c) {
		this.verticle__c = verticle__c;
	}
	public String getSourcing_manager_name__c() {
		return sourcing_manager_name__c;
	}
	public void setSourcing_manager_name__c(String sourcing_manager_name__c) {
		this.sourcing_manager_name__c = sourcing_manager_name__c;
	}
	public String getClosing_manager_name__c() {
		return closing_manager_name__c;
	}
	public void setClosing_manager_name__c(String closing_manager_name__c) {
		this.closing_manager_name__c = closing_manager_name__c;
	}
	public String getWalk_in_source__c() {
		return walk_in_source__c;
	}
	public void setWalk_in_source__c(String walk_in_source__c) {
		this.walk_in_source__c = walk_in_source__c;
	}
	public String getPropstrength__request_source__c() {
		return propstrength__request_source__c;
	}
	public void setPropstrength__request_source__c(String propstrength__request_source__c) {
		this.propstrength__request_source__c = propstrength__request_source__c;
	}
	public String getPropstrength__enquiry_type__c() {
		return propstrength__enquiry_type__c;
	}
	public void setPropstrength__enquiry_type__c(String propstrength__enquiry_type__c) {
		this.propstrength__enquiry_type__c = propstrength__enquiry_type__c;
	}
	public String getPropstrength__broker_account__c() {
		return propstrength__broker_account__c;
	}
	public void setPropstrength__broker_account__c(String propstrength__broker_account__c) {
		this.propstrength__broker_account__c = propstrength__broker_account__c;
	}
	public String getBrokername() {
		return brokername;
	}
	public void setBrokername(String brokername) {
		this.brokername = brokername;
	}
	public String getOffername() {
		return offername;
	}
	public void setOffername(String offername) {
		this.offername = offername;
	}
	public Double getPropstrength__super_area__c() {
		return propstrength__super_area__c;
	}
	public void setPropstrength__super_area__c(Double propstrength__super_area__c) {
		this.propstrength__super_area__c = propstrength__super_area__c;
	}
	public Double getPropstrength__carpet_area__c() {
		return propstrength__carpet_area__c;
	}
	public void setPropstrength__carpet_area__c(Double propstrength__carpet_area__c) {
		this.propstrength__carpet_area__c = propstrength__carpet_area__c;
	}
	public String getPropstrength__unit_type__c() {
		return propstrength__unit_type__c;
	}
	public void setPropstrength__unit_type__c(String propstrength__unit_type__c) {
		this.propstrength__unit_type__c = propstrength__unit_type__c;
	}
	public String getPropstrength__total_sales_consideration__c() {
		return propstrength__total_sales_consideration__c;
	}
	public void setPropstrength__total_sales_consideration__c(String propstrength__total_sales_consideration__c) {
		this.propstrength__total_sales_consideration__c = propstrength__total_sales_consideration__c;
	}
	public String getPropstrength__total_basic_sale_price__c() {
		return propstrength__total_basic_sale_price__c;
	}
	public void setPropstrength__total_basic_sale_price__c(String propstrength__total_basic_sale_price__c) {
		this.propstrength__total_basic_sale_price__c = propstrength__total_basic_sale_price__c;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPriority_no__c() {
		return priority_no__c;
	}
	public void setPriority_no__c(String priority_no__c) {
		this.priority_no__c = priority_no__c;
	}
	public String getOfferamount() {
		return offeramount;
	}
	public void setOfferamount(String offeramount) {
		this.offeramount = offeramount;
	}
	public Date getOffer_date__c() {
		return offer_date__c;
	}
	public void setOffer_date__c(Date offer_date__c) {
		this.offer_date__c = offer_date__c;
	}
	public String getProject_sfid() {
		return project_sfid;
	}
	public void setProject_sfid(String project_sfid) {
		this.project_sfid = project_sfid;
	}
	public String getPropStrength__Property_Name__c() {
		return propStrength__Property_Name__c;
	}
	public void setPropStrength__Property_Name__c(String propStrength__Property_Name__c) {
		this.propStrength__Property_Name__c = propStrength__Property_Name__c;
	}
	public String getPropStrength__House_Unit_No__c() {
		return propStrength__House_Unit_No__c;
	}
	public void setPropStrength__House_Unit_No__c(String propStrength__House_Unit_No__c) {
		this.propStrength__House_Unit_No__c = propStrength__House_Unit_No__c;
	}
	public String getDiffamount() {
		return diffamount;
	}
	public void setDiffamount(String diffamount) {
		this.diffamount = diffamount;
	}
	public Double getActula_5_per() {
		return actula_5_per;
	}
	public void setActula_5_per(Double actula_5_per) {
		this.actula_5_per = actula_5_per;
	}
	public String getCar_park_type() {
		return car_park_type;
	}
	public void setCar_park_type(String car_park_type) {
		this.car_park_type = car_park_type;
	}
	public String getScheme_name() {
		return scheme_name;
	}
	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}
	public Date getOffer_date() {
		return offer_date;
	}
	public void setOffer_date(Date offer_date) {
		this.offer_date = offer_date;
	}
	public String getBooking_name() {
		return booking_name;
	}
	public void setBooking_name(String booking_name) {
		this.booking_name = booking_name;
	}
	public String getBooking_status() {
		return booking_status;
	}
	public void setBooking_status(String booking_status) {
		this.booking_status = booking_status;
	}
	public String getKyc_approvedby() {
		return kyc_approvedby;
	}
	public void setKyc_approvedby(String kyc_approvedby) {
		this.kyc_approvedby = kyc_approvedby;
	}
	public String getSourcing_Team_Lead_Name__c() {
		return Sourcing_Team_Lead_Name__c;
	}
	public void setSourcing_Team_Lead_Name__c(String sourcing_Team_Lead_Name__c) {
		Sourcing_Team_Lead_Name__c = sourcing_Team_Lead_Name__c;
	}
	public String getClosing_Team_Lead_Name__c() {
		return Closing_Team_Lead_Name__c;
	}
	public void setClosing_Team_Lead_Name__c(String closing_Team_Lead_Name__c) {
		Closing_Team_Lead_Name__c = closing_Team_Lead_Name__c;
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
	public String getProject_name__c() {
		return project_name__c;
	}
	public void setProject_name__c(String project_name__c) {
		this.project_name__c = project_name__c;
	}
	public String getPropstrength__total_agreement_amount__c() {
		return propstrength__total_agreement_amount__c;
	}
	public void setPropstrength__total_agreement_amount__c(String propstrength__total_agreement_amount__c) {
		this.propstrength__total_agreement_amount__c = propstrength__total_agreement_amount__c;
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