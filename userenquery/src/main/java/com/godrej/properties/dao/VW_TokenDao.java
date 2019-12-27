package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.VW_Token;

public interface VW_TokenDao {

	List<VW_Token> getTokenAssignList(String tokenType,String projectid,String inputDate,String toDate);

	List<VW_Token> getTokenList(String tokenType, String projectId,String inputDate,String toDate);

}
