package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.vw_user_assign_list")
public class UserProjectMappingList {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="gpl_user_project_mapping_id") private  int gpl_user_project_mapping_id ;
	@Column(name="user_id") private  int user_id ;
	@Column(name="project_id") private  String project_id ;
	@Column(name="project_name") private  String project_name ;
	@Column(name="created_date") private  Timestamp created_date ;
	@Column(name="createdby") private  String createdby ;
	@Column(name="isactive") private  String isactive ;
	@Column(name="user_name") private  String user_name ;
	
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getGpl_user_project_mapping_id() {
		return gpl_user_project_mapping_id;
	}
	public void setGpl_user_project_mapping_id(int gpl_user_project_mapping_id) {
		this.gpl_user_project_mapping_id = gpl_user_project_mapping_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public Timestamp getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

}
