package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.CarparkTypeMst;
import com.godrej.properties.model.SchemePromotional;

public interface CarparkTypeMstService {
	List<CarparkTypeMst> getCarparkType(String projectSFID);
	void insertCarparkDtl(CarparkTypeMst carparkTypeMst);
}
