package com.godrej.properties.service;

import java.util.List;
import com.godrej.properties.model.TnC;

public interface TnCService {
	void insertTNCForPP (TnC tnC);
	List<TnC> getTncData (String ppId, String projectid, String tower_sfid);
	boolean deleteTncData(int id);
	List<TnC> getSalesTncData(String pymt_plan_id, String project_id, String tower_sfid);
	List<TnC> getSearchTncData(String pymtPlanId, String proId, String towerId);
}
