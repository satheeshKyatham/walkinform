package com.godrej.properties.master.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dto.SysConfigEnum;
import com.godrej.properties.master.dao.SysConfigDao;
import com.godrej.properties.master.dto.SysConfigDto;
import com.godrej.properties.master.model.SysConfig;
import com.godrej.properties.master.service.SysConfigService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Service
public class SysConfigServiceImpl implements SysConfigService{

	private Logger log =  LoggerFactory.getLogger(getClass());
	
	Map<String, SysConfig> configMap =  new ConcurrentHashMap<>();
	
	@Autowired 
	private SysConfigDao sysConfigDao;

	@PostConstruct
	@Transactional(propagation = Propagation.REQUIRED)
	public void postInit() {
		load();
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String reload() {
		configMap = new HashMap<>();
		return load();
	}
	@Override
	public String reload(String key) {
		try {
			SysConfig config = sysConfigDao.getConfig(key);
			if(config == null) {
				return "No value against key - " + key;
			}
			configMap.put(key, config);
		}catch (Exception e) {
			log.error("Error", e);
			return "Problem in reloading :- " + e.getMessage();
		}
		return "OK";
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String reload(String key, String recordId) {
		try {
			SysConfig config = sysConfigDao.getConfig(key, recordId);
			if(config == null) {
				return "No value against key - " + key ;
			}
			loadEntity(config);
		}catch (Exception e) {
			log.error("Error", e);
			return "Problem in reloading :- " + e.getMessage();
		}
		return "OK";
	}

	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String load() {
		log.info("Loading sysconfig");
		try {
			List<SysConfig> configList =  sysConfigDao.getConfigList();
			loadEntities(configList);
		}catch (Exception e) {
			log.error("Error", e);
			return "Problem in loading " + e.getMessage(); 
		}
		return "OK";
	}

	@Override
	public String getValue(String name) {
		SysConfig config = configMap.get(name);
		if(config == null || !"Y".equalsIgnoreCase(config.getIsActive())) {
			return null;
		}
		
		return config.getValue();
	}

	private SysConfigDto convertEntityToDto(SysConfig sysConfig) {
		
		if(sysConfig == null) {
			return null;
		}
		SysConfigDto sysConfigDto = new SysConfigDto();
		sysConfigDto.setSysConfigId(sysConfig.getSysConfigId());
		sysConfigDto.setIsActive(sysConfig.getIsActive());
		sysConfigDto.setName(sysConfig.getName());
		sysConfigDto.setValue(sysConfig.getValue());
		
		return sysConfigDto;
	}
	
	public void loadEntities(List<SysConfig> configList) {
		if(configList == null || configList.isEmpty()) {
			return;
		}
		for(SysConfig config: configList) {
			loadEntity(config);
		}
	}
	
	public void loadEntity(SysConfig config) {
		if(config == null) {
			return;
		}
		
		String mapKey = getMapKey(config.getName(), config.getRecordId());
		configMap.put(mapKey, config);
	}
	
	@Override
	public int getValueAsInt(String key) {
		String value  = getValue(key);
		if(value == null) {
			return 0;
		}
		try {
			return Integer.parseInt(value);
		}catch (Exception e) {
			log.error("Error: ", e);
		}
		return 0;
	}
	@Override
	public int getValueAsInt(String key, String recordId) {
		String mapKey= getMapKey(key, recordId);
		String value  = getValue(mapKey);
		if(value ==  null) {
			return 0;
		}
		try {
			return Integer.parseInt(value);
		}catch (Exception e) {
			log.error("Error: ", e);
		}
		return 0;
	}
	
	@Override
	public int getValueAsInt(SysConfigEnum value, String recordId) {
		String key =  value.toString();
		return getValueAsInt(key, recordId);
	}
	
	@Override
	public String getValue(String key, String recordId) {
		String mapKey =  getMapKey(key, recordId);
		return getValue(mapKey);
	}
	
	@Override
	public String getValue(SysConfigEnum value, String recordId) {
		String key = value.toString();
		return getValue(key, recordId);
	}
	
	@Override
	public Double getValueAsDouble(SysConfigEnum param, String recordId) {
		String key = param.toString();
		String mapKey= getMapKey(key, recordId);
		String value  = getValue(mapKey);
		if(value ==  null) {
			return 0d;
		}
		try {
			return Double.valueOf(value);
		}catch (Exception e) {
			log.error("Error: ", e);
		}
		return 0d;
	}
	private String getMapKey(String key, String recordId) {
		String record = recordId==null?"":"-"+recordId;
		String configName =  key == null?"":key;
		return configName + record;
	}
}
