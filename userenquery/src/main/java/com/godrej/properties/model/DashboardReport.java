package com.godrej.properties.model;

public class DashboardReport {
	
	private String todayAssigned;
	//private Integer totalPendingLead;
	private String totalPendingLead;
	private String todayFollowup;
	//private String totalPendingKYCApproval;
	//private String totalCreatedOffer;
	//private String totalBookingDone;
	
	public String getTodayAssigned() {
		return todayAssigned;
	}
	public void setTodayAssigned(String todayAssigned) {
		this.todayAssigned = todayAssigned;
	}
	public String getTotalPendingLead() {
		return totalPendingLead;
	}
	public void setTotalPendingLead(String totalPendingLead) {
		this.totalPendingLead = totalPendingLead;
	}
	public String getTodayFollowup() {
		return todayFollowup;
	}
	public void setTodayFollowup(String todayFollowup) {
		this.todayFollowup = todayFollowup;
	} 
}