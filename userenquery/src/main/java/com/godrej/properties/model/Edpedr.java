package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.propstrength__floor__c")
public class Edpedr implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id") private int id;
	@Column(name = "propstrength__tower_name__c") private String propstrength__tower_name__c;
	@Column(name = "sfid") private String sfid;
	@Column(name = "propstrength__tower__c") private String propstrength__tower__c;
	@Column(name = "propstrength__floor_name__c") private String propstrength__floor_name__c;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPropstrength__tower_name__c() {
		return propstrength__tower_name__c;
	}
	public void setPropstrength__tower_name__c(String propstrength__tower_name__c) {
		this.propstrength__tower_name__c = propstrength__tower_name__c;
	}
	public String getSfid() {
		return sfid;
	}
	public void setSfid(String sfid) {
		this.sfid = sfid;
	}
	public String getPropstrength__tower__c() {
		return propstrength__tower__c;
	}
	public void setPropstrength__tower__c(String propstrength__tower__c) {
		this.propstrength__tower__c = propstrength__tower__c;
	}
	public String getPropstrength__floor_name__c() {
		return propstrength__floor_name__c;
	}
	public void setPropstrength__floor_name__c(String propstrength__floor_name__c) {
		this.propstrength__floor_name__c = propstrength__floor_name__c;
	} 
}