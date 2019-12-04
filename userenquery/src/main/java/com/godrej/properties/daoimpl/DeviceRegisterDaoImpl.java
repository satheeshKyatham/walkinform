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
import com.godrej.properties.dao.DeviceRegisterDao;
import com.godrej.properties.dao.OtpDao;
import com.godrej.properties.model.DeviceRegister;
import com.godrej.properties.model.OTP;
import com.godrej.properties.model.UserMaster;
import com.godrej.properties.util.OtpGenerate;
import com.godrej.properties.util.SendSMS; 
@Repository("deviceRegisterDao")
public class DeviceRegisterDaoImpl extends AbstractDao<Integer, DeviceRegister> implements DeviceRegisterDao {


	@Autowired
	private SessionFactory sessionFactory;
	public DeviceRegisterDaoImpl() {
		
	}
	
 

	@Override
	public void insertDevice(String deviceId) {
		// TODO Auto-generated method stub
		
		DeviceRegister deviceRegister= new DeviceRegister();
		deviceRegister.setDevice_id(deviceId);
		deviceRegister.setIsactive("A");
		
		Session session = this.sessionFactory.getCurrentSession();
		
		List<OTP> list =session.createQuery(" from DeviceRegister where isactive='A' and device_id like '%"+deviceId+"'").list();
		if(list.size()>0) {
		
		}else {
			persist(deviceRegister);
		}
		
		
	}

	 	
}
