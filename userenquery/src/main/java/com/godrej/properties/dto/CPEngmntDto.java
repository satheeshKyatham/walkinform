package com.godrej.properties.dto;

public class CPEngmntDto {
	
	private String visit_date;
	private String project_sfid;
	private String cp_name;
	private String meeting_place;
	private String topic;
	private String discussed_point; 
	private String createdby;
	private String updatedby;  
	
	private String cpid;
	private String cpsfid; 
	
	public String getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(String visit_date) {
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
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public String getCpid() {
		return cpid;
	}
	public void setCpid(String cpid) {
		this.cpid = cpid;
	}
	public String getCpsfid() {
		return cpsfid;
	}
	public void setCpsfid(String cpsfid) {
		this.cpsfid = cpsfid;
	}
}