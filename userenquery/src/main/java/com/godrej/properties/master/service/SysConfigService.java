package com.godrej.properties.master.service;

/**
 * 
 * @author Vivek Birdi
 *
 */
public interface SysConfigService {
	
	public static final String HOLD_TIME="HOLD_TIME";

	public String load();
	public String getValue(String name);
	public String reload();
	public String reload(String key);
	public int getValueAsInt(String key);
	public String reload(String key, String recordId);
	public int getValueAsInt(String key, String recordId);
}
