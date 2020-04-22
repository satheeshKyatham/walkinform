package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PaymentPlanRanking;
import com.godrej.properties.model.SchemeMapping;

public interface PaymentPlanRankingService {

	public PaymentPlanRanking addPaymentPlanRanking(PaymentPlanRanking data);

	public String insertBulkPaymentRanking(List<PaymentPlanRanking> charges1);

}
