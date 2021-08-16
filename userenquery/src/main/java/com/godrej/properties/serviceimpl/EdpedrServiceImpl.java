package com.godrej.properties.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.EdpedrDao;
import com.godrej.properties.service.EdpedrService;

@Service("edpedrService")
@Transactional
public class EdpedrServiceImpl implements EdpedrService{
	
	@Autowired
	EdpedrDao edpedrDao;
	
	@Override
	//@Transactional(propagation = Propagation.REQUIRED)
	public Integer updateData(String multiFloor, String projectsfid, String edpDate) {
		
		// For multiple Project SFID
		String multiFloorNew = "";
		if (multiFloor != null && !multiFloor.equals("null") && !multiFloor.equals("")) {
			String [] mf= multiFloor.split(",");
			
			StringBuilder modifiedVer = new StringBuilder();
			
			for (int i=0;i<mf.length;i++){
				modifiedVer.append("'"+mf[i]+"'");
				modifiedVer.append(",");
			}
			
			multiFloorNew = modifiedVer.toString();
			
			if (multiFloorNew != null && multiFloorNew.length() > 0 && multiFloorNew.charAt(multiFloorNew.length() - 1) == ',') {
				multiFloorNew = multiFloorNew.substring(0, multiFloorNew.length() - 1);
			}
		} else {
			multiFloorNew = null;
		}
		
		return edpedrDao.updateData(multiFloorNew, projectsfid, edpDate);
		
	}
	
	
	@Override
	public Integer updateEDRData(String bookingsfid, String projectsfid, String edpDate) {
		
		String bookingsfidNew = "";
		if (bookingsfid != null && !bookingsfid.equals("null") && !bookingsfid.equals("")) {
			String [] mf= bookingsfid.split(",");
			
			StringBuilder modifiedVer = new StringBuilder();
			
			for (int i=0;i<mf.length;i++){
				modifiedVer.append("'"+mf[i]+"'");
				modifiedVer.append(",");
			}
			
			bookingsfidNew = modifiedVer.toString();
			
			if (bookingsfidNew != null && bookingsfidNew.length() > 0 && bookingsfidNew.charAt(bookingsfidNew.length() - 1) == ',') {
				bookingsfidNew = bookingsfidNew.substring(0, bookingsfidNew.length() - 1);
			}
		} else {
			bookingsfidNew = null;
		}
		
		return edpedrDao.updateEDRData(bookingsfidNew, projectsfid, edpDate);
		
	}
	
	
	@Override
	public Boolean insertData(String multiFloor, String projectsfid, String edpDate, String userid, String tower) {
		return edpedrDao.insertData(multiFloor, projectsfid, edpDate, userid, tower);
	}
	
	@Override
	public Boolean insertDataEDR(String selectedFloor, String projectsfid, String edrDate, String userid, String selectedTower, String bookingsfid) {
		return edpedrDao.insertDataEDR(selectedFloor, projectsfid, edrDate, userid, selectedTower, bookingsfid);
	}
}