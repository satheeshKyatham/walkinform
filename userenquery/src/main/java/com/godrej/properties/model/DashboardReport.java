package com.godrej.properties.model;

public class DashboardReport {
	
	private String todayAssigned;
	private Integer totalPendingLead;
	private String todayFollowup;
	public String getTodayAssigned() {
		return todayAssigned;
	}
	public void setTodayAssigned(String todayAssigned) {
		this.todayAssigned = todayAssigned;
	}
	public Integer getTotalPendingLead() {
		return totalPendingLead;
	}
	public void setTotalPendingLead(Integer totalPendingLead) {
		this.totalPendingLead = totalPendingLead;
	}
	public String getTodayFollowup() {
		return todayFollowup;
	}
	public void setTodayFollowup(String todayFollowup) {
		this.todayFollowup = todayFollowup;
	}
}