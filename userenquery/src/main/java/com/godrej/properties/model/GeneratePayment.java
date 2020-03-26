package com.godrej.properties.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="salesforce.gpl_cc_payment_request")

public class GeneratePayment implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;	
	@Column(name = "enquiry_name") private String enquiry_name;
	@Column(name = "enquiry_sfid") private String enquiry_sfid;
	@Column(name = "unit_no") private String unit_no;
	@Column(name = "unit_sfid") private String unit_sfid;
	@Column(name = "project_sfid") private String project_sfid;  
	@Column(name = "project_name") private String project_name; 
	@Column(name = "ispayment_status") private String ispayment_status; 
	@Column(name = "isactive") private String isactive; 
	@Column(name = "createdby") private int createdby;
	@Column(name = "updatedby") private int updatedby;
	@Column(name = "created_date") private Timestamp created_date;
	@Column(name = "update_date") private Timestamp update_date;
	@Column(name = "transaction_date") private Date transaction_date;
	@Column(name = "amount") private BigDecimal amount;
	@Column(name = "description") private String description;
	@Column(name = "request_url") private String request_url;
	
	@Transient private String transaction_date_string;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEnquiry_name() {
		return enquiry_name;
	}
	public void setEnquiry_name(String enquiry_name) {
		this.enquiry_name = enquiry_name;
	}
	public String getEnquiry_sfid() {
		return enquiry_sfid;
	}
	public void setEnquiry_sfid(String enquiry_sfid) {
		this.enquiry_sfid = enquiry_sfid;
	}
	public String getUnit_no() {
		return unit_no;
	}
	public void setUnit_no(String unit_no) {
		this.unit_no = unit_no;
	}
	public String getUnit_sfid() {
		return unit_sfid;
	}
	public void setUnit_sfid(String unit_sfid) {
		this.unit_sfid = unit_sfid;
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
	public String getIspayment_status() {
		return ispayment_status;
	}
	public void setIspayment_status(String ispayment_status) {
		this.ispayment_status = ispayment_status;
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
	public int getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}
	public Timestamp getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}
	public Timestamp getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Timestamp update_date) {
		this.update_date = update_date;
	}
	public Date getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTransaction_date_string() {
		return transaction_date_string;
	}
	public void setTransaction_date_string(String transaction_date_string) {
		this.transaction_date_string = transaction_date_string;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRequest_url() {
		return request_url;
	}
	public void setRequest_url(String request_url) {
		this.request_url = request_url;
	}
}