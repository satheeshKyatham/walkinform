package com.godrej.properties.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.constants.ConfigConstants;
import com.godrej.properties.converter.ChannelPartnerConverter;
import com.godrej.properties.dao.AAbstractDao;
import com.godrej.properties.dao.ChannelPartnerDao;
import com.godrej.properties.dto.ChannelPartnerDto;
import com.godrej.properties.model.ChannelPartner;

@Repository
public class ChannelPartnerDaoImpl extends AAbstractDao<ChannelPartner> implements ChannelPartnerDao {

	@Autowired
	private ChannelPartnerConverter channelPartnerConverter;
	
	@Override
	@Transactional(readOnly=true)
	public List<ChannelPartnerDto> getChannelPartner(String name) {
		 StringBuilder jpql=new StringBuilder();
		 // Condition added default id 
		 jpql.append(" SELECT c FROM ChannelPartner c where c.recordTypeId = '0126F000000uazCQAQ' and  LOWER(c.name) like Lower(:name) and propstrength__active__c='Yes' ORDER BY c.name ASC");
		 Map<String, Object> params=new HashMap<>();
		 params.put("name",name);
		 List<ChannelPartner> channelPartnerList=getEntities(jpql.toString(), params, ConfigConstants.CHANNEL_PARTNER_MAX_RESULTS);
		 return channelPartnerConverter.entityToDto(channelPartnerList);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ChannelPartnerDto> getChannelPartners() {
		 StringBuilder jpql=new StringBuilder();
		 // Condition added default id 
		 jpql.append(" SELECT c FROM ChannelPartner c where c.recordTypeId = '0126F000000uazCQAQ'  and propstrength__active__c='Yes' ORDER BY c.name ASC");
		 List<ChannelPartner> channelPartnerList=getEntities(jpql.toString(),null);
		 return channelPartnerConverter.entityToDto(channelPartnerList);
	}
	@Override
	public ChannelPartner getChannelPartnerBySfid(String sfid) {
		StringBuilder jpql=new StringBuilder();
		 // Condition added default id 
		 jpql.append(" SELECT c FROM ChannelPartner c where c.sfid = :sfid");
		 Map<String, Object> params=new HashMap<>();
		 params.put("sfid",sfid);
		 return getSingleEntity(jpql.toString(), params);
		 
	}

	@Override
	public int getChannelPartnerId(String sfid) {
		ChannelPartner channelPartner = getChannelPartnerBySfid(sfid);
		if(channelPartner == null) {
			return 0;
		}
		return channelPartner.getChannelPartnerId();
	}
	
}
