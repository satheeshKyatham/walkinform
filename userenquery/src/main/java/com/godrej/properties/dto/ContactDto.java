package com.godrej.properties.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.godrej.properties.common.dto.CommonDto;
import com.godrej.properties.constants.KeyConstants;

public class ContactDto extends CommonDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer contactId;
	private String mobileNo;
	private String salutation;
	private String firstName;
	private String lastName;
	
	@DateTimeFormat(pattern=KeyConstants.DEFAULT_DATE_FORMAT)
	private Date dateOfBirth;
	private String email;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	/*private String locality;*/
	private String city;
	private String country;
	private String pinCode;
	private String occupation;
	private String companyName;
	private String companyLocality;
	private String designation;
	private String officeCity;
	private Integer officePinCode;

	private String currentOwnershipType;

	private String currentResidenceType;

	private ChannelPartnerDto channelPartner;

	private String ageGroup;
	private String industry;
	private String residentialState;

	private String residentialCountry;

	private String countryCode;
	private ContactReportDto contactReport;
	private String mobile;
	private String recordType;
	private String otherEmail;
	private String residenceFullAddress;
	private String mailingCountry;
	public String getMailingCountry() {
		return mailingCountry;
	}
	public void setMailingCountry(String mailingCountry) {
		this.mailingCountry = mailingCountry;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	public String getMobileNo() {
		
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	/*public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}*/
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyLocality() {
		return companyLocality;
	}
	public void setCompanyLocality(String companyLocality) {
		this.companyLocality = companyLocality;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getOfficeCity() {
		return officeCity;
	}
	public void setOfficeCity(String officeCity) {
		this.officeCity = officeCity;
	}
	public Integer getOfficePinCode() {
		return officePinCode;
	}
	public void setOfficePinCode(Integer officePinCode) {
		this.officePinCode = officePinCode;
	}
	public String getCurrentResidenceType() {
		return currentResidenceType;
	}
	public void setCurrentResidenceType(String currentResidenceType) {
		this.currentResidenceType = currentResidenceType;
	}
	
	public String getCurrentOwnershipType() {
		return currentOwnershipType;
	}
	public void setCurrentOwnershipType(String currentOwnershipType) {
		this.currentOwnershipType = currentOwnershipType;
	}
	public ChannelPartnerDto getChannelPartner() {
		return channelPartner;
	}
	public void setChannelPartner(ChannelPartnerDto channelPartner) {
		this.channelPartner = channelPartner;
	}
	
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getResidentialState() {
		return residentialState;
	}
	public void setResidentialState(String residentialState) {
		this.residentialState = residentialState;
	}

	public String getResidentialCountry() {
		return residentialCountry;
	}
	public void setResidentialCountry(String residentialCountry) {
		this.residentialCountry = residentialCountry;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public ContactReportDto getContactReport() {
		return contactReport;
	}
	public void setContactReport(ContactReportDto contactReport) {
		this.contactReport = contactReport;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getOtherEmail() {
		return otherEmail;
	}
	public void setOtherEmail(String otherEmail) {
		this.otherEmail = otherEmail;
	}
	public String getResidenceFullAddress() {
		return residenceFullAddress;
	}
	public void setResidenceFullAddress(String residenceFullAddress) {
		this.residenceFullAddress = residenceFullAddress;
	}
	
}
