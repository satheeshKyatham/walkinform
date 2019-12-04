package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.AuditLogDao;
import com.godrej.properties.dto.AuditLogDto;
import com.godrej.properties.model.AuditLog;
import com.godrej.properties.service.AuditLogService;

@Service("auditLogService")
@Transactional
public class AuditLogServiceImpl implements AuditLogService{

	@Autowired
	AuditLogDao auditLogDao;
	
	@Override
	public AuditLogDto insertAuditLog(AuditLogDto aLogDto) {
		AuditLog aLog = new AuditLog();
		//Convert Dto to Entity 
		aLog.setCategory(aLogDto.getCategory());
		aLog.setContact_name(aLogDto.getContact_name());
		aLog.setContact_sfid(aLogDto.getContact_sfid());
//		aLog.setCreated_date(aLogDto.getCreated_date());
		aLog.setCreated_date(null);
		aLog.setEnquiry_no(aLogDto.getEnquiry_no());
		aLog.setEnquiry_sfid(aLogDto.getEnquiry_sfid());
		aLog.setInventory_name(aLogDto.getInventory_name());
		aLog.setInventory_sfid(aLogDto.getInventory_sfid());
		aLog.setPaymentplan_name(aLogDto.getPaymentplan_name());
		aLog.setPaymentplan_sfid(aLogDto.getPaymentplan_sfid());
		aLog.setProject_name(aLogDto.getProject_name());
		aLog.setProject_sfid(aLogDto.getProject_sfid());
		aLog.setScheme_name(aLogDto.getScheme_name());
		aLog.setScheme_rate(aLogDto.getScheme_rate());
		aLog.setToken_id(aLogDto.getToken_id());
		aLog.setToken_no(aLogDto.getToken_no());
		aLog.setTower_name(aLogDto.getTower_name());
		aLog.setTower_sfid(aLogDto.getTower_sfid());
		aLog.setUser_email(aLogDto.getUser_email());
		aLog.setUser_machine_details(aLogDto.getUser_machine_details());
		aLog.setUser_id(aLogDto.getUser_id());
		auditLogDao.insertAuditLog(aLog);
		return null;
	}

}
