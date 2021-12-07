package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dto.HoldParkingAdminDao;
import com.godrej.properties.model.HoldParkingAdmin;

@Repository("holdParkingAdminDao")
public class HoldParkingAdminDaoImpl extends AbstractDao<Integer, HoldParkingAdmin> implements HoldParkingAdminDao{

	@Autowired
	private SessionFactory sessionFactory;	
	  
	@Override
	public void saveHoldInventoryAdmin(HoldParkingAdmin parkingAdmin) {
		
		Session session = this.sessionFactory.getCurrentSession();	
		 
		Query query = session.createNativeQuery("INSERT INTO salesforce.gpl_cs_hold_admin_parking "
				+ " (version, "
				+ " flatsfid, "
				+ " parkingsfid,  "
				+ " projectsfid, "
				+ " created_at, "
				+ " created_by, "
				+ " hold_reason, "
				+ " hold_status, "
				+ " hold_description)"
				
				+ " Values(0, "
				+ " '"+parkingAdmin.getFlatsfid()+"',  "
				+ " '"+parkingAdmin.getParkingsfid()+"',  "
				+ " '"+parkingAdmin.getProjectsfid()+"',  "
				+ " '"+parkingAdmin.getCreated_at()+"',  "
				+ " "+ parkingAdmin.getCreated_by()+",  "
				+ " '"+parkingAdmin.getHold_reason()+"',  "
				+ " '"+parkingAdmin.isHold_status()+"',  "
				+ " '"+parkingAdmin.getHold_description()+"')");
		
		query.executeUpdate();
	}
	
	@Override
	public void updateHoldParkingAdmin(HoldParkingAdmin parkingAdmin) {
		Session session = this.sessionFactory.getCurrentSession();	
		 
		Query q = session.createQuery(" FROM  HoldParkingAdmin where  projectsfid ='"
		+parkingAdmin.getProjectsfid()+"' and parkingsfid ='"+parkingAdmin.getParkingsfid()+"'");
		 
		List<HoldParkingAdmin>  list1  =(List<HoldParkingAdmin>)q.list();
		if(list1!=null)
		{
			if(list1.size()>0) {
				Query query = session.createNativeQuery(" update salesforce.gpl_cs_hold_admin_parking A set hold_status="+parkingAdmin.isHold_status()
				+" , hold_reason='"+parkingAdmin.getHold_reason()+"' , "
				+ " version =(SELECT coalesce(MAX(VERSION),0) +1 "
				+ " FROM salesforce.gpl_cs_hold_admin_parking B WHERE A.parkingsfid=B.parkingsfid)"
				+ " where A.projectsfid ='" +parkingAdmin.getProjectsfid()+"' and A.hold_status = true and A.parkingsfid ='"+parkingAdmin.getParkingsfid()+"'");
				query.executeUpdate();
			
			}
		}			

	}
}	