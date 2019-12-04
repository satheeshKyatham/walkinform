package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.RoleMstDao;
import com.godrej.properties.model.RoleMst;
import com.godrej.properties.service.RoleMstService;

@Service("roleMstService")
@Transactional
public class RoleMstServiceImpl implements RoleMstService {

	@Autowired
	private RoleMstDao dao;

	@Override
	public List<RoleMst> roleDtl(String userId) {
		// TODO Auto-generated method stub
		return dao.roleDtl(userId);
	}

}
