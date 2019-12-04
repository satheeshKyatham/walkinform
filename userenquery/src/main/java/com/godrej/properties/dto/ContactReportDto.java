package com.godrej.properties.dto;

import javax.persistence.Column;

import com.godrej.properties.common.dto.CommonDto;
import com.godrej.properties.common.utilities.CommonValidations;
/**
 * 
 * @author Varsha Patil
 *
 */
public class ContactReportDto extends CommonDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer contactReportId;
	private String gender;
	private String customerClassification;
    private String ethnicity;
    /*private EnquiryDto enquiry;*/
    private String currentResidenceType;
    private String currentResidenceOwnership;
    private String occupation;
    /*private ContactDto contact;
    private ContactDto contactSfid;*/
    private Integer contactId;
    private String contactSfid;    
    
    private String ageGroup;
    private String employmentStatus;
    private String officeAddress;
    private String officeCity;
    private String officePincode;
    private String isUpdated;

    private String contactName;
    private String mobileNo;
    
    private String officelat;
    private String officelng;
    private String reslat;
    private String reslng;
    private String projectId;
    private String tokenno;
    private Integer userid;
    
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
	/*public EnquiryDto getEnquiry() {
		return enquiry;
	}
	public void setEnquiry(EnquiryDto enquiry) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getIsUpdated() {
		if(CommonValidations.isEmpty(isUpdated)){
			return "N";
		}
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
	
	@Column(name="reslng")
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
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
    
    
	
	
	
	
	
	
	
	
	
    
}
