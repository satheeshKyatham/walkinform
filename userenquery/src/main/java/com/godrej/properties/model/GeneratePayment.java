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
	@Column(name = "customer_mobile") private String customer_mobile;
	@Column(name = "customer_name") private String customer_name;
	@Column(name = "customer_email") private String customer_email;
	
	@Column(name = "bank_ref_no") private String bank_ref_no;
	@Column(name = "payment_status") private String payment_status;
	@Column(name = "towercode") private String towercode;
	@Column(name = "towersfid") private String towersfid;
	@Transient private String transaction_date_string;
	@Column(name = "requestsource") private String requestsource;
	
	@Column(name = "cp_floor_band") private String cp_floor_band;
	@Column(name = "cp_typology") private String cp_typology;
	@Column(name = "cp_broker_name") private String cp_broker_name;
	@Column(name = "cp_broker_sfid") private String cp_broker_sfid;
	@Column(name = "cp_broker_email") private String cp_broker_email;
	
	public String getRequestsource() {
		return requestsource;
	}
	public void setRequestsource(String requestsource) {
		this.requestsource = requestsource;
	}
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
	public String getCustomer_mobile() {
		return customer_mobile;
	}
	public void setCustomer_mobile(String customer_mobile) {
		this.customer_mobile = customer_mobile;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getBank_ref_no() {
		return bank_ref_no;
	}
	public void setBank_ref_no(String bank_ref_no) {
		this.bank_ref_no = bank_ref_no;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public String getTowercode() {
		return towercode;
	}
	public void setTowercode(String towercode) {
		this.towercode = towercode;
	}
	public String getTowersfid() {
		return towersfid;
	}
	public void setTowersfid(String towersfid) {
		this.towersfid = towersfid;
	}
	public String getCp_floor_band() {
		return cp_floor_band;
	}
	public void setCp_floor_band(String cp_floor_band) {
		this.cp_floor_band = cp_floor_band;
	}
	public String getCp_typology() {
		return cp_typology;
	}
	public void setCp_typology(String cp_typology) {
		this.cp_typology = cp_typology;
	}
	public String getCp_broker_name() {
		return cp_broker_name;
	}
	public void setCp_broker_name(String cp_broker_name) {
		this.cp_broker_name = cp_broker_name;
	}
	public String getCp_broker_sfid() {
		return cp_broker_sfid;
	}
	public void setCp_broker_sfid(String cp_broker_sfid) {
		this.cp_broker_sfid = cp_broker_sfid;
	}
	public String getCp_broker_email() {
		return cp_broker_email;
	}
	public void setCp_broker_email(String cp_broker_email) {
		this.cp_broker_email = cp_broker_email;
	}
	
	
	
}