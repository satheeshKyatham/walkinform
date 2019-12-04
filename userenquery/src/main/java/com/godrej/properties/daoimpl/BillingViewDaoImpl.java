package com.godrej.properties.daoimpl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.BillingViewDao;
import com.godrej.properties.model.BillingData; 
@SuppressWarnings("unchecked")
@Repository("billingViewDao")
public class BillingViewDaoImpl extends AbstractDao<Integer, BillingData> implements BillingViewDao {


	@Autowired
	private SessionFactory sessionFactory;
	public BillingViewDaoImpl() {
		
	}
	 

	@Override
	public List<BillingData> getdataMapping(String page,String count,String str_date) {
		Session session = this.sessionFactory.getCurrentSession();
		StringBuffer wherecondition=new  StringBuffer();
		wherecondition.append("  ");
		
		if(str_date!=null &&! str_date.equals("") ) {
			wherecondition.append(" Where lastmodifieddate >'"+str_date+"'");
		}
		//+" OFFSET "+page+" ROWS FETCH NEXT "+count+" ROWS ONLY"
		List<BillingData> list  =session.createQuery("  FROM  BillingData  "+wherecondition ).setFirstResult(Integer.valueOf(page)).setMaxResults(Integer.valueOf(count)).list(); // where PROPSTRENGTH__HOUSE_UNIT_NO__C='"+unit+"'
		if(list.size()>0)
		{
			return list;
		}
		return list;
	}


	@Override
	public String getdataCount(String model,String str_date) {
		Session session = this.sessionFactory.getCurrentSession();
		 String wherecondition="";
		 if(str_date!=null &&! str_date.equals("") ) {
				wherecondition=" Where lastmodifieddate > '"+str_date+"'";
			}
		 
		
		List<Object>  list  =(List<Object>)session.createQuery("Select count(*) as a,'a' as b  FROM  BillingData  "+wherecondition ).list();
		if(list.size()>0)
		{
			 
	
			 Iterator itr = list.iterator();
			  while(itr.hasNext()){
				 
			     Object[] obj = (Object[]) itr.next();
			      
			     String client = String.valueOf(obj[0]); 
			     return  client;
			  }	
			 
		}
		
		
		 return  "";
		 
	}


	@Override
	public List<BillingData> getCreateTemp() {
	
		Session session = this.sessionFactory.getCurrentSession();
		
		
		 StringBuffer stringBuffer = new StringBuffer();
		 stringBuffer.append("DROP TABLE _TEMP_BILLING"); 
		 session.createNativeQuery(stringBuffer.toString()).executeUpdate();
		 stringBuffer = new StringBuffer();
		 stringBuffer.append("CREATE TABLE _TEMP_BILLING AS "); 
		 stringBuffer.append("SELECT * FROM SALESFORCE.VW_BILLING");
		 session.createNativeQuery(stringBuffer.toString()).executeUpdate();
		 	
		
		 return  null;
	
	}
		 
	
	 
	 
	
}
