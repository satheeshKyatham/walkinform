package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.VW_TokenDao;
import com.godrej.properties.model.VWTokenScreen;
import com.godrej.properties.model.VW_Token;

@Repository("vw_tokenDao")
public class VW_TokenDaoImpl extends AbstractDao<Integer, VW_Token> implements VW_TokenDao {

	@Autowired
	private SessionFactory sessionFactory;

	public VW_TokenDaoImpl() {

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<VW_Token> getTokenAssignList(String tokenType,String projectid,String inputDate,String toDate) {
		Session session = this.sessionFactory.getCurrentSession();
		List<VW_Token> list=null;
		String tokenTypeConfig="";
		if(!tokenType.equals("All"))
			tokenTypeConfig="type='" + tokenType+ "' and ";
		if(inputDate!=null && inputDate.length()>0)
		{
			list = session.createQuery("  FROM VW_Token where "+tokenTypeConfig+"  projectname='"+projectid+"' and Date(created) between '"+inputDate+"' and '"+toDate+"' and queue is not null and window_assign is not null order by queue asc ").list();
		}
		else
			list = session.createQuery("  FROM VW_Token where  " +tokenTypeConfig+" and projectname='"+projectid+"' and queue is not null and window_assign is not null order by queue asc ").list();
			
		if (list.size() > 0) {

			return list;
		}
		return null;
	}
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<VW_Token> getTokenList(String tokenType,String projectId,String inputDate,String toDate) {
		Session session = this.sessionFactory.getCurrentSession();
		List<VW_Token> list = null;
		String tokenTypeConfig="";
		if(!tokenType.equals("All"))
			tokenTypeConfig="type='" + tokenType+ "' and ";
		if(inputDate!=null && inputDate.length()>0)
		{
			list = session.createQuery( "  FROM VW_Token where  "+tokenTypeConfig+" window_assign is null and isactive='Y' and projectname='"+projectId+"' and Date(created) between '"+inputDate+"' and '"+toDate+"' order by queue asc ").list();
		}
		else
			list = session.createQuery( "  FROM VW_Token where  "+tokenTypeConfig+" window_assign is null and isactive='Y' and projectname='"+projectId+"' order by queue asc ").list();
		
		if (list.size() > 0) {
			return list;
		}

		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VWTokenScreen> getUpcomingToken(String tokenType,String projectId,String inputDate,String toDate) {
		Session session = this.sessionFactory.getCurrentSession();
		List<VWTokenScreen> list = null;
		String tokenTypeConfig="";
		if(!tokenType.equals("All"))
			tokenTypeConfig="type='" + tokenType+ "' and ";
		if(inputDate!=null && inputDate.length()>0)
		{
			list = session.createQuery( "  FROM VWTokenScreen where  "+tokenTypeConfig+" window_assign is null and isactive='Y' and projectname='"+projectId+"' and Date(created) between '"+inputDate+"' and '"+toDate+"' order by created asc").setMaxResults(20).list();
		}
		else
			list = session.createQuery( "  FROM VWTokenScreen where  "+tokenTypeConfig+" window_assign is null and isactive='Y' and projectname='"+projectId+"' order by created asc ").setMaxResults(10).list();
		
		if (list.size() > 0) {
			return list;
		}

		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VWTokenScreen> getAssignedList(String tokenType,String projectid,String inputDate,String toDate) {
		Session session = this.sessionFactory.getCurrentSession();
		List<VWTokenScreen> list=null;
		String tokenTypeConfig="";
		if(!tokenType.equals("All"))
			tokenTypeConfig="type='" + tokenType+ "' and ";
		if(inputDate!=null && inputDate.length()>0)
		{
			list = session.createQuery("  FROM VWTokenScreen where "+tokenTypeConfig+"  projectname='"+projectid+"' and Date(created) between '"+inputDate+"' and '"+toDate+"' and queue is not null and window_assign is not null order by created DESC ").setMaxResults(10).list();
		}
		else
			list = session.createQuery("  FROM VWTokenScreen where  " +tokenTypeConfig+" and projectname='"+projectid+"' and queue is not null and window_assign is not null order by created DESC ").setMaxResults(10).list();
			
		if (list.size() > 0) {

			return list;
		}
		return null;
	}
}