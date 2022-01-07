/**
 * 
 */
package com.godrej.properties.dto;

import java.io.Serializable;

/**
 * @author balram.asati
 *
 */
public class CPContactEnquiryCreateUpdateReqDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4922255275867275036L;
	private String broker_sfid;
	private String project_sfid;
	private String contact_sfid;
	private String enquiry_sfid;
	private String cust_first_name;
	private String cust_last_name;
	private String cust_country_code;
	private String cust_mobile;
	private String cust_emailId;
	private String communication_address1;
	private String communication_address2;
	private String communication_address3;
	private String communication_city;
	private String communication_country;
	private String communication_pin;
	private String from_date;
	private String to_date;
	private String eoi_status;
	private String typology;
	private String tower;
	private String floor_band;
	private String payment_amount;
	private String no_of_units;
	private String enquiry_name;
	private String project_name;
	private String cust_name;
	private String towercode;
	private String towersfid;
	private String loginUser_name;
	private String loginemail_id;
	private String loginUser_id;
	private String requestsource="CP APP";
	private Integer contactId;
	private Integer enquiryId;
	private String token_amount;
	private String broker_name;
	private String broker_email;
	
	
	public String getRequestsource() {
		return requestsource;
	}
	public void setRequestsource(String requestsource) {
		this.requestsource = requestsource;
	}
	public String getEnquiry_name() {
		return enquiry_name;
	}
	public void setEnquiry_name(String enquiry_name) {
		this.enquiry_name = enquiry_name;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
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
	public String getLoginUser_name() {
		return loginUser_name;
	}
	public void setLoginUser_name(String loginUser_name) {
		this.loginUser_name = loginUser_name;
	}
	public String getLoginemail_id() {
		return loginemail_id;
	}
	public void setLoginemail_id(String loginemail_id) {
		this.loginemail_id = loginemail_id;
	}
	public String getLoginUser_id() {
		return loginUser_id;
	}
	public void setLoginUser_id(String loginUser_id) {
		this.loginUser_id = loginUser_id;
	}
	public String getTypology() {
		return typology;
	}
	public void setTypology(String typology) {
		this.typology = typology;
	}
	public String getTower() {
		return tower;
	}
	public void setTower(String tower) {
		this.tower = tower;
	}
	public String getFloor_band() {
		return floor_band;
	}
	public void setFloor_band(String floor_band) {
		this.floor_band = floor_band;
	}
	public String getPayment_amount() {
		return payment_amount;
	}
	public void setPayment_amount(String payment_amount) {
		this.payment_amount = payment_amount;
	}
	public String getNo_of_units() {
		return no_of_units;
	}
	public void setNo_of_units(String no_of_units) {
		this.no_of_units = no_of_units;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	public String getEoi_status() {
		return eoi_status;
	}
	public void setEoi_status(String eoi_status) {
		this.eoi_status = eoi_status;
	}
	public String getCust_emailId() {
		return cust_emailId;
	}
	public void setCust_emailId(String cust_emailId) {
		this.cust_emailId = cust_emailId;
	}
	public String getBroker_sfid() {
		return broker_sfid;
	}
	public void setBroker_sfid(String broker_sfid) {
		this.broker_sfid = broker_sfid;
	}
	public String getProject_sfid() {
		return project_sfid;
	}
	public void setProject_sfid(String project_sfid) {
		this.project_sfid = project_sfid;
	}
	public String getContact_sfid() {
		return contact_sfid;
	}
	public void setContact_sfid(String contact_sfid) {
		this.contact_sfid = contact_sfid;
	}
	public String getEnquiry_sfid() {
		return enquiry_sfid;
	}
	public void setEnquiry_sfid(String enquiry_sfid) {
		this.enquiry_sfid = enquiry_sfid;
	}
	public String getCust_first_name() {
		return cust_first_name;
	}
	public void setCust_first_name(String cust_first_name) {
		this.cust_first_name = cust_first_name;
	}
	public String getCust_last_name() {
		return cust_last_name;
	}
	public void setCust_last_name(String cust_last_name) {
		this.cust_last_name = cust_last_name;
	}
	public String getCust_country_code() {
		return cust_country_code;
	}
	public void setCust_country_code(String cust_country_code) {
		this.cust_country_code = cust_country_code;
	}
	public String getCust_mobile() {
		return cust_mobile;
	}
	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}
	public String getCommunication_address1() {
		return communication_address1;
	}
	public void setCommunication_address1(String communication_address1) {
		this.communication_address1 = communication_address1;
	}
	public String getCommunication_address2() {
		return communication_address2;
	}
	public void setCommunication_address2(String communication_address2) {
		this.communication_address2 = communication_address2;
	}
	public String getCommunication_address3() {
		return communication_address3;
	}
	public void setCommunication_address3(String communication_address3) {
		this.communication_address3 = communication_address3;
	}
	public String getCommunication_city() {
		return communication_city;
	}
	public void setCommunication_city(String communication_city) {
		this.communication_city = communication_city;
	}
	public String getCommunication_country() {
		return communication_country;
	}
	public void setCommunication_country(String communication_country) {
		this.communication_country = communication_country;
	}
	public String getCommunication_pin() {
		return communication_pin;
	}
	public void setCommunication_pin(String communication_pin) {
		this.communication_pin = communication_pin;
	}
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	public Integer getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(Integer enquiryId) {
		this.enquiryId = enquiryId;
	}
	public String getToken_amount() {
		return token_amount;
	}
	public void setToken_amount(String token_amount) {
		this.token_amount = token_amount;
	}
	public String getBroker_name() {
		return broker_name;
	}
	public void setBroker_name(String broker_name) {
		this.broker_name = broker_name;
	}
	public String getBroker_email() {
		return broker_email;
	}
	public void setBroker_email(String broker_email) {
		this.broker_email = broker_email;
	}


}

