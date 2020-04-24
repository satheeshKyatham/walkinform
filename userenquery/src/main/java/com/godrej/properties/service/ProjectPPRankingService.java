package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.ProjectPPRanking;

public interface ProjectPPRankingService {
	public ProjectPPRanking addPaymentPlanRanking(ProjectPPRanking data);

	public String insertBulkPaymentRanking(List<ProjectPPRanking> charges1);
}
