package com.godrej.properties.model;

public class DashboardReport {
	
	private String todayAssigned;
	private Integer totalPendingLead;
	private String todayFollowup;
	private String totalPendingKYCApproval;
	private String totalCreatedOffer;
	private String totalBookingDone;
	
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
	public String getTotalPendingKYCApproval() {
		return totalPendingKYCApproval;
	}
	public void setTotalPendingKYCApproval(String totalPendingKYCApproval) {
		this.totalPendingKYCApproval = totalPendingKYCApproval;
	}
	public String getTotalCreatedOffer() {
		return totalCreatedOffer;
	}
	public void setTotalCreatedOffer(String totalCreatedOffer) {
		this.totalCreatedOffer = totalCreatedOffer;
	}
	public String getTotalBookingDone() {
		return totalBookingDone;
	}
	public void setTotalBookingDone(String totalBookingDone) {
		this.totalBookingDone = totalBookingDone;
	}
}