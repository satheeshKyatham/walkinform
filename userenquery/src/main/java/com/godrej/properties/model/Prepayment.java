package com.godrej.properties.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.propstrength__prepayment_received__c")
public class Prepayment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String paymentMode;
	private String bankName;
	private double chequeNo;
	private String ifscRTGSCode;
	private Date instrumentDate;
	private String amount;
	private String offerSfid;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="propstrength__payment_mode__c")
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	@Column(name="propstrength__bank_name__c")
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@Column(name="propstrength__cheque_demand_draft_number__c")
	public double getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(double chequeNo) {
		this.chequeNo = chequeNo;
	}
	@Column(name="propstrength__ifsc_rtgs_code__c")
	public String getIfscRTGSCode() {
		return ifscRTGSCode;
	}
	public void setIfscRTGSCode(String ifscRTGSCode) {
		this.ifscRTGSCode = ifscRTGSCode;
	}
	@Column(name="propstrength__instrument_date__c")
	public Date getInstrumentDate() {
		return instrumentDate;
	}
	public void setInstrumentDate(Date instrumentDate) {
		this.instrumentDate = instrumentDate;
	}
	@Column(name="propstrength__amount__c")
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Column(name="propstrength__offer__c")
	public String getOfferSfid() {
		return offerSfid;
	}
	public void setOfferSfid(String offerSfid) {
		this.offerSfid = offerSfid;
	}

}
