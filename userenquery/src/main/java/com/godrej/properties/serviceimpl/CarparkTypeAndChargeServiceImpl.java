package com.godrej.properties.serviceimpl;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.CarParkingMappingDao;
import com.godrej.properties.dao.CarParkingMappingLogDao;
import com.godrej.properties.dao.CarparkTypeAndChargeDao;
import com.godrej.properties.dto.SoldCarParkDTO;
import com.godrej.properties.model.CarParkingMapping;
import com.godrej.properties.model.CarParkingMappingLog;
import com.godrej.properties.model.CarparkCount;
import com.godrej.properties.model.CarparkTypeAndCharge;
import com.godrej.properties.service.CarparkTypeAndChargeService;

@Service("carparkTypeAndChargeService")
@Transactional
public class CarparkTypeAndChargeServiceImpl implements CarparkTypeAndChargeService {
	
	@Autowired
	CarparkTypeAndChargeDao carparkTypeAndChargeDao;
	
	@Autowired
	CarParkingMappingDao carparkingMappingDao;
	
	@Autowired
	CarParkingMappingLogDao carparkingMappingLogDao;
	

	@Override
	public List<CarparkTypeAndCharge> getCarparkType(String projectSFID) {
		// TODO Auto-generated method stub
		return carparkTypeAndChargeDao.getCarparkType(projectSFID);
	}	
	
	@Override
	public List<CarparkCount> getCarparkCount(String projectSFID) {
		return carparkTypeAndChargeDao.getCarparkCount(projectSFID);
	}

	@Override
	public List<CarParkingMapping> getCarParkingCombination(String towerSFID) {
		return carparkTypeAndChargeDao.getCarParkingCombination(towerSFID);
	}

	@Override
	public List<CarParkingMapping> insertCarParkingCombination(List<CarParkingMapping> carParkingMapping) {
		//Check combination exist or not
		//carparkTypeAndChargeDao.checkCarParkingCombination(carParkingMapping);
		//if yes update combination
		//carparkTypeAndChargeDao.updateCarParkingCombination(carParkingMapping);
		//if no insert combination
		//insert combination with multiple property type
		
		//call
		
		//int countA=Collections.frequency(listMapping, listMapping.get(i));
		for(CarParkingMapping car: carParkingMapping)
		{
			if(car.getAbsolute_amount()>0)
			{
				CarParkingMapping carSelect = carparkingMappingDao.selectCarParkingCombination(car);
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				car.setUpdated(timestamp);
				car.setCreated(timestamp);
				
				CarParkingMappingLog log = new CarParkingMappingLog();
				log.setCreated(car.getCreated());
				log.setAbsolute_amount(car.getAbsolute_amount());
				log.setCreatedby(car.getCreatedby());
				log.setIsactive(car.getIsactive());
				log.setParking_category(car.getParking_category());
				log.setProject_sfid(car.getProject_sfid());
				log.setProperty_type_code(car.getProperty_type_code());
				log.setProperty_type_name(car.getProperty_type_name());
				log.setProperty_type_sfid(car.getProperty_type_sfid());
				log.setRow_count(car.getRow_count());
				log.setTower_sfid(car.getTower_sfid());
				log.setUpdated(car.getUpdated());
				log.setUpdatedby(car.getUpdatedby());
	//			log.setNv_parking_mapping_id(car.getId());
				//log.setId(car.getId());
				if(carSelect!=null && car.getAbsolute_amount()>0)
				{
					carparkingMappingDao.updateCarParkingCombination(car);
					log.setNv_parking_mapping_id(car.getId());
					carparkingMappingLogDao.insertCarParkingCombination(log);
					
					
				}
				else if(car.getAbsolute_amount()>0)
				{
					CarParkingMapping carpa = carparkingMappingDao.insertCarParkingCombination(car);
					log.setNv_parking_mapping_id(carpa.getId());
					//log.setId(carpa.getId());
					carparkingMappingLogDao.insertCarParkingCombination(log);
					
				}
			}
		}
		
		return carParkingMapping;//carparkingMappingDao.insertCarParkingCombination(carParkingMapping);
	}

	@Override
	public String inActiveCarParkingCombination(String property_type_sfid,String parking_category,String isactive) {
		// TODO Auto-generated method stub
		return carparkingMappingDao.inActiveCarParkingCombination(property_type_sfid,parking_category,isactive);
	}

	@Override
	public List<SoldCarParkDTO> getCarParkingCount(String projectsfid) {
		// TODO Auto-generated method stub
		return carparkingMappingDao.getCarParkingCount(projectsfid);
	}
}