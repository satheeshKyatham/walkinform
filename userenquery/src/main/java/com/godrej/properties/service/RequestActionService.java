package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.RequestAction;

public interface RequestActionService {

	List<RequestAction> rqstForAdmin (String userId);

	List<RequestAction> getRqstForSales(String userId);

}
