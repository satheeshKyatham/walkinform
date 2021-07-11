package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.AssignedUser;

public interface AssignUserService {

	List<AssignedUser> getassignedusers(String user_id, String projectId,String fromdate,String todate, String source);

}
