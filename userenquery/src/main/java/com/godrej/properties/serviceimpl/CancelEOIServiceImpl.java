package com.godrej.properties.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.CancelEOIDao;
import com.godrej.properties.dao.EOIPaymentDtlDao;
import com.godrej.properties.dao.EOIPreferenceDtlDao;
import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.model.EOIPreferenceDtl;
import com.godrej.properties.service.CancelEOIService;
import com.godrej.properties.service.InventoryService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service("cancelEOIService")
@Transactional
public class CancelEOIServiceImpl implements CancelEOIService{
	
	@Autowired
	private EOIPreferenceDtlDao dao;
	
	@Autowired
	private EOIPaymentDtlDao daoPyt;
	
	@Autowired
	private CancelEOIDao cancelEOIDao;
	
	@Autowired
	private InventoryService inventoryService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String deleteEOI(String preferenceJson, String paymentJson, String unitsfidOldArray, String userid, String enq_sfid, String project_sfid, String username, String enqid) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		int useridInt = Integer.parseInt(userid);
		
		String str=preferenceJson;
		  
		Object object=null;
		JsonArray arrayObj=null;
		JsonParser jsonParser=new JsonParser();
		object=jsonParser.parse(str);
		arrayObj=(JsonArray) object;
		
		List<EOIPreferenceDtl> charges1=new ArrayList<>();
		
		if(arrayObj!=null && arrayObj.size()>0) {
			
			for(int i=0;i<arrayObj.size();i++) {
				
				JsonObject jobj = new Gson().fromJson(arrayObj.get(i), JsonObject.class);
				
				EOIPreferenceDtl ecData1= new EOIPreferenceDtl();
				ecData1= gson.fromJson(arrayObj.get(i), EOIPreferenceDtl.class);
				
				if (jobj.get("rowid") != null && !jobj.get("rowid").getAsString().equals("")) 
				{
					ecData1.setEnq_sfid(enq_sfid);
					ecData1.setProject_sfid(project_sfid);
					ecData1.setUpdatedby(userid);
					ecData1.setIsactive("Y");
					charges1.add(ecData1);
				}  else {
					String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"CANCEL_EOI_ER1001\"}";
					return response;
				}
			}
			
			boolean isUpdated = dao.inactiveEOIPreference(charges1);
			
			if (isUpdated) {
				if (!unitsfidOldArray.equals("")) {	
					 inventoryService.releaseEOIHoldInventory (project_sfid, unitsfidOldArray, userid, username);
				 }
			} else {
				String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not updated on portal\",\"error_id\":\"CANCEL_EOI_ER1002\"}";
				return response;
			}
		} else {
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"CANCEL_EOI_ER1003\"}";
			return response;
		}
		
		
		GsonBuilder gsonBuilderPyt = new GsonBuilder();
		Gson gsonPyt = gsonBuilder.create();
		
		//int useridInt = Integer.parseInt(userid);
		
		String strPyt=paymentJson;
		  
		Object objectPyt=null;
		JsonArray arrayObjPyt=null;
		JsonParser jsonParserPyt=new JsonParser();
		objectPyt=jsonParserPyt.parse(strPyt);
		arrayObjPyt=(JsonArray) objectPyt;
		
		List<EOIPaymentDtl> chargesPyt=new ArrayList<>();
		
		if(arrayObjPyt!=null && arrayObjPyt.size()>0) {
			
			for(int i=0;i<arrayObjPyt.size();i++) {
				
				JsonObject jobj = new Gson().fromJson(arrayObjPyt.get(i), JsonObject.class);
				
				EOIPaymentDtl ecData1= new EOIPaymentDtl();
				ecData1= gsonPyt.fromJson(arrayObjPyt.get(i), EOIPaymentDtl.class);
				
				if (jobj.get("rowid") != null && !jobj.get("rowid").getAsString().equals("")) {
					ecData1.setEnq_sfid(enq_sfid);
					ecData1.setProject_sfid(project_sfid);
					chargesPyt.add(ecData1);
				}  else {
					String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"CANCEL_EOI_PAYMENT_ER1001\"}";
					return response;
				}
			}
			boolean isUpdated = daoPyt.inactiveEOIPayment(chargesPyt);
			boolean isEOIFlagUpdated = cancelEOIDao.updateEOI(enq_sfid, project_sfid, enqid);
			
			if (isUpdated) {
				if(isEOIFlagUpdated) {
					String response = "{\"status\":\"STATUS_OK\",\"error_msg\":\"Successfully deleted\",\"error_id\":null}";
					return response;
				} else {
					String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not updated on portal\",\"error_id\":\"CANCEL_EOI_PAYMENT_ER1004\"}";
					return response;
				}
			} else {
				String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not updated on portal\",\"error_id\":\"CANCEL_EOI_PAYMENT_ER1002\"}";
				return response;
			}
		} else {
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"CANCEL_EOI_PAYMENT_ER1003\"}";
			return response;
		}
	}
}