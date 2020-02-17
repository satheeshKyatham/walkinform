package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.vw_eoi_form_kyc")
public class EOIDataVW implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id") private int id;
	@Column(name="enquiryid") private String enquiryid;
	@Column(name="application_name") private String application_name;
	@Column(name="phone_number") private String phone_number;
	@Column(name="issubmitted") private String issubmitted;
	@Column(name="userdocid", nullable = false) private String userdocid;
	@Column(name="userid") private String userid;
	@Column(name="kyclink") private String kyclink;
	@Column(name="kycapproval_status") private String kycapproval_status;
	@Column(name="kycreject_comment") private String kycreject_comment;
	@Column(name="offername")
	private String offerName;

	@Column(name="offersfid")
	private String offerSfid;
	@Column(name = "contactsfid")
	private String contactSfid;
	@Column(name = "enquirysfid")
	private String enquirySfid;
	@Column(name="costsheet_path")	
	private String costsheetPath;
	@Column(name="property_name1__c") private String property_name1__c;
	@Column(name="totalamount")	
	private String totalamount;
	@Column(name="createdoffer_userid")	
	private String createdoffer_userid;
	
	public String getUserdocid() {
		return userdocid;
	}
	public void setUserdocid(String userdocid) {
	this.userdocid = userdocid;
	}
	
	public String getIssubmitted() {
		return issubmitted;
	}
	public void setIssubmitted(String issubmitted) {
		this.issubmitted = issubmitted;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEnquiryid() {
		return enquiryid;
	}
	public void setEnquiryid(String enquiryid) {
		this.enquiryid = enquiryid;
	}
	public String getApplication_name() {
		return application_name;
	}
	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getKyclink() {
		return kyclink;
	}
	public void setKyclink(String kyclink) {
		this.kyclink = kyclink;
	}
	public String getKycapproval_status() {
		return kycapproval_status;
	}
	public void setKycapproval_status(String kycapproval_status) {
		this.kycapproval_status = kycapproval_status;
	}
	public String getKycreject_comment() {
		return kycreject_comment;
	}
	public void setKycreject_comment(String kycreject_comment) {
		this.kycreject_comment = kycreject_comment;
	}
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public String getOfferSfid() {
		return offerSfid;
	}
	public void setOfferSfid(String offerSfid) {
		this.offerSfid = offerSfid;
	}
	public String getContactSfid() {
		return contactSfid;
	}
	public void setContactSfid(String contactSfid) {
		this.contactSfid = contactSfid;
	}
	public String getEnquirySfid() {
		return enquirySfid;
	}
	public void setEnquirySfid(String enquirySfid) {
		this.enquirySfid = enquirySfid;
	}
	public String getCostsheetPath() {
		return costsheetPath;
	}
	public void setCostsheetPath(String costsheetPath) {
		this.costsheetPath = costsheetPath;
	}
	public String getProperty_name1__c() {
		return property_name1__c;
	}
	public void setProperty_name1__c(String property_name1__c) {
		this.property_name1__c = property_name1__c;
	}
	public String getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
	public String getCreatedoffer_userid() {
		return createdoffer_userid;
	}
	public void setCreatedoffer_userid(String createdoffer_userid) {
		this.createdoffer_userid = createdoffer_userid;
	}
	
}
