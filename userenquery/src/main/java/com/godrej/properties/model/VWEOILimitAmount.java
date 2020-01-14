package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.vw_get_eoi_limit_amount")
public class VWEOILimitAmount{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="row_number") private int row_number;
	@Column(name="eoi_sum_amount") private Integer eoi_sum_amount;
	@Column(name="enq_sfid") private String enq_sfid;
	@Column(name="eoi_limit_amount") private Integer eoi_limit_amount;
	@Column(name="project_18_digit__c") private String project_18_digit__c;
	public Integer getEoi_sum_amount() {
		return eoi_sum_amount;
	}
	public void setEoi_sum_amount(Integer eoi_sum_amount) {
		this.eoi_sum_amount = eoi_sum_amount;
	}
	public String getEnq_sfid() {
		return enq_sfid;
	}
	public void setEnq_sfid(String enq_sfid) {
		this.enq_sfid = enq_sfid;
	}
	public Integer getEoi_limit_amount() {
		return eoi_limit_amount;
	}
	public void setEoi_limit_amount(Integer eoi_limit_amount) {
		this.eoi_limit_amount = eoi_limit_amount;
	}
	public String getProject_18_digit__c() {
		return project_18_digit__c;
	}
	public void setProject_18_digit__c(String project_18_digit__c) {
		this.project_18_digit__c = project_18_digit__c;
	}
	
	
	
	
	

}
