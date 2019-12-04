package com.godrej.properties.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.godrej.properties.common.model.CommonModel;

/**
 * 
 * @author Varsha Patil
 *
 */
@Entity
@Table(name="salesforce.nv_hc_custom_enquiry")
public class CustomEnquiry extends CommonModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer customEnquiryId;
    private Contact contact;
    private String isReferredByChannelPartnerFlag;
	private String synchronised;
	private EnquiryReport enquiryReport;
	private Enquiry enquiry;
	
	@Id
	@SequenceGenerator(allocationSize=1,name="salesforce.nv_hc_custom_enquiry_seq",sequenceName="salesforce.nv_hc_custom_enquiry_seq")
	@GeneratedValue(generator="salesforce.nv_hc_custom_enquiry_seq",strategy=GenerationType.SEQUENCE)
	@Column(name="nv_hc_custom_enquiry_id")
	public Integer getCustomEnquiryId() {
		return customEnquiryId;
	}
	public void setCustomEnquiryId(Integer customEnquiryId) {
		this.customEnquiryId = customEnquiryId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="external_contact_id",referencedColumnName="id")
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	@Column(name="referred_partner_flag")
	public String getIsReferredByChannelPartnerFlag() {
		return isReferredByChannelPartnerFlag;
	}
	public void setIsReferredByChannelPartnerFlag(String isReferredByChannelPartnerFlag) {
		this.isReferredByChannelPartnerFlag = isReferredByChannelPartnerFlag;
	}
	@Column(name="synchronised")
	public String getSynchronised() {
		return synchronised;
	}
	public void setSynchronised(String synchronised) {
		this.synchronised = synchronised;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nv_hc_enquiry_id")
	public EnquiryReport getEnquiryReport() {
		return enquiryReport;
	}
	public void setEnquiryReport(EnquiryReport enquiryReport) {
		this.enquiryReport = enquiryReport;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="enquiry_id",referencedColumnName="id")
	public Enquiry getEnquiry() {
		return enquiry;
	}
	public void setEnquiry(Enquiry enquiry) {
		this.enquiry = enquiry;
	}
    
}
