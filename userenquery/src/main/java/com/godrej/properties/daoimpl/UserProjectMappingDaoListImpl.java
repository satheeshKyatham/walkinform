package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.UserProjectMappingDao;
import com.godrej.properties.dao.UserProjectMappingListDao;
import com.godrej.properties.model.DailyUserMater;
import com.godrej.properties.model.UserProjectMapping;
import com.godrej.properties.model.UserProjectMappingList;
import com.godrej.properties.model.Vw_UserMaster;

@Repository("userProjectMappingListDao")
public class UserProjectMappingDaoListImpl  extends AbstractDao<Integer, UserProjectMappingList> implements UserProjectMappingListDao{

	@Autowired
	private SessionFactory sessionFactory;
	 
	@Override
	public List<UserProjectMappingList> getProjectMappingList(String projectID) {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserProjectMappingList> list =null;
		 list =session.createQuery(" from UserProjectMappingList where project_id='"+projectID+"'").list();
	 
		if(list.size()>0)
			return list;
		return null;
	} 
}
