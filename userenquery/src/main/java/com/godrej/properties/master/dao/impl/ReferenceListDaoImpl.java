package com.godrej.properties.master.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.converter.ReferenceListConverter;
import com.godrej.properties.dao.AAbstractDao;
import com.godrej.properties.master.dao.ReferenceListDao;
import com.godrej.properties.master.dto.ReferenceListDto;
import com.godrej.properties.master.model.ReferenceList;

@Repository
public class ReferenceListDaoImpl extends AAbstractDao<ReferenceList> implements ReferenceListDao {
	
	@Autowired
	private ReferenceListConverter referenceListConverter;
	
	@Override
	@Transactional(readOnly=true)
	public List<ReferenceListDto> getReferenceListByReferenceCode(String referenceCode) {
		StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT c FROM ReferenceList c ")
		.append(" INNER JOIN FETCH c.reference r ")
		.append(" WHERE  r.code=:referenceCode AND c.isActive='Y'");
		Map<String,Object> params=new HashMap<>();
		params.put("referenceCode",referenceCode);
		
		List<ReferenceList> refList=getEntities(jpql.toString(), params);
		
		return referenceListConverter.entityToDto(refList);
	}

}
