package com.godrej.properties.serviceimpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.RefundInitiateDao;
import com.godrej.properties.model.RefundInitiate;
import com.godrej.properties.service.RefundInitiateService;

@Service("refundInitiateService")
@Transactional
public class RefundInitiateServiceImpl implements RefundInitiateService{

	@Autowired
	public RefundInitiateDao refundInitiateDao;
	
	@Override
	public RefundInitiate insertRefundInitiateRequest(RefundInitiate refIn) {
		String uuid = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
		String uuid16digits = uuid.substring(uuid.length() - 16);
		refIn.setTrx_id(uuid16digits);
		refIn.setTrx_id_test(UUID.randomUUID());
		refIn.setRefund_initiated_date(new Timestamp(System.currentTimeMillis()));
		refIn.setRefund_transaction_date(new Timestamp(System.currentTimeMillis()));
		return refundInitiateDao.insertRefundInitiateRequest(refIn);
	}
	@Override
	public List<RefundInitiate> getRefundInitiatedData(String whereCodnition) {
		return refundInitiateDao.getRefundInitiatedData(whereCodnition);
	}
	@Override
	public String updateEOIRefundEnty(String enquiry_Sfid,String trx_no, String whereCodnition) {
		// TODO Auto-generated method stub
		return refundInitiateDao.updateEOIRefundEnty(enquiry_Sfid,trx_no, whereCodnition);
	}
	@Override
	public String approveRejectRefund(Integer id, String updateValues, String whereCodnition) {
		return refundInitiateDao.approveRejectRefund(id, updateValues, whereCodnition);
	}

	
}
