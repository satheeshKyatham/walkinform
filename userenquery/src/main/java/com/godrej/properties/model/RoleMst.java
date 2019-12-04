package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.gpl_cs_role_mst")
public class RoleMst {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpl_cs_role_mst_id")
	private int id;
	
	@Column(name = "user_id")
	private String user_id;
	
	@Column(name = "role_name")
	private String role_name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "isactive")
	private String isactive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
}