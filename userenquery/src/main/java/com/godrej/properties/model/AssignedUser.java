package com.godrej.properties.model;

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
@Table(name = "salesforce.vw_assigneduser") 
public class AssignedUser {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="row_number") private String	row_number;
	
	@Column(name="nv_token_id") private String	nv_token_id;
	@Column(name="token_no") private  String token_no ;
	@Column(name="name") private String	name;
	@Column(name="starteddate") private Timestamp	starteddate;
	@Column(name="mobileno") private String	mobileno;
	@Column(name="window_assign") private String	window_assign;
	@Column(name="isdone") private String	isdone;
	@Column(name="projectname") private String	projectname;
	@Column(name="created") private Date created;
	@Column(name="priority_no__c") private String priority_no__c;
	@Column(name="closing_manager_name__c") private String closing_manager_name__c;
	
	@Column(name="offersfid") private String offersfid;
	@Column(name="offerName") private String offerName;
	@Column(name="countrycode") private String countrycode;
	
	@Column(name="followdate") private Timestamp followdate;
	@Column(name="followtype") private String followtype;
	
	public String getPriority_no__c() {
		return priority_no__c;
	}
	public void setPriority_no__c(String priority_no__c) {
		this.priority_no__c = priority_no__c;
	}
	public String getClosing_manager_name__c() {
		return closing_manager_name__c;
	}
	public void setClosing_manager_name__c(String closing_manager_name__c) {
		this.closing_manager_name__c = closing_manager_name__c;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getNv_token_id() {
		return nv_token_id;
	}
	public void setNv_token_id(String nv_token_id) {
		this.nv_token_id = nv_token_id;
	}
	public String getToken_no() {
		return token_no;
	}
	public void setToken_no(String token_no) {
		this.token_no = token_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getStarteddate() {
		return starteddate;
	}
	public void setStarteddate(Timestamp starteddate) {
		this.starteddate = starteddate;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getWindow_assign() {
		return window_assign;
	}
	public void setWindow_assign(String window_assign) {
		this.window_assign = window_assign;
	}
	public String getIsdone() {
		return isdone;
	}
	public void setIsdone(String isdone) {
		this.isdone = isdone;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getRow_number() {
		return row_number;
	}
	public void setRow_number(String row_number) {
		this.row_number = row_number;
	}
	public String getOffersfid() {
		return offersfid;
	}
	public void setOffersfid(String offersfid) {
		this.offersfid = offersfid;
	}
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	public Timestamp getFollowdate() {
		return followdate;
	}
	public void setFollowdate(Timestamp followdate) {
		this.followdate = followdate;
	}
	public String getFollowtype() {
		return followtype;
	}
	public void setFollowtype(String followtype) {
		this.followtype = followtype;
	}
}