package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.godrej.properties.common.model.CommonModel;
/***
 * 
 * @author Varsha Patil
 *
 */
@Entity
@Table(name="salesforce.nv_hc_contact")
public class ContactReport extends CommonModel{

	private static final long serialVersionUID = 1L;
	private Integer contactReportId;
   /* private Contact contact;
    private Contact contactSfid;*/
	private Integer contactId;
	private String contactSfid;
    /* private Enquiry enquiry;*/
    private String isUpdated;
	/**
	 *  sales desk field
	 */
	private String gender;
	private String customerClassification;
    private String ethnicity;   
    private String currentResidenceType;
    private String currentResidenceOwnership;
    private String occupation;
    
    /**
     * customer walkin field
     */
    private String ageGroup;
    private String employmentStatus;
    private String officeAddress;
    private String officeCity;
    private String officePincode;
    
    private String contactName;
    private String mobileNo;
    
    private String officelat;
    private String officelng;
    private String reslat;
    private String reslng;
    private String projectId;
    @Column(name="tokenno")
    private String tokenno;
    @Column(name="userid")
    private Integer userid;
    
    
    @Id
	@SequenceGenerator(allocationSize=1,name="salesforce.nv_hc_contact_seq",sequenceName="salesforce.nv_hc_contact_seq")
	@GeneratedValue(generator="salesforce.nv_hc_contact_seq",strategy=GenerationType.AUTO)
	@Column(name="nv_hc_contact_id")
	public Integer getContactReportId() {
		return contactReportId;
	}
	public void setContactReportId(Integer contactReportId) {
		this.contactReportId = contactReportId;
	}
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="customer_classification")
	public String getCustomerClassification() {
		return customerClassification;
	}
	public void setCustomerClassification(String customerClassification) {
		this.customerClassification = customerClassification;
	}
	@Column(name="ethnicity")
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	/*@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="enquiry_id",referencedColumnName="id")
	public Enquiry getEnquiry() {
		return enquiry;
	}
	public void setEnquiry(Enquiry enquiry) {
		this.enquiry = enquiry;
	}*/
	@Column(name="current_residence_configuration")
	public String getCurrentResidenceType() {
		return currentResidenceType;
	}
	public void setCurrentResidenceType(String currentResidenceType) {
		this.currentResidenceType = currentResidenceType;
	}
	@Column(name="current_residence_ownership")
	public String getCurrentResidenceOwnership() {
		return currentResidenceOwnership;
	}
	public void setCurrentResidenceOwnership(String currentResidenceOwnership) {
		this.currentResidenceOwnership = currentResidenceOwnership;
	}
	@Column(name="employment_sector")
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	/*@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contact_id",referencedColumnName="id")
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contact_sfid",referencedColumnName="sfid")
	public Contact getContactSfid() {
		return contactSfid;
	}
	public void setContactSfid(Contact contactSfid) {
		this.contactSfid = contactSfid;
	}*/
	@Column(name="age_group")
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	@Column(name="employment_status")
	public String getEmploymentStatus() {
		return employmentStatus;
	}
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
	@Column(name="office_address")
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	@Column(name="office_city")
	public String getOfficeCity() {
		return officeCity;
	}
	public void setOfficeCity(String officeCity) {
		this.officeCity = officeCity;
	}
	@Column(name="office_pincode")
	public String getOfficePincode() {
		return officePincode;
	}
	public void setOfficePincode(String officePincode) {
		this.officePincode = officePincode;
	}
	@Column(name="isupdated")
	public String getIsUpdated() {
		return isUpdated;
	}
	public void setIsUpdated(String isUpdated) {
		this.isUpdated = isUpdated;
	}
	@Column(name="contact_id")
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	@Column(name="contact_sfid")
	public String getContactSfid() {
		return contactSfid;
	}
	public void setContactSfid(String contactSfid) {
		this.contactSfid = contactSfid;
	}
	@Column(name="contact_name")
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name="officelat")
	public String getOfficelat() {
		return officelat;
	}
	public void setOfficelat(String officelat) {
		this.officelat = officelat;
	}
	
	@Column(name="officelng")
	public String getOfficelng() {
		return officelng;
	}
	public void setOfficelng(String officelng) {
		this.officelng = officelng;
	}
	
	@Column(name="residencelat")
	public String getReslat() {
		return reslat;
	}
	public void setReslat(String reslat) {
		this.reslat = reslat;
	}
	
	@Column(name="residencelng")
	public String getReslng() {
		return reslng;
	}
	public void setReslng(String reslng) {
		this.reslng = reslng;
	}
	@Column(name="projectId")
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getTokenno() {
		return tokenno;
	}
	public void setTokenno(String tokenno) {
		this.tokenno = tokenno;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	
	
	
	
	
}
