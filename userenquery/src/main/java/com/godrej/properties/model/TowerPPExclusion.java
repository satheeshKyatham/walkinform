package com.godrej.properties.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="salesforce.gpl_cip_tower_pp_exclusion")
public class TowerPPExclusion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;		
	@Column(name = "payment_plan_sfid")private String payment_plan_sfid;
	@Column(name = "payment_plan_name")private String payment_plan_name;
	@Column(name = "project_sfid")private String project_sfid;
	@Column(name = "project_name")private String project_name;
	@Column(name = "createdby")private int createdby;
	@Column(name = "tower_sfid")private String tower_sfid;
	@Column(name = "tower_name")private String tower_name;
	@Column(name="createddate", updatable=false)private Timestamp created;
	@Column(name="isactive")private String isactive;
	@Transient private String insertStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPayment_plan_sfid() {
		return payment_plan_sfid;
	}
	public void setPayment_plan_sfid(String payment_plan_sfid) {
		this.payment_plan_sfid = payment_plan_sfid;
	}
	public String getPayment_plan_name() {
		return payment_plan_name;
	}
	public void setPayment_plan_name(String payment_plan_name) {
		this.payment_plan_name = payment_plan_name;
	}
	public String getProject_sfid() {
		return project_sfid;
	}
	public void setProject_sfid(String project_sfid) {
		this.project_sfid = project_sfid;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	
	public String getTower_sfid() {
		return tower_sfid;
	}
	public void setTower_sfid(String tower_sfid) {
		this.tower_sfid = tower_sfid;
	}
	public String getTower_name() {
		return tower_name;
	}
	public void setTower_name(String tower_name) {
		this.tower_name = tower_name;
	}
	public String getInsertStatus() {
		return insertStatus;
	}
	public void setInsertStatus(String insertStatus) {
		this.insertStatus = insertStatus;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	
	

}
