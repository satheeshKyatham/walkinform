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
//@Table(name = "salesforce.propstrength__request__c")
@Table(name = "salesforce.propstrength__request__c")
public class Enquiry extends CommonModel{

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
	//private String projectType;
	private String enquiryRating;
	private String enquirySource;
	private String enquiryStatus;
	private String isReferredByChannelPartnerFlag;
	private String synchronised;
	private String advertisement;
	private Contact contactId;
	private Contact contact;
	private Contact brokerContact;
	private Date dateOfSiteVisit;
	private EnquiryReport enquiryReport;
	private Date lastModifiedDate;
	private String eoiBankName;
	private String branch;
	private String  micRChequeNoNEFTRTGS;
	private Date transactionDate;
	private Double transactionAmount;
	private String transactionType;
	private String transactionID;
	private String closingmanagers;
	private String assignTo;
	@Column(name="eoi_enquiry__c") private boolean eoi_enquiry__c;
	private String eoi_preferred_unit__c;
	 
	@Column(name="priority_no__c") 
	private String   priority_no__c;
	
	@Column(name="closing_manager_name__c")
	private String closing_manager_name__c;
	@Column(name="Appointment_Done__c") private Double Appointment_Done__c;
	@Column(name="Site_Visit_Done__c") private Double Site_Visit_Done__c;
	@Column(name="appointment__c") private Double appointment__c;
	@Column(name="site_visit_requested__c") private Double site_visit_requested__c;
	@Column(name="Lost_reason_c__c") private String lost_reason_c__c;
	
	@Column(name="virtual_meeting_count__c") private Double virtual_meeting_count__c;
	
	private Phase phase;
	
	@Column(name="rating_reason__c") private String rating_reason__c;
	
	public String getClosing_manager_name__c() {
		return closing_manager_name__c;
	}

	public void setClosing_manager_name__c(String closing_manager_name__c) {
		this.closing_manager_name__c = closing_manager_name__c;
	}

	
	public String getPriority_no__c() {
		return priority_no__c;
	}

	public void setPriority_no__c(String priority_no__c) {
		this.priority_no__c = priority_no__c;
	}
	private String name;
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="eoi_preferred_unit__c")
	public String getEoi_preferred_unit__c() {
		return eoi_preferred_unit__c;
	}

	public void setEoi_preferred_unit__c(String eoi_preferred_unit__c) {
		this.eoi_preferred_unit__c = eoi_preferred_unit__c;
	}

	
    public boolean isEoi_enquiry__c() {
		return eoi_enquiry__c;
	}

	public void setEoi_enquiry__c(boolean eoi_enquiry__c) {
		this.eoi_enquiry__c = eoi_enquiry__c;
	}
	@Column(name="EOI_Tower_Series__c")
	private String EOI_Tower_Series__c;
    
    @Column(name="EOI_Preferred_Floor_Band__c")
	private String EOI_Preferred_Floor_Band__c;
    
    @Column(name="Transaction_Status__c")
	private String Transaction_Status__c;
    
    @Column(name="EOI_Remarks__c")
	private String EOI_Remarks__c;
    
    @Column(name="Preferred_Unit__c")
	private String Preferred_Unit__c;
   
    @Column(name="date_of_eoi__c")
	private Date date_of_eoi__c;

    @Column(name="Trigger_1__c")
   	private String trigger_1__c;
    @Column(name="Trigger_2__c")
   	private String trigger_2__c;
    @Column(name="Barrier_1__c")
   	private String barrier_1__c;
    @Column(name="Barrier_2__c")
   	private String barrier_2__c;
    @Column(name="Follow_up_reason__c")
   	private String follow_up_reason__c;
    @Column(name="Follow_up_Date_Time__c")
   	private Date follow_up_Date_Time__c;
    @Column(name="Verticle__c")
   	private String verticle__c;
    @Column(name="Sourcing_Managers__c")
   	private String sourcing_Managers__c;
    @Column(name="Contact_referral__c")
   	private String contact_referral__c;
    
    @Column(name="Contact_Loyalty__c")
   	private String contact_Loyalty__c;
    @Column(name="Employee_Referral__c")
   	private String employee_Referral__c;
    @Column(name="Closing_Team_Lead__c")
   	private String closing_Team_Lead__c;
    @Column(name="Sourcing_Team_Lead__c")
   	private String sourcing_Team_Lead__c;
    @Column(name="International_Sales_Manager__c")
   	private String international_Sales_Manager__c;
    
    
//branch__c
//EOI_Bank_Name__c
//MICR_Cheque_No_NEFT_RTGS__c
//Desired_Unit_type__c
//Transaction_Type__c
//Transaction_Date__c
//Transaction_ID__c
	 	
	@Column(name="closing_managers__c")
	public String getClosingmanagers() {
		return closingmanagers;
	} 
	
	public String getEOI_Tower_Series__c() {
		return EOI_Tower_Series__c;
	}

	public void setEOI_Tower_Series__c(String eOI_Tower_Series__c) {
		EOI_Tower_Series__c = eOI_Tower_Series__c;
	}

	public String getEOI_Preferred_Floor_Band__c() {
		return EOI_Preferred_Floor_Band__c;
	}

	public void setEOI_Preferred_Floor_Band__c(String eOI_Preferred_Floor_Band__c) {
		EOI_Preferred_Floor_Band__c = eOI_Preferred_Floor_Band__c;
	}

	public String getTransaction_Status__c() {
		return Transaction_Status__c;
	}

	public void setTransaction_Status__c(String transaction_Status__c) {
		Transaction_Status__c = transaction_Status__c;
	}

	public String getEOI_Remarks__c() {
		return EOI_Remarks__c;
	}

	public void setEOI_Remarks__c(String eOI_Remarks__c) {
		EOI_Remarks__c = eOI_Remarks__c;
	}

	public String getPreferred_Unit__c() {
		return Preferred_Unit__c;
	}

	public void setPreferred_Unit__c(String preferred_Unit__c) {
		this.Preferred_Unit__c = preferred_Unit__c;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getDate_of_eoi__c() {
		return date_of_eoi__c;
	}
	public void setDate_of_eoi__c(Date date_of_eoi__c) {
		this.date_of_eoi__c = date_of_eoi__c;
	}
	public void setClosingmanagers(String closingmanagers) {
		this.closingmanagers = closingmanagers;
	}
	
	@Column(name="user__c ")
	public String getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}
	@Column(name="EOI_Bank_Name__c")
	public String getEoiBankName() {
		return eoiBankName;
	}
	public void setEoiBankName(String eoiBankName) {
		this.eoiBankName = eoiBankName;
	}
	
	@Column(name="branch__c")
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	@Column(name="MICR_Cheque_No_NEFT_RTGS__c")
	public String getMicRChequeNoNEFTRTGS() {
		return micRChequeNoNEFTRTGS;
	}
	public void setMicRChequeNoNEFTRTGS(String micRChequeNoNEFTRTGS) {
		this.micRChequeNoNEFTRTGS = micRChequeNoNEFTRTGS;
	}
	
	@Column(name="Transaction_Date__c")
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	@Column(name="Transaction_Amount__c")
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	@Column(name="transaction_type__c")
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	@Column(name="Transaction_ID__c")
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
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
	
	//@Column(name="old_comments__c")
	/*public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}*/
	
	@Column(name="rating__c")/*@Column(name="propstrength__rating__c")*/
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
	
	@Column(name="Referred_Partner_Flag__c")/*@Column(name="referred_partner_flag")*/
	public String getIsReferredByChannelPartnerFlag() {
		return isReferredByChannelPartnerFlag;
	}
	public void setIsReferredByChannelPartnerFlag(String isReferredByChannelPartnerFlag) {
		this.isReferredByChannelPartnerFlag = isReferredByChannelPartnerFlag;
	}
	
	@Column(name="Synchronised__c")/*@Column(name="synchronised")*/
	public String getSynchronised() {
		return synchronised;
	}
	public void setSynchronised(String synchronised) {
		this.synchronised = synchronised;
	}
    @Column(name="Advertisement__c")/*Advertisement__c*/
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

	@ManyToOne(fetch=FetchType.LAZY)
	//@Convert(converter = DecimalConverter.class)
	/*@JoinColumn(name="propstrength__primary_contact__c",referencedColumnName="sfid")*//*@JoinColumn(name="external_contact_id",referencedColumnName="id")*/
	@JoinColumn(name="External_Contact_ID__c",referencedColumnName="id", nullable = false,columnDefinition = "int default 0")
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="External_Contact_ID__c",referencedColumnName="id")
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
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NVHC_Enquiry_ID__c",referencedColumnName="nv_hc_enquiry_id")/*@JoinColumn(name="nv_hc_enquiry_id")*/
	public EnquiryReport getEnquiryReport() {
		return enquiryReport;
	}
	public void setEnquiryReport(EnquiryReport enquiryReport) {
		this.enquiryReport = enquiryReport;
	}
	@Column(name="lastmodifieddate")
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Double getAppointment_Done__c() {
		return Appointment_Done__c;
	}

	public void setAppointment_Done__c(Double appointment_Done__c) {
		Appointment_Done__c = appointment_Done__c;
	}

	public Double getSite_Visit_Done__c() {
		return Site_Visit_Done__c;
	}

	public void setSite_Visit_Done__c(Double site_Visit_Done__c) {
		Site_Visit_Done__c = site_Visit_Done__c;
	}

	public Double getAppointment__c() {
		return appointment__c;
	}

	public void setAppointment__c(Double appointment__c) {
		this.appointment__c = appointment__c;
	}

	public Double getSite_visit_requested__c() {
		return site_visit_requested__c;
	}

	public void setSite_visit_requested__c(Double site_visit_requested__c) {
		this.site_visit_requested__c = site_visit_requested__c;
	}

	public String getLost_reason_c__c() {
		return lost_reason_c__c;
	}

	public void setLost_reason_c__c(String lost_reason_c__c) {
		this.lost_reason_c__c = lost_reason_c__c;
	}

	public String getTrigger_1__c() {
		return trigger_1__c;
	}

	public void setTrigger_1__c(String trigger_1__c) {
		this.trigger_1__c = trigger_1__c;
	}

	public String getTrigger_2__c() {
		return trigger_2__c;
	}

	public void setTrigger_2__c(String trigger_2__c) {
		this.trigger_2__c = trigger_2__c;
	}

	public String getBarrier_1__c() {
		return barrier_1__c;
	}

	public void setBarrier_1__c(String barrier_1__c) {
		this.barrier_1__c = barrier_1__c;
	}

	public String getBarrier_2__c() {
		return barrier_2__c;
	}

	public void setBarrier_2__c(String barrier_2__c) {
		this.barrier_2__c = barrier_2__c;
	}

	public String getFollow_up_reason__c() {
		return follow_up_reason__c;
	}

	public void setFollow_up_reason__c(String follow_up_reason__c) {
		this.follow_up_reason__c = follow_up_reason__c;
	}

	public Date getFollow_up_Date_Time__c() {
		return follow_up_Date_Time__c;
	}

	public void setFollow_up_Date_Time__c(Date follow_up_Date_Time__c) {
		this.follow_up_Date_Time__c = follow_up_Date_Time__c;
	}

	public String getVerticle__c() {
		return verticle__c;
	}

	public void setVerticle__c(String verticle__c) {
		this.verticle__c = verticle__c;
	}

	public String getSourcing_Managers__c() {
		return sourcing_Managers__c;
	}

	public void setSourcing_Managers__c(String sourcing_Managers__c) {
		this.sourcing_Managers__c = sourcing_Managers__c;
	}

	public String getContact_referral__c() {
		return contact_referral__c;
	}

	public void setContact_referral__c(String contact_referral__c) {
		this.contact_referral__c = contact_referral__c;
	}

	public String getContact_Loyalty__c() {
		return contact_Loyalty__c;
	}

	public void setContact_Loyalty__c(String contact_Loyalty__c) {
		this.contact_Loyalty__c = contact_Loyalty__c;
	}

	public String getEmployee_Referral__c() {
		return employee_Referral__c;
	}

	public void setEmployee_Referral__c(String employee_Referral__c) {
		this.employee_Referral__c = employee_Referral__c;
	}

	public String getClosing_Team_Lead__c() {
		return closing_Team_Lead__c;
	}

	public void setClosing_Team_Lead__c(String closing_Team_Lead__c) {
		this.closing_Team_Lead__c = closing_Team_Lead__c;
	}

	public String getSourcing_Team_Lead__c() {
		return sourcing_Team_Lead__c;
	}

	public void setSourcing_Team_Lead__c(String sourcing_Team_Lead__c) {
		this.sourcing_Team_Lead__c = sourcing_Team_Lead__c;
	}

	public Double getVirtual_meeting_count__c() {
		return virtual_meeting_count__c;
	}

	public void setVirtual_meeting_count__c(Double virtual_meeting_count__c) {
		this.virtual_meeting_count__c = virtual_meeting_count__c;
	}

	public String getInternational_Sales_Manager__c() {
		return international_Sales_Manager__c;
	}

	public void setInternational_Sales_Manager__c(String international_Sales_Manager__c) {
		this.international_Sales_Manager__c = international_Sales_Manager__c;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Project_Phase__c",referencedColumnName="sfid")
	public Phase getPhase() {
		return phase;
	}
	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public String getRating_reason__c() {
		return rating_reason__c;
	}

	public void setRating_reason__c(String rating_reason__c) {
		this.rating_reason__c = rating_reason__c;
	}
}