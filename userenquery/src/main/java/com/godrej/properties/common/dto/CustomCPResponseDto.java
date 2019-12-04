package com.godrej.properties.common.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Ankush/Vivek Birdi
 *
 */
public class CustomCPResponseDto implements Serializable {

	private static final long serialVersionUID = -6956235527346229861L;

	private Object data;
	private Map<String, Object> objectMap =  new HashMap<>();
	private String message;
	private boolean success;
	private String url;
	
	public CustomCPResponseDto() {
	
	}
	public CustomCPResponseDto(boolean success, String message ){
		this.success = success;
		this.message = message;  
	}
	
	public CustomCPResponseDto(Object data, String message,boolean success){
		this.data = data;
		this.message = message;  
		this.success=success;
	}
	public CustomCPResponseDto(String key, Object value) {
		success = true;
		addObject(key, value);
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}	
	public Map<String, Object> getObjectMap() {
		return objectMap;
	}
	public void setObjectMap(Map<String, Object> objectMap) {
		this.objectMap = objectMap;
	}
	public void addObject(String key, Object value){
		objectMap.put(key, value);
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
