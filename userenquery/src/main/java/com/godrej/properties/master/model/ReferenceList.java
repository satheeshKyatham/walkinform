package com.godrej.properties.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.godrej.properties.common.model.CommonModel;

@Entity
@Table(name="salesforce.m_reference_list")
public class ReferenceList extends CommonModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String referenceListId;
	private String name;
	private String code;
	private String description;
	private Reference reference;
	private String isActive;
	
	@Id
	@SequenceGenerator(sequenceName="salesforce.M_REFERENCE_LIST_SEQ",allocationSize=1,name="salesforce.M_REFERENCE_LIST_SEQ")
	@GeneratedValue(generator="salesforce.M_REFERENCE_LIST_SEQ",strategy=GenerationType.SEQUENCE)
	@Column(name="m_reference_list_id")
	public String getReferenceListId() {
		return referenceListId;
	}
	public void setReferenceListId(String referenceListId) {
		this.referenceListId = referenceListId;
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_reference_id")
	public Reference getReference() {
		return reference;
	}
	public void setReference(Reference reference) {
		this.reference = reference;
	}
    @Column(name="isactive")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
