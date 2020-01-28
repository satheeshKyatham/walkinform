package com.godrej.properties.dto;

import java.io.Serializable;

public class UserTokenDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3757776776568248547L;
	private String tokenId;
	private String tokenNo;

	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getTokenNo() {
		return tokenNo;
	}
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}
	
	
}
