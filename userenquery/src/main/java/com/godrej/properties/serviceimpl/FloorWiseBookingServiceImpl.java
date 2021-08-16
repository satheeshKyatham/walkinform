package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.FloorWiseBookingDao;
import com.godrej.properties.model.FloorWiseBooking;
import com.godrej.properties.service.FloorWiseBookingService;

@Service
@Transactional
public class FloorWiseBookingServiceImpl implements FloorWiseBookingService{
	
	@Autowired
	FloorWiseBookingDao floorWiseBookingDao;
	
	@Override
	public List<FloorWiseBooking> getData(String towersfid, String projectsfid) {
		
		// For multiple Project SFID
		String towersfidNew = "";
		if (towersfid != null && !towersfid.equals("null") && !towersfid.equals("")) {
			String [] multiTower= towersfid.split(",");
			
			StringBuilder modifiedVer = new StringBuilder();
			
			for (int i=0;i<multiTower.length;i++){
				modifiedVer.append("'"+multiTower[i]+"'");
				modifiedVer.append(",");
			}
			
			towersfidNew = modifiedVer.toString();
			
			if (towersfidNew != null && towersfidNew.length() > 0 && towersfidNew.charAt(towersfidNew.length() - 1) == ',') {
				towersfidNew = towersfidNew.substring(0, towersfidNew.length() - 1);
			}
		} else {
			towersfidNew = null;
		}
		
		return floorWiseBookingDao.getData(towersfidNew, projectsfid);
		
	}

}
