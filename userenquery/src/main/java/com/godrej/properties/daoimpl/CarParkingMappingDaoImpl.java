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
import com.godrej.properties.dao.CarParkingMappingDao;
import com.godrej.properties.model.CarParkingMapping;

@Repository("carparkingMappingDao")
public class CarParkingMappingDaoImpl extends AbstractDao<Integer, CarParkingMapping> implements CarParkingMappingDao{

	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public CarParkingMapping insertCarParkingCombination(CarParkingMapping carParkingMapping) {
		persist(carParkingMapping);
		return carParkingMapping;
	}

	@Override
	public CarParkingMapping updateCarParkingCombination(CarParkingMapping carParkingMapping) {
		Session session = this.sessionFactory.getCurrentSession();

		Query
			query = session.createQuery(" UPDATE CarParkingMapping set absolute_amount="+carParkingMapping.getAbsolute_amount()+",updatedby='"+carParkingMapping.getUpdatedby()+"',updated=now()  where  property_type_sfid='" + carParkingMapping.getProperty_type_sfid()+"' and parking_category='"+carParkingMapping.getParking_category()+"'");
			query.executeUpdate();
		return carParkingMapping;
	}

	@Override
	public CarParkingMapping selectCarParkingCombination(CarParkingMapping carParkingMapping) {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<CarParkingMapping> list = session
				.createQuery( "  FROM CarParkingMapping where property_type_sfid='" + carParkingMapping.getProperty_type_sfid() + "' and parking_category='"+carParkingMapping.getParking_category()+"'")
				.list();

		if (list.size() > 0) {

			return list.get(0);
		}
		return null;
	}

	@Override
	public String inActiveCarParkingCombination(String property_type_sfid,String parking_category,String isactive) {
		Session session = this.sessionFactory.getCurrentSession();

		Query
			query = session.createQuery(" UPDATE CarParkingMapping set isactive='"+isactive+"'  where  property_type_sfid='" + property_type_sfid + "' and parking_category='"+parking_category+"'");
			query.executeUpdate();
		return "{\"status\":\"Success\"}";
	}

}
