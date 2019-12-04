package com.godrej.properties.dao;

import com.godrej.properties.model.UnitDtl;

public interface RqstProcessDao {

	void updateRqst (UnitDtl action);
	
	void updateSendFApproval (UnitDtl action);

}
