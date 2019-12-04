package com.godrej.properties.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.converter.EnquiryReportConverter;
import com.godrej.properties.dao.EnquiryReportDao;
import com.godrej.properties.dao.EnquiryReportLogDao;
import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.dto.EnquiryReportDto;
import com.godrej.properties.model.EnquiryReport;
import com.godrej.properties.model.EnquiryReportLog;
import com.godrej.properties.service.EnquiryReportService;

/**
 * 
 * @author Varsha Patil
 *
 */
@Service
public class EnquiryReportServiceImpl implements EnquiryReportService{
    
	@Autowired
	private EnquiryReportDao enquiryReportDao;
	@Autowired
	private EnquiryReportLogDao enquiryReportLogDao;
	@Autowired
	private EnquiryReportConverter enquiryReportConverter; 
	
	@Override
	@Transactional
	public EnquiryReportDto save(EnquiryReportDto dto) {
		EnquiryReport enquiryReport=enquiryReportConverter.dtoToEntity(dto);
		System.out.println("EnquiryReportDto query Start :"+new Date());
		enquiryReport=enquiryReportDao.insertEnquiryReport(enquiryReport);
		System.out.println("EnquiryReportDto query END :"+new Date());
		return enquiryReportConverter.entityToDto(enquiryReport);
	}

	@Override
	@Transactional
	public EnquiryReportDto findById(EnquiryReportDto dto) {
		EnquiryReport enquiryReport=enquiryReportDao.findById(dto.getEnquiryReportId());
		return enquiryReportConverter.entityToDto(enquiryReport);
	}

	@Override
	@Transactional
	public EnquiryReportDto update(EnquiryReportDto src, EnquiryReportDto dest) {
		/*EnquiryReport enquiryReport=enquiryReportConverter.dtoToEntity(dto);
		enquiryReport=enquiryReportDao.update(enquiryReport);
		return enquiryReportConverter.entityToDto(enquiryReport);*/
		return null;
	}

	@Override
	@Transactional
	public EnquiryReportDto update(EnquiryReportDto dto) {
		EnquiryReport enquiryReport=enquiryReportConverter.dtoToEntity(dto);
		enquiryReport=enquiryReportDao.update(enquiryReport);
		return enquiryReportConverter.entityToDto(enquiryReport);
	}

	@Override
	@Transactional
	public EnquiryReportDto updateBaseInfo(EnquiryReportDto src, EnquiryReportDto dest) {
		dest.setHaveWeMetBefore(src.getHaveWeMetBefore());
		dest.setBudget(src.getBudget());
		dest.setPurpose(src.getPurpose());
		dest.setDesiredUnitType(src.getDesiredUnitType());
		dest.setCarpetAreaRequirement(src.getCarpetAreaRequirement());
		dest.setCpComments(src.getCpComments());
		return update(dest);
	}

	@Override
	@Transactional
	public EnquiryReportDto updateAddressInfo(EnquiryReportDto src,EnquiryReportDto dest) {
		if(dest==null) {
			dest= new EnquiryReportDto();
		}
		dest.setSourceOfFunding(src.getSourceOfFunding());
		dest.setAllocatedSalesManager(src.getAllocatedSalesManager());
		dest.setVastuPreference(src.getVastuPreference());
		dest.setUnitAvailability(src.getUnitAvailability());
		dest.setAccompaniedBy(src.getAccompaniedBy());
		dest.setDealNegotiation(src.getDealNegotiation());
		dest.setConstructionStatus(src.getConstructionStatus());
		dest.setTimeframeToBook(src.getTimeframeToBook());
		dest.setEnquiryNonEditComment(src.getEnquiryNonEditComment());
		dest.setContributionReceipt(src.getContributionReceipt());
		dest.setLoanEligibility(src.getLoanEligibility());
		dest.setFollowDate(src.getFollowDate());
		dest.setFollowType(src.getFollowType());
		dest.setTokenno(src.getTokenno());
		dest.setUserid(src.getUserid());
		dest.setTrigger1(src.getTrigger1().trim());
		dest.setBarrier1(src.getBarrier1().trim());
		dest.setTrigger2(src.getTrigger2().trim());
		dest.setBarrier2(src.getBarrier2().trim());
		
		//Added By Satheesh Kyahtam
		EnquiryReportLog enqLog = new EnquiryReportLog();
		enqLog.setSourceOfFunding(dest.getSourceOfFunding());
		enqLog.setAllocatedSalesManager(dest.getAllocatedSalesManager());
		enqLog.setVastuPreference(dest.getVastuPreference());
		enqLog.setUnitAvailability(dest.getUnitAvailability());
		enqLog.setAccompaniedBy(dest.getAccompaniedBy());
		enqLog.setDealNegotiation(dest.getDealNegotiation());
		enqLog.setConstructionStatus(dest.getConstructionStatus());
		enqLog.setTimeframeToBook(dest.getTimeframeToBook());
		enqLog.setEnquiryNonEditComment(dest.getEnquiryNonEditComment());
		enqLog.setContributionReceipt(dest.getContributionReceipt());
		enqLog.setLoanEligibility(dest.getLoanEligibility());
		enqLog.setFollowDate(dest.getFollowDate());
		enqLog.setFollowType(dest.getFollowType());
		enqLog.setBudget(dest.getBudget());
		enqLog.setCarpetAreaRequirement(dest.getCarpetAreaRequirement());
		enqLog.setDesiredUnitType(dest.getDesiredUnitType());
		enqLog.setHaveWeMetBefore(dest.getHaveWeMetBefore());
		enqLog.setPurpose(dest.getPurpose());
		enqLog.setSourceOfFunding(dest.getSourceOfFunding());
		enqLog.setTimeframeToBook(dest.getTimeframeToBook());
		enqLog.setIsUpdated("Y");
		enqLog.setContactId(dest.getContactId());
		enqLog.setEnquirySfid(dest.getEnquirySfid());
		enqLog.setEnquiryId(dest.getEnquiryId());
		enqLog.setContributionReceipt(dest.getContributionReceipt());
		enqLog.setLoanEligibility(dest.getLoanEligibility());
		enqLog.setEnquiryNonEditComment(dest.getEnquiryNonEditComment());
		
		
		enqLog.setEnquiryReportId(src.getEnquiryReportId());
		enqLog.setCreateddate(new java.sql.Date(System.currentTimeMillis()));
		enqLog.setTokenno(dest.getTokenno());
		enqLog.setUserid(dest.getUserid());
		enquiryReportLogDao.insertEnquiryReportLog(enqLog);
		return update(dest);
	}

	@Override
	@Transactional
	public EnquiryReportDto getEnquiryReportById(EnquiryReportDto dto) {
		EnquiryReport enquiryReport=enquiryReportDao.getEnquiryReportById(dto.getEnquiryReportId());
		return enquiryReportConverter.entityToDto(enquiryReport);
	}	
	@Override
	@Transactional
	public void updateEnquirySfidToCustomEnquiry() {
		enquiryReportDao.updateEnquirySfidToCustomEnquiry();		
	}

	@Override
	@Transactional
	public void updateById(EnquiryDto dto) {
		Map<String,Object> params=new HashMap<>();
		params.put("enquiryId",dto.getEnquiryId());
		params.put("enquiryReportId", dto.getEnquiryReport().getEnquiryReportId());
		/*params.put("contactId",dto.getContact().getContactId());*/
		enquiryReportDao.updateById(params);
	}

	@Override
	@Transactional
	public EnquiryReportDto saveEnquiryReport(EnquiryReportDto dto) {
		if(null==dto.getEnquiryReportId()){
		    return save(dto);			
		}else{
			EnquiryReportDto enquiryReportDest=getEnquiryReportById(dto);
			return updateBaseInfo(dto, enquiryReportDest);	
		}
	}

	@Override
	@Transactional
	public int updatePaymentDetails(EnquiryReportDto dto) {		
		return enquiryReportDao.updatePaymentDetails(dto);
	}
}
