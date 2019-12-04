package com.godrej.properties.master.service;

import java.util.List;

import com.godrej.properties.master.dto.ReferenceListDto;

public interface ReferenceListService {

	public List<ReferenceListDto> getReferenceListByReferenceCode(String referenceCode);

}
