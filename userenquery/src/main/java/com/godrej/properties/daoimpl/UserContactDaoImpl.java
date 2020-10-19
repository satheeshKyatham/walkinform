package com.godrej.properties.daoimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.converter.ContactConverter;
import com.godrej.properties.dao.AAbstractDao;
import com.godrej.properties.dao.UserContactDao;
import com.godrej.properties.dto.ContactDto;
import com.godrej.properties.model.Contact;
import com.godrej.properties.model.TriggerLog; 

@Repository("userContactDao")
@Transactional
public class UserContactDaoImpl extends AAbstractDao<Contact> implements UserContactDao {

	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private ContactConverter contactConverter;
	
	@Override
	public ContactDto findMobileNoExist(String countryCode, String mobileNo) {
		 StringBuilder jpql=new StringBuilder();
		/* jpql.append(" SELECT c FROM Contact c where c.mobileNo=:mobileNo ");*//* changed on 1.04.2019*/
		 jpql.append(" SELECT c FROM Contact c ");
		 jpql.append(" LEFT JOIN FETCH c.contactReport ccr ");
		/* jpql.append(" where c.mobileNo=:mobileNo ");*/
		 /*jpql.append(" WHERE concat(c.countryCode,c.mobile)=:mobileNo ");*/
		 jpql.append(" WHERE c.mobile=:mobile ")
		 .append(" AND c.countryCode=:countryCode ");
		 Map<String, Object> params=new HashMap<>();
		/* params.put("mobileNo", mobileNo);*/
		    /*String code=mobileNo.substring(0,3);
			String mobile=mobileNo.substring(3);*/
			params.put("countryCode",countryCode);
			params.put("mobile",mobileNo);
		 Contact contact=getSingleEntity(jpql.toString(), params);
		 
		 
		 return contactConverter.entityToDto(contact);
	}
	@Override
	public List<ContactDto> getContactByPartnerSfid(String sfid) {
		 StringBuilder jpql=new StringBuilder();
		 jpql.append(" SELECT c FROM Contact c where c.channelPartner.sfid=:sfid");
		 Map<String, Object> params=new HashMap<>();
		 params.put("sfid", sfid);
		 List<Contact> contact=getEntities(jpql.toString(), params);
		 return contactConverter.entityToDto(contact);
	}
	
	@Override
	public ContactDto save(ContactDto dto) {
		Contact contact=contactConverter.dtoToEntity(dto);
		System.out.println("Contact query Start :"+new Date());
		contact=create(contact);
		System.out.println("Contact query END :"+new Date());
		return findById(contact.getContactId());
	}

	@Override
	public ContactDto update(ContactDto dto) {
		Contact contact=contactConverter.dtoToEntity(dto);
		contact=update(contact);
		return contactConverter.entityToDto(contact);
	}
	
	@Override
	public ContactDto findById(Integer id){
		Contact contact=super.findOne(id);
		return contactConverter.entityToDto(contact);
	}
	@Override
	public List<ContactDto> getContactByEmail(String email) {
		//Changes By Satheesh Kyahtam
		List<Contact> contact=null;
		if(!email.equals(KeyConstants.COMMON_EMAIL))
		{
			StringBuilder jpql=new StringBuilder();
			 jpql.append(" SELECT c FROM Contact c where c.email=:email");
			 Map<String, Object> params=new HashMap<>();
			 params.put("email", email);
			 contact=getEntities(jpql.toString(), params);
		}
		 return contactConverter.entityToDto(contact);
	}
	@Override
	public String getBrokerContact(String brokeraccount) {
		 Query q = getSession().createNativeQuery("SELECT c.sfid FROM salesforce.contact c where c.accountid=:AccountId and RecordTypeId=:RecordTypeId");
		 q.setParameter("AccountId", brokeraccount);
		 q.setParameter("RecordTypeId", "0126F000000uazGQAQ");
		 return q.getResultList().get(0).toString();
	}
	@Override
	public int getContactPKID(String contactsfid) {
		try
		{
		 Query q = getSession().createNativeQuery("SELECT c.id FROM salesforce.contact c where c.sfid=:contactsfid");
		 q.setParameter("contactsfid", contactsfid);
		 return (int) q.getResultList().get(0);
		}
		catch (Exception e) {
			log.error("getContactPKID Contcat SFID: {} - "+contactsfid+" Error {}",e);
			return 0;
		}
		
	}
	@Override
	public ContactDto getContactBySfid(String sfid) {
		//Added By Satheesh Kyahtam - 19-10-2020
		 List<Contact> contact=null;
		 StringBuilder jpql=new StringBuilder();
		 jpql.append(" SELECT c FROM Contact c where c.sfid=:sfid");
		 Map<String, Object> params=new HashMap<>();
		 params.put("sfid", sfid);
		 contact= getEntities(jpql.toString(), params);
		 return contactConverter.entityToDto(contact.get(0));
	}

	
	
}
