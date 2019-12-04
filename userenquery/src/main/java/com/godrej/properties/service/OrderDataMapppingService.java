package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.OrderDataMapping;
import com.godrej.properties.model.ValueOldNew;

public interface OrderDataMapppingService {

	List<OrderDataMapping> getdataMapping(String str_date);

	List<OrderDataMapping> getdataMapping();

	List<ValueOldNew> old_newValueDataSysnc();

}
