/**
 * 
 */
package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.dto.CPContactEnquiryCreateUpdateReqDto;
import com.godrej.properties.dto.CPEOIRespAPIDto;
import com.godrej.properties.model.CPAPP_EnquiryRequest;
import com.godrej.properties.model.Contact;

/**
 * @author balram.asati
 *
 */
public interface CPAPP_Service {

	/**
	 * @param contact
	 */
	Contact createContact(Contact contact);

	/**
	 * @param cpapp_EnquiryRequest
	 */
	void updateEnquery_CPAPP(CPAPP_EnquiryRequest cpapp_EnquiryRequest);

	/**
	 * @param cpapp_EnquiryRequest
	 */
	CPAPP_EnquiryRequest createEnquery_CPAPP(CPAPP_EnquiryRequest cpapp_EnquiryRequest);

	/**
	 * @param cpapp_EnquiryRequest
	 */
	void updateEnquery_status_CPAPP(CPAPP_EnquiryRequest cpapp_EnquiryRequest);

	/**
	 * @param cpContactEnquiryCreateUpdateReqDto
	 * @return
	 */
	List<CPEOIRespAPIDto> getPastEOIData(CPContactEnquiryCreateUpdateReqDto cpContactEnquiryCreateUpdateReqDto);

}

