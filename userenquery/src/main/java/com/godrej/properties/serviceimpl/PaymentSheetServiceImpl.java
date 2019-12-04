package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.PaymentSheetDao;
import com.godrej.properties.model.PaymentSheet;
import com.godrej.properties.service.PaymentSheetService;

@Service("paymentSheetService")
@Transactional
public class PaymentSheetServiceImpl implements PaymentSheetService{

	@Autowired
	PaymentSheetDao paymentSheetDao;
	
	@Override
	public List<PaymentSheet> getpaymentsheet(String project_c) {
		// TODO Auto-generated method stub
		return paymentSheetDao.getpaymentsheet(project_c);
	}

	 

}
