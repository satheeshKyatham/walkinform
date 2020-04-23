package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.ProjectPPRankingDao;
import com.godrej.properties.model.ProjectPPRanking;

@Repository("projectPPRankingDao")
public class ProjectPPRankingDaoImpl extends AbstractDao<Integer, ProjectPPRanking> implements ProjectPPRankingDao{
	private Logger Log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public ProjectPPRanking insertPaymentRanking(ProjectPPRanking towerPP) {
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
	public String insertBulkPaymentRankingQuery(List<ProjectPPRanking> ranking) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery("INSERT INTo salesforce.gpl_cip_project_pp_ranking	(project_name, project_sfid, payment_plan_name,payment_plan_sfid, "
				+ "	sequence, isactive, createddate, createdby)	(	SELECT	b.project_name, b.project_sfid, b.payment_plan_name,b.payment_plan_sfid, "
				+ "	b.sequence, b.isactive, b.createddate, b.createdby	from salesforce.gpl_cip_project_pp_ranking_temp b WHERE "
				+ "  NOT EXISTS (SELECT  a.project_name FROM salesforce.gpl_cip_project_pp_ranking a  WHERE a.payment_plan_sfid = b.payment_plan_sfid "
				+ "	  AND a.project_sfid =b.project_sfid)	)");
		query.executeUpdate();
		Query query1 = session.createQuery("delete from ProjectPPRanking ");
		query1.executeUpdate();
		return "";
	}


	@Override
	public String updateBulkPaymentRankingQuery(List<ProjectPPRanking> ranking) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 = session.createNativeQuery("UPDATE salesforce.gpl_cip_project_pp_ranking z SET sequence =  x.sequence FROM "
				+ " (SELECT  b.project_name, b.project_sfid, b.payment_plan_name,b.payment_plan_sfid,  b.sequence, b.isactive, b.createddate, b.createdby "
				+ " from salesforce.gpl_cip_project_pp_ranking_temp b WHERE  EXISTS "
				+ " (SELECT  a.project_name FROM salesforce.gpl_cip_project_pp_ranking a  WHERE a.payment_plan_sfid = b.payment_plan_sfid"
				+ "  AND a.project_sfid =b.project_sfid )) x WHERE z.project_sfid=x.project_sfid AND z.payment_plan_sfid=x.payment_plan_sfid and z.sequence !=x.sequence");
		
		query1.executeUpdate();		
		return "";
	}


	@Override
	public String saveBulkPaymentRankingQuery(List<ProjectPPRanking> ranking) {
		batchPersist(ranking);
		return "";
	}

}
