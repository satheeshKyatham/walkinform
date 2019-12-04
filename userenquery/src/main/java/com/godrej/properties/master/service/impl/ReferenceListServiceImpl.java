package com.godrej.properties.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.master.dao.ReferenceListDao;
import com.godrej.properties.master.dto.ReferenceListDto;
import com.godrej.properties.master.service.ReferenceListService;

@Service
public class ReferenceListServiceImpl implements ReferenceListService {

	@Autowired
	private ReferenceListDao referenceListDao;
	
	@Override
//	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ReferenceListDto> getReferenceListByReferenceCode(String referenceCode){
		return referenceListDao.getReferenceListByReferenceCode(referenceCode);
	}
	
}
