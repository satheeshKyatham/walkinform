package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.TokenDao;
import com.godrej.properties.model.Token;

@Repository("tokenDao")
@Transactional
public class TokenDaoImpl extends AbstractDao<Integer, Token> implements TokenDao {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public Token generateToken(String mobileno, String type) {
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Token generateToken(Token token) {
		Token token1=new Token();
		Session session = this.sessionFactory.getCurrentSession();	
		
		/*Query q = session.createQuery(" FROM  Token where  type ='"+token.getType()+"' and projectname ='"+token.getProjectname()+"' and Date(created) = Date(now())  order by queue desc ");*/
		/*Query q = session.createQuery(" FROM  Token where  type ='"+token.getType()+"' and projectname ='"+token.getProjectname()+"' and Date(created) = Date(now()+ (interval '330 minute'))  order by queue desc ");*/
		Query q = session.createQuery(" FROM Token where  type ='"+token.getType()+"' and projectname ='"+token.getProjectname()+"' and Date(created) = :date  order by queue desc ");
		//AT TIME ZONE 'Asia/Kolkata'
//		q.setParameter("date", new Date(System.currentTimeMillis() + 5*60*60*1000));
		q.setParameter("date", new Date(System.currentTimeMillis()));
		//q.setTimestamp("now", new java.util.Date());	
		 
		List<Token>  list1  =(List<Token>)((org.hibernate.query.Query) q).list();
		if(list1!=null)
		{
			if(list1.size()>0) {
			 token.setQueue(list1.get(0).getQueue()+1);
				token1.setQueue(list1.get(0).getQueue()+1);
			}else {
				token.setQueue(1);
				token1.setQueue(1);
			}
		}
		 
	//	List<Token> list =session.createQuery(" from Token where mobileno like '%"+token.getMobileno()+"' and isactive='Y'  and projectname ='"+token.getProjectname()+"' and created=now() ").list();
		Query qr = session.createQuery(" from Token where mobileno like '%"+token.getMobileno()+"' and isactive='Y'  and projectname ='"+token.getProjectname()+"' ");//and Date(created)=Date(now()) 
		//qr.setTimestamp("now", new java.util.Date());	
		 
		@SuppressWarnings("unchecked")
		List<Token>  list2  =(List<Token>)((org.hibernate.query.Query) qr).list();
		if(list2.size()>0)
		{
			Query query = session.createQuery(" update Token set isactive='N' where  mobileno like '%"+token.getMobileno()+"' and projectname ='"+token.getProjectname()+"'");
			query.executeUpdate();
			//token.setEnquiry_18(list2.get(0).getEnquiry_18());
			persist(token);
			token1.setNv_token_id(token.getNv_token_id());
			token1.setMsg("Submitted.");
			//token1=list.get(0);
			//token1.setMsg("Already Exist.");
		}else {
			persist(token);
			token1.setNv_token_id(token.getNv_token_id());
			token1.setMsg("Submitted.");
		}
		token1.setMobileno(token.getMobileno());
		token1.setType(token.getType());
		return token1;
	}


	@Override
	public List<Token> getuniqeTypes() {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) session.createQuery(
				" SELECT count(*) as count_type, type,case when type='W' then 'Walkin' when type='G' then 'Green Chennel'      when type='E' then 'EOI' end as type_name  FROM Token where window_assign is null group by type  ")
				.list();
		if (list.size() > 0) {
			List<Token> paymentPlans = new ArrayList<Token>();

			Iterator itr = list.iterator();
			while (itr.hasNext()) {
				Token token = new Token();
				// Object obj = (Object) itr.next();
				Object[] obj = (Object[]) itr.next();

				String client = String.valueOf(obj[0]);
				String clien1t = String.valueOf(obj[1]);
				String clien1t2 = String.valueOf(obj[2]);

				token.setTypeCount(client);
				token.setType(clien1t);
				token.setTypeName(clien1t2);

				paymentPlans.add(token);
			}

			return paymentPlans;
		}
		return new ArrayList<>();
	}

	@Override
	public Token getNextType(String type, String counterNo, String lastTokenNo) {
		Session session = this.sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		List<Token> list = session
				.createQuery("  FROM Token where  (window_assign ='' or window_assign is null) and type='" + type
						+ "' order by queue asc ")
				.list();

		if (list.size() > 0) {
			Query query;
			/*
			 * if(lastTokenNo!=null && !"null".equals(lastTokenNo) &&
			 * !"".equals(lastTokenNo)) { query = session.
			 * createQuery(" update Token set isdone='Y' , enddate=now()  where  nv_token_id="
			 * +lastTokenNo); query.executeUpdate(); }
			 */
			query = session.createQuery(" update Token set window_assign='" + counterNo
					+ "' , starteddate=now()  where nv_token_id=" + list.get(0).getNv_token_id());
			query.executeUpdate();
			return list.get(0);
		}

		return new Token();
	}

	@Override
	public String getDealDone(String lastTokenNo) {
		Session session = this.sessionFactory.getCurrentSession();

		Query query;
		if (lastTokenNo != null && !"null".equals(lastTokenNo) && !"".equals(lastTokenNo)) {
			query = session .createQuery(" update Token set isdone='Y' , enddate=now()  where  nv_token_id=" + lastTokenNo);
			query.executeUpdate();
		}
		return "Success";
	}
	
	@Override
	public String getDealStarted(String lastTokenNo) {
		Session session = this.sessionFactory.getCurrentSession();

		Query query;
		if (lastTokenNo != null && !"null".equals(lastTokenNo) && !"".equals(lastTokenNo)) {
			query = session .createQuery(" update Token set isdone='S'  where  nv_token_id=" + lastTokenNo);
			query.executeUpdate();
		}
		return "Success";
	}

	@Override
	public List<Token> getDisplayTV() {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Token> list = session.createQuery(
				"  FROM Token where (window_assign is not null and window_assign<>'')  and isdone is null order by starteddate ")
				.list();

		if (list.size() > 0) {

			return list;
		}

		return list;
	}

	@Override
	public List<Token> getTokenList(String tokenType,String projectId) {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Token> list = session
				.createQuery( "  FROM Token where  type='" + tokenType + "' and window_assign is null and isactive='Y' and projectname='"+projectId+"' order by queue asc ")
				.list();

		if (list.size() > 0) {

			return list;
		}
		return list;
	}

	@Override
	public List<Token> getTokenAssignList(String tokenType) {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Token> list = session.createQuery("  FROM Token where  type='" + tokenType
				+ "' and queue is not null and window_assign is not null order by queue asc ").list();

		if (list.size() > 0) {

			return list;
		}
		return list;
	}

	@Override
	public Token updateAssignStatus(String id, String assinedto) {
		Token t = new Token();
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				" update Token set window_assign='" + assinedto + "',starteddate=now()  where  nv_token_id=" + id);
		query.executeUpdate();
		return t;
	}

	@Override
	public List<Token> getTokenList(String tokenType) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		List<Token> list  =session.createQuery("  FROM Token where  type='"+tokenType+"' and window_assign is null order by queue asc ").list();
		
		if(list.size()>0) {
			
			return list;
		}
		return list;
	}
	
	 
 
	@Override
	public Token updateTokenStatus(String mobileno) {
		
		//No required
		/*Session session = this.sessionFactory.getCurrentSession();
		List<Token> list  =session.createQuery("  FROM Token where  mobileno='"+mobileno+"' and isactive='Y'").list();
		if(list.size()>0) {
			Query query = session.createQuery(" update Token set isactive='N' where  mobileno='"+mobileno+"' ");
			query.executeUpdate();
			return null;
		}*/
		return null;
	}

	@Override
	public Token updateTokenEnquiryID(String mobileno, String sfid,String tokenid) {
		Token t = new Token();
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(" update Token set enquiry_18='"+sfid+"' where  mobileno='"+mobileno+"' and isactive='Y' and nv_token_id="+tokenid);
		log.info("After execute...{}",query.executeUpdate());
		return t;
	}
	@Override
	public Token getTokenDetails(String tokenID) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		List<Token> list  =session.createQuery("  FROM Token where  nv_token_id='"+tokenID+"'").list();
		
		if(list.size()>0) {
			
			return list.get(0);
		}
		return null;
	}

	@Override
	public String updateEnqSalesTab(int enqid, String email) {
		Session session = this.sessionFactory.getCurrentSession();
		
		Query q = session.createNativeQuery("select sfid from  salesforce.user where email ='"+email+"' and isactive='t' ");//and user_type__c='Sales User'
		
		
		int i = q.getResultList().size();
		if(i>0)
		{
			Query query = session.createNativeQuery(" update salesforce.propstrength__request__c set User__c='"+q.getResultList().get(0)+"',OwnerId='"+q.getResultList().get(0)+"' where  id="+enqid+" ");
			log.info("After execute...{}",query.executeUpdate());
			return "updated CM";
		}
		else
			return "updated AM";
		
		
	}

	@Override
	public String getSalesUserSFID(int enqid, String email) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createNativeQuery("select sfid from  salesforce.case_escalation_users_detail__c where email__c ='"+email+"' ");
		int i = q.getResultList().size();
		if(i>0)
		{
			return q.getResultList().get(0).toString();
		}
		else
			return null;
		/*if(i>0)
		{
			Query query = session.createNativeQuery(" update salesforce.propstrength__request__c set Sourcing_Managers__c='"+q.getResultList().get(0)+"' where  id="+enqid+" ");
			System.out.println("After execute..."+query.executeUpdate());
			return "updated CM";
		}
		else
			return "No Entry Found";*/
	}
	@Override
	public String getSalesUserEmailID(int enqid, String sfid) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createNativeQuery("select email__c from  salesforce.case_escalation_users_detail__c where sfid ='"+sfid+"' ");
		int i = q.getResultList().size();
		if(i>0)
		{
			return q.getResultList().get(0).toString();
		}
		else
			return null;
	}
	
	public Token getTokenByEnquiry(String enquirySfid) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		List<Token> list  =session.createQuery("  FROM Token where  enquiry_18='"+enquirySfid+"'").list();
		if(list.size()>0) {
			
			return list.get(0);
		}
		return null;
	}

	@Override
	public int getEnquiryIDFromSFID(String enquirySfid) {
		try
		{
			Session session = this.sessionFactory.getCurrentSession();
			Query q = session.createNativeQuery("select id from  salesforce.propstrength__request__c where sfid ='"+enquirySfid+"' ");
			int i = q.getResultList().size();
			if(i>0)
			{
				return Integer.parseInt(q.getResultList().get(0).toString());
			}
			else return 0;
		}
		catch (Exception e) {
			log.error("No Enquiry Find: {}",e);
			return 0;
		}
	}

	@Override
	public void updateEnquiryData(int enqID, String updateData) {
		Query q = getSession().createNativeQuery("Update salesforce.propstrength__request__c set "+updateData+" where id ="+enqID+" ");
		/*q.setParameter("updateData", updateData);
		q.setParameter("enqid", enqID);*/
		q.executeUpdate();
		
	}

	@Override
	public void updateContactData(int contactID, String updateData) {
		Query q = getSession().createNativeQuery("Update salesforce.contact set "+updateData+" where id ="+contactID+" ");
		/*q.setParameter("updateData", updateData);
		q.setParameter("enqid", enqID);*/
		q.executeUpdate();
		
	}

	@Override
	public void updateClosingMangerOnOfferCreation(String enquirysfid) {

		Session session = this.sessionFactory.getCurrentSession();
		
		Query q = session.createNativeQuery("select Closing_Managers__c from  salesforce.propstrength__request__c where sfid ='"+enquirysfid+"'");
		//int i = q.getResultList().size();
		/*if(i>0)
		{
			return q.getResultList().get(0).toString();
		}*/
		
		/*int i = q.getResultList().size();
		if(i>0)
		{*/
			//return Integer.parseInt(q.getResultList().get(0).toString());
			if(q.getResultList().get(0)==null)
			{
				Query query = session.createNativeQuery(" update salesforce.propstrength__request__c set Closing_Managers__c='a0W6F00000S4J5UUAV' where  sfid='"+enquirysfid+"' ");
				log.info("After execute Closing Manager Update...{}",query.executeUpdate());
			}
		/*}*/
		
		
		
	
		
	}
}
