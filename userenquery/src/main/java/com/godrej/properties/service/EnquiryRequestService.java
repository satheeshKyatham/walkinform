package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.dto.EnquiryDto;

public interface EnquiryRequestService {

	public EnquiryDto save(EnquiryDto dto);
	
	public EnquiryDto findById(EnquiryDto dto);
	
	public EnquiryDto updateBaseInfo(EnquiryDto src,EnquiryDto dest);
	
	public EnquiryDto updateAddressInfo(EnquiryDto src,EnquiryDto dest);

	/*public EnquiryDto updateRequirementInfo(EnquiryDto src,EnquiryDto dest);

	public EnquiryDto updateOtherInfo(EnquiryDto src, EnquiryDto dest);
	*/
	public EnquiryDto getEnquiryByMobileNo(String mobileNo);

	public List<EnquiryDto> getEnquiriesByMobileNo(String countryCode,String mobileNo);

	public List<EnquiryDto> getEnquiriesByMobileNo(String countryCode,String mobileNo, String projectSfid,String userid);
	public EnquiryDto getEnquiryById(EnquiryDto dto);
	public List<EnquiryDto> getEnquiriesByMobileNo(String countryCode,String mobileNo,String projectSfid,String token,String userid);
	public boolean validateMobileAndToken(String mobileNo,String token,String projectSfid);
	public EnquiryDto savePaymentDetails(EnquiryDto enq);

	public List<EnquiryDto> getEnquiriesByMobileNoEOI(String countryCode,String mobileNo, String projectSfid);
	/* Added for CP App - Satheesh Kyatham*/
	public List<EnquiryDto> getEnquiriesByCPAppWithParam(String custname,String countryCode,String mobileno,String projectsfid,String emailid);
	public List<EnquiryDto> getSourcingLeadsEnquiryList(String sourcManageremail,String projectSfid,String fromdate,String todate);
}
