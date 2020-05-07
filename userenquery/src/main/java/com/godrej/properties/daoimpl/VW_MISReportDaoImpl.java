package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.VW_MISReportDao;
import com.godrej.properties.model.Vw_MISReport;


@Repository("vW_MISReportDao")
public class VW_MISReportDaoImpl extends AbstractDao<Integer, Vw_MISReport> implements VW_MISReportDao{

	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Vw_MISReport> getUserProjectList(String projectid,String userid, String fromDate, String toDate) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Vw_MISReport> list =null;
		
		if(((fromDate!=null && fromDate.length()>0) && (toDate!=null && toDate.length()>0)) && (userid !=null && userid.length()>0) )
			list =session.createQuery(" from Vw_MISReport where projectid= "+projectid+" and user_id="+userid+" and Date(created) between '"+fromDate+"' and '"+toDate+"' order by created desc ").list();
		else if((fromDate!=null && fromDate.length()>0) && (toDate!=null && toDate.length()>0))
			list =session.createQuery(" from Vw_MISReport where projectid in ("+projectid+") and Date(created) between '"+fromDate+"' and '"+toDate+"' order by projectname,created desc ").list();
		else if(projectid!=null && projectid.length()>0)
			list =session.createQuery(" from Vw_MISReport where projectid in ("+projectid+") order by projectname,created desc ").list();
		else
			list =session.createQuery(" from Vw_MISReport ").list();
		if(list.size()>0)
			return list;
		return null;
	}
}
