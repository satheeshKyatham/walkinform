package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.ProjectDtlDao;
import com.godrej.properties.model.ProjectDtl;
import com.godrej.properties.service.ProjectDtlService;


@Service("projectDtlService")
@Transactional
public class ProjectDtlServiceImpl implements ProjectDtlService{
	
	@Autowired
	private ProjectDtlDao dao;
	
	@Override
	public List<ProjectDtl> getProjectData(String projectId) {
		// TODO Auto-generated method stub
		return dao.getProjectData(projectId);
	}
	
}
