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
@Table(name="salesforce.gpl_cs_extra_charges_his")

public class ExtraChargesHis implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpl_cs_extra_charges_his_id")
	private int id;	
	
	@Column(name = "seq")
	private int seq;
		
	@Column(name = "enquiry_id")
	private String enquiry_id;
		
	@Column(name = "user_contact")
	private String user_contact;
	
	
	@SerializedName("Description")
	@Column(name = "Description")
	private String Description;	
	
	@SerializedName("Charge Amount (INR)")
	@Column(name = "ChargeAmount")
	private String ChargeAmount;
	
	@SerializedName("Taxable Amount (INR)")
	@Column(name = "TaxableAmount")
	private String TaxableAmount;
	
	@SerializedName("CGST Rate")
	@Column(name = "CGSTRate")
	private String CGSTRate;
	
	@SerializedName("CGST Amount (INR)")
	@Column(name = "CGSTAmount")
	private String CGSTAmount;
	
	@SerializedName("SGST Rate")
	@Column(name = "SGSTRate")
	private String SGSTRate;
	
	
	@SerializedName("SGST Amount (INR)")
	@Column(name = "SGSTAmount")
	private String SGSTAmount;
	
	@SerializedName("Total (INR)")
	@Column(name = "Total")
	private String Total;

	
	@Column(name = "timeid")
	private long timeid;
	
	
	@Column(name = "isactive")
	private String isactive;
	
	//newTime
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

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

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getChargeAmount() {
		return ChargeAmount;
	}

	public void setChargeAmount(String chargeAmount) {
		ChargeAmount = chargeAmount;
	}

	public String getTaxableAmount() {
		return TaxableAmount;
	}

	public void setTaxableAmount(String taxableAmount) {
		TaxableAmount = taxableAmount;
	}

	public String getCGSTRate() {
		return CGSTRate;
	}

	public void setCGSTRate(String cGSTRate) {
		CGSTRate = cGSTRate;
	}

	public String getCGSTAmount() {
		return CGSTAmount;
	}

	public void setCGSTAmount(String cGSTAmount) {
		CGSTAmount = cGSTAmount;
	}

	public String getSGSTRate() {
		return SGSTRate;
	}

	public void setSGSTRate(String sGSTRate) {
		SGSTRate = sGSTRate;
	}

	public String getSGSTAmount() {
		return SGSTAmount;
	}

	public void setSGSTAmount(String sGSTAmount) {
		SGSTAmount = sGSTAmount;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		

}