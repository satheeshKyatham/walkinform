package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="salesforce.vw_token_screen_details")
public class VWTokenScreen {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="nv_token_id") private int nv_token_id;
	@Column(name="type") private String  type;
	@Column(name="window_assign") private String  window_assign;
	@Column(name="created") private Timestamp  created;
	@Column(name="queue") private int  queue;
	@Column(name="isactive") private String   isactive;
	@Column(name="projectname") private String   projectname;
	@Column(name="contact_name") private String   contact_name;
	@Column(name="user_name") private String   user_name;
	
	public int getNv_token_id() {
		return nv_token_id;
	}
	public void setNv_token_id(int nv_token_id) {
		this.nv_token_id = nv_token_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWindow_assign() {
		return window_assign;
	}
	public void setWindow_assign(String window_assign) {
		this.window_assign = window_assign;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public int getQueue() {
		return queue;
	}
	public void setQueue(int queue) {
		this.queue = queue;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}