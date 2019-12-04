package com.godrej.properties.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Component;

import com.godrej.properties.common.converter.CommonConverter;
import com.godrej.properties.master.dto.ReferenceDto;
import com.godrej.properties.master.model.Reference;

@Component
public class ReferenceConverter implements CommonConverter<Reference, ReferenceDto> {

	@Override
	public ReferenceDto entityToDto(Reference entity) {
		if(null==entity){
			return null;
		}
		if(entity instanceof HibernateProxy) {
			return null;
		}
		ReferenceDto dto=new ReferenceDto();
		dto.setReferenceId(entity.getReferenceId());
		dto.setName(entity.getName());
		dto.setCode(entity.getCode());
		dto.setDescription(entity.getDescription());
		/*dto.setIsActive(entity.getIsActive());*/
		return dto;
	}

	@Override
	public Reference dtoToEntity(ReferenceDto dto) {
		if(null==dto){
			return null;
		}
		Reference entity=new Reference();
		entity.setReferenceId(dto.getReferenceId());
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		entity.setDescription(dto.getDescription());
		/*entity.setIsActive(dto.getIsActive());*/
		return entity;
	}

	@Override
	public List<ReferenceDto> entityToDto(List<Reference> entityList) {
		if(null==entityList){
			return Collections.emptyList();
		}
		List<ReferenceDto> dtoList=new ArrayList<>();
		for(Reference entity:entityList){
			ReferenceDto dto=entityToDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<Reference> dtoToEntity(List<ReferenceDto> dtoList) {
		if(null==dtoList){
			return Collections.emptyList();
		}
		List<Reference> entityList=new ArrayList<>();
		for(ReferenceDto dto:dtoList){
			Reference entity=dtoToEntity(dto);
			entityList.add(entity);
		}
		return entityList;
	}
}
