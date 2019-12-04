create sequence salesforce.nv_hc_contact_seq 
INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

create sequence salesforce.nv_hc_enquiry_seq
INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
create table salesforce.nv_hc_contact (
       nv_hc_contact_id int4 not null,
        sfid varchar(255),
        age_group varchar(255),
        current_residence_ownership varchar(255),
        current_residence_configuration varchar(255),
        customer_classification varchar(255),
        employment_status varchar(255),
        ethnicity varchar(255),
        gender varchar(255),
        employment_sector varchar(255),
        office_address varchar(255),
        office_city varchar(255),
        office_pincode varchar(255),
        contact_id int4,
        contact_sfid varchar(255),
        primary key (nv_hc_contact_id)
    )
    
    
  create table salesforce.nv_hc_enquiry (
       nv_hc_enquiry_id int4 not null,
        sfid varchar(255),
        accompanied_by varchar(255),
        allocated_sales_manager varchar(255),
        budget varchar(255),
        carpet_area_requirement varchar(255),
        construction_status varchar(255),
        deal_negotiation varchar(255),
        typology_requirement varchar(255),
        have_we_met_before varchar(255),
        purchase varchar(255),
        source_of_funding varchar(255),
        timeframe_to_book varchar(255),
        unit_availability varchar(255),
        vastu_preference varchar(255),
        enquiry_id int4,
        enquiry_sfid varchar(255),
        primary key (nv_hc_enquiry_id)
    )
    
    ALTER TABLE salesforce.contact ADD COLUMN nv_hc_contact_id INTEGER;
    alter table salesforce.contact 
       add constraint FKk7n8ai4vev2m24m0yr9lywu6w 
       foreign key (nv_hc_contact_id) 
       references salesforce.nv_hc_contact
       
       
  alter table salesforce.nv_hc_contact 
       add constraint FKjbtsy9qn35tbc066xr4gn8kve 
       foreign key (contact_id) 
       references salesforce.contact

       alter table salesforce.nv_hc_contact 
       add constraint FKf4q835yosoy31k3heem8kaucl 
       foreign key (contact_sfid) 
       references salesforce.contact (sfid)
    
    alter table salesforce.nv_hc_enquiry 
       add constraint FK74qt6sov819jxj898amh50t4s 
       foreign key (enquiry_id) 
       references salesforce.propstrength__request__c
       
       alter table salesforce.nv_hc_enquiry 
       add constraint FKnos5e4yke5ylyw8hruht09f5x 
       foreign key (enquiry_sfid) 
       references salesforce.propstrength__request__c (sfid)
       
       
       
     ALTER TABLE salesforce.propstrength__request__c ADD COLUMN nv_hc_enquiry_id INTEGER;
 
       alter table salesforce.propstrength__request__c 
       add constraint FKsxm3t5apalanxas9qx7xmbqmv 
       foreign key (nv_hc_enquiry_id) 
       references salesforce.nv_hc_enquiry
       
       
       
alter table salesforce.nv_hc_enquiry add isUpdated character varying(255);

alter table salesforce.nv_hc_contact add isUpdated character varying(255);



ALTER TABLE salesforce.nv_hc_enquiry ADD COLUMN external_contact_id INTEGER;
    alter table salesforce.nv_hc_enquiry 
       add constraint FK_external_contact_id
       foreign key (external_contact_id) 
       references salesforce.contact
       
       
alter table salesforce.nv_hc_enquiry add referred_partner_flag character varying(255);

alter table salesforce.nv_hc_enquiry add synchronised character varying(255);