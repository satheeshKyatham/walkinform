package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.BSPAgainstPymtPlanDao;
import com.godrej.properties.model.BSPAgainstPymtPlan;

@Repository("bSPAgainstPymtPlanDao")
public class BSPAgainstPymtPlanDaoImpl extends AbstractDao<Integer, BSPAgainstPymtPlan> implements BSPAgainstPymtPlanDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insertBSPForPP(BSPAgainstPymtPlan bSPAgainstPymtPlan) {
		persist(bSPAgainstPymtPlan);
	}

	@Override
	public int checkBSPForPP(BSPAgainstPymtPlan bSPAgainstPymtPlan) {
		Session session = this.sessionFactory.getCurrentSession();
		String appendWhere = "";
		if(bSPAgainstPymtPlan.getTowerid()!=null && bSPAgainstPymtPlan.getTowerid().length()>0)
		{
			appendWhere+=" and towerid='"+bSPAgainstPymtPlan.getTowerid()+"' and typology='"+bSPAgainstPymtPlan.getTypology()+"'";
		}
		/*if(bSPAgainstPymtPlan.getTypology() !=null && bSPAgainstPymtPlan.getTypology().length()>0)
		{
			appendWhere+=" and typology='"+bSPAgainstPymtPlan.getTypology()+"'";
		}*/
		List<BSPAgainstPymtPlan> list  =session.createQuery("  FROM  BSPAgainstPymtPlan  where pymt_plan_id='"+bSPAgainstPymtPlan.getPymt_plan_id()+"' "+appendWhere+" " ).list();
		if(list.size()>0)
		{
			return 1;
		}
		return 0;
	}

	@Override
	public List<BSPAgainstPymtPlan> getPaymentPlanBSPList(String paymentPlanID) {
		Session session = this.sessionFactory.getCurrentSession();
		List<BSPAgainstPymtPlan> list  =session.createQuery("  FROM  BSPAgainstPymtPlan  where pymt_plan_id='"+paymentPlanID+"'" ).list();
		if(list.size()>0)
		{
			return list;
		}
		return null;
	}
	
	
}
