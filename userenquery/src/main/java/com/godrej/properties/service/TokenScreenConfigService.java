package com.godrej.properties.service;

import com.godrej.properties.dto.TokenScreenConfigDto;
import com.godrej.properties.model.TokenScreenConfig;

public interface TokenScreenConfigService {
	public TokenScreenConfigDto insertConfig (TokenScreenConfigDto aLog);
	TokenScreenConfig getTncData (String projectid);
	Boolean updateTokenScreen(TokenScreenConfigDto aLog);
}
