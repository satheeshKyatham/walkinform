package com.godrej.properties.master.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.master.dao.TemplateDao;
import com.godrej.properties.master.dto.TemplateDto;
import com.godrej.properties.master.model.Template;
import com.godrej.properties.master.service.SysConfigService;
import com.godrej.properties.master.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService{

	@Autowired
	private TemplateDao templateDao;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	private Logger log =  LoggerFactory.getLogger(getClass());
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TemplateDto getTemplate(String projectSfid) {
		String templateCode = sysConfigService.getValue(SysConfigService.OFFER_CREATION_TEMPLATE, projectSfid);
		if(templateCode == null) {
			templateCode = sysConfigService.getValue(SysConfigService.OFFER_CREATION_TEMPLATE_DEFAULT);
		}
		try {
			Template template = templateDao.getTemplate(templateCode);
			return getTemplateDto(template);
		}catch (Exception e) {
			log.error("Error : ", e);
		}
		return null;
	}
	
	private TemplateDto getTemplateDto(Template template) {
		if(template ==null) {
			return null;
		}
		TemplateDto dto =  new TemplateDto();
		dto.setName(template.getName());
		dto.setValue(template.getValue());
		dto.setBigText(template.getBigText());
		dto.setTemplateId(template.getTemplateId());
		return dto;
	}

}
