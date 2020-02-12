package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.SchemeChargeDao;
import com.godrej.properties.model.Scheme;

@Repository("schemeChargesDao")
public class SchemeChargeDaoImpl extends AbstractDao<Integer, Scheme> implements SchemeChargeDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertSchemeCharge(Scheme schemeCharges) {
		persist(schemeCharges);
		
	}

	/*@Override
	public List<Scheme> getSchemeChargs(String projectId, String cp_flag_yn) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		
		List<Scheme> list;
		
		if("".equals(cp_flag_yn)) {
			list  =session.createQuery("  from Scheme where project_id='"+projectId+"' and isactive='A' ").list();
		} else {
			list  =session.createQuery("  from Scheme where project_id='"+projectId+"' and isactive='A' and cp_flag_yn = '"+cp_flag_yn+"' ").list();
		}
		
		if(list.size()>0)
		{
			return list;
		}
		return list;
	}*/

	
	
	
	
	//New
	@Override
	public List<Scheme> getSchemeChargs(int schemeSource, int schemeSite, int schemePromotional, String projectId, String cp_flag_yn) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<Scheme> authors=null;
		
		
		Query q = session.createNativeQuery("SELECT a.id, "
				+ " a.region_name, "
				+ " a.project_name, "
				+ " a.scheme, "
				+ " a.rate, "
				+ " a.isactive, "
				+ " a.project_id, "
				+ " a.percentage, "
				+ " a.absolute_amount, "
				+ " a.zero_govt_charges, "
				+ " b.combination_name, "
				+ " b.scheme_source_id, "
				+ " b.scheme_site_id, "
				+ " b.scheme_promotional_id, "
				
				+ " a.source_name, "
				+ " a.site_name, "
				+ " a.promotional_name "
				
				+ " FROM salesforce.gpl_cs_scheme a "
				+ " INNER JOIN salesforce.gpl_cs_scheme_mapping b ON a.id = b.scheme_id "
				+ " where a.isactive = 'A' and scheme_site_id = '"+schemeSite+"' and scheme_promotional_id = '"+schemePromotional+"' and scheme_source_id = '"+schemeSource+"'  and a.project_id = '"+projectId+"' " , Scheme.class); 
				
				  
				
				
				//+ " FROM salesforce.gpl_cs_scheme  where project_id='"+projectId+"' and isactive='A' ", Scheme.class);

		authors = q.getResultList();
		
		//System.out.println("Final Size::"+authors.size());
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
	// END New
	
}
