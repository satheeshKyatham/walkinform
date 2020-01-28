package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.ChannelPartnerDao;
import com.godrej.properties.dto.ChannelPartnerDto;
import com.godrej.properties.service.ChannelPartnerService;

@Service
public class ChannelPartnerServiceImpl implements ChannelPartnerService {

	@Autowired
	private ChannelPartnerDao channelPartnerDao;
	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public List<ChannelPartnerDto> getChannelPartners(String name){
		return channelPartnerDao.getChannelPartner(name);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public List<ChannelPartnerDto> getChannelPartners(){
		return channelPartnerDao.getChannelPartners();
	}
	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public int getChannelPartnerId(String sfid) {
		return channelPartnerDao.getChannelPartnerId(sfid);
	}
}
