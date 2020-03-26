package com.godrej.properties.model;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
//@Table(name="salesforce.vw_eoi_data_uat")
@Table(name="salesforce.vw_eoi_data")
public class EnqJourneyDtl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id") private int id;
	@Column(name="enquiryid") private String enquiryid;
	@Column(name="enquiry_sfid") private String enquiry_sfid;
	@Column(name="Date_Of_EOI__c") private Date date_eoi;
	@Column(name="application_name") private String application_name;
	@Column(name="firstname") private String firstname;
	@Column(name="lastname") private String lastname;
	@Column(name="email_id") private String email_id;
	@Column(name="phone_number") private String phone_number;
	@Column(name="telephone") private String telephone;
	@Column(name="dob") private String dob;
	@Column(name="resident") private String resident;
	@Column(name="contact_address") private String contact_address;
	@Column(name="city") private String city;
	@Column(name="pincode") private String pincode;
	@Column(name="state") private String state;
	@Column(name="country") private String country;
	@Column(name="permanent_address") private String permanent_address;
	@Column(name="p_city") private String p_city;
	@Column(name="p_pincode") private String p_pincode;
	@Column(name="p_state") private String p_state;
	@Column(name="p_country") private String p_country;
	@Column(name="pan_no") private String pan_no;
	@Column(name="aadhar_no") private String aadhar_no;
	@Column(name="passport_no") private String passport_no;
	//transit
//	@Column(name="other_address_proof") private String other_address_proof;
	@Transient private String other_address_proof;
	//transit
//	@Column(name="photograph_attached") private String photograph_attached;
	@Transient private String photograph_attached;
	//transit
//	@Column(name="kyc_attached") private String kyc_attached;
	@Transient private String kyc_attached;
	
	@Column(name="tower_and_series") private String tower_and_series;
	@Column(name="typology") private String typology;
	@Column(name="floor_band") private String floor_band;
	@Column(name="source") private String source;
	@Column(name="cp_name") private String cp_name;
	//transit@Transient
//	@Column(name="direct_sub_source") private String direct_sub_source;
	@Transient private String direct_sub_source;
	@Column(name="payment_mode") private String payment_mode;
	@Column(name="eoi_amount") private String eoi_amount;
	@Column(name="date_swipe_cheque") private Date date_swipe_cheque;
	@Column(name="cheque_no") private String cheque_no;
	@Column(name="bank_name") private String bank_name;
	@Column(name="digits_card") private String digits_card;
	@Column(name="auth_id") private String auth_id;
	@Column(name="remarks") private String remarks;
	@Column(name="closing_manager") private String closing_manager;
	@Column(name="unit_number") private String unit_number;
	@Column(name="isupdated_prime") private String isupdated_prime;
	@Column(name="issubmitted") private String issubmitted;
	
	@Column(name="projectname") private String projectname;
	@Column(name="projectsfid") private String projectsfid;
	@Column(name="contactsfid") private String contactsfid;
	@Column(name="enquirysfid") private String enquirysfid;
	@Column(name="unit_facing") private String unit_facing;
	@Column(name="PropStrength__Request_Status__c") private String PropStrength__Request_Status__c;
	@Column(name="propstrength__project__c") private String propstrength__project__c;
	
	@Transient private File panupload;
	@Transient private File addressUpload;
	
	/*@Column(name="barcode_str") private String barcode_str;
	@Column(name="barcode_numeric") private String barcode_numeric;
	@Column(name="barcode_location") private String barcode_location;
	@Column(name="token_no", nullable = false) private Integer token_no;
	@Column(name="userdocid", nullable = false) private String userdocid;*/
	@Transient private String barcode_str;
	@Transient private String barcode_numeric;
	@Transient private String barcode_location;
	@Transient private Integer token_no;
	@Transient private String userdocid;
	
	
	
	public String getUserdocid() {
		return userdocid;
	}
	public void setUserdocid(String userdocid) {
		this.userdocid = userdocid;
	}
	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}
	
	public String getIssubmitted() {
		return issubmitted;
	}
	public void setIssubmitted(String issubmitted) {
		this.issubmitted = issubmitted;
	}
	public String getBarcode_str() {
		return barcode_str;
	}
	public void setBarcode_str(String barcode_str) {
		this.barcode_str = barcode_str;
	}
	public String getBarcode_numeric() {
		return barcode_numeric;
	}
	public void setBarcode_numeric(String barcode_numeric) {
		this.barcode_numeric = barcode_numeric;
	}
	public String getBarcode_location() {
		return barcode_location;
	}
	public void setBarcode_location(String barcode_location) {
		this.barcode_location = barcode_location;
	}
	public int getToken_no() {
		return token_no;
	}
	public void setToken_no(int token_no) {
		this.token_no = token_no;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEnquiryid() {
		return enquiryid;
	}
	public void setEnquiryid(String enquiryid) {
		this.enquiryid = enquiryid;
	}
	public Date getDate_eoi() {
		return date_eoi;
	}
	public void setDate_eoi(Date date_eoi) {
		this.date_eoi = date_eoi;
	}
	public String getApplication_name() {
		return application_name;
	}
	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getResident() {
		return resident;
	}
	public void setResident(String resident) {
		this.resident = resident;
	}
	public String getContact_address() {
		return contact_address;
	}
	public void setContact_address(String contact_address) {
		this.contact_address = contact_address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPermanent_address() {
		return permanent_address;
	}
	public void setPermanent_address(String permanent_address) {
		this.permanent_address = permanent_address;
	}
	public String getP_city() {
		return p_city;
	}
	public void setP_city(String p_city) {
		this.p_city = p_city;
	}
	public String getP_pincode() {
		return p_pincode;
	}
	public void setP_pincode(String p_pincode) {
		this.p_pincode = p_pincode;
	}
	public String getP_state() {
		return p_state;
	}
	public void setP_state(String p_state) {
		this.p_state = p_state;
	}
	public String getP_country() {
		return p_country;
	}
	public void setP_country(String p_country) {
		this.p_country = p_country;
	}
	public String getPan_no() {
		return pan_no;
	}
	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}
	public String getAadhar_no() {
		return aadhar_no;
	}
	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}
	public String getPassport_no() {
		return passport_no;
	}
	public void setPassport_no(String passport_no) {
		this.passport_no = passport_no;
	}
	public String getOther_address_proof() {
		return other_address_proof;
	}
	public void setOther_address_proof(String other_address_proof) {
		this.other_address_proof = other_address_proof;
	}
	public String getPhotograph_attached() {
		return photograph_attached;
	}
	public void setPhotograph_attached(String photograph_attached) {
		this.photograph_attached = photograph_attached;
	}
	public String getKyc_attached() {
		return kyc_attached;
	}
	public void setKyc_attached(String kyc_attached) {
		this.kyc_attached = kyc_attached;
	}
	public String getTower_and_series() {
		return tower_and_series;
	}
	public void setTower_and_series(String tower_and_series) {
		this.tower_and_series = tower_and_series;
	}
	public String getTypology() {
		return typology;
	}
	public void setTypology(String typology) {
		this.typology = typology;
	}
	public String getFloor_band() {
		return floor_band;
	}
	public void setFloor_band(String floor_band) {
		this.floor_band = floor_band;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCp_name() {
		return cp_name;
	}
	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}
	public String getDirect_sub_source() {
		return direct_sub_source;
	}
	public void setDirect_sub_source(String direct_sub_source) {
		this.direct_sub_source = direct_sub_source;
	}
	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	public String getEoi_amount() {
		return eoi_amount;
	}
	public void setEoi_amount(String eoi_amount) {
		this.eoi_amount = eoi_amount;
	}
	public Date getDate_swipe_cheque() {
		return date_swipe_cheque;
	}
	public void setDate_swipe_cheque(Date date_swipe_cheque) {
		this.date_swipe_cheque = date_swipe_cheque;
	}
	public String getCheque_no() {
		return cheque_no;
	}
	public void setCheque_no(String cheque_no) {
		this.cheque_no = cheque_no;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getDigits_card() {
		return digits_card;
	}
	public void setDigits_card(String digits_card) {
		this.digits_card = digits_card;
	}

	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getClosing_manager() {
		return closing_manager;
	}
	public void setClosing_manager(String closing_manager) {
		this.closing_manager = closing_manager;
	}
	public String getUnit_number() {
		return unit_number;
	}
	public void setUnit_number(String unit_number) {
		this.unit_number = unit_number;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public File getPanupload() {
		return panupload;
	}
	public void setPanupload(File panupload) {
		this.panupload = panupload;
	}
	public File getAddressUpload() {
		return addressUpload;
	}
	public void setAddressUpload(File addressUpload) {
		this.addressUpload = addressUpload;
	}
	public String getIsupdated_prime() {
		return isupdated_prime;
	}
	public void setIsupdated_prime(String isupdated_prime) {
		this.isupdated_prime = isupdated_prime;
	}
	public void setToken_no(Integer token_no) {
		this.token_no = token_no;
	}
	public String getAuth_id() {
		return auth_id;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getProjectsfid() {
		return projectsfid;
	}
	public void setProjectsfid(String projectsfid) {
		this.projectsfid = projectsfid;
	}
	public String getContactsfid() {
		return contactsfid;
	}
	public void setContactsfid(String contactsfid) {
		this.contactsfid = contactsfid;
	}
	public String getEnquirysfid() {
		return enquirysfid;
	}
	public void setEnquirysfid(String enquirysfid) {
		this.enquirysfid = enquirysfid;
	}
	public String getUnit_facing() {
		return unit_facing;
	}
	public void setUnit_facing(String unit_facing) {
		this.unit_facing = unit_facing;
	}
	public String getPropStrength__Request_Status__c() {
		return PropStrength__Request_Status__c;
	}
	public void setPropStrength__Request_Status__c(String propStrength__Request_Status__c) {
		PropStrength__Request_Status__c = propStrength__Request_Status__c;
	}
	public String getPropstrength__project__c() {
		return propstrength__project__c;
	}
	public void setPropstrength__project__c(String propstrength__project__c) {
		this.propstrength__project__c = propstrength__project__c;
	}
	public String getEnquiry_sfid() {
		return enquiry_sfid;
	}
	public void setEnquiry_sfid(String enquiry_sfid) {
		this.enquiry_sfid = enquiry_sfid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	
}
