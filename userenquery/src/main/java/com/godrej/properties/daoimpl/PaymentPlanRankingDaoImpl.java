package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.PaymentPlanRankingDao;
import com.godrej.properties.model.PaymentPlanRanking;

@SuppressWarnings("unchecked")
@Repository("paymentPlanRankingDao")
public class PaymentPlanRankingDaoImpl extends AbstractDao<Integer, PaymentPlanRanking> implements PaymentPlanRankingDao {

	private Logger Log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public PaymentPlanRanking insertPaymentRanking(PaymentPlanRanking towerPP) {
		try{
			persist(towerPP);
			return towerPP;
		}catch(Exception e){
			Log.error("not saved");
			e.printStackTrace();
		}
		return towerPP;
	}


	@Override
	public String insertBulkPaymentRankingQuery(List<PaymentPlanRanking> ranking) {
		String msg="Not Submitted";
		try{
		batchPersist(ranking);
		
		}catch (Exception e) {
			 msg="already submmitted";
			 return msg;
		}
		return msg;
	}


	@Override
	public List<PaymentPlanRanking> getPaymentPlanRankingQuery(String projectId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<PaymentPlanRanking> data=new ArrayList<PaymentPlanRanking>();		
		List<PaymentPlanRanking> list = session.createQuery(" from PaymentPlanRanking where isactive='A' and project_sfid='"+projectId+"' order By sequence").list();
		if (list.size() > 0){
			return list;
		}
		return data;
	}


	
}
