package com.godrej.properties.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.OTPAdminLogDao;
import com.godrej.properties.model.OTP;
import com.godrej.properties.model.OTPAdminLog;
import com.godrej.properties.service.GetOTPService;
import com.godrej.properties.service.OtpService;

@Service("getOTPService")
@Transactional
public class GetOTPServiceImpl implements GetOTPService{
	 
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private OTPAdminLogDao oTPAdminLogDao;
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String adminOTP(String projectsfid, String loggedinuserid, String otprequesterid, String mobileno) {

		if (projectsfid != null && !projectsfid.equals("")
		 && loggedinuserid != null && !loggedinuserid.equals("")
		 && otprequesterid != null && !otprequesterid.equals("")
		 && mobileno != null && !mobileno.equals("")) {
			
			List<OTPAdminLog> otpLog=new ArrayList<>();
			
			OTP otpDtl = otpService.getOtp(mobileno);
			if(otpDtl.getOtp_id()!=null) {
				String OTP = otpDtl.getOtp();
				
				OTPAdminLog logVal= new OTPAdminLog();
				
				logVal.setAdmin_userid(Integer.parseInt(loggedinuserid));
				logVal.setCreateddate(new Timestamp(System.currentTimeMillis()));
				logVal.setMobileno(mobileno);
				logVal.setNv_otp_log_id(Integer.parseInt(otpDtl.getOtp_id()));
				logVal.setOtp(otpDtl.getOtp());
				logVal.setProjectsfid(projectsfid);
				logVal.setRequester_userid(Integer.parseInt(otprequesterid));
				logVal.setIsactive("Y");
				
				otpLog.add(logVal);
				
				boolean isInserted = oTPAdminLogDao.insertAdminOTPLog(otpLog);
				
				if (isInserted) {
					String response = "{\"otp\":"+OTP+",\"status\":\"STATUS_OK\",\"error_msg\":\"Record Founded\",\"error_id\":null}";
					return response;
				} else {
					String response = "{\"otp\":null,\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Error inserting data into table\",\"error_id\":\"ADMINOTP_ER1003\"}";
					return response;
				}
			} else {
				String response = "{\"otp\":null,\"status\":\"STATUS_NOTOK\",\"error_msg\":\"No OTP Created\",\"error_id\":\"ADMINOTP_ER1004\"}";
				return response;
			}
			
		}  else {
			String response = "{\"otp\":null,\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\",\"error_id\":\"ADMINOTP_ER1005\"}";
			return response;
		}
		
		
		/*GsonBuilder gsonBuilder = new GsonBuilder();
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
		}*/
		
		
	}
	
}