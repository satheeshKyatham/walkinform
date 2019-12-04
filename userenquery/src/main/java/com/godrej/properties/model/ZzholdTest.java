package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.propstrength__property__c")

public class ZzholdTest {
	
	private static final long serialVersionUID = 1L; 

	
	  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "id") 
	  private int id;
	 
	
	@Column(name = "sfid")
	private String sfid;
	
	@Column(name = "propstrength__property_on_hold_for_reallocation__c")
	private String propstrength__property_on_hold_for_reallocation__c;
	
	@Column(name = "registration_crm_user__c")
	private String registration_crm_user__c;

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getPropstrength__property_on_hold_for_reallocation__c() {
		return propstrength__property_on_hold_for_reallocation__c;
	}

	public void setPropstrength__property_on_hold_for_reallocation__c(
			String propstrength__property_on_hold_for_reallocation__c) {
		this.propstrength__property_on_hold_for_reallocation__c = propstrength__property_on_hold_for_reallocation__c;
	}

	public String getRegistration_crm_user__c() {
		return registration_crm_user__c;
	}

	public void setRegistration_crm_user__c(String registration_crm_user__c) {
		this.registration_crm_user__c = registration_crm_user__c;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	/*
	 * public static long getSerialversionuid() { return serialVersionUID; }
	 */
	
	
	
	
	
	
}
