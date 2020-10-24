package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.vw_enquiry_dtl" )
public class EnqOnMap {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="row_number") private int  row_number;
	@Column(name="project_name") private String project_name;
	@Column(name="name") private String name;
	@Column(name="firstname") private String firstname;
	@Column(name="lastname") private String lastname;
	@Column(name="residencelat") private String residencelat;
	@Column(name="residencelng") private String residencelng;
	@Column(name="mobileno") private String mobileno;
	@Column(name="referred_partner_flag__c") private String referred_partner_flag__c;
	@Column(name="walk_in_source__c") private String walk_in_source__c;
	@Column(name="verticle__c") private String verticle__c;
	
	
	public int getRow_number() {
		return row_number;
	}
	public void setRow_number(int row_number) {
		this.row_number = row_number;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getResidencelat() {
		return residencelat;
	}
	public void setResidencelat(String residencelat) {
		this.residencelat = residencelat;
	}
	public String getResidencelng() {
		return residencelng;
	}
	public void setResidencelng(String residencelng) {
		this.residencelng = residencelng;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getReferred_partner_flag__c() {
		return referred_partner_flag__c;
	}
	public void setReferred_partner_flag__c(String referred_partner_flag__c) {
		this.referred_partner_flag__c = referred_partner_flag__c;
	}
	public String getWalk_in_source__c() {
		return walk_in_source__c;
	}
	public void setWalk_in_source__c(String walk_in_source__c) {
		this.walk_in_source__c = walk_in_source__c;
	}
	public String getVerticle__c() {
		return verticle__c;
	}
	public void setVerticle__c(String verticle__c) {
		this.verticle__c = verticle__c;
	}
}