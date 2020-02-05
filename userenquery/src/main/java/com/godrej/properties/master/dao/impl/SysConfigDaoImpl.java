package com.godrej.properties.master.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.master.dao.SysConfigDao;
import com.godrej.properties.master.dto.SysConfigDto;
import com.godrej.properties.master.model.SysConfig;
/**
 * 
 * @author Vivek Birdi
 *
 */
@Repository
@Transactional
public class SysConfigDaoImpl extends AbstractDao<SysConfig, SysConfig> implements SysConfigDao{


	@Override
	public SysConfig getConfig(String name) {
		StringBuilder jpql = new StringBuilder(); 
		jpql.append(" FROM SysConfig WHERE  name=:name");
		Map<String, Object> params = new HashMap<>();
		params.put("name",name);
		return getSingleEntity(jpql.toString(), params);
	}

	@Override
	public List<SysConfig> getConfigList() {
		StringBuilder jpql = new StringBuilder(); 
		jpql.append(" FROM SysConfig");
		return	getEntities(jpql.toString(), new HashMap<>());
	}

	@Override
	public SysConfig getConfig(String name, String recordId) {
		StringBuilder jpql = new StringBuilder(); 
		jpql.append(" FROM SysConfig WHERE name=:name and recordId=:recordId");
		Map<String, Object> params = new HashMap<>();
		params.put("name",name);
		params.put("recordId", recordId);
		return getSingleEntity(jpql.toString(), params);
	}
}
