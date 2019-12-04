package com.godrej.properties.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godrej.properties.common.converter.CommonConverter;
import com.godrej.properties.master.dto.ReferenceListDto;
import com.godrej.properties.master.model.ReferenceList;

@Component
public class ReferenceListConverter implements CommonConverter<ReferenceList, ReferenceListDto> {

	@Autowired
	private ReferenceConverter referenceConverter;
	
	@Override
	public ReferenceListDto entityToDto(ReferenceList entity) {
		if(null==entity){
			return null;
		}
		if(entity instanceof HibernateProxy) {
			return null;
		}
		ReferenceListDto dto=new ReferenceListDto();
		dto.setReferenceListId(entity.getReferenceListId());
		dto.setName(entity.getName());
		dto.setCode(entity.getCode());
		dto.setDescription(entity.getDescription());
		/*dto.setIsActive(entity.getIsActive());*/
		dto.setReference(referenceConverter.entityToDto(entity.getReference()));
		
		return dto;
	}

	@Override
	public ReferenceList dtoToEntity(ReferenceListDto dto) {
		if(null==dto){
			return null;
		}
		ReferenceList entity=new ReferenceList();
		entity.setReferenceListId(dto.getReferenceListId());
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		entity.setDescription(dto.getDescription());
		entity.setReference(referenceConverter.dtoToEntity(dto.getReference()));
		/*entity.setIsActive(dto.getIsActive());*/
		return entity;
	}

	@Override
	public List<ReferenceListDto> entityToDto(List<ReferenceList> entityList) {
		if(null==entityList){
			return Collections.emptyList();
		}
		List<ReferenceListDto> dtoList=new ArrayList<>();
		for(ReferenceList entity:entityList){
			ReferenceListDto dto=entityToDto(entity);
			if(dto!=null) {
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	@Override
	public List<ReferenceList> dtoToEntity(List<ReferenceListDto> dtoList) {
		if(null==dtoList){
			return Collections.emptyList();
		}
		List<ReferenceList> entityList=new ArrayList<>();
		for(ReferenceListDto dto:dtoList){
			ReferenceList entity=dtoToEntity(dto);
			entityList.add(entity);
		}
		return entityList;
	}
}
