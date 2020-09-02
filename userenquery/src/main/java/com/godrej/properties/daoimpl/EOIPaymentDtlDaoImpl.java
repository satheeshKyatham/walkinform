package com.godrej.properties.daoimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.EOIPaymentDtlDao;
import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.model.EOIPaymentModel;
import com.godrej.properties.model.EOIPreferenceDtl;

@SuppressWarnings("unchecked")
@Repository("eOIpaymentDtlDao")
@Transactional
public class EOIPaymentDtlDaoImpl extends AbstractDao<Integer, EOIPaymentDtl> implements EOIPaymentDtlDao {
	private Logger Log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertPaymentDtl(List<EOIPaymentDtl> pymtDtl) {
		batchPersist(pymtDtl);
	}	
	
	
	
	
	@Override
	public List<EOIPaymentDtl> getEOIPaymentRecord(String enqSfid) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<EOIPaymentDtl> authors=null;
		
		Query q = session.createNativeQuery("SELECT * FROM salesforce.gpl_cs_eoi_payment_details "
				//+ " where enq_sfid = '"+enqSfid+"' and isactive = 'Y' order by gpl_cs_eoi_payment_details_id ", EOIPaymentDtl.class);
				+ " where enq_sfid = '"+enqSfid+"' order by gpl_cs_eoi_payment_details_id ", EOIPaymentDtl.class);
		//order by b.id
		authors = q.getResultList();
		
		Log.info("Final Size::{}",authors.size());
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
	
	
	@Override
	public void updateEOIForOffer(List<EOIPaymentDtl> charges) {
		for (int i = 0; i <charges.size(); i++) {
			Session session = this.sessionFactory.getCurrentSession();
			
			Log.info("Test :::{} ", charges.get(i).getId());
			
			charges.get(i).getDescription();
			Query query = session.createQuery("UPDATE EOIPaymentDtl SET isactive = 'O' WHERE id = '"+charges.get(i).getId()+"' ");
			
			query.executeUpdate();
		}
	}




	@Override
	public List<EOIPaymentDtl> getCommonEOIPaymentEntrys(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();
		//List<EOIPaymentDtl> list = session.createQuery( "  FROM EOIPaymentDtl "+whereCondition+"").list();
		List<EOIPaymentDtl> list=new ArrayList<EOIPaymentDtl>();
		//List<EOIPaymentModel> listNew=new ArrayList<EOIPaymentModel>();
		/*Query q = session.createNativeQuery("select eoiPay.gpl_cs_eoi_payment_details_id,eoiPay.project_sfid,eoiPay.userid,eoiPay.payment_type,eoiPay.bank_name,eoiPay.branch,eoiPay.isactive,eoiPay.enq_sfid,req.name "
				+ " ,eoiPay.transaction_id,eoiPay.transaction_date,eoiPay.transaction_amount,eoiPay.description,eoiPay.total_amount "
				+ " ,eoiPay.gpl_cs_balance_details_id,eoiPay.pan_attach,cheque_attach,user_email,eoiPay.user_name,eoiPay.project_name,eoiPay.isfromcp "
				+ " from salesforce.gpl_cs_eoi_payment_details eoiPay "
				+ " inner join salesforce.propstrength__request__c req on(eoiPay.enq_sfid=req.sfid) "+whereCondition+" ");*/
		
		Query q = session.createNativeQuery("select eoiPay.gpl_cs_eoi_payment_details_id,eoiPay.project_sfid,eoiPay.userid,eoiPay.payment_type,eoiPay.bank_name,eoiPay.branch,eoiPay.isactive,eoiPay.enq_sfid,req.name "
				+ " ,eoiPay.transaction_id,eoiPay.transaction_date,eoiPay.transaction_amount,eoiPay.description,eoiPay.total_amount "
				+ " ,eoiPay.gpl_cs_balance_details_id,eoiPay.pan_attach,cheque_attach,user_email,eoiPay.user_name,eoiPay.project_name,eoiPay.isfromcp "
				+ " ,c.name as customername,c.mobile__c,eoiPay.created,eoiPay.updated,req.Closing_Manager_Name__c "
				+ " from salesforce.gpl_cs_eoi_payment_details eoiPay "
				+ " inner join salesforce.propstrength__request__c req on(eoiPay.enq_sfid=req.sfid) "
				+ " inner join salesforce.contact c on(req.PropStrength__Primary_Contact__c=c.sfid)"+whereCondition+" ");
		
		
		//order by b.id
		List<Object[]> listObject = q.getResultList();
		
		Log.info("Final Size::{}",list.size());
		/*if (list.size() > 0) {
			return null;
		}*/
		//return null;
		
		/*List<Object[]> results = session.createQuery("select eoiPay.id,eoiPay.project_sfid,eoiPay.userid,eoiPay.payment_type,eoiPay.bank_name,eoiPay.branch,eoiPay.isactive,eoiPay.enq_sfid,req.name "
				+ " ,eoiPay.transaction_id,eoiPay.transaction_date,eoiPay.transaction_amount,eoiPay.description,eoiPay.total_amount "
				+ " ,eoiPay.gpl_cs_balance_details_id,eoiPay.pan_attach,eoiPay.cheque_attach,eoiPay.user_email,eoiPay.user_name,eoiPay.project_name,eoiPay.isfromcp "
				+ "from EOIPaymentDtl eoiPay "
				+ " inner join Enquiry req on(eoiPay.enq_sfid=req.sfid) "
				+ ""+whereCondition+" ").getResultList();

		for (Object[] result : results) {
			EOIPaymentDtl dtl = new EOIPaymentDtl();
			dtl.setId(Integer.parseInt(result[0].toString()));
			dtl.setUserid(Integer.parseInt(result[2].toString()));
			dtl.setPayment_type(result[3].toString());
			dtl.setBank_name(result[4].toString());
			dtl.setBranch(result[5].toString());
			dtl.setEnq_sfid(result[7].toString());
			dtl.setTransaction_id(result[9].toString());
			dtl.setTransaction_date(result[10].toString());
			dtl.setTransaction_amount(result[11].toString());
			dtl.setDescription(result[12].toString());
			dtl.setTotal_amount(result[13].toString());
			dtl.setPan_attach(result[15].toString());
			dtl.setCheque_attach(result[16].toString());
			dtl.setUser_email(result[17].toString());
			dtl.setUser_name(result[18].toString());
			dtl.setName(result[8].toString());
			dtl.setProject_sfid(result[1].toString());
			list.add(dtl);
		}*/
		
		for (Object[] result : listObject) {
			EOIPaymentDtl dtl = new EOIPaymentDtl();
			dtl.setId(Integer.parseInt(result[0].toString()));
			dtl.setUserid(Integer.parseInt(result[2].toString()));
			dtl.setPayment_type(result[3].toString());
			dtl.setBank_name(result[4].toString());
			dtl.setBranch(result[5].toString());
			dtl.setEnq_sfid(result[7].toString());
			dtl.setTransaction_id(result[9].toString());
			dtl.setTransaction_date(result[10].toString());
			dtl.setTransaction_amount(result[11].toString());
			dtl.setDescription(result[12].toString());
			dtl.setTotal_amount(result[13].toString());
			dtl.setPan_attach(result[15].toString());
			dtl.setCheque_attach(result[16].toString());
			//dtl.setUser_email(result[17].toString());
			if(result[24]!=null)
				dtl.setUser_name(result[24].toString());
			else
				dtl.setUser_name("");
			dtl.setName(result[8].toString());
			dtl.setCustomerName(result[21].toString());
			dtl.setMobileNo(result[22].toString());
			dtl.setProject_sfid(result[1].toString());
			dtl.setCreatedDate((Timestamp) result[23]);
			dtl.setUpdatedDate((Timestamp) result[24]);
			list.add(dtl);
		}
		
		return list;
	}




	@Override
	public void paymentEOIApproveReject(String whereCondition) {
			Session session = this.sessionFactory.getCurrentSession();
//			Query query = session.createQuery("UPDATE EOIPaymentDtl SET isactive = 'O' WHERE id = '"+charges.get(i).getId()+"' ");
			Query query = session.createQuery("UPDATE EOIPaymentDtl SET  "+whereCondition+"");
			query.executeUpdate();
		}




	@Override
	public void insertOnePaymentDtl(EOIPaymentDtl pymtDtl) {
		persist(pymtDtl);
		
	}
	
	
	@Override
	public Boolean updateEOIPayment(List<EOIPaymentDtl> eoiReq) {
		try {
			for (int i = 0; i <eoiReq.size(); i++) {
				Session session = this.sessionFactory.getCurrentSession();
				
				String isactiveStatus = "";
				
				if (eoiReq.get(i).getIsactive() != null && eoiReq.get(i).getIsactive().equals("R")) {
					isactiveStatus = " , isactive = 'N' "; 
				} else {
					isactiveStatus = " "; 
				}
				
				Query query = session.createQuery("UPDATE EOIPaymentDtl  "
						+ " SET "
								+ " payment_type = '"+eoiReq.get(i).getPayment_type()+"' "
								+ " , bank_name = '"+eoiReq.get(i).getBank_name()+"' "
								+ " , transaction_id = '"+eoiReq.get(i).getTransaction_id()+"' "
								+ " , transaction_date = '"+eoiReq.get(i).getTransaction_date()+"' "
								+ " , transaction_amount = '"+eoiReq.get(i).getTransaction_amount()+"' "
								+ " , cheque_attach = '"+eoiReq.get(i).getCheque_attach()+"' "
								+ " , description = '"+eoiReq.get(i).getDescription() +"' "
								+ " , updatedby = '"+eoiReq.get(i).getUpdatedby() +"' "
								+ " , updated = now() " + isactiveStatus

						+ " WHERE id = '"+eoiReq.get(i).getRowid()+"' "
						+ " AND enq_sfid = '"+eoiReq.get(i).getEnq_sfid()+"' "
						+ " AND project_sfid = '"+eoiReq.get(i).getProject_sfid()+"' " );
				
				query.executeUpdate();
			}
			return true;
		} catch (Exception e) {
			Log.info("Update EOI Payment Error:- ",e);
			return false;
		}
	}
	
	
	@Override
	public Boolean inactiveEOIPayment(List<EOIPaymentDtl> eoiReq) {
		//try {
			for (int i = 0; i <eoiReq.size(); i++) {
				Session session = this.sessionFactory.getCurrentSession();
				Query q = session.createQuery("DELETE from EOIPaymentDtl  "
						+ " WHERE id = '"+eoiReq.get(i).getRowid()+"' AND "
						+ " enq_sfid = '"+eoiReq.get(i).getEnq_sfid()+"' AND "
						+ " project_sfid = '"+eoiReq.get(i).getProject_sfid()+"' ");
				//q.setParameter ("id", eoiReq.get(i).getRowid());
				//q.setParameter ("enq_sfid", eoiReq.get(i).getEnq_sfid());
				//q.setParameter ("project_sfid", eoiReq.get(i).getProject_sfid());
				//q.setParameter ("isactive", "Y");
				q.executeUpdate();
			}
			return true;
		/*
		 * } catch (Exception e) { Log.info("Delete EOI Payment Error:- ",e); return
		 * false; }
		 */
	}
	
}