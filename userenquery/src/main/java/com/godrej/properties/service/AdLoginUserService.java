package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.ADLoginPass;

public interface AdLoginUserService {

	List<ADLoginPass> getUserList(String emailid);

}
