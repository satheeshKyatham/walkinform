CREATE TABLE salesforce.nv_hc_custom_enquiry (
	nv_hc_custom_enquiry_id int4 NOT NULL,
	sfid varchar(255),
	referred_partner_flag varchar(255),
	synchronised varchar(255),
	external_contact_id int4,
	enquiry_id int4,
	nv_hc_enquiry_id int4,
	PRIMARY KEY (nv_hc_custom_enquiry_id)
);

ALTER TABLE salesforce.nv_hc_custom_enquiry
	ADD FOREIGN KEY (external_contact_id) 
	REFERENCES salesforce.contact (id);

ALTER TABLE salesforce.nv_hc_custom_enquiry
	ADD FOREIGN KEY (enquiry_id) 
	REFERENCES salesforce.propstrength__request__c (id);

ALTER TABLE salesforce.nv_hc_custom_enquiry
	ADD FOREIGN KEY (nv_hc_enquiry_id) 
	REFERENCES salesforce.nv_hc_enquiry (nv_hc_enquiry_id);


