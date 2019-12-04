package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.document_storage__c")
public class DocumentStorage {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") private int id;
	@Column(name="document_category__c") private String document_category__c;
	@Column(name="document_status__c") private String document_status__c;
	@Column(name="Document_Type__c") private String Document_Type__c;
	@Column(name="contact__c") private String contact__c;
	@Column(name="KYCID__c") private String KYCID__c;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDocument_category__c() {
		return document_category__c;
	}
	public void setDocument_category__c(String document_category__c) {
		this.document_category__c = document_category__c;
	}
	public String getDocument_status__c() {
		return document_status__c;
	}
	public void setDocument_status__c(String document_status__c) {
		this.document_status__c = document_status__c;
	}
	public String getDocument_Type__c() {
		return Document_Type__c;
	}
	public void setDocument_Type__c(String document_Type__c) {
		Document_Type__c = document_Type__c;
	}
	public String getContact__c() {
		return contact__c;
	}
	public void setContact__c(String contact__c) {
		this.contact__c = contact__c;
	}
	public String getKYCID__c() {
		return KYCID__c;
	}
	public void setKYCID__c(String kYCID__c) {
		KYCID__c = kYCID__c;
	}

	
}
