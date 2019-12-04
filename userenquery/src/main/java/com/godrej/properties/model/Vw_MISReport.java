package com.godrej.properties.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.vw_MISReport") 
public class Vw_MISReport {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="row_number") private  int row_number ;
	
	
	@Column(name="projectid") private String projectid;
	@Column(name="projectname") private String projectname;
	@Column(name="tokenno") private String tokenno;
	@Column(name="created") private Date created;
	@Column(name="enquiryname") private String enquiryname;
	@Column(name="mobilephone") private String mobilephone;
	@Column(name="name") private String name;
	@Column(name="email") private String email;
	@Column(name="have_we_met_before") private String have_we_met_before;
	@Column(name="age_a__c") private String age_a__c;
	@Column(name="residenceaddress") private String residenceaddress;
	@Column(name="officelocation") private String officelocation;
	@Column(name="empstatus") private String empstatus;
	@Column(name="company_name__c") private String company_name__c;
	@Column(name="is_purchase_for_self_use_or_investment__c") private String is_purchase_for_self_use_or_investment__c;
	@Column(name="budget") private String budget;
	@Column(name="carpet_area_requirement") private String carpet_area_requirement;
	@Column(name="typology_requirement") private String typology_requirement;
	@Column(name="walk_in_source__c") private String walk_in_source__c;
	@Column(name="advertisement__c") private String advertisement__c;
	@Column(name="id") private int id;
	@Column(name="advertisementname") private String advertisementname;
	@Column(name="propstrength__broker_account__c") private String propstrength__broker_account__c;
	@Column(name="brokername") private String brokername;
	@Column(name="current_residence_configuration") private String current_residence_configuration;
	@Column(name="current_residence_ownership") private String current_residence_ownership;
	@Column(name="source_of_funding") private String source_of_funding;
	@Column(name="customer_classification") private String customer_classification;
	@Column(name="ethnicity") private String ethnicity;
	@Column(name="unit_availability") private String unit_availability;
	@Column(name="accompanied_by") private String accompanied_by;
	@Column(name="deal_negotiation") private String deal_negotiation;
	@Column(name="construction_status") private String construction_status;
	@Column(name="timeframe_to_book") private String timeframe_to_book;
	@Column(name="enquirynoneditcomment") private String enquirynoneditcomment;
	@Column(name="sourcing_managers__c") private String sourcing_managers__c;
	@Column(name="sourcingname") private String sourcingname;
	@Column(name="sourcingemail") private String sourcingemail;
	@Column(name="closing_managers__c") private String closing_managers__c;
	@Column(name="closingname") private String closingname;
	@Column(name="closingemail") private String closingemail;
	@Column(name="own_contribution_receipt") private String own_contribution_receipt;
	@Column(name="loan_eligibility") private String loan_eligibility;
	
	@Column(name="user_name") private String user_name;
	@Column(name="attended") private String attended;
	@Column(name="cp_comments__c") private String cp_comments__c;
	
	@Column(name="followdate") private Date followdate;
	@Column(name="followtype") private String followtype;
	@Column(name="user_id") private int user_id;
	
	@Column(name="trigger1") private String trigger1;
	@Column(name="trigger2") private String trigger2;
	@Column(name="barrier1") private String barrier1;
	@Column(name="barrier2") private String barrier2;
	@Column(name="Lost_reason_c__c") private String Lost_reason_c__c;
	
	public int getRow_number() {
		return row_number;
	}
	public void setRow_number(int row_number) {
		this.row_number = row_number;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getTokenno() {
		return tokenno;
	}
	public void setTokenno(String tokenno) {
		this.tokenno = tokenno;
	}
	public String getEnquiryname() {
		return enquiryname;
	}
	public void setEnquiryname(String enquiryname) {
		this.enquiryname = enquiryname;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHave_we_met_before() {
		return have_we_met_before;
	}
	public void setHave_we_met_before(String have_we_met_before) {
		this.have_we_met_before = have_we_met_before;
	}
	public String getAge_a__c() {
		return age_a__c;
	}
	public void setAge_a__c(String age_a__c) {
		this.age_a__c = age_a__c;
	}
	public String getResidenceaddress() {
		return residenceaddress;
	}
	public void setResidenceaddress(String residenceaddress) {
		this.residenceaddress = residenceaddress;
	}
	public String getOfficelocation() {
		return officelocation;
	}
	public void setOfficelocation(String officelocation) {
		this.officelocation = officelocation;
	}
	public String getEmpstatus() {
		return empstatus;
	}
	public void setEmpstatus(String empstatus) {
		this.empstatus = empstatus;
	}
	public String getCompany_name__c() {
		return company_name__c;
	}
	public void setCompany_name__c(String company_name__c) {
		this.company_name__c = company_name__c;
	}
	public String getIs_purchase_for_self_use_or_investment__c() {
		return is_purchase_for_self_use_or_investment__c;
	}
	public void setIs_purchase_for_self_use_or_investment__c(String is_purchase_for_self_use_or_investment__c) {
		this.is_purchase_for_self_use_or_investment__c = is_purchase_for_self_use_or_investment__c;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getCarpet_area_requirement() {
		return carpet_area_requirement;
	}
	public void setCarpet_area_requirement(String carpet_area_requirement) {
		this.carpet_area_requirement = carpet_area_requirement;
	}
	public String getTypology_requirement() {
		return typology_requirement;
	}
	public void setTypology_requirement(String typology_requirement) {
		this.typology_requirement = typology_requirement;
	}
	public String getWalk_in_source__c() {
		return walk_in_source__c;
	}
	public void setWalk_in_source__c(String walk_in_source__c) {
		this.walk_in_source__c = walk_in_source__c;
	}
	public String getAdvertisement__c() {
		return advertisement__c;
	}
	public void setAdvertisement__c(String advertisement__c) {
		this.advertisement__c = advertisement__c;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdvertisementname() {
		return advertisementname;
	}
	public void setAdvertisementname(String advertisementname) {
		this.advertisementname = advertisementname;
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
	public String getCurrent_residence_configuration() {
		return current_residence_configuration;
	}
	public void setCurrent_residence_configuration(String current_residence_configuration) {
		this.current_residence_configuration = current_residence_configuration;
	}
	public String getCurrent_residence_ownership() {
		return current_residence_ownership;
	}
	public void setCurrent_residence_ownership(String current_residence_ownership) {
		this.current_residence_ownership = current_residence_ownership;
	}
	public String getSource_of_funding() {
		return source_of_funding;
	}
	public void setSource_of_funding(String source_of_funding) {
		this.source_of_funding = source_of_funding;
	}
	public String getCustomer_classification() {
		return customer_classification;
	}
	public void setCustomer_classification(String customer_classification) {
		this.customer_classification = customer_classification;
	}
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public String getUnit_availability() {
		return unit_availability;
	}
	public void setUnit_availability(String unit_availability) {
		this.unit_availability = unit_availability;
	}
	public String getAccompanied_by() {
		return accompanied_by;
	}
	public void setAccompanied_by(String accompanied_by) {
		this.accompanied_by = accompanied_by;
	}
	public String getDeal_negotiation() {
		return deal_negotiation;
	}
	public void setDeal_negotiation(String deal_negotiation) {
		this.deal_negotiation = deal_negotiation;
	}
	public String getConstruction_status() {
		return construction_status;
	}
	public void setConstruction_status(String construction_status) {
		this.construction_status = construction_status;
	}
	public String getTimeframe_to_book() {
		return timeframe_to_book;
	}
	public void setTimeframe_to_book(String timeframe_to_book) {
		this.timeframe_to_book = timeframe_to_book;
	}
	public String getEnquirynoneditcomment() {
		return enquirynoneditcomment;
	}
	public void setEnquirynoneditcomment(String enquirynoneditcomment) {
		this.enquirynoneditcomment = enquirynoneditcomment;
	}
	public String getSourcing_managers__c() {
		return sourcing_managers__c;
	}
	public void setSourcing_managers__c(String sourcing_managers__c) {
		this.sourcing_managers__c = sourcing_managers__c;
	}
	public String getSourcingname() {
		return sourcingname;
	}
	public void setSourcingname(String sourcingname) {
		this.sourcingname = sourcingname;
	}
	public String getSourcingemail() {
		return sourcingemail;
	}
	public void setSourcingemail(String sourcingemail) {
		this.sourcingemail = sourcingemail;
	}
	public String getClosing_managers__c() {
		return closing_managers__c;
	}
	public void setClosing_managers__c(String closing_managers__c) {
		this.closing_managers__c = closing_managers__c;
	}
	public String getClosingname() {
		return closingname;
	}
	public void setClosingname(String closingname) {
		this.closingname = closingname;
	}
	public String getClosingemail() {
		return closingemail;
	}
	public void setClosingemail(String closingemail) {
		this.closingemail = closingemail;
	}
	public String getOwn_contribution_receipt() {
		return own_contribution_receipt;
	}
	public void setOwn_contribution_receipt(String own_contribution_receipt) {
		this.own_contribution_receipt = own_contribution_receipt;
	}
	public String getLoan_eligibility() {
		return loan_eligibility;
	}
	public void setLoan_eligibility(String loan_eligibility) {
		this.loan_eligibility = loan_eligibility;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getAttended() {
		return attended;
	}
	public void setAttended(String attended) {
		this.attended = attended;
	}
	public String getCp_comments__c() {
		return cp_comments__c;
	}
	public void setCp_comments__c(String cp_comments__c) {
		this.cp_comments__c = cp_comments__c;
	}
	public Date getFollowdate() {
		return followdate;
	}
	public void setFollowdate(Date followdate) {
		this.followdate = followdate;
	}
	public String getFollowtype() {
		return followtype;
	}
	public void setFollowtype(String followtype) {
		this.followtype = followtype;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getTrigger1() {
		return trigger1;
	}
	public void setTrigger1(String trigger1) {
		this.trigger1 = trigger1;
	}
	public String getTrigger2() {
		return trigger2;
	}
	public void setTrigger2(String trigger2) {
		this.trigger2 = trigger2;
	}
	public String getBarrier1() {
		return barrier1;
	}
	public void setBarrier1(String barrier1) {
		this.barrier1 = barrier1;
	}
	public String getBarrier2() {
		return barrier2;
	}
	public void setBarrier2(String barrier2) {
		this.barrier2 = barrier2;
	}
	public String getLost_reason_c__c() {
		return Lost_reason_c__c;
	}
	public void setLost_reason_c__c(String lost_reason_c__c) {
		Lost_reason_c__c = lost_reason_c__c;
	}


}
