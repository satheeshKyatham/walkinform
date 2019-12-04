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
@Table(name="salesforce.vw_prop_other_charges")
public class ApplicantDtl implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") private int id;
	@Column(name="propstrength__type__c") private String propstrength__type__c;
	@Column(name="name") private String name;
	@Column(name="birthdate") private Date birthdate;
	@Column(name="propstrength__income_tax_permanent_account_no__c") private String propstrength__income_tax_permanent_account_no__c;
	@Column(name="aadhar_card_no__c") private String aadhar_card_no__c;
	@Column(name="email") private String email;
	@Column(name="mobile_number__c") private String mobile_number__c;
	@Column(name="propstrength__sharing_ratio__c") private double propstrength__sharing_ratio__c;
	@Column(name="passport_no__c") private String passport_no__c;
	@Column(name="nationality_a__c") private String nationality_a__c;
	@Column(name="propstrength__resident_status__c") private String propstrength__resident_status__c;
	@Column(name="residential_street1__c") private String residential_street1__c;
	@Column(name="residential_street2__c") private String residential_street2__c;
	@Column(name="residential_street3__c") private String residential_street3__c;
	@Column(name="residential_city__c") private String residential_city__c;
	@Column(name="residential_post_code__c") private String residential_post_code__c;
	@Column(name="residential_state__c") private String residential_state__c;
	@Column(name="residential_country__c") private String residential_country__c;
	@Column(name="mailing_street1__c") private String mailing_street1__c;
	@Column(name="mailing_street2__c") private String mailing_street2__c;
	@Column(name="mailing_street3__c") private String mailing_street3__c;
	@Column(name="mailing_city__c") private String mailing_city__c;
	@Column(name="mailing_state__c") private String mailing_state__c;
	@Column(name="country__c") private String country__c;
	@Column(name="postal_code__c") private String postal_code__c;
	
	@Column(name="salutation") private String salutation;
	
	public String getPropstrength__type__c() {
		return propstrength__type__c;
	}
	public void setPropstrength__type__c(String propstrength__type__c) {
		this.propstrength__type__c = propstrength__type__c;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getPropstrength__income_tax_permanent_account_no__c() {
		return propstrength__income_tax_permanent_account_no__c;
	}
	public void setPropstrength__income_tax_permanent_account_no__c(
			String propstrength__income_tax_permanent_account_no__c) {
		this.propstrength__income_tax_permanent_account_no__c = propstrength__income_tax_permanent_account_no__c;
	}
	public String getAadhar_card_no__c() {
		return aadhar_card_no__c;
	}
	public void setAadhar_card_no__c(String aadhar_card_no__c) {
		this.aadhar_card_no__c = aadhar_card_no__c;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_number__c() {
		return mobile_number__c;
	}
	public void setMobile_number__c(String mobile_number__c) {
		this.mobile_number__c = mobile_number__c;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPropstrength__sharing_ratio__c() {
		return propstrength__sharing_ratio__c;
	}
	public void setPropstrength__sharing_ratio__c(double propstrength__sharing_ratio__c) {
		this.propstrength__sharing_ratio__c = propstrength__sharing_ratio__c;
	}
	public String getPassport_no__c() {
		return passport_no__c;
	}
	public void setPassport_no__c(String passport_no__c) {
		this.passport_no__c = passport_no__c;
	}
	public String getNationality_a__c() {
		return nationality_a__c;
	}
	public void setNationality_a__c(String nationality_a__c) {
		this.nationality_a__c = nationality_a__c;
	}
	public String getPropstrength__resident_status__c() {
		return propstrength__resident_status__c;
	}
	public void setPropstrength__resident_status__c(String propstrength__resident_status__c) {
		this.propstrength__resident_status__c = propstrength__resident_status__c;
	}
	public String getResidential_street1__c() {
		return residential_street1__c;
	}
	public void setResidential_street1__c(String residential_street1__c) {
		this.residential_street1__c = residential_street1__c;
	}
	public String getResidential_street2__c() {
		return residential_street2__c;
	}
	public void setResidential_street2__c(String residential_street2__c) {
		this.residential_street2__c = residential_street2__c;
	}
	public String getResidential_street3__c() {
		return residential_street3__c;
	}
	public void setResidential_street3__c(String residential_street3__c) {
		this.residential_street3__c = residential_street3__c;
	}
	public String getResidential_city__c() {
		return residential_city__c;
	}
	public void setResidential_city__c(String residential_city__c) {
		this.residential_city__c = residential_city__c;
	}
	public String getResidential_post_code__c() {
		return residential_post_code__c;
	}
	public void setResidential_post_code__c(String residential_post_code__c) {
		this.residential_post_code__c = residential_post_code__c;
	}
	public String getResidential_state__c() {
		return residential_state__c;
	}
	public void setResidential_state__c(String residential_state__c) {
		this.residential_state__c = residential_state__c;
	}
	public String getResidential_country__c() {
		return residential_country__c;
	}
	public void setResidential_country__c(String residential_country__c) {
		this.residential_country__c = residential_country__c;
	}
	public String getMailing_street1__c() {
		return mailing_street1__c;
	}
	public void setMailing_street1__c(String mailing_street1__c) {
		this.mailing_street1__c = mailing_street1__c;
	}
	public String getMailing_street2__c() {
		return mailing_street2__c;
	}
	public void setMailing_street2__c(String mailing_street2__c) {
		this.mailing_street2__c = mailing_street2__c;
	}
	public String getMailing_street3__c() {
		return mailing_street3__c;
	}
	public void setMailing_street3__c(String mailing_street3__c) {
		this.mailing_street3__c = mailing_street3__c;
	}
	public String getMailing_city__c() {
		return mailing_city__c;
	}
	public void setMailing_city__c(String mailing_city__c) {
		this.mailing_city__c = mailing_city__c;
	}
	public String getMailing_state__c() {
		return mailing_state__c;
	}
	public void setMailing_state__c(String mailing_state__c) {
		this.mailing_state__c = mailing_state__c;
	}
	public String getCountry__c() {
		return country__c;
	}
	public void setCountry__c(String country__c) {
		this.country__c = country__c;
	}
	public String getPostal_code__c() {
		return postal_code__c;
	}
	public void setPostal_code__c(String postal_code__c) {
		this.postal_code__c = postal_code__c;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
}