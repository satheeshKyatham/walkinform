package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.Enquiry;

public interface EOIUpdateService {
	void updateEOI(List<Enquiry> eoiData);
}