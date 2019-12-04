package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.RequestActionDao;
import com.godrej.properties.model.RequestAction;
import com.godrej.properties.service.RequestActionService;

@Service("requestActionService")
@Transactional
public class RequestActionServiceImpl implements RequestActionService {

	@Autowired
	private RequestActionDao dao;
	
	public List<RequestAction> rqstForAdmin(String userId) {
		// TODO Auto-generated method stub
		return dao.rqstForAdmin(userId);
	}
	
	public List<RequestAction> getRqstForSales(String userId) {
		return dao.getRqstForSales(userId);
	}
}
