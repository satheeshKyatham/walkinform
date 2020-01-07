package com.godrej.properties.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "salesforce.mst_user")
public class UserMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int user_id;
	@Column(name = "user_name")
	private String user_name;
	@Column(name = "password")
	private String password;
	@Column(name = "emailid")
	private String emailid;
	@Column(name = "mobileNo")
	private String mobileNo;
	@Column(name = "deviceid")
	private String deviceid;
	@Column(name = "latitude")
	private String latitude;
	@Column(name = "longitute")
	private String longitute;
	@Column(name = "projectId")
	private String projectId;
	@Column(name = "projectName")
	private String projectName;
	@Column(name = "role")
	private String role;
	@Column(name = "isActive")
	private String isActive;
	@Column(name = "createdDate")
	private Timestamp createdDate;

	@Column(name = "fromdate")
	private Date fromdate;
	@Column(name = "todate")
	private Date todate;
	@Column(name = "launchtype")
	private String launchtype;
	@Column(name = "mst_user_rolemaster_id")
	private int mst_user_rolemaster_id;
	@Transient
	private String msg;

	@Transient
	private String type;

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

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public String getLaunchtype() {
		return launchtype;
	}

	public void setLaunchtype(String launchtype) {
		this.launchtype = launchtype;
	}

	public int getMst_user_rolemaster_id() {
		return mst_user_rolemaster_id;
	}

	public void setMst_user_rolemaster_id(int mst_user_rolemaster_id) {
		this.mst_user_rolemaster_id = mst_user_rolemaster_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UserMaster [user_id=" + user_id + ", user_name=" + user_name + ", password=" + password + ", emailid="
				+ emailid + ", mobileNo=" + mobileNo + ", deviceid=" + deviceid + ", latitude=" + latitude
				+ ", longitute=" + longitute + ", projectId=" + projectId + ", projectName=" + projectName + ", role="
				+ role + ", isActive=" + isActive + ", createdDate=" + createdDate + "]";
	}

}
