package com.godrej.properties.master.dto;

import java.io.Serializable;

public class TemplateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -406339806059051246L;

	private String templateId;
	private String value;
	private String name;
	private String bigText;
	
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBigText() {
		return bigText;
	}
	public void setBigText(String bigText) {
		this.bigText = bigText;
	}
	
}
