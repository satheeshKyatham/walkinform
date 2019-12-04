package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.RqstProcessDao;
import com.godrej.properties.model.UnitDtl;
import com.godrej.properties.service.RqstProcessService;

@Service("rqstProcessService")
@Transactional
public class RqstProcessServiceImpl implements RqstProcessService {
	@Autowired
	private RqstProcessDao dao;
	
	@Override
	public void updateRqst(UnitDtl action) {
		dao.updateRqst(action);		
	}

	@Override
	public void updateSendFApproval(UnitDtl action) {
		dao.updateSendFApproval(action);		
	}
	
	
}
