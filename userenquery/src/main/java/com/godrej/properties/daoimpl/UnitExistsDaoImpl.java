package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.UnitExistsDao;
import com.godrej.properties.model.UnitDtl;

@SuppressWarnings("unchecked")
@Repository("unitExistsDao")
public class UnitExistsDaoImpl extends AbstractDao<Integer, UnitDtl> implements UnitExistsDao {

	public UnitDtl getUnitData(String contactNo) {	 
		
		Criteria criteria = createEntityCriteria();		
		criteria.add(Restrictions.eq("user_contact", contactNo));
		//criteria.add(Restrictions.eq("isactive", "Y"));
		
		List<UnitDtl> req= (List<UnitDtl>) criteria.list();
		if(req.size()>0) {
			return req.get(0);
		}
		return null;		
	}
	
	
	public UnitDtl unitExist(String unitNo) {	 
		
		Criteria criteria = createEntityCriteria();		
		criteria.add(Restrictions.eq("unit_no", unitNo));
		/*criteria.add(Restrictions.isNull("admin_status"));
		criteria.add(Restrictions.isNull("sendforapproval"));
		criteria.add(Restrictions.isNull("senttocrm"));*/
		criteria.add(Restrictions.and(Restrictions.or(Restrictions.eq("admin_status", "Y"),Restrictions.eq("senttocrm", "Y"),Restrictions.eq("sendforapproval", "Y")) ));
		criteria.add(Restrictions.not(Restrictions.eq("admin_status", "R")));
		
		//criteria.add(Restrictions.or(Restrictions.eq("admin_status", "Y"),Restrictions.eq("senttocrm", "Y"),Restrictions.eq("sendforapproval", "Y")));
		 
			
		//criteria.add(Restrictions.eq("isactive", "Y"));
		
		List<UnitDtl> req= (List<UnitDtl>) criteria.list();
		if(req.size()>0) {
			return req.get(0);
		}
		return null;		
	}
	

	@Override
	public void updateUnitDtl(UnitDtl uDtl) {
		update (uDtl);
	}

}
