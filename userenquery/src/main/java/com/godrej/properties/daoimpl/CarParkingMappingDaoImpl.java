package com.godrej.properties.daoimpl;

import java.util.ArrayList;
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
import com.godrej.properties.dto.SoldCarParkDTO;
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

	@Override
	public List<SoldCarParkDTO> getCarParkingCount(String projectsfid) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createNativeQuery("SELECT PropStrength__Category_of_Parking__c,count(*) as parkcount FROM salesforce.propstrength__car_parking__c  where propstrength__project__c='"+projectsfid+"' and propstrength__active__c=true "
				+ "group by PropStrength__Category_of_Parking__c  ");
		List<Object[]> list = q.getResultList();
		
		List<SoldCarParkDTO> finalList = new ArrayList<SoldCarParkDTO>();
		if(list.size()>0)
		{
			/*System.out.println("PropStrength__Category_of_Parking__c: name:-"+list.get(0)[0] == null ? "0" : list.get(0)[0]);
			System.out.println("PropStrength__Category_of_Parking__c: Count:-"+list.get(0)[1] == null ? "0" : list.get(0)[1]);*/
			for(Object[] dto:list)
			{
				SoldCarParkDTO dtoparking = new SoldCarParkDTO();
				Query totalPrakingCount = session.createNativeQuery(" select count(*) as total_prak_count from salesforce.propstrength__car_parking__c  where PropStrength__Category_of_Parking__c='"+dto[0]+"' and propstrength__project__c='"+projectsfid+"' and Allotted_Through_Offer__c=true ");
				Query allothed_Offer = session.createNativeQuery(" select count(*) as total_prak_count from salesforce.propstrength__car_parking__c  where PropStrength__Category_of_Parking__c='"+dto[0]+"' and propstrength__project__c='"+projectsfid+"' and propstrength__allotted__c=true ");
				
				dtoparking.setCategory_Name(dto[0].toString());
				dtoparking.setSold_parking_count(Integer.parseInt(totalPrakingCount.getResultList().get(0).toString()));
				dtoparking.setTotal_parking_count(Integer.parseInt(dto[1].toString()));//
				dtoparking.setAlloted_through_offer_count(Integer.parseInt(allothed_Offer.getResultList().get(0).toString()));
				finalList.add(dtoparking);
			}
		}
		return finalList;
	}

}
