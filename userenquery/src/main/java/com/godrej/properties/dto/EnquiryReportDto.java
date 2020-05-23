package com.godrej.properties.dto;

import java.sql.Time;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.godrej.properties.common.dto.CommonDto;
import com.godrej.properties.common.utilities.CommonValidations;
import com.godrej.properties.constants.KeyConstants;

/**
 * 
 * @author Varsha Patil
 *
 */
public class EnquiryReportDto extends CommonDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6775111629548405387L;
	private Integer enquiryReportId;
	private String vastuPreference;
    private String unitAvailability;
    private String accompaniedBy;
    private String dealNegotiation;
    private String constructionStatus;
    private String timeframeToBook;
    /*private EnquiryDto enquiry;
    private EnquiryDto enquirySfid;*/
    
    private String allocatedSalesManager;
    private String sourceOfFunding;
       
    private String haveWeMetBefore;
    private String purpose;
    private String budget;
    private String desiredUnitType;
    private String carpetAreaRequirement;
    
    private String isUpdated;
   /* private ContactDto contact;*/
    private Integer enquiryId;
    private String enquirySfid;
    private Integer contactId;
    
    private String chequeno_file_name;
  	private String panNo_file_name;
  	private String uTRNo_file_name;
  	@JsonIgnore
    private MultipartFile chequeFile;
  	@JsonIgnore
    private MultipartFile panFile;
  	@JsonIgnore
    private MultipartFile swapFile;
  	private String contributionReceipt;
    private String loanEligibility;
    private String contributionReceipt_val;
    private String loanEligibility_val;
    private String enquiryNonEditComment;
    private String cpComments;   
	
    
    private String followType;
   	private String visitType;
    
    @DateTimeFormat(pattern=KeyConstants.DEFAULT_DATE_FORMAT)
    private Date followDate;
    private String unit;
    private String floorBand;
    private String bankOtherInfo;
    private String tower;
    private String projectId;
    private String tokenno;
    private Integer userid;
    
    /* Triggers and barriers adding on sales page -  Change By Satheesh Kyatham- 07-10-2019*/
    /*=======Start==========*/
    private String trigger1;
    private String barrier1;
    private String trigger2;
    private String barrier2;
    /*=========End========*/
    /* Referred by added on Enquiry page, on select of walk-in source as referral -  
     * Change By Satheesh Kyatham- 25-12-2019
     * Request From - Prakash Idnani*/
    /*=======Start==========*/
    private String referredby;
    /*=========End========*/
    public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
 
    public String getTower() {
		return tower;
	}
	public void setTower(String tower) {
		this.tower = tower;
	}
	public String getBankOtherInfo() {
		return bankOtherInfo;
	}
	public void setBankOtherInfo(String bankOtherInfo) {
		this.bankOtherInfo = bankOtherInfo;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getFloorBand() {
		return floorBand;
	}
	public void setFloorBand(String floorBand) {
		this.floorBand = floorBand;
	}
	public String getCpComments() {
		return cpComments;
	}
	public void setCpComments(String cpComments) {
		this.cpComments = cpComments;
	}
	public String getFollowType() {
		return followType;
	}
	public void setFollowType(String followType) {
		this.followType = followType;
	}
	public Date getFollowDate() {
		return followDate;
	}
	public void setFollowDate(Date followDate) {
		this.followDate = followDate;
	}
	public String getEnquiryNonEditComment() {
		return enquiryNonEditComment;
	}
	public void setEnquiryNonEditComment(String enquiryNonEditComment) {
		this.enquiryNonEditComment = enquiryNonEditComment;
	}
	public String getContributionReceipt_val() {
		return contributionReceipt_val;
	}
	public void setContributionReceipt_val(String contributionReceipt_val) {
		this.contributionReceipt_val = contributionReceipt_val;
	}
	public String getLoanEligibility_val() {
		return loanEligibility_val;
	}
	public void setLoanEligibility_val(String loanEligibility_val) {
		this.loanEligibility_val = loanEligibility_val;
	}
	public String getChequeno_file_name() {
		return chequeno_file_name;
	}
	public void setChequeno_file_name(String chequeno_file_name) {
		this.chequeno_file_name = chequeno_file_name;
	}
	public String getPanNo_file_name() {
		return panNo_file_name;
	}
	public void setPanNo_file_name(String panNo_file_name) {
		this.panNo_file_name = panNo_file_name;
	}
	public String getuTRNo_file_name() {
		return uTRNo_file_name;
	}
	public void setuTRNo_file_name(String uTRNo_file_name) {
		this.uTRNo_file_name = uTRNo_file_name;
	}
	public Integer getEnquiryReportId() {
		return enquiryReportId;
	}
	public void setEnquiryReportId(Integer enquiryReportId) {
		this.enquiryReportId = enquiryReportId;
	}
	public String getVastuPreference() {
		return vastuPreference;
	}
	public void setVastuPreference(String vastuPreference) {
		this.vastuPreference = vastuPreference;
	}
	public String getUnitAvailability() {
		return unitAvailability;
	}
	public void setUnitAvailability(String unitAvailability) {
		this.unitAvailability = unitAvailability;
	}
	public String getAccompaniedBy() {
		return accompaniedBy;
	}
	public void setAccompaniedBy(String accompaniedBy) {
		this.accompaniedBy = accompaniedBy;
	}
	public String getDealNegotiation() {
		return dealNegotiation;
	}
	public void setDealNegotiation(String dealNegotiation) {
		this.dealNegotiation = dealNegotiation;
	}
	public String getConstructionStatus() {
		return constructionStatus;
	}
	public void setConstructionStatus(String constructionStatus) {
		this.constructionStatus = constructionStatus;
	}
	public String getTimeframeToBook() {
		return timeframeToBook;
	}
	public void setTimeframeToBook(String timeframeToBook) {
		this.timeframeToBook = timeframeToBook;
	}
	public String getAllocatedSalesManager() {
		return allocatedSalesManager;
	}
	public void setAllocatedSalesManager(String allocatedSalesManager) {
		this.allocatedSalesManager = allocatedSalesManager;
	}
	public String getSourceOfFunding() {
		return sourceOfFunding;
	}
	public void setSourceOfFunding(String sourceOfFunding) {
		this.sourceOfFunding = sourceOfFunding;
	}
    
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getHaveWeMetBefore() {
		return haveWeMetBefore;
	}
	public void setHaveWeMetBefore(String haveWeMetBefore) {
		this.haveWeMetBefore = haveWeMetBefore;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getDesiredUnitType() {
		return desiredUnitType;
	}
	public void setDesiredUnitType(String desiredUnitType) {
		this.desiredUnitType = desiredUnitType;
	}
	public String getCarpetAreaRequirement() {
		return carpetAreaRequirement;
	}
	public void setCarpetAreaRequirement(String carpetAreaRequirement) {
		this.carpetAreaRequirement = carpetAreaRequirement;
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
	public Integer getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(Integer enquiryId) {
		this.enquiryId = enquiryId;
	}
	public String getEnquirySfid() {
		return enquirySfid;
	}
	public void setEnquirySfid(String enquirySfid) {
		this.enquirySfid = enquirySfid;
	}
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	public MultipartFile getChequeFile() {
		return chequeFile;
	}
	public void setChequeFile(MultipartFile chequeFile) {
		this.chequeFile = chequeFile;
	}
	public MultipartFile getPanFile() {
		return panFile;
	}
	public void setPanFile(MultipartFile panFile) {
		this.panFile = panFile;
	}
	public MultipartFile getSwapFile() {
		return swapFile;
	}
	public void setSwapFile(MultipartFile swapFile) {
		this.swapFile = swapFile;
	}
	public String getContributionReceipt() {
		return contributionReceipt;
	}
	public void setContributionReceipt(String contributionReceipt) {
		this.contributionReceipt = contributionReceipt;
	}
	public String getLoanEligibility() {
		return loanEligibility;
	}
	public void setLoanEligibility(String loanEligibility) {
		this.loanEligibility = loanEligibility;
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
	public String getTrigger1() {
		return trigger1;
	}
	public void setTrigger1(String trigger1) {
		this.trigger1 = trigger1;
	}
	public String getBarrier1() {
		return barrier1;
	}
	public void setBarrier1(String barrier1) {
		this.barrier1 = barrier1;
	}
	public String getTrigger2() {
		return trigger2;
	}
	public void setTrigger2(String trigger2) {
		this.trigger2 = trigger2;
	}
	public String getBarrier2() {
		return barrier2;
	}
	public void setBarrier2(String barrier2) {
		this.barrier2 = barrier2;
	}
	public String getReferredby() {
		return referredby;
	}
	public void setReferredby(String referredby) {
		this.referredby = referredby;
	}	
	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
	
}
