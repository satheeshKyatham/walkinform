package com.godrej.properties.dto;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

import com.godrej.properties.common.dto.CommonDto;
import com.godrej.properties.common.utilities.CommonValidations;
import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.model.ProjectLaunch;

public class EnquiryDto extends CommonDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer enquiryId;
	private String projectName;
	private String walkInSourceDetail;
	private Date dateOfEnquiry;
	private String walkInSource;
	private String desiredUnitType;
	private String currentResidenceType;
	private String currentOwnershipType;
	private String purpose;
	private String budget;
	private String requiredPossesionTimeLine;
	private ProjectDto project;
	private ContactDto contact;
	private String otherChannelPartner;
	private String cpComment;
	private String isReferredByChannelPartner;
	private String region;
	private ChannelPartnerDto channelPartner;
	private ContactDto brokerContact;

	private String projectType;

	private String enquiryRating;

	private String enquirySource;

	private ContactDto contactId;
	
	private String enquiryStatus;
	private String isReferredByChannelPartnerFlag;
	
	private boolean eoi_enquiry__c;
	
	

	private String synchronised;
	private String advertisement;
	private Date dateOfSiteVisit;
    private EnquiryReportDto enquiryReport;
   
	private Date lastModifiedDate;
	private String nonEdit;
	private String enquiryNonEditComment;
	private String eoiBankName;
	private String branch;
	private String  micRChequeNoNEFTRTGS;
	@DateTimeFormat(pattern=KeyConstants.DEFAULT_DATE_FORMAT)
	private Date transactionDate;
	private Double transactionAmount;
	private String transactionType;
	private String transactionID;
	private String nefttransactionID;
	
	private String closingmanagers;
	private String assignTo;
	private String eoi_preferred_unit__c;	
	private String   priority_no__c;
	private String closing_manager_name__c;
	
	private Double   Appointment_Done__c;
	private Double Site_Visit_Done__c;
	
	private Double appointment__c;
	private Double site_visit_requested__c;
	private String lost_reason_c__c;
	
	private String trigger_1__c;
   	private String trigger_2__c;
   	private String barrier_1__c;
   	private String barrier_2__c;
	
   	private String follow_up_reason__c;
   	private Date follow_up_Date_Time__c;
   	private String verticle__c;
   	private String sourcing_Managers__c;
   	private String referredbyDto;
   	private String closingManagerDto;
   	
   	private String contact_referral__c;
   	private String contact_Loyalty__c;
   	private String employee_Referral__c;
	private String sourcingmanger_email;
	
	private String EOI_Tower_Series__c;
    private String EOI_Preferred_Floor_Band__c;
    private String Transaction_Status__c;
    private String EOI_Remarks__c;
    private String Preferred_Unit__c;
    private Date date_of_eoi__c;

    private String closing_Team_Lead__c;
    private String sourcing_Team_Lead__c;
    private String closing_Team_Lead_email;
    private String sourcing_Team_Lead_email;
    private String closingTeamLeadDto;
    private String sourcingTeamLeadDto;
    
    private String name;
    private String isAllowCCPaymentGateway;
    private Double virtual_meeting_count__c;
   	
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
 	public String getEoi_preferred_unit__c() {
		return eoi_preferred_unit__c;
	}
	public void setEoi_preferred_unit__c(String eoi_preferred_unit__c) {
		this.eoi_preferred_unit__c = eoi_preferred_unit__c;
	}
	public boolean getEoi_enquiry__c() {
		return eoi_enquiry__c;
	}
	public void setEoi_enquiry__c(boolean eoi_enquiry__c) {
		this.eoi_enquiry__c = eoi_enquiry__c;
	}
	public String getNefttransactionID() {
		return nefttransactionID;
	}
	public void setNefttransactionID(String nefttransactionID) {
		this.nefttransactionID = nefttransactionID;
	}
	
	 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public Date getDate_of_eoi__c() {
		return date_of_eoi__c;
	}
	public void setDate_of_eoi__c(Date date_of_eoi__c) {
		this.date_of_eoi__c = date_of_eoi__c;
	}
	public String getCpComment() {
		return cpComment;
	}
	public void setCpComment(String cpComment) {
		this.cpComment = cpComment;
	}
	public String getClosingmanagers() {
		return closingmanagers;
	}
	public void setClosingmanagers(String closingmanagers) {
		this.closingmanagers = closingmanagers;
	}
	public String getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}
	public String getMicRChequeNoNEFTRTGS() {
		return micRChequeNoNEFTRTGS;
	}
	public void setMicRChequeNoNEFTRTGS(String micRChequeNoNEFTRTGS) {
		this.micRChequeNoNEFTRTGS = micRChequeNoNEFTRTGS;
	}
	public String getEoiBankName() {
		return eoiBankName;
	}
	public void setEoiBankName(String eoiBankName) {
		this.eoiBankName = eoiBankName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	 
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public Integer getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(Integer enquiryId) {
		this.enquiryId = enquiryId;
	}
	public String getWalkInSourceDetail() {
		return walkInSourceDetail;
	}
	public void setWalkInSourceDetail(String walkInSourceDetail) {
		this.walkInSourceDetail = walkInSourceDetail;
	}
	public Date getDateOfEnquiry() {
		return dateOfEnquiry;
	}
	public void setDateOfEnquiry(Date dateOfEnquiry) {
		this.dateOfEnquiry = dateOfEnquiry;
	}
	public String getWalkInSource() {
		return walkInSource;
	}
	public void setWalkInSource(String walkInSource) {
		this.walkInSource = walkInSource;
	}
	public String getDesiredUnitType() {
		return desiredUnitType;
	}
	public void setDesiredUnitType(String desiredUnitType) {
		this.desiredUnitType = desiredUnitType;
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
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public ProjectDto getProject() {
		return project;
	}
	public void setProject(ProjectDto project) {
		this.project = project;
	}
	public ContactDto getContact() {
		return contact;
	}
	public void setContact(ContactDto contact) {
		this.contact = contact;
	}
	public String getOtherChannelPartner() {
		return otherChannelPartner;
	}
	public void setOtherChannelPartner(String otherChannelPartner) {
		this.otherChannelPartner = otherChannelPartner;
	}
	public String getIsReferredByChannelPartner() {
		return isReferredByChannelPartner;
	}
	public void setIsReferredByChannelPartner(String isReferredByChannelPartner) {
		this.isReferredByChannelPartner = isReferredByChannelPartner;
	}
	public ChannelPartnerDto getChannelPartner() {
		return channelPartner;
	}
	public void setChannelPartner(ChannelPartnerDto channelPartner) {
		this.channelPartner = channelPartner;
	}
	public ContactDto getBrokerContact() {
		return brokerContact;
	}
	public void setBrokerContact(ContactDto brokerContact) {
		this.brokerContact = brokerContact;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRequiredPossesionTimeLine() {
		return requiredPossesionTimeLine;
	}
	public void setRequiredPossesionTimeLine(String requiredPossesionTimeLine) {
		this.requiredPossesionTimeLine = requiredPossesionTimeLine;
	} 
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	public String getEnquiryRating() {
		return enquiryRating;
	}
	public void setEnquiryRating(String enquiryRating) {
		this.enquiryRating = enquiryRating;
	}
	
	public String getEnquirySource() {
		return enquirySource;
	}
	public void setEnquirySource(String enquirySource) {
		this.enquirySource = enquirySource;
	} 
	
	public ContactDto getContactId() {
		return contactId;
	}
	public void setContactId(ContactDto contactId) {
		this.contactId = contactId;
	}
	public String getEnquiryStatus() {
		return enquiryStatus;
	}
	public void setEnquiryStatus(String enquiryStatus) {
		this.enquiryStatus = enquiryStatus;
	}
	public String getIsReferredByChannelPartnerFlag() {
		return isReferredByChannelPartnerFlag;
	}
	public void setIsReferredByChannelPartnerFlag(String isReferredByChannelPartnerFlag) {
		this.isReferredByChannelPartnerFlag = isReferredByChannelPartnerFlag;
	}
	public String getSynchronised() {
		if(CommonValidations.isEmpty(synchronised)){
			return "N";
		}
		return synchronised;
	}
	public void setSynchronised(String synchronised) {
		this.synchronised = synchronised;
	}
	public String getAdvertisement() {
		return advertisement;
	}
	public void setAdvertisement(String advertisement) {
		this.advertisement = advertisement;
	}
	public Date getDateOfSiteVisit() {
		return dateOfSiteVisit;
	}
	public void setDateOfSiteVisit(Date dateOfSiteVisit) {
		this.dateOfSiteVisit = dateOfSiteVisit;
	}
	public EnquiryReportDto getEnquiryReport() {
		return enquiryReport;
	}
	public void setEnquiryReport(EnquiryReportDto enquiryReport) {
		this.enquiryReport = enquiryReport;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getNonEdit() {
		return nonEdit;
	}
	public void setNonEdit(String nonEdit) {
		this.nonEdit = nonEdit;
	}
	public String getEnquiryNonEditComment() {
		return enquiryNonEditComment;
	}
	public void setEnquiryNonEditComment(String enquiryNonEditComment) {
		this.enquiryNonEditComment = enquiryNonEditComment;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public String getReferredbyDto() {
		return referredbyDto;
	}

	public void setReferredbyDto(String referredbyDto) {
		this.referredbyDto = referredbyDto;
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

	public String getClosingManagerDto() {
		return closingManagerDto;
	}

	public void setClosingManagerDto(String closingManagerDto) {
		this.closingManagerDto = closingManagerDto;
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

	public String getSourcingmanger_email() {
		return sourcingmanger_email;
	}

	public void setSourcingmanger_email(String sourcingmanger_email) {
		this.sourcingmanger_email = sourcingmanger_email;
	}

	public String getClosing_Team_Lead_email() {
		return closing_Team_Lead_email;
	}

	public void setClosing_Team_Lead_email(String closing_Team_Lead_email) {
		this.closing_Team_Lead_email = closing_Team_Lead_email;
	}

	public String getSourcing_Team_Lead_email() {
		return sourcing_Team_Lead_email;
	}

	public void setSourcing_Team_Lead_email(String sourcing_Team_Lead_email) {
		this.sourcing_Team_Lead_email = sourcing_Team_Lead_email;
	}

	public String getClosingTeamLeadDto() {
		return closingTeamLeadDto;
	}

	public void setClosingTeamLeadDto(String closingTeamLeadDto) {
		this.closingTeamLeadDto = closingTeamLeadDto;
	}

	public String getSourcingTeamLeadDto() {
		return sourcingTeamLeadDto;
	}

	public void setSourcingTeamLeadDto(String sourcingTeamLeadDto) {
		this.sourcingTeamLeadDto = sourcingTeamLeadDto;
	}

	public String getIsAllowCCPaymentGateway() {
		return isAllowCCPaymentGateway;
	}

	public void setIsAllowCCPaymentGateway(String isAllowCCPaymentGateway) {
		this.isAllowCCPaymentGateway = isAllowCCPaymentGateway;
	}

	public Double getVirtual_meeting_count__c() {
		return virtual_meeting_count__c;
	}

	public void setVirtual_meeting_count__c(Double virtual_meeting_count__c) {
		this.virtual_meeting_count__c = virtual_meeting_count__c;
	}

	
	
	
}
