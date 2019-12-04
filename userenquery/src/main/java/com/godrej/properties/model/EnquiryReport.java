package com.godrej.properties.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.godrej.properties.common.model.CommonModel;

/**
 * 
 * @author Varsha Patil
 *
 */
@Entity
@Table(name="salesforce.nv_hc_enquiry")
/*@Table(name="salesforce.nv_enquiry_report")*/
public class EnquiryReport extends CommonModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer enquiryReportId;
	private String isUpdated;
	/*private Enquiry enquiry;
	private Enquiry enquirySfid;*/
	/**
	 * sales desk field
	 */
	private String vastuPreference;
    private String unitAvailability;
    private String accompaniedBy;
    private String dealNegotiation;
    private String constructionStatus;
    private String timeframeToBook;
    private String allocatedSalesManager;
    private String sourceOfFunding;
    /**
     * customer walkin field
     */
    private String haveWeMetBefore;
    private String purpose;
    private String budget;
    private String desiredUnitType;
    private String carpetAreaRequirement;
    /*private Contact contact;*/
    
    private Integer enquiryId;
    private String enquirySfid;
    private Integer contactId;
    private String contributionReceipt;
    private String loanEligibility;
    
    @Column(name="chequeno_file_name")
    private String chequeno_file_name;
    @Column(name="panNo_file_name")
  	private String panNo_file_name;
    @Column(name="uTRNo_file_name")
  	private String uTRNo_file_name;
   
    @Column(name="enquiryNonEditComment")
  	private String enquiryNonEditComment;
    private String cpComments;
    @Column(name="followType") private String followType;
    @Column(name="followDate") private Date followDate;
    @Column(name="bankOtherInfo") private String bankOtherInfo;
    @Column(name="unit") private String unit;
    @Column(name="floorBand") private String floorBand;
    @Column(name="tower")   private String tower;
    @Column(name="projectId")   private String projectId;
    @Column(name="tokenno") private String tokenno;
    @Column(name="userid") private Integer userid;
    /* Triggers and barriers adding on sales page -  Change By Satheesh Kyatham- 07-10-2019*/
    /*=======Start==========*/
    @Column(name="trigger1") private String trigger1;
    @Column(name="barrier1") private String barrier1;
    @Column(name="trigger2") private String trigger2;
    @Column(name="barrier2") private String barrier2;
    /*=========End========*/
    public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
	public void setFollowDate(Date followDate) {
		this.followDate = followDate;
	}
	public String getEnquiryNonEditComment() {
		return enquiryNonEditComment;
	}
	public void setEnquiryNonEditComment(String enquiryNonEditComment) {
		this.enquiryNonEditComment = enquiryNonEditComment;
	}
	@Column(name="cp_comments__c")
	public String getCpComments() {
		return cpComments;
	}
	public void setCpComments(String cpComments) {
		this.cpComments = cpComments;
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
	@Id
	@SequenceGenerator(allocationSize=1,name="salesforce.nv_hc_enquiry_seq",sequenceName="salesforce.nv_hc_enquiry_seq")
	@GeneratedValue(generator="salesforce.nv_hc_enquiry_seq",strategy=GenerationType.SEQUENCE)
	@Column(name="nv_hc_enquiry_id")
	public Integer getEnquiryReportId() {
		return enquiryReportId;
	}
	public void setEnquiryReportId(Integer enquiryReportId) {
		this.enquiryReportId = enquiryReportId;
	}
	@Column(name="vastu_preference")
	public String getVastuPreference() {
		return vastuPreference;
	}
	public void setVastuPreference(String vastuPreference) {
		this.vastuPreference = vastuPreference;
	}
	@Column(name="unit_availability")
	public String getUnitAvailability() {
		return unitAvailability;
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
	public void setUnitAvailability(String unitAvailability) {
		this.unitAvailability = unitAvailability;
	}
	@Column(name="accompanied_by")
	public String getAccompaniedBy() {
		return accompaniedBy;
	}
	public void setAccompaniedBy(String accompaniedBy) {
		this.accompaniedBy = accompaniedBy;
	}
	@Column(name="deal_negotiation")
	public String getDealNegotiation() {
		return dealNegotiation;
	}
	public void setDealNegotiation(String dealNegotiation) {
		this.dealNegotiation = dealNegotiation;
	}
	@Column(name="construction_status")
	public String getConstructionStatus() {
		return constructionStatus;
	}
	public void setConstructionStatus(String constructionStatus) {
		this.constructionStatus = constructionStatus;
	}
	@Column(name="timeframe_to_book")
	public String getTimeframeToBook() {
		return timeframeToBook;
	}
	public void setTimeframeToBook(String timeframeToBook) {
		this.timeframeToBook = timeframeToBook;
	}
	/*@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="enquiry_id",referencedColumnName="id")
	public Enquiry getEnquiry() {
		return enquiry;
	}
	public void setEnquiry(Enquiry enquiry) {
		this.enquiry = enquiry;
	}	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="enquiry_sfid",referencedColumnName="sfid")
	public Enquiry getEnquirySfid() {
		return enquirySfid;
	}
	public void setEnquirySfid(Enquiry enquirySfid) {
		this.enquirySfid = enquirySfid;
	}*/
	@Column(name="allocated_sales_manager")
	public String getAllocatedSalesManager() {
		return allocatedSalesManager;
	}
	public void setAllocatedSalesManager(String allocatedSalesManager) {
		this.allocatedSalesManager = allocatedSalesManager;
	}
	@Column(name="source_of_funding")
	public String getSourceOfFunding() {
		return sourceOfFunding;
	}
	public void setSourceOfFunding(String sourceOfFunding) {
		this.sourceOfFunding = sourceOfFunding;
	}
	@Column(name="budget")
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	@Column(name="have_we_met_before")
	public String getHaveWeMetBefore() {
		return haveWeMetBefore;
	}
	public void setHaveWeMetBefore(String haveWeMetBefore) {
		this.haveWeMetBefore = haveWeMetBefore;
	}
	@Column(name="purchase")
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	@Column(name="typology_requirement")
	public String getDesiredUnitType() {
		return desiredUnitType;
	}
	public void setDesiredUnitType(String desiredUnitType) {
		this.desiredUnitType = desiredUnitType;
	}
	@Column(name="carpet_area_requirement")
	public String getCarpetAreaRequirement() {
		return carpetAreaRequirement;
	}
	public void setCarpetAreaRequirement(String carpetAreaRequirement) {
		this.carpetAreaRequirement = carpetAreaRequirement;
	}
	@Column(name="isupdated")
	public String getIsUpdated() {
		return isUpdated;
	}
	public void setIsUpdated(String isUpdated) {
		this.isUpdated = isUpdated;
	}
	/*@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="external_contact_id",referencedColumnName="id")
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}*/
	@Column(name="enquiry_id")
	public Integer getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(Integer enquiryId) {	
		this.enquiryId = enquiryId;
	}
	@Column(name="enquiry_sfid")
	public String getEnquirySfid() {
		return enquirySfid;
	}
	public void setEnquirySfid(String enquirySfid) {
		this.enquirySfid = enquirySfid;
	}
	@Column(name="external_contact_id")
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	@Column(name="own_contribution_receipt")
	public String getContributionReceipt() {
		return contributionReceipt;
	}
	public void setContributionReceipt(String contributionReceipt) {
		this.contributionReceipt = contributionReceipt;
	}
	@Column(name="loan_eligibility")
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
	



}
