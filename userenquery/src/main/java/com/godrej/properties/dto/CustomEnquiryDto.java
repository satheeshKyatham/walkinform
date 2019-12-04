package com.godrej.properties.dto;

import com.godrej.properties.common.dto.CommonDto;
import com.godrej.properties.common.utilities.CommonValidations;

/**
 * 
 * @author Varsha Patil
 *
 */
public class CustomEnquiryDto extends CommonDto{

	/**
	 * 
	 */
	    private static final long serialVersionUID = 1L;
	    private Integer customEnquiryId;
	    private ContactDto contact;
	    private String isReferredByChannelPartnerFlag;
		private String synchronised;
		private EnquiryReportDto enquiryReport;
		private EnquiryDto enquiry;
		public Integer getCustomEnquiryId() {
			return customEnquiryId;
		}
		public void setCustomEnquiryId(Integer customEnquiryId) {
			this.customEnquiryId = customEnquiryId;
		}
		public ContactDto getContact() {
			return contact;
		}
		public void setContact(ContactDto contact) {
			this.contact = contact;
		}
		public String getIsReferredByChannelPartnerFlag() {
			return isReferredByChannelPartnerFlag;
		}
		public void setIsReferredByChannelPartnerFlag(String isReferredByChannelPartnerFlag) {
			this.isReferredByChannelPartnerFlag = isReferredByChannelPartnerFlag;
		}
		public String getSynchronised() {
			if(CommonValidations.isEmpty(synchronised)){
				return "N";
			}
			return synchronised;
		}
		public void setSynchronised(String synchronised) {
			this.synchronised = synchronised;
		}
		public EnquiryReportDto getEnquiryReport() {
			return enquiryReport;
		}
		public void setEnquiryReport(EnquiryReportDto enquiryReport) {
			this.enquiryReport = enquiryReport;
		}
		public EnquiryDto getEnquiry() {
			return enquiry;
		}
		public void setEnquiry(EnquiryDto enquiry) {
			this.enquiry = enquiry;
		}
		
		
}
