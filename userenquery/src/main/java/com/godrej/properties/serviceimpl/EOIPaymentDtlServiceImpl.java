package com.godrej.properties.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.EOIPaymentDtlDao;
import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.service.EOIPaymentDtlService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service("eOIpaymentDtlService")
@Transactional
public class EOIPaymentDtlServiceImpl implements EOIPaymentDtlService{
	private Logger Log = LoggerFactory.getLogger(getClass());
	@Autowired
	private EOIPaymentDtlDao dao;

	@Override
	public void insertPaymentDtl(List<EOIPaymentDtl> pymtDtl) {
		dao.insertPaymentDtl(pymtDtl);
	}
	
	@Override
	public List<EOIPaymentDtl> getEOIPaymentRecord(String enqSfid) {
		return dao.getEOIPaymentRecord(enqSfid);
	}
	
	@Override
	public void updateEOIForOffer(List<EOIPaymentDtl> charges) {
		  dao.updateEOIForOffer(charges);
	}

	@Override
	public List<EOIPaymentDtl> getCommonEOIPaymentEntrys(String whereCondition) {
		// TODO Auto-generated method stub
		return dao.getCommonEOIPaymentEntrys(whereCondition);
	}

	@Override
	public void paymentEOIApproveReject(String whereCondition) {
		Object object=null;
		JsonArray arrayObj=null;
		JsonParser jsonParser=new JsonParser();
		object=jsonParser.parse(whereCondition);
		arrayObj=(JsonArray) object;
		Log.info("arrayObj : {}",arrayObj);
		if(arrayObj.size()>0)
		{
			StringBuilder queryWhereClause=new StringBuilder("isactive = '"+arrayObj.get(0).getAsJsonObject().get("isactive").getAsString()+"' WHERE id in(");
			for(int i=0;i<arrayObj.size();i++) {
				JsonObject obj =  (JsonObject) arrayObj.get(i);
				obj.get("id").getAsInt();
				queryWhereClause.append(obj.get("id").getAsInt()+",");
			}
			queryWhereClause.setLength(queryWhereClause.length() - 1);
			queryWhereClause.append(")");
			Log.info("Update Query Where Clause : {}",queryWhereClause);
			dao.paymentEOIApproveReject(queryWhereClause.toString());
		}
	}
	
	
	
	
	
	/* Update EOI payment */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String updateEOIPayment(String paymentDtlJson, String userid, String enq_sfid, String project_sfid, String username) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		int useridInt = Integer.parseInt(userid);
		
		String str=paymentDtlJson;
		  
		Object object=null;
		JsonArray arrayObj=null;
		JsonParser jsonParser=new JsonParser();
		object=jsonParser.parse(str);
		arrayObj=(JsonArray) object;
		
		List<EOIPaymentDtl> charges1=new ArrayList<>();
		
		if(arrayObj!=null && arrayObj.size()>0) {
			
			for(int i=0;i<arrayObj.size();i++) {
				
				JsonObject jobj = new Gson().fromJson(arrayObj.get(i), JsonObject.class);
				
				EOIPaymentDtl ecData1= new EOIPaymentDtl();
				ecData1= gson.fromJson(arrayObj.get(i), EOIPaymentDtl.class);
				
				if (jobj.get("rowid") != null && !jobj.get("rowid").getAsString().equals("")){
					ecData1.setEnq_sfid(enq_sfid);
					ecData1.setProject_sfid(project_sfid);
					ecData1.setUpdatedby(userid);
					ecData1.setIsactive("Y");
					charges1.add(ecData1);
				}  else {
					String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"UPDATE_EOI_PAYMENT_ER1001\"}";
					return response;
				}
			}
			boolean isUpdated = dao.updateEOIPayment(charges1);
			
			if (isUpdated) {
				String response = "{\"status\":\"STATUS_OK\",\"error_msg\":\"Successfully submitted\",\"error_id\":null}";
				return response;
			} else {
				String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not updated on portal\",\"error_id\":\"UPDATE_EOI_PAYMENT_ER1002\"}";
				return response;
			}
		} else {
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Data Not Found\",\"error_id\":\"UPDATE_EOI_PAYMENT_ER1003\"}";
			return response;
		}
	}
	/* END Update EOI payment */
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String deleteEOIPayment(String paymentJson, String userid, String enq_sfid, String project_sfid) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		int useridInt = Integer.parseInt(userid);
		
		String str=paymentJson;
		  
		Object object=null;
		JsonArray arrayObj=null;
		JsonParser jsonParser=new JsonParser();
		object=jsonParser.parse(str);
		arrayObj=(JsonArray) object;
		
		List<EOIPaymentDtl> charges1=new ArrayList<>();
		
		if(arrayObj!=null && arrayObj.size()>0) {
			
			for(int i=0;i<arrayObj.size();i++) {
				
				JsonObject jobj = new Gson().fromJson(arrayObj.get(i), JsonObject.class);
				
				EOIPaymentDtl ecData1= new EOIPaymentDtl();
				ecData1= gson.fromJson(arrayObj.get(i), EOIPaymentDtl.class);
				
				if (jobj.get("rowid") != null && !jobj.get("rowid").getAsString().equals("")) {
					ecData1.setEnq_sfid(enq_sfid);
					ecData1.setProject_sfid(project_sfid);
					charges1.add(ecData1);
				}  else {
					String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"DELETE_EOI_PAYMENT_ER1001\"}";
					return response;
				}
			}
			boolean isUpdated = dao.inactiveEOIPayment(charges1);
			
			if (isUpdated) {
				String response = "{\"status\":\"STATUS_OK\",\"error_msg\":\"Successfully deleted\",\"error_id\":null}";
				return response;
			} else {
				String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not updated on portal\",\"error_id\":\"DELETE_EOI_PAYMENT_ER1002\"}";
				return response;
			}
		} else {
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Data Not Found\",\"error_id\":\"DELETE_EOI_PAYMENT_ER1003\"}";
			return response;
		}
	}
	
}