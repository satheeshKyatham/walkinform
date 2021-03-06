package com.godrej.properties.dto;

import com.godrej.properties.common.dto.CommonDto;

public class ChannelPartnerDto extends CommonDto {




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer channelPartnerId;
	private String name;
	private String code;
	private String description;
    
	public Integer getChannelPartnerId() {
		return channelPartnerId;
	}
	public void setChannelPartnerId(Integer channelPartnerId) {
		this.channelPartnerId = channelPartnerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
