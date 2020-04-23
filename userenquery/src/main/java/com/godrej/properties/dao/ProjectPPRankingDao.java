package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.ProjectPPRanking;

public interface ProjectPPRankingDao {
	public ProjectPPRanking insertPaymentRanking(ProjectPPRanking rankingData);

	public String insertBulkPaymentRankingQuery(List<ProjectPPRanking> ranking);

	public String updateBulkPaymentRankingQuery(List<ProjectPPRanking> ranking);

	public String saveBulkPaymentRankingQuery(List<ProjectPPRanking> ranking);

}
