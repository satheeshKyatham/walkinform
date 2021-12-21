package com.godrej.properties.dto;

public class SoldCarParkDTO {
	
	int id;
	String category_Name;
	Integer sold_parking_count;
	Integer total_parking_count;
	Integer alloted_through_offer_count;
	public String getCategory_Name() {
		return category_Name;
	}
	public void setCategory_Name(String category_Name) {
		this.category_Name = category_Name;
	}
	
	
	public Integer getSold_parking_count() {
		return sold_parking_count;
	}
	public void setSold_parking_count(Integer sold_parking_count) {
		this.sold_parking_count = sold_parking_count;
	}
	public Integer getTotal_parking_count() {
		return total_parking_count;
	}
	public void setTotal_parking_count(Integer total_parking_count) {
		this.total_parking_count = total_parking_count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getAlloted_through_offer_count() {
		return alloted_through_offer_count;
	}
	public void setAlloted_through_offer_count(Integer alloted_through_offer_count) {
		this.alloted_through_offer_count = alloted_through_offer_count;
	}
	
	

}
