package com.godrej.properties.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Component;

import com.godrej.properties.common.converter.CommonConverter;
import com.godrej.properties.dto.ProjectDto;
import com.godrej.properties.model.Project;

@Component
public class ProjectConverter implements CommonConverter<Project, ProjectDto> {

	@Override
	public ProjectDto entityToDto(Project entity) {
		if(null == entity){
			return null;
		}
		if(entity instanceof HibernateProxy) {
			return null;
		}
		ProjectDto dto=new ProjectDto();
		dto.setProjectId(entity.getProjectId());
		dto.setPname(entity.getPname());
		/*dto.setCode(entity.getCode());
		dto.setDescription(entity.getCode());
		dto.setName(entity.getName());*/
		dto.setSfid(entity.getSfid());
		return dto;
	}

	@Override
	public Project dtoToEntity(ProjectDto dto) {
		if(null == dto){
			return null;
		}
		Project entity =new Project();
		entity.setProjectId(dto.getProjectId());
		/*entity.setCode(dto.getCode());
		entity.setDescription(dto.getCode());
		entity.setName(dto.getName());*/
		entity.setSfid(dto.getSfid());
		return entity;
	}

	@Override
	public List<ProjectDto> entityToDto(List<Project> entityList) {
		if(null==entityList){
			return Collections.emptyList();
		}
		List<ProjectDto> dtoList=new ArrayList<>();
		for(Project entity:entityList){
			ProjectDto dto=entityToDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<Project> dtoToEntity(List<ProjectDto> dtoList) {
		if(null==dtoList){
			return Collections.emptyList();
		}
		List<Project> entityList=new ArrayList<>();
		for(ProjectDto dto:dtoList){
			Project entity=dtoToEntity(dto);
			entityList.add(entity);
		}
		return entityList;
	}

}
