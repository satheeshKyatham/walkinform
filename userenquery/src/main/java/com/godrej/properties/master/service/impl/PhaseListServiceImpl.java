package com.godrej.properties.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dto.PhaseDto;
import com.godrej.properties.master.dao.PhaseListDao;
import com.godrej.properties.master.service.PhaseListService;

@Service
public class PhaseListServiceImpl implements PhaseListService{

	@Autowired
	private PhaseListDao phaseListDao;
	@Override
	public List<PhaseDto> getPhaseListByProjectCode(String projectCode) {
		// TODO Auto-generated method stub
		return phaseListDao.getPhaseListByProjectCode(projectCode);
	}

}
