package com.godrej.properties.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.proxy.HibernateProxy;

import com.godrej.properties.common.converter.CommonConverter;
import com.godrej.properties.common.dto.CommonDto;
import com.godrej.properties.common.model.CommonModel;

public class CommonModelToCommonDtoConverter implements CommonConverter<CommonModel, CommonDto> {

	@Override
	public CommonDto entityToDto(CommonModel entity) {
		if(null == entity){
			return null;
		}
		if(entity instanceof HibernateProxy) {
			return null;
		}
		CommonDto dto=new CommonDto();
		/*dto.setIsActive(entity.getIsActive());
		dto.setSynchronised(entity.getSynchronised());*/
		return dto;
	}

	@Override
	public CommonModel dtoToEntity(CommonDto dto) {
		if(null == dto){
			return null;
		}
		CommonModel entity=new CommonModel();
		/*entity.setIsActive(dto.getIsActive());
		entity.setSynchronised(dto.getSynchronised());*/
		return entity;
	}

	@Override
	public List<CommonDto> entityToDto(List<CommonModel> entityList) {
		if(null==entityList){
			return Collections.emptyList();
		}
		List<CommonDto> dtoList=new ArrayList<>();
		for(CommonModel entity:entityList){
			CommonDto dto=entityToDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<CommonModel> dtoToEntity(List<CommonDto> dtoList) {
		if(null==dtoList){
			return Collections.emptyList();
		}
		List<CommonModel> entityList=new ArrayList<>();
		for(CommonDto dto:dtoList){
			CommonModel entity=dtoToEntity(dto);
			entityList.add(entity);
		}
		return entityList;
	}

}
