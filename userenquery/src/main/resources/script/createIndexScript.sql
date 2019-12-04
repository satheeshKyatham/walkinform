CREATE INDEX pc_propstrength__property__c
ON salesforce.propstrength__property_charges__c(propstrength__property__c);

CREATE INDEX pl_propstrength__property__c
ON salesforce.propstrength__payment_plan_line_items__c(propstrength__property__c);

CREATE INDEX pc_propstrength__other_charges__c
ON salesforce.propstrength__property_charges__c(propstrength__other_charges__c);

CREATE INDEX pcpay_propstrength__other_charges__c
ON salesforce.propstrength__payment_plan_other_charges__c(propstrength__other_charges__c);

CREATE INDEX mobile_country_idx
ON salesforce.contact (Mobile__c,Country_Codes__c);



CREATE INDEX cp_name 
ON salesforce.account(name);


CREATE INDEX cp_sfid
ON salesforce.account(sfid);

CREATE INDEX External_Contact_ID__c_idx 
ON salesforce.propstrength__request__c(External_Contact_ID__c);

CREATE INDEX reference_id 
ON salesforce.m_reference(m_reference_id);

CREATE INDEX reference_value 
ON salesforce.m_reference(value);

CREATE INDEX m_reference_id 
ON salesforce.m_reference_list(m_reference_id);

CREATE INDEX request_id 
ON salesforce.propstrength__request__c(id);


CREATE INDEX contact_cp 
ON salesforce.contact(accountid);

CREATE INDEX contact_emailID 
ON salesforce.contact(email);

CREATE INDEX request_project_idx 
ON salesforce.propstrength__request__c(PropStrength__Project__c);

CREATE INDEX request_broker_account_idx 
ON salesforce.propstrength__request__c(propstrength__broker_account__c);

CREATE INDEX request_broker_contact_idx 
ON salesforce.propstrength__request__c(propstrength__broker_contact__c);

CREATE INDEX request_primary_contact_idx 
ON salesforce.propstrength__request__c(propstrength__primary_contact__c);

CREATE INDEX contact_id
ON salesforce.contact(id);

CREATE INDEX contact_sfid
ON salesforce.contact(sfid);


CREATE INDEX request_nv_hc_enquiry_idx 
ON salesforce.propstrength__request__c(NVHC_Enquiry_ID__c);

CREATE INDEX nv_hc_enquiry_idx 
ON salesforce.nv_hc_enquiry(nv_hc_enquiry_id);


CREATE INDEX contact_nv_hc_contact_idx
ON salesforce.contact(External_Contact_ID__c);

CREATE INDEX nv_hc_contact_idx
ON salesforce.nv_hc_contact(nv_hc_contact_id);
