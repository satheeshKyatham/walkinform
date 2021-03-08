package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.TokenScreenConfigDao;
import com.godrej.properties.model.TokenScreenConfig;

@Repository("tokenScreenConfigDao")
public class TokenScreenConfigDaoImpl extends AbstractDao<Integer, TokenScreenConfig> implements TokenScreenConfigDao{
	
	private Logger Log = LoggerFactory.getLogger(getClass());

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public TokenScreenConfig insertConfig(TokenScreenConfig aLog) {
		persist(aLog);
		return aLog;
	}

	@Override
	public TokenScreenConfig getTncData(String projectid) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			List<TokenScreenConfig> list = session.createQuery(" FROM  TokenScreenConfig where projectsfid = '"+projectid+"'  order by created DESC ").list();
			if (list.size() > 0) {
				return list.get(0);
			} else {
				TokenScreenConfig aLog = new TokenScreenConfig();
				aLog.setStatus("NO_RECORD");
				return aLog;
			}
		} catch (Exception e) {
			Log.info("Get Token Screen Config Error:- ", e);
			TokenScreenConfig aLog = new TokenScreenConfig();
			aLog.setStatus("ERROR");
			return aLog;
		}
	}
	
	@Override
	public Boolean updateTokenScreen(TokenScreenConfig aLog) {
		try {
			
				Session session = this.sessionFactory.getCurrentSession();
				Query query = session.createQuery("UPDATE TokenScreenConfig  "
						+ " SET "
								+ "  token_type =  '"+aLog.getToken_type()+"' "
								+ " , key_cont1 = '"+aLog.getKey_cont1()+"' "
								+ " , key_cont2 = '"+aLog.getKey_cont2()+"' "
								+ " , video_url = '"+aLog.getVideo_url()+"' "
								+ " , image_url = '"+aLog.getImage_url()+"' "
								+ " , ticker_label = '"+aLog.getTicker_label()+"' "
								+ " , ticker_text = '"+aLog.getTicker_text()+"' "
								+ " , updatedby = '"+aLog.getUpdatedby()+"' "
								+ " , updated = '"+aLog.getUpdated()+"' "

						+ " WHERE projectsfid = '"+aLog.getProjectsfid()+"' AND id = '"+aLog.getId()+"'  ");
				
				query.executeUpdate();
				return true;
		} catch (Exception e) {
			Log.info("Update Token Screen Error:- ",e);
			return false;
		}
	}
}