package com.godrej.properties.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.EOIPaymentDtlDao;
import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.service.EOIPaymentDtlService;
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
}