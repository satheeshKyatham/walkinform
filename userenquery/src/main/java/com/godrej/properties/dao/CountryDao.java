package com.godrej.properties.dao;

import com.godrej.properties.model.Country;

public interface CountryDao {
	
	public Country getCountryData(String countryCode);

}
