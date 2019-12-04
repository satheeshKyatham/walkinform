package com.godrej.properties.common.converter;

import java.util.List;

public interface CommonConverter<E,D> {

	public D entityToDto(E entity);
	
	public E dtoToEntity(D dto);


	public List<D> entityToDto(List<E> entityList);/*{
		if(null==entityList){
			return Collections.emptyList();
		}
		List<D> dtoList=new ArrayList<>();
		for(E entity: entityList){
			D dto= entityToDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}*/
	
	public List<E> dtoToEntity(List<D> dtoList);/*{
		if(null==dtoList){
			return Collections.emptyList();
		}
		List<E> entityList=new ArrayList<>();
		for(D dto: dtoList){
			E entity= dtoToEntity(dto);
			entityList.add(entity);
		}
		return entityList;
	}*/
}
