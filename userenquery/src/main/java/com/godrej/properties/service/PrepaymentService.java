package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.Prepayment;

public interface PrepaymentService {

	public List<Prepayment> getPrepayments(String offerSfid);
}
