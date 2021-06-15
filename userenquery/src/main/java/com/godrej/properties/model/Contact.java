package com.godrej.properties.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.godrej.properties.common.model.CommonModel;

@Entity
/*@Table(name="salesforce.contact")*/
@Table(name="salesforce.contact")
public class Contact extends CommonModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer contactId;
	private String mobileNo;
	private String salutation;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String email;
	private String otherEmail;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	/*private String locality;*/
	private String city;
	/*private String country;*/
	private String pinCode;
	private String occupation;
	private String companyName;
	private String companyLocality;
	private String designation;
	private String officeCity;
	private Integer officePinCode;

	private String currentOwnershipType;

	private String currentResidenceType;
	private ChannelPartner channelPartner;
	private String ageGroup;
	/*private String industry;*/
	private String residentialState;
	private String residentialCountry;
	private String countryCode;
	private ContactReport contactReport;
	private String mobile;
	private String recordType;
	private String residenceFullAddress;
	private String mailingCountry;
	
	@Id
	/*@SequenceGenerator(name="salesforce.t_contact_seq",sequenceName="salesforce.t_contact_seq",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="salesforce.t_contact_seq")*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",updatable=false) 
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	
	@Column(name="mobilephone")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name="salutation")
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	
	@Column(name="firstName")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="lastName")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="birthday__c")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	/*@Column(name="Residential_Street1__c")
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	@Column(name="Residential_Street2__c")
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	@Column(name="Residential_Street3__c")
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}*/
	
	@Column(name="Residential_Street1__c")
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	@Column(name="Residential_Street2__c" )
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	@Column(name="Residential_Street3__c")
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	
	/*@Column(name="Locality__c")
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}*/
	
	/*@Column(name="Residential_City__c")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}*/
	
	@Column(name="Residential_City__c")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	/*@Column(name="Residential_Country__c")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}*/
	
	@Column(name="Residential_Post_Code__c")
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	/*@Column(name="Postal_Code__c")
	public Integer getPinCode() {
		return pinCode;
	}
	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}*/
	
	@Column(name="Occupation__c")
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	@Column(name="Company_Name__c")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Column(name="Company_Locality__c")
	public String getCompanyLocality() {
		return companyLocality;
	}
	public void setCompanyLocality(String companyLocality) {
		this.companyLocality = companyLocality;
	}
	
	@Column(name="Designation__c")
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	@Column(name="Company_City__c")
	public String getOfficeCity() {
		return officeCity;
	}
	public void setOfficeCity(String officeCity) {
		this.officeCity = officeCity;
	}
	
	@Column(name="Company_Pin__c")
	public Integer getOfficePinCode() {
		return officePinCode;
	}
	public void setOfficePinCode(Integer officePinCode) {
		this.officePinCode = officePinCode;
	}
	 
	@Column(name="current_residence_type__c")
	public String getCurrentResidenceType() {
		return currentResidenceType;
	}
	public void setCurrentResidenceType(String currentResidenceType) {
		this.currentResidenceType = currentResidenceType;
	}
	
	@Column(name="current_residence_ownership__c")
	public String getCurrentOwnershipType() {
		return currentOwnershipType;
	}
	public void setCurrentOwnershipType(String currentOwnershipType) {
		this.currentOwnershipType = currentOwnershipType;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="accountid",referencedColumnName="sfid")
	public ChannelPartner getChannelPartner() {
		return channelPartner;
	}
	public void setChannelPartner(ChannelPartner channelPartner) {
		this.channelPartner = channelPartner;
	}

	@Column(name="Age_A__c")
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	/*commented on 19.4.2019*/
	/*@Column(name="Industry_Code_1_ASM_Territory__c")
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}*/
	
	@Column(name="residential_State__c")
	public String getResidentialState() {
		return residentialState;
	}
	public void setResidentialState(String residentialState) {
		this.residentialState = residentialState;
	}
	
	@Column(name="residential_country__c")
	public String getResidentialCountry() {
		return residentialCountry;
	}
	public void setResidentialCountry(String residentialCountry) {
		this.residentialCountry = residentialCountry;
	}
	
/*	@Column(name="Mailing_State__c")
	public String getResidentialState() {
		return residentialState;
	}
	public void setResidentialState(String residentialState) {
		this.residentialState = residentialState;
	}*/
	
	@Column(name="Country__c")
	public String getMailingCountry() {
		return mailingCountry;
	}
	public void setMailingCountry(String mailingCountry) {
		this.mailingCountry = mailingCountry;
	}
	
	@Column(name="Country_Codes__c")
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	//@Convert(converter = DecimalConverter.class)
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="External_Contact_ID__c",referencedColumnName="nv_hc_contact_id", nullable = false,columnDefinition = "Intger")/*@JoinColumn(name="nv_hc_contact_id")*/
	public ContactReport getContactReport() {
		return contactReport;
	}
	public void setContactReport(ContactReport contactReport) {
		this.contactReport = contactReport;
	}
	@Column(name="Mobile__c")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name="recordtypeid")
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	@Column(name="PropStrength__Email_Id__c")
	public String getOtherEmail() {
		return otherEmail;
	}
	public void setOtherEmail(String otherEmail) {
		this.otherEmail = otherEmail;
	}
	@Column(name="Residence_Full_Address__c")
	public String getResidenceFullAddress() {
		return residenceFullAddress;
	}
	public void setResidenceFullAddress(String residenceFullAddress) {
		this.residenceFullAddress = residenceFullAddress;
	}
	
}
