package com.godrej.properties.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce._trigger_log") 
public class TriggerLog implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="id") private  int id ;
	@Column(name="txid") private int txid;
	@Column(name="created_at") private Date	created_at;
	@Column(name="updated_at") private Date	updated_at;
	@Column(name="processed_at") private Date processed_at;
	//@Column(name="processed_tx") private int processed_tx;
	@Column(name="state") private String	state;
	@Column(name="action") private String	action;
	@Column(name="table_name") private String	table_name;
	@Column(name="record_id") private int record_id;
	@Column(name="sfid") private String sfid;
	@Column(name="old") private String old;
	@Column(name="values") private String values;
	//@Column(name="sf_result") private int sf_result;
	@Column(name="sf_message") private String sf_message;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTxid() {
		return txid;
	}
	public void setTxid(int txid) {
		this.txid = txid;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public Date getProcessed_at() {
		return processed_at;
	}
	public void setProcessed_at(Date processed_at) {
		this.processed_at = processed_at;
	}

	/*
	 * public int getProcessed_tx() { return processed_tx; } public void
	 * setProcessed_tx(int processed_tx) { this.processed_tx = processed_tx; }
	 */
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public int getRecord_id() {
		return record_id;
	}
	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}
	public String getSfid() {
		return sfid;
	}
	public void setSfid(String sfid) {
		this.sfid = sfid;
	}
	public String getOld() {
		return old;
	}
	public void setOld(String old) {
		this.old = old;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}

	/*
	 * public int getSf_result() { return sf_result; } public void setSf_result(int
	 * sf_result) { this.sf_result = sf_result; }
	 */
	
	public String getSf_message() {
		return sf_message;
	}
	public void setSf_message(String sf_message) {
		this.sf_message = sf_message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}