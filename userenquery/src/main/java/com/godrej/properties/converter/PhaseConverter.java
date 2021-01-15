package com.godrej.properties.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Component;

import com.godrej.properties.common.converter.CommonConverter;
import com.godrej.properties.dto.PhaseDto;
import com.godrej.properties.model.Phase;

@Component
public class PhaseConverter implements CommonConverter<Phase, PhaseDto>{

	@Override
	public PhaseDto entityToDto(Phase entity) {
		if(null == entity){
			return null;
		}
		if(entity instanceof HibernateProxy) {
			return null;
		}
		PhaseDto dto=new PhaseDto();
		dto.setPhaseId(entity.getPhaseid());
		dto.setName(entity.getName());
		dto.setSfid(entity.getSfid());
		return dto;
	}

	@Override
	public Phase dtoToEntity(PhaseDto dto) {
		if(null == dto){
			return null;
		}
		Phase entity =new Phase();
		entity.setPhaseid(dto.getPhaseId());
		entity.setName(dto.getName());
		entity.setSfid(dto.getSfid());
		return entity;
	}

	@Override
	public List<PhaseDto> entityToDto(List<Phase> entityList) {
		if(null==entityList){
			return Collections.emptyList();
		}
		List<PhaseDto> dtoList=new ArrayList<>();
		for(Phase entity:entityList){
			PhaseDto dto=entityToDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<Phase> dtoToEntity(List<PhaseDto> dtoList) {
		if(null==dtoList){
			return Collections.emptyList();
		}
		List<Phase> entityList=new ArrayList<>();
		for(PhaseDto dto:dtoList){
			Phase entity=dtoToEntity(dto);
			entityList.add(entity);
		}
		return entityList;
	}

}
