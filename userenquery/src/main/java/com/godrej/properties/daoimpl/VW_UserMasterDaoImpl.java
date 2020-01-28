package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dto.VW_UserMasterDao;
import com.godrej.properties.model.DailyUserMater;
import com.godrej.properties.model.Vw_UserMaster;
import com.godrej.properties.model.Vw_UserProjectMapping; 

@Repository("vw_UserMasterDao")
public class VW_UserMasterDaoImpl extends AbstractDao<Integer, Vw_UserMaster> implements VW_UserMasterDao {


	@Autowired
	private SessionFactory sessionFactory;
	public VW_UserMasterDaoImpl() {
		
	}
	  
	@Override
	public List<Vw_UserMaster> getUserProjectList(String userid) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Vw_UserMaster> list =null;
		 list =session.createQuery(" from Vw_UserMaster where  user_id = "+userid).list();
	 
		if(list.size()>0)
			return list;
		return null;
	}

	@Override
	public Vw_UserMaster getUserDetails(int user_id) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Vw_UserMaster> list =null;
		 list =session.createQuery(" from Vw_UserMaster where isActive='A' and  user_id = "+user_id).list();
	 
		if(list.size()>0)
			return list.get(0);
		return null;
	}
	@Override
	public Vw_UserMaster getUserDetails(int user_id,String projectId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Vw_UserMaster> list =null;
		 list =session.createQuery(" from Vw_UserMaster where isActive='A' and projectId='"+projectId+"' and  user_id = "+user_id).list();
	 
		if(list.size()>0)
			return list.get(0);
		return null;
	}
	@Override
	public List<Vw_UserMaster> getUserListProjectWise(String projectID) {

		System.out.println("Project ID:-"+projectID);
		Session session = this.sessionFactory.getCurrentSession();
		List<Vw_UserMaster> list =null;
		
		 list =session.createQuery(" from Vw_UserMaster where projectid = '"+projectID+"'").list();
	 
		if(list.size()>0)
			return list;
		return null;
	
	}	//Vw_UserProjectMapping

	@Override
	public List<Vw_UserProjectMapping> getUserProjectMapping(String projectID) {


		System.out.println("Project ID:-"+projectID);
		Session session = this.sessionFactory.getCurrentSession();
		List<Vw_UserProjectMapping> list =null;
		
		 list =session.createQuery(" from Vw_UserProjectMapping where projectid = '"+projectID+"' and roleid in(1,3,6,7,10,11,12,17, 47, 16) order by user_name asc ").list();
	 
		if(list.size()>0)
			return list;
		return null;
	
	
	}

	@Override
	public List<Vw_UserProjectMapping> getProjectListUserWise(String userid) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Vw_UserProjectMapping> list =null;
		
		 list =session.createQuery(" from Vw_UserProjectMapping where user_id = '"+userid+"' order by projectName").list();
	 
		if(list.size()>0)
			return list;
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vw_UserProjectMapping> getOfferApprovalUser(String projectid) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Vw_UserProjectMapping> list =null;
		 list =session.createQuery(" from Vw_UserProjectMapping where roleid=11 and projectid = '"+projectid+"'").list();
		if(list.size()>0)
			return list;
		return null;
	}
	
	
	
}
