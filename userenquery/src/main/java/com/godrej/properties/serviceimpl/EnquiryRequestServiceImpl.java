package com.godrej.properties.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.comparator.EnquiryComparator;
import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.dao.CountryDao;
import com.godrej.properties.dto.ChannelPartnerDto;
import com.godrej.properties.dto.ContactDto;
import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.dto.EnquiryReportDto;
import com.godrej.properties.model.Country;
import com.godrej.properties.service.ChannelPartnerService;
import com.godrej.properties.service.ContactReportService;
import com.godrej.properties.service.EnquiryReportService;
import com.godrej.properties.service.EnquiryRequestService;
import com.godrej.properties.service.PushEnquiryDataService;
import com.godrej.properties.service.UserContactService;
import com.godrej.properties.util.CommonUtil;
import com.godrej.properties.util.DateUtil;
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

@Service
public class EnquiryRequestServiceImpl implements EnquiryRequestService {

	@Autowired
	private PushEnquiryDataService pushEnquiryDataService;
	
	@Autowired
	private UserContactService userContactService;
	
	@Autowired
	private EnquiryReportService enquiryReportService;
	@Autowired
	private ContactReportService contactReportService;
	
	@Autowired
	private ChannelPartnerService channelPartnerService;
	
	@Autowired
	private CountryDao countryDao;
			
	@Override
	public EnquiryDto save(EnquiryDto dto) {
           //Contact Save
		if(null==dto.getContact().getContactId()){
			
			dto.getContact().setRecordType(KeyConstants.RECORD_TYPE_PROSPECT);
			ContactDto contact=userContactService.save(dto.getContact());
			if(null==contact.getContactReport().getContactId()){
				contactReportService.updateById(contact);
			}			
			System.out.println("contact insert query ended :"+new Date());
			dto.setContact(contact);
			dto.setContactId(contact);
		}else{
			ContactDto contactDest=userContactService.findById(dto.getContact());
			if(null==contactDest.getRecordType()){
				contactDest.setRecordType(KeyConstants.RECORD_TYPE_PROSPECT);
			}
			contactDest=userContactService.updateBaseInfo(dto.getContact(), contactDest);
			if(null==contactDest.getContactReport().getContactId()){
				contactReportService.updateById(contactDest);
			}
			dto.setContact(contactDest);
			dto.setContactId(contactDest);
		}
		
		//Enquiry
		if(null==dto.getEnquiryReport().getEnquiryReportId()){
			dto.getEnquiryReport().setContactId(dto.getContact().getContactId());
			dto.getEnquiryReport().setCpComments(dto.getCpComment());
			EnquiryReportDto enquiryReport=enquiryReportService.save(dto.getEnquiryReport());			
			dto.setEnquiryReport(enquiryReport);
		}else{
			EnquiryReportDto enquiryReportDest=enquiryReportService.getEnquiryReportById(dto.getEnquiryReport());
			if(null==enquiryReportDest.getContactId()){
				enquiryReportDest.setContactId(dto.getContact().getContactId());
			}
			
			enquiryReportDest=enquiryReportService.updateBaseInfo(dto.getEnquiryReport(),enquiryReportDest);
			dto.setEnquiryReport(enquiryReportDest);
			
		}
		/*if(CommonUtil.isStringEmpty(dto.getAdvertisement())){
			String advertisementSfid=pushEnquiryDataService.getAdvertisementForEnquiry(dto);
		    dto.setAdvertisement(advertisementSfid);
		}*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateWithoutTime = null;
		try {
			dateWithoutTime = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*// Method 2
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		dateWithoutTime = cal.getTime();*/		
		
		
		dto.setDateOfSiteVisit(DateUtil.getUKDateTime());
		dto.setDateOfEnquiry(DateUtil.getUKDateTime());
		
		
		//dto.setClosingmanagers(closingmanagers);
		if(dto.getWalkInSource()!=null && !dto.getWalkInSource().equals("")){
			//dto.setWalkInSourceDetail(dto.getWalkInSource().replace(",", ""));
			dto.setWalkInSource(dto.getWalkInSource().replace(",", ""));
		}else {
			if(dto.getIsReferredByChannelPartnerFlag().equals("CP") || dto.getIsReferredByChannelPartnerFlag().equals("O"))
				dto.setWalkInSource("Channel Partner");
			
			if(dto.getIsReferredByChannelPartnerFlag().equals("O")) {
				 ContactDto contactDto=dto.getContact();
				 ChannelPartnerDto channelPartnerDto=  contactDto.getChannelPartner();
				 if(channelPartnerDto==null)
					 channelPartnerDto= new ChannelPartnerDto();
				 int channelPartnerId = channelPartnerService.getChannelPartnerId("0016F00002yk66oQAA");
				 
				 channelPartnerDto.setSfid("0016F00002yk66oQAA");
				 contactDto.setChannelPartner(channelPartnerDto);
				 dto.setContact(contactDto);
				
				 
				 ChannelPartnerDto partnerDto= dto.getChannelPartner();
				 if(partnerDto==null) {
					 partnerDto=new ChannelPartnerDto();
				 }
				
				 partnerDto.setChannelPartnerId(channelPartnerId);
				 partnerDto.setSfid("0016F00002yk66oQAA");
				 dto.setChannelPartner(partnerDto); 
				 
				
			}
		}
		/* AdvertisementForEnquiry Code Sequence Change line from 100 to 158 -  Change By Satheesh Kyatham- 11-10-2019*/
		/*=======Start==========*/
		if(CommonUtil.isStringEmpty(dto.getAdvertisement())){
			String advertisementSfid=pushEnquiryDataService.getAdvertisementForEnquiry(dto);
		    dto.setAdvertisement(advertisementSfid);
		}
		/*=========End========*/
		String comment=dto.getEnquiryNonEditComment()==null?"":dto.getEnquiryNonEditComment();
		if(dto.getOtherChannelPartner()!=null){
			dto.setOtherChannelPartner(dto.getOtherChannelPartner());
		    //dto.setOtherChannelPartner("CP Other- "+dto.getOtherChannelPartner().replaceAll("CP Other-","")+" | "+"Comment- "+comment.replaceAll("Comment-","")+" | ");
		}else{
			//dto.setOtherChannelPartner("Comment- "+comment.replaceAll("Comment-","")+" | "+" Commented By-"+dto.getClosingmanagers());
			dto.setOtherChannelPartner(dto.getOtherChannelPartner());
		}
		//Set comment__c into Enquiry table 
		dto.setOtherChannelPartner(dto.getCpComment());
		dto.setDesiredUnitType(dto.getEnquiryReport().getDesiredUnitType());
		dto=formValidation(dto);
		return pushEnquiryDataService.insertInputEnquiry(dto);
	}
    private EnquiryDto formValidation(EnquiryDto dto){
    	String address1=dto.getContact().getAddressLine1()==null?"":dto.getContact().getAddressLine1();
    //	dto.getContact().setResidenceFullAddress(address1+dto.getContact().getAddressLine2());
    	String address=dto.getContact().getAddressLine2();
    	if(address ==null) {
    		return dto;
    	}
    	int length=address.length();
    	if(length<=32)
        {
    		dto.getContact().setAddressLine2(address.substring(0, length));

        }else if(length>32 && length<64){             

        	 dto.getContact().setAddressLine2(address.substring(0, 32));

             if(length<64)
             {
            	 dto.getContact().setAddressLine3(address.substring(32, length)); 
             }
        }/*else if(length>64 && length<96 || length>96){*/          
        else if(length>=64) {
        	dto.getContact().setAddressLine2(address.substring(0, 32));
        	dto.getContact().setAddressLine3(address.substring(32, 64));        	
        }/*else if(length>96){
        	dto.getContact().setAddressLine2(address.substring(0, 32));
        	dto.getContact().setAddressLine3(address.substring(32, 64));  
        }*/
    	

    	dto.getContact().setResidenceFullAddress(address1+" "+dto.getContact().getAddressLine2()+" "+
    			(dto.getContact().getAddressLine3()!=null?dto.getContact().getAddressLine3():"")+" "+dto.getContact().getCity()+" "
    	    	+dto.getContact().getResidentialState()+" "+dto.getContact().getResidentialCountry());
    	    	
    	//dto.getContact().setResidenceFullAddress(address1+dto.getContact().getAddressLine2()+" ");
    	return dto;
    }
	@Override
	public EnquiryDto updateBaseInfo(EnquiryDto src,EnquiryDto dest) {
		return pushEnquiryDataService.update(copyBaseInfo(src,dest));
	}

	/*@Override
	public EnquiryDto updateRequirementInfo(EnquiryDto src,EnquiryDto dest) {
		return pushEnquiryDataService.update(copyRequirementInfo(src, dest));
	}*/

	@Override
	public EnquiryDto updateAddressInfo(EnquiryDto src,EnquiryDto dest) {
		return pushEnquiryDataService.update(copyAddressInfo(src,dest));
	}

	/*@Override
	public EnquiryDto updateOtherInfo(EnquiryDto src,EnquiryDto dest) {
		
		return pushEnquiryDataService.update(copyOtherInfo(src,dest));
	}*/
	
	private EnquiryDto copyBaseInfo(EnquiryDto src,EnquiryDto dest){
		ContactDto contactDest=userContactService.findById(src.getContact());
		if(contactDest == null) {
			contactDest = new ContactDto();
		}
		EnquiryDto addressData = formValidation(src);
		String add1=addressData.getContact().getAddressLine1()!=null?addressData.getContact().getAddressLine1():"";
		String add2=addressData.getContact().getAddressLine2()!=null?addressData.getContact().getAddressLine2():"";
		String add3=addressData.getContact().getAddressLine3()!=null?addressData.getContact().getAddressLine3():"";
		contactDest.setAddressLine2(add2);
		contactDest.setAddressLine3(add3);
		contactDest.setResidenceFullAddress(add1+add2+add3+addressData.getContact().getCity()+addressData.getContact().getResidentialState()+" "+addressData.getContact().getResidentialCountry());
		System.out.println("Country Name233:"+addressData.getContact().getCountry());
		Country country = countryDao.getCountryData(addressData.getContact().getCountryCode());
		if(country!=null)
			contactDest.setMailingCountry(country.getName().toString());//
		else
			contactDest.setMailingCountry("India");//
		contactDest=userContactService.updateBaseInfo(src.getContact(), contactDest);
		
		if(null==contactDest.getContactReport().getContactId()){
			contactReportService.updateById(contactDest);
		}
		
		if(null==src.getEnquiryReport().getEnquiryReportId()){
			/*src.getEnquiryReport().setContact(contactDest);*/
			src.getEnquiryReport().setContactId(contactDest.getContactId());
			src.getEnquiryReport().setCpComments(src.getCpComment());
			EnquiryReportDto enquiryReport=enquiryReportService.save(src.getEnquiryReport());			
			dest.setEnquiryReport(enquiryReport);
		}else{
			EnquiryReportDto enquiryReportDest=enquiryReportService.findById(src.getEnquiryReport());
			if(null==enquiryReportDest.getContactId()){
				enquiryReportDest.setContactId(contactDest.getContactId());
			}
			enquiryReportDest=enquiryReportService.updateBaseInfo(src.getEnquiryReport(), enquiryReportDest);
			dest.setEnquiryReport(enquiryReportDest);			
		}
		
		/* Commented for Scenario 4 to avoid change in enquiry type.- Changes By Satheesh*/
		/*if(!dest.getIsReferredByChannelPartner().equals(src.getIsReferredByChannelPartner())){
			String advertisementSfid=pushEnquiryDataService.getAdvertisementForEnquiry(src);
			dest.setAdvertisement(advertisementSfid);
		}*/
		dest.setContactId(contactDest);
		dest.setBrokerContact(src.getBrokerContact());
		dest.setContact(contactDest);
		dest.setProject(src.getProject());
		/* Commented for Scenario 4 to avoid change in enquiry type.- Changes By Satheesh*/
		//dest.setIsReferredByChannelPartner(src.getIsReferredByChannelPartner());
		dest.setIsReferredByChannelPartnerFlag(src.getIsReferredByChannelPartnerFlag());
		/* Channel Partner Validation*/
		
		if(dest.getIsReferredByChannelPartnerFlag().equals("CP") || dest.getIsReferredByChannelPartnerFlag().equals("O"))
		{
			dest.setWalkInSource("Channel Partner");
			//src.getChannelPartner().getSfid();
		}
		else
			dest.setWalkInSource(src.getWalkInSource());
		/* Update Site visit done to ensure sync delays does not violate source protection*/
		if(dest.getSite_Visit_Done__c()==null || dest.getSite_Visit_Done__c()==0)
			dest.setSite_Visit_Done__c(1.0);
		
		if(dest.getIsReferredByChannelPartnerFlag().equals("O")) {
			 ContactDto contactDto=dest.getContact();
			 ChannelPartnerDto channelPartnerDto=  contactDto.getChannelPartner();
			 if(channelPartnerDto==null)
				 channelPartnerDto= new ChannelPartnerDto();
			 int channelPartnerId = channelPartnerService.getChannelPartnerId("0016F00002yk66oQAA");
			 channelPartnerDto.setSfid("0016F00002yk66oQAA");
			 contactDto.setChannelPartner(channelPartnerDto);
			 dest.setContact(contactDto);
			 ChannelPartnerDto partnerDto= dest.getChannelPartner();
			 if(partnerDto==null) {
				 partnerDto=new ChannelPartnerDto();
			 }
			 partnerDto.setChannelPartnerId(channelPartnerId);
			 partnerDto.setSfid("0016F00002yk66oQAA");
			 dest.setChannelPartner(partnerDto);
			 dest.setCpComment(src.getCpComment());
			 dest.setOtherChannelPartner(src.getCpComment());
		}
		else
		{
			dest.setChannelPartner(src.getChannelPartner());
			dest.setOtherChannelPartner(src.getOtherChannelPartner());
		}
		
		/*dest.setWalkInSourceDetail(src.getWalkInSourceDetail());*/
		/*dest.setDesiredUnitType(src.getDesiredUnitType());*/
		/*dest.setDateOfEnquiry(new Date());*/
		if(null==dest.getDateOfEnquiry()){
			dest.setDateOfEnquiry(new Date());
		}
		dest.setDateOfSiteVisit(DateUtil.getUKDateTime());
		
		dest.setEnquiryStatus(src.getEnquiryStatus());
		/*if(contactDest!=null && KeyConstants.RECORD_TYPE_CUSTOMER.equals(contactDest.getRecordType())){
			dest.setOtherChannelPartner(src.getEnquiryNonEditComment());
		}*/	
		 
		 if(src.getWalkInSource()!=null){
			dest.setWalkInSourceDetail(src.getWalkInSource());
		} 
		String comment=src.getEnquiryNonEditComment()==null?"":src.getEnquiryNonEditComment();
		if(src.getOtherChannelPartner()!=null){
			dest.setOtherChannelPartner(src.getOtherChannelPartner());
		//	dest.setOtherChannelPartner("CP Other- "+src.getOtherChannelPartner().replaceAll("CP Other-","")+" | "+"Comment- "+comment.replaceAll("Comment-","")+" | "+" Commented By-"+src.getClosingmanagers());
		}else{
			dest.setOtherChannelPartner(src.getCpComment());
			//dest.setOtherChannelPartner("Comment- "+comment.replaceAll("Comment-","")+" | "+" Commented By-"+src.getClosingmanagers());
 
		}
		/*dest.setAdvertisement(src.getAdvertisement());*/
		return dest;
	}
	
	/*private EnquiryDto copyRequirementInfo(EnquiryDto src,EnquiryDto dest){
		
		
		return dest;
	}*/
	
	private EnquiryDto copyAddressInfo(EnquiryDto src,EnquiryDto dest){
		 if(dest==null) {
			 dest=new EnquiryDto();
		 }
		ContactDto contactDest=userContactService.findById(src.getContact());
		contactDest=userContactService.updateAddressInfo(src.getContact(), contactDest);
		
		EnquiryReportDto enquiryReport=enquiryReportService.getEnquiryReportById(src.getEnquiryReport());
		if(enquiryReport==null) {
			enquiryReport= new EnquiryReportDto();
					
		}
		enquiryReport=enquiryReportService.updateAddressInfo(src.getEnquiryReport(), enquiryReport);
		if(enquiryReport==null) {
			enquiryReport= new EnquiryReportDto();
					
		}
		dest.setEnquiryReport(enquiryReport);		
		dest.setContact(contactDest);
		/*Closing Manager ID passign on Assined to Field in SFDC -  Change By Satheesh Kyatham- 16-10-2019*/
		/*=======Start==========*/
		//dest.setAssignTo(src.getClosingmanagers());
		/*=========End========*/
		dest.setClosingmanagers(src.getClosingmanagers());
		String manager=src.getClosingmanagers()==null?"":src.getClosingmanagers();
		if(dest.getOtherChannelPartner()!=null){
			dest.setOtherChannelPartner(src.getOtherChannelPartner());
		//   dest.setOtherChannelPartner(dest.getOtherChannelPartner()+" | "+" Commented By-"+manager);
		}else{
			dest.setOtherChannelPartner(src.getOtherChannelPartner());
		 //  dest.setOtherChannelPartner("Commented By-"+manager);
		}
		/*dest.setCurrentResidenceType(src.getCurrentResidenceType());
		dest.setCurrentOwnershipType(src.getCurrentOwnershipType());
		dest.setBudget(src.getBudget());
		dest.setRequiredPossesionTimeLine(src.getRequiredPossesionTimeLine());
		dest.setPurpose(src.getPurpose());*/
		/* Added By Satheesh Kyahtam*/
		dest.setEnquiryRating(src.getContact().getContactReport().getCustomerClassification());
		if(src.getLost_reason_c__c()!=null)
			dest.setLost_reason_c__c(src.getLost_reason_c__c());
		if(src.getEnquiryReport().getEnquiryNonEditComment()!=null)
			dest.setOtherChannelPartner(src.getEnquiryReport().getEnquiryNonEditComment());
		return dest;
	}
	
	/*private EnquiryDto copyOtherInfo(EnquiryDto src,EnquiryDto dest){
		
		ContactDto contactDest=userContactService.findById(src.getContact());
		contactDest=userContactService.updateAddressInfo(src.getContact(), contactDest);
		dest.setContact(contactDest);
		
		return dest;
	}*/

	@Override
	public EnquiryDto findById(EnquiryDto dto) {
		return pushEnquiryDataService.findById(dto);
	}

	@Override
	public EnquiryDto getEnquiryByMobileNo(String mobileNo) {
		
		return pushEnquiryDataService.getEnquiryByMobileNo(mobileNo);
	}
	
	@Override
	public List<EnquiryDto> getEnquiriesByMobileNo(String countryCode,String mobileNo) {
		
		return pushEnquiryDataService.getEnquiriesByMobileNo(countryCode,mobileNo);
	}
	
	@Override
	public List<EnquiryDto> getEnquiriesByMobileNo(String countryCode,String mobileNo,String projectSfid,String userid) {
//		 1)Get all Enquiries basis on mobile no and project SFid
		
			List<EnquiryDto> enquiryList=pushEnquiryDataService.getEnquiriesByMobileNo(countryCode,mobileNo,projectSfid,userid);
			List<EnquiryDto> enquiries=new ArrayList<>();
			
			
//		 2) if the list size is 0 then return  new Enquiry form
			if(CommonUtil.isListEmpty(enquiryList)){
				 return enquiryList;
			 }
			else //3) Check for Enquiries with LMD<30 && Date of site visit <90 && Site visit counter or Appointment counter >0
			{
				String name = "";
				for(EnquiryDto enquiry:enquiryList){
					
					enquiry.setContact(enquiry.getContactId());
					int lastModifyDays=DateUtil.getDays(enquiry.getLastModifiedDate(),new Date());
				    int siteVisitDays=DateUtil.getDays(enquiry.getDateOfSiteVisit(),new Date());
				   
				    if(enquiry.getSite_Visit_Done__c()==null)
				    {
				    	enquiry.setSite_Visit_Done__c(0.0);
				    }
				    if(enquiry.getAppointment_Done__c()==null)
				    {
				    	enquiry.setAppointment_Done__c(0.0);
				    }
				    //
					if((enquiry.getSite_Visit_Done__c()>0 || enquiry.getAppointment_Done__c()>0) 
							&& siteVisitDays < KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays < KeyConstants.LAST_MODIFY_DAYS_LIMIT)
					{
						enquiries.add(enquiry);
					}
				}
				//4) If 3) gives 0 results then latest enquires checked for Appointment propsed and site visit requested counter >0
				if(enquiries.size()==0)
				{
					EnquiryDto enquiry = enquiryList.get(0);
					if(KeyConstants.RECORD_TYPE_CUSTOMER.equals(enquiry.getContact().getRecordType())){
						enquiry.getContact().setHasError(true);
						name ="Cannot edit customer details & ";
					}
					if(enquiry.getSite_visit_requested__c()==null)
				    {
				    	enquiry.setSite_visit_requested__c(0.0);
				    }
					if(enquiry.getAppointment__c()==null)
				    {
				    	enquiry.setAppointment__c(0.0);
				    }
					if((enquiry.getSite_visit_requested__c()>0 || enquiry.getAppointment__c()>0 || enquiry.getEoi_enquiry__c()==true) && enquiry.getSite_Visit_Done__c()==0 && enquiry.getAppointment_Done__c()==0 )
					{
						enquiry.setHasError(true);
						//enquiry.setNonEdit("ENQUIRY");						
						enquiry.setMessage("Scenario 4: "+name+"Edit existing enquiry details");
						enquiry.setIsReferredByChannelPartner("");
						enquiry.setIsReferredByChannelPartnerFlag("");
						enquiries.add(enquiry);						
						System.out.println("Scenario 4: "+name+"Edit existing enquiry details");
						//add CP hide and show logic
					}
					else
					{
						enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 2: "+"Create New Enquiry");
					    System.out.println("Scenario 2: "+"Create New Enquiry");
						//return enquiries;
					}
				}
				else if(enquiries.size()==1)//5) If 3) gives 1 results then source protuction.
				{
					EnquiryDto enquiry = enquiries.get(0);
					
					if(KeyConstants.RECORD_TYPE_CUSTOMER.equals(enquiry.getContact().getRecordType())){
						enquiry.getContact().setHasError(true);
						name= "customer & ";
					}
					enquiry.setHasError(true);
					enquiry.setNonEdit("ENQUIRY");						
					enquiry.setMessage("Scenario 1: "+"Cannot edit "+name+" enquiry details");
					enquiries.add(enquiry);						
					System.out.println("Scenario 1: "+"Cannot edit "+name+"enquiry details");
				}
				else if(enquiries.size()>1)//6) If 3) gives more than 1 results then select latest ENQ basis site visit date
				{
					//pick latest one basis site visit done
					enquiries.sort(Comparator.comparing(EnquiryDto::getDateOfSiteVisit).reversed());
					EnquiryDto enquiry = enquiries.get(0);
					if(KeyConstants.RECORD_TYPE_CUSTOMER.equals(enquiry.getContact().getRecordType())){
						enquiry.getContact().setHasError(true);
						name= "customer & ";
					}
					enquiry.setHasError(true);
					enquiry.setNonEdit("ENQUIRY");						
					enquiry.setMessage("Scenario 1: "+"Cannot edit "+name+" enquiry details");
					enquiries.add(enquiry);						
					System.out.println("Scenario 1: "+"Cannot edit "+name+" enquiry details");
				}
			}
			
			/*if(KeyConstants.RECORD_TYPE_PROSPECT.equals(enquiryList.get(0).getContact().getRecordType())){
				//check scenario
				enquiry.setHasError(true);
				enquiry.setNonEdit("ENQUIRY");						
				enquiry.setMessage("Scenario 1: "+"Cannot edit enquiry details");
				enquiries.add(enquiry);						
				System.out.println("Scenario 1: "+"Cannot edit enquiry details");
			}
			else if(KeyConstants.RECORD_TYPE_CUSTOMER.equals(enquiryList.get(0).getContact().getRecordType())){
					enquiry.getContact().setHasError(true);
					enquiry.setHasError(true);
					enquiry.setNonEdit("ENQUIRY");
					enquiry.setMessage("Scenario 3: "+"Cannot edit enquiry and Contact details");
					enquiries.add(enquiry);
					System.out.println("Scenario 3: "+"Cannot edit enquiry and Contact details");
			}*/
			
			
			/*EnquiryComparator enquiryComparator = new EnquiryComparator();
			
			Collections.sort(enquiryList, enquiryComparator);*/
			
//			3)If no enquiry with source walk-in, [has status walk-in done]
//			3.1)then find the latest enquiry and provide the editable enquiry form with available data
				
			/*EnquiryDto enquiryTop = enquiryList.get(0);
			List<EnquiryDto> enquiryDtos=new ArrayList<>();
			enquiryDtos.add(enquiryTop);
			 
			
			if(enquiryTop.getWalkInSource() == null) {
				return enquiryDtos;
			}*/

//		4)Find the latest enquiry with source "walk-in" [has status walk-in done]
			
//		4.1)If found the apply further checks.
			

		//List<EnquiryDto> enquiries1=new ArrayList<>();
		/*for(EnquiryDto enquiry:enquiryDtos){
			int lastModifyDays=DateUtil.getDays(enquiry.getLastModifiedDate(),new Date());
		    int siteVisitDays=DateUtil.getDays(enquiry.getDateOfSiteVisit(),new Date());
		    if(enquiry.getContactId()==null)
		    {
		    	return enquiryDtos;
		    }
		    enquiry.setContact(enquiry.getContactId());
			if(KeyConstants.RECORD_TYPE_PROSPECT.equals(enquiry.getContact().getRecordType())){
			
				if(siteVisitDays < KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays < KeyConstants.LAST_MODIFY_DAYS_LIMIT){
						
					    enquiry.setHasError(true);
						enquiry.setNonEdit("ENQUIRY");						
						enquiry.setMessage("Scenario 1: "+"Cannot edit enquiry details");
						enquiries.add(enquiry);						
						System.out.println("Scenario 1: "+"Cannot edit enquiry details");
				}else if((siteVisitDays < KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays > KeyConstants.LAST_MODIFY_DAYS_LIMIT)){
					    enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 3: "+"Create New Enquiry");
					    System.out.println("Scenario 3: "+"Create New Enquiry");
			    }else if(siteVisitDays > KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays < KeyConstants.LAST_MODIFY_DAYS_LIMIT){
						
				    	enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 4: "+"Create New Enquiry");
					    System.out.println("Scenario 4: "+"Create New Enquiry");
				}else if(siteVisitDays > KeyConstants.SITE_VISIT_DAYS_LIMIT){
					   
						enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 2: "+"Create New Enquiry");
					    System.out.println("Scenario 2: "+"Create New Enquiry");
				}else{	
					enquiry.setHasError(false);
					enquiries.add(enquiry);
					System.out.println("Scenario 5: else condition");
				}
			}else if(KeyConstants.RECORD_TYPE_CUSTOMER.equals(enquiry.getContact().getRecordType())){
	
				if(siteVisitDays < KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays < KeyConstants.LAST_MODIFY_DAYS_LIMIT){
					    enquiry.getContact().setHasError(true);
					    enquiry.setHasError(true);
						enquiry.setNonEdit("ENQUIRY");
						enquiry.setMessage("Scenario 1: "+"Cannot edit enquiry details");
						enquiries.add(enquiry);
						System.out.println("Scenario 1: "+"Cannot edit enquiry details");
				}else if((siteVisitDays < KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays > KeyConstants.LAST_MODIFY_DAYS_LIMIT)){
						
					    enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 3: "+"Create New Enquiry");
					    System.out.println("Scenario 3: "+"Create New Enquiry");
			    }else if(siteVisitDays > KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays < KeyConstants.LAST_MODIFY_DAYS_LIMIT){
						
				    	enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 4: "+"Create New Enquiry");
					    System.out.println("Scenario 4: "+"Create New Enquiry");
				}else if(siteVisitDays > KeyConstants.SITE_VISIT_DAYS_LIMIT){
					   
						enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 2: "+"Create New Enquiry");
					    System.out.println("Scenario 2: "+"Create New Enquiry");
				}else{
						enquiry.setHasError(true);
						enquiry.setNonEdit("CONTACT");
						enquiries.add(enquiry);
						enquiry.setMessage("Scenario 5: "+"Cannot edit contact details");
						System.out.println("Scenario 5: "+"Cannot edit contact details");
				}
			}			 
		 }	*/
			
			
		 return enquiries;
	}
	@Override
	public List<EnquiryDto> getEnquiriesByMobileNo(String countryCode,String mobileNo,String projectSfid,String token,String userid) {
		
		return pushEnquiryDataService.getEnquiriesByMobileNo(countryCode,mobileNo,projectSfid,token,userid);
	}
	@Override
	public EnquiryDto getEnquiryById(EnquiryDto dto) {
		return pushEnquiryDataService.getEnquiryById(dto);
	}

	@Override
	public boolean validateMobileAndToken(String mobileNo,String token,String projectSfid) {
		return pushEnquiryDataService.validateMobileAndToken(mobileNo, token,projectSfid);
	}

	@Override
	public EnquiryDto savePaymentDetails(EnquiryDto enq) {
		
		enq=pushEnquiryDataService.savePaymentDetails(enq);
		if(!enq.isHasError()){
			EnquiryReportDto enqRepo=enq.getEnquiryReport();
		
			try {				
				Path path = Paths.get(KeyConstants.BASE_PATH);
				if(!Files.exists(path)){
					Files.createDirectories(path);
				}
				if(enqRepo.getChequeFile()!=null){
				  byte[] bytes = enqRepo.getChequeFile().getBytes();
				  Path chequePath=Paths.get(KeyConstants.BASE_PATH+new Date().getTime()+"_"+enqRepo.getChequeFile().getOriginalFilename());
				  Files.write(chequePath, bytes);
				  enqRepo.setChequeno_file_name(chequePath.toString());
				}
				if(enqRepo.getPanFile()!=null){
					  byte[] bytes = enqRepo.getPanFile().getBytes();
					  Path panPath=Paths.get(KeyConstants.BASE_PATH+new Date().getTime()+"_"+enqRepo.getPanFile().getOriginalFilename());
					  Files.write(panPath, bytes);
					  enqRepo.setPanNo_file_name(panPath.toString());
				}
				if(enqRepo.getSwapFile()!=null){
					  byte[] bytes = enqRepo.getSwapFile().getBytes();
					  Path swapPath=Paths.get(KeyConstants.BASE_PATH+new Date().getTime()+"_"+enqRepo.getSwapFile().getOriginalFilename());
					  Files.write(swapPath, bytes);
					  enqRepo.setuTRNo_file_name(swapPath.toString());
				}			
				enq.setEnquiryReport(enqRepo);
			} catch (IOException e) {
				e.printStackTrace();
			}
            
			enquiryReportService.updatePaymentDetails(enq.getEnquiryReport());
		}
		return enq;
	}
	@Override
	public List<EnquiryDto> getEnquiriesByMobileNoEOI(String countryCode,String mobileNo, String projectSfid) {
			List<EnquiryDto> enquiryList=pushEnquiryDataService.getEnquiriesByMobileNoEOI(countryCode,mobileNo,projectSfid);
			enquiryList.sort(Comparator.comparing(EnquiryDto::getLastModifiedDate).reversed());
			/*Comment By Satheesh Kyatham for invalid code for EOI enquiry  - 23rd Sept 2019*/
			/*List<EnquiryDto> enquiries=new ArrayList<>();
			if(CommonUtil.isListEmpty(enquiryList)){
				 return enquiryList;
			 }
			else //3) Check for Enquiries with LMD<30 && Date of site visit <90 && Site visit counter or Appointment counter >0
			{
				String name = "";
				for(EnquiryDto enquiry:enquiryList){
					
					enquiry.setContact(enquiry.getContactId());
					int lastModifyDays=DateUtil.getDays(enquiry.getLastModifiedDate(),new Date());
				    int siteVisitDays=DateUtil.getDays(enquiry.getDateOfSiteVisit(),new Date());
				   
				    if(enquiry.getSite_Visit_Done__c()==null)
				    {
				    	enquiry.setSite_Visit_Done__c(0.0);
				    }
				    if(enquiry.getAppointment_Done__c()==null)
				    {
				    	enquiry.setAppointment_Done__c(0.0);
				    }
				    //
					if((enquiry.getSite_Visit_Done__c()>0 || enquiry.getAppointment_Done__c()>0) 
							&& siteVisitDays < KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays < KeyConstants.LAST_MODIFY_DAYS_LIMIT)
					{
						enquiries.add(enquiry);
					}
				}
				//4) If 3) gives 0 results then latest enquires checked for Appointment propsed and site visit requested counter >0
				if(enquiries.size()==0)
				{
					EnquiryDto enquiry = enquiryList.get(0);
					if(KeyConstants.RECORD_TYPE_CUSTOMER.equals(enquiry.getContact().getRecordType())){
						enquiry.getContact().setHasError(true);
						name ="Cannot edit customer details & ";
					}
					if(enquiry.getSite_visit_requested__c()==null)
				    {
				    	enquiry.setSite_visit_requested__c(0.0);
				    }
					if(enquiry.getAppointment__c()==null)
				    {
				    	enquiry.setAppointment__c(0.0);
				    }
					if((enquiry.getSite_visit_requested__c()>0 || enquiry.getAppointment__c()>0 || enquiry.getEoi_enquiry__c()==true) && enquiry.getSite_Visit_Done__c()==0 && enquiry.getAppointment_Done__c()==0 )
					{
						enquiry.setHasError(true);
						//enquiry.setNonEdit("ENQUIRY");						
						enquiry.setMessage("Scenario 4: "+name+"Edit existing enquiry details");
						enquiry.setIsReferredByChannelPartner("");
						enquiry.setIsReferredByChannelPartnerFlag("");
						enquiries.add(enquiry);						
						System.out.println("Scenario 4: "+name+"Edit existing enquiry details");
						//add CP hide and show logic
					}
					else
					{
						enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 2: "+"Create New Enquiry");
					    System.out.println("Scenario 2: "+"Create New Enquiry");
						//return enquiries;
					}
				}
				else if(enquiries.size()==1)//5) If 3) gives 1 results then source protuction.
				{
					EnquiryDto enquiry = enquiries.get(0);
					
					if(KeyConstants.RECORD_TYPE_CUSTOMER.equals(enquiry.getContact().getRecordType())){
						enquiry.getContact().setHasError(true);
						name= "customer & ";
					}
					enquiry.setHasError(true);
					enquiry.setNonEdit("ENQUIRY");						
					enquiry.setMessage("Scenario 1: "+"Cannot edit "+name+" enquiry details");
					enquiries.add(enquiry);						
					System.out.println("Scenario 1: "+"Cannot edit "+name+"enquiry details");
				}
				else if(enquiries.size()>1)//6) If 3) gives more than 1 results then select latest ENQ basis site visit date
				{
					//pick latest one basis site visit done
					enquiries.sort(Comparator.comparing(EnquiryDto::getDateOfSiteVisit).reversed());
					EnquiryDto enquiry = enquiries.get(0);
					if(KeyConstants.RECORD_TYPE_CUSTOMER.equals(enquiry.getContact().getRecordType())){
						enquiry.getContact().setHasError(true);
						name= "customer & ";
					}
					enquiry.setHasError(true);
					enquiry.setNonEdit("ENQUIRY");						
					enquiry.setMessage("Scenario 1: "+"Cannot edit "+name+" enquiry details");
					enquiries.add(enquiry);						
					System.out.println("Scenario 1: "+"Cannot edit "+name+" enquiry details");
				}
			}*/
			
			/*EnquiryComparator enquiryComparator = new EnquiryComparator();
			Collections.sort(enquiryList, enquiryComparator);
			EnquiryDto enquiryTop = enquiryList.get(0);
			List<EnquiryDto> enquiryDtos=new ArrayList<>();
			enquiryDtos.add(enquiryTop);
			if(enquiryTop.getWalkInSource() == null) {
				return enquiryDtos;
			}
		List<EnquiryDto> enquiries=new ArrayList<>();
		for(EnquiryDto enquiry:enquiryDtos){
			int lastModifyDays=DateUtil.getDays(enquiry.getLastModifiedDate(),new Date());
		    int siteVisitDays=DateUtil.getDays(enquiry.getDateOfSiteVisit(),new Date());
		    if(enquiry.getContactId()==null)
		    {
		    	return enquiryDtos;
		    }
		    enquiry.setContact(enquiry.getContactId());
			if(KeyConstants.RECORD_TYPE_PROSPECT.equals(enquiry.getContact().getRecordType())){
			
				if(siteVisitDays < KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays < KeyConstants.LAST_MODIFY_DAYS_LIMIT){
						
					    enquiry.setHasError(true);
						enquiry.setNonEdit("ENQUIRY");						
						enquiry.setMessage("Scenario 1: "+"Cannot edit enquiry details");
						enquiries.add(enquiry);						
						System.out.println("Scenario 1: "+"Cannot edit enquiry details");
				}else if((siteVisitDays < KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays > KeyConstants.LAST_MODIFY_DAYS_LIMIT)){
					    enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 3: "+"Create New Enquiry");
					    System.out.println("Scenario 3: "+"Create New Enquiry");
			    }else if(siteVisitDays > KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays < KeyConstants.LAST_MODIFY_DAYS_LIMIT){
						
				    	enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 4: "+"Create New Enquiry");
					    System.out.println("Scenario 4: "+"Create New Enquiry");
				}else if(siteVisitDays > KeyConstants.SITE_VISIT_DAYS_LIMIT){
					   
						enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 2: "+"Create New Enquiry");
					    System.out.println("Scenario 2: "+"Create New Enquiry");
				}else{	
					enquiry.setHasError(false);
					enquiries.add(enquiry);
					System.out.println("Scenario 5: else condition");
				}
			}else if(KeyConstants.RECORD_TYPE_CUSTOMER.equals(enquiry.getContact().getRecordType())){
	
				if(siteVisitDays < KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays < KeyConstants.LAST_MODIFY_DAYS_LIMIT){
					    enquiry.getContact().setHasError(true);
					    enquiry.setHasError(true);
						enquiry.setNonEdit("ENQUIRY");
						enquiry.setMessage("Scenario 1: "+"Cannot edit enquiry details");
						enquiries.add(enquiry);
						System.out.println("Scenario 1: "+"Cannot edit enquiry details");
				}else if((siteVisitDays < KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays > KeyConstants.LAST_MODIFY_DAYS_LIMIT)){
						
					    enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 3: "+"Create New Enquiry");
					    System.out.println("Scenario 3: "+"Create New Enquiry");
			    }else if(siteVisitDays > KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays < KeyConstants.LAST_MODIFY_DAYS_LIMIT){
						
				    	enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 4: "+"Create New Enquiry");
					    System.out.println("Scenario 4: "+"Create New Enquiry");
				}else if(siteVisitDays > KeyConstants.SITE_VISIT_DAYS_LIMIT){
					   
						enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 2: "+"Create New Enquiry");
					    System.out.println("Scenario 2: "+"Create New Enquiry");
				}else{
						enquiry.setHasError(true);
						enquiry.setNonEdit("CONTACT");
						enquiries.add(enquiry);
						enquiry.setMessage("Scenario 5: "+"Cannot edit contact details");
						System.out.println("Scenario 5: "+"Cannot edit contact details");
				}
			}			 
		 }	*/	 
		 return enquiryList;
	
	}
	@Override
	public List<EnquiryDto> getEnquiriesByCPAppWithParam(String custname,String countryCode,String mobileno, String projectsfid,
			String emailid) {

//		 1)Get all Enquiries basis on mobile no and project SFid
		
			List<EnquiryDto> enquiryList=pushEnquiryDataService.getEnquiriesByCPAppWithParam(custname,countryCode,mobileno,projectsfid,emailid);
			
		
//		 2) if the list size is 0 then return  new Enquiry form
			if(CommonUtil.isListEmpty(enquiryList)){
				 return enquiryList;
			 }

			EnquiryComparator enquiryComparator = new EnquiryComparator();
			
			Collections.sort(enquiryList, enquiryComparator);
			
//			3)If no enquiry with source walk-in, [has status walk-in done]
//			3.1)then find the latest enquiry and provide the editable enquiry form with available data
				
			EnquiryDto enquiryTop = enquiryList.get(0);
			List<EnquiryDto> enquiryDtos=new ArrayList<>();
			enquiryDtos.add(enquiryTop);
			 
			
			if(enquiryTop.getWalkInSource() == null) {
				return enquiryDtos;
			}

//		4)Find the latest enquiry with source "walk-in" [has status walk-in done]
			
//		4.1)If found the apply further checks.
			

		List<EnquiryDto> enquiries=new ArrayList<>();
		for(EnquiryDto enquiry:enquiryDtos){
			int lastModifyDays=DateUtil.getDays(enquiry.getLastModifiedDate(),new Date());
		    int siteVisitDays=DateUtil.getDays(enquiry.getDateOfSiteVisit(),new Date());
		    if(enquiry.getContactId()==null)
		    {
		    	return enquiryDtos;
		    }
		    enquiry.setContact(enquiry.getContactId());
			if(KeyConstants.RECORD_TYPE_PROSPECT.equals(enquiry.getContact().getRecordType())){
			
				if(siteVisitDays < KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays < KeyConstants.LAST_MODIFY_DAYS_LIMIT){
						
					    enquiry.setHasError(true);
						enquiry.setNonEdit("ENQUIRY");						
						enquiry.setMessage("Scenario 1: "+"Cannot edit enquiry details");
						enquiries.add(enquiry);						
						System.out.println("Scenario 1: "+"Cannot edit enquiry details");
				}else if((siteVisitDays < KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays > KeyConstants.LAST_MODIFY_DAYS_LIMIT)){
					    enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 3: "+"Create New Enquiry");
					    System.out.println("Scenario 3: "+"Create New Enquiry");
			    }else if(siteVisitDays > KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays < KeyConstants.LAST_MODIFY_DAYS_LIMIT){
						
				    	enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 4: "+"Create New Enquiry");
					    System.out.println("Scenario 4: "+"Create New Enquiry");
				}else if(siteVisitDays > KeyConstants.SITE_VISIT_DAYS_LIMIT){
					   
						enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 2: "+"Create New Enquiry");
					    System.out.println("Scenario 2: "+"Create New Enquiry");
				}else{	
					enquiry.setHasError(false);
					enquiries.add(enquiry);
					System.out.println("Scenario 5: else condition");
				}
			}else if(KeyConstants.RECORD_TYPE_CUSTOMER.equals(enquiry.getContact().getRecordType())){
	
				if(siteVisitDays < KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays < KeyConstants.LAST_MODIFY_DAYS_LIMIT){
					    enquiry.getContact().setHasError(true);
					    enquiry.setHasError(true);
						enquiry.setNonEdit("ENQUIRY");
						enquiry.setMessage("Scenario 1: "+"Cannot edit enquiry details");
						enquiries.add(enquiry);
						System.out.println("Scenario 1: "+"Cannot edit enquiry details");
				}else if((siteVisitDays < KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays > KeyConstants.LAST_MODIFY_DAYS_LIMIT)){
						
					    enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 3: "+"Create New Enquiry");
					    System.out.println("Scenario 3: "+"Create New Enquiry");
			    }else if(siteVisitDays > KeyConstants.SITE_VISIT_DAYS_LIMIT && lastModifyDays < KeyConstants.LAST_MODIFY_DAYS_LIMIT){
						
				    	enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 4: "+"Create New Enquiry");
					    System.out.println("Scenario 4: "+"Create New Enquiry");
				}else if(siteVisitDays > KeyConstants.SITE_VISIT_DAYS_LIMIT){
					   
						enquiry.setHasError(true);
					    enquiry.setMessage("Scenario 2: "+"Create New Enquiry");
					    System.out.println("Scenario 2: "+"Create New Enquiry");
				}else{
						enquiry.setHasError(true);
						enquiry.setNonEdit("CONTACT");
						enquiries.add(enquiry);
						enquiry.setMessage("Scenario 5: "+"Cannot edit contact details");
						System.out.println("Scenario 5: "+"Cannot edit contact details");
				}
			}			 
		 }		 
	
		 return enquiries;
	
	}
	
}
