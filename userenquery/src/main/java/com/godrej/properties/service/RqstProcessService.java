package com.godrej.properties.service;

import com.godrej.properties.model.UnitDtl;

public interface RqstProcessService {
	void updateRqst (UnitDtl action);
	
	void updateSendFApproval (UnitDtl action);
}
