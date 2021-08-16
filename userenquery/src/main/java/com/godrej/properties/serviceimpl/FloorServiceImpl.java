package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.FloorDao;
import com.godrej.properties.model.Floor;
import com.godrej.properties.service.FloorService;

@Service
@Transactional
public class FloorServiceImpl implements FloorService{
	
	@Autowired
	FloorDao floorDao;
	
	@Override
	public List<Floor> getData(String towersfid) {
		
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
		
		return floorDao.getData(towersfidNew);
		
	}

}
