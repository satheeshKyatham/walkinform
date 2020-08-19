package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dto.VW_UserTowerMasterDao;
import com.godrej.properties.model.Vw_UserTowerMapping; 

@Repository("vw_UserTowerMasterDao")
public class VW_UserTowerMasterDaoImpl  implements VW_UserTowerMasterDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Vw_UserTowerMapping> getProjectListUserWise(String userid) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Vw_UserTowerMapping> list =null;
		
		
		 list =session.createQuery(" from Vw_UserTowerMapping where user_id = '"+userid+"' order by projectname, tower_name").list();
	 
		if(list.size()>0)
			return list;
		return null;
	} 
	
	
	
}
