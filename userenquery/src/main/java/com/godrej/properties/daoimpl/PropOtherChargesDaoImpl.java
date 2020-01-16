package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.PropOtherChargesDao;
import com.godrej.properties.model.CustomOtherCharges;
import com.godrej.properties.model.OtherCharges;
import com.godrej.properties.model.PaymentPlanWithOtherCharge;
import com.godrej.properties.model.PropOtherCharges;
import com.godrej.properties.util.CommonUtil;


@Repository("propOtherChargesDao")
public class PropOtherChargesDaoImpl extends AbstractDao<Integer, PropOtherCharges> implements PropOtherChargesDao{
	@Autowired
	private SessionFactory sessionFactory;	
	
	
	/*
	 * @Override public List<PropOtherCharges> getCharges(String propSfid) { Session
	 * session = this.sessionFactory.getCurrentSession();
	 * 
	 * @SuppressWarnings("unchecked") List<PropOtherCharges> list =session.
	 * createQuery(" from PropOtherCharges where propstrength__Property__c='"
	 * +propSfid+"'").list();
	 * 
	 * if(list.size()>0) return list; return null; }
	 */
	
	
	
	@Override
	public List<PropOtherCharges> getCharges(String propSfid, String projectId) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		/*
		 * TypedQuery<String> ccpf = session.
		 * createQuery("select propstrength__other_charge_code__c,typology from PropOtherCharges where propstrength__type__c='Fixed'  "
		 * , String.class); List<String> lista1 = ccpf.getResultList();
		 * TypedQuery<String> ccnpj = session.
		 * createQuery("select propstrength__other_charge_code__c,typology from OtherCharges where propstrength__type__c='Fixed'"
		 * , String.class); lista1.addAll( ccnpj.getResultList());
		 * System.out.println("Final List:-"+lista1.size());
		 */
		List<PropOtherCharges> authors=null;
		/* Excluding otehr charges for Nurture*/
		/*
		 * if(projectId.equals("a1l6F0000080irTQAQ")) { Query q = session.
		 * createNativeQuery("SELECT a.propstrength__Property__c, 	a.row_number,				a.propstrength__rate_per_unit_area__c, a.propstrength__fixed_charge__c, a.propstrength__type__c, a.propstrength__other_charge_code__c, a.sum, a.propstrength__part_of_cop__c FROM salesforce.vw_prop_other_charges a where a.propstrength__Property__c = '"
		 * +propSfid+"' and a.propstrength__part_of_cop__c='t' ",
		 * PropOtherCharges.class); authors = q.getResultList(); } else {
		 */
			
		
		/*
			Query q = session.createNativeQuery("SELECT a.propstrength__sta_applicable__c, a.propstrength__Property__c, 	a.row_number,				a.propstrength__rate_per_unit_area__c, a.propstrength__fixed_charge__c, a.propstrength__type__c, a.propstrength__other_charge_code__c, a.sum, a.propstrength__part_of_cop__c,    a.propstrength__tax_percentage__c, a.propstrength__new_tax_percentage__c, a.propstrength__pmay_gst__c, a.propstrength__new_pmay_gst__c, a.propstrength__st_on_completion_certificate__c, a.propstrength__pmay_abatement__c, a.propstrength__gst_status__c, a.propstrength__completion_certificate_received__c     "
					+ " FROM salesforce.vw_prop_other_charges a where a.propstrength__Property__c = '"+propSfid+"' ", PropOtherCharges.class);
			authors = q.getResultList();
		*/
		String conditionString = "";
		
		if (projectId.equals("a1l2s00000000X5AAI")) {
			conditionString = " AND  propstrength__other_charges__c.propstrength__part_of_cop__c ='t' ";
		} else {
			conditionString = " ";
		}
		
		
			
			Query q = session.createNativeQuery("SELECT row_number() OVER (ORDER BY propstrength__property_charges__c.propstrength__property__c) AS row_number, propstrength__property_charges__c.propstrength__property__c, "
					+ " CASE WHEN propstrength__property_charges__c.propstrength__rate_per_unit_area__c IS NULL THEN cast(0 as double precision)  ELSE propstrength__property_charges__c.propstrength__rate_per_unit_area__c END AS propstrength__rate_per_unit_area__c, "
					+ " CASE  WHEN propstrength__property_charges__c.propstrength__fixed_charge__c IS NULL THEN cast(0 as double precision) ELSE propstrength__property_charges__c.propstrength__fixed_charge__c END AS propstrength__fixed_charge__c, "
					+ " propstrength__property_charges__c.propstrength__type__c, "
					+ " propstrength__property_charges__c.name AS pcname, "
					+ " propstrength__property_charges__c.isdeleted AS property_charges_isdeleted, "
					+ " propstrength__other_charges__c.isdeleted, "
					+ " propstrength__other_charges__c.propstrength__saleable_charge__c, "
					+ " propstrength__other_charges__c.propstrength__active__c, "
					+ " propstrength__other_charges__c.propstrength__part_of_cop__c, "
					+ " propstrength__other_charges__c.name AS propstrength__other_charge_code__c, "
					+ " propstrength__other_charges_tax_mapping__c.name, "
					+ " propstrength__other_charges_tax_mapping__c.isdeleted AS other_charges_tax_isdeleted, "
					+ " propstrength__other_charges_tax_mapping__c.propstrength__service_tax_mapping_category__c, "
					+ " propstrength__service_tax_mapping__c.isdeleted AS service_tax_isdeleted, "
					+ " propstrength__service_tax_mapping__c.propstrength__service_category__c, "
					+ " propstrength__tax_record__c.isdeleted AS tax_record_isdeleted, "
					+ " CASE WHEN  CAST(propstrength__tax_record__c.propstrength__tax_percentage__c as text) <> CAST('' as text) THEN  propstrength__tax_record__c.propstrength__tax_percentage__c * cast(2 as double precision) ELSE cast(0 as double precision) END AS propstrength__tax_percentage__c, "
					+ " CASE WHEN CAST(propstrength__tax_record__c.propstrength__tax_percentage__c as text) <> CAST('' as text) THEN propstrength__tax_record__c.propstrength__tax_percentage__c * cast(2 as double precision) ELSE cast(0 as double precision) END AS sum, "
					+ " propstrength__property_charges__c.propstrength__other_charges__c, "
					+ " CASE WHEN CAST(propstrength__tax_record__c.propstrength__pmay_gst__c as text) <> CAST('' as text) THEN propstrength__tax_record__c.propstrength__pmay_gst__c * cast(2 as double precision) ELSE cast(0 as double precision) END AS propstrength__pmay_gst__c, "
					+ " CASE WHEN CAST(propstrength__tax_record__c.propstrength__new_pmay_gst__c as text) <> CAST('' as text) THEN propstrength__tax_record__c.propstrength__new_pmay_gst__c * cast(2 as double precision) ELSE cast(0 as double precision) END AS propstrength__new_pmay_gst__c, "
					+ " CASE WHEN CAST(propstrength__tax_record__c.propstrength__new_tax_percentage__c as text) <> CAST('' as text) THEN propstrength__tax_record__c.propstrength__new_tax_percentage__c * cast(2 as double precision) ELSE cast(0 as double precision) END AS propstrength__new_tax_percentage__c, "
					+ " propstrength__other_charges__c.propstrength__st_on_completion_certificate__c, "
					+ " propstrength__property__c.propstrength__pmay_abatement__c, "
					+ " propstrength__tower__c.propstrength__gst_status__c, "
					+ " propstrength__other_charges__c.propstrength__sta_applicable__c, "
					+ " propstrength__other_charges_tax_mapping__c.propstrength__tax_name__c, "
					+ " propstrength__property__c.propstrength__completion_certificate_received__c "
					+ " FROM salesforce.propstrength__property_charges__c "
					+ " LEFT JOIN salesforce.propstrength__other_charges__c ON CAST(propstrength__property_charges__c.propstrength__other_charges__c as text)  = CAST(propstrength__other_charges__c.sfid as text)  "
					+ " LEFT JOIN salesforce.propstrength__other_charges_tax_mapping__c ON CAST(propstrength__other_charges__c.sfid as text) = CAST(propstrength__other_charges_tax_mapping__c.propstrength__other_charges__c as text) AND CAST(propstrength__other_charges_tax_mapping__c.propstrength__tax_name__c as text)  ~~  CAST('CGST%' as text) "
					+ " LEFT JOIN salesforce.propstrength__service_tax_mapping__c ON CAST(propstrength__other_charges_tax_mapping__c.propstrength__service_tax_mapping__c as text)  = CAST(propstrength__service_tax_mapping__c.sfid as text) "
					+ " LEFT JOIN salesforce.propstrength__tax_record__c ON CAST(propstrength__service_tax_mapping__c.propstrength__tax_record__c as text) = CAST(propstrength__tax_record__c.sfid as text) "
					+ " LEFT JOIN salesforce.propstrength__property__c ON CAST(propstrength__property_charges__c.propstrength__property__c as text) = CAST(propstrength__property__c.sfid as text) "
					+ " LEFT JOIN salesforce.propstrength__tower__c ON CAST(propstrength__property__c.propstrength__tower__c as text) = CAST(propstrength__tower__c.sfid as text) "
					+ " where propstrength__Property__c = '"+propSfid+"'  "+conditionString+" " , PropOtherCharges.class);
			authors = q.getResultList();
			
			
			
			
			
			/* } */
		
		Query q1 = session.createNativeQuery("SELECT a.project_id,					a.gpl_cs_other_charges_id,	a.propstrength__rate_per_unit_area__c, a.propstrength__fixed_charge__c, a.propstrength__type__c, a.propstrength__other_charge_code__c, a.sum  FROM salesforce.gpl_cs_other_charges a where a.project_id = '"+projectId+"'", CustomOtherCharges.class);
		List<CustomOtherCharges> authors1 = q1.getResultList();
//		authors.add((PropOtherCharges)authors1);
//		System.out.println("Final Size::"+authors.size());
//		System.out.println("Final Size::"+authors.get(0).getPropstrength__other_charge_code__c());
		
		
		for(CustomOtherCharges obj:authors1 ){
			PropOtherCharges prop = new PropOtherCharges();
			prop.setRow_number(obj.getId());
			prop.setPropstrength__other_charge_code__c(obj.getPropstrength__other_charge_code__c());
			prop.setPropstrength__fixed_charge__c(obj.getPropstrength__fixed_charge__c());
			prop.setPropstrength__rate_per_unit_area__c(obj.getPropstrength__rate_per_unit_area__c());
			prop.setPropstrength__type__c(obj.getPropstrength__type__c());
			prop.setSum(obj.getSum());
			
			authors.add(prop);
		}
		
		
		System.out.println("Final Size::"+authors.size());
		
		if (authors.size() > 0)
			return authors;

		return null;
	}


	@Override
	public String getPropertyTypeStatus(String propSfid) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query q = session.createNativeQuery("select property.name,property.sfid,property.propstrength__property_type__c from salesforce.propstrength__property__c property "
							+ " left join  salesforce.propstrength__property_type__c propertytype on(property.propstrength__property_type__c=propertytype.sfid)  "
							+ " left join  salesforce.propstrength__property_type_charges__c typecharge on(propertytype.sfid=typecharge.propstrength__property_type__c)"
							+ " where typecharge.propstrength__iscompulsory__c ='t' and property.sfid='"+propSfid+"'");
		int i = q.getResultList().size();
		if(i>0)
		{
			return "Y";
		}
		else
			return "N";
	}

	/*Changes By Satheesh for Inventory Update*/
	@Override
	public String updatePropertyStatus(String propSfid) {
		Session session = this.sessionFactory.getCurrentSession();	
		Query q = session.createNativeQuery(" update salesforce.propstrength__property__c set propstrength__property_alloted_through_offer__c='t' where sfid='"+propSfid+"'");
		q.executeUpdate();
		return "updated";
		
	}
}