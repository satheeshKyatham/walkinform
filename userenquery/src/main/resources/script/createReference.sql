CREATE TABLE salesforce.m_reference
(
  m_reference_id bigint NOT NULL,
  isactive character varying(255),
  synchronised character varying(255),
  value character varying(255),
  description character varying(255),
  name character varying(255),
  CONSTRAINT m_reference_pkey PRIMARY KEY (m_reference_id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE salesforce.m_reference
  OWNER TO "myPostgresql";
  
  
  
  
CREATE SEQUENCE salesforce.m_reference_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE salesforce.m_reference_seq
  OWNER TO "myPostgresql";

