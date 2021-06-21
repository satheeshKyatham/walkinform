/**
 * 
 */
package com.godrej.properties.dao;

import com.godrej.properties.model.CPAPP_EnquiryRequest;

/**
 * @author balram.asati
 *
 */
public interface CPAPP_EnquiryDao {

	/**
	 * @param cpapp_EnquiryRequest
	 */
	void updateEnquery_CPAPP(CPAPP_EnquiryRequest cpapp_EnquiryRequest);

	/**
	 * @param cpapp_EnquiryRequest
	 * @return
	 */
	CPAPP_EnquiryRequest createEnquery_CPAPP(CPAPP_EnquiryRequest cpapp_EnquiryRequest);

	/**
	 * @param cpapp_EnquiryRequest
	 */
	void updateEnquery_status_CPAPP(CPAPP_EnquiryRequest cpapp_EnquiryRequest);

}

