package com.godrej.properties.serviceimpl;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.ProjectPPRankingDao;
import com.godrej.properties.model.ProjectPPRanking;
import com.godrej.properties.service.ProjectPPRankingService;
@Service("projectPPRankingService")
@Transactional
public class ProjectPPRankingServiceImpl implements ProjectPPRankingService{
	@Autowired
	private ProjectPPRankingDao projectPPRankingDao;
	@Override
	public ProjectPPRanking addPaymentPlanRanking(ProjectPPRanking data) {
		ProjectPPRanking rankingData=new ProjectPPRanking();
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
        rankingData=projectPPRankingDao.insertPaymentRanking(rankingData);
		return rankingData;
		
	}
	@Override
	public String insertBulkPaymentRanking(List<ProjectPPRanking> ranking) {
		projectPPRankingDao.saveBulkPaymentRankingQuery(ranking);
		projectPPRankingDao.updateBulkPaymentRankingQuery(ranking);
		String msg=projectPPRankingDao.insertBulkPaymentRankingQuery(ranking);
		return msg;
		
	}

}
