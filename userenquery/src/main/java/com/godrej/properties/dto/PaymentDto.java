package com.godrej.properties.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDto {
	
	@JsonProperty("id")
	private int id;	
	@JsonProperty("enq_sfid")
	private String enqSfid;
	@JsonProperty("contact_sfid")
	private String contactSfid;
	@JsonProperty("offer_sfid")
	private String offerSfid;
	@JsonProperty("project_sfid")
	private String projectSfid;
	@JsonProperty("userid")
	private int userId;
	@JsonProperty("payment_type")
	private String paymentType;
	@JsonProperty("bank_name")
	private String bankName;
	@JsonProperty("branch")
	private String branch;
	@JsonProperty("transaction_id")
	private String transactionId;
	@JsonProperty("transaction_date")
	private Date transactionDate;
	@JsonProperty("transaction_amount")
	private String transactionAmount;
	@JsonProperty("description")
	private String description;
	@JsonProperty("total_amount")
	private String totalAmount;
	@JsonProperty("is_active")
	private String isActive;
	@JsonProperty("cheque_attach")
	private String chequeAttach;
	@JsonProperty("pan_attach")
	private String panAttach;
	@JsonProperty("gpl_cs_balance_details_id")
	private int gplCsBalanceDetailsId;
	@JsonProperty("gpl_cs_eoi_payment_details_id")
	private int gplCsEoiPaymentDetailsId;
	@JsonProperty("transaction_date_string")
	private String transactionDateString;
	@JsonProperty("offer_id")
	private String offerId;
	@JsonProperty("bankGL")
	private String bankGL;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEnqSfid() {
		return enqSfid;
	}
	public void setEnqSfid(String enqSfid) {
		this.enqSfid = enqSfid;
	}
	public String getContactSfid() {
		return contactSfid;
	}
	public void setContactSfid(String contactSfid) {
		this.contactSfid = contactSfid;
	}
	public String getOfferSfid() {
		return offerSfid;
	}
	public void setOfferSfid(String offerSfid) {
		this.offerSfid = offerSfid;
	}
	public String getProjectSfid() {
		return projectSfid;
	}
	public void setProjectSfid(String projectSfid) {
		this.projectSfid = projectSfid;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getChequeAttach() {
		return chequeAttach;
	}
	public void setChequeAttach(String chequeAttach) {
		this.chequeAttach = chequeAttach;
	}
	public String getPanAttach() {
		return panAttach;
	}
	public void setPanAttach(String panAttach) {
		this.panAttach = panAttach;
	}
	public int getGplCsBalanceDetailsId() {
		return gplCsBalanceDetailsId;
	}
	public void setGplCsBalanceDetailsId(int gplCsBalanceDetailsId) {
		this.gplCsBalanceDetailsId = gplCsBalanceDetailsId;
	}
	public int getGplCsEoiPaymentDetailsId() {
		return gplCsEoiPaymentDetailsId;
	}
	public void setGplCsEoiPaymentDetailsId(int gplCsEoiPaymentDetailsId) {
		this.gplCsEoiPaymentDetailsId = gplCsEoiPaymentDetailsId;
	}
	public String getTransactionDateString() {
		return transactionDateString;
	}
	public void setTransactionDateString(String transactionDateString) {
		this.transactionDateString = transactionDateString;
	}
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public String getBankGL() {
		return bankGL;
	}
	public void setBankGL(String bankGL) {
		this.bankGL = bankGL;
	}
}
