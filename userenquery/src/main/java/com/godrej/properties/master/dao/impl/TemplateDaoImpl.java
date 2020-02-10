package com.godrej.properties.master.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.master.dao.TemplateDao;
import com.godrej.properties.master.model.Template;

@Repository
public class TemplateDaoImpl  extends AbstractDao<Template, Template> implements TemplateDao{

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Template getTemplate(String value) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" FROM Template WHERE value=:value");
		Map<String,Object> params =  new HashMap<>();
		params.put("value", value);
		return getSingleEntity(sql.toString(), params);
	}

}
