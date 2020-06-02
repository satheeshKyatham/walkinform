package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.ValidateOTPAdminDao;
import com.godrej.properties.model.UserMaster;

@Repository("validateOTPAdminDao")
public class ValidateOTPAdminDaoImpl extends AbstractDao<Integer, UserMaster> implements ValidateOTPAdminDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean validateAdmin(String loggedinuserid) {
		Session session = this.sessionFactory.getCurrentSession();	
		List<UserMaster> list =session.createQuery(" from UserMaster where user_id = "+loggedinuserid+" AND isactive='A' AND isotpadmin = 'Y' ").list();
		if(list.size()>0) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean validateRequester(String otprequesterid) {
		Session session = this.sessionFactory.getCurrentSession();	
		List<UserMaster> list =session.createQuery(" from UserMaster where user_id = "+otprequesterid+" AND isactive='A' ").list();
		if(list.size()>0) {
			return true;
		}
		return false;
	}
	
}