package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.converter.CustomEnquiryConverter;
import com.godrej.properties.dao.CustomEnquiryDao;
import com.godrej.properties.dto.CustomEnquiryDto;
import com.godrej.properties.model.CustomEnquiry;
import com.godrej.properties.service.CustomEnquiryService;

/**
 * 
 * @author Varsha Patil
 *
 */
@Service
public class CustomEnquiryServiceImpl implements CustomEnquiryService{
	@Autowired
	private CustomEnquiryDao customEnquiryDao;

	@Autowired
	private CustomEnquiryConverter customEnquiryConverter; 
	
	@Override
	@Transactional
	public CustomEnquiryDto save(CustomEnquiryDto dto) {
		CustomEnquiry customEnquiry=customEnquiryConverter.dtoToEntity(dto);
		customEnquiry=customEnquiryDao.save(customEnquiry);
		return customEnquiryConverter.entityToDto(customEnquiry);
	}

	@Override
	public CustomEnquiryDto saveCustomEnquiry(CustomEnquiryDto dto) {
		if(null==dto.getCustomEnquiryId()){
			return save(dto);
		}else{
			CustomEnquiryDto customEnquiry=findById(dto);
			customEnquiry.setIsReferredByChannelPartnerFlag(dto.getIsReferredByChannelPartnerFlag());
			return update(customEnquiry);			
		}
	}

	@Override
	public CustomEnquiryDto update(CustomEnquiryDto dto) {
		CustomEnquiry customEnquiry=customEnquiryConverter.dtoToEntity(dto);
		customEnquiry=customEnquiryDao.update(customEnquiry);
		return customEnquiryConverter.entityToDto(customEnquiry);
	}

	@Override
	public CustomEnquiryDto findById(CustomEnquiryDto dto) {
		CustomEnquiry customEnquiry=customEnquiryDao.findById(dto.getCustomEnquiryId());
		return customEnquiryConverter.entityToDto(customEnquiry);
	}
	
}
