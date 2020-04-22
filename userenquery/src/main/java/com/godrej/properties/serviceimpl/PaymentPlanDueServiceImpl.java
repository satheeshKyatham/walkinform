package com.godrej.properties.serviceimpl;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.PaymentPlanDueDao;
import com.godrej.properties.model.PaymentPlanDue;
import com.godrej.properties.service.PaymentPlanDueService;
@Service("paymentPlanDueService")
@Transactional
public class PaymentPlanDueServiceImpl implements PaymentPlanDueService{
	
	@Autowired
	private PaymentPlanDueDao paymentPlanDueDao;

	@Override
	public PaymentPlanDue addPaymentPlanDue(PaymentPlanDue data) {
		PaymentPlanDue setDto=new PaymentPlanDue();
		setDto.setDues_amount(data.getDues_amount());
		setDto.setIsactive("A");
		setDto.setProject_id(data.getProject_id());
		setDto.setProject_name(data.getProject_name());
		setDto.setPymt_plan_id(data.getPymt_plan_id());
		setDto.setPymt_plan_name(data.getPymt_plan_name());
		setDto.setRegion_id(data.getRegion_id());
		setDto.setRegion_name(data.getRegion_name());
		setDto.setCreatedby("9999");
		setDto.setUpdatedby("9999");
		setDto.setTowerid(data.getTowerid());
		setDto.setTower_name(data.getTower_name());	
		Timestamp createdTimestamp = new Timestamp(System.currentTimeMillis());
		Timestamp updatedTimestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(createdTimestamp);
        setDto.setCreated(createdTimestamp);
        setDto.setUpdated(updatedTimestamp);
		setDto=paymentPlanDueDao.insertPaymentDue(setDto);
		return setDto;
	}

	@Override
	public List<PaymentPlanDue> getPaymentDueList() {
		
		return paymentPlanDueDao.getPaymentDueListQuery();
	}

	@Override
	public PaymentPlanDue updatePaymentDue(PaymentPlanDue data) {
		PaymentPlanDue setDto=new PaymentPlanDue();
		setDto.setId(data.getId());
		setDto.setDues_amount(data.getDues_amount());
		setDto.setIsactive("A");
		setDto.setProject_id(data.getProject_id());
		setDto.setProject_name(data.getProject_name());
		setDto.setPymt_plan_id(data.getPymt_plan_id());
		setDto.setPymt_plan_name(data.getPymt_plan_name());
		setDto.setRegion_id(data.getRegion_id());
		setDto.setRegion_name(data.getRegion_name());
		setDto.setCreatedby("9999");
		setDto.setUpdatedby("9999");
		setDto.setTowerid(data.getTowerid());
		setDto.setTower_name(data.getTower_name());	
		Timestamp updatedTimestamp = new Timestamp(System.currentTimeMillis());
        setDto.setUpdated(updatedTimestamp);
		boolean updateDto=paymentPlanDueDao.updatePaymentDueQuery(setDto);
		if(updateDto==true){
			setDto.setInsertStatus("Status_OK");
			return setDto;
		}
		setDto.setInsertStatus("Status_NOTOK");
		return setDto;
		
	}

}
