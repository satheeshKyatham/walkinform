package com.godrej.properties.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.PaymentPlanDueDao;
import com.godrej.properties.model.PaymentPlanDue;
import com.godrej.properties.model.PaymentPlanLineItem;
import com.godrej.properties.service.PaymentPlanDueService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
		setDto.setPayplan_milestones(data.getPayplan_milestones());
		Timestamp createdTimestamp = new Timestamp(System.currentTimeMillis());
		Timestamp updatedTimestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(createdTimestamp);
        setDto.setCreated(createdTimestamp);
        setDto.setUpdated(updatedTimestamp);
		setDto=paymentPlanDueDao.insertPaymentDue(setDto);
		return setDto;
	}

	/*@Override
	public List<PaymentPlanDue> getPaymentDueList(String project_sfid,String tower_sfid,String payment_plan_sfid) {
		
		return paymentPlanDueDao.getPaymentDueListQuery(project_sfid,tower_sfid,payment_plan_sfid);
	}*/
	@Override
	public List<PaymentPlanLineItem> getPaymentDueList(String project_sfid,String tower_sfid,String payment_plan_sfid) {
		List<PaymentPlanLineItem> pplineitem = new ArrayList<PaymentPlanLineItem>();
		List<PaymentPlanDue> paymenPlanDue = paymentPlanDueDao.getPaymentDueListQuery(project_sfid,tower_sfid,payment_plan_sfid);
		if(paymenPlanDue.size()>0)
		{
			Object object=null;
			JsonArray arrayObj=null;
			JsonParser jsonParser=new JsonParser();
			object=jsonParser.parse(paymenPlanDue.get(0).getPayplan_milestones());
			arrayObj=(JsonArray) object;
			System.out.println("Array : "+arrayObj);
			if(arrayObj.size()>0)
			{
				
				for(int i=0;i<arrayObj.size();i++) {
					PaymentPlanLineItem ppLine = new PaymentPlanLineItem();
					JsonObject obj =  (JsonObject) arrayObj.get(i);
					ppLine.setId(obj.get("id").getAsInt());
					ppLine.setIscompleted("Y");
					ppLine.setSfid(obj.get("sfid").getAsString());
					ppLine.setMilestone(obj.get("name").getAsString());
					ppLine.setPer(obj.get("per").getAsString());
					ppLine.setIscompleted(obj.get("iscompleted").getAsString());
					ppLine.setAmount(obj.get("amount").getAsString());
					ppLine.setOrder(obj.get("order").getAsDouble());
					System.out.println("ID : "+obj.get("id").getAsInt());
					System.out.println("sfid : "+obj.get("sfid").getAsString());
					pplineitem.add(ppLine);
				}
			}
		}
		
		return pplineitem;
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
