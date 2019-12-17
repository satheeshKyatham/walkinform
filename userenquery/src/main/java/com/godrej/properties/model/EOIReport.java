package com.godrej.properties.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.vw_eoi_report") 
public class EOIReport implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="id") private  int id ;
	@Column(name="region__c") private String	region__c;
	@Column(name="project_name") private String	project_name;
	@Column(name="enq_name") private String	enq_name;
	@Column(name="customer_name") private String customer_name;
	@Column(name="customer_mobile") private String	customer_mobile;
	@Column(name="customer_email") private String	customer_email;
	@Column(name="walkin_date") private Date	walkin_date;
	@Column(name="walk_in_source__c") private String walk_in_source__c;
	@Column(name="verticle__c") private String verticle__c;
	@Column(name="closing_manager_name__c") private String closing_manager_name__c;
	
	@Column(name="total_payment_done") private String total_payment_done;
	@Column(name="total_payment_approved") private String total_payment_approved;
	
	@Column(name="typology_name1") private String typology_name1;
	@Column(name="typology_name2") private String typology_name2;
	@Column(name="typology_name3") private String typology_name3;
	
	@Column(name="floor_band1") private String floor_band1;
	@Column(name="floor_band2") private String floor_band2;
	@Column(name="floor_band3") private String floor_band3;
	
	@Column(name="tower_name1") private String tower_name1;
	@Column(name="tower_name2") private String tower_name2;
	@Column(name="tower_name3") private String tower_name3;
	
	@Column(name="date_of_eoi__c") private Date date_of_eoi__c;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRegion__c() {
		return region__c;
	}
	public void setRegion__c(String region__c) {
		this.region__c = region__c;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getEnq_name() {
		return enq_name;
	}
	public void setEnq_name(String enq_name) {
		this.enq_name = enq_name;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_mobile() {
		return customer_mobile;
	}
	public void setCustomer_mobile(String customer_mobile) {
		this.customer_mobile = customer_mobile;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public Date getWalkin_date() {
		return walkin_date;
	}
	public void setWalkin_date(Date walkin_date) {
		this.walkin_date = walkin_date;
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
	public String getClosing_manager_name__c() {
		return closing_manager_name__c;
	}
	public void setClosing_manager_name__c(String closing_manager_name__c) {
		this.closing_manager_name__c = closing_manager_name__c;
	}
	public String getTotal_payment_done() {
		return total_payment_done;
	}
	public void setTotal_payment_done(String total_payment_done) {
		this.total_payment_done = total_payment_done;
	}
	public String getTotal_payment_approved() {
		return total_payment_approved;
	}
	public void setTotal_payment_approved(String total_payment_approved) {
		this.total_payment_approved = total_payment_approved;
	}
	public String getTypology_name1() {
		return typology_name1;
	}
	public void setTypology_name1(String typology_name1) {
		this.typology_name1 = typology_name1;
	}
	public String getTypology_name2() {
		return typology_name2;
	}
	public void setTypology_name2(String typology_name2) {
		this.typology_name2 = typology_name2;
	}
	public String getTypology_name3() {
		return typology_name3;
	}
	public void setTypology_name3(String typology_name3) {
		this.typology_name3 = typology_name3;
	}
	public String getFloor_band1() {
		return floor_band1;
	}
	public void setFloor_band1(String floor_band1) {
		this.floor_band1 = floor_band1;
	}
	public String getFloor_band2() {
		return floor_band2;
	}
	public void setFloor_band2(String floor_band2) {
		this.floor_band2 = floor_band2;
	}
	public String getFloor_band3() {
		return floor_band3;
	}
	public void setFloor_band3(String floor_band3) {
		this.floor_band3 = floor_band3;
	}
	public String getTower_name1() {
		return tower_name1;
	}
	public void setTower_name1(String tower_name1) {
		this.tower_name1 = tower_name1;
	}
	public String getTower_name2() {
		return tower_name2;
	}
	public void setTower_name2(String tower_name2) {
		this.tower_name2 = tower_name2;
	}
	public String getTower_name3() {
		return tower_name3;
	}
	public void setTower_name3(String tower_name3) {
		this.tower_name3 = tower_name3;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getDate_of_eoi__c() {
		return date_of_eoi__c;
	}
	public void setDate_of_eoi__c(Date date_of_eoi__c) {
		this.date_of_eoi__c = date_of_eoi__c;
	}
}