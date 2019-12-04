package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.TriggerLog;

public interface TriggerLogService {
	List<TriggerLog> getTriggerLogDtl(String whereCondition);
}