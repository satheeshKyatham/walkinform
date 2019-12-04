package com.godrej.properties.dao;

import java.util.Map;

import com.godrej.properties.dto.EnquiryReportDto;
import com.godrej.properties.model.EnquiryReport;
/**
 * 
 * @author Varsha Patil
 *
 */
public interface EnquiryReportDao {

	public EnquiryReport insertEnquiryReport(EnquiryReport enq);

	public EnquiryReport update(EnquiryReport entity);
	
	public EnquiryReport findById(Integer id);
	
	public EnquiryReport getEnquiryReportById(Integer id);
	
	public void updateEnquirySfidToCustomEnquiry(); 
	
	public void updateById(Map<String,Object> params);
	public int updatePaymentDetails(EnquiryReportDto  dto);
}
