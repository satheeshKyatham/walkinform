package com.godrej.properties.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "salesforce.nv_cp_engagement")
public class CPEngmnt {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id") private  int id;
	@Column(name="visit_date") private Date visit_date;
	@Column(name="project_sfid") private String project_sfid;
	@Column(name="cp_name") private String cp_name;
	@Column(name="meeting_place") private String meeting_place;
	@Column(name="topic") private String topic;
	@Column(name="discussed_point") private String discussed_point;   
	@Column(name="createdby")  private Integer createdby;
	@Column(name="updatedby")  private Integer updatedby;
	@Column(name="isactive") private String isactive;   
	@Column(name="createddate") private Timestamp createddate;
	@Column(name="updateddate") private Timestamp updateddate;
	
	@Transient private String project_name;
	@Transient private String user_name; 
	
	@Transient private String qry_count;
	@Transient private String qry_msg;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(Date visit_date) {
		this.visit_date = visit_date;
	}
	public String getProject_sfid() {
		return project_sfid;
	}
	public void setProject_sfid(String project_sfid) {
		this.project_sfid = project_sfid;
	}
	public String getCp_name() {
		return cp_name;
	}
	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}
	public String getMeeting_place() {
		return meeting_place;
	}
	public void setMeeting_place(String meeting_place) {
		this.meeting_place = meeting_place;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getDiscussed_point() {
		return discussed_point;
	}
	public void setDiscussed_point(String discussed_point) {
		this.discussed_point = discussed_point;
	}
	public Integer getCreatedby() {
		return createdby;
	}
	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}
	public Integer getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(Integer updatedby) {
		this.updatedby = updatedby;
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
	public Timestamp getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(Timestamp updateddate) {
		this.updateddate = updateddate;
	}
	public String getQry_count() {
		return qry_count;
	}
	public void setQry_count(String qry_count) {
		this.qry_count = qry_count;
	}
	public String getQry_msg() {
		return qry_msg;
	}
	public void setQry_msg(String qry_msg) {
		this.qry_msg = qry_msg;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}