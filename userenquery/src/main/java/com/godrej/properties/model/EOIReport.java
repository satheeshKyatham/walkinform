package com.godrej.properties.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.vw_eoi_report") 
public class EOIReport implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="gpl_cs_eoi_payment_details_id") private  int id ;
	@Column(name="preferences") private String	preferences;
	@Column(name="enq_sfid") private String	enq_sfid;
	@Column(name="payment_type") private String	payment_type;
	@Column(name="bank_name") private String bank_name;
	@Column(name="branch") private String	branch;
	@Column(name="transaction_id") private String	transaction_id;
	@Column(name="transaction_date") private String	transaction_date;
	@Column(name="transaction_amount") private String transaction_amount;
	@Column(name="project_sfid") private String project_sfid;
	@Column(name="created") private Date created;
	
	@Column(name="project_name") private String project_name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPreferences() {
		return preferences;
	}
	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}
	public String getEnq_sfid() {
		return enq_sfid;
	}
	public void setEnq_sfid(String enq_sfid) {
		this.enq_sfid = enq_sfid;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(String transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public String getProject_sfid() {
		return project_sfid;
	}
	public void setProject_sfid(String project_sfid) {
		this.project_sfid = project_sfid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
}