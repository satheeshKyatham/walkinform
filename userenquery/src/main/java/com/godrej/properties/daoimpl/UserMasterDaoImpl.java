package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.UserMasterDao;
import com.godrej.properties.model.UserMaster; 

@Repository("userMasterDao")
public class UserMasterDaoImpl extends AbstractDao<Integer, UserMaster> implements UserMasterDao {


	@Autowired
	private SessionFactory sessionFactory;
	public UserMasterDaoImpl() {
		
	}
	
 

	@Override
	public String saveUser(UserMaster userMaster) {
		UserMaster val = searchUser(userMaster,"");
		String resp ="";
		if(val==null)
		{
			persist(userMaster);
			resp = "Done";
		}
		else
		{
			System.out.println("Entry already exists....");
			resp="Entry already exists";
		}
		return resp;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserMaster searchUser(UserMaster userMaster,String type) {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserMaster> list =null;
		if(userMaster.getEmailid().contains("@godrejproperties.com"))
			type = "E";
		else
			type = "";
	 if(type.equals("E")) {
		 list =session.createQuery(" from UserMaster where  emailid like '%"+userMaster.getEmailid()+"' and isActive='A' ").list();
	 }else {
		 list =session.createQuery(" from UserMaster where  emailid like '%"+userMaster.getEmailid()+"' and password='"+userMaster.getPassword()+"' and isActive='A' ").list();
	 }
		if(list.size()>0)
			return list.get(0);
		
		return null;
	}

	@Override
	public UserMaster updateUser(UserMaster userMaster) {
		  update(userMaster);
		return userMaster;
	}



	@Override
	public List<UserMaster> getUserList(String projectID) {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserMaster> list =null;
		 list =session.createQuery(" from UserMaster ").list();//where isactive='A'
		if(list.size()>0)
			return list;
		return null;
	}

	@Override
	public String updateUserMaster(UserMaster updatemaster) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				" update UserMaster set isactive='" + updatemaster.getIsActive() + "'  where  user_id=" + updatemaster.getUser_id());
		query.executeUpdate();
		return null;
	}



	@Override
	public List<UserMaster> getActiveUserList() {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserMaster> list =null;
		 list =session.createQuery(" from UserMaster where isactive='A' ").list();//where isactive='A'
		if(list.size()>0)
			return list;
		return null;
	}



	@Override
	public UserMaster getUserDetails(String userID) {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserMaster> list =null;
		 list =session.createQuery(" from UserMaster where isactive='A' and user_id="+userID).list();//where isactive='A'
		if(list.size()>0)
			return list.get(0);
		return null;
	}
}
