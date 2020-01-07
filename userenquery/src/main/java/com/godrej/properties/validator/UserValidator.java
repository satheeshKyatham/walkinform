package com.godrej.properties.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.godrej.properties.core.dto.Errors;
import com.godrej.properties.core.validator.Validator;
import com.godrej.properties.model.UserMaster;
import com.godrej.properties.service.UserMasterService;
import com.godrej.properties.util.ValidationUtil;

@Component
public class UserValidator implements Validator {
	
	@Autowired
	private ValidationUtil validator;

	@Autowired
	private UserMasterService userMasterService;

	
	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(UserMaster.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		UserMaster enq=(UserMaster) object;
	}

	public UserMaster validateUser(UserMaster userMaster, String type) {
		UserMaster master= userMasterService.searchUser(userMaster,type);
		if(master == null) {
 			userMaster.setMsg("Please contact  admin  for access app.");
 			return userMaster;			
		}
		if (master.getIsActive().equals("A")) {
			if (master.getUser_id() == 0) {
				master.setLatitude(userMaster.getLatitude());
				master.setLongitute(userMaster.getLongitute());
				master.setMsg("Success");
				userMasterService.updateUser(master);
				return userMaster;
			} else {
				master.setMsg("Success");
				return master;
			}	 		
	 		
	 	}else {
 			userMaster.setMsg("Kindly contact admin for access.");
 		}
		return userMaster;
	}
}
