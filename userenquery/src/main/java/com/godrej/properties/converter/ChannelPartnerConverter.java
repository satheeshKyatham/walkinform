package com.godrej.properties.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Component;

import com.godrej.properties.common.converter.CommonConverter;
import com.godrej.properties.dto.ChannelPartnerDto;
import com.godrej.properties.model.ChannelPartner;

@Component
public class ChannelPartnerConverter implements CommonConverter<ChannelPartner, ChannelPartnerDto>{

	@Override
	public ChannelPartnerDto entityToDto(ChannelPartner entity) {
		if(null == entity){
			return null;
		}
		if(entity instanceof HibernateProxy) {
			return null;
		}
		ChannelPartnerDto dto=new ChannelPartnerDto();
		dto.setChannelPartnerId(entity.getChannelPartnerId());
		/*dto.setCode(entity.getCode());
		dto.setDescription(entity.getDescription());*/
		dto.setName(entity.getName());
		dto.setSfid(entity.getSfid());
		return dto;
	}

	@Override
	public ChannelPartner dtoToEntity(ChannelPartnerDto dto) {
		if(null == dto){
			return null;
		}
		ChannelPartner entity =new ChannelPartner();
		entity.setChannelPartnerId(dto.getChannelPartnerId());
		/*entity.setCode(dto.getCode());
		entity.setDescription(dto.getDescription());*/
		entity.setName(dto.getName());
		entity.setSfid(dto.getSfid());
		return entity;
	}

	@Override
	public List<ChannelPartnerDto> entityToDto(List<ChannelPartner> entityList) {
		if(null==entityList){
			return Collections.emptyList();
		}
		List<ChannelPartnerDto> dtoList=new ArrayList<>();
		for(ChannelPartner entity:entityList){
			ChannelPartnerDto dto=entityToDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<ChannelPartner> dtoToEntity(List<ChannelPartnerDto> dtoList) {
		if(null==dtoList){
			return Collections.emptyList();
		}
		List<ChannelPartner> entityList=new ArrayList<>();
		for(ChannelPartnerDto dto:dtoList){
			ChannelPartner entity=dtoToEntity(dto);
			entityList.add(entity);
		}
		return entityList;
	}

}
