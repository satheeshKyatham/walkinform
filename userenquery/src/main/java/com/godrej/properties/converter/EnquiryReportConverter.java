package com.godrej.properties.converter;

import java.util.List;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Component;

import com.godrej.properties.common.converter.CommonConverter;
import com.godrej.properties.dto.EnquiryReportDto;
import com.godrej.properties.model.EnquiryReport;

/**
 * 
 * @author Varsha Patil
 *
 */
@Component
public class EnquiryReportConverter implements CommonConverter<EnquiryReport, EnquiryReportDto>{
	
    /*@Autowired
	private EnquiryConverter enquiryConverter;*/
	
    @Override
	public EnquiryReportDto entityToDto(EnquiryReport entity) {
		if(null == entity){
			return null;
		}
		if(entity instanceof HibernateProxy) {
			return null;
		}
		EnquiryReportDto dto=new EnquiryReportDto();
		dto.setAccompaniedBy(entity.getAccompaniedBy());
		dto.setConstructionStatus(entity.getConstructionStatus());
		dto.setDealNegotiation(entity.getDealNegotiation());
		/*if(null!=entity.getEnquiry()){
			dto.setEnquiry(new EnquiryDto());
			dto.getEnquiry().setEnquiryId(entity.getEnquiry().getEnquiryId());
	    }
		if(null!=entity.getEnquirySfid()){
			dto.setEnquirySfid(new EnquiryDto());
			dto.getEnquirySfid().setSfid(entity.getEnquirySfid().getSfid());
			dto.getEnquirySfid().setEnquiryId(entity.getEnquirySfid().getEnquiryId());
	    }
		if(null!=entity.getContact()){
			dto.setContact(new ContactDto());
			dto.getContact().setContactId(entity.getContact().getContactId());
		}*/
		
		dto.setContactId(entity.getContactId());
		dto.setEnquiryId(entity.getEnquiryId());
		dto.setEnquirySfid(entity.getSfid());
		
		dto.setEnquiryReportId(entity.getEnquiryReportId());
		dto.setTimeframeToBook(entity.getTimeframeToBook());
		dto.setUnitAvailability(entity.getUnitAvailability());
		dto.setVastuPreference(entity.getVastuPreference());
		dto.setAllocatedSalesManager(entity.getAllocatedSalesManager());
		dto.setSourceOfFunding(entity.getSourceOfFunding());
		dto.setBudget(entity.getBudget());
		dto.setCarpetAreaRequirement(entity.getCarpetAreaRequirement());
		dto.setPurpose(entity.getPurpose());
		dto.setHaveWeMetBefore(entity.getHaveWeMetBefore());
		dto.setDesiredUnitType(entity.getDesiredUnitType());
		dto.setIsUpdated(entity.getIsUpdated());
		
		dto.setChequeno_file_name(entity.getChequeno_file_name());
		dto.setPanNo_file_name(entity.getPanNo_file_name());
		dto.setuTRNo_file_name(entity.getuTRNo_file_name());
		dto.setEnquiryNonEditComment(entity.getEnquiryNonEditComment());
		
		dto.setLoanEligibility(entity.getLoanEligibility());
		dto.setContributionReceipt(entity.getContributionReceipt());
		dto.setLoanEligibility_val(entity.getLoanEligibility());
		dto.setContributionReceipt_val(entity.getContributionReceipt());
		dto.setCpComments(entity.getCpComments());
		dto.setFollowDate(entity.getFollowDate());
		dto.setFollowType(entity.getFollowType());
		dto.setUnit(entity.getUnit());
		dto.setFloorBand(entity.getFloorBand());
		dto.setBankOtherInfo(entity.getBankOtherInfo());
		dto.setProjectId(entity.getProjectId());
		dto.setTower(entity.getTower());
		dto.setTokenno(entity.getTokenno());
		dto.setVisitType(entity.getVisitType());
		if(entity.getTrigger1()!=null)
			dto.setTrigger1(entity.getTrigger1().trim());
		if(entity.getBarrier1()!=null)
			dto.setBarrier1(entity.getBarrier1().trim());
		if(entity.getTrigger2()!=null)
			dto.setTrigger2(entity.getTrigger2().trim());
		if(entity.getBarrier2()!=null)
			dto.setBarrier2(entity.getBarrier2().trim());
		return dto;
	}

	@Override
	public EnquiryReport dtoToEntity(EnquiryReportDto dto) {
		if(null == dto){
			return null;
		}
		
		EnquiryReport enquiry=new EnquiryReport();
		enquiry.setAccompaniedBy(dto.getAccompaniedBy());
		enquiry.setConstructionStatus(dto.getConstructionStatus());
		enquiry.setDealNegotiation(dto.getDealNegotiation());
		
		enquiry.setEnquiryId(dto.getEnquiryId());
		enquiry.setEnquirySfid(dto.getEnquirySfid());
		enquiry.setContactId(dto.getContactId());
		
		/*if(null!=dto.getEnquiry()){
			enquiry.setEnquiry(new Enquiry());
			enquiry.getEnquiry().setEnquiryId(dto.getEnquiry().getEnquiryId());
		}*/
		/*if(null!=dto.getEnquirySfid()){
			enquiry.setEnquirySfid(new Enquiry());
			enquiry.getEnquirySfid().setSfid(dto.getEnquirySfid().getSfid());
			enquiry.getEnquirySfid().setEnquiryId(dto.getEnquirySfid().getEnquiryId());
	    }*/
		/*if(null!=dto.getContact()){
			enquiry.setContact(new Contact());
			enquiry.getContact().setContactId(dto.getContact().getContactId());
		}*/
		enquiry.setEnquiryReportId(dto.getEnquiryReportId());
		enquiry.setTimeframeToBook(dto.getTimeframeToBook());
		enquiry.setUnitAvailability(dto.getUnitAvailability());
		enquiry.setVastuPreference(dto.getVastuPreference());
		enquiry.setAllocatedSalesManager(dto.getAllocatedSalesManager());
		enquiry.setSourceOfFunding(dto.getSourceOfFunding());
		enquiry.setBudget(dto.getBudget());
		enquiry.setCarpetAreaRequirement(dto.getCarpetAreaRequirement());
		enquiry.setPurpose(dto.getPurpose());
		enquiry.setHaveWeMetBefore(dto.getHaveWeMetBefore());
		enquiry.setDesiredUnitType(dto.getDesiredUnitType());
		enquiry.setIsUpdated(dto.getIsUpdated());
		enquiry.setChequeno_file_name(dto.getChequeno_file_name());
		enquiry.setPanNo_file_name(dto.getPanNo_file_name());
		enquiry.setuTRNo_file_name(dto.getuTRNo_file_name());
		enquiry.setEnquiryNonEditComment(dto.getEnquiryNonEditComment());
		enquiry.setLoanEligibility(dto.getLoanEligibility());
		enquiry.setContributionReceipt(dto.getContributionReceipt());
		enquiry.setCpComments(dto.getCpComments());
		
		enquiry.setFollowDate(dto.getFollowDate());
		enquiry.setFollowType(dto.getFollowType());
		enquiry.setTower(dto.getTower());		 
		enquiry.setBankOtherInfo(dto.getBankOtherInfo());
		enquiry.setUnit(dto.getUnit());
		enquiry.setFloorBand(dto.getFloorBand());
		enquiry.setProjectId(dto.getProjectId());
		enquiry.setTokenno(dto.getTokenno());
		enquiry.setVisitType(dto.getVisitType());
		if(dto.getTrigger1()!=null)
			enquiry.setTrigger1(dto.getTrigger1().trim());
		if(dto.getBarrier1()!=null)
			enquiry.setBarrier1(dto.getBarrier1().trim());
		if(dto.getTrigger2()!=null)
			enquiry.setTrigger2(dto.getTrigger2().trim());
		if(dto.getBarrier2()!=null)
			enquiry.setBarrier2(dto.getBarrier2().trim());
		if(dto.getReferredby()!=null)
			enquiry.setReferredby(dto.getReferredby());
		
		if(dto.getIs_revisit()!=null && dto.getIs_revisit().contains("Yes"))
		{
			enquiry.setIs_revisit("Yes");
			if(dto.getLastvisitdate()!=null)
				enquiry.setLastvisitdate(dto.getLastvisitdate());
		}
		return enquiry;
	}

	@Override
	public List<EnquiryReportDto> entityToDto(List<EnquiryReport> entityList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EnquiryReport> dtoToEntity(List<EnquiryReportDto> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}

}
