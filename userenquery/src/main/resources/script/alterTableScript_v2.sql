
alter table propstrength__request__c add walk_in_remarks__c character varying(255);

alter table propstrength__request__c add Is_purchase_for_Self_Use_or_Investment__c character varying(255);

alter table salesforce.propstrength__request__c add Advertisement__c character varying(255);


ALTER TABLE salesforce.contact ALTER COLUMN Mailing_Street1__c TYPE character varying(255);

ALTER TABLE salesforce.contact ALTER COLUMN Mailing_Street2__c TYPE character varying(255);

ALTER TABLE salesforce.contact ALTER COLUMN Mailing_Street3__c TYPE character varying(255);


ALTER TABLE salesforce.nv_hc_enquiry add COLUMN external_contact_id INTEGER;

ALTER TABLE salesforce.nv_hc_enquiry add COLUMN enquiry_sfid character varying(255);

ALTER TABLE salesforce.nv_hc_enquiry add COLUMN enquiry_id INTEGER;

ALTER TABLE salesforce.nv_hc_contact add COLUMN contact_sfid character varying(255);

ALTER TABLE salesforce.nv_hc_contact add COLUMN contact_id INTEGER;