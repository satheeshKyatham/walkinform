package com.godrej.properties.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.common.model.CommonModel;
import com.godrej.properties.common.utilities.CommonValidations;

public abstract class AAbstractDao<T extends CommonModel> {
	
	private final Class<T> persistentClass;
	
	private Logger LOG=LoggerFactory.getLogger(AbstractDao.class);
	
	@SuppressWarnings("unchecked")
	public AAbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession(){
		try{
			return sessionFactory.getCurrentSession();
		}catch(Throwable ex){
			ex.printStackTrace();
			throw ex; 
		}
	}

	protected Session getNewSession(){
		try{
			return sessionFactory.openSession();
		}catch(Throwable ex){
			ex.printStackTrace();
			throw ex; 
		}
	}
	
	protected void closeSession(Session session){
		try{
			session.close();
		}catch(Throwable ex){
			ex.printStackTrace();
			throw ex; 
		}
	}

	/*public T getByKey(E key) {
		try{
			return getSession().get(persistentClass, key);
		}catch(Throwable ex){
			ex.printStackTrace();
			throw ex; 
		}
	}*/

	public void persist(T entity) {
		try{
			getSession().persist(entity);
		}catch(Throwable ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public void delete(T entity) {
		try{
			getSession().delete(entity);
		}catch(Throwable ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	/*protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}*/

	public T create(T entity) {
		try{
			getSession().persist(entity);
			return entity;
		}catch(Throwable ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public T update(T entity){
		try{
			getSession().merge(entity);
			return entity;
		}catch(Throwable ex){
			ex.printStackTrace();
			throw ex; 
		}
	}
	
	public T findOne(Integer id){
		try{
			return getSession().find(persistentClass, id);
		}catch(Throwable ex){
			ex.printStackTrace();
			throw ex; 
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<T> getEntities(String queryString,Map<String, Object> params){
		Query query=getQuery(queryString, params);
		if(null==query){
			return Collections.emptyList();          
		}
		try{
			return query.getResultList();
		}catch(Throwable ex){
			ex.printStackTrace();
			throw ex; 
		}
		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public Integer updateByNative(String queryString,Map<String, Object> params){
		Query query=getNativeQuery(queryString, params);
		if(null==query){
			return 0;          
		}
		try{
			return query.executeUpdate();
		}catch(Throwable ex){
			ex.printStackTrace();
			throw ex; 
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<T> getEntities(String queryString,Map<String, Object> params,Integer maxResults){
		Query query=getQuery(queryString, params);
		if(null==query || maxResults<1){
			return Collections.emptyList();          
		}
		try{
			query.setMaxResults(maxResults);
			return query.getResultList();
		}catch(Throwable ex){
			ex.printStackTrace();
			throw ex; 
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	public T getSingleEntity(String queryString,Map<String, Object> params){
		Query query=getQuery(queryString, params);
		if(null==query){
			return null;          
		}
		try{
			List<T> resultList=query.getResultList();
			if (CommonValidations.isEmpty(resultList)) {
				return null;
			}
			return resultList.get(0);
		}catch(Throwable ex){
			ex.printStackTrace();
			throw ex; 
		}
	}
	
	private Query getQuery(String queryString,Map<String, Object> params){
		if (CommonValidations.isEmpty(queryString)) {
			return null;
		}
		try{
			Query query = getSession().createQuery(queryString, persistentClass);
			if (params == null) {
				return query;
			}
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				query.setParameter(key, value);
			}
			return query;
		}catch(Throwable ex){
			ex.printStackTrace();
			throw ex; 
		}
	}
	
	private Query getNativeQuery(String queryString,Map<String, Object> params){
		if (CommonValidations.isEmpty(queryString)) {
			return null;
		}
		try{
			Query query = getSession().createNativeQuery(queryString, persistentClass);
			if (params == null) {
				return query;
			}
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				query.setParameter(key, value);
			}
			return query;
		}catch(Throwable ex){
			ex.printStackTrace();
			throw ex; 
		}
	}
}
