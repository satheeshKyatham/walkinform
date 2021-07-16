package com.godrej.properties.service;

import com.godrej.properties.model.DashboardReport;

public interface DashboardService {
	public DashboardReport getDashboard(String projectSFID, String userid);
}