package com.godrej.properties.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.godrej.properties.common.model.CommonModel;
/***
 * 
 * @author Satheesh Kyahtam
 *
 */
@Entity
@Table(name="salesforce.nv_hc_contact_log")
public class ContactReportLog extends CommonModel{

	private static final long serialVersionUID = 1L;
	@Column(name="nv_hc_contact_id") private Integer contactReportId;
    @Id
	@SequenceGenerator(allocationSize=1,name="salesforce.nv_hc_contact_log_seq",sequenceName="salesforce.nv_hc_contact_log_seq")
	@GeneratedValue(generator="salesforce.nv_hc_contact_log_seq",strategy=GenerationType.AUTO)
    @Column(name="nv_hc_contact_log_id") private Integer contactLogReportId;
   /* private Contact contact;
    private Contact contactSfid;*/
	@Column(name="contact_id") private Integer contactId;
	@Column(name="contact_sfid") private String contactSfid;
    /* private Enquiry enquiry;*/
	@Column(name="isupdated") private String isUpdated;
	/**
	 *  sales desk field
	 */
    @Column(name="gender") private String gender;
	@Column(name="customer_classification") private String customerClassification;
	@Column(name="ethnicity") private String ethnicity;   
    @Column(name="current_residence_configuration") private String currentResidenceType;
    @Column(name="current_residence_ownership") private String currentResidenceOwnership;
    @Column(name="employment_sector") private String occupation;
    
    /**
     * customer walkin field
     */
    @Column(name="age_group") private String ageGroup;
    @Column(name="employment_status") private String employmentStatus;
    @Column(name="office_address") private String officeAddress;
    @Column(name="office_city") private String officeCity;
    @Column(name="office_pincode") private String officePincode;
    
    @Column(name="contact_name") private String contactName;
    @Column(name="mobile_no") private String mobileNo;
    
    @Column(name="officelat") private String officelat;
    @Column(name="officelng") private String officelng;
    @Column(name="residencelat") private String reslat;
    @Column(name="residencelng") private String reslng;
    @Column(name="projectId") private String projectId;
    
    @Column(name="tokenno") private String tokenno;
    @Column(name="createddate") private Date createdDate;
    
    @Column(name="userid") private Integer userid;
    public Integer getContactLogReportId() {
		return contactLogReportId;
	}

	public void setContactLogReportId(Integer contactLogReportId) {
		this.contactLogReportId = contactLogReportId;
	}

	
	public Integer getContactReportId() {
		return contactReportId;
	}
	public void setContactReportId(Integer contactReportId) {
		this.contactReportId = contactReportId;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getCustomerClassification() {
		return customerClassification;
	}
	public void setCustomerClassification(String customerClassification) {
		this.customerClassification = customerClassification;
	}
	
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
	
	public String getCurrentResidenceType() {
		return currentResidenceType;
	}
	public void setCurrentResidenceType(String currentResidenceType) {
		this.currentResidenceType = currentResidenceType;
	}
	
	public String getCurrentResidenceOwnership() {
		return currentResidenceOwnership;
	}
	public void setCurrentResidenceOwnership(String currentResidenceOwnership) {
		this.currentResidenceOwnership = currentResidenceOwnership;
	}
	
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
	
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	
	public String getEmploymentStatus() {
		return employmentStatus;
	}
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
	
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	
	public String getOfficeCity() {
		return officeCity;
	}
	public void setOfficeCity(String officeCity) {
		this.officeCity = officeCity;
	}
	
	public String getOfficePincode() {
		return officePincode;
	}
	public void setOfficePincode(String officePincode) {
		this.officePincode = officePincode;
	}
	
	public String getIsUpdated() {
		return isUpdated;
	}
	public void setIsUpdated(String isUpdated) {
		this.isUpdated = isUpdated;
	}
	
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	
	public String getContactSfid() {
		return contactSfid;
	}
	public void setContactSfid(String contactSfid) {
		this.contactSfid = contactSfid;
	}
	
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getOfficelat() {
		return officelat;
	}
	public void setOfficelat(String officelat) {
		this.officelat = officelat;
	}
	
	
	public String getOfficelng() {
		return officelng;
	}
	public void setOfficelng(String officelng) {
		this.officelng = officelng;
	}
	
	
	public String getReslat() {
		return reslat;
	}
	public void setReslat(String reslat) {
		this.reslat = reslat;
	}
	
	
	public String getReslng() {
		return reslng;
	}
	public void setReslng(String reslng) {
		this.reslng = reslng;
	}
	
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
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	
	
	
	
	
}
