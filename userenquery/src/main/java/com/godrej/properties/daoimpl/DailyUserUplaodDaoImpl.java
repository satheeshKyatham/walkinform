package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.DailyUserUplaodDao;
import com.godrej.properties.model.DailyUserMater;
import com.godrej.properties.model.Vw_DailyUserMater;

@Repository("dailyUserUplaod")
public class DailyUserUplaodDaoImpl extends AbstractDao<Integer, DailyUserMater> implements DailyUserUplaodDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public String uploadDailyUserMaster(DailyUserMater daily) {
		
		List<DailyUserMater> list =null;
		Session session = this.sessionFactory.getCurrentSession();
		 list =session.createQuery(" from DailyUserMater where project_id = '"+daily.getProject_id()+"' and user_id='"+daily.getUser_id()+"' ").list();
		
		 if(list.size()>0)
		 {
				return "Enter User Mapping already existed";
		 }
		 else
		 {
			 persist(daily);
			 return "Enter User successfully mapped";
		 }
	}

	@Override
	public List<DailyUserMater> getUploadDailyUserList(String projectID) {
		List<DailyUserMater> list =null;
		Session session = this.sessionFactory.getCurrentSession();
		 list =session.createQuery(" from DailyUserMater where project_id = '"+projectID+"' ").list();
		
		 if(list.size()>0)
			 return list;
		 
		 return null;
	}

	@Override
	public String updateDailyUserUpload(DailyUserMater daily) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				" update DailyUserMater set isactive='" + daily.getIsactive() + "'  where  user_id=" + daily.getUser_id());
		query.executeUpdate();
		return null;
	}

	@Override
	public List<Vw_DailyUserMater> getViewUploadDailyUserList(String projectID) {
		List<Vw_DailyUserMater> list =null;
		Session session = this.sessionFactory.getCurrentSession();
		 list =session.createQuery(" from Vw_DailyUserMater where project_id = '"+projectID+"' ").list();
		
		 if(list.size()>0)
			 return list;
		 
		 return null;
	}

}
