package com.godrej.properties.serviceimpl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.converter.EnquiryConverter;
import com.godrej.properties.dao.PushEnquiryDataDao;
import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.model.Enquiry;
import com.godrej.properties.model.Token;
import com.godrej.properties.service.PushEnquiryDataService;

@Service("pushEnqService")
@Transactional
public class PushEnquiryDataServiceImpl  implements PushEnquiryDataService{
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private PushEnquiryDataDao pushEnquiryDataDao;
	
	@Autowired
	private EnquiryConverter enquiryConverter;
	
	@Override
	public Enquiry insertInputEnquiry(Enquiry enq) {
		return pushEnquiryDataDao.insertInputEnquiry(enq);
	}

	@Override
	public EnquiryDto insertInputEnquiry(EnquiryDto dto) {
		if(null == dto){
			return null;
		}
		Enquiry enq=enquiryConverter.dtoToEntity(dto);
		log.info("enquiry insert query started :{}",new Date());
		enq=insertInputEnquiry(enq);
		log.info("enquiry insert query ended :{}",new Date());
		return enquiryConverter.entityToDto(enq);
	}

	@Override
	public EnquiryDto update(EnquiryDto dto) {
		if(dto==null) {
			dto=new EnquiryDto();
		}
		if("CP".equals(dto.getIsReferredByChannelPartnerFlag()))
			dto.setWalkInSource("Channel Partner");
		Enquiry enq=enquiryConverter.dtoToEntity(dto);
		
		enq=pushEnquiryDataDao.update(enq);
		return enquiryConverter.entityToDto(enq);
	}

	@Override
	public EnquiryDto findById(EnquiryDto dto) {
		Enquiry enq=pushEnquiryDataDao.findById(dto.getEnquiryId());
		return enquiryConverter.entityToDto(enq);
	}

	
	@Override
	public EnquiryDto getEnquiryByMobileNo(String mobileNo) {
		Enquiry enq=pushEnquiryDataDao.getEnquiryByMobileNo(mobileNo);
		return enquiryConverter.entityToDto(enq);
	}
	
	@Override
	public List<EnquiryDto> getEnquiriesByMobileNo(String countryCode,String mobileNo) {
		List<Enquiry> enq=pushEnquiryDataDao.getEnquiriesByMobileNo(countryCode,mobileNo);
		return enquiryConverter.entityToDto(enq);
	}
	
	@Override
	public List<EnquiryDto> getEnquiriesByMobileNo(String countryCode,String mobileNo, String projectSfid,String userid) {
		List<Enquiry> enq=pushEnquiryDataDao.getEnquiriesByMobileNoAndProject(countryCode,mobileNo,projectSfid,userid);
		log.info("Enquiry END={}",new Date());
		return enquiryConverter.entityToDto(enq);
	}
	@Override
	public List<EnquiryDto> getEnquiriesByMobileNo(String countryCode,String mobileNo,String projectSfid,String token,String userid) {
		List<Enquiry> enq=pushEnquiryDataDao.getEnquiriesByMobileNoAndProject(countryCode,mobileNo,projectSfid,token,userid);
		
		return enquiryConverter.entityToDto(enq);
	}
	@Override
	public void syncContactSfidToEnquiry() {
		pushEnquiryDataDao.syncContactSfidToEnquiry();
	}

	@Override
	public EnquiryDto getEnquiryById(EnquiryDto dto) {
		Enquiry enq=pushEnquiryDataDao.getEnquiryById(dto.getEnquiryId());
		return enquiryConverter.entityToDto(enq);
	}
	@Override
	public boolean validateMobileAndToken(String mobileNo,String token,String projectSfid){
		Token tk=pushEnquiryDataDao.validateMobileAndToken(mobileNo,token,projectSfid);
		if(null!=tk){
			return true;
		}
		return false;
	}

	@Override
	public String getAdvertisementForEnquiry(EnquiryDto dto) {
		String mediaType="";
		String mediaSubType="";
		String mediapPhaseId="";
		String projectSfid=dto.getProject()==null?"":dto.getProject().getSfid();
		
		if("Partner".equals(dto.getIsReferredByChannelPartner()) && ((null!=dto.getChannelPartner() && null!=dto.getBrokerContact()) || (null!=dto.getOtherChannelPartner()))){
			mediaType="Channel Partner";
			mediaSubType="CP - Walk-in";
			if(dto.getPhasedto()!=null && dto.getPhasedto().getSfid()!=null)
			{
				//getAdvertisementForEnquiryWithPhase
				mediapPhaseId=dto.getPhasedto().getSfid();
			}
		}else if("Direct".equals(dto.getIsReferredByChannelPartner()) && null!=dto.getWalkInSource()){
			mediaType="Walkin";
			mediaSubType="Walkin";
			if(dto.getPhasedto()!=null && dto.getPhasedto().getSfid()!=null)
			{
				//getAdvertisementForEnquiryWithPhase
				mediapPhaseId=dto.getPhasedto().getSfid();
			}
		}
		else if("Employee".equals(dto.getIsReferredByChannelPartner()) && null!=dto.getWalkInSource())
		{
			/*mediaType="Employee";
			mediaSubType="Employee Referral";*/
			mediaType="Referral";
			mediaSubType="Employee Referral";
			if(dto.getPhasedto()!=null && dto.getPhasedto().getSfid()!=null)
			{
				mediapPhaseId=dto.getPhasedto().getSfid();
			}
		}
		else if("Referral".equals(dto.getIsReferredByChannelPartner()) && null!=dto.getWalkInSource())
		{
			mediaType="Referral";
			mediaSubType="Customer Referral";
			if(dto.getPhasedto()!=null && dto.getPhasedto().getSfid()!=null)
			{
				mediapPhaseId=dto.getPhasedto().getSfid();
			}
		}
		else if("Loyalty".equals(dto.getIsReferredByChannelPartner()) && null!=dto.getWalkInSource())
		{
			mediaType="Referral";
			mediaSubType="Loyalty customers";
			if(dto.getPhasedto()!=null && dto.getPhasedto().getSfid()!=null)
			{
				mediapPhaseId=dto.getPhasedto().getSfid();
			}
		}
		else
		{
			mediaType="Walkin";
			mediaSubType="Walkin";
			if(dto.getPhasedto()!=null)
			{
				mediapPhaseId=dto.getPhasedto().getSfid();
			}
		}
		if(mediapPhaseId!=null)
		{
			String phaseIDVal = pushEnquiryDataDao.getAdvertisementForEnquiryWithPhase(projectSfid, mediaType, mediaSubType, mediapPhaseId);
			if(phaseIDVal!=null && phaseIDVal.length()>0)
			{
				return phaseIDVal;
			}
			else
				return pushEnquiryDataDao.getAdvertisementForEnquiry(projectSfid, mediaType, mediaSubType);
		}
		else
			return pushEnquiryDataDao.getAdvertisementForEnquiry(projectSfid, mediaType, mediaSubType);
	}

	@Override
	public EnquiryDto savePaymentDetails(EnquiryDto enq) {		
		int count=pushEnquiryDataDao.savePaymentDetails(enq);
        if(count>0) {        	
           enq.setHasError(false);
        }else {
        	enq.setHasError(true);
        }
		return enq;
	}

	@Override
	public List<EnquiryDto> getEnquiriesByMobileNoEOI(String countryCode,String mobileNo, String projectSfid) {

		List<Enquiry> enq=pushEnquiryDataDao.getEnquiriesByMobileNoAndProjectEOI(countryCode,mobileNo,projectSfid);
		return enquiryConverter.entityToDto(enq);
	
	}
	/* Added for CP App - SK*/
	@Override
	public List<EnquiryDto> getEnquiriesByCPAppWithParam(String custname,String countryCode,String mobileno,String projectsfid,String emailid) {
		List<Enquiry> enq=pushEnquiryDataDao.getEnquiriesByCPAppWithParam(custname,countryCode,mobileno,projectsfid,emailid);
		log.info("Enquiry END={}",new Date());
		return enquiryConverter.entityToDto(enq);
	}

	@Override
	public List<EnquiryDto> getEnquiriesForAffiliateSalesPPortalService(String countryCode, String mobileNo,
			String projectSfid) {
		List<Enquiry> enq=pushEnquiryDataDao.getEnquiriesForAffiliateSalesPPortalService(countryCode,mobileNo,projectSfid);
		log.info("Enquiry END={}",new Date());
		return enquiryConverter.entityToDto(enq);
	}

	@Override
	public List<EnquiryDto> getSourcingLeadsEnquiryList(String sourcManagerSFID, String projectSfid, String fromdate,
			String todate) {
		// TODO Auto-generated method stub
		List<Enquiry> enq= pushEnquiryDataDao.getSourcingLeadsEnquiryList(sourcManagerSFID, projectSfid, fromdate, todate);
		return enquiryConverter.entityToDto(enq);
	}

}
