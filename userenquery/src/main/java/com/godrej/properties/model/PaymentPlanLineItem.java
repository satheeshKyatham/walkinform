package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="salesforce.vw_payment_plan_line_item" )
public class PaymentPlanLineItem {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="row_number") private int  id;
	@Column(name="propstrength__amount_percent__c") private String  Per;
	@Column(name="propstrength__amount__c") private String  Amount;
	@Column(name="id") private String  iduniqe;
	@Column(name="propstrength__payment_plan_line_item_name__c") private String  Milestone;
	@Column(name="fixed") private String  fixedYN;
	@Column(name="sfid") private String  sfid;
	@Column(name="PropStrength__Order__c") private Double  order;
	@Transient private String lastRowYN="N";
	@Transient private String iscompleted;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPer() {
		return Per;
	}
	public void setPer(String per) {
		Per = per;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getIduniqe() {
		return iduniqe;
	}
	public void setIduniqe(String iduniqe) {
		this.iduniqe = iduniqe;
	}
	public String getMilestone() {
		return Milestone;
	}
	public void setMilestone(String milestone) {
		Milestone = milestone;
	}
	public String getFixedYN() {
		return fixedYN;
	}
	public void setFixedYN(String fixedYN) {
		this.fixedYN = fixedYN;
	}
	public String getLastRowYN() {
		return lastRowYN;
	}
	public void setLastRowYN(String lastRowYN) {
		this.lastRowYN = lastRowYN;
	}
	public String getSfid() {
		return sfid;
	}
	public void setSfid(String sfid) {
		this.sfid = sfid;
	}
	public String getIscompleted() {
		return iscompleted;
	}
	public void setIscompleted(String iscompleted) {
		this.iscompleted = iscompleted;
	}
	public Double getOrder() {
		return order;
	}
	public void setOrder(Double order) {
		this.order = order;
	}
	



	
}
