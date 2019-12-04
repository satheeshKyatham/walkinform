package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.TriggerLogArchive;

public interface TriggerLogArchiveDao {
	List<TriggerLogArchive>getTriggerLogArchiveDtl(String whereCondition);
}
