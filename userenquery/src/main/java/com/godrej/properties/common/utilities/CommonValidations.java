package com.godrej.properties.common.utilities;

import java.util.List;

public class CommonValidations {
	
	private CommonValidations(){
		throw new IllegalStateException("Utility class");
	}
	
	public static boolean isEmpty(String object){
		boolean empty =false;
		if(object==null || object.trim().length() ==0)
			empty =true;
		return empty;
	}
	
	public static boolean isEmpty(List<?> list){
		boolean empty =false;
		if(list == null || list.isEmpty())
			empty=true;
		return empty;
	}

}
