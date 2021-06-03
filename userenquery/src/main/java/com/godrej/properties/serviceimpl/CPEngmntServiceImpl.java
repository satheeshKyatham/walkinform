package com.godrej.properties.serviceimpl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.CPEngmntDao;
import com.godrej.properties.dto.CPEngmntDto;
import com.godrej.properties.model.CPEngmnt;
import com.godrej.properties.model.CPEngmntReport;
import com.godrej.properties.service.CPEngmntService;

@Service("cPEngmntService")
@Transactional
public class CPEngmntServiceImpl implements CPEngmntService{
	
	@Autowired
	CPEngmntDao cPEngmntDao;
	
	@Override
	public CPEngmntDto insertAuditLog(CPEngmntDto aLogDto) {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateInString = aLogDto.getVisit_date();
        Date date = null;
        Integer cpid = null;
        String cpsfid = null;
		try {
			
			if (!aLogDto.getCpid().equals("")) {
				cpid = Integer.parseInt(aLogDto.getCpid());
			} else {
				cpid = null;
			}
			
			if (!aLogDto.getCpsfid().equals("")) {
				cpsfid = aLogDto.getCpsfid();
			} else {
				cpsfid = null;
			}
			
			
			
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println(formatter.format(date));
		
		CPEngmnt aLog = new CPEngmnt();
		
		aLog.setVisit_date(date);
		aLog.setProject_sfid(aLogDto.getProject_sfid());
		aLog.setCp_name(aLogDto.getCp_name());
		aLog.setMeeting_place(aLogDto.getMeeting_place());
		aLog.setTopic(aLogDto.getTopic());
		aLog.setDiscussed_point(aLogDto.getDiscussed_point());
		
		aLog.setCreatedby(Integer.parseInt(aLogDto.getCreatedby()));
		aLog.setUpdatedby(Integer.parseInt(aLogDto.getUpdatedby()));
		 
		aLog.setCreateddate(timestamp);
		aLog.setUpdateddate(timestamp);
		
		aLog.setCpid(cpid);
		aLog.setCpsfid(cpsfid);
		 
		aLog.setIsactive("Y");
		
		
		cPEngmntDao.insertAuditLog(aLog);
		return null;
	}
	
	@Override
	public List<CPEngmntReport> getReportDtl(String whereCondition) {
		return cPEngmntDao.getReportDtl(whereCondition);
	}
}