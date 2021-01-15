package com.godrej.properties.master.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.converter.PhaseConverter;
import com.godrej.properties.converter.ReferenceListConverter;
import com.godrej.properties.dao.AAbstractDao;
import com.godrej.properties.dto.PhaseDto;
import com.godrej.properties.master.dao.PhaseListDao;
import com.godrej.properties.master.dao.ReferenceListDao;
import com.godrej.properties.master.dto.ReferenceListDto;
import com.godrej.properties.master.model.ReferenceList;
import com.godrej.properties.model.Phase;

@Repository
public class PhaseListDaoImpl extends AAbstractDao<Phase> implements PhaseListDao {
	
	@Autowired
	private PhaseConverter phaseConverter;
	
	@Override
	@Transactional(readOnly=true)
	public List<PhaseDto> getPhaseListByProjectCode(String projectCode) {
		StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT p FROM Phase p ")
		.append(" WHERE  p.project__c=:projectCode order by p.name asc");
		Map<String,Object> params=new HashMap<>();
		params.put("projectCode",projectCode);
		
		List<Phase> refList=getEntities(jpql.toString(), params);
		
		return phaseConverter.entityToDto(refList);
	}

}
