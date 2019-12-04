package com.godrej.properties.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name="salesforce.gpl_cs_unit_dtl")

public class RequestAction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="gpl_cs_unit_dtl_id") private int id;
	
	
	@Column (name="timeid")
	private String timeid;
	

	@Column (name="user_contact")
	private String user_contact;
	
	//@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "created")
	@Type(type="date")
	private Date created;
	
	@Column (name="unit_no")
	private String unit_no;
	
	@Column (name="tower")
	private String tower;
	
	@Column (name="typology")
	private String typology;

	@Column (name="admin_status")
	private String admin_status;
	
	
	
	@Column (name="updated_uid")
	private String updated_uid;
	
	@Column (name="updated_email")
	private String updated_email;
	
	@Column (name="updatedby")
	private String updatedby;
	
	
	
	@Column (name="created_uid")
	private String created_uid;
	
	@Column (name="created_email")
	private String created_email;
	
	@Column (name="createdby")
	private String createdby;
	
	@Column (name="customer_name")
	private String customer_name;
	
	@Column (name="scheme_type")
	private String scheme_type;
	
	@Column (name="sendforapproval")
	private String sendforapproval;
	
	
	
	@Column (name="discount_val")
	private String discount_val;
	
	@Column (name="senttocrm")
	private String senttocrm;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_contact() {
		return user_contact;
	}

	public void setUser_contact(String user_contact) {
		this.user_contact = user_contact;
	}

	public String getUnit_no() {
		return unit_no;
	}

	public void setUnit_no(String unit_no) {
		this.unit_no = unit_no;
	}

	public String getTower() {
		return tower;
	}

	public void setTower(String tower) {
		this.tower = tower;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTimeid() {
		return timeid;
	}

	public void setTimeid(String timeid) {
		this.timeid = timeid;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getTypology() {
		return typology;
	}

	public void setTypology(String typology) {
		this.typology = typology;
	}

	public String getAdmin_status() {
		return admin_status;
	}

	public void setAdmin_status(String admin_status) {
		this.admin_status = admin_status;
	}

	public String getUpdated_uid() {
		return updated_uid;
	}

	public void setUpdated_uid(String updated_uid) {
		this.updated_uid = updated_uid;
	}

	public String getUpdated_email() {
		return updated_email;
	}

	public void setUpdated_email(String updated_email) {
		this.updated_email = updated_email;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getCreated_uid() {
		return created_uid;
	}

	public void setCreated_uid(String created_uid) {
		this.created_uid = created_uid;
	}

	public String getCreated_email() {
		return created_email;
	}

	public void setCreated_email(String created_email) {
		this.created_email = created_email;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getScheme_type() {
		return scheme_type;
	}

	public void setScheme_type(String scheme_type) {
		this.scheme_type = scheme_type;
	}

	public String getSendforapproval() {
		return sendforapproval;
	}

	public void setSendforapproval(String sendforapproval) {
		this.sendforapproval = sendforapproval;
	}

	public String getDiscount_val() {
		return discount_val;
	}

	public void setDiscount_val(String discount_val) {
		this.discount_val = discount_val;
	}
}