package com.godrej.properties.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.EOIPreferenceDtlDao;
import com.godrej.properties.model.EOIPreferenceDtl;
import com.godrej.properties.service.EOIPreferenceDtlService;
import com.godrej.properties.service.InventoryService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service("eOIPreferenceDtlService")
@Transactional
public class EOIPreferenceDtlServiceImpl implements EOIPreferenceDtlService{
	
	//@Autowired
	//private EOIPreferenceDtlService eOIPreferenceDtlService;
	
	@Autowired
	private EOIPreferenceDtlDao dao;

	
	@Autowired
	private InventoryService inventoryService;
	
	
	@Override
	public void insertEOI(List<EOIPreferenceDtl> eoiDtl) {
		dao.insertEOI(eoiDtl);
	}
	
	@Override
	public List<EOIPreferenceDtl> getEOIPreferenceRecord(String enqSfid) {
		return dao.getEOIPreferenceRecord(enqSfid);
	}
	
	/*
	 * @Override public Boolean updateEOIPreference(List<EOIPreferenceDtl> eoiReq) {
	 * return dao.updateEOIPreference(eoiReq); }
	 */
	
	
	
	
	
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String updateEOIPreference(String preferenceJson, String userid, String enq_sfid, String project_sfid, String unitsfidOldArray, String username, String newUnitsfidArray) {

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
				
				if (jobj.get("rowid") != null && !jobj.get("rowid").getAsString().equals("")
						 && jobj.get("tower_id") != null && !jobj.get("tower_id").getAsString().equals("")) 
				{
					ecData1.setEnq_sfid(enq_sfid);
					ecData1.setProject_sfid(project_sfid);
					ecData1.setUpdatedby(userid);
					ecData1.setIsactive("Y");
					charges1.add(ecData1);
				}  else {
					String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"UPDATE_EOI_ER1001\"}";
					return response;
				}
			}
			boolean isUpdated = dao.updateEOIPreference(charges1);
			
			if (isUpdated) {
				 if (!newUnitsfidArray.equals("")) {
					 String holdmsg = "eoi_block";
					 String reasonInput = "EOI EDIT";
					 inventoryService.holdInventoryAdmin(project_sfid, userid, newUnitsfidArray, holdmsg, reasonInput, username, useridInt, enq_sfid);
				 }
				
				 if (!unitsfidOldArray.equals("")) {	
					 inventoryService.releaseEOIHoldInventory (project_sfid, unitsfidOldArray, userid, username);
				 }
				 
				String response = "{\"status\":\"STATUS_OK\",\"error_msg\":\"Successfully submitted\",\"error_id\":null}";
				return response;
			} else {
				String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not updated on portal\",\"error_id\":\"UPDATE_EOI_ER1002\"}";
				return response;
			}
		} else {
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Data Not Found\",\"error_id\":\"UPDATE_EOI_ER1003\"}";
			return response;
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String deleteEOIPreference(String preferenceJson, String userid, String enq_sfid, String project_sfid, String unitsfidOldArray, String username) {

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
					String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"DELETE_EOI_ER1001\"}";
					return response;
				}
			}
			boolean isUpdated = dao.inactiveEOIPreference(charges1);
			
			if (isUpdated) {
				if (!unitsfidOldArray.equals("")) {	
					 inventoryService.releaseEOIHoldInventory (project_sfid, unitsfidOldArray, userid, username);
				 }
				 
				String response = "{\"status\":\"STATUS_OK\",\"error_msg\":\"Successfully submitted\",\"error_id\":null}";
				return response;
			} else {
				String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Details is not updated on portal\",\"error_id\":\"DELETE_EOI_ER1002\"}";
				return response;
			}
		} else {
			String response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Data Not Found\",\"error_id\":\"DELETE_EOI_ER1003\"}";
			return response;
		}
	}
}