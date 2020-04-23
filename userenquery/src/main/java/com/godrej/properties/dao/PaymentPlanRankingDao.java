package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.PaymentPlanRanking;

public interface PaymentPlanRankingDao {

	public PaymentPlanRanking insertPaymentRanking(PaymentPlanRanking rankingData);

	public String insertBulkPaymentRankingQuery(List<PaymentPlanRanking> ranking);

	public List<PaymentPlanRanking> getPaymentPlanRankingQuery(String projectId);

}
