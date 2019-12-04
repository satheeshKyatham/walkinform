package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.godrej.properties.common.model.CommonModel;

@Entity
@Table(name="salesforce.account")
public class ChannelPartner extends CommonModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer channelPartnerId;
	private String name;
	private String recordTypeId;
	private String propstrength__active__c;
	/*private String code;
	private String description;*/
    
	@Id
	/*@SequenceGenerator(name = "salesforce.m_channel_partner_seq", sequenceName = "salesforce.m_channel_partner_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salesforce.m_channel_partner_seq")*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getChannelPartnerId() {
		return channelPartnerId;
	}
	public void setChannelPartnerId(Integer channelPartnerId) {
		this.channelPartnerId = channelPartnerId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="recordtypeid")
	public String getRecordTypeId() {
		return recordTypeId;
	}
	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}
	@Column(name="propstrength__active__c")
	public String getPropstrength__active__c() {
		return propstrength__active__c;
	}
	public void setPropstrength__active__c(String propstrength__active__c) {
		this.propstrength__active__c = propstrength__active__c;
	}
	
	
	/*
	@Column(name="value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}*/

}
