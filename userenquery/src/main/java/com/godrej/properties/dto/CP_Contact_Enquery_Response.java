/**
 * 
 */
package com.godrej.properties.dto;

import com.godrej.properties.model.CPAPP_EnquiryRequest;
import com.godrej.properties.model.Contact;

/**
 * @author balram.asati
 *
 */
public class CP_Contact_Enquery_Response {

	
	private Contact contact;
	private CPAPP_EnquiryRequest cpapp_EnquiryRequest;
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public CPAPP_EnquiryRequest getCpapp_EnquiryRequest() {
		return cpapp_EnquiryRequest;
	}
	public void setCpapp_EnquiryRequest(CPAPP_EnquiryRequest cpapp_EnquiryRequest) {
		this.cpapp_EnquiryRequest = cpapp_EnquiryRequest;
	} 
	
}

