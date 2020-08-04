package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dto.ProjectLaunchDao;
import com.godrej.properties.model.ProjectLaunch;
@SuppressWarnings("unchecked")
@Repository("projectLaunchDao")
@Transactional
public class ProjectLaunchDaoImpl implements ProjectLaunchDao {// extends AbstractDao<Integer, ProjectLaunch>
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;

	public ProjectLaunchDaoImpl() {

	}

	     
 

	
	@Override
	public List<ProjectLaunch> getActiveProjectList() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<ProjectLaunch> list = session .createQuery( "  FROM ProjectLaunch where isActive ='A' order by name asc ") .list();//where  isactive='A'

		if (list.size() > 0) {

			return list;
		}
		return null;
	}
 

	@Override
	public String UpdateProjectStatus(String id, String status) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(" Update ProjectLaunch set isactive ='"+status+"' where id='"+id+"'");
		query.executeUpdate();
		return null;
	}





	@Override
	public List<ProjectLaunch> getProjectList(String regionid) {
		Session session = this.sessionFactory.getCurrentSession();
		StringBuffer buffer= new StringBuffer();
		if(regionid!=null && !"".equals(regionid)) {
			buffer.append(" FROM ProjectLaunch where isActive ='A' and region__c='"+regionid+"' order by name asc " );
		}else {
			buffer.append(" FROM ProjectLaunch  order by name asc " );
		}
		List<ProjectLaunch> list = session .createQuery(  buffer.toString()) .list();//where  isactive='A'

		if (list.size() > 0) {

			return list;
		}
		return null;
	}





	@Override
	public ProjectLaunch getProjectSaleMgrID(String projectid) {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			StringBuffer buffer= new StringBuffer();
			if(projectid!=null && !"".equals(projectid)) {
				buffer.append(" FROM ProjectLaunch where project_18_digit__c='"+projectid+"' order by name asc " );
			}
			List<ProjectLaunch> list = session .createQuery(  buffer.toString()) .list();//where  isactive='A'
	
			if (list.size() > 0) {
	
				return list.get(0);
			}
		}
		catch (Exception e) {
			log.error("Error getProjectSaleMgrID :{}",e);
		}
		return null;
	}





	@Override
	public ProjectLaunch getprojectDetailsForCCPaymentQuery(String projectSfid) {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<ProjectLaunch> list = session .createQuery( "  FROM ProjectLaunch where project_18_digit__c='"+projectSfid+"'and isActive ='A' order by name asc ") .list();//where  isactive='A'

		if (list.size() > 0) {

			return list.get(0);
		}
		return null;
	}
	
	
 
}
