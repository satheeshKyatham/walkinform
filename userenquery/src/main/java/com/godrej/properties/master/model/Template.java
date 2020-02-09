package com.godrej.properties.master.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.ad_template")
public class Template implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4219076341128758924L;
	private String templateId;
	private String value;
	private String name;
	private String bigText;
	
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	@Column(name = "ad_template_id")
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	@Column(name = "value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "big_text")
	public String getBigText() {
		return bigText;
	}
	public void setBigText(String bigText) {
		this.bigText = bigText;
	}
	
}
