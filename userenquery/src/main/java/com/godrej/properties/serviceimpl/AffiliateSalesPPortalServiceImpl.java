package com.godrej.properties.serviceimpl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.service.AffiliateSalesPPortalService;
import com.godrej.properties.service.PushEnquiryDataService;
import com.godrej.properties.util.DateUtil;

@Service
public class AffiliateSalesPPortalServiceImpl implements AffiliateSalesPPortalService{
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PushEnquiryDataService pushEnquiryDataService;
	
	@Override
	public String getAffiliateSPEnquiries(String countryCode, String mobileNo, String projectSfid) {
		List<EnquiryDto> enquiryList=pushEnquiryDataService.getEnquiriesForAffiliateSalesPPortalService(countryCode, mobileNo, projectSfid);
		String response ="New";
		for(EnquiryDto dto: enquiryList)
		{
			int dateofEnquiry=DateUtil.getDays(dto.getDateOfEnquiry(),new Date());
			int lastmodifydate=DateUtil.getDays(dto.getLastModifiedDate(),new Date());
			if(dateofEnquiry<90 || lastmodifydate<60)
			{
				log.info("Reject");
				return "{\"resp_status\":\"Reject\"}";
			}
			else if(dateofEnquiry>90 || lastmodifydate>60)
			{
				log.info("Create");
			    return "{\"resp_status\":\"Create\"}";
			}
		}
		return "{\"resp_status\":\"Create\"}";
	}

}
