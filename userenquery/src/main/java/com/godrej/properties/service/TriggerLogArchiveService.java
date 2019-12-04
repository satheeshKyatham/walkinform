package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.TriggerLogArchive;

public interface TriggerLogArchiveService {
	List<TriggerLogArchive> getTriggerLogArchiveDtl(String whereCondition);
}
