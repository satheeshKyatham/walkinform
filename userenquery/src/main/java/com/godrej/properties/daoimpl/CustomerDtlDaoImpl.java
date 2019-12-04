package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CustomerDtlDao;
import com.godrej.properties.model.CustomerDtl;

@SuppressWarnings("unchecked")
@Repository("customerDtlDao")
public class CustomerDtlDaoImpl extends AbstractDao<Integer, CustomerDtl> implements CustomerDtlDao {

	
	public CustomerDtl getCustomerDeta(String contactNo) {	 
		
		Criteria criteria = createEntityCriteria();		
		criteria.add(Restrictions.eq("mobilephone", contactNo));
		criteria.add(Restrictions.eq("isactive", "Y"));
		
		List<CustomerDtl> req= (List<CustomerDtl>) criteria.list();
		if(req.size()>0) {
			return req.get(0);
		}
		return null;		
	}
}
