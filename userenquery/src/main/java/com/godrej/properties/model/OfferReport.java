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
@Table(name = "salesforce.vw_offer_with_balance_details") 
public class OfferReport{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id") private  int id;
	@Column(name="createddate") private Date createddate;
	@Column(name="offername") private String offername;
	@Column(name="propstrength__status__c") private String propstrength__status__c;	
	@Column(name="enquiryname")  private String enquiryname;	 
	@Column(name="verticle__c") private String verticle__c;   	
	@Column(name="closing_manager_name__c") private String closing_manager_name__c;	 
	@Column(name="sourcing_manager_name__c") private String sourcing_manager_name__c;	 
	@Column(name="contactname") private String contactname;	 
	@Column(name="projectname") private String projectname;
	@Column(name="propstrength__property_name__c") private String propstrength__property_name__c;
	
	@Column(name="isdeleted") private Boolean isdeleted;
	@Column(name="propstrength__project__c") private String propstrength__project__c;
	
	@Column(name="propstrength__reason_for_loss__c") private String propstrength__reason_for_loss__c;
	@Column(name="propstrength__comment__c") private String propstrength__comment__c;
	
	
	@Transient private String qry_count;
	@Transient private String qry_msg;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	public String getOffername() {
		return offername;
	}
	public void setOffername(String offername) {
		this.offername = offername;
	}
	public String getPropstrength__status__c() {
		return propstrength__status__c;
	}
	public void setPropstrength__status__c(String propstrength__status__c) {
		this.propstrength__status__c = propstrength__status__c;
	}
	public String getEnquiryname() {
		return enquiryname;
	}
	public void setEnquiryname(String enquiryname) {
		this.enquiryname = enquiryname;
	}
	public String getVerticle__c() {
		return verticle__c;
	}
	public void setVerticle__c(String verticle__c) {
		this.verticle__c = verticle__c;
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
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getPropstrength__property_name__c() {
		return propstrength__property_name__c;
	}
	public void setPropstrength__property_name__c(String propstrength__property_name__c) {
		this.propstrength__property_name__c = propstrength__property_name__c;
	}
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	public String getPropstrength__project__c() {
		return propstrength__project__c;
	}
	public void setPropstrength__project__c(String propstrength__project__c) {
		this.propstrength__project__c = propstrength__project__c;
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
	public String getPropstrength__reason_for_loss__c() {
		return propstrength__reason_for_loss__c;
	}
	public void setPropstrength__reason_for_loss__c(String propstrength__reason_for_loss__c) {
		this.propstrength__reason_for_loss__c = propstrength__reason_for_loss__c;
	}
	public String getPropstrength__comment__c() {
		return propstrength__comment__c;
	}
	public void setPropstrength__comment__c(String propstrength__comment__c) {
		this.propstrength__comment__c = propstrength__comment__c;
	}
}