package com.godrej.properties.daoimpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dto.HoldInventoryAdminDao;
import com.godrej.properties.model.HoldInventoryAdmin;
import com.godrej.properties.model.Token;

@Repository("holdInventoryAdminDao")
public class HoldInventoryAdminDaoImpl extends AbstractDao<Integer, HoldInventoryAdmin> implements HoldInventoryAdminDao{

	@Autowired
	private SessionFactory sessionFactory;	
	  
	@Override
	public void saveHoldInventoryAdmin(HoldInventoryAdmin inventoryAdmin) {
		
		Session session = this.sessionFactory.getCurrentSession();	
		 
		//Query q = session.createQuery(" FROM  HoldInventoryAdmin where  project_id ='"
		//+inventoryAdmin.getProject_id()+"' and unitSfid ='"+inventoryAdmin.getUnitSfid()+"'");
		 
		//List<HoldInventoryAdmin>  list1  =(List<HoldInventoryAdmin>)q.list();
		//if(list1!=null)
		//{
			//if(list1.size()>0) {

				/*Query query = session.createNativeQuery(" update salesforce.gpl_cs_hold_admin_unit A set hold_status="+inventoryAdmin.isHold_status()
				+",hold_reason='"+inventoryAdmin.getHold_reason()+"', eoi_unit_locked='"+inventoryAdmin.isEoi_unit_locked()+"', hold_description='"+inventoryAdmin.getHold_description()+"' , enq_sfid='"+inventoryAdmin.getEnq_sfid()+"' ,  hold_behalf_username='"+inventoryAdmin.getHold_behalf_username()+"',  hold_behalf_userid='"+inventoryAdmin.getHold_behalf_userid()+"',  created_at= now(),  "
						+ " version =(SELECT coalesce(MAX(VERSION),0) +1 "
						+ " FROM salesforce.gpl_cs_hold_admin_unit B WHERE A.sfid=B.sfid)"
						+ " where A.project_id ='" +inventoryAdmin.getProject_id()+"' and A.sfid ='"+inventoryAdmin.getUnitSfid()+"'");
				query.executeUpdate();*/
				
				//Query query = session.createNativeQuery("UPDATE salesforce.gpl_cs_hold_unit_uat A SET holdstatusyn = '"+action.getHoldstatusyn()+"', statusai = '"+action.getStatusai()+"', version =(SELECT coalesce(MAX(VERSION),0) +1 "
					//	+ " FROM salesforce.gpl_cs_hold_unit_uat B WHERE A.sfid=B.sfid) "
						//+ " WHERE A.created_at + (interval '5 minute') < now() + (interval '330 minute') AND A.holdstatusyn = 'Y' AND A.statusai = 'A' ");
			//}else {
				//persist(inventoryAdmin);		
			//}
		//}			

		//persist(inventoryAdmin);
		
		
		Query query = session.createNativeQuery("INSERT INTO salesforce.gpl_cs_hold_admin_unit "
				+ " (version, sfid,  project_id, created_at, customer_id, "
				+ " hold_reason, hold_status, hold_description, hold_behalf_username, "
				+ " hold_behalf_userid, eoi_unit_locked, enq_sfid)"
				+ " Values(0, '"+inventoryAdmin.getUnitSfid()+"',  '"+inventoryAdmin.getProject_id()+"',  'now()',  '"+inventoryAdmin.getCustomer_id()+"',"
				+ "  '"+inventoryAdmin.getHold_reason()+"',  '"+inventoryAdmin.isHold_status()+"', '"+inventoryAdmin.getHold_description()+"',  '"+inventoryAdmin.getHold_behalf_username()+"',"
				+ "  '"+inventoryAdmin.getHold_behalf_userid()+"',  '"+inventoryAdmin.isEoi_unit_locked()+"',   '"+inventoryAdmin.getEnq_sfid()+"')");
		
		query.executeUpdate();
		
	}

	@Override
	public void updateHoldInventoryAdmin(HoldInventoryAdmin inventoryAdmin) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();	
		 
		Query q = session.createQuery(" FROM  HoldInventoryAdmin where  project_id ='"
		+inventoryAdmin.getProject_id()+"' and unitSfid ='"+inventoryAdmin.getUnitSfid()+"'");
		//q.setTimestamp("now", new java.util.Date());	
		 
		List<HoldInventoryAdmin>  list1  =(List<HoldInventoryAdmin>)q.list();
		if(list1!=null)
		{
			if(list1.size()>0) {

				/*Query query = session.createQuery(" update HoldInventoryAdmin set hold_status="+inventoryAdmin.isHold_status()
				+",hold_reason='"+inventoryAdmin.getHold_reason()+"' , eoi_unit_locked='"+inventoryAdmin.isEoi_unit_locked()+"' where project_id ='" +inventoryAdmin.getProject_id()+"' and unitSfid ='"+inventoryAdmin.getUnitSfid()+"'");
				query.executeUpdate();*/
				
				
				Query query = session.createNativeQuery(" update salesforce.gpl_cs_hold_admin_unit A set hold_status="+inventoryAdmin.isHold_status()
				+" , hold_reason='"+inventoryAdmin.getHold_reason()+"' , eoi_unit_locked='"+inventoryAdmin.isEoi_unit_locked()+"' , "
				+ " version =(SELECT coalesce(MAX(VERSION),0) +1 "
				+ " FROM salesforce.gpl_cs_hold_admin_unit B WHERE A.sfid=B.sfid)"
				+ " where A.project_id ='" +inventoryAdmin.getProject_id()+"' and A.sfid ='"+inventoryAdmin.getUnitSfid()+"'");
				query.executeUpdate();
			
			}
		}			

	}
}	