package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.SchemeMappingDao;
import com.godrej.properties.model.PaymentDtl;
import com.godrej.properties.model.Scheme;
import com.godrej.properties.model.SchemeMapping;

@Repository("schemeMappingDao")
public class SchemeMappingDaoImpl extends AbstractDao<Integer, SchemeMapping> implements SchemeMappingDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	/*public SchemeMapping getMappingDtl(int schemeID) {	 
		Criteria criteria = createEntityCriteria();		
		criteria.add(Restrictions.eq("unit_no", unitNo));

		criteria.add(Restrictions.and(Restrictions.or(Restrictions.eq("admin_status", "Y"),Restrictions.eq("senttocrm", "Y"),Restrictions.eq("sendforapproval", "Y")) ));
		criteria.add(Restrictions.not(Restrictions.eq("admin_status", "R")));
		
		List<SchemeMapping> req= (List<SchemeMapping>) criteria.list();
		if(req.size()>0) {
			return req.get(0);
		}
		return null;		
	}*/
	
	@Override
	public void insertBulkMapping(List<SchemeMapping> mapping) {
		batchPersist(mapping);
	}
	
	
	
	@Override
	public SchemeMapping getMappingDtl(int schemeID) {
		Session session = this.sessionFactory.getCurrentSession();	
		List<SchemeMapping> authors=null;
		Query q = session.createNativeQuery("SELECT * "
				+ " FROM salesforce.gpl_cs_scheme_mapping "
				+ " where isactive = 'A' and scheme_id = "+schemeID+" " , SchemeMapping.class); 
		authors = q.getResultList();
		if (authors.size() > 0)
			return authors.get(0);
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void insertSchemeMapping(SchemeMapping schemeMapping) {
		persist(schemeMapping);
	}
}