package com.godrej.properties.daoimpl;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.AAbstractDao;
import com.godrej.properties.dao.EnquiryReportDao;
import com.godrej.properties.dto.EnquiryReportDto;
import com.godrej.properties.model.EnquiryReport;

/**
 * 
 * @author Varsha Patil
 *
 */
@Repository
@Transactional
public class EnquiryReportDaoImpl extends AAbstractDao<EnquiryReport> implements EnquiryReportDao{
	
	@Override
	public EnquiryReport insertEnquiryReport(EnquiryReport enq) {
		persist(enq);
		return enq;
	}

	/*@Override
	public EnquiryReport update(EnquiryReport entity) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	@Override
	public EnquiryReport findById(Integer id) {		
		return findOne(id);
	}

	@Override
	public EnquiryReport getEnquiryReportById(Integer id) {
		 StringBuilder jpql=new StringBuilder();
			 jpql.append(" SELECT er FROM EnquiryReport er ");
			 /*jpql.append(" LEFT JOIN FETCH er.enquiry eid ");
			 jpql.append(" LEFT JOIN FETCH er.enquirySfid esfid ");
			 jpql.append(" LEFT JOIN FETCH er.contact c ");*/
			 jpql.append(" where er.enquiryReportId=:enquiryReportId ");
			 Map<String, Object> params=new HashMap<>();
			 params.put("enquiryReportId", id);
		return getSingleEntity(jpql.toString(), params);
	}
	@Override
	public synchronized void updateEnquirySfidToCustomEnquiry() {
		StringBuilder jpql=new StringBuilder();
		jpql.append(" UPDATE salesforce.nv_hc_enquiry he ")
		.append(" SET enquiry_sfid=a.sfid,isupdated='Y' ")
		.append(" FROM (select sfid,NVHC_Enquiry_ID__c from salesforce.propstrength__request__c enq) a where a.NVHC_Enquiry_ID__c=he.nv_hc_enquiry_id and a.sfid is not null and he.isupdated='N'  ");
		updateByNative(jpql.toString(), null);	
	}

	@Override
	public void updateById(Map<String,Object> params) {
		 StringBuilder jpql=new StringBuilder();
		 jpql.append(" Update EnquiryReport er ")
		 .append(" set er.enquiryId=:enquiryId ");
		/* .append(" set er.enquiry.enquiryId=:enquiryId ");*/
		 jpql.append(" where er.enquiryReportId=:enquiryReportId ");
		Query query=getSession().createQuery(jpql.toString());
		query.setParameter("enquiryId", params.get("enquiryId"));
		query.setParameter("enquiryReportId", params.get("enquiryReportId"));
		/*query.setParameter("contactId", params.get("contactId"));*/
		query.executeUpdate();	
	}

	@Override
	public int updatePaymentDetails(EnquiryReportDto dto) {
		StringBuilder jpql=new StringBuilder(" Update EnquiryReport enq ")
				.append(" set enq.desiredUnitType=:desiredUnitType, ")
				.append("  enq.chequeno_file_name=:chequeno_file_name, ")
				.append("  enq.panNo_file_name=:panNo_file_name, ")
				.append("  enq.uTRNo_file_name=:uTRNo_file_name ,")
				.append("  enq.unit=:unit , enq.tower=:tower ")
				 
				 
				.append(" where enq.enquiryReportId=:enquiryReportId ");
		 
				Query query=getSession().createQuery(jpql.toString());
				query.setParameter("desiredUnitType", dto.getDesiredUnitType());
				query.setParameter("enquiryReportId",dto.getEnquiryReportId());
				query.setParameter("chequeno_file_name",dto.getChequeno_file_name());
				query.setParameter("panNo_file_name",dto.getPanNo_file_name());
				query.setParameter("uTRNo_file_name",dto.getuTRNo_file_name());
				query.setParameter("tower",dto.getTower());
				query.setParameter("unit",dto.getUnit());
				
				 
				
				int i=query.executeUpdate();
				return i;
	}

	@Override
	public EnquiryReport getEnquiryReportEnquiryID(Integer id) {
		 StringBuilder jpql=new StringBuilder();
		 jpql.append(" SELECT er FROM EnquiryReport er ");
		 jpql.append(" where er.enquiryId=:enquiryId ");
		 Map<String, Object> params=new HashMap<>();
		 params.put("enquiryId", id);
	return getSingleEntity(jpql.toString(), params);
	}

}
