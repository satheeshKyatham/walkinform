package com.godrej.properties.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="salesforce.gpl_refund_initiate")
public class RefundInitiate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="gpl_refund_initiate_id") private int id;
	@Column(name="trx_id") private String trx_id;
	@Column(name="ac_holder_name") private String ac_holder_name;
	@Column(name="bank_name") private String bank_name;
	@Column(name="branch_name") private String branch_name;
	@Column(name="account_no") private String account_no;
	@Column(name="ifsc_code") private String ifsc_code;
	@Column(name="account_type") private String account_type;
	@Column(name="cancelled_check_path") private String cancelled_check_path;
	@Column(name="cancelled_check_file_name") private String cancelled_check_file_name;
	@Column(name="reason_for_cancel_refund") private String reason_for_cancel_refund;
	@Column(name="description") private String description;
	@Column(name="enquiry_sfid") private String enquiry_sfid;
	@Column(name="refund_initiated_date") private Timestamp refund_initiated_date;
	@Column(name="refund_updated_by") private Integer refund_updated_by;
	@Column(name="refund_initiated_by") private Integer refund_initiated_by;
	@Column(name="gpl_cs_eoi_payment_details_id") private Integer gpl_cs_eoi_payment_details_id;
	@Column(name="refund_updated_date") private Timestamp refund_updated_date;
	@Column(name="refund_amount") private Double refund_amount;
	@Column(name="refund_transaction_date") private Timestamp refund_transaction_date;
	@Column(name="trx_id_test") private UUID trx_id_test;
	@Column(name="project_sfid") private String project_sfid;
	@Column(name="refund_status") private String refund_status;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTrx_id() {
		return trx_id;
	}
	public void setTrx_id(String trx_id) {
		this.trx_id = trx_id;
	}
	public String getAc_holder_name() {
		return ac_holder_name;
	}
	public void setAc_holder_name(String ac_holder_name) {
		this.ac_holder_name = ac_holder_name;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getIfsc_code() {
		return ifsc_code;
	}
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getCancelled_check_path() {
		return cancelled_check_path;
	}
	public void setCancelled_check_path(String cancelled_check_path) {
		this.cancelled_check_path = cancelled_check_path;
	}
	public String getCancelled_check_file_name() {
		return cancelled_check_file_name;
	}
	public void setCancelled_check_file_name(String cancelled_check_file_name) {
		this.cancelled_check_file_name = cancelled_check_file_name;
	}
	public String getReason_for_cancel_refund() {
		return reason_for_cancel_refund;
	}
	public void setReason_for_cancel_refund(String reason_for_cancel_refund) {
		this.reason_for_cancel_refund = reason_for_cancel_refund;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEnquiry_sfid() {
		return enquiry_sfid;
	}
	public void setEnquiry_sfid(String enquiry_sfid) {
		this.enquiry_sfid = enquiry_sfid;
	}
	public Timestamp getRefund_initiated_date() {
		return refund_initiated_date;
	}
	public void setRefund_initiated_date(Timestamp refund_initiated_date) {
		this.refund_initiated_date = refund_initiated_date;
	}
	public Integer getRefund_updated_by() {
		return refund_updated_by;
	}
	public void setRefund_updated_by(Integer refund_updated_by) {
		this.refund_updated_by = refund_updated_by;
	}
	public Integer getRefund_initiated_by() {
		return refund_initiated_by;
	}
	public void setRefund_initiated_by(Integer refund_initiated_by) {
		this.refund_initiated_by = refund_initiated_by;
	}
	public Integer getGpl_cs_eoi_payment_details_id() {
		return gpl_cs_eoi_payment_details_id;
	}
	public void setGpl_cs_eoi_payment_details_id(Integer gpl_cs_eoi_payment_details_id) {
		this.gpl_cs_eoi_payment_details_id = gpl_cs_eoi_payment_details_id;
	}
	public Timestamp getRefund_updated_date() {
		return refund_updated_date;
	}
	public void setRefund_updated_date(Timestamp refund_updated_date) {
		this.refund_updated_date = refund_updated_date;
	}
	public Double getRefund_amount() {
		return refund_amount;
	}
	public void setRefund_amount(Double refund_amount) {
		this.refund_amount = refund_amount;
	}
	public Timestamp getRefund_transaction_date() {
		return refund_transaction_date;
	}
	public void setRefund_transaction_date(Timestamp refund_transaction_date) {
		this.refund_transaction_date = refund_transaction_date;
	}
	public UUID getTrx_id_test() {
		return trx_id_test;
	}
	public void setTrx_id_test(UUID trx_id_test) {
		this.trx_id_test = trx_id_test;
	}
	public String getProject_sfid() {
		return project_sfid;
	}
	public void setProject_sfid(String project_sfid) {
		this.project_sfid = project_sfid;
	}
	public String getRefund_status() {
		return refund_status;
	}
	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}

}
