package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.AdLoginUserDao;
import com.godrej.properties.dao.AssignUserDao;
import com.godrej.properties.dto.VW_UserMasterDao;
import com.godrej.properties.model.ADLoginPass;
import com.godrej.properties.model.AssignedUser;
import com.godrej.properties.model.Vw_UserMaster; 

@Repository("sdLoginUserDao")
public class AdLoginUserDaoImpl extends AbstractDao<Integer, ADLoginPass> implements AdLoginUserDao {


	@Autowired
	private SessionFactory sessionFactory;
	public AdLoginUserDaoImpl() {
		
	}
	  
 


	@Override
	public List<ADLoginPass> getUserList(String emailid) {
		Session session = this.sessionFactory.getCurrentSession();
		List<ADLoginPass> list =null;
		 list =session.createQuery(" from ADLoginPass where isactive='A'  and emailid='"+emailid+"'").list(); // and projectname='"+projectId+"'
	 
		if(list.size()>0)
			return list;
		
		return null;
	}
	
}
