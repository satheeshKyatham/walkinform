package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.vw_otherdata_paymentsheet" )
public class PaymentSheet {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sequence") private String  sequence;
	@Column(name="casetest") private String  casetest;
	@Column(name="propstrength__rate_per_unit_area__c") private String  propstrength__rate_per_unit_area__c;
	@Column(name="propstrength__type__c") private String  propstrength__type__c;
	@Column(name="name") private String  name;
	@Column(name="propstrength__property__c") private String  propstrength__property__c;
	@Column(name="propstrength__other_charges__c") private String  propstrength__other_charges__c;
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getCasetest() {
		return casetest;
	}
	public void setCasetest(String casetest) {
		this.casetest = casetest;
	}
	public String getPropstrength__rate_per_unit_area__c() {
		return propstrength__rate_per_unit_area__c;
	}
	public void setPropstrength__rate_per_unit_area__c(String propstrength__rate_per_unit_area__c) {
		this.propstrength__rate_per_unit_area__c = propstrength__rate_per_unit_area__c;
	}
	public String getPropstrength__type__c() {
		return propstrength__type__c;
	}
	public void setPropstrength__type__c(String propstrength__type__c) {
		this.propstrength__type__c = propstrength__type__c;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
 
	 
	public String getPropstrength__property__c() {
		return propstrength__property__c;
	}
	public void setPropstrength__property__c(String propstrength__property__c) {
		this.propstrength__property__c = propstrength__property__c;
	}
	public String getPropstrength__other_charges__c() {
		return propstrength__other_charges__c;
	}
	public void setPropstrength__other_charges__c(String propstrength__other_charges__c) {
		this.propstrength__other_charges__c = propstrength__other_charges__c;
	}
	
	

}
