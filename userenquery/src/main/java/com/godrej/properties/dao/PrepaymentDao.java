package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.Prepayment;

public interface PrepaymentDao {

	public List<Prepayment> getPrepayments(String offerId);
}
