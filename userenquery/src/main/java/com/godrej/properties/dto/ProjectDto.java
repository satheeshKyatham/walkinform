package com.godrej.properties.dto;

import com.godrej.properties.common.dto.CommonDto;

public class ProjectDto extends CommonDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String projectId;
	private String name;
	private String code;
	private String description;
	private String pname;
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}	
	
}
