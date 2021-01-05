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
	
		List<RefundInitiate> list =session.createQuery(" from RefundInitiate "+whereCodnition+"").list();
		if(list.size()>0)
		{
			return list;
		}
		return list;
	}

	@Override
	public String updateEOIRefundEnty(String enquiry_Sfid,String trx_no, String whereCodnition) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE EOIPaymentDtl SET refund_trx_no ='"+trx_no+"'  WHERE enq_sfid = '"+enquiry_Sfid+"' and refund_trx_no is null and isactive='Y' ");
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
