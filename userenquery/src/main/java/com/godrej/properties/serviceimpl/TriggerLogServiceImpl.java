package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.TriggerLogDao;
import com.godrej.properties.model.TriggerLog;
import com.godrej.properties.service.TriggerLogService;

@Service("triggerLogService")
@Transactional
public class TriggerLogServiceImpl implements TriggerLogService{
	@Autowired
	TriggerLogDao  triggerLogDao;

	@Override
	public List<TriggerLog> getTriggerLogDtl(String whereCondition) {
		// TODO Auto-generated method stub
		return triggerLogDao.getTriggerLogDtl(whereCondition);
	}
}