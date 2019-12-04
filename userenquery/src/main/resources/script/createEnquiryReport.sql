 create table salesforce.nv_enquiry_report (
       nv_enquiry_report_id int4 not null,
        sfid varchar(255),
        accompanied_by varchar(255),
        construction_status varchar(255),
        customer_lassification varchar(255),
        deal_negotiation varchar(255),
        ethnicity varchar(255),
        gender varchar(255),
        timeframe_to_book varchar(255),
        unit_availability varchar(255),
        vastu_preference varchar(255),
        enquiry_id int4,
        primary key (nv_enquiry_report_id)
    )

     alter table salesforce.nv_enquiry_report 
       add constraint FK7w3kw0uo3hv3vekthna8jtik1 
       foreign key (enquiry_id) 
       references salesforce.propstrength__request__c


ALTER TABLE salesforce.propstrength__request__c ADD COLUMN nv_enquiry_report_id INTEGER;

ALTER TABLE salesforce.propstrength__request__c ADD CONSTRAINT fk_er foreign KEY(nv_enquiry_report_id) REFERENCES salesforce.nv_enquiry_report(nv_enquiry_report_id);



CREATE SEQUENCE salesforce.nv_enquiry_report_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;