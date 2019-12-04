package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.TriggerLog;

public interface TriggerLogDao {
	List<TriggerLog>getTriggerLogDtl(String whereCondition);
}
