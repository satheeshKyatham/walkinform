package com.godrej.properties.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="salesforce.vw_Presales_paymentPlanlineItem")
public class PaymentPlanMilestone {
 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")private int id;
	
	//@Column(name="nv_amount_percent__c")private String nv_amount_percent__c;
	@Column(name = "nv_amount_percent__c") private double nv_amount_percent__c;
	
	@Column(name="nv_payment_plan_line_item_name__c")private String nv_payment_plan_line_item_name__c;
	@Column(name="propstrength__payment_plan__c")private String propstrength__payment_plan__c;
	@Column(name="name")private String name;
	@Column(name="sfid")private String sfid;
	@Transient
	private String iscompleted;
	
	@Transient
	private BigDecimal bookingamount;
	
	@Transient
	private Integer ppDueday;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNv_payment_plan_line_item_name__c() {
		return nv_payment_plan_line_item_name__c;
	}
	public void setNv_payment_plan_line_item_name__c(String nv_payment_plan_line_item_name__c) {
		this.nv_payment_plan_line_item_name__c = nv_payment_plan_line_item_name__c;
	}
	public String getPropstrength__payment_plan__c() {
		return propstrength__payment_plan__c;
	}
	public void setPropstrength__payment_plan__c(String propstrength__payment_plan__c) {
		this.propstrength__payment_plan__c = propstrength__payment_plan__c;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIscompleted() {
		return iscompleted;
	}
	public void setIscompleted(String iscompleted) {
		this.iscompleted = iscompleted;
	}
	public String getSfid() {
		return sfid;
	}
	public void setSfid(String sfid) {
		this.sfid = sfid;
	}
	public BigDecimal getBookingamount() {
		return bookingamount;
	}
	public void setBookingamount(BigDecimal bookingamount) {
		this.bookingamount = bookingamount;
	}
	public Integer getPpDueday() {
		return ppDueday;
	}
	public void setPpDueday(Integer ppDueday) {
		this.ppDueday = ppDueday;
	}
	public double getNv_amount_percent__c() {
		return nv_amount_percent__c;
	}
	public void setNv_amount_percent__c(double nv_amount_percent__c) {
		this.nv_amount_percent__c = nv_amount_percent__c;
	}
}