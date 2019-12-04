ALTER TABLE salesforce.propstrength__request__c ADD COLUMN external_contact_id INTEGER;

ALTER TABLE salesforce.propstrength__request__c ADD CONSTRAINT fk_ext foreign KEY(external_contact_id) REFERENCES salesforce.contact(id);

alter table salesforce.propstrength__request__c add synchronised character varying(255);

--alter table salesforce.propstrength__request__c add isactive character varying(255);

--alter table salesforce.contact add isactive character varying(255);

alter table salesforce.propstrength__request__c add referred_partner_flag character varying(255);

update m_reference_list set isactive='N' where value='2.5 BHK' OR value='4 BHK';

--alter table salesforce.account add isactive character varying(255);

--alter table salesforce.propstrength__projects__c add isactive character varying(255);


alter table salesforce.m_reference add sfid character varying(255);
alter table salesforce.m_reference_list add sfid character varying(255);