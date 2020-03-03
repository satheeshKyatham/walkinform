package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.PaymentPlanDao;
import com.godrej.properties.model.PaymentPlan; 
@SuppressWarnings("unchecked")
@Repository("paymentPlanDao")
public class PaymentPlanDaoImpl extends AbstractDao<Integer, PaymentPlan> implements PaymentPlanDao {


	@Autowired
	private SessionFactory sessionFactory;
	public PaymentPlanDaoImpl() {
		
	}
	
	 

	 
	
	@Override
	public List<PaymentPlan> getProjectPlan(String project_code, String unit, String towerCode, String pymtPlanSfid) {
		Session session = this.sessionFactory.getCurrentSession();
		//List<PaymentPlan> list  =session.createQuery("  FROM  PaymentPlan  where   propstrength__project_name__c= '"+project_code+"' and  PROPSTRENGTH__HOUSE_UNIT_NO__C='"+unit+"'" ).list();
		
		/*
		 * SELECT * FROM salesforce.propstrength__property__c prop LEFT JOIN
		 * salesforce.gpl_cs_custom_tax ct ON ct.pmay =
		 * prop.propstrength__pmay_abatement__c where propstrength__project_name__c =
		 * 'a1l6F000004QtUZQA0' and sfid = 'a1s6F00000AWYPTQA5'
		 */
		
		
		
		List<PaymentPlan> list  =session.createQuery("  FROM  PaymentPlan  where   propstrength__project_name__c= '"+project_code+"' and  sfid='"+unit+"'  and  pymt_plan_id = '"+pymtPlanSfid+"'   " ).list();  //and tower_sfid='"+towerCode+"' 
		if(list.size()>0)
		{
			return list;
		}
		return list;
	}

 
	
	@Override
	public List<PaymentPlan> getTower(String project_code) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Object>  list  =(List<Object>)session.createQuery( " select Distinct(tower_code__c),tower_name__c FROM  PaymentPlan  where propstrength__project_name__c='"+project_code+"'" + " order by tower_name__c" ).list();
		if(list.size()>0)
		{
			List<PaymentPlan> paymentPlans = new ArrayList<PaymentPlan>();
	
			 Iterator itr = list.iterator();
			  while(itr.hasNext()){
				  PaymentPlan paymentPlan= new PaymentPlan();
			     Object[] obj = (Object[]) itr.next();
			      
			     String client = String.valueOf(obj[0]); 
			     String clien1t = String.valueOf(obj[1]);
			     paymentPlan.setTower_code__c(client);
			     paymentPlan.setTower_name__c(clien1t);
			     
			     paymentPlans.add(paymentPlan);
			  }
			 
			return paymentPlans;
		}
		return new ArrayList<>();
	}



	@Override
	public List<PaymentPlan> getHouseUnit(String project_code, String tower_code, String floor_code, String unit) {
 
		//FROM SALESFORCE.PROPSTRENGTH__PROPERTY__C  where propstrength__project_name__c='a1l6F000004QtUZQA0'  
		//and tower_code__c='AQ02' and propstrength__floor_number__c=1  and PropStrength__Unit_Type__c='2 BHK'
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Object>  list  ;
	 	if(floor_code!=null && !"".equals(floor_code) && unit!=null &&!"".equals(unit) ) {
			list  =(List<Object>)session.createQuery("Select Distinct(propstrength__house_unit_no__c)  as propstrength__house_unit_no__c,tower_code__c,sfid,propstrength__property_name__c  "
					+ "FROM  PaymentPlan  where  propstrength__project_name__c='"+project_code+"'  and tower_code__c='"+tower_code+"' and propstrength__floor_number__c='"+floor_code+"'  and PropStrength__Unit_Type__c='"+unit+"'" ).list();
	 	}else {
	 		list  =(List<Object>)session.createQuery("Select Distinct(propstrength__house_unit_no__c)  as propstrength__house_unit_no__c,tower_code__c,sfid,propstrength__property_name__c  "
					+ "FROM  PaymentPlan  where  propstrength__project_name__c='"+project_code+"'  and tower_code__c='"+tower_code+"' and propstrength__allotted__c=false" ).list();
	 	}
	 	
		if(list.size()>0)
		{
			List<PaymentPlan> paymentPlans = new ArrayList<PaymentPlan>();
	
			 Iterator itr = list.iterator();
			 int sum =0;
			 while (itr.hasNext()) {
				 itr.next();
				  sum++;
				}
			 itr = list.iterator();
			  while(itr.hasNext()){
				  PaymentPlan paymentPlan= new PaymentPlan();
				  /*if(sum>1) {*/
					  Object[] obj = (Object[]) itr.next();
					  String client = String.valueOf(obj[0]); 
					  paymentPlan.setPropstrength__house_unit_no__c(client);
					  paymentPlan.setSfid( String.valueOf(obj[2]));
					  paymentPlan.setPropstrength__property_name__c( String.valueOf(obj[3]));
					  paymentPlans.add(paymentPlan);
				 /* }
				  else {
					  Object s=(Object) itr.next();
					  
					  paymentPlan.setPropstrength__house_unit_no__c(s.toString() );
					  paymentPlans.add(paymentPlan);
					 
				  }*/
					  
			    
			     
			    
			  }	
			  return paymentPlans;
		}
		return new ArrayList<>();
	}



	@Override
	public List<PaymentPlan> getUnitType(String project_code, String tower_code, String floor_code) {
//Select Distinct(PropStrength__Unit_Type__c)  as PropStrength__Unit_Type__c FROM SALESFORCE.PROPSTRENGTH__PROPERTY__C  where
		//propstrength__project_name__c='a1l6F000004QtUZQA0'    and tower_code__c='AQ02' and propstrength__floor_number__c=1
		Session session = this.sessionFactory.getCurrentSession();
		 StringBuffer str= new StringBuffer();
		 str.append(" Select Distinct propstrength__unit_type__c  FROM  PaymentPlan  ");
		 str.append("where propstrength__project_name__c='"+project_code+"' and d4u_active__c = true ");
		 
		 if(tower_code.equals("All")) {
			 //str.append(" and propstrength__floor_number__c='"+floor_code+"'" );
		 } else {
			 str.append(" and tower_code__c='"+tower_code+"'" );
		 }
		 
		 
		 if(!floor_code.equals("")) {
			 str.append(" and propstrength__floor_number__c='"+floor_code+"'" );
		 }
		  
		List<Object>  list  =(List<Object>)session.createQuery(str.toString()  ).list();
		if(list.size()>0)
		{
			List<PaymentPlan> paymentPlans = new ArrayList<PaymentPlan>();
	 for(int i=0;i<list.size();i++) {
		  PaymentPlan paymentPlan= new PaymentPlan();
		  paymentPlan.setPropstrength__unit_type__c(list.get(i).toString());
		  paymentPlans.add(paymentPlan);
		 
	 }
			
			 
			  return paymentPlans;
		}
		return null;
	}



	@Override
	public List<PaymentPlan> getfloor(String project_code, String tower_code) {
		//Select Distinct(propstrength__floor_number__c),floor_name__c,tower_name__c FROM
		//SALESFORCE.PROPSTRENGTH__PROPERTY__C  
		//where propstrength__project_name__c='a1l6F000004QtUZQA0' and tower_code__c='AQ01'

		Session session = this.sessionFactory.getCurrentSession();
		 
		List<Object>  list  =(List<Object>)session.createQuery(" Select Distinct(propstrength__floor_number__c),floor_name__c "
				+ " FROM  PaymentPlan  where propstrength__project_name__c='"+project_code+"'  and  tower_code__c='"+tower_code+"'" ).list();
		if(list.size()>0)
		{
			List<PaymentPlan> paymentPlans = new ArrayList<PaymentPlan>();
	
			 Iterator itr = list.iterator();
			  while(itr.hasNext()){
				  PaymentPlan paymentPlan= new PaymentPlan();
			     Object[] obj = (Object[]) itr.next();
			      
			     String client = String.valueOf(obj[0]); 
			     String client1 = String.valueOf(obj[1]); 
			     
			     paymentPlan.setPropstrength__floor_number__c(client);
			     paymentPlan.setFloor_name__c(client1);
			     
			     
			     paymentPlans.add(paymentPlan);
			  }	
			  return paymentPlans;
		}
		return new ArrayList<>();
	}
		 
	
	 
	 
	
}
