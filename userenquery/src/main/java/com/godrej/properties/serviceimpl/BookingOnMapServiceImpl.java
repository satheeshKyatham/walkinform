package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.BookingOnMapDao;
import com.godrej.properties.model.BookingOnMap;
import com.godrej.properties.service.BookingOnMapService;

@Service("bookingOnMapService")
@Transactional
public class BookingOnMapServiceImpl implements BookingOnMapService{
	@Autowired
	BookingOnMapDao bookingOnMapDao;
	
	@Override
	public List<BookingOnMap> getEnqDtl(String projectId, String finalVerticales) {
		// TODO Auto-generated method stub
		return bookingOnMapDao.getEnqDtl(projectId, finalVerticales);
	}
}
