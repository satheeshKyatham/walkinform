package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.propstrength__tower__c")
public class TowerMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int  id;
	
	@Column(name = "sfid")
	private  String sfid;
	

	@Column(name = "propstrength__tower_code__c")
	private  String tower_code__c;
	

	@Column(name = "propstrength__project_name__c")
	private  String propstrength__project_name__c;
	

	@Column(name = "propstrength__tower_name__c")
	private  String tower_name__c;

	@Column(name = "d4u_active__c")
	private  String d4u_active__c;

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
 

	public String getPropstrength__project_name__c() {
		return propstrength__project_name__c;
	}


	public void setPropstrength__project_name__c(String propstrength__project_name__c) {
		this.propstrength__project_name__c = propstrength__project_name__c;
	}


	public String getTower_code__c() {
		return tower_code__c;
	}


	public void setTower_code__c(String tower_code__c) {
		this.tower_code__c = tower_code__c;
	}


	public String getTower_name__c() {
		return tower_name__c;
	}


	public void setTower_name__c(String tower_name__c) {
		this.tower_name__c = tower_name__c;
	}


	public String getD4u_active__c() {
		return d4u_active__c;
	}


	public void setD4u_active__c(String d4u_active__c) {
		this.d4u_active__c = d4u_active__c;
	}


 	
	
}
