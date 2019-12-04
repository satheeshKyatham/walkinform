package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.ProjectDtlDao;
import com.godrej.properties.dao.TnCDao;
import com.godrej.properties.dao.UnitExistsDao;
import com.godrej.properties.model.ProjectDtl;
import com.godrej.properties.model.TnC;
import com.godrej.properties.model.UnitDtl;

@Repository("projectDtlDao")
public class ProjectDtlDaoImpl extends AbstractDao<Integer, ProjectDtl> implements ProjectDtlDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public  List<ProjectDtl> getProjectData(String projectId) {	 
		
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<ProjectDtl> list  =session.createQuery(" FROM  ProjectDtl where project_18_digit__c = '"+projectId+"'    ").list();
		
		if(list.size()>0)
		{
			return list;
		}
		
		return null;		
	}
	
}
