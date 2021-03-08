package com.godrej.properties.dao;

import com.godrej.properties.model.TokenScreenConfig;

public interface TokenScreenConfigDao {
	public TokenScreenConfig insertConfig(TokenScreenConfig aLog);
	TokenScreenConfig getTncData (String projectid);
	Boolean updateTokenScreen(TokenScreenConfig aLog);
}