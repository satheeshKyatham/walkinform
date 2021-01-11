package com.godrej.properties.master.dao;

import java.util.List;

import com.godrej.properties.dto.PhaseDto;

public interface PhaseListDao {

	public List<PhaseDto> getPhaseListByProjectCode(String projectCode);
	
}
