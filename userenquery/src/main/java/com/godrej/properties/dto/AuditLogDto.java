package com.godrej.properties.dto;

import java.math.BigDecimal;

public class AuditLogDto {
	
	private int category;
	private String user_email;
	private String enquiry_no;
	private String enquiry_sfid;
	private String contact_sfid;
	private String contact_name;
	private String project_sfid;
	private String project_name;
	private String paymentplan_sfid;
	private String paymentplan_name;
	private String tower_sfid;
	private String tower_name;
	private String inventory_sfid;
	private String inventory_name;
	private String scheme_name;
	private int scheme_rate;
	private int token_id;
	private String token_no;
	private String created_date;
	private String user_machine_details;
	private int user_id;
	
	private String source; 
	
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getEnquiry_no() {
		return enquiry_no;
	}
	public void setEnquiry_no(String enquiry_no) {
		this.enquiry_no = enquiry_no;
	}
	public String getEnquiry_sfid() {
		return enquiry_sfid;
	}
	public void setEnquiry_sfid(String enquiry_sfid) {
		this.enquiry_sfid = enquiry_sfid;
	}
	public String getContact_sfid() {
		return contact_sfid;
	}
	public void setContact_sfid(String contact_sfid) {
		this.contact_sfid = contact_sfid;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
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
	public String getPaymentplan_sfid() {
		return paymentplan_sfid;
	}
	public void setPaymentplan_sfid(String paymentplan_sfid) {
		this.paymentplan_sfid = paymentplan_sfid;
	}
	public String getPaymentplan_name() {
		return paymentplan_name;
	}
	public void setPaymentplan_name(String paymentplan_name) {
		this.paymentplan_name = paymentplan_name;
	}
	public String getTower_sfid() {
		return tower_sfid;
	}
	public void setTower_sfid(String tower_sfid) {
		this.tower_sfid = tower_sfid;
	}
	public String getTower_name() {
		return tower_name;
	}
	public void setTower_name(String tower_name) {
		this.tower_name = tower_name;
	}
	public String getInventory_sfid() {
		return inventory_sfid;
	}
	public void setInventory_sfid(String inventory_sfid) {
		this.inventory_sfid = inventory_sfid;
	}
	public String getInventory_name() {
		return inventory_name;
	}
	public void setInventory_name(String inventory_name) {
		this.inventory_name = inventory_name;
	}
	public int getToken_id() {
		return token_id;
	}
	public void setToken_id(int token_id) {
		this.token_id = token_id;
	}
	public String getToken_no() {
		return token_no;
	}
	public void setToken_no(String token_no) {
		this.token_no = token_no;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getUser_machine_details() {
		return user_machine_details;
	}
	public void setUser_machine_details(String user_machine_details) {
		this.user_machine_details = user_machine_details;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getScheme_name() {
		return scheme_name;
	}
	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}
	public int getScheme_rate() {
		return scheme_rate;
	}
	public void setScheme_rate(int scheme_rate) {
		this.scheme_rate = scheme_rate;
	}  
}