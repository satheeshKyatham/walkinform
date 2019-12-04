package com.godrej.properties.dao;

import com.godrej.properties.model.CustomEnquiry;

/**
 * 
 * @author Varsha Patil
 *
 */
public interface CustomEnquiryDao {
	public CustomEnquiry save(CustomEnquiry enq);
	public CustomEnquiry update(CustomEnquiry enq);
	public CustomEnquiry findById(Integer id);
}
