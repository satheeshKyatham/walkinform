package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.TnCEOIDao;
import com.godrej.properties.model.TnC;
import com.godrej.properties.model.TnCEOI;
import com.godrej.properties.service.TnCEOIService;

@Service("tnCEOIService")
@Transactional
public class TnCEOIServiceImpl implements TnCEOIService{
	
	@Autowired
	private TnCEOIDao dao;
	
	public void insertTNCForEOI(TnCEOI tnCEOI) {
		dao.insertTNCForEOI(tnCEOI);
	}
	
	@Override
	public List<TnCEOI> getTncData(String projectid) {
		return dao.getTncData(projectid);
	}
}
