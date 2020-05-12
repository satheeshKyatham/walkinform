package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.EOIPreferenceDtlDao;
import com.godrej.properties.model.EOIPreferenceDtl;

@Repository("eOIPreferenceDtlDao")
public class EOIPreferenceDtlDaoImpl extends AbstractDao<Integer, EOIPreferenceDtl> implements EOIPreferenceDtlDao {
	private Logger Log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertEOI(List<EOIPreferenceDtl> eoiDtl) {
		batchPersist(eoiDtl);
	}	
	
	@Override
	public List<EOIPreferenceDtl> getEOIPreferenceRecord(String enqSfid) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<EOIPreferenceDtl> authors=null;
		
		Query q = session.createNativeQuery("SELECT * FROM salesforce.gpl_cs_eoi_header_dtl "
				+ " where enq_sfid = '"+enqSfid+"' order by gpl_cs_eoi_header_dtl ", EOIPreferenceDtl.class);
		//order by b.id
		authors = q.getResultList();
		
		System.out.println("Final Size::"+authors.size());
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
	
	@Override
	public Boolean updateEOIPreference(List<EOIPreferenceDtl> eoiReq) {
		try {
			for (int i = 0; i <eoiReq.size(); i++) {
				Session session = this.sessionFactory.getCurrentSession();
				Query query = session.createQuery("UPDATE EOIPreferenceDtl  "
						+ " SET "
								+ "	  tower_id = '"+eoiReq.get(i).getTower_id()+"' "
								+ " , tower_name =  '"+eoiReq.get(i).getTower_name()+"' "
								+ " , typology_id = '"+eoiReq.get(i).getTypology_id() +"' "
								+ " , typology_name = '"+eoiReq.get(i).getTypology_name() +"' "
								+ " , unit_id = '"+eoiReq.get(i).getUnit_id() +"' "
								+ " , unit_name = '"+eoiReq.get(i).getUnit_name() +"' "
								+ " , floor_band = '"+eoiReq.get(i).getFloor_band() +"' "
								+ " , eoi_carpark_mst_id = '"+eoiReq.get(i).getEoi_carpark_mst_id() +"' "
								+ " , eoi_carpark_name = '"+eoiReq.get(i).getEoi_carpark_name() +"' "
								+ " , description = '"+eoiReq.get(i).getDescription() +"' "
								+ " , updatedby = '"+eoiReq.get(i).getUpdatedby() +"' "
								+ " , updated = now() "

						+ " WHERE id = '"+eoiReq.get(i).getRowid()+"' "
						+ " AND enq_sfid = '"+eoiReq.get(i).getEnq_sfid()+"' "
						+ " AND project_sfid = '"+eoiReq.get(i).getProject_sfid()+"' "
						+ " AND isactive = '"+eoiReq.get(i).getIsactive()+"' ");
				
				query.executeUpdate();
			}
			return true;
		} catch (Exception e) {
			Log.info("Update EOI Preference Error:- ",e);
			return false;
		}
	}
	
	
	
	
	
	@Override
	public Boolean inactiveEOIPreference(List<EOIPreferenceDtl> eoiReq) {
		try {
			for (int i = 0; i <eoiReq.size(); i++) {
				Session session = this.sessionFactory.getCurrentSession();
				Query q = session.createQuery("DELETE from EOIPreferenceDtl  "
						+ " WHERE id = '"+eoiReq.get(i).getRowid()+"' AND "
						+ " enq_sfid = '"+eoiReq.get(i).getEnq_sfid()+"' AND "
						+ " project_sfid = '"+eoiReq.get(i).getProject_sfid()+"' AND "
						+ " isactive = '"+eoiReq.get(i).getIsactive()+"' "
						);
				//q.setParameter ("id", eoiReq.get(i).getRowid());
				//q.setParameter ("enq_sfid", eoiReq.get(i).getEnq_sfid());
				//q.setParameter ("project_sfid", eoiReq.get(i).getProject_sfid());
				//q.setParameter ("isactive", "Y");
				q.executeUpdate();
			}
			return true;
		} catch (Exception e) {
			Log.info("Delete EOI Preference Error:- ",e);
			return false;
		}
	}
}