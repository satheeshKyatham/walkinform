package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
//@Table(name="salesforce.vw_token_details")
@Table(name="salesforce.vw_token_details_uat")
public class VW_Token {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="nv_token_id") private int nv_token_id;
	@Column(name="mobileno") private String  mobileno;
	@Column(name="type") private String  type;
	@Column(name="uniqe_no") private String  uniqe_no;
	@Column(name="uniqe_str") private String  uniqe_str;
	@Column(name="window_assign") private String  window_assign;
	@Column(name="is_reached") private String  is_reached;
	@Column(name="created") private Timestamp  created;
	@Column(name="queue") private int  queue;
	@Column(name="isdone") private String isdone;
	@Column(name="starteddate") private Timestamp   starteddate;
	@Column(name="salesname") private String salesname;
	@Column(name="enddate") private Timestamp   enddate;
	@Column(name="isactive") private String   isactive;
	@Column(name="enquiry_18") private String   enquiry_18;
	@Column(name="projectname") private String   projectname;
	@Column(name="contact_name") private String   contact_name;
	@Column(name="mobile_no") private String   mobile_no;
	@Column(name="user_name") private String   user_name;
	@Column(name="partnertype") private String   partnertype;
	@Column(name="createdBy") private String   createdBy;
	@Column(name="priority_no__c") private String   priority_no__c;
	@Column(name="closing_manager_name__c") private String   closing_manager_name__c;
	@Column(name="countrycode") private String   countrycode;
	
	
	@Transient private String msg;
	@Transient private String name;
	@Transient private String docNo;
	@Transient private String amount;
	@Transient private String isKYCFilled;
	@Transient private String encStr;
	@Transient private String typeCount;
	@Transient private String typeName;
	

	public String getClosing_manager_name__c() {
		return closing_manager_name__c;
	}
	public void setClosing_manager_name__c(String closing_manager_name__c) {
		this.closing_manager_name__c = closing_manager_name__c;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getPriority_no__c() {
		return priority_no__c;
	}
	public void setPriority_no__c(String priority_no__c) {
		this.priority_no__c = priority_no__c;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeCount() {
		return typeCount;
	}
	public void setTypeCount(String typeCount) {
		this.typeCount = typeCount;
	}
	public String getEncStr() {
		return encStr;
	}
	public void setEncStr(String encStr) {
		this.encStr = encStr;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getIsdone() {
		return isdone;
	}
	public void setIsdone(String isdone) {
		this.isdone = isdone;
	}
	public Timestamp getStarteddate() {
		return starteddate;
	}
	public void setStarteddate(Timestamp starteddate) {
		this.starteddate = starteddate;
	}
	public String getSalesname() {
		return salesname;
	}
	public void setSalesname(String salesname) {
		this.salesname = salesname;
	}
	public Timestamp getEnddate() {
		return enddate;
	}
	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}
	public int getQueue() {
		return queue;
	}
	public void setQueue(int queue) {
		this.queue = queue;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getNv_token_id() {
		return nv_token_id;
	}
	public void setNv_token_id(int nv_token_id) {
		this.nv_token_id = nv_token_id;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUniqe_no() {
		return uniqe_no;
	}
	public void setUniqe_no(String uniqe_no) {
		this.uniqe_no = uniqe_no;
	}
	public String getUniqe_str() {
		return uniqe_str;
	}
	public void setUniqe_str(String uniqe_str) {
		this.uniqe_str = uniqe_str;
	}
	public String getWindow_assign() {
		return window_assign;
	}
	public void setWindow_assign(String window_assign) {
		this.window_assign = window_assign;
	}
	public String getIs_reached() {
		return is_reached;
	}
	public void setIs_reached(String is_reached) {
		this.is_reached = is_reached;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public String getIsKYCFilled() {
		return isKYCFilled;
	}
	public void setIsKYCFilled(String isKYCFilled) {
		this.isKYCFilled = isKYCFilled;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public String getEnquiry_18() {
		return enquiry_18;
	}
	public void setEnquiry_18(String enquiry_18) {
		this.enquiry_18 = enquiry_18;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getPartnertype() {
		return partnertype;
	}
	public void setPartnertype(String partnertype) {
		this.partnertype = partnertype;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	
}
