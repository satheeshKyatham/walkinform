package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.HeaderSchemeDao;
import com.godrej.properties.model.HeaderScheme;
import com.godrej.properties.service.HeaderSchemeService;

@Service("headerSchemeService")
@Transactional
public class HeaderSchemeServiceImpl implements HeaderSchemeService{

	@Autowired
	private HeaderSchemeDao headerSchemeDao;
	
	@Override
	public List<HeaderScheme> getHeaderSchemeChargs(String projectId) {
		// TODO Auto-generated method stub
		return headerSchemeDao.getHeaderSchemeChargs(projectId);
	}

}
