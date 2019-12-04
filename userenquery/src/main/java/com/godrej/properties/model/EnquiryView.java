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
import javax.persistence.Transient;

@Entity
@Table(name="salesforce.vm_propstrength__request__c")
public class EnquiryView {
   


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer enquiryId;
	private String walkInSourceDetail;
	private Date dateOfEnquiry;
	private String walkInSource;
	private String desiredUnitType;
	private String purpose;
	private String budget;
	private String requiredPossesionTimeLine;
	private Project project;
	private String otherChannelPartner;
	private String isReferredByChannelPartner;
	private ChannelPartner channelPartner;
	private String projectType;
	private String enquiryRating;
	private String enquirySource;
	private String enquiryStatus;
	@Transient
	private String isReferredByChannelPartnerFlag;
	@Transient
	private String synchronised;
	private String advertisement;
	private Contact contactId;
	@Transient
	private Contact contact;
	private Contact brokerContact;
	private Date dateOfSiteVisit;
	@Transient
	private EnquiryReport enquiryReport;
	
	@Id
	/*@SequenceGenerator(name = "salesforce.t_enquiry_seq", sequenceName = "salesforce.t_enquiry_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salesforce.t_enquiry_seq")*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(Integer enquiryId) {
		this.enquiryId = enquiryId;
	}
	
	@Column(name="Walk_In_Remarks__c")/*@Column(name="details_of_newspaper_website_hoarding__c")*/
	public String getWalkInSourceDetail() {
		return walkInSourceDetail;
	}
	public void setWalkInSourceDetail(String walkInSourceDetail) {
		this.walkInSourceDetail = walkInSourceDetail;
	}
	
	@Column(name="date_of_enquiry__c")
	public Date getDateOfEnquiry() {
		return dateOfEnquiry;
	}
	public void setDateOfEnquiry(Date dateOfEnquiry) {
		this.dateOfEnquiry = dateOfEnquiry;
	}
	
	@Column(name="walk_in_source__c")
	public String getWalkInSource() {
		return walkInSource;
	}
	public void setWalkInSource(String walkInSource) {
		this.walkInSource = walkInSource;
	}
	
	@Column(name="Desired_Unit_type__c")
	public String getDesiredUnitType() {
		return desiredUnitType;
	}
	public void setDesiredUnitType(String desiredUnitType) {
		this.desiredUnitType = desiredUnitType;
	}
	
	@Column(name="Is_purchase_for_Self_Use_or_Investment__c")/*@Column(name="purpose__c")*/
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PropStrength__Project__c",referencedColumnName="sfid")//project18digit__c
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	@Column(name="comments__c")
	public String getOtherChannelPartner() {
		return otherChannelPartner;
	}
	public void setOtherChannelPartner(String otherChannelPartner) {
		this.otherChannelPartner = otherChannelPartner;
	}
	
	@Column(name="propstrength__enquiry_type__c")
	public String getIsReferredByChannelPartner() {
		return isReferredByChannelPartner;
	}
	public void setIsReferredByChannelPartner(String isReferredByChannelPartner) {
		this.isReferredByChannelPartner = isReferredByChannelPartner;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="propstrength__broker_account__c",referencedColumnName="sfid")
	public ChannelPartner getChannelPartner() {
		return channelPartner;
	}
	public void setChannelPartner(ChannelPartner channelPartner) {
		this.channelPartner = channelPartner;
	}
	
    @Column(name="budget__c")
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	
	@Column(name="required_possession_timeline__c")
	public String getRequiredPossesionTimeLine() {
		return requiredPossesionTimeLine;
	}
	public void setRequiredPossesionTimeLine(String requiredPossesionTimeLine) {
		this.requiredPossesionTimeLine = requiredPossesionTimeLine;
	}
	
	@Column(name="old_comments__c")
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	@Column(name="propstrength__rating__c")
	public String getEnquiryRating() {
		return enquiryRating;
	}
	public void setEnquiryRating(String enquiryRating) {
		this.enquiryRating = enquiryRating;
	}
	@Column(name="propstrength__request_source__c")
	public String getEnquirySource() {
		return enquirySource;
	}
	public void setEnquirySource(String enquirySource) {
		this.enquirySource = enquirySource;
	}
	
	@Column(name="propstrength__request_status__c")
	public String getEnquiryStatus() {
		return enquiryStatus;
	}
	public void setEnquiryStatus(String enquiryStatus) {
		this.enquiryStatus = enquiryStatus;
	}
	
	@Column(name="referred_partner_flag")
	public String getIsReferredByChannelPartnerFlag() {
		return isReferredByChannelPartnerFlag;
	}
	public void setIsReferredByChannelPartnerFlag(String isReferredByChannelPartnerFlag) {
		this.isReferredByChannelPartnerFlag = isReferredByChannelPartnerFlag;
	}
	
	@Column(name="synchronised")
	public String getSynchronised() {
		return synchronised;
	}
	public void setSynchronised(String synchronised) {
		this.synchronised = synchronised;
	}
    @Column(name="Advertisement__c")//Advertisement__c
    public String getAdvertisement() {
 		return advertisement;
	}
	public void setAdvertisement(String advertisement) {
		this.advertisement = advertisement;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="propstrength__broker_contact__c",referencedColumnName="sfid")
	public Contact getBrokerContact() {
		return brokerContact;
	}
	public void setBrokerContact(Contact brokerContact) {
		this.brokerContact = brokerContact;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="external_contact_id",referencedColumnName="id")
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="propstrength__primary_contact__c",referencedColumnName="sfid")
	public Contact getContactId() {
		return contactId;
	}
	public void setContactId(Contact contactId) {
		this.contactId = contactId;
	}
	@Column(name="Date_of_Site_Visit__c")
	public Date getDateOfSiteVisit() {
		return dateOfSiteVisit;
	}
	public void setDateOfSiteVisit(Date dateOfSiteVisit) {
		this.dateOfSiteVisit = dateOfSiteVisit;
	}
	@Column(name="nv_hc_enquiry_id")
	public EnquiryReport getEnquiryReport() {
		return enquiryReport;
	}
	public void setEnquiryReport(EnquiryReport enquiryReport) {
		this.enquiryReport = enquiryReport;
	}
	

	
}
