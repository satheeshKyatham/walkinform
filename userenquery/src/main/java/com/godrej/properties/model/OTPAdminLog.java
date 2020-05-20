package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 

@Entity
@Table(name="salesforce.nv_otp_log_admin")
public class OTPAdminLog {
 
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") private int id ;
	@Column(name="nv_otp_log_id") private Integer nv_otp_log_id;
	@Column(name="otp") private String  otp;
	@Column(name="mobileno") private String  mobileno;
	@Column(name="admin_userid") private Integer admin_userid;
	@Column(name="requester_userid") private Integer requester_userid;
	@Column(name="projectsfid") private String  projectsfid;
	@Column(name="createddate") private Timestamp  createddate;
	@Column(name="isactive") private String  isactive ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getNv_otp_log_id() {
		return nv_otp_log_id;
	}
	public void setNv_otp_log_id(Integer nv_otp_log_id) {
		this.nv_otp_log_id = nv_otp_log_id;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public Integer getAdmin_userid() {
		return admin_userid;
	}
	public void setAdmin_userid(Integer admin_userid) {
		this.admin_userid = admin_userid;
	}
	public Integer getRequester_userid() {
		return requester_userid;
	}
	public void setRequester_userid(Integer requester_userid) {
		this.requester_userid = requester_userid;
	}
	public String getProjectsfid() {
		return projectsfid;
	}
	public void setProjectsfid(String projectsfid) {
		this.projectsfid = projectsfid;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}