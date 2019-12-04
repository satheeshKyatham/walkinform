package com.godrej.properties.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.godrej.properties.common.model.CommonModel;


@Entity
@Table(name="salesforce.m_reference")
public class Reference extends CommonModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String referenceId;
	private String name;
	private String code;
	private String description;
	private String isActive;
	
	@Id
	@SequenceGenerator(allocationSize=1,name="salesforce.M_REFERENCE_SEQ",sequenceName="salesforce.M_REFERENCE_SEQ")
	@GeneratedValue(generator="salesforce.M_REFERENCE_SEQ",strategy=GenerationType.SEQUENCE)
	@Column(name="m_reference_id")
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
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
	}
	@Column(name="isactive")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
