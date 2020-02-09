package com.godrej.properties.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.vw_propstrength__prepayment_received__c")
public class PrePaymentReceived {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") private int id;
	@Column(name="propstrength__instrument_date__c") private Date propstrength__instrument_date__c;
	@Column(name="propstrength__cheque_demand_draft_number__c") private Double propstrength__cheque_demand_draft_number__c;
	@Column(name="propstrength__payment_mode__c") private String propstrength__payment_mode__c;
	@Column(name="propstrength__amount__c") private Double propstrength__amount__c;
	@Column(name="propstrength__offer__c") private String propstrength__offer__c;
	@Column(name="name") private String name;
	@Column(name="bank_name_gl__c") private String bank_name_gl__c;
	@Column(name="ownerid") private String ownerid;
	@Column(name="isdeleted") private boolean isdeleted;
	@Column(name="propstrength__ifsc_rtgs_code__c") private String propstrength__ifsc_rtgs_code__c;
	@Column(name="createddate ") private Timestamp createddate ;
	@Column(name="propstrength__payment_date__c") private Date propstrength__payment_date__c;
	@Column(name="propstrength__bank_name__c") private String propstrength__bank_name__c;
	@Column(name="propstrength__crn_no__c") private String propstrength__crn_no__c;
	@Column(name="PropStrength__Cheque_Demand_Draft_No__c") private String propStrength__Cheque_Demand_Draft_No__c;
	
	@Column(name="pan_attach") private String pan_attach;
	@Column(name="cheque_attach") private String cheque_attach;
	@Column(name="description") private String description;
	
	@Column(name="sfid") private String sfid;
	@Column(name="propstrength__request__c") private String enq_sfid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getPropstrength__instrument_date__c() {
		return propstrength__instrument_date__c;
	}
	public void setPropstrength__instrument_date__c(Date propstrength__instrument_date__c) {
		this.propstrength__instrument_date__c = propstrength__instrument_date__c;
	}
	public Double getPropstrength__cheque_demand_draft_number__c() {
		return propstrength__cheque_demand_draft_number__c;
	}
	public void setPropstrength__cheque_demand_draft_number__c(Double propstrength__cheque_demand_draft_number__c) {
		this.propstrength__cheque_demand_draft_number__c = propstrength__cheque_demand_draft_number__c;
	}
	public String getPropstrength__payment_mode__c() {
		return propstrength__payment_mode__c;
	}
	public void setPropstrength__payment_mode__c(String propstrength__payment_mode__c) {
		this.propstrength__payment_mode__c = propstrength__payment_mode__c;
	}
	public Double getPropstrength__amount__c() {
		return propstrength__amount__c;
	}
	public void setPropstrength__amount__c(Double propstrength__amount__c) {
		this.propstrength__amount__c = propstrength__amount__c;
	}
	public String getPropstrength__offer__c() {
		return propstrength__offer__c;
	}
	public void setPropstrength__offer__c(String propstrength__offer__c) {
		this.propstrength__offer__c = propstrength__offer__c;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBank_name_gl__c() {
		return bank_name_gl__c;
	}
	public void setBank_name_gl__c(String bank_name_gl__c) {
		this.bank_name_gl__c = bank_name_gl__c;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	public boolean isIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	public String getPropstrength__ifsc_rtgs_code__c() {
		return propstrength__ifsc_rtgs_code__c;
	}
	public void setPropstrength__ifsc_rtgs_code__c(String propstrength__ifsc_rtgs_code__c) {
		this.propstrength__ifsc_rtgs_code__c = propstrength__ifsc_rtgs_code__c;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public Date getPropstrength__payment_date__c() {
		return propstrength__payment_date__c;
	}
	public void setPropstrength__payment_date__c(Date propstrength__payment_date__c) {
		this.propstrength__payment_date__c = propstrength__payment_date__c;
	}
	public String getPropstrength__bank_name__c() {
		return propstrength__bank_name__c;
	}
	public void setPropstrength__bank_name__c(String propstrength__bank_name__c) {
		this.propstrength__bank_name__c = propstrength__bank_name__c;
	}
	public String getPropstrength__crn_no__c() {
		return propstrength__crn_no__c;
	}
	public void setPropstrength__crn_no__c(String propstrength__crn_no__c) {
		this.propstrength__crn_no__c = propstrength__crn_no__c;
	}
	public String getSfid() {
		return sfid;
	}
	public void setSfid(String sfid) {
		this.sfid = sfid;
	}
	public String getPropStrength__Cheque_Demand_Draft_No__c() {
		return propStrength__Cheque_Demand_Draft_No__c;
	}
	public void setPropStrength__Cheque_Demand_Draft_No__c(String propStrength__Cheque_Demand_Draft_No__c) {
		this.propStrength__Cheque_Demand_Draft_No__c = propStrength__Cheque_Demand_Draft_No__c;
	}
	public String getPan_attach() {
		return pan_attach;
	}
	public void setPan_attach(String pan_attach) {
		this.pan_attach = pan_attach;
	}
	public String getCheque_attach() {
		return cheque_attach;
	}
	public void setCheque_attach(String cheque_attach) {
		this.cheque_attach = cheque_attach;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEnq_sfid() {
		return enq_sfid;
	}
	public void setEnq_sfid(String enq_sfid) {
		this.enq_sfid = enq_sfid;
	}
	
	
	
}
