package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.AssignedUser;

public interface AssignUserDao {

	List<AssignedUser> getassignedusers(String user_id, String projectId,String fromdate,String todate, String source);

}
