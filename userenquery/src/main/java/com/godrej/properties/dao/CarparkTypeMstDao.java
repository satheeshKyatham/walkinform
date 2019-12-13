package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.CarparkTypeMst;
import com.godrej.properties.model.SchemePromotional;

public interface CarparkTypeMstDao {
	List<CarparkTypeMst> getCarparkType(String projectSFID);
	
	void insertCarparkDtl(CarparkTypeMst carparkTypeMst);
}
