package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

 
@Entity
@Table(name = "salesforce.vw_old_new_values") 
public class ValueOldNew {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="row_number") private  int row_number;
	@Column(name="parentid") private String	parentid;
	@Column(name="field") private String field;
	@Column(name="createddate") private Timestamp createddate;
	@Column(name="oldvalue") private String oldvalue;
	@Column(name="newvalue") private String newvalue;
	@Column(name="sap_customer_code__c") private String sap_customer_code__c;
	public int getRow_number() {
		return row_number;
	}
	public void setRow_number(int row_number) {
		this.row_number = row_number;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public String getOldvalue() {
		return oldvalue;
	}
	public void setOldvalue(String oldvalue) {
		this.oldvalue = oldvalue;
	}
	public String getNewvalue() {
		return newvalue;
	}
	public void setNewvalue(String newvalue) {
		this.newvalue = newvalue;
	}
	public String getSap_customer_code__c() {
		return sap_customer_code__c;
	}
	public void setSap_customer_code__c(String sap_customer_code__c) {
		this.sap_customer_code__c = sap_customer_code__c;
	}
	   	
}
