package com.godrej.properties.daoimpl;

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
		 
		Query q = session.createQuery(" FROM  HoldInventoryAdmin where  project_id ='"
		+inventoryAdmin.getProject_id()+"' and unitSfid ='"+inventoryAdmin.getUnitSfid()+"'");
		//q.setTimestamp("now", new java.util.Date());	
		 
		List<HoldInventoryAdmin>  list1  =(List<HoldInventoryAdmin>)q.list();
		if(list1!=null)
		{
			if(list1.size()>0) {

				Query query = session.createQuery(" update HoldInventoryAdmin set hold_status="+inventoryAdmin.isHold_status()
				+",hold_reason='"+inventoryAdmin.getHold_reason()+"', hold_description='"+inventoryAdmin.getHold_description()+"',  hold_behalf_username='"+inventoryAdmin.getHold_behalf_username()+"'  where project_id ='" +inventoryAdmin.getProject_id()+"' and unitSfid ='"+inventoryAdmin.getUnitSfid()+"'");
				query.executeUpdate();
			
			}else {
				persist(inventoryAdmin);		
			}
		}			

			
	
		
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

				Query query = session.createQuery(" update HoldInventoryAdmin set hold_status="+inventoryAdmin.isHold_status()
				+",hold_reason='"+inventoryAdmin.getHold_reason()+"' where project_id ='" +inventoryAdmin.getProject_id()+"' and unitSfid ='"+inventoryAdmin.getUnitSfid()+"'");
				query.executeUpdate();
			
			}
		}			

	}
}	