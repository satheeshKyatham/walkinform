package com.godrej.properties.master.service;

import com.godrej.properties.dto.SysConfigEnum;

/**
 * 
 * @author Vivek Birdi
 *
 */
public interface SysConfigService {
	
	public static final String HOLD_TIME="HOLD_TIME";

	public static final String OFFER_CREATION_TEMPLATE="OFFER_CREATION_TEMPLATE";	
	public static final String OFFER_CREATION_TEMPLATE_DEFAULT="OFFER_CREATION_TEMPLATE_DEFAULT";
	
	public String load();
	public String getValue(String name);
	public String reload();
	public String reload(String key);
	public int getValueAsInt(String key);
	public String reload(String key, String recordId);
	public int getValueAsInt(String key, String recordId);
	public String getValue(String key, String recordId);
	public String getValue(SysConfigEnum value, String recordId);
	public int getValueAsInt(SysConfigEnum value, String recordId);
	public Double getValueAsDouble(SysConfigEnum param, String recordId);
}
