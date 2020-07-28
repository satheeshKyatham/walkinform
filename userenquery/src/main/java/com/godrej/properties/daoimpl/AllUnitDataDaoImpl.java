package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AllUnitDataDao;
import com.godrej.properties.model.AllUnitData;

@Repository("allUnitDataDao")
public class AllUnitDataDaoImpl implements AllUnitDataDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<AllUnitData> getInventoryReportDtl(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<AllUnitData> authors=null;
		
		Query q = session.createNativeQuery(" SELECT  row_number() OVER () AS row_no, "
				
				+ " a.saleable_area__c * a.propstrength__rate_per_unit_area__c +COALESCE(x.fixed_charges,0)+ COALESCE((y.propstrength__rate_per_unit_area__c * a.saleable_area__c),0)  as cop , "
				
				+ " a.project_name__c, "
				+ " a.propstrength__allotted__c, "
				
				+ " a.property_facing__c, "
				
				+ " a.propstrength__project_name__c, "
				+ " a.tower_name__c, "
				+ " a.propstrength__house_unit_no__c, "
				+ " a.wing_block__c, "
				+ " a.inventory_category__c, "
				+ " a.propstrength__property_name__c, "
				
				 
				+ " CASE WHEN a.propstrength__rate_per_unit_area__c IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.propstrength__rate_per_unit_area__c as numeric (20,2))  END AS propstrength__rate_per_unit_area__c, "
				
				+ " a.propstrength__property_alloted_through_offer__c, "
				+ " a.propstrength__active__c, "
				+ " a.propstrength__property_on_hold_for_reallocation__c, "
				+ " b.hold_reason, " 
				+ " c.name as enq_name,  "
				
				+ " b.hold_status,  "
				 
				
				+ " CASE WHEN a.open_balc_sq_ft__c IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.open_balc_sq_ft__c as numeric (20,2))  END AS rera_net_carpet_sqft, "
				+ " CASE WHEN a.appurtenant_area_sq_ft__c IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.appurtenant_area_sq_ft__c as numeric (20,2))  END AS rera_exclusive_sqft, "
				+ " CASE WHEN a.saleable_area__c IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.saleable_area__c as numeric (20,2))  END AS saleable_area__c, "				
				+ " CASE WHEN a.propstrength__super_area__c IS NULL THEN cast(0 as numeric (20,2))  ELSE cast(a.propstrength__super_area__c as numeric (20,2))  END AS super_area "				
				 
				+ " FROM salesforce.propstrength__property__c a "
				
				//---------------------------------------------
				+" LEFT JOIN (  "
					+ " SELECT b.propstrength__property__c,SUM(COALESCE(b.propstrength__fixed_charge__c, 0)) fixed_charges "
					+ " FROM  salesforce.propstrength__property_charges__c  b "
					+ " LEFT JOIN salesforce.propstrength__other_charges__c c ON  b.propstrength__other_charges__c  =c.sfid "
					+ " where c.propstrength__part_of_cop__c = true and propstrength__type__c='Fixed' "
					+ " group by propstrength__property__c "
				+ " ) x on x.propstrength__property__c=a.sfid "
				
				+ " left Join ( "
					+ " SELECT    b.propstrength__property__c ,SUM(COALESCE(b.propstrength__rate_per_unit_area__c, 0))  propstrength__rate_per_unit_area__c "
					+ " FROM  salesforce.propstrength__property_charges__c  b "
					+ " LEFT JOIN salesforce.propstrength__other_charges__c c ON  b.propstrength__other_charges__c  =c.sfid "
					+ " where c.propstrength__part_of_cop__c = true and propstrength__type__c='Flexible' "
					+ " group by propstrength__property__c "
				+ " )y on y.propstrength__property__c=a.sfid "
				
				//----------------------------
						
				+ " LEFT JOIN salesforce.gpl_cs_hold_admin_unit b ON a.sfid = b.sfid and b.hold_status = true "
				+ " LEFT JOIN salesforce.propstrength__request__c c ON b.enq_sfid = c.sfid "
				
				+ " where "+whereCondition+"     order by a.propstrength__project_name__c, a.tower_name__c, a.propstrength__house_unit_no__c, a.wing_block__c desc  ", AllUnitData.class);
		 
		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}	