package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;


 
@Entity
@Table(name="salesforce.nv_eoi_applicant_data")
public class CoApplicant {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="eoi_applicant_data_id") private int eoi_applicant_data_id;
	@Column(name="eoi_form_id") private int eoi_form_id;
	@Column(name="First_Name") private String First_Name;
	@Column(name="Last_Name") private String Last_Name;
	@Column(name="DOB") private String DOB;
	@Column(name="Email") private String Email;
	@Column(name="Phone_No") private String Phone_No;
	@Column(name="Enquiry_ID") private String Enquiry_ID;
	@Column(name="telephone_no") private String telephone_no;
	@Column(name="Resident") private String Resident;
	@Column(name="Contact_Address") private String Contact_Address;
	@Column(name="City") private String City;
	@Column(name="Pincode") private String Pincode;
	@Column(name="State") private String State;
	@Column(name="Country") private String Country;
	@Column(name="Permanent_Address") private String Permanent_Address;
	@Column(name="P_City") private String P_City;
	@Column(name="P_Pincode") private String P_Pincode;
	@Column(name="P_State") private String P_State;
	@Column(name="P_Country") private String P_Country;
	@Column(name="Pan_No") private String Pan_No;
	@Column(name="address_proof_type") private String address_proof_type;
	@Column(name="address_proof_No") private String address_proof_No;
	@Column(name="isactive") private String isactive;
	@Column(name="createddate") private Timestamp createddate;
	@Column(name="updateddate") private Timestamp updateddate;
	@Column(name="createdby") private int createdby;
	@Column(name="isphoto_attached") private String isphoto_attached;
	@Column(name="iskyc_attached") private String iskyc_attached;
	@Column(name="occupation_type") private String occupation_type;
	@Column(name="salary_range") private String salary_range;
	@Column(name="ispan_attached") private String ispan_attached;
	@Column(name="applicanttype") private String applicantType;
	
	@Column(name="house_no") private String house_no;
	@Column(name="p_house_no") private String p_house_no;
	
	@Column(name="kycapproval_status") private String kycapproval_status;
	@Column(name="kycreject_comment") private String kycreject_comment;
	@Column(name="panapprovalstatus") private String panapprovalstatus;
	@Column(name="addproofapprovalstatus") private String addproofapprovalstatus;
	
	@Column(name="pan_doc_storage_id") private String pan_doc_storage_id;
	@Column(name="pan_doc_storage_sfid") private String pan_doc_storage_sfid;
	@Column(name="address_doc_storage_id") private String address_doc_storage_id;
	@Column(name="address_doc_storage_sfid") private String address_doc_storage_sfid;
	@Column(name="contactid") private String contactid;
	@Column(name="projectid") private String projectid;
	@Column(name="issync") private String issync;
	
	@Transient private String issubmitted;
	@Transient public MultipartFile panupload;
	@Transient public MultipartFile addressUpload;
	@Transient public MultipartFile profileUpload;
	@Transient public String inputAddressStatus;
	@Transient public String response_msg;
	
	public String getIssubmitted() {
		return issubmitted;
	}
	public void setIssubmitted(String issubmitted) {
		this.issubmitted = issubmitted;
	}
	public int getEoi_applicant_data_id() {
		return eoi_applicant_data_id;
	}
	public void setEoi_applicant_data_id(int eoi_applicant_data_id) {
		this.eoi_applicant_data_id = eoi_applicant_data_id;
	}
	public int getEoi_form_id() {
		return eoi_form_id;
	}
	public void setEoi_form_id(int eoi_form_id) {
		this.eoi_form_id = eoi_form_id;
	}
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone_No() {
		return Phone_No;
	}
	public void setPhone_No(String phone_No) {
		Phone_No = phone_No;
	}
	public String getEnquiry_ID() {
		return Enquiry_ID;
	}
	public void setEnquiry_ID(String enquiry_ID) {
		Enquiry_ID = enquiry_ID;
	}
	public String getTelephone_no() {
		return telephone_no;
	}
	public void setTelephone_no(String telephone_no) {
		this.telephone_no = telephone_no;
	}
	public String getResident() {
		return Resident;
	}
	public void setResident(String resident) {
		Resident = resident;
	}
	public String getContact_Address() {
		return Contact_Address;
	}
	public void setContact_Address(String contact_Address) {
		Contact_Address = contact_Address;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getPincode() {
		return Pincode;
	}
	public void setPincode(String pincode) {
		Pincode = pincode;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getPermanent_Address() {
		return Permanent_Address;
	}
	public void setPermanent_Address(String permanent_Address) {
		Permanent_Address = permanent_Address;
	}
	public String getP_City() {
		return P_City;
	}
	public void setP_City(String p_City) {
		P_City = p_City;
	}
	public String getP_Pincode() {
		return P_Pincode;
	}
	public void setP_Pincode(String p_Pincode) {
		P_Pincode = p_Pincode;
	}
	public String getP_State() {
		return P_State;
	}
	public void setP_State(String p_State) {
		P_State = p_State;
	}
	public String getP_Country() {
		return P_Country;
	}
	public void setP_Country(String p_Country) {
		P_Country = p_Country;
	}
	public String getPan_No() {
		return Pan_No;
	}
	public void setPan_No(String pan_No) {
		Pan_No = pan_No;
	}
	public String getAddress_proof_type() {
		return address_proof_type;
	}
	public void setAddress_proof_type(String address_proof_type) {
		this.address_proof_type = address_proof_type;
	}
	public String getAddress_proof_No() {
		return address_proof_No;
	}
	public void setAddress_proof_No(String address_proof_No) {
		this.address_proof_No = address_proof_No;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public Timestamp getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(Timestamp updateddate) {
		this.updateddate = updateddate;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public String getIsphoto_attached() {
		return isphoto_attached;
	}
	public void setIsphoto_attached(String isphoto_attached) {
		this.isphoto_attached = isphoto_attached;
	}
	public String getIskyc_attached() {
		return iskyc_attached;
	}
	public void setIskyc_attached(String iskyc_attached) {
		this.iskyc_attached = iskyc_attached;
	}
	public String getOccupation_type() {
		return occupation_type;
	}
	public void setOccupation_type(String occupation_type) {
		this.occupation_type = occupation_type;
	}
	public String getSalary_range() {
		return salary_range;
	}
	public void setSalary_range(String salary_range) {
		this.salary_range = salary_range;
	}
	public String getIspan_attached() {
		return ispan_attached;
	}
	public void setIspan_attached(String ispan_attached) {
		this.ispan_attached = ispan_attached;
	}
	public MultipartFile getPanupload() {
		return panupload;
	}
	public void setPanupload(MultipartFile panupload) {
		this.panupload = panupload;
	}
	public MultipartFile getAddressUpload() {
		return addressUpload;
	}
	public void setAddressUpload(MultipartFile addressUpload) {
		this.addressUpload = addressUpload;
	}
	public MultipartFile getProfileUpload() {
		return profileUpload;
	}
	public void setProfileUpload(MultipartFile profileUpload) {
		this.profileUpload = profileUpload;
	}
	public String getInputAddressStatus() {
		return inputAddressStatus;
	}
	public void setInputAddressStatus(String inputAddressStatus) {
		this.inputAddressStatus = inputAddressStatus;
	}
	public String getApplicantType() {
		return applicantType;
	}
	public void setApplicantType(String applicantType) {
		this.applicantType = applicantType;
	}
	public String getResponse_msg() {
		return response_msg;
	}
	public void setResponse_msg(String response_msg) {
		this.response_msg = response_msg;
	}
	public String getHouse_no() {
		return house_no;
	}
	public void setHouse_no(String house_no) {
		this.house_no = house_no;
	}
	public String getP_house_no() {
		return p_house_no;
	}
	public void setP_house_no(String p_house_no) {
		this.p_house_no = p_house_no;
	}
	public String getKycapproval_status() {
		return kycapproval_status;
	}
	public void setKycapproval_status(String kycapproval_status) {
		this.kycapproval_status = kycapproval_status;
	}
	public String getKycreject_comment() {
		return kycreject_comment;
	}
	public void setKycreject_comment(String kycreject_comment) {
		this.kycreject_comment = kycreject_comment;
	}
	public String getPanapprovalstatus() {
		return panapprovalstatus;
	}
	public void setPanapprovalstatus(String panapprovalstatus) {
		this.panapprovalstatus = panapprovalstatus;
	}
	public String getAddproofapprovalstatus() {
		return addproofapprovalstatus;
	}
	public void setAddproofapprovalstatus(String addproofapprovalstatus) {
		this.addproofapprovalstatus = addproofapprovalstatus;
	}
	public String getPan_doc_storage_id() {
		return pan_doc_storage_id;
	}
	public void setPan_doc_storage_id(String pan_doc_storage_id) {
		this.pan_doc_storage_id = pan_doc_storage_id;
	}
	public String getPan_doc_storage_sfid() {
		return pan_doc_storage_sfid;
	}
	public void setPan_doc_storage_sfid(String pan_doc_storage_sfid) {
		this.pan_doc_storage_sfid = pan_doc_storage_sfid;
	}
	public String getAddress_doc_storage_id() {
		return address_doc_storage_id;
	}
	public void setAddress_doc_storage_id(String address_doc_storage_id) {
		this.address_doc_storage_id = address_doc_storage_id;
	}
	public String getAddress_doc_storage_sfid() {
		return address_doc_storage_sfid;
	}
	public void setAddress_doc_storage_sfid(String address_doc_storage_sfid) {
		this.address_doc_storage_sfid = address_doc_storage_sfid;
	}
	public String getContactid() {
		return contactid;
	}
	public void setContactid(String contactid) {
		this.contactid = contactid;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}


	

}
