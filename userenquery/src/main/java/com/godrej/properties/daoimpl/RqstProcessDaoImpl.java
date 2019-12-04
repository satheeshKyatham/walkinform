package com.godrej.properties.daoimpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.RqstProcessDao;
import com.godrej.properties.model.UnitDtl;

@SuppressWarnings("unchecked")
@Repository("rqstProcessDao")
public class RqstProcessDaoImpl extends AbstractDao<Integer, UnitDtl> implements RqstProcessDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void updateRqst(UnitDtl action) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE UnitDtl SET admin_status = '"+action.getAdmin_status()+"' WHERE timeid = '"+action.getTimeid()+"' ");
		query.executeUpdate();
	}
	
	@Override
	public void updateSendFApproval(UnitDtl action) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE UnitDtl SET sendforapproval = '"+action.getSendforapproval()+"' WHERE timeid = '"+action.getTimeid()+"' ");
		query.executeUpdate();
	}

}
