package com.godrej.properties.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godrej.properties.common.converter.CommonConverter;
import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.model.Enquiry;
import com.godrej.properties.model.EnquiryReport;

@Component
public class EnquiryConverter implements CommonConverter<Enquiry, EnquiryDto>{

	@Autowired
	private ContactConverter contactConverter;
	
	@Autowired
	private ChannelPartnerConverter channelPartnerConverter;
	
	@Autowired
	private ProjectConverter projectConverter;
	
	@Autowired
	private EnquiryReportConverter enquiryReportConverter;
	
	@Override
	public EnquiryDto entityToDto(Enquiry entity) {
		
		if(null == entity){
			return null;
		}
		
		if (entity instanceof HibernateProxy) {
			return null;
		}
		EnquiryDto dto=new EnquiryDto();		
		dto.setBrokerContact(contactConverter.entityToDto(entity.getBrokerContact()));
		dto.setBudget(entity.getBudget());
		dto.setChannelPartner(channelPartnerConverter.entityToDto(entity.getChannelPartner()));
		dto.setContact(contactConverter.entityToDto(entity.getContact()));
		dto.setContactId(contactConverter.entityToDto(entity.getContactId()));
		dto.setDateOfEnquiry(entity.getDateOfEnquiry());
		dto.setDateOfSiteVisit(entity.getDateOfSiteVisit());
		dto.setDesiredUnitType(entity.getDesiredUnitType());
		dto.setEnquiryId(entity.getEnquiryId());
		dto.setIsReferredByChannelPartner(entity.getIsReferredByChannelPartner());
		dto.setIsReferredByChannelPartnerFlag(entity.getIsReferredByChannelPartnerFlag());
		dto.setOtherChannelPartner(entity.getOtherChannelPartner());
		dto.setProject(projectConverter.entityToDto(entity.getProject()));
		
		/*Purchase of purpose selected value push into enquiry -  Change By Satheesh Kyatham- 26-09-2019*/
		/*=======Start==========*/
		if(entity.getEnquiryReport()!=null)
		{
			if(entity.getEnquiryReport().getPurpose().equals("Self Use"))
				dto.setPurpose("Personal use");
			else if(entity.getEnquiryReport().getPurpose().equals("Investment"))
				dto.setPurpose("Investment");
			else
				dto.setPurpose(entity.getPurpose());
		}
		/*=========End========*/
		
		/*Lost Reason selected value Display/Push into enquiry -  Change By Satheesh Kyatham- 25-10-2019*/
		/*=======Start==========*/
		if(entity.getLost_reason_c__c()!=null)
			dto.setLost_reason_c__c(entity.getLost_reason_c__c());
		/*=========End========*/
		
		dto.setPriority_no__c(entity.getPriority_no__c());
		
		
		//dto.setPurpose(entity.getPurpose());
		dto.setRequiredPossesionTimeLine(entity.getRequiredPossesionTimeLine());
		dto.setWalkInSource(entity.getWalkInSource());
		dto.setWalkInSourceDetail(entity.getWalkInSourceDetail());
		dto.setEnquiryRating(entity.getEnquiryRating());
		//dto.setProjectType(entity.getProjectType());
		dto.setEnquirySource(entity.getEnquirySource());
		dto.setSfid(entity.getSfid());
		dto.setEnquiryStatus(entity.getEnquiryStatus());
		dto.setSynchronised(entity.getSynchronised());
		dto.setAdvertisement(entity.getAdvertisement());
		dto.setEnquiryReport(enquiryReportConverter.entityToDto(entity.getEnquiryReport()));
		dto.setLastModifiedDate(entity.getLastModifiedDate());
		
		dto.setEoiBankName(entity.getEoiBankName());
		dto.setBranch(entity.getBranch());
		dto.setMicRChequeNoNEFTRTGS(entity.getMicRChequeNoNEFTRTGS());
		dto.setTransactionDate(entity.getTransactionDate());
		dto.setTransactionAmount(entity.getTransactionAmount());
		dto.setTransactionType( entity.getTransactionType());
		dto.setTransactionID(entity.getTransactionID());
		dto.setClosingmanagers(entity.getClosingmanagers());
		dto.setAssignTo(entity.getAssignTo());
		
		dto.setEOI_Tower_Series__c(entity.getEOI_Tower_Series__c());
		dto.setEOI_Preferred_Floor_Band__c(entity.getEOI_Preferred_Floor_Band__c());
		dto.setTransaction_Status__c(entity.getTransaction_Status__c());
		dto.setEOI_Remarks__c(entity.getEOI_Remarks__c());
		dto.setPreferred_Unit__c(entity.getPreferred_Unit__c());
		dto.setDate_of_eoi__c(entity.getDate_of_eoi__c());
		dto.setEoi_enquiry__c(entity.isEoi_enquiry__c());
		dto.setEoi_preferred_unit__c(entity.getEoi_preferred_unit__c());
		dto.setName(entity.getName());
		dto.setAppointment_Done__c(entity.getAppointment_Done__c());
		dto.setSite_Visit_Done__c(entity.getSite_Visit_Done__c());
		dto.setAppointment__c(entity.getAppointment__c());
		dto.setSite_visit_requested__c(entity.getSite_visit_requested__c());
		//dto.setClosing_manager_name__c(entity.getClosing_manager_name__c());
		//dto.setClosingmanagers(entity.getClosingmanagers());
		
		/* Trigger 1,2 and Barrier 1, 2 pushing to SFDC Enquiry Object from Sales Tab -  
	     * Change By Satheesh Kyatham- 25-12-2019
	     * Request From - Prakash Idnani*/
		/*=======Start==========*/
		if(entity.getTrigger_1__c()!=null)
			dto.setTrigger_1__c(entity.getTrigger_1__c());
		if(entity.getTrigger_2__c()!=null)
			dto.setTrigger_2__c(entity.getTrigger_2__c());
		if(entity.getBarrier_1__c()!=null)
			dto.setBarrier_1__c(entity.getBarrier_1__c());
		if(entity.getBarrier_2__c()!=null)
			dto.setBarrier_2__c(entity.getBarrier_2__c());
		/*=========End========*/
		/* Selected Follow type and follow Date value are pushing to Enquiry Object, -  
	     * Change By - Satheesh Kyatham- 25-12-2019
	     * Request From - Prakash Idnani*/
		/*=======Start==========*/
		if(entity.getFollow_up_reason__c()!=null)
			dto.setFollow_up_reason__c(entity.getFollow_up_reason__c());
		if(entity.getFollow_up_Date_Time__c()!=null)
			dto.setFollow_up_Date_Time__c(entity.getFollow_up_Date_Time__c());
		/*=========End========*/
		if(entity.getEnquiryReport()!=null)
		{
		if(entity.getEnquiryReport().getReferredby()!=null)
			dto.setReferredbyDto(entity.getEnquiryReport().getReferredby());
		}
		
		/* Sourcing Manager and Vertical Drop downs adding on Sales Tab for passing drop down values to SFDC, -  
	     * Change By - Satheesh Kyatham- 27-12-2019
	     * Request From - Prakash Idnani*/
		/*=======Start==========*/
		if(entity.getVerticle__c()!=null)
			dto.setVerticle__c(entity.getVerticle__c());
		if(entity.getSourcing_Managers__c()!=null)
			dto.setSourcing_Managers__c(entity.getSourcing_Managers__c());
		/*=========End========*/
		return dto;
	}

	@Override
	public Enquiry dtoToEntity(EnquiryDto dto) {
		if(null == dto){
			return null;
		}
		
		Enquiry entity=new Enquiry();		
		entity.setBrokerContact(contactConverter.dtoToEntity(dto.getBrokerContact()));
		
		entity.setBudget(dto.getBudget());
		entity.setChannelPartner(channelPartnerConverter.dtoToEntity(dto.getChannelPartner()));
		entity.setContact(contactConverter.dtoToEntity(dto.getContact()));
		entity.setContactId(contactConverter.dtoToEntity(dto.getContactId()));
		entity.setDateOfEnquiry(dto.getDateOfEnquiry());
		entity.setDateOfSiteVisit(dto.getDateOfSiteVisit());
		entity.setDesiredUnitType(dto.getDesiredUnitType());
		entity.setEnquiryId(dto.getEnquiryId());
		entity.setIsReferredByChannelPartner(dto.getIsReferredByChannelPartner());
		entity.setIsReferredByChannelPartnerFlag(dto.getIsReferredByChannelPartnerFlag());
		entity.setOtherChannelPartner(dto.getOtherChannelPartner());
		entity.setProject(projectConverter.dtoToEntity(dto.getProject()));
		/*Purchase of purpose selected value push into enquiry -  Change By Satheesh Kyatham- 26-09-2019*/
		/*=======Start==========*/
		if(dto.getEnquiryReport()!=null)
		{
			if(dto.getEnquiryReport().getPurpose().equals("Personal use"))
				entity.setPurpose("Self Use");
			else if(dto.getEnquiryReport().getPurpose().equals("Investment"))
				entity.setPurpose("Investment");
			else
				entity.setPurpose(dto.getPurpose());
		}
		/*=========End========*/
		
		/*Lost Reason selected value Display/Push into enquiry -  Change By Satheesh Kyatham- 25-10-2019*/
		/*=======Start==========*/
		if(dto.getLost_reason_c__c()!=null && dto.getLost_reason_c__c().length()>0)
			entity.setLost_reason_c__c(dto.getLost_reason_c__c());
		/*=========End========*/
		
		entity.setRequiredPossesionTimeLine(dto.getRequiredPossesionTimeLine());
		entity.setWalkInSource(dto.getWalkInSource());
		entity.setWalkInSourceDetail(dto.getWalkInSourceDetail());
		entity.setEnquiryRating(dto.getEnquiryRating());
		//entity.setProjectType(dto.getProjectType());
		entity.setEnquirySource(dto.getEnquirySource());
		entity.setSfid(dto.getSfid());
		entity.setEnquiryStatus(dto.getEnquiryStatus());
		entity.setSynchronised(dto.getSynchronised());
		entity.setAdvertisement(dto.getAdvertisement());
		entity.setEnquiryReport(enquiryReportConverter.dtoToEntity(dto.getEnquiryReport()));
		entity.setDesiredUnitType(dto.getDesiredUnitType());
		
		entity.setLastModifiedDate(dto.getLastModifiedDate());
		
		entity.setEoiBankName(dto.getEoiBankName());
		entity.setBranch(dto.getBranch());
		if("Cheque".equals(dto.getTransactionType()))
			entity.setMicRChequeNoNEFTRTGS(dto.getMicRChequeNoNEFTRTGS());
		else if("NEFT".equals(dto.getTransactionType()))
			entity.setMicRChequeNoNEFTRTGS(dto.getNefttransactionID());
		
		entity.setTransactionDate(dto.getTransactionDate());
		entity.setTransactionAmount(dto.getTransactionAmount());
		entity.setTransactionType( dto.getTransactionType());
		entity.setTransactionID(dto.getTransactionID());
		entity.setClosingmanagers(dto.getClosingmanagers());
		entity.setAssignTo(dto.getAssignTo());
		entity.setDate_of_eoi__c(dto.getDate_of_eoi__c());
		entity.setEOI_Tower_Series__c(dto.getEOI_Tower_Series__c());
		entity.setEOI_Preferred_Floor_Band__c(dto.getEOI_Preferred_Floor_Band__c());
		entity.setTransaction_Status__c(dto.getTransaction_Status__c());
		entity.setEOI_Remarks__c(dto.getEOI_Remarks__c());
		entity.setPreferred_Unit__c(dto.getPreferred_Unit__c());
		entity.setEoi_preferred_unit__c(dto.getEoi_preferred_unit__c());
		entity.setName(dto.getName());
		
		entity.setAppointment_Done__c(dto.getAppointment_Done__c());
		//entity.setSite_Visit_Done__c(dto.getSite_Visit_Done__c());
		
		/*Purchase of purpose selected value push into enquiry -  Change By Satheesh Kyatham- 26-09-2019*/
		/*=======Start==========*/
		if(dto.getSite_Visit_Done__c()==null || dto.getSite_Visit_Done__c()==0)
			entity.setSite_Visit_Done__c(1.0);
		/*=========End========*/
		
		
		entity.setAppointment__c(dto.getAppointment__c());
		entity.setSite_visit_requested__c(dto.getSite_visit_requested__c());
		//entity.setClosing_manager_name__c(dto.getClosing_manager_name__c());
		//entity.setClosingmanagers(dto.getClosingmanagers());
		
		/* Trigger 1,2 and Barrier 1, 2 pushing to SFDC Enquiry Object from Sales Tab -  
	     * Change By Satheesh Kyatham- 25-12-2019
	     * Request From - Prakash Idnani*/
		/*=======Start==========*/
		if(dto.getTrigger_1__c()!=null)
			entity.setTrigger_1__c(dto.getTrigger_1__c());
		if(dto.getTrigger_2__c()!=null)
			entity.setTrigger_2__c(dto.getTrigger_2__c());
		if(dto.getBarrier_1__c()!=null)
			entity.setBarrier_1__c(dto.getBarrier_1__c());
		if(dto.getBarrier_2__c()!=null)
			entity.setBarrier_2__c(dto.getBarrier_2__c());
		/*=========End========*/
		/* Selected Follow type and follow Date value are pushing to Enquiry Object, -  
	     * Change By - Satheesh Kyatham- 25-12-2019
	     * Request From - Prakash Idnani*/
		/*=======Start==========*/
		if(dto.getFollow_up_reason__c()!=null)
			entity.setFollow_up_reason__c(dto.getFollow_up_reason__c());
		if(dto.getFollow_up_Date_Time__c()!=null)
			entity.setFollow_up_Date_Time__c(dto.getFollow_up_Date_Time__c());
		/*=========End========*/
		/* Sourcing Manager and Vertical Drop downs adding on Sales Tab for passing drop down values to SFDC, -  
	     * Change By - Satheesh Kyatham- 27-12-2019
	     * Request From - Prakash Idnani*/
		/*=======Start==========*/
		if(dto.getVerticle__c()!=null)
			entity.setVerticle__c(dto.getVerticle__c());
		/*=========End========*/
		
		if(dto.getEnquiryReport().getReferredby()!=null)
			entity.getEnquiryReport().setReferredby(dto.getEnquiryReport().getReferredby());
		return entity;
	}

	@Override
	public List<EnquiryDto> entityToDto(List<Enquiry> entityList) {
		if(null==entityList){
			return Collections.emptyList();
		}
		List<EnquiryDto> dtoList=new ArrayList<>();
		for(Enquiry entity:entityList){
			EnquiryDto dto=entityToDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<Enquiry> dtoToEntity(List<EnquiryDto> dtoList) {
		if(null==dtoList){
			return Collections.emptyList();
		}
		List<Enquiry> entityList=new ArrayList<>();
		for(EnquiryDto dto:dtoList){
			Enquiry entity=dtoToEntity(dto);
			entityList.add(entity);
		}
		return entityList;
	}
}
