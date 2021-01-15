package com.godrej.properties.daoimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AAbstractDao;
import com.godrej.properties.dao.PushEnquiryDataDao;
import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.model.Enquiry;
import com.godrej.properties.model.Enquiryqty;
import com.godrej.properties.model.Token;
import com.godrej.properties.util.CommonUtil;

@Repository("pushEnquiryDataDao")
public class PushEnquiryDataDaoImpl  extends AAbstractDao<Enquiry> implements PushEnquiryDataDao{

	@Override
	public Enquiry insertInputEnquiry(Enquiry enq) {
		persist(enq);
		return enq;
	}

	/*@Override
	public Enquiry update(Enquiry entity) {
		return super.update(entity);
	}*/

	@Override
	public Enquiry findById(Integer id) {
		return findOne(id);
	}

	@Override
	public Enquiry getEnquiryByMobileNo(String mobileNo) {
		StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT e FROM Enquiry e ")
		.append(" LEFT JOIN FETCH e.project p ")
		.append(" INNER JOIN FETCH e.contact c ")
		.append(" LEFT JOIN FETCH c.contactReport ccr ")
		.append(" LEFT JOIN FETCH e.contactId cs ")
		.append(" LEFT JOIN FETCH e.channelPartner cp ")
		.append(" LEFT JOIN FETCH e.brokerContact bc ")
		.append(" LEFT JOIN FETCH e.enquiryReport er ")		
		/*.append(" WHERE c.mobileNo=:mobileNo ");*/
		/*.append(" WHERE concat(c.countryCode,c.mobile)=:mobileNo ");*/
		.append(" WHERE c.mobile=:mobile ")
		.append(" AND c.countryCode=:countryCode ");
	/*	.append(" WHERE concat(c.countryCode,c.mobile)=:mobileNo ");*/
		Map<String, Object> params=new HashMap<>();
		String code=mobileNo.substring(0,3);
		String mobile=mobileNo.substring(3);
		params.put("countryCode",code);
		params.put("mobile",mobile);
		/*Map<String, Object> params=new HashMap<>();
		params.put("mobileNo", mobileNo);*/
		return getSingleEntity(jpql.toString(), params);
	}
	
	@Override
	public List<Enquiry> getEnquiriesByMobileNo(String countryCode,String mobileNo) {
		StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT e FROM Enquiry e ")
		.append(" LEFT JOIN FETCH e.project p ")
		.append(" INNER JOIN FETCH e.contact c ")
		.append(" LEFT JOIN FETCH c.contactReport ccr ")
		.append(" LEFT JOIN FETCH e.contactId cs ")
		.append(" LEFT JOIN FETCH e.channelPartner cp ")
		.append(" LEFT JOIN FETCH e.brokerContact bc ")
		.append(" LEFT JOIN FETCH e.enquiryReport er ")
		/*.append(" WHERE c.mobileNo=:mobileNo ");*/
		.append(" WHERE c.mobile=:mobile ")
		.append(" AND c.countryCode=:countryCode ");
	/*	.append(" WHERE concat(c.countryCode,c.mobile)=:mobileNo ");*/
		Map<String, Object> params=new HashMap<>();
		//Change By Satheesh Kyatham
		/*String code=mobileNo.substring(0,3);
		String mobile=mobileNo.substring(3);*/
		params.put("countryCode",countryCode);
		params.put("mobile",mobileNo);
		/*params.put("mobileNo",mobileNo);*/
		return getEntities(jpql.toString(),params);
	}
	
	@Override
	public void syncContactSfidToEnquiry() {
		StringBuilder jpql=new StringBuilder();
		
		/*jpql.append(" UPDATE salesforce.propstrength__request__c abc")
		.append(" SET propstrength__primary_contact__c=c.sfid, ")
		.append(" synchronised='Y' ")
		.append(" FROM salesforce.contact c ")
		.append(" WHERE c.id=abc.external_contact_id and c.sfid is not null and abc.synchronised='N' ");*/
		jpql.append(" UPDATE salesforce.propstrength__request__c abc ")
		.append(" SET propstrength__primary_contact__c=a.sfid,Synchronised__c='Y'  ")
		.append(" FROM (select sfid,id from salesforce.contact c) a where a.id=CAST(abc.External_Contact_ID__c as integer) and a.sfid is not null and abc.Synchronised__c='N'  ");
		updateByNative(jpql.toString(), null);
		
	}

	@Override
	public List<Enquiry> getEnquiriesByMobileNoAndProject(String countryCode,String mobileNo, String projectSfid,String userid) {
		StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT e FROM Enquiry e ")
		.append(" INNER JOIN FETCH e.contactId c ")
		.append(" LEFT JOIN FETCH c.contactReport ccr ")
		.append(" LEFT JOIN FETCH e.project p ")
		//.append(" LEFT JOIN FETCH e.contactId cs ")
		.append(" LEFT JOIN FETCH e.channelPartner cp ")
		.append(" LEFT JOIN FETCH e.brokerContact bc ")
		.append(" LEFT JOIN FETCH e.enquiryReport er ")
		/*.append(" WHERE c.mobileNo=:mobileNo and p.sfid=:projectSfid ");*/
		/*.append(" WHERE concat(c.countryCode,c.mobile)=:mobileNo and p.sfid=:projectSfid ");*/
		/*Map<String, Object> params=new HashMap<>();
		params.put("mobileNo", mobileNo);*/
		.append(" WHERE c.mobile=:mobile ")
		.append(" AND c.countryCode=:countryCode ")
		//.append(" AND e.eoi_enquiry__c=:eoi_enquiry__c ")
		.append(" AND p.sfid=:projectSfid  ")
		.append(" and (Date(now())-Date(e.lastModifiedDate))<60  ORDER BY e.dateOfEnquiry asc");//(Date(now())-Date(e.dateOfEnquiry))<90 and//desc and Date(Date_of_Site_Visit__c)='2020-05-12'
		// .append(" ORDER BY to_char(e.lastModifiedDate,'yyyy-MM-dd hh24:')"); //and e.lastModifiedDate is not null 
		/*.append(" AND (c.recordType='"+KeyConstants.RECORD_TYPE_PROSPECT+"'")*/
		/*.append(" OR (c.recordType='"+KeyConstants.RECORD_TYPE_CUSTOMER+"'");*/
		
	/*	.append(" WHERE concat(c.countryCode,c.mobile)=:mobileNo ");*/
		Map<String, Object> params=new HashMap<>();
		//Changes By Satheesh Kyatham
		/*String code=mobileNo.substring(0,3);
		String mobile=mobileNo.substring(3);*/
		
		params.put("countryCode",countryCode);
		params.put("mobile",mobileNo);
		/*params.put("mobileNo",mobile);*/
		params.put("projectSfid", projectSfid);
		//params.put("eoi_enquiry__c", false);
		
		return getEntities(jpql.toString(), params);
	}
	@Override
	public List<Enquiry> getEnquiriesByMobileNoAndProject(String countryCode,String mobileNo, String projectSfid,String token,String userid) {
		StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT e FROM Enquiry e ")
		.append(" INNER JOIN FETCH e.contact c ")
		.append(" LEFT JOIN FETCH c.contactReport ccr ")
		.append(" LEFT JOIN FETCH e.project p ")
		.append(" LEFT JOIN FETCH e.contactId cs ")
		.append(" LEFT JOIN FETCH e.channelPartner cp ")
		.append(" LEFT JOIN FETCH e.brokerContact bc ")
		.append(" LEFT JOIN FETCH e.enquiryReport er ")
		.append(" WHERE c.mobile=:mobile ")
		.append(" AND c.countryCode=:countryCode ")
		.append(" AND p.sfid=:projectSfid ")
		//.append(" AND e.eoi_enquiry__c=:eoi_enquiry__c ")
		/*.append(" WHERE c.mobileNo=:mobileNo and p.sfid=:projectSfid ")*/
		/*.append(" WHERE concat(c.countryCode,c.mobile)=:mobileNo and p.sfid=:projectSfid ")*/
		.append(" and e.enquiryId IN(select CAST(t.enquiry_18 AS integer) FROM Token t where concat(t.type,cast(t.queue as text))=:token  AND t.isactive='Y' and projectname=:projectSfid) ")
		.append(" and p.sfid IN(select tk.projectname FROM Token tk where concat(tk.type,cast(tk.queue as text))=:token  AND tk.isactive='Y' and projectname=:projectSfid) ");
		Map<String, Object> params=new HashMap<>();
		/*params.put("mobileNo", mobileNo);*/
		
		//Added By Satheesh Kyatham
		/*String code=mobileNo.substring(0,3);
		String mobile=mobileNo.substring(3);*/
		params.put("countryCode",countryCode);
		params.put("mobile",mobileNo);
		params.put("projectSfid", projectSfid);
		params.put("token", token);
		//params.put("eoi_enquiry__c", false);
		return getEntities(jpql.toString(), params);
	}
	
	/*@Override
	public Enquiry getEnquiryById(Integer id) {
		StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT e FROM Enquiry e ")
		.append(" LEFT JOIN FETCH e.project p ")
		.append(" INNER JOIN FETCH e.contact c ")
		.append(" LEFT JOIN FETCH c.contactReport ccr ")
		.append(" LEFT JOIN FETCH e.contactId cs ")
		.append(" LEFT JOIN FETCH e.channelPartner cp ")
		.append(" LEFT JOIN FETCH e.brokerContact bc ")
		.append(" LEFT JOIN FETCH e.enquiryReport er ")
		.append(" WHERE e.id=:enquiryId ");
		
		Map<String, Object> params=new HashMap<>();
		params.put("enquiryId", id);
		return getSingleEntity(jpql.toString(), params);
	}*/
	
	/*@Override
	public Enquiry getEnquiryById(Integer id) {
		StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT e FROM Enquiry e ")
		.append(" LEFT JOIN Project p on e.project = p.sfid ")
//		.append(" INNER JOIN Contact c on CAST(e.External_Contact_ID__c as Integer)= c.id ")
//		.append(" INNER JOIN FETCH e.contact c ")//(a.publications AS Book)
		.append(" INNER JOIN Contact c on e.id::integer=c.id ")
		.append(" LEFT JOIN ContactReport ccr on c.contactReport=ccr.contactReportId ")
		//.append(" LEFT JOIN Contact cs e.contactId = cs. ")
		//.append(" LEFT JOIN FETCH e.channelPartner cp ")
		//.append(" LEFT JOIN FETCH e.brokerContact bc ")
		//.append(" LEFT JOIN FETCH e.enquiryReport er ")
		
		jpql.append(" SELECT req.sfid FROM salesforce.propstrength__request__c req ")
		.append(" left outer join salesforce.PropStrength__Projects__c prj on req.PropStrength__Project__c=prj.sfid ")
		.append(" inner join salesforce.contact con on CAST(req.External_Contact_ID__c as integer)=con.id ")
		.append(" WHERE CAST(req.id as integer)=:enquiryId ");
		
		Query query=getSession().createQuery(jpql.toString());
		query.setParameter("enquiryId", id);
		@SuppressWarnings("unchecked")
		List<Enquiry> list=query.getResultList();
		if(!CommonUtil.isListEmpty(list)){
			return list.get(0);
		}		
		return null;
		//Map<String, Object> params=new HashMap<>();
		//params.put("enquiryId", id);
		//return getSingleEntity(jpql.toString(), params);
	}*/
	
	/*@Override
	public Enquiry getEnquiryById(Integer id) {
		StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT e FROM Enquiry e ")
		.append(" left outer join Project prj on e.project=prj.sfid ");
		jpql.append(" inner join Contact con on e.contact=con.id ");
		jpql.append(" WHERE e.id=:enquiryId  ");
		Map<String, Object> params=new HashMap<>();
		params.put("mobileNo", mobileNo);
		params.put("token", token);
		Query query=getSession().createQuery(jpql.toString());
		query.setParameter("enquiryId", id);
		@SuppressWarnings("unchecked")
		List<Enquiry> list=query.getResultList();
		if(!CommonUtil.isListEmpty(list)){
			return list.get(0);
		}		
		return null;
		
	}*/
	
	@Override
	public Enquiry getEnquiryById(Integer id) {
		/*StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT e FROM Enquiry e ")
		.append(" LEFT JOIN FETCH e.project p ")
		.append(" INNER JOIN FETCH e.contact c ")
		.append(" LEFT JOIN FETCH c.contactReport ccr ")
		.append(" LEFT JOIN FETCH e.contactId cs ")
		.append(" LEFT JOIN FETCH e.channelPartner cp ")
		.append(" LEFT JOIN FETCH e.brokerContact bc ")
		.append(" LEFT JOIN FETCH e.enquiryReport er ")
		.append(" WHERE e.id="+id+" ");
		//.append(" WHERE e.id=:enquiryId ");
		
		Map<String, Object> params=new HashMap<>();
		//params.put("enquiryId", id);
		return getSingleEntity(jpql.toString(), params);*/
		
		StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT e FROM Enquiryqty e ")
		.append(" LEFT JOIN FETCH e.project p ")
		.append(" INNER JOIN FETCH e.contact c ")
		.append(" LEFT JOIN FETCH c.contactReport ccr ")
		//.append(" LEFT JOIN FETCH e.contactId cs ")
		.append(" LEFT JOIN FETCH e.channelPartner cp ")
		.append(" LEFT JOIN FETCH e.brokerContact bc ")
		.append(" LEFT JOIN FETCH e.enquiryReport er ")
		.append(" WHERE e.id=:enquiryId ");
		Query query=getSession().createQuery(jpql.toString());
		query.setParameter("enquiryId", id);
		@SuppressWarnings("unchecked")
		List<Enquiryqty> list=query.getResultList();
		
		if(!CommonUtil.isListEmpty(list)){
			Enquiryqty enqqty = list.get(0);
			
			Enquiry cust = new Enquiry();
			cust.setAdvertisement(enqqty.getAdvertisement());
			cust.setAppointment__c(enqqty.getAppointment__c());
			cust.setAppointment_Done__c(enqqty.getAppointment_Done__c());
			cust.setAssignTo(enqqty.getAssignTo());
			cust.setBranch(enqqty.getBranch());
			cust.setBrokerContact(enqqty.getBrokerContact());
			cust.setBudget(enqqty.getBudget());
			cust.setChannelPartner(enqqty.getChannelPartner());
			cust.setClosing_manager_name__c(enqqty.getClosing_manager_name__c());
			cust.setClosingmanagers(enqqty.getClosingmanagers());
			
			cust.setContact(enqqty.getContact());
			cust.setContactId(enqqty.getContactId());
			
			cust.setDate_of_eoi__c(enqqty.getDate_of_eoi__c());
			cust.setDateOfEnquiry(enqqty.getDateOfEnquiry());
			cust.setDateOfSiteVisit(enqqty.getDateOfSiteVisit());
			cust.setDesiredUnitType(enqqty.getDesiredUnitType());
			cust.setEnquiryId(enqqty.getEnquiryId());
			cust.setEnquiryRating(enqqty.getEnquiryRating());
			cust.setEnquiryReport(enqqty.getEnquiryReport());
			cust.setEnquirySource(enqqty.getEnquirySource());
			cust.setEnquiryStatus(enqqty.getEnquiryStatus());
			cust.setEoi_enquiry__c(enqqty.isEoi_enquiry__c());
			cust.setEOI_Preferred_Floor_Band__c(enqqty.getEOI_Preferred_Floor_Band__c());
			cust.setEoi_preferred_unit__c(enqqty.getEoi_preferred_unit__c());
			cust.setEOI_Remarks__c(enqqty.getEOI_Remarks__c());
			cust.setEOI_Tower_Series__c(enqqty.getEOI_Tower_Series__c());
			cust.setEoiBankName(enqqty.getEoiBankName());
			cust.setIsReferredByChannelPartner(enqqty.getIsReferredByChannelPartner());
			cust.setIsReferredByChannelPartnerFlag(enqqty.getIsReferredByChannelPartnerFlag());
			cust.setLastModifiedDate(enqqty.getLastModifiedDate());
			cust.setMicRChequeNoNEFTRTGS(enqqty.getMicRChequeNoNEFTRTGS());
			cust.setName(enqqty.getName());
			cust.setOtherChannelPartner(enqqty.getOtherChannelPartner());
			cust.setPreferred_Unit__c(enqqty.getPreferred_Unit__c());
			cust.setPriority_no__c(enqqty.getPriority_no__c());
			cust.setProject(enqqty.getProject());
			cust.setPurpose(enqqty.getPurpose());
			cust.setRequiredPossesionTimeLine(enqqty.getRequiredPossesionTimeLine());
			cust.setSfid(enqqty.getSfid());
			cust.setSite_Visit_Done__c(enqqty.getSite_Visit_Done__c());
			cust.setSite_visit_requested__c(enqqty.getSite_visit_requested__c());
			cust.setSynchronised(enqqty.getSynchronised());
			cust.setTransaction_Status__c(enqqty.getTransaction_Status__c());
			cust.setTransactionAmount(enqqty.getTransactionAmount());
			cust.setTransactionDate(enqqty.getTransactionDate());
			cust.setTransactionID(enqqty.getTransactionID());
			cust.setTransactionType(enqqty.getTransactionType());
			cust.setWalkInSource(enqqty.getWalkInSource());
			cust.setWalkInSourceDetail(enqqty.getWalkInSourceDetail());
			cust.setSourcing_Managers__c(enqqty.getSourcing_Managers__c());
			cust.setVirtual_meeting_count__c(enqqty.getVirtual_meeting_count__c());
			return cust;
		}		
		return null;
	}
	@Override
	public Token validateMobileAndToken(String mobileNo,String token,String projectSfid) {
		StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT t FROM Token t ")
		.append(" WHERE t.mobileno=:mobileNo and concat(t.type,cast(t.queue as text))=:token and t.isactive='Y' ");
		jpql.append(" AND t.enquiry_18 IN(select CAST(e.id AS text) from Enquiry e inner join e.project ep where ep.sfid=:projectSfid )");
		/*Map<String, Object> params=new HashMap<>();
		params.put("mobileNo", mobileNo);
		params.put("token", token);*/
		Query query=getSession().createQuery(jpql.toString());
		query.setParameter("mobileNo", mobileNo);
		query.setParameter("token", token);
		query.setParameter("projectSfid", projectSfid);
		@SuppressWarnings("unchecked")
		List<Token> list=query.getResultList();
		if(!CommonUtil.isListEmpty(list)){
			return list.get(0);
		}		
		return null;
	}

	

	@Override
	public String getAdvertisementForEnquiry(String projectSfid,String mediaType,String mediaSubType) {
		StringBuilder nQuery= new StringBuilder(" select sfid from salesforce.vcc1__advertisement__c ad ")
		.append(" where ad.Project__c=:projectSfid ")
		.append(" AND ad.Media_Type__c=:mediaType ")
		.append(" AND ad.Media_Sub_Type__c=:mediaSubType ");		
		Query query=getSession().createNativeQuery(nQuery.toString());
		query.setParameter("projectSfid", projectSfid);
		query.setParameter("mediaType", mediaType);
		query.setParameter("mediaSubType", mediaSubType);
		@SuppressWarnings("unchecked")
		List<String> list=query.getResultList();
		if(!CommonUtil.isListEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public String getAdvertisementForEnquiryWithPhase(String projectSfid,String mediaType,String mediaSubType, String phaseID) {
		StringBuilder nQuery= new StringBuilder(" select sfid from salesforce.vcc1__advertisement__c ad ")
		.append(" where ad.Project__c=:projectSfid ")
		.append(" AND ad.Media_Type__c=:mediaType ")
		.append(" AND ad.Media_Sub_Type__c=:mediaSubType ")
		.append(" AND ad.Project_Phase_Name__c=:phaseID ");
		Query query=getSession().createNativeQuery(nQuery.toString());
		query.setParameter("projectSfid", projectSfid);
		query.setParameter("mediaType", mediaType);
		query.setParameter("mediaSubType", mediaSubType);
		query.setParameter("phaseID", phaseID);
		@SuppressWarnings("unchecked")
		List<String> list=query.getResultList();
		if(!CommonUtil.isListEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	

	@Override
	public int savePaymentDetails(EnquiryDto enq) {
		StringBuilder jpql=new StringBuilder(" Update Enquiry enq ")
		.append(" set enq.branch=:branch, enq.eoiBankName=:bankName, enq.transactionType=:transactionType, ")
		.append(" enq.micRChequeNoNEFTRTGS=:chequeNo, enq.transactionDate=:transactionDate, ")
		.append(" enq.EOI_Tower_Series__c=:EOI_Tower_Series__c, enq.EOI_Preferred_Floor_Band__c=:EOI_Preferred_Floor_Band__c, ")
		.append(" enq.EOI_Remarks__c=:EOI_Remarks__c,  enq.date_of_eoi__c=:date_of_eoi__c ,")
		.append(" enq.preferred_Unit__c=:preferred_Unit__c,enq.desiredUnitType=:desiredUnitType,")
		.append(" enq.transactionAmount=:transactionAmount, enq.transactionID=:transactionID ,enq.eoi_enquiry__c=:eoi_enquiry__c")
		.append(" where enq.enquiryId=:enquiryId ");
		Query query=getSession().createQuery(jpql.toString());
		query.setParameter("branch", enq.getBranch());
		query.setParameter("bankName", enq.getEoiBankName());
		query.setParameter("transactionType", enq.getTransactionType());
		
		if("NEFT".equals(enq.getTransactionType()))
			query.setParameter("chequeNo", enq.getNefttransactionID());
		else
	    	query.setParameter("chequeNo", enq.getMicRChequeNoNEFTRTGS());
		
		query.setParameter("transactionDate", enq.getTransactionDate());
		query.setParameter("transactionAmount", enq.getTransactionAmount());
		query.setParameter("transactionID", enq.getTransactionID());
		query.setParameter("enquiryId",enq.getEnquiryId());
		query.setParameter("desiredUnitType",enq.getEnquiryReport().getDesiredUnitType());
		
		query.setParameter("EOI_Tower_Series__c",enq.getEOI_Tower_Series__c());
		query.setParameter("EOI_Preferred_Floor_Band__c",enq.getEOI_Preferred_Floor_Band__c());
		//query.setParameter("Transactionstatus",enq.getTransaction_Status__c());
		query.setParameter("EOI_Remarks__c",enq.getEOI_Remarks__c());
		query.setParameter("date_of_eoi__c",new Date());
		query.setParameter("preferred_Unit__c",enq.getPreferred_Unit__c());
		query.setParameter("eoi_enquiry__c",true);
		
		
		int i=query.executeUpdate();
		return i;
	}

	@Override
	public List<Enquiry> getEnquiriesByMobileNoAndProjectEOI(String countryCode,String mobileNo, String projectSfid) {

		StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT e FROM Enquiry e ")
		.append(" INNER JOIN FETCH e.contact c ")
		.append(" LEFT JOIN FETCH c.contactReport ccr ")
		.append(" LEFT JOIN FETCH e.project p ")
		//.append(" LEFT JOIN FETCH e.contactId cs ")
		.append(" LEFT JOIN FETCH e.channelPartner cp ")
		.append(" LEFT JOIN FETCH e.brokerContact bc ")
		.append(" LEFT JOIN FETCH e.enquiryReport er ");
		
		if(mobileNo.matches("[0-9]+")) {
			jpql.append(" WHERE c.mobile=:mobile ")
			.append(" AND c.countryCode=:countryCode ");
		}
		else
			jpql.append(" WHERE e.priority_no__c=:Priority_No__c ");
		
		jpql.append(" AND p.sfid=:projectSfid ")
		.append(" AND e.eoi_enquiry__c=:eoi_enquiry__c ");
		Map<String, Object> params=new HashMap<>();
	     
		if(mobileNo.matches("[0-9]+")) {
	    	  params.put("countryCode",countryCode);
	  		  params.put("mobile",mobileNo);
	      }
	      else
	      {
	    	  params.put("Priority_No__c", mobileNo);
	      }
		
		
		params.put("projectSfid", projectSfid);
		params.put("eoi_enquiry__c", true);
		
		return getEntities(jpql.toString(), params);
	
	}

	@Override
	public List<Enquiry> getEnquiriesByCPAppWithParam(String custname, String countryCode,String mobileno, String projectsfid,
			String emailid) {
		StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT e FROM Enquiry e ")
		.append(" INNER JOIN FETCH e.contactId c ")
		.append(" LEFT JOIN FETCH c.contactReport ccr ")
		.append(" LEFT JOIN FETCH e.project p ")
		.append(" LEFT JOIN FETCH e.channelPartner cp ")
		.append(" LEFT JOIN FETCH e.brokerContact bc ")
		.append(" LEFT JOIN FETCH e.enquiryReport er ")
		.append(" WHERE c.mobile=:mobile ")
		.append(" AND c.countryCode=:countryCode ")
		//.append(" AND e.eoi_enquiry__c=:eoi_enquiry__c ")
		.append(" AND p.sfid=:projectSfid ");
		Map<String, Object> params=new HashMap<>();
		/*String code=mobileno.substring(0,3);
		String mobile=mobileno.substring(3);*/
		params.put("countryCode",countryCode);
		params.put("mobile",mobileno);
		/*params.put("mobileNo",mobile);*/
		params.put("projectSfid", projectsfid);
		//params.put("eoi_enquiry__c", false);
		
		return getEntities(jpql.toString(), params);
	}

	@Override
	public List<Enquiry> getEnquiriesForAffiliateSalesPPortalService(String countryCode, String mobileNo,
			String projectSfid) {

		StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT e FROM Enquiry e ")
		.append(" INNER JOIN FETCH e.contactId c ")
		.append(" LEFT JOIN FETCH c.contactReport ccr ")
		.append(" LEFT JOIN FETCH e.project p ")
		.append(" LEFT JOIN FETCH e.channelPartner cp ")
		.append(" LEFT JOIN FETCH e.brokerContact bc ")
		.append(" LEFT JOIN FETCH e.enquiryReport er ")
		.append(" WHERE c.mobile=:mobile ")
		.append(" AND c.countryCode=:countryCode ")
		.append(" AND p.sfid=:projectSfid  ")
		.append(" ORDER BY e.dateOfEnquiry asc");
		Map<String, Object> params=new HashMap<>();
		params.put("countryCode",countryCode);
		params.put("mobile",mobileNo);
		params.put("projectSfid", projectSfid);
		return getEntities(jpql.toString(), params);
	}

	@Override
	public List<Enquiry> getSourcingLeadsEnquiryList(String sourcManagerSFID, String projectSfid, String fromdate,
			String todate) {
		StringBuilder jpql=new StringBuilder();
		jpql.append(" SELECT e FROM Enquiry e ")
		.append(" INNER JOIN FETCH e.contactId c ")
		.append(" LEFT JOIN FETCH e.project p ")
		.append(" WHERE p.sfid=:projectSfid  ")
		.append(" AND e.sourcing_Managers__c=:sourcing_Managers__c ")
		.append(" AND  Date(e.lastModifiedDate) BETWEEN '"+fromdate+"' and '"+todate+"' ORDER BY e.lastModifiedDate asc "); 
		Map<String, Object> params=new HashMap<>();
		params.put("projectSfid",projectSfid);
		params.put("sourcing_Managers__c",sourcManagerSFID);
		return getEntities(jpql.toString(),params);
	}
}
