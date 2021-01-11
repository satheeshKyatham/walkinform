package com.godrej.properties.master.service;

import java.util.List;

import com.godrej.properties.dto.PhaseDto;

public interface PhaseListService {

	public List<PhaseDto> getPhaseListByProjectCode(String projectCode);

}
