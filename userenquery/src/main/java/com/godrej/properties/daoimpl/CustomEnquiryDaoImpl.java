package com.godrej.properties.daoimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AAbstractDao;
import com.godrej.properties.dao.CustomEnquiryDao;
import com.godrej.properties.model.CustomEnquiry;

/**
 * 
 * @author Varsha Patil
 *
 */
@Repository
public class CustomEnquiryDaoImpl extends AAbstractDao<CustomEnquiry> implements CustomEnquiryDao{

	@Override
	public CustomEnquiry save(CustomEnquiry enq) {
		persist(enq);
		return enq;
	}

	@Override
	public CustomEnquiry findById(Integer id) {
		 StringBuilder jpql=new StringBuilder();
		 jpql.append(" SELECT ce FROM CustomEnquiry ce ");
		 jpql.append(" LEFT JOIN FETCH ce.enquiryReport cer ");
		 jpql.append(" LEFT JOIN FETCH ce.contact c ");
		 jpql.append(" where ce.customEnquiryId=:customEnquiryId ");
		 Map<String, Object> params=new HashMap<>();
		 params.put("customEnquiryId",id);
	     return getSingleEntity(jpql.toString(), params);
	}
}
