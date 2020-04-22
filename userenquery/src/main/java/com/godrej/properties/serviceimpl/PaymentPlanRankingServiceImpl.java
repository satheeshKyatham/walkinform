package com.godrej.properties.serviceimpl;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.PaymentPlanRankingDao;
import com.godrej.properties.model.PaymentPlanRanking;
import com.godrej.properties.service.PaymentPlanRankingService;
@Service("paymentPlanRankingService")
@Transactional
public class PaymentPlanRankingServiceImpl implements PaymentPlanRankingService{

	@Autowired
	private PaymentPlanRankingDao paymentPlanRankingDao;
	@Override
	public PaymentPlanRanking addPaymentPlanRanking(PaymentPlanRanking data) {
		PaymentPlanRanking rankingData=new PaymentPlanRanking();
		rankingData.setIsactive("A");
		rankingData.setProject_sfid(data.getPayment_plan_sfid());;
		rankingData.setProject_name(data.getProject_name());
		rankingData.setPayment_plan_sfid(data.getPayment_plan_sfid());
		rankingData.setPayment_plan_name(data.getPayment_plan_name());
		rankingData.setSequence(data.getSequence());
		rankingData.setCreatedby(9999);	
		Timestamp createdTimestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(createdTimestamp);
        rankingData.setCreated(createdTimestamp);
        rankingData=paymentPlanRankingDao.insertPaymentRanking(rankingData);
		return rankingData;
		
	}
	@Override
	public void insertBulkPaymentRanking(List<PaymentPlanRanking> ranking) {
		paymentPlanRankingDao.insertBulkPaymentRankingQuery(ranking);
		
	}

}
