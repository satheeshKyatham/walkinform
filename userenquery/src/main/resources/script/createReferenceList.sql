CREATE TABLE salesforce.m_reference_list
(
  m_reference_list_id bigint NOT NULL,
  isactive character varying(255),
  synchronised character varying(255),
  value character varying(255),
  description character varying(255),
  name character varying(255),
  m_reference_id bigint,
  CONSTRAINT m_reference_list_pkey PRIMARY KEY (m_reference_list_id ),
  CONSTRAINT fk4n8ypnl8r9rjeulgaeyp5jwpg FOREIGN KEY (m_reference_id)
      REFERENCES salesforce.m_reference (m_reference_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE salesforce.m_reference_list
  OWNER TO "ubb3mhh56quq28";

  CREATE SEQUENCE salesforce.m_reference_list_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE salesforce.m_reference_list_seq
  OWNER TO "ubb3mhh56quq28";
