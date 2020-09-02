package com.godrej.properties.serviceimpl;

import java.sql.Timestamp;

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
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String tokenno = null; 
		String contactsfid = null;
		
		String enqsfid = null;
		//String carparkType = null;
		
		
		AuditLog aLog = new AuditLog();
		
		if (!aLogDto.getToken_no().trim().equals("undefined") && !aLogDto.getToken_no().trim().equals("")) {
			tokenno = aLogDto.getToken_no(); 
		}
		if (!aLogDto.getContact_sfid().trim().equals("undefined") && !aLogDto.getContact_sfid().trim().equals("")) {
			contactsfid = aLogDto.getContact_sfid();
		}
		if (!aLogDto.getEnquiry_sfid().trim().equals("undefined") && !aLogDto.getEnquiry_sfid().trim().equals("")) {
			enqsfid = aLogDto.getEnquiry_sfid();
		}
		
		//Convert Dto to Entity 
		aLog.setCategory(aLogDto.getCategory());
		aLog.setContact_name(aLogDto.getContact_name());
		aLog.setContact_sfid(contactsfid);
//		aLog.setCreated_date(aLogDto.getCreated_date());
		aLog.setCreated_date(null);
		aLog.setEnquiry_no(aLogDto.getEnquiry_no());
		aLog.setEnquiry_sfid(enqsfid);
		aLog.setInventory_name(aLogDto.getInventory_name());
		aLog.setInventory_sfid(aLogDto.getInventory_sfid());
		aLog.setPaymentplan_name(aLogDto.getPaymentplan_name());
		aLog.setPaymentplan_sfid(aLogDto.getPaymentplan_sfid());
		aLog.setProject_name(aLogDto.getProject_name());
		aLog.setProject_sfid(aLogDto.getProject_sfid());
		aLog.setScheme_name(aLogDto.getScheme_name());
		aLog.setScheme_rate(aLogDto.getScheme_rate());
		aLog.setToken_id(aLogDto.getToken_id());
		aLog.setToken_no(tokenno);
		aLog.setTower_name(aLogDto.getTower_name());
		aLog.setTower_sfid(aLogDto.getTower_sfid());
		aLog.setUser_email(aLogDto.getUser_email());
		aLog.setUser_machine_details(aLogDto.getUser_machine_details());
		aLog.setUser_id(aLogDto.getUser_id());
		
		
		aLog.setSource(aLogDto.getSource());
		aLog.setCreated_date(timestamp);
		//aLog.setCarpark_type(carparkType);
		//aLog.setCarpark_count(aLogDto.getCarpark_count());
		//aLog.setScheme_type(aLogDto.getScheme_type());
		
		
		auditLogDao.insertAuditLog(aLog);
		return null;
	}

}
