package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.dto.ChannelPartnerDto;
import com.godrej.properties.model.ChannelPartner;

public interface ChannelPartnerDao {

	public List<ChannelPartnerDto> getChannelPartner(String name);
	
	public ChannelPartner getChannelPartnerBySfid(String sfid);

	public int getChannelPartnerId(String sfid);
}
