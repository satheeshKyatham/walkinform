package com.godrej.properties.dao;

import java.util.List;
import com.godrej.properties.model.TnC;

public interface TnCDao {
	void insertTNCForPP (TnC tnC);
	List<TnC> getTncData (String ppId, String projectid, String tower_sfid);
	boolean deleteTncQuery(int id);
	List<TnC> getSalesTncDataQuery(String pymt_plan_id, String project_id, String tower_sfid);
	List<TnC> getSearchTncByQuery(String ppId, String projectid, String tower_sfid);
	
	
}
