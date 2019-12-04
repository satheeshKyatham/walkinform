package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;


@Entity
@Table(name="salesforce.gpl_cs_costsheet")

public class CostSheet implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpl_cs_costsheet_id")
	private int id;	
	
	@Column(name = "seq")
	private int seq;
	
	
	@Column(name = "enquiry_id")
	private String enquiry_id;
	
	@Column(name = "user_contact")
	private String user_contact;
	
	@SerializedName("Milestones")
	@Column(name = "milestones")
	private String milestones;	
	
	@SerializedName("Charge Amount (INR)")
	@Column(name = "chargeamount")
	private String chargeAmount;
	
	@SerializedName("Taxable Amount (INR)")
	@Column(name = "taxableamount")
	private String taxableAmount;
	
	@SerializedName("CGST Rate")
	@Column(name = "cgstrate")
	private String cgstRate;
	
	@SerializedName("CGST Amount (INR)")
	@Column(name = "cgstamount")
	private String cgstAmount;
	
	@SerializedName("SGST Rate")
	@Column(name = "sgstrate")
	private String sgstRate;
	
	@SerializedName("SGST Amount (INR)")
	@Column(name = "sgstamount")
	private String sgstAmount;
	
	@SerializedName("Total (INR)")
	@Column(name = "total")
	private String total;
	
	@SerializedName("TDS @ 1% (INR)")
	@Column(name = "tds")
	private String tds;
	
	@SerializedName("Payable to")
	@Column(name = "payableto")
	private String payableTo;
	
	@Column(name = "timeid")
	private long timeid;
	
	
	@Column(name = "isactive")
	private String isactive;
	
	
	//newTime
	
	public String getEnquiry_id() {
		return enquiry_id;
	}
	public void setEnquiry_id(String enquiry_id) {
		this.enquiry_id = enquiry_id;
	}
	public String getUser_contact() {
		return user_contact;
	}
	public void setUser_contact(String user_contact) {
		this.user_contact = user_contact;
	}
	
	public String getMilestones() {
		return milestones;
	}
	
	public void setMilestones(String milestones) {
		this.milestones = milestones;
	}
	public String getChargeAmount() {
		return chargeAmount;
	}
	public void setChargeAmount(String chargeAmount) {
		this.chargeAmount = chargeAmount;
	}
	public String getTaxableAmount() {
		return taxableAmount;
	}
	public void setTaxableAmount(String taxableAmount) {
		this.taxableAmount = taxableAmount;
	}
	public String getCgstRate() {
		return cgstRate;
	}
	public void setCgstRate(String cgstRate) {
		this.cgstRate = cgstRate;
	}
	public String getCgstAmount() {
		return cgstAmount;
	}
	public void setCgstAmount(String cgstAmount) {
		this.cgstAmount = cgstAmount;
	}
	public String getSgstRate() {
		return sgstRate;
	}
	public void setSgstRate(String sgstRate) {
		this.sgstRate = sgstRate;
	}
	public String getSgstAmount() {
		return sgstAmount;
	}
	public void setSgstAmount(String sgstAmount) {
		this.sgstAmount = sgstAmount;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTds() {
		return tds;
	}
	public void setTds(String tds) {
		this.tds = tds;
	}
	public String getPayableTo() {
		return payableTo;
	}
	public void setPayableTo(String payableTo) {
		this.payableTo = payableTo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	
	
	
	
	
	
	
	
	
	public long getTimeid() {
		return timeid;
	}
	public void setTimeid(long timeid) {
		this.timeid = timeid;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	@Override
	public String toString() {
		return seq+"CostSheet [milestones=" + milestones + ", chargeAmount=" + chargeAmount + ", taxableAmount="
				+ taxableAmount + ", cgstRate=" + cgstRate + ", cgstAmount=" + cgstAmount + ", sgstRate=" + sgstRate
				+ ", sgstAmount=" + sgstAmount + ", total=" + total + ", tds=" + tds + ", payableTo=" + payableTo + "]";
	}
	
	
	
	
}
