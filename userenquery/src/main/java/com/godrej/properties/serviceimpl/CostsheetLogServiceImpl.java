package com.godrej.properties.serviceimpl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.CostsheetLogDao;
import com.godrej.properties.dto.CostsheetLogDto;
import com.godrej.properties.model.CostsheetLog;
import com.godrej.properties.service.CostsheetLogService;

@Service("costsheetLogService")
@Transactional
public class CostsheetLogServiceImpl implements CostsheetLogService{
	
	@Autowired
	CostsheetLogDao costsheetLogDao;
	
	@Override
	public CostsheetLogDto insertAuditLog(CostsheetLogDto aLogDto) {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String tokenno = null; 
		String contactsfid = null;
		
		String enqsfid = null;
		String carparkType = null;
		
		
		CostsheetLog aLog = new CostsheetLog();
		
		aLog.setSource(aLogDto.getSource());
		aLog.setCostsheet_type(aLogDto.getCostsheet_type());
		aLog.setToken_id(aLogDto.getToken_id());
		aLog.setContact_sfid(aLogDto.getContact_sfid());
		aLog.setEnquiry_sfid(aLogDto.getEnquiry_sfid());
		aLog.setProject_sfid(aLogDto.getProject_sfid());
		aLog.setTower_sfid(aLogDto.getTower_sfid());
		aLog.setInventory_sfid(aLogDto.getInventory_sfid());
		aLog.setGpl_cs_scheme_id(aLogDto.getGpl_cs_scheme_id());
		aLog.setScheme_type(aLogDto.getScheme_type());
		aLog.setScheme_name(aLogDto.getScheme_name());
		
		aLog.setScheme_rate(aLogDto.getScheme_rate());
		aLog.setScheme_absolute(aLogDto.getScheme_absolute());
		aLog.setScheme_percentage(aLogDto.getScheme_percentage());
		aLog.setScheme_zero_govt_charges(aLogDto.getScheme_zero_govt_charges());
		
		aLog.setCarpark_type(aLogDto.getCarpark_type());
		aLog.setCarpark_count(aLogDto.getCarpark_count());
		aLog.setPaymentplan_sfid(aLogDto.getPaymentplan_sfid());
		
		aLog.setCs_sales_comments(aLogDto.getCs_sales_comments());
aLog.setCostsheet_path(aLogDto.getCostsheet_path());
		
		aLog.setCreateddate(timestamp);
		aLog.setCreatedby(aLogDto.getCreatedby());
		aLog.setCreatedbyemail(aLogDto.getCreatedbyemail());
		aLog.setUpdateddate(timestamp);
		aLog.setUpdatedby(aLogDto.getUpdatedby());
		aLog.setIsactive(aLogDto.getIsactive());
		
		//--------------------------------------------
		aLog.setDiscounted_bsp(aLogDto.getDiscounted_bsp());
		aLog.setOg_bsp(aLogDto.getOg_bsp());
		
		aLog.setCarpet_area_sqft(aLogDto.getCarpet_area_sqft());
		aLog.setSaleable_area_sqft(aLogDto.getSaleable_area_sqft());
		aLog.setProperty_name(aLogDto.getProperty_name());
		
		aLog.setCarpet_area_rera_sqmt(aLogDto.getCarpet_area_rera_sqmt());
		aLog.setExclusive_area_sqmt(aLogDto.getExclusive_area_sqmt());
		aLog.setTotal_area_sqmt(aLogDto.getTotal_area_sqmt());
		aLog.setCarpet_area_amount(aLogDto.getCarpet_area_amount());
		
		aLog.setExclusive_area_amount(aLogDto.getExclusive_area_amount());
		aLog.setFlat_unit_cost(aLogDto.getFlat_unit_cost());
		aLog.setTotal_a(aLogDto.getTotal_a());
		aLog.setTotal_b(aLogDto.getTotal_b());
		
		aLog.setStemp_duty_amount(aLogDto.getStemp_duty_amount());
		aLog.setRegistration_amount(aLogDto.getRegistration_amount());
		aLog.setGst_amount(aLogDto.getGst_amount());
		
		aLog.setTotal_c(aLogDto.getTotal_c());
		aLog.setTotal_abc(aLogDto.getTotal_abc());
		aLog.setTotal_discount(aLogDto.getTotal_discount());
		aLog.setPaymentplan_total(aLogDto.getPaymentplan_total());
		
		
		//Parking
		aLog.setParking_selection(aLogDto.getParking_selection());
		aLog.setParking_name(aLogDto.getParking_name());
		aLog.setParking_sfid(aLogDto.getParking_sfid());
		aLog.setParking_amount(aLogDto.getParking_amount());
		//END Parking
		
		//--------------------------------------------
		
		
		/* 
		if (!aLogDto.getContact_sfid().trim().equals("undefined") && !aLogDto.getContact_sfid().trim().equals("")) {
			contactsfid = aLogDto.getContact_sfid();
		}
		if (!aLogDto.getEnquiry_sfid().trim().equals("undefined") && !aLogDto.getEnquiry_sfid().trim().equals("")) {
			enqsfid = aLogDto.getEnquiry_sfid();
		}
		if (!aLogDto.getCarpark_type().trim().equals("undefined") && !aLogDto.getCarpark_type().trim().equals("") && !aLogDto.getCarpark_type().trim().equals("-1")) {
			carparkType = aLogDto.getCarpark_type();
		}
		*/
		
		//Convert Dto to Entity 
		/*
		aLog.setCategory(aLogDto.getCategory());
		aLog.setContact_name(aLogDto.getContact_name());
		aLog.setContact_sfid(contactsfid);
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
		aLog.setCarpark_type(carparkType);
		aLog.setCarpark_count(aLogDto.getCarpark_count());
		aLog.setScheme_type(aLogDto.getScheme_type());
		*/
		
		costsheetLogDao.insertAuditLog(aLog);
		return null;
	}

}
