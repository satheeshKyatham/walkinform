package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nv_eoi_pricesheet_cal",schema="godrejpl")
public class PriceSheet {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") private int id;
	@Column(name="counter_no") private int  counter_no;
	@Column(name="type") private String  type;
	@Column(name="phone_no") private String  phone_no;
	@Column(name="unit_type") private String  unit_type;
	@Column(name="last_bsp") private String  last_bsp;
	@Column(name="new_bsp") private String  new_bsp;
	@Column(name="createddate") private Timestamp  createddate;
	@Column(name="updateddate") private Timestamp  updateddate;
	@Column(name="isactive") private String  isactive;
	@Column(name="updatedby") private String  updatedby;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCounter_no() {
		return counter_no;
	}
	public void setCounter_no(int counter_no) {
		this.counter_no = counter_no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getUnit_type() {
		return unit_type;
	}
	public void setUnit_type(String unit_type) {
		this.unit_type = unit_type;
	}
	public String getLast_bsp() {
		return last_bsp;
	}
	public void setLast_bsp(String last_bsp) {
		this.last_bsp = last_bsp;
	}
	public String getNew_bsp() {
		return new_bsp;
	}
	public void setNew_bsp(String new_bsp) {
		this.new_bsp = new_bsp;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public Timestamp getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(Timestamp updateddate) {
		this.updateddate = updateddate;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

}
