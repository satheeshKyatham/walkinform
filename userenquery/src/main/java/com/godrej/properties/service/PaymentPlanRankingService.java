package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PaymentPlanRanking;

public interface PaymentPlanRankingService {

	public PaymentPlanRanking addPaymentPlanRanking(PaymentPlanRanking data);

	public String insertBulkPaymentRanking(List<PaymentPlanRanking> charges1);

	public List<PaymentPlanRanking> getPaymentPlanRankingList(String projectcode);

}
