package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.TriggerLogArchiveDao;
import com.godrej.properties.model.TriggerLogArchive;
import com.godrej.properties.service.TriggerLogArchiveService;

@Service("triggerLogArchiveService")
@Transactional
public class TriggerLogArchiveServiceImpl implements TriggerLogArchiveService{
	@Autowired
	TriggerLogArchiveDao  triggerLogArchiveDao;

	@Override
	public List<TriggerLogArchive> getTriggerLogArchiveDtl(String whereCondition) {
		// TODO Auto-generated method stub
		return triggerLogArchiveDao.getTriggerLogArchiveDtl(whereCondition);
	}
}