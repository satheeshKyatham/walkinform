package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.CarparkTypeEOIDao;
import com.godrej.properties.model.CarparkTypeEOI;
import com.godrej.properties.service.CarparkTypeEOIService;

@Service("carparkTypeEOIService")
@Transactional
public class CarparkTypeEOIServiceImpl implements CarparkTypeEOIService {

	@Autowired
	CarparkTypeEOIDao carparkTypeEOIDao;

	@Override
	public List<CarparkTypeEOI> getTowerBand(String project_code) {
		return carparkTypeEOIDao.getTowerBand(project_code);
	}
}