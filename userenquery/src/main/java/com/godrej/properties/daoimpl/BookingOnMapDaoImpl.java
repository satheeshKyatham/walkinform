package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.BookingOnMapDao;
import com.godrej.properties.model.BookingOnMap;
import com.godrej.properties.model.EnqOnMap;


@Repository("bookingOnMapDao")
public class BookingOnMapDaoImpl extends AbstractDao<Integer, BookingOnMap> implements BookingOnMapDao{
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	
	@Override
	public List<BookingOnMap> getEnqDtl(String projectId, String finalVerticales) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		
		String condition = "";
		
		if (finalVerticales != null) {
			condition = " and verticle__c in ("+finalVerticales+") ";
		} else {
			condition = "";
		}
		
		List<BookingOnMap> list =session.createQuery(" from BookingOnMap where    sfid='"+projectId+"'   and residencelat <> '' and residencelng <> '' AND propstrength__status__c = 'Closed Won' "+condition+" ").list();
		
		if(list.size()>0)
			return list;
		return null;
	}
}