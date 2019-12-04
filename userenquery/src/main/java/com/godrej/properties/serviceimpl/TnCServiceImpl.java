package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.TnCDao;
import com.godrej.properties.model.TnC;
import com.godrej.properties.service.TnCService;

@Service("tnCService")
@Transactional
public class TnCServiceImpl implements TnCService{
	
	@Autowired
	private TnCDao dao;
	
	public void insertTNCForPP(TnC tnC) {
		dao.insertTNCForPP(tnC);
	}
	
	
	@Override
	public List<TnC> getTncData(String ppId, String projectid) {
		// TODO Auto-generated method stub
		return dao.getTncData(ppId, projectid);
	}
}
