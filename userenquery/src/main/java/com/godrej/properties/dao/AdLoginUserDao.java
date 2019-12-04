package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.ADLoginPass;

public interface AdLoginUserDao {

	List<ADLoginPass> getUserList(String emailid);

}
