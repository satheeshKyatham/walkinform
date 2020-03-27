package com.godrej.properties.daoimpl;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CCAvenueGatewayRequestDao;
import com.godrej.properties.model.CCAvenueGatewayRequest;
import com.godrej.properties.model.CCAvenueResponseModel;

@Repository("ccAvenueGatewayRequestDao")
public class CCAvenueGatewayRequestDaoImpl extends AbstractDao<Integer, CCAvenueGatewayRequest> implements CCAvenueGatewayRequestDao{

	private Logger Log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public CCAvenueGatewayRequest insertCCAvenueGatewayRequest(CCAvenueGatewayRequest gatewayRequest) {
		persist(gatewayRequest);
		return gatewayRequest;
	}

	@Override
	public CCAvenueGatewayRequest updateCCAvenueResponse(CCAvenueResponseModel responseModel) {
		Session session = this.sessionFactory.getCurrentSession();	
		try {
			/*Query query = session.createQuery("UPDATE CCAvenueGatewayRequest SET  "//tracking_id="+responseModel.getTracking_id()+",
					+ "bank_ref_no=:bank_ref_no,order_status=:order_status,failure_message=:failure_message,"
					+ "payment_mode=:payment_mode,card_name=:card_name,status_code=:status_code,status_message=:status_message,"
					+ "response_currency=:response_currency,response_amount=:response_amount,vault=:vault,response_code=:response_code,"
					+ "trans_date=:trans_date,updateddate=:updateddate "
					+ "WHERE order_id="+responseModel.getOrder_id());
			//query.setParameter("tracking_id", Long.parseLong(responseModel.getTracking_id()));
			query.setParameter("bank_ref_no", responseModel.getBank_ref_no());
			query.setParameter("order_status", responseModel.getTracking_id());
			query.setParameter("failure_message", responseModel.getTracking_id());
			query.setParameter("payment_mode", responseModel.getTracking_id());
			query.setParameter("card_name", responseModel.getTracking_id());
			query.setParameter("status_code", responseModel.getTracking_id());
			query.setParameter("status_message", responseModel.getTracking_id());
			query.setParameter("response_currency", responseModel.getTracking_id());
			query.setParameter("response_amount", responseModel.getTracking_id());
			query.setParameter("vault", responseModel.getVault());
			query.setParameter("response_code", responseModel.getResponse_code());
			query.setParameter("trans_date", responseModel.getTrans_date());
			query.setParameter("updateddate", responseModel.getUpdateddate());
			query.executeUpdate();*/
			
			Query q = session.createNativeQuery("Update salesforce.gpl_cc_gateway_req_resp  SET "//SET tracking_id='"+String.valueOf(responseModel.getTracking_id())+"',
					+ "bank_ref_no='"+responseModel.getBank_ref_no()+"',order_status='"+responseModel.getOrder_status()+"',failure_message='"+responseModel.getFailure_message()+"'"
					//+ ""
					+ "WHERE order_id='"+responseModel.getOrder_id()+"'");
			q.executeUpdate();
			
		}
		catch (Exception e) {
			Log.info(" updateCCAvenueResponse Error :-",e);
		}
		String ispayment = "N";
		if(responseModel.getOrder_status().equals("Success"))
		{
			ispayment="Y";
		}
		Query q1 = session.createNativeQuery("Update salesforce.gpl_cc_payment_request SET payment_status='"+responseModel.getOrder_status()+"',bank_ref_no='"+responseModel.getBank_ref_no()+"',ispayment_status='"+ispayment+"',update_date=now() where id ="+responseModel.getOrder_id());
		q1.executeUpdate();
		
		return null;
	}

}
