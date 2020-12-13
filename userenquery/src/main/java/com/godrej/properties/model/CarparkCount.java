package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="salesforce.gpl_cs_carpark_type_mst")
public class CarparkCount implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  private int id;
	 
	@Column(name = "carpark_type") private String carpark_type;
	@Column(name = "sold_carpark") private Integer sold_carpark;
	@Column(name = "total_carpark") private Integer total_carpark;
	@Column(name = "carpark_alias") private String carpark_alias;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCarpark_type() {
		return carpark_type;
	}
	public void setCarpark_type(String carpark_type) {
		this.carpark_type = carpark_type;
	}
	public Integer getSold_carpark() {
		return sold_carpark;
	}
	public void setSold_carpark(Integer sold_carpark) {
		this.sold_carpark = sold_carpark;
	}
	public Integer getTotal_carpark() {
		return total_carpark;
	}
	public void setTotal_carpark(Integer total_carpark) {
		this.total_carpark = total_carpark;
	}
	public String getCarpark_alias() {
		return carpark_alias;
	}
	public void setCarpark_alias(String carpark_alias) {
		this.carpark_alias = carpark_alias;
	}
}