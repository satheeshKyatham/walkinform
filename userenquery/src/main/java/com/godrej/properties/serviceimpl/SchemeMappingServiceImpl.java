package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.SchemeMappingDao;
import com.godrej.properties.model.PaymentDtl;
import com.godrej.properties.model.SchemeMapping;
import com.godrej.properties.model.UnitDtl;
import com.godrej.properties.service.SchemeMappingService;

@Service("schemeMappingService")
@Transactional
public class SchemeMappingServiceImpl implements SchemeMappingService{
	
	@Autowired
	private SchemeMappingDao schemeMappingDao;
	
	@Override
	public SchemeMapping getMappingDtl(int schemeID) {
		return schemeMappingDao.getMappingDtl(schemeID);
	}
	
	@Override
	public void insertSchemeMapping(SchemeMapping schemeMapping) {
		schemeMappingDao.insertSchemeMapping(schemeMapping);
	}
	
	@Override
	public void insertBulkMapping(List<SchemeMapping> mapping) {
		schemeMappingDao.insertBulkMapping(mapping);
	}
}