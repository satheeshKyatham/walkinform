package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.model.Enquiry;

public interface PushEnquiryDataService {
	
	public Enquiry insertInputEnquiry(Enquiry enq);
	
	public EnquiryDto insertInputEnquiry(EnquiryDto dto);
	
	public EnquiryDto update(EnquiryDto dto);
	
	public EnquiryDto findById(EnquiryDto dto);

	public EnquiryDto getEnquiryByMobileNo(String mobileNo);

	public List<EnquiryDto> getEnquiriesByMobileNo(String countryCode,String mobileNo);

	public void syncContactSfidToEnquiry();

	public List<EnquiryDto> getEnquiriesByMobileNo(String countryCode,String mobileNo, String projectSfid,String userid);
	public EnquiryDto getEnquiryById(EnquiryDto dto);
	public List<EnquiryDto> getEnquiriesByMobileNo(String countryCode,String mobileNo,String projectSfid,String token,String userid);
	public boolean validateMobileAndToken(String mobileNo,String token,String projectSfid);
	
	public String getAdvertisementForEnquiry(EnquiryDto dto);
	public EnquiryDto savePaymentDetails(EnquiryDto enq);

	public List<EnquiryDto> getEnquiriesByMobileNoEOI(String countryCode,String mobileNo, String projectSfid);
	/* Added for CP App - SK*/
	public List<EnquiryDto> getEnquiriesByCPAppWithParam(String custname,String countryCode,String mobileno,String projectsfid,String emailid);
	public List<EnquiryDto> getEnquiriesForAffiliateSalesPPortalService(String countryCode,String mobileNo, String projectSfid);
}
