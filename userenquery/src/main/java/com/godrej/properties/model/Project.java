package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.godrej.properties.common.model.CommonModel;

@Entity
@Table(name="salesforce.PropStrength__Projects__c")
public class Project extends CommonModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String projectId;
	private String pname;
	/*private String name;
	private String code;
	private String description;
	private String region;*/

	@Id
	/*@SequenceGenerator(name = "t_project_seq", sequenceName = "t_project_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_project_seq")*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	@Column(name="name")
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	

	/*@Column(name="name")
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

	@Column(name="region__c")
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}*/
	
}
