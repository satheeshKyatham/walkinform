package com.godrej.properties.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.TokenScreenConfigDao;
import com.godrej.properties.dto.TokenScreenConfigDto;
import com.godrej.properties.model.EOIPreferenceDtl;
import com.godrej.properties.model.TokenScreenConfig;
import com.godrej.properties.service.TokenScreenConfigService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service("tokenScreenConfigService")
@Transactional
public class TokenScreenConfigServiceImpl implements TokenScreenConfigService{

	@Autowired
	TokenScreenConfigDao tokenScreenConfigDao;
	
	@Override
	public TokenScreenConfigDto insertConfig(TokenScreenConfigDto aLogDto) {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Integer createdby = null; 
		Integer updatedby = null;
		
		TokenScreenConfig aLog = new TokenScreenConfig();
		
		if (!aLogDto.getCreatedby().trim().equals("undefined") && !aLogDto.getCreatedby().trim().equals("")) {
			createdby = Integer.parseInt(aLogDto.getCreatedby()); 
		}
		
		if (!aLogDto.getUpdatedby().trim().equals("undefined") && !aLogDto.getUpdatedby().trim().equals("")) {
			updatedby = Integer.parseInt(aLogDto.getUpdatedby()); 
		}

		aLog.setCreatedby(createdby);
		aLog.setUpdatedby(updatedby);
		aLog.setCreated(timestamp);
		aLog.setUpdated(timestamp);
		
		aLog.setProjectsfid(aLogDto.getProjectsfid());
		aLog.setToken_type(aLogDto.getToken_type());
		aLog.setKey_cont1(aLogDto.getKey_cont1());
		aLog.setKey_cont2(aLogDto.getKey_cont2());
		aLog.setVideo_url(aLogDto.getVideo_url());
		aLog.setImage_url(aLogDto.getImage_url());
		aLog.setTicker_label(aLogDto.getTicker_label());
		aLog.setTicker_text(aLogDto.getTicker_text());
		
		tokenScreenConfigDao.insertConfig(aLog);
		return null;
	}
	
	@Override
	public TokenScreenConfig getTncData(String projectid) {
		return tokenScreenConfigDao.getTncData(projectid);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean updateTokenScreen(TokenScreenConfigDto aLogDto) {
		Integer updatedby = null;
		Integer recordid = null;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		TokenScreenConfig aLog1 = new TokenScreenConfig();
		
		if (!aLogDto.getUpdatedby().trim().equals("undefined") && !aLogDto.getUpdatedby().trim().equals("")) {
			updatedby = Integer.parseInt(aLogDto.getUpdatedby()); 
		}
		
		if (!aLogDto.getId().trim().equals("undefined") && !aLogDto.getId().trim().equals("")) {
			recordid = Integer.parseInt(aLogDto.getId()); 
		}

		aLog1.setUpdatedby(updatedby);
		aLog1.setUpdated(timestamp);
		
		aLog1.setProjectsfid(aLogDto.getProjectsfid());
		aLog1.setToken_type(aLogDto.getToken_type());
		aLog1.setKey_cont1(aLogDto.getKey_cont1());
		aLog1.setKey_cont2(aLogDto.getKey_cont2());
		aLog1.setVideo_url(aLogDto.getVideo_url());
		aLog1.setImage_url(aLogDto.getImage_url());
		aLog1.setTicker_label(aLogDto.getTicker_label());
		aLog1.setTicker_text(aLogDto.getTicker_text());
		aLog1.setId(recordid);
		
		boolean isUpdated = tokenScreenConfigDao.updateTokenScreen(aLog1);
		return isUpdated;
	}
}