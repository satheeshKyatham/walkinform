package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.UserProjectMappingDao;
import com.godrej.properties.model.UserProjectMapping;
@SuppressWarnings("unchecked")
@Repository("userProjectMappingDao")
public class UserProjectMappingDaoImpl  extends AbstractDao<Integer, UserProjectMapping> implements UserProjectMappingDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public String projectassignUpdate(UserProjectMapping mapping) {
		List<UserProjectMapping> list =null;
		Session session = this.sessionFactory.getCurrentSession();
		 list =session.createQuery(" from UserProjectMapping where project_id = '"+mapping.getProject_id()+"' and user_id='"+mapping.getUser_id()+"' ").list();
		
		 if(list.size()>0)
		 {
				return "Enter User Mapping already existed";
		 }
		 else
		 {
			 persist(mapping);
			 return "Enter User successfully mapped";
		 }
	}

	@Override
	public List<UserProjectMapping> getProjectMappingList(String projectID) {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserProjectMapping> list =null;
		 list =session.createQuery(" from UserProjectMapping where project_id='"+projectID+"'").list();
	 
		if(list.size()>0)
			return list;
		return null;
	}

	@Override
	public String projectMappingUpdateStatus(UserProjectMapping mapping) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				" update UserProjectMapping set isactive='" + mapping.getIsactive() + "'  where  gpl_user_project_mapping_id=" + mapping.getGpl_user_project_mapping_id());
		query.executeUpdate();
		return null;
	}

	@Override
	public List<UserProjectMapping> getUserProjectByQuery(int userId) {
		List<UserProjectMapping> data = null;
		Session session = this.sessionFactory.getCurrentSession();
		StringBuilder sql = new StringBuilder();
		sql.append("Select a from UserProjectMapping a ")
		.append(" where a.user_id=:userId")
		.append(" order by project_name ASC");
		Query query = session.createQuery(sql.toString());
		query.setParameter("userId", userId);
		
		List<UserProjectMapping> list = query.getResultList();
		if (list.size() > 0) {
			return list;
		} else {
			data = new ArrayList<UserProjectMapping>();
			return data;
		}
		
	}

}
