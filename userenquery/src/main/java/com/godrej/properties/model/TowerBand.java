package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.nv_project_floor_range")
public class TowerBand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nv_project_floor_range_id")
	private int nvProjectFloorRangeId;
	@Column(name = "projectid")
	private String projectid;

	@Column(name = "floorid")
	private String floorid;
	@Column(name = "name")
	private String name;
	@Column(name = "isactive")
	private String isactive;

	public int getNvProjectFloorRangeId() {
		return nvProjectFloorRangeId;
	}

	public void setNvProjectFloorRangeId(int nvProjectFloorRangeId) {
		this.nvProjectFloorRangeId = nvProjectFloorRangeId;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getFloorid() {
		return floorid;
	}

	public void setFloorid(String floorid) {
		this.floorid = floorid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
