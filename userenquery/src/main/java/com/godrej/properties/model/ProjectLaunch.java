package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.nv_projects_c")
public class ProjectLaunch implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id") private String  id;
	@Column(name="project_18_digit__c") private String  project_18_digit__c;
	@Column(name="name") private String  name;
	@Column(name="propstrength__project_code__c") private String  propstrength__project_code__c;
	@Column(name="isActive") private String  isActive;
	@Column(name="region__c") private String  regionname;
	@Column(name="salesmanager_sfid") private String  salesmanager_sfid;
	
	@Column(name="ccavenue_merchant_id") private Integer  ccavenue_merchant_id;
	@Column(name="ccavenue_workingkey") private String  ccavenue_workingkey;
	@Column(name="ccavenue_accesscode") private String  ccavenue_accesscode;
	@Column(name="isallow_ccpayment_gateway") private String  isallow_payment;
	
	@Column(name="sitehead_user_id") private Integer  sitehead_user_id;
	@Column(name="sitehead_user_mail") private String  sitehead_user_mail;
	@Column(name="sitehead_user_name") private String  sitehead_user_name;
	
	public String getSalesmanager_sfid() {
		return salesmanager_sfid;
	}
	public void setSalesmanager_sfid(String salesmanager_sfid) {
		this.salesmanager_sfid = salesmanager_sfid;
	}
	public String getRegionname() {
		return regionname;
	}
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProject_18_digit__c() {
		return project_18_digit__c;
	}
	public void setProject_18_digit__c(String project_18_digit__c) {
		this.project_18_digit__c = project_18_digit__c;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPropstrength__project_code__c() {
		return propstrength__project_code__c;
	}
	public void setPropstrength__project_code__c(String propstrength__project_code__c) {
		this.propstrength__project_code__c = propstrength__project_code__c;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getCcavenue_merchant_id() {
		return ccavenue_merchant_id;
	}
	public void setCcavenue_merchant_id(Integer ccavenue_merchant_id) {
		this.ccavenue_merchant_id = ccavenue_merchant_id;
	}
	public String getCcavenue_workingkey() {
		return ccavenue_workingkey;
	}
	public void setCcavenue_workingkey(String ccavenue_workingkey) {
		this.ccavenue_workingkey = ccavenue_workingkey;
	}
	public String getCcavenue_accesscode() {
		return ccavenue_accesscode;
	}
	public void setCcavenue_accesscode(String ccavenue_accesscode) {
		this.ccavenue_accesscode = ccavenue_accesscode;
	}
	public String getIsallow_payment() {
		return isallow_payment;
	}
	public void setIsallow_payment(String isallow_payment) {
		this.isallow_payment = isallow_payment;
	}
	public Integer getSitehead_user_id() {
		return sitehead_user_id;
	}
	public void setSitehead_user_id(Integer sitehead_user_id) {
		this.sitehead_user_id = sitehead_user_id;
	}
	public String getSitehead_user_mail() {
		return sitehead_user_mail;
	}
	public void setSitehead_user_mail(String sitehead_user_mail) {
		this.sitehead_user_mail = sitehead_user_mail;
	}
	public String getSitehead_user_name() {
		return sitehead_user_name;
	}
	public void setSitehead_user_name(String sitehead_user_name) {
		this.sitehead_user_name = sitehead_user_name;
	}

}
