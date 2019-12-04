package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.nv_daily_user_master")
public class DailyUserMater {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="usermaster_id") private  int usermaster_id ;
	
	@Column(name="user_id") private int	user_id;
	@Column(name="project_id") private String project_id ;
	@Column(name="desk_code") private String desk_code;
	@Column(name="isactive") private String	isactive;
	@Column(name="createddate") private Timestamp	createddate;
	public int getUsermaster_id() {
		return usermaster_id;
	}
	public void setUsermaster_id(int usermaster_id) {
		this.usermaster_id = usermaster_id;
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
	public String getDesk_code() {
		return desk_code;
	}
	public void setDesk_code(String desk_code) {
		this.desk_code = desk_code;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}

}
