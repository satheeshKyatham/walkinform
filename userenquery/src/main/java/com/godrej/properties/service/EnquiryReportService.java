package com.godrej.properties.service;

import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.dto.EnquiryReportDto;

/**
 * 
 * @author Varsha Patil
 *
 */
public interface EnquiryReportService {

    public EnquiryReportDto save(EnquiryReportDto dto);
    
    public EnquiryReportDto saveEnquiryReport(EnquiryReportDto dto);
	
	public EnquiryReportDto findById(EnquiryReportDto dto);
	
	public EnquiryReportDto getEnquiryReportById(EnquiryReportDto dto);
	
	public EnquiryReportDto update(EnquiryReportDto src,EnquiryReportDto dest);
	
	public EnquiryReportDto update(EnquiryReportDto dto);
	
	public EnquiryReportDto updateBaseInfo(EnquiryReportDto src, EnquiryReportDto dest);

	public EnquiryReportDto updateAddressInfo(EnquiryReportDto src, EnquiryReportDto dest);
	
	public void updateEnquirySfidToCustomEnquiry();
	
	public void updateById(EnquiryDto dto);
	public int updatePaymentDetails(EnquiryReportDto dto);
}
