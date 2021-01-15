package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.RefundInitiateDao;
import com.godrej.properties.model.RefundInitiate;

@Repository("refundInitiateDao")
public class RefundInitiateDaoImpl extends AbstractDao<Integer, RefundInitiate> implements RefundInitiateDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public RefundInitiate insertRefundInitiateRequest(RefundInitiate refIn) {
		persist(refIn);
		return refIn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RefundInitiate> getRefundInitiatedData(String whereCodnition) {
		Session session = this.sessionFactory.getCurrentSession();	
	
		/*List<RefundInitiate> list =session.createQuery(" from RefundInitiate "+whereCodnition+"").list();
		if(list.size()>0)
		{
			return list;
		}
		return list;*/
		
		
		List<RefundInitiate> list=null;
		
		Query q = session.createNativeQuery("SELECT refund.gpl_refund_initiate_id, refund.trx_id,refund.ac_holder_name,refund.bank_name,refund.branch_name,refund.account_no,refund.ifsc_code,refund.account_type"
				+ ",refund.cancelled_check_path,refund.cancelled_check_file_name,refund.reason_for_cancel_refund,refund.description,refund.enquiry_sfid,refund.refund_initiated_date,refund.refund_updated_by"
				+ ",refund.refund_initiated_by,refund.gpl_cs_eoi_payment_details_id,refund.refund_updated_date,refund.refund_amount,refund.refund_transaction_date,refund.trx_id_test"
				+ ",refund.project_sfid,refund.refund_status,refund.neft_rtgs_utr_no,refund.approval_refund_status,refund.refund_comments"
				+ ",req.name as enq_name,con.name as customer_name,usr.user_name as cm_name"
				+ " FROM salesforce.gpl_refund_initiate refund"
				+ " inner join salesforce.propstrength__request__c req on(refund.enquiry_sfid=req.sfid)  "
				+ " inner join salesforce.contact con on(req.propstrength__primary_contact__c=con.sfid)  "
				+ " inner join salesforce.mst_user usr on(refund.refund_initiated_by=usr.user_id) " +whereCodnition+" ", RefundInitiate.class);

		list = q.getResultList();
		
		if (list.size() > 0)
			return list;
		return list;
	}

	@Override
	public String updateEOIRefundEnty(String enquiry_Sfid,String trx_no, String whereCodnition) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE EOIPaymentDtl SET refund_trx_no ='"+trx_no+"'  WHERE enq_sfid = '"+enquiry_Sfid+"' and (refund_trx_no is null or refund_trx_no='0') and isactive='Y' ");
		query.executeUpdate();
		return null;
	}

	@Override
	public String approveRejectRefund(Integer id, String updateValues, String whereCodnition) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE RefundInitiate SET "+updateValues+"  "+whereCodnition+" ");
		query.executeUpdate();
		return null;
	}

}
