package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.PaymentPlanDueDao;
import com.godrej.properties.model.PaymentPlanDue;

@SuppressWarnings("unchecked")
@Repository("paymentPlanDueDao")
public class PaymentPlanDueDaoImpl extends AbstractDao<Integer, PaymentPlanDue> implements PaymentPlanDueDao {
	private Logger Log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public PaymentPlanDue insertPaymentDue(PaymentPlanDue paymentDue) {
		try{
			persist(paymentDue);
			return paymentDue;
		}catch(Exception e){
			Log.error("not saved");
			e.printStackTrace();
		}
		return paymentDue;
		
	}


	@Override
	public List<PaymentPlanDue> getPaymentDueListQuery(String project_sfid,String tower_sfid,String payment_plan_sfid) {
		Session session = this.sessionFactory.getCurrentSession();
		List<PaymentPlanDue> list = session.createQuery(" from PaymentPlanDue where isactive='A' and project_id='"+project_sfid+"' and towerid='"+tower_sfid+"' and pymt_plan_id='"+payment_plan_sfid+"'").list();
		if (list.size() > 0){
			return list;
		}
		return list;
	}


	@Override
	public boolean updatePaymentDueQuery(PaymentPlanDue setDto) {
		Session session = this.sessionFactory.getCurrentSession();
		/*Query query = session.createQuery("UPDATE PaymentPlanDue  set  dues_amount="+setDto.getDues_amount()+",pymt_plan_id='"+setDto.getPymt_plan_id()+"',"
				+ "pymt_plan_name='"+setDto.getPymt_plan_name()+"' ,project_id='"+setDto.getProject_id()+"' ,project_name='"+setDto.getProject_name()+"',"
						+ "region_id='"+setDto.getRegion_id()+"',region_name='"+setDto.getRegion_name()+"',towerid='"+setDto.getTowerid()+"',tower_name='"+setDto.getTower_name()+"'"
				
				+ " WHERE id = '"+setDto.getId()+"' ");*/
		//dues_amount='"+setDto.getDues_amount()+"',days='"+setDto.getDays()+"',
		Query query = session.createNativeQuery("UPDATE salesforce.gpl_cs_due_against_pymtplan  set dues_amount='"+setDto.getDues_amount()+"',days='"+setDto.getDays()+"', bookingamount='"+setDto.getBookingamount()+"',payplan_milestones='"+setDto.getPayplan_milestones()+"' "
				
				+ " WHERE gpl_cs_due_against_pymtplan_id = "+setDto.getId()+" ");
		query.executeUpdate();
		return true;
	}

}
