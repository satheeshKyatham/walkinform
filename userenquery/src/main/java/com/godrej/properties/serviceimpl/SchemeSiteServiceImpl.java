package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.SchemeSiteDao;
import com.godrej.properties.model.SchemeSite;
import com.godrej.properties.service.SchemeSiteService;

@Service("schemeSiteService")
@Transactional
public class SchemeSiteServiceImpl implements SchemeSiteService{
	
	@Autowired
	private SchemeSiteDao schemeSiteDao;
	
	@Override
	public List<SchemeSite> getSchemeSite(String projectId, String cp_flag_yn) {
		return schemeSiteDao.getSchemeSite(projectId, cp_flag_yn);
	}
	
	@Override
	public void insertSchemeSite(SchemeSite schemeSite) {
		schemeSiteDao.insertSchemeSite(schemeSite);
	}
}