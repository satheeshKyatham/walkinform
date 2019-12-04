package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.OrderDataMapping;

public interface OrderDataMappingDao {

	List<OrderDataMapping> getdataMapping();

	List<OrderDataMapping> getdataMapping(String str_date);

}
