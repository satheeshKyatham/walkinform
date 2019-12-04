package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.SchemePromotionalDao;
import com.godrej.properties.model.SchemePromotional;
import com.godrej.properties.service.SchemePromotionalService;

@Service("schemePromotionalService")
@Transactional
public class SchemePromotionalServiceImpl implements SchemePromotionalService{
	
	@Autowired
	private SchemePromotionalDao schemePromotionalDao;
	
	@Override
	public List<SchemePromotional> getSchemePromotional(String projectId, String cp_flag_yn) {
		return schemePromotionalDao.getSchemePromotional(projectId, cp_flag_yn);
	}
	
	@Override
	public void insertSchemePromotional(SchemePromotional schemePromotional) {
		schemePromotionalDao.insertSchemePromotional(schemePromotional);
	}
}