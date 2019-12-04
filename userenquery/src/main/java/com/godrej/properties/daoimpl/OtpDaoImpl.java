package com.godrej.properties.daoimpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.OtpDao;
import com.godrej.properties.model.OTP;
import com.godrej.properties.model.UserMaster;
import com.godrej.properties.util.OtpGenerate;
import com.godrej.properties.util.SendSMS; 
@Repository("otpDao")
public class OtpDaoImpl extends AbstractDao<Integer, OTP> implements OtpDao {


	@Autowired
	private SessionFactory sessionFactory;
	public OtpDaoImpl() {
		
	}
	
	@Override
	public OTP createOtp(String mobileno) {
		 
		Session session = this.sessionFactory.getCurrentSession();	
		long currentTimestamp = System.currentTimeMillis();
		String otp_str= OtpGenerate.OTP();
		OTP otp= new OTP();
		otp.setMobileno(mobileno);
		otp.setOtp(otp_str);
		otp.setIsactive("A");
		otp.setApp_type("KYC");
		otp.setCreateddate(new Timestamp(currentTimestamp));
		otp.setExpirydate(new Timestamp(new Date(System.currentTimeMillis()+5*60*1000).getTime()));
		
	//	persist(otp);	
		String msg=" is your OTP for verification. OTP is confidential. For security reasons, DO NOT SHARE THIS OTP WITH ANYONE.".replaceAll(" ", "%20");
		
		List<OTP> list =session.createQuery(" from OTP where isactive='A' and mobileno like '%"+mobileno+"'").list();
		if(list.size()>0) {
			
			
			long searchTimestamp = list.get(0).getCreateddate().getTime();// this also gives me back timestamp in 13 digit (1425506040493)

			long difference = Math.abs(currentTimestamp - searchTimestamp);
//223134 is your OTP for verification of your KYC documents . OTP is confidential. For security reasons, DO NOT SHARE THIS OTP WITH ANYONE.
//			 String msg=" is your OTP for verification of your KYC documents . OTP is confidential. For security reasons, DO NOT SHARE THIS OTP WITH ANYONE.".replaceAll(" ", "%20");
				
			if (difference >  5 * 1000) {
				Query query = session.createQuery(" Update OTP set  isactive ='I' where  mobileno like '%"+mobileno+"'");
				query.executeUpdate();
				persist(otp);	
				String status=SendSMS.SMSSend(mobileno,otp_str+msg);
				/* Call for Shree SMS*/
				String statusSreeSMS=SendSMS.ShreeSMSSend(mobileno,otp_str+msg);
				
			}
			else {
				String status=SendSMS.SMSSend(mobileno,list.get(0).getOtp()+msg);
				/* Call for Shree SMS*/
				String statusSreeSMS=SendSMS.ShreeSMSSend(mobileno,otp_str+msg);
				otp=new OTP();
				otp=list.get(0);
			}
			 
		}else {
			
			persist(otp);
			String status=SendSMS.SMSSend(mobileno,otp.getOtp()+msg);
			
		}
		
		return otp;
	}

	@Override
	public OTP getValidOtp(String mobileno, String otp) {
		Session session = this.sessionFactory.getCurrentSession();	
		List<OTP> list =session.createQuery(" from OTP where isactive='A' and mobileno like '%"+mobileno+"' and otp='"+otp+"'").list();
		if(list.size()>0) {
			return list.get(0);
		}
		return new OTP();
	}
	@Override
	public OTP getOtp(String mobileno) {
		Session session = this.sessionFactory.getCurrentSession();	
		List<OTP> list =session.createQuery(" from OTP where isactive='A' and mobileno = '"+mobileno+"' ").list();
		if(list.size()>0) {
			return list.get(0);
		}
		return new OTP();
	}

	 

	@Override
	public UserMaster authenticationDevice(String deviceId) {
		//Session session = this.sessionFactory.getCurrentSession();	
		
		 UserMaster userMaster = new UserMaster();
		 
			/*Query q = session.createNativeQuery("SELECT * FROM salesforce.nv_device_register  where  isactive = 'A'  ");
			Object o = (Object)q.getResultList();
			
		  */
		 
		 userMaster.setMsg("Success");
		 return userMaster;
	}

	@Override
	public OTP createOtpCountry(String countryCode, String mobileno) {

		 
		Session session = this.sessionFactory.getCurrentSession();	
		long currentTimestamp = System.currentTimeMillis();
		String otp_str= OtpGenerate.OTP();
		OTP otp= new OTP();
		otp.setMobileno(mobileno);
		otp.setOtp(otp_str);
		otp.setIsactive("A");
		otp.setApp_type("KYC");
		otp.setCreateddate(new Timestamp(currentTimestamp));
		otp.setExpirydate(new Timestamp(new Date(System.currentTimeMillis()+5*60*1000).getTime()));
		otp.setCountrycode(countryCode);
	//	persist(otp);	
		String msg=" is your OTP for verification. OTP is confidential. For security reasons, DO NOT SHARE THIS OTP WITH ANYONE.".replaceAll(" ", "%20");
		
		List<OTP> list =session.createQuery(" from OTP where isactive='A' and mobileno like '%"+mobileno+"'").list();
		if(list.size()>0) {
			
			
			long searchTimestamp = list.get(0).getCreateddate().getTime();// this also gives me back timestamp in 13 digit (1425506040493)

			long difference = Math.abs(currentTimestamp - searchTimestamp);
//223134 is your OTP for verification of your KYC documents . OTP is confidential. For security reasons, DO NOT SHARE THIS OTP WITH ANYONE.
//			 String msg=" is your OTP for verification of your KYC documents . OTP is confidential. For security reasons, DO NOT SHARE THIS OTP WITH ANYONE.".replaceAll(" ", "%20");
				
			if (difference >  5 * 1000) {
				Query query = session.createQuery(" Update OTP set  isactive ='I' where  mobileno like '%"+mobileno+"'");
				query.executeUpdate();
				persist(otp);	
				String status=SendSMS.SMSSend(countryCode+mobileno,otp_str+msg);
				/* Call for Shree SMS*/
				String statusSreeSMS=SendSMS.ShreeSMSSend(countryCode+mobileno,otp_str+msg);
				
			}
			else {
				String status=SendSMS.SMSSend(countryCode+mobileno,list.get(0).getOtp()+msg);
				/* Call for Shree SMS*/
				String statusSreeSMS=SendSMS.ShreeSMSSend(countryCode+mobileno,otp_str+msg);
				otp=new OTP();
				otp=list.get(0);
			}
			 
		}else {
			
			persist(otp);
			String status=SendSMS.SMSSend(countryCode+mobileno,otp.getOtp()+msg);
			String statusSreeSMS=SendSMS.ShreeSMSSend(countryCode+mobileno,otp_str+msg);
		}
		
		return otp;
	
	}
	
}
