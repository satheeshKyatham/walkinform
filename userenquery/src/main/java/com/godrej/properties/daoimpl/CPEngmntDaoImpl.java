package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CPEngmntDao;
import com.godrej.properties.model.CPEngmnt;
import com.godrej.properties.model.CPEngmntReport;

@Repository("cPEngmntDao")
public class CPEngmntDaoImpl extends AbstractDao<Integer, CPEngmnt> implements CPEngmntDao{
	@Override
	public CPEngmnt insertAuditLog(CPEngmnt aLog) {
		persist(aLog);
		return aLog;
	}
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<CPEngmntReport> getReportDtl(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<CPEngmntReport> authors=null;
		 
		
		/* -------------- Query For Count --------------- */
	  	Query countQuery = session.createNativeQuery(" SELECT  COUNT(*) "
				+ " FROM salesforce.nv_cp_engagement a  "
				+ " LEFT JOIN salesforce.nv_projects_c b ON a.project_sfid = b.sfid "
				+ " LEFT JOIN salesforce.mst_user c ON a.createdby = c.user_id  "
				+ " where "+whereCondition+"      ");
	  	
	  		/* -------------- END Query For Count --------------- */
	  	
	  			long count = ((Number) countQuery.getSingleResult()).intValue();
	  	
	  			System.out.println(count);
			   
	  			String strRowCount = Long.toString(count);
	  		  	
	  		  	if (count <= 5000) {
	  		  		
	  		  		/* Final Query */
		  		  	Query q = session.createNativeQuery(" SELECT  b.name as project_name, c.user_name, "
		  		  	+ " a.cpsfid, a.cpid, a.project_sfid, a.createdby, a.isactive, a.id, a.visit_date, a.cp_name, a.meeting_place, a.topic, a.discussed_point "
					+ " FROM salesforce.nv_cp_engagement a "
					+ " LEFT JOIN salesforce.nv_projects_c b ON a.project_sfid = b.sfid "
					+ " LEFT JOIN salesforce.mst_user c ON a.createdby = c.user_id "
					+ " where "+whereCondition+"  order by id desc  ", CPEngmntReport.class);
			 
					authors = q.getResultList();
	  		  	} else {
		  		  	List<CPEngmntReport> lists = new ArrayList<CPEngmntReport>();
			  		lists = getlist(lists,"MAX_LIMIT",strRowCount);
			  		return lists;
	  		  	}
	  		  
	  		  if (authors.size() > 0) {
	  			  return authors;  
	  		  }
	  		  	
	  		  return null;
	}

	private List<CPEngmntReport> getlist(List<CPEngmntReport> list,String msg, String count){
		List<CPEngmntReport> lists= list;
		if(lists.size()==0) {
			CPEngmntReport getMisReport=	new CPEngmntReport();
			getMisReport.setQry_count(count);
			getMisReport.setQry_msg(msg);
			lists.add(getMisReport);
		}
		return lists;
	}
}