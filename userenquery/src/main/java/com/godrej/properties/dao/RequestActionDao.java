package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.RequestAction;

public interface RequestActionDao {

	List<RequestAction> rqstForAdmin (String userId);
	
	List<RequestAction> getRqstForSales (String userId);

}
