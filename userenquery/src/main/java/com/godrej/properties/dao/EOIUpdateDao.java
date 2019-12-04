package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.Enquiry;

public interface EOIUpdateDao {
	void updateEOI(List<Enquiry> eoiRec);
}
