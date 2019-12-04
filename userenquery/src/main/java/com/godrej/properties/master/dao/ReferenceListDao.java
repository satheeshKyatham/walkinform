package com.godrej.properties.master.dao;

import java.util.List;

import com.godrej.properties.master.dto.ReferenceListDto;

public interface ReferenceListDao {

	public List<ReferenceListDto> getReferenceListByReferenceCode(String referenceCode);
	
}
