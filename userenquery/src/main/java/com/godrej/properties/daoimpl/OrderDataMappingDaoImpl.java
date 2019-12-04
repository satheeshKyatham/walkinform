package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.OrderDataMappingDao;
import com.godrej.properties.model.OrderDataMapping; 
@SuppressWarnings("unchecked")
@Repository("orderDataMappingDao")
public class OrderDataMappingDaoImpl extends AbstractDao<Integer, OrderDataMapping> implements OrderDataMappingDao {


	@Autowired
	private SessionFactory sessionFactory;
	public OrderDataMappingDaoImpl() {
		
	}
	 

	@Override
	public List<OrderDataMapping> getdataMapping() {
		Session session = this.sessionFactory.getCurrentSession();
		List<OrderDataMapping> list  =session.createQuery("  FROM  OrderDataMapping " ).list(); // where PROPSTRENGTH__HOUSE_UNIT_NO__C='"+unit+"'
		if(list.size()>0)
		{
			return list;
		}
		return list;
	}
		 
	@Override
	public List<OrderDataMapping> getdataMapping(String str_date) {
		Session session = this.sessionFactory.getCurrentSession();
		 String wherecondition="";
		 if(str_date!=null &&! str_date.equals("") ) {
				wherecondition=" Where lastmodifieddate >'"+str_date+"'";
			}
		List<OrderDataMapping> list  =session.createQuery("  FROM  OrderDataMapping "+wherecondition ).list(); // where PROPSTRENGTH__HOUSE_UNIT_NO__C='"+unit+"'
		if(list.size()>0)
		{
			return list;
		}
		return list;
	}
	 
	 
	
}
