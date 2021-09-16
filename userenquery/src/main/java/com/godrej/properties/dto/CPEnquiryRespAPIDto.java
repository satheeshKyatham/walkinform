package com.godrej.properties.dto;

/**
 Created by balram for CP source protection response 
*/
public class CPEnquiryRespAPIDto {
	
	private String src_protection_flag;
	private Integer enquiryId;
	private String enquiry_sfid;
	private String enquiry_name;
	private String date_of_enquiry;
	private String date_of_sitevisit;
	private Boolean eoi_flag;
	private String closing_manager_name;
	private String enquiry_type;
	private String enquiry_source;
	private String project_sfid;
	private String project_name;
	private String walkin_source;
	private String source_name;
	private String other_cp_name;
	private String broker_account_sfid;
	private String broker_account_name;
	private String loyalty_sfid;
	private String loyalty_name;
	private String referral_sfid;
	private String referral_name;
	private String employee_referral_sfid;
	private String employee_referral_name;
	private String referred_partner_flag;
	private String comments;
	private String walkin_source_mobile;	
	private String is_same_broker;
 
	private String contact_sfid;
	private String contact_firstname;
	private String contact_lastname;
	private String contact_id;
	private String contact_mobileno;
	private String contact_emailid;
	private String contact_countrycode;
	private String resp_msg;
	private boolean lead_status;

	
	public String getSrc_protection_flag() {
		return src_protection_flag;
	}
	public void setSrc_protection_flag(String src_protection_flag) {
		this.src_protection_flag = src_protection_flag;
	}
	public String getEnquiry_sfid() {
		return enquiry_sfid;
	}
	public void setEnquiry_sfid(String enquiry_sfid) {
		this.enquiry_sfid = enquiry_sfid;
	}
	public String getEnquiry_name() {
		return enquiry_name;
	}
	public void setEnquiry_name(String enquiry_name) {
		this.enquiry_name = enquiry_name;
	}
	public String getDate_of_enquiry() {
		return date_of_enquiry;
	}
	public void setDate_of_enquiry(String date_of_enquiry) {
		this.date_of_enquiry = date_of_enquiry;
	}
	public String getDate_of_sitevisit() {
		return date_of_sitevisit;
	}
	public void setDate_of_sitevisit(String date_of_sitevisit) {
		this.date_of_sitevisit = date_of_sitevisit;
	}

	public Boolean getEoi_flag() {
		return eoi_flag;
	}
	public void setEoi_flag(Boolean eoi_flag) {
		this.eoi_flag = eoi_flag;
	}
	public String getClosing_manager_name() {
		return closing_manager_name;
	}
	public void setClosing_manager_name(String closing_manager_name) {
		this.closing_manager_name = closing_manager_name;
	}
	public String getEnquiry_type() {
		return enquiry_type;
	}
	public void setEnquiry_type(String enquiry_type) {
		this.enquiry_type = enquiry_type;
	}
	public String getEnquiry_source() {
		return enquiry_source;
	}
	public void setEnquiry_source(String enquiry_source) {
		this.enquiry_source = enquiry_source;
	}
	public String getContact_sfid() {
		return contact_sfid;
	}
	public void setContact_sfid(String contact_sfid) {
		this.contact_sfid = contact_sfid;
	}
	public String getProject_sfid() {
		return project_sfid;
	}
	public void setProject_sfid(String project_sfid) {
		this.project_sfid = project_sfid;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getWalkin_source() {
		return walkin_source;
	}
	public void setWalkin_source(String walkin_source) {
		this.walkin_source = walkin_source;
	}
	public String getSource_name() {
		return source_name;
	}
	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}
	public String getOther_cp_name() {
		return other_cp_name;
	}
	public void setOther_cp_name(String other_cp_name) {
		this.other_cp_name = other_cp_name;
	}
	public String getBroker_account_sfid() {
		return broker_account_sfid;
	}
	public void setBroker_account_sfid(String broker_account_sfid) {
		this.broker_account_sfid = broker_account_sfid;
	}
	public String getLoyalty_sfid() {
		return loyalty_sfid;
	}
	public void setLoyalty_sfid(String loyalty_sfid) {
		this.loyalty_sfid = loyalty_sfid;
	}
	public String getLoyalty_name() {
		return loyalty_name;
	}
	public void setLoyalty_name(String loyalty_name) {
		this.loyalty_name = loyalty_name;
	}
	public String getReferral_sfid() {
		return referral_sfid;
	}
	public void setReferral_sfid(String referral_sfid) {
		this.referral_sfid = referral_sfid;
	}
	public String getReferral_name() {
		return referral_name;
	}
	public void setReferral_name(String referral_name) {
		this.referral_name = referral_name;
	}
	public String getEmployee_referral_sfid() {
		return employee_referral_sfid;
	}
	public void setEmployee_referral_sfid(String employee_referral_sfid) {
		this.employee_referral_sfid = employee_referral_sfid;
	}
	public String getEmployee_referral_name() {
		return employee_referral_name;
	}
	public void setEmployee_referral_name(String employee_referral_name) {
		this.employee_referral_name = employee_referral_name;
	}
	public String getReferred_partner_flag() {
		return referred_partner_flag;
	}
	public void setReferred_partner_flag(String referred_partner_flag) {
		this.referred_partner_flag = referred_partner_flag;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getWalkin_source_mobile() {
		return walkin_source_mobile;
	}
	public void setWalkin_source_mobile(String walkin_source_mobile) {
		this.walkin_source_mobile = walkin_source_mobile;
	}
	
	public Integer getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(Integer enquiryId) {
		this.enquiryId = enquiryId;
	}
	public String getBroker_account_name() {
		return broker_account_name;
	}
	public void setBroker_account_name(String broker_account_name) {
		this.broker_account_name = broker_account_name;
	}
	public String getIs_same_broker() {
		return is_same_broker;
	}
	public void setIs_same_broker(String is_same_broker) {
		this.is_same_broker = is_same_broker;
	}
	public String getContact_firstname() {
		return contact_firstname;
	}
	public void setContact_firstname(String contact_firstname) {
		this.contact_firstname = contact_firstname;
	}
	public String getContact_lastname() {
		return contact_lastname;
	}
	public void setContact_lastname(String contact_lastname) {
		this.contact_lastname = contact_lastname;
	}
	public String getContact_id() {
		return contact_id;
	}
	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}
	public String getContact_mobileno() {
		return contact_mobileno;
	}
	public void setContact_mobileno(String contact_mobileno) {
		this.contact_mobileno = contact_mobileno;
	}
	public String getContact_emailid() {
		return contact_emailid;
	}
	public void setContact_emailid(String contact_emailid) {
		this.contact_emailid = contact_emailid;
	}
	public String getContact_countrycode() {
		return contact_countrycode;
	}
	public void setContact_countrycode(String contact_countrycode) {
		this.contact_countrycode = contact_countrycode;
	}
	public String getResp_msg() {
		return resp_msg;
	}
	public void setResp_msg(String resp_msg) {
		this.resp_msg = resp_msg;
	}
	public boolean isLead_status() {
		return lead_status;
	}
	public void setLead_status(boolean lead_status) {
		this.lead_status = lead_status;
	}
	 

	

}
