package com.godrej.properties.context.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.godrej.properties.common.dto.CommonDto;

public class UserDto extends CommonDto {
	
	private  int user_id ;
	private String	user_name;
	private String	password;
	private String	emailid;
	private String	mobileNo;
	private String	deviceid;
	private String	latitude;  
	private String	longitute;
	private String	projectId;
	private String	projectName;
	private String	role;
	private String	isActive ;
	private Timestamp	createdDate  ;
	private String	msg  ;

}
