package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

@SuppressWarnings("serial")
@Entity
@Table(name="salesforce.gpl_cs_scheme")
public class HeaderScheme implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")private int id;
	@Column(name = "scheme") private String scheme;
	@Column(name = "rate") private double rate;
	@Column(name = "isactive") private String isactive;
	@Column(name = "project_id") private String project_id;
	@Column(name = "percentage") private double percentage;
	@Column(name = "absolute_amount") private double absolute_amount;
	@Column(name = "zero_govt_charges") private boolean zero_govt_charges;
	
	@Column(name = "source_name") private String source_name;
	@Column(name = "site_name") private String site_name;
	@Column(name = "promotional_name") private String promotional_name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public double getAbsolute_amount() {
		return absolute_amount;
	}
	public void setAbsolute_amount(double absolute_amount) {
		this.absolute_amount = absolute_amount;
	}
	public boolean isZero_govt_charges() {
		return zero_govt_charges;
	}
	public void setZero_govt_charges(boolean zero_govt_charges) {
		this.zero_govt_charges = zero_govt_charges;
	}
	public String getSource_name() {
		return source_name;
	}
	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}
	public String getSite_name() {
		return site_name;
	}
	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}
	public String getPromotional_name() {
		return promotional_name;
	}
	public void setPromotional_name(String promotional_name) {
		this.promotional_name = promotional_name;
	}
}