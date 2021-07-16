package com.godrej.properties.dao;

import com.godrej.properties.model.DashboardReport;

public interface DashboardDao {
	 public DashboardReport getDashboard(String projectSFID, String userid);
}