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
 * @author Satheesh Kyahtam
 *
 */
@Entity
@Table(name="salesforce.nv_hc_enquiry_log")
/*@Table(name="salesforce.nv_enquiry_report")*/
public class EnquiryReportLog extends CommonModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="nv_hc_enquiry_id")
	private Integer enquiryReportId;
	@Column(name="isupdated")
	private String isUpdated;
	/*private Enquiry enquiry;
	private Enquiry enquirySfid;*/
	/**
	 * sales desk field
	 */
	@Column(name="vastu_preference")private String vastuPreference;
	@Column(name="unit_availability") private String unitAvailability;
    @Column(name="accompanied_by") private String accompaniedBy;
    @Column(name="deal_negotiation") private String dealNegotiation;
    @Column(name="construction_status") private String constructionStatus;
    @Column(name="timeframe_to_book") private String timeframeToBook;
    @Column(name="allocated_sales_manager") private String allocatedSalesManager;
    @Column(name="source_of_funding") private String sourceOfFunding;
    /**
     * customer walkin field
     */
    @Column(name="have_we_met_before") private String haveWeMetBefore;
    @Column(name="purchase") private String purpose;
    @Column(name="budget") private String budget;
    @Column(name="typology_requirement") private String desiredUnitType;
    @Column(name="carpet_area_requirement") private String carpetAreaRequirement;
    /*private Contact contact;*/
    
    @Column(name="enquiry_id") private Integer enquiryId;
    @Column(name="enquiry_sfid") private String enquirySfid;
    @Column(name="external_contact_id") private Integer contactId;
    @Column(name="own_contribution_receipt") private String contributionReceipt;
    @Column(name="loan_eligibility") private String loanEligibility;
    
    @Column(name="chequeno_file_name") private String chequeno_file_name;
    @Column(name="panNo_file_name") private String panNo_file_name;
    @Column(name="uTRNo_file_name") private String uTRNo_file_name;
   
    @Column(name="enquiryNonEditComment") private String enquiryNonEditComment;
    @Column(name="cp_comments__c") private String cpComments; 
    @Column(name="followType") private String followType;
    @Column(name="followDate") private Date followDate;
    @Column(name="bankOtherInfo") private String bankOtherInfo;
    @Column(name="unit") private String unit;
    @Column(name="floorBand") private String floorBand;
    @Column(name="tower")   private String tower;
    @Column(name="projectId")   private String projectId;

    
    @Column(name="tokenno") private String tokenno;
    @Column(name="createddate")   private Date createddate;
    @Column(name="userid") private Integer userid;
    @Id
	@SequenceGenerator(allocationSize=1,name="salesforce.nv_hc_enquiry_log_seq",sequenceName="salesforce.nv_hc_enquiry_log_seq")
	@GeneratedValue(generator="salesforce.nv_hc_enquiry_log_seq",strategy=GenerationType.SEQUENCE)
    @Column(name="nv_hc_enquiry_log_id")   
    private Integer nv_hc_enquiry_log_id;
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
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	public Integer getNv_hc_enquiry_log_id() {
		return nv_hc_enquiry_log_id;
	}
	public void setNv_hc_enquiry_log_id(Integer nv_hc_enquiry_log_id) {
		this.nv_hc_enquiry_log_id = nv_hc_enquiry_log_id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	



}
