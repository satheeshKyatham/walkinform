package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "salesforce.nv_user_ad_pass") 
public class ADLoginPass {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id") private int	id;
	
	@Column(name="emailid") private String	emailid;
	@Column(name="isactive") private  String isactive ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	    
	
	
}
