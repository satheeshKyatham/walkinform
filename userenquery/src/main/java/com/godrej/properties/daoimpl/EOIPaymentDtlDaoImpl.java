package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.EOIPaymentDtlDao;
import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.model.EOIPaymentModel;

@SuppressWarnings("unchecked")
@Repository("eOIpaymentDtlDao")
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
		/*Query q = session.createNativeQuery("select eoiPay.gpl_cs_eoi_payment_details_id,eoiPay.project_sfid,eoiPay.userid,eoiPay.payment_type,eoiPay.bank_name,eoiPay.branch,eoiPay.isactive,eoiPay.enq_sfid,req.name "
				+ " ,eoiPay.transaction_id,eoiPay.transaction_date,eoiPay.transaction_amount,eoiPay.description,eoiPay.total_amount "
				+ " ,eoiPay.gpl_cs_balance_details_id,eoiPay.pan_attach,cheque_attach,user_email,eoiPay.user_name,eoiPay.project_name,eoiPay.isfromcp "
				+ " from salesforce.gpl_cs_eoi_payment_details eoiPay "
				+ " inner join salesforce.propstrength__request__c req on(eoiPay.enq_sfid=req.sfid) "+whereCondition+" ", EOIPaymentModel.class);
		//order by b.id
		list = (List<EOIPaymentModel>)q.getResultList();
		
		Log.info("Final Size::{}",list.size());
		if (list.size() > 0) {
			return null;
		}
		return null;*/
		
		List<Object[]> results = session.createQuery("select eoiPay.id,eoiPay.project_sfid,eoiPay.userid,eoiPay.payment_type,eoiPay.bank_name,eoiPay.branch,eoiPay.isactive,eoiPay.enq_sfid,req.name "
				+ " ,eoiPay.transaction_id,eoiPay.transaction_date,eoiPay.transaction_amount,eoiPay.description,eoiPay.total_amount "
				+ " ,eoiPay.gpl_cs_balance_details_id,eoiPay.pan_attach,eoiPay.cheque_attach,eoiPay.user_email,eoiPay.user_name,eoiPay.project_name,eoiPay.isfromcp "
				//+ " ,c.name as customername,c.mobile__c "
				+ "from EOIPaymentDtl eoiPay "
				+ " inner join Enquiry req on(eoiPay.enq_sfid=req.sfid) "
				//+ " inner join Contact c on(req.contactId=c.channelPartner) "
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
			//dtl.setGpl_cs_balance_details_id(Integer.parseInt(result[13].toString()));
			dtl.setPan_attach(result[15].toString());
			dtl.setCheque_attach(result[16].toString());
			dtl.setUser_email(result[17].toString());
			dtl.setUser_name(result[18].toString());
			dtl.setName(result[8].toString());
			dtl.setProject_sfid(result[1].toString());
			//dtl.setCustomerName(result[21].toString());
			//dtl.setMobileNo(result[22].toString());
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
	
}