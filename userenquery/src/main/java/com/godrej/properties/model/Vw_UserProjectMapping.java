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
@Table(name = "salesforce.vw_user_project_mapping") 
public class Vw_UserProjectMapping {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="row_number") private  int row_number ;
	
	@Column(name="projectId") private String	projectId;
	@Column(name="user_id") private  int user_id ;
	@Column(name="user_name") private String	user_name;
	@Column(name="password") private String	password;
	@Column(name="emailid") private String	emailid;
	@Column(name="mobileNo") private String	mobileNo;
	@Column(name="deviceid") private String	deviceid;
	@Column(name="latitude") private String	latitude;  
	@Column(name="longitute") private String	longitute;
	@Column(name="projectName") private String	projectName;
	@Column(name="role") private String	role;
	@Column(name="isActive") private String	isActive ;
	@Column(name="createdDate") private Timestamp	createdDate  ;
	@Column(name="desk_code") private String desk_code  ;
	@Column(name="assignto") private String assignto  ;
	@Column(name="closingmgr") private String closingmgr  ;
	@Column(name="email") private String email  ;
	@Column(name="user_type__c") private String user_type__c  ;
	@Column(name="roleid") private String roleid  ;
	@Column(name = "isstlead") private String isstlead;
	@Column(name = "isctlead") private String isctlead;
	@Column(name = "region__c") private String region__c;
	@Column(name = "sitehead_user_mail") private String sitehead_user_mail;
	
	@Column(name = "region_head_name") private String region_head_name;
	@Column(name = "region_head_email") private String region_head_email;
	@Column(name = "region_head_mobile") private String region_head_mobile;
	@Column(name = "sitehead_user_name") private String sitehead_user_name;
	@Column(name = "sitehead_user_mobile") private String sitehead_user_mobile;
	
	@Transient private String	msg  ;
	@Transient private boolean	isregionhead_approval=false;
	
	
	
	public String getAssignto() {
		return assignto;
	}
	public void setAssignto(String assignto) {
		this.assignto = assignto;
	}
	public String getClosingmgr() {
		return closingmgr;
	}
	public void setClosingmgr(String closingmgr) {
		this.closingmgr = closingmgr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUser_type__c() {
		return user_type__c;
	}
	public void setUser_type__c(String user_type__c) {
		this.user_type__c = user_type__c;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitute() {
		return longitute;
	}
	public void setLongitute(String longitute) {
		this.longitute = longitute;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getDesk_code() {
		return desk_code;
	}
	public void setDesk_code(String desk_code) {
		this.desk_code = desk_code;
	}
	public int getRow_number() {
		return row_number;
	}
	public void setRow_number(int row_number) {
		this.row_number = row_number;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getIsstlead() {
		return isstlead;
	}
	public void setIsstlead(String isstlead) {
		this.isstlead = isstlead;
	}
	public String getIsctlead() {
		return isctlead;
	}
	public void setIsctlead(String isctlead) {
		this.isctlead = isctlead;
	}
	public String getRegion__c() {
		return region__c;
	}
	public void setRegion__c(String region__c) {
		this.region__c = region__c;
	}
	public String getSitehead_user_mail() {
		return sitehead_user_mail;
	}
	public void setSitehead_user_mail(String sitehead_user_mail) {
		this.sitehead_user_mail = sitehead_user_mail;
	}
	public String getRegion_head_name() {
		return region_head_name;
	}
	public void setRegion_head_name(String region_head_name) {
		this.region_head_name = region_head_name;
	}
	public String getRegion_head_email() {
		return region_head_email;
	}
	public void setRegion_head_email(String region_head_email) {
		this.region_head_email = region_head_email;
	}
	public String getRegion_head_mobile() {
		return region_head_mobile;
	}
	public void setRegion_head_mobile(String region_head_mobile) {
		this.region_head_mobile = region_head_mobile;
	}
	public boolean isIsregionhead_approval() {
		return isregionhead_approval;
	}
	public void setIsregionhead_approval(boolean isregionhead_approval) {
		this.isregionhead_approval = isregionhead_approval;
	}
	public String getSitehead_user_name() {
		return sitehead_user_name;
	}
	public void setSitehead_user_name(String sitehead_user_name) {
		this.sitehead_user_name = sitehead_user_name;
	}
	public String getSitehead_user_mobile() {
		return sitehead_user_mobile;
	}
	public void setSitehead_user_mobile(String sitehead_user_mobile) {
		this.sitehead_user_mobile = sitehead_user_mobile;
	}
	
	
	
	/*@Override
	public String toString() {
		return "UserMaster [user_id=" + user_id + ", user_name=" + user_name + ", password=" + password + ", emailid="
				+ emailid + ", mobileNo=" + mobileNo + ", deviceid=" + deviceid + ", latitude=" + latitude
				+ ", longitute=" + longitute + ", projectId=" + projectId + ", projectName=" + projectName + ", role="
				+ role + ", isActive=" + isActive + ", createdDate=" + createdDate + ", desk_code=" + desk_code + "]";
	}*/
	
	
	
}
