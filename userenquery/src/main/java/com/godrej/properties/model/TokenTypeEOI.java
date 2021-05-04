package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.nv_eoi_token_type")
public class TokenTypeEOI implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") private int id;	
	@Column(name = "project_sfid") private String project_sfid;
	@Column(name = "tokenname") private String tokenname;
	@Column(name = "tokentype") private String tokentype;
	@Column(name = "isactive") private String isactive;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProject_sfid() {
		return project_sfid;
	}
	public void setProject_sfid(String project_sfid) {
		this.project_sfid = project_sfid;
	}
	public String getTokenname() {
		return tokenname;
	}
	public void setTokenname(String tokenname) {
		this.tokenname = tokenname;
	}
	public String getTokentype() {
		return tokentype;
	}
	public void setTokentype(String tokentype) {
		this.tokentype = tokentype;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
}