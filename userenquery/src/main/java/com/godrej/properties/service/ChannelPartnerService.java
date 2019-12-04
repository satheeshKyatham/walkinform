package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.dto.ChannelPartnerDto;

public interface ChannelPartnerService {

	public List<ChannelPartnerDto> getChannelPartners(String name);
	
	public int getChannelPartnerId(String sfid);

}
