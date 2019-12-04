package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.ProjectRegionDao;
import com.godrej.properties.model.ProjectRegion;

@SuppressWarnings("unchecked")
@Repository("projectRegionDao")
public class ProjectRegionDaoImpl extends AbstractDao<Integer, ProjectRegion> implements ProjectRegionDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ProjectRegion> getRegion() {
		Session session = this.sessionFactory.getCurrentSession();
		StringBuffer str= new StringBuffer();
		str.append("SELECT  Distinct region__c FROM ProjectRegion where propstrength__active__c = 't'");
		List<Object>  list  =(List<Object>)session.createQuery(str.toString()  ).list();
		if(list.size()>0)
		{
			List<ProjectRegion> projectRegionObj = new ArrayList<ProjectRegion>();
			 for(int i=0;i<list.size();i++) {
				 	
				 if (list.get(i) != null ) {
					ProjectRegion projectRegion = new ProjectRegion();
					projectRegion.setRegion__c(list.get(i).toString());
					projectRegionObj.add(projectRegion);
				 } else {
					 System.out.println("Region Name Empty or Null");
				 }
				 
				 	
			 }
			  return projectRegionObj;
		}
		return null;
	}

	/*
	 * @Override public List<ProjectRegion> getProjectData(String region) { Session
	 * session = this.sessionFactory.getCurrentSession(); StringBuffer str= new
	 * StringBuffer(); str.
	 * append("SELECT  sfid, propstrength__project_code__c FROM ProjectRegion where propstrength__active__c = 't' and region__c ='"
	 * +region+"' "); List<Object> list
	 * =(List<Object>)session.createQuery(str.toString() ).list(); if(list.size()>0)
	 * { List<ProjectRegion> projectDataObj = new ArrayList<ProjectRegion>();
	 * for(int i=0;i<list.size();i++) {
	 * 
	 * ProjectRegion projectRegion = new ProjectRegion();
	 * projectRegion.setMarketing_project_name__c(list.get(i).toString());
	 * projectRegion.setSfid(list.get(i).toString());
	 * projectRegion.setPropstrength__project_code__c(list.get(i).toString());
	 * 
	 * projectDataObj.add(projectRegion); } return projectDataObj; } return null; }
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public List<ProjectRegion> getProjectData(String region) {	 
		
		Session session = this.sessionFactory.getCurrentSession();	
		List<Object>  list  =(List<Object>)session.createQuery("SELECT  sfid, propstrength__project_code__c, name FROM ProjectRegion where propstrength__active__c = 't' and region__c ='"+region+"' ").list();
		
		if(list.size()>0) {
			List<ProjectRegion> project = new ArrayList<ProjectRegion>();
			
			Iterator itr = list.iterator();
			
			while(itr.hasNext()){
				ProjectRegion projectList = new ProjectRegion();
			     Object[] obj = (Object[]) itr.next();
			      
			     String sfid = String.valueOf(obj[0]); 
			     String projectCode = String.valueOf(obj[1]);
			     String projectname = String.valueOf(obj[2]);
			     
			     projectList.setSfid(sfid);
			     projectList.setPropstrength__project_code__c(projectCode);
			     projectList.setName(projectname);
			     
			     project.add(projectList);
			 }
			
			return project;
		}
		return new ArrayList<>();
	}
	
	
	
}
