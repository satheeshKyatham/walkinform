package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.VW_OfferWithBalanceDao;
import com.godrej.properties.model.OfferReport;
import com.godrej.properties.model.Vw_OfferWithBalance;

@Repository("vw_OfferWithBalanceDao")
public class VW_OfferWithBalanceDaoImpl implements VW_OfferWithBalanceDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Vw_OfferWithBalance> getOfferListList(String whereCondition) {
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Vw_OfferWithBalance> list =null;
		
		String SQL_QUERY = "";
		SQL_QUERY = "SELECT COUNT(*) FROM Vw_OfferWithBalance where isactive='A' "+whereCondition+"";
	  	Query query = session.createQuery(SQL_QUERY);
	
	  	long row = 0L;
	  	for(Iterator it=query.iterate();it.hasNext();){
			row = (Long) it.next();
	  	}
	  	
	  	String strRowCount = Long.toString(row);
	  	
	  	if (row <= 5000) {
	  		list =session.createQuery(" from Vw_OfferWithBalance where isactive='A' "+whereCondition+" order by projectname,createddate desc ").list();
	  	}else {
	  		List<Vw_OfferWithBalance> lists = new ArrayList<Vw_OfferWithBalance>();
	  		lists = getlist(lists,"MAX_LIMIT",strRowCount);
	  		return lists;
	  	}
	  	
	  	if(list.size()>0) {
			return list;
	  	}
	  	
		return null;
		//------------------------------
		
		/*Session session = this.sessionFactory.getCurrentSession();	
		
		list =session.createQuery(" from Vw_OfferWithBalance where isactive='A' "+whereCondition+" ").list();
		
		if(list.size()>0)
		{
			return list;
		}
		return null;
		*/
	}
	
	private List<Vw_OfferWithBalance> getlist(List<Vw_OfferWithBalance> list,String msg, String count){
		List<Vw_OfferWithBalance> lists= list;
		if(lists.size()==0) {
			Vw_OfferWithBalance getMisReport=	new Vw_OfferWithBalance();;
			getMisReport.setQry_count(count);
			getMisReport.setQry_msg(msg);
			lists.add(getMisReport);
		}
		return lists;
	}
	
	
	
	@Override
	public List<OfferReport> getOfferDtl(String whereCondition) {
		
		Session session = this.sessionFactory.getCurrentSession();
		List<OfferReport> authors=null;
		
		Query countQuery  = session.createNativeQuery("SELECT COUNT(*) FROM salesforce.propstrength__offer__c a where "+whereCondition+"");
	
	  	long count = ((Number) countQuery.getSingleResult()).intValue();
	  	
		System.out.println(count);
   
		String strRowCount = Long.toString(count);
	  	
	  	if (count <= 5000) {
	  		Query q = session.createNativeQuery(" SELECT a.id, a.isdeleted, a.createddate, "
	  				+ " a.name AS offername, "
	  				+ " a.propstrength__status__c, "
	  				+ " a.propstrength__project__c, "
	  				
	  				+ " b.name AS enquiryname, "
	  				+ " b.verticle__c, "
	  				+ " b.closing_manager_name__c, "
	  				+ " b.sourcing_manager_name__c, "
	  				+ " c.name AS contactname, "
	  				+ " e.name AS projectname, "
	  				+ " f.propstrength__property_name__c "
	  				
	  				
	  				+ " FROM salesforce.propstrength__offer__c a  "
	  				+ " LEFT JOIN salesforce.propstrength__request__c b 	ON a.propstrength__request__c = b.sfid "
	  				+ " LEFT JOIN salesforce.contact c				ON a.propstrength__primary_contact__c = c.sfid "
	  				+ " LEFT JOIN salesforce.propstrength__projects__c e 	ON a.propstrength__project__c = e.sfid "
	  				+ " LEFT JOIN salesforce.propstrength__property__c f 	ON a.propstrength__property__c = f.sfid "
	  				+ " where "+whereCondition+" order by e.name,a.createddate desc  ", OfferReport.class);
	  		
	  		authors = q.getResultList();
	  		
	  	}else {
	  		List<OfferReport> lists = new ArrayList<OfferReport>();
	  		lists = getOfferlist(lists,"MAX_LIMIT",strRowCount);
	  		return lists;
	  	}
	  	
	  	if (authors.size() > 0) {
			  return authors;  
		  }
		  	
		  return null;
	}
	
	private List<OfferReport> getOfferlist(List<OfferReport> list,String msg, String count){
		List<OfferReport> lists= list;
		if(lists.size()==0) {
			OfferReport getMisReport=	new OfferReport();;
			getMisReport.setQry_count(count);
			getMisReport.setQry_msg(msg);
			lists.add(getMisReport);
		}
		return lists;
	}
}