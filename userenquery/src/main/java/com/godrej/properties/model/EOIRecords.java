package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.propstrength__request__c") 
public class EOIRecords implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="id") private  int id ;
	
	@Column(name="sfid") private String	sfid;

	@Column(name="priority_no__c") private String priority_no__c;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPriority_no__c() {
		return priority_no__c;
	}

	public void setPriority_no__c(String priority_no__c) {
		this.priority_no__c = priority_no__c;
	}
}