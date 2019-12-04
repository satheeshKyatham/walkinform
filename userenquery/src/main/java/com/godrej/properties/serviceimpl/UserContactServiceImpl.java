package com.godrej.properties.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.common.utilities.CommonValidations;
import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.dao.CountryDao;
import com.godrej.properties.dao.UserContactDao;
import com.godrej.properties.dto.ContactDto;
import com.godrej.properties.dto.ContactReportDto;
import com.godrej.properties.model.Country;
import com.godrej.properties.service.ContactReportService;
import com.godrej.properties.service.UserContactService;
import com.godrej.properties.util.CommonUtil;

@Service("userContactService")
@Transactional
public class UserContactServiceImpl implements UserContactService {

	@Autowired
	private UserContactDao userContactDao;
	
	@Autowired
	private ContactReportService contactReportService;
	
	@Autowired
	private CountryDao countryDao;
	
	@Override
	public ContactDto findMobileNoExist(String countryCode, String mobileno) {
		ContactDto contact= userContactDao.findMobileNoExist(countryCode,mobileno);
		if(null!=contact && KeyConstants.RECORD_TYPE_CUSTOMER.equals(contact.getRecordType())){
			contact.setHasError(true);
			contact.setMessage("Scenario 5: "+"Cannot edit existing customer details");
		}
		return contact;
	}

	@Override
	public ContactDto save(ContactDto contact) {
		contact.setMobile(contact.getMobileNo());
	    ContactReportDto contactReport=saveContactReport(contact);
		contact.setContactReport(contactReport);
		//1. find the record against the email id filled in UI Form.
		List<ContactDto> contactList=getContactByEmail(contact.getOtherEmail());
		//2. if record exists then set emailid= COMMON_EMAIL and alternate id from UI Form.
		if(!CommonUtil.isListEmpty(contactList)){
			contact.setOtherEmail(contact.getOtherEmail());
			contact.setEmail(KeyConstants.COMMON_EMAIL);
		}
		//3. if record not exists then set emailid and alternate id as obtained from UI Form.
		else {
			contact.setOtherEmail(contact.getOtherEmail());
			contact.setEmail(contact.getOtherEmail());	
		}
	  	
		
		// Address Splits into address 1,2,3 
		String address1=contact.getAddressLine1()==null?"":contact.getAddressLine1();
    	String address=contact.getAddressLine2();
    	if(address ==null) {
    		return contact;
    	}
    	int length=address.length();
    	if(length<=32)
        {
    		contact.setAddressLine2(address.substring(0, length));

        }else if(length>32 && length<64){             

        	 contact.setAddressLine2(address.substring(0, 32));

             if(length<64)
             {
            	 contact.setAddressLine3(address.substring(32, length)); 
             }
        }/*else if((length>64 && length<96) || length>96){ */         
        else if(length>=64){
        	contact.setAddressLine2(address.substring(0, 32));
        	contact.setAddressLine3(address.substring(32, 64));        	
        }
    	/*else if(length>96){
        	contact.setAddressLine2(address.substring(0, 32));
        	contact.setAddressLine3(address.substring(32, 64));  
        }*/
    
    	// 
    	contact.setResidenceFullAddress(address1+contact.getAddressLine2()+
    			(contact.getAddressLine3()!=null?contact.getAddressLine3():"")+contact.getCity()
    	    	+contact.getResidentialState()+contact.getResidentialCountry());
    	    	
    	//contact.setMailingCountry("Utd.Arab Emir.");
    	System.out.println("Country Name233:"+contact.getCountry());
		Country country = countryDao.getCountryData(contact.getCountryCode());
		if(country!=null)
			contact.setMailingCountry(country.getName().toString());//
		else
			contact.setMailingCountry("India");//
		
		/*Customer Occupation value push into Contact -  Change By Satheesh Kyatham- 26-09-2019*/
		/*=======Start==========*/
		if(contact.getContactReport().getEmploymentStatus()!=null)
			contact.setOccupation(contact.getContactReport().getEmploymentStatus());
		/*=========End========*/
		
		return userContactDao.save(contact);
	}
	private ContactReportDto saveContactReport(ContactDto dto){
		
		if(null==dto.getContactReport().getContactReportId()){
			dto.getContactReport().setContactName(dto.getFirstName()+" "+dto.getLastName());
			dto.getContactReport().setMobileNo(dto.getCountryCode()+dto.getMobile());
			return contactReportService.save(dto.getContactReport());			
		}else{
			ContactReportDto dest=contactReportService.getContactReportById(dto.getContactReport());
			if(dest == null) {
				dto.getContactReport().setContactName(dto.getFirstName()+" "+dto.getLastName());
				dto.getContactReport().setMobileNo(dto.getCountryCode()+dto.getMobile());
				return contactReportService.save(dto.getContactReport());			
			}
			dest.setContactName(dto.getFirstName()+" "+dto.getLastName());
			dest.setMobileNo(dto.getCountryCode()+dto.getMobile());
			return contactReportService.updateBaseInfo(dto.getContactReport(), dest);			
		}
	}
	@Override
	public ContactDto update(ContactDto contact) {
		return userContactDao.update(contact);
	}
	
	@Override
	public ContactDto updateBaseInfo(ContactDto src,ContactDto dest){
		dest = copyBaseInfo(src, dest);
		return update(dest);
	}
	
	@Override
	public ContactDto updateAddressInfo(ContactDto src,ContactDto dest){
		dest=copyAddressInfo(src, dest);
		return update(dest);
	}
	
	/*@Override
	public ContactDto updateOtherInfo(ContactDto src,ContactDto dest){
		dest=copyOtherInfo(src, dest);
		return update(dest);
	}*/
	
	private ContactDto copyBaseInfo(ContactDto src, ContactDto dest) {
		src.setMobile(dest.getMobile());
		ContactReportDto contactReport=saveContactReport(src);
		dest.setContactReport(contactReport);
		
		/*dest.setSalutation(src.getSalutation());*/
		
		dest.setMobile(src.getMobileNo());
		dest.setFirstName(src.getFirstName());
		dest.setLastName(src.getLastName());
		//1. find the record against the email id filled in UI Form.
			List<ContactDto> contactList=getContactByEmail(src.getOtherEmail());
			//2. if record exists then set emailid= COMMON_EMAIL and alternate id from UI Form.
			if(!CommonUtil.isListEmpty(contactList) ){
				if(dest.getOtherEmail()!=null) {
					if(!dest.getOtherEmail().equals(src.getOtherEmail())) {
						dest.setOtherEmail(src.getOtherEmail());
						dest.setEmail(KeyConstants.COMMON_EMAIL);
					}
				}else {
					dest.setOtherEmail(src.getOtherEmail());
					dest.setEmail(src.getOtherEmail());
				}
			}
			//3. if record not exists then set emailid and alternate id as obtained from UI Form.
			else {
				dest.setOtherEmail(src.getOtherEmail());
				dest.setEmail(src.getOtherEmail());	
			}
		
			//dest.setEmail(src.getEmail());
		/*dest.setAgeGroup(src.getAgeGroup());*/		
		
		dest.setAddressLine1(src.getAddressLine1()!=null?src.getAddressLine1():"");
		String address=src.getAddressLine2();
		int length=src.getAddressLine2().length();
    	if(length<=32)
        {
    		dest.setAddressLine2(address.substring(0, length));

        }else if(length>32 && length<64){             

        	dest.setAddressLine2(address.substring(0, 32));

             if(length<64)
             {
            	 dest.setAddressLine3(address.substring(32, length)); 
             }
        }/*else if((length>64 && length<96) || length>96){ */         
        else if(length>=64){
        	dest.setAddressLine2(address.substring(0, 32));
        	dest.setAddressLine3(address.substring(32, 64));        	
        }

    	dest.setResidenceFullAddress(address+" "+src.getCity()+" "+src.getResidentialState()+" "+src.getResidentialCountry());
    	
    	dest.setCity(src.getCity());
		dest.setPinCode(src.getPinCode());
		dest.setResidentialState(src.getResidentialState());
		dest.setResidentialCountry(src.getResidentialCountry());
		/*dest.setIndustry(src.getIndustry());*/
		dest.setCompanyName(src.getCompanyName());
		//dest.setOtherEmail(src.getOtherEmail());
		return dest;
	}

	private ContactDto copyAddressInfo(ContactDto src, ContactDto dest) {
		
		ContactReportDto contactReportDest=contactReportService.getContactReportById(src.getContactReport());
		contactReportDest=contactReportService.updateAddressInfo(src.getContactReport(), contactReportDest);
		dest.setContactReport(contactReportDest);
		
		/*dest.setCurrentResidenceType(src.getCurrentResidenceType());*/
		/*dest.setOccupation(src.getOccupation());*/
		/*dest.setDesignation(src.getDesignation());*/
		/*dest.setCompanyName(src.getCompanyName());*/
		/*dest.setOfficeCity(src.getOfficeCity());
		dest.setOfficePinCode(src.getOfficePinCode());
		dest.setCompanyLocality(src.getCompanyLocality());*/
		return dest;
	}

	/*private ContactDto copyOtherInfo(ContactDto src, ContactDto dest) {
		
		return dest;
	}*/

	@Override
	public ContactDto findById(ContactDto contact) {
		if(null==contact || null==contact.getContactId()){
			return null;
		}
		return userContactDao.findById(contact.getContactId());
	}
	
	@Override
	public List<ContactDto> getContactByPartnerSfid(String sfid) {
		if(CommonValidations.isEmpty(sfid)){
			return null;
		}
		return userContactDao.getContactByPartnerSfid(sfid);
	}

	@Override
	public List<ContactDto> getContactByEmail(String email) {
		if(CommonValidations.isEmpty(email)){
			return new ArrayList<>();
		}
		return userContactDao.getContactByEmail(email);
	}

	@Override
	public String getBrokerContact(String brokeraccount) {
		// TODO Auto-generated method stub
		return userContactDao.getBrokerContact(brokeraccount);
	}

	

}
