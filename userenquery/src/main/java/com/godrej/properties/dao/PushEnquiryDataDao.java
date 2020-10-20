package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.model.Enquiry;
import com.godrej.properties.model.Token;

public interface PushEnquiryDataDao {
	
	public Enquiry insertInputEnquiry(Enquiry enq);

	public Enquiry update(Enquiry entity);
	
	public Enquiry findById(Integer id);
	
	public Enquiry getEnquiryByMobileNo(String mobileNo);

	public List<Enquiry> getEnquiriesByMobileNo(String countryCode,String mobileNo);

	public void syncContactSfidToEnquiry();

	public List<Enquiry> getEnquiriesByMobileNoAndProject(String countryCode,String mobileNo, String projectSfid,String userid);
	
	public Enquiry getEnquiryById(Integer id);
	public List<Enquiry> getEnquiriesByMobileNoAndProject(String countryCode,String mobileNo, String projectSfid,String token,String userid);
	public Token validateMobileAndToken(String mobileNo,String token,String projectSfid);

	public String getAdvertisementForEnquiry(String projectSfid,String mediaType,String mediaSubType);
	public int savePaymentDetails(EnquiryDto enq);

	public List<Enquiry> getEnquiriesByMobileNoAndProjectEOI(String countryCode,String mobileNo, String projectSfid);
	/* Added for CP App - SK*/
	public List<Enquiry> getEnquiriesByCPAppWithParam(String custname,String countryCode,String mobileno,String projectsfid,String emailid);
	public List<Enquiry> getEnquiriesForAffiliateSalesPPortalService(String countryCode,String mobileNo, String projectSfid);
	public List<Enquiry> getSourcingLeadsEnquiryList(String sourcManagerSFID,String projectSfid,String fromdate,String todate);
}
