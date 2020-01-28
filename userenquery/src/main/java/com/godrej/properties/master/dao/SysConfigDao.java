package com.godrej.properties.master.dao;

import java.util.List;

import com.godrej.properties.master.model.SysConfig;

/**
 * 
 * @author Vivek Birdi
 *
 */
public interface SysConfigDao {

	public SysConfig getConfig(String name);
	public List<SysConfig> getConfigList();	
	public SysConfig getConfig(String name, String recordId);
}
