DELETE from salesforce.m_reference_list rl WHERE  rl.m_reference_id 
IN (SELECT r.m_reference_id FROM  salesforce.m_reference r WHERE  r.value='PURPOSE'); 

                            
INSERT INTO salesforce.M_REFERENCE_LIST(M_REFERENCE_LIST_ID,NAME,VALUE,DESCRIPTION,M_REFERENCE_ID,ISACTIVE)values(NEXTVAL('salesforce."m_reference_list_seq"'),'Self Use','Self Use','Self Use',2,'Y');
INSERT INTO salesforce.M_REFERENCE_LIST(M_REFERENCE_LIST_ID,NAME,VALUE,DESCRIPTION,M_REFERENCE_ID,ISACTIVE)values(NEXTVAL('salesforce."m_reference_list_seq"'),'Investment','Investment','Investment',2,'Y');
INSERT INTO salesforce.M_REFERENCE_LIST(M_REFERENCE_LIST_ID,NAME,VALUE,DESCRIPTION,M_REFERENCE_ID,ISACTIVE)values(NEXTVAL('salesforce."m_reference_list_seq"'),'Weekend / Second home','Weekend / Second home','Weekend / Second home',2,'Y');
INSERT INTO salesforce.M_REFERENCE_LIST(M_REFERENCE_LIST_ID,NAME,VALUE,DESCRIPTION,M_REFERENCE_ID,ISACTIVE)values(NEXTVAL('salesforce."m_reference_list_seq"'),'Others','Others','Others',2,'Y');


DELETE from salesforce.m_reference_list rl WHERE  rl.m_reference_id
IN (SELECT r.m_reference_id FROM  salesforce.m_reference r WHERE  r.value='INDUSTRY');


insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Agent','Agent','Agent',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Agri/Dairy','Agri/Dairy','Agri/Dairy',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Arch/Interior Design','Arch/Interior Design','Arch/Interior Design',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'ATM','ATM','ATM',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Atta Chaki','Atta Chaki','Atta Chaki',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Auto/Auto Ancillary','Auto/Auto Ancillary','Auto/Auto Ancillary',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Ayurvedic Clinic','Ayurvedic Clinic','Ayurvedic Clinic',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Bank/Fin/Broking','Bank/Fin/Broking','Bank/Fin/Broking',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Boutique','Boutique','Boutique',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'BPO/ITES/CRM/Transcr','BPO/ITES/CRM/Transcr','BPO/ITES/CRM/Transcr',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Chem/Petro/Plas/Rub','Chem/Petro/Plas/Rub','Chem/Petro/Plas/Rub',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Chemist / Pharmacy','Chemist / Pharmacy','Chemist / Pharmacy',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Chocolate Room','Chocolate Room','Chocolate Room',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Computer Shop','Computer Shop','Computer Shop',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Cons/Engg/Cem/Metal','Cons/Engg/Cem/Metal','Cons/Engg/Cem/Metal',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Consumer Durables','Consumer Durables','Consumer Durables',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Content/Journalism','Content/Journalism','Content/Journalism',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Corp Plan/Consult','Corp Plan/Consult','Corp Plan/Consult',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Cou/Trans/Freight','Cou/Trans/Freight','Cou/Trans/Freight',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Dairy Product','Dairy Product','Dairy Product',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Defence/Govt.','Defence/Govt.','Defence/Govt.',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Dentist','Dentist','Dentist',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Dry Cleaner','Dry Cleaner','Dry Cleaner',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Eatery','Eatery','Eatery',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Edu/Teach/Training','Edu/Teach/Training','Edu/Teach/Training',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Electrical Shop & Re','Electrical Shop & Re','Electrical Shop & Re',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Electronics Shop','Electronics Shop','Electronics Shop',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Engg Deg/R&D','Engg Deg/R&D','Engg Deg/R&D',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Export/Import','Export/Import','Export/Import',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Fashion/Garm/Merchan','Fashion/Garm/Merchan','Fashion/Garm/Merchan',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Fert/Pesticides','Fert/Pesticides','Fert/Pesticides',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Florist','Florist','Florist',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'FMCG/FOOD/Bevg','FMCG/FOOD/Bevg','FMCG/FOOD/Bevg',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Footwear','Footwear','Footwear',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Gem/Jewellery','Gem/Jewellery','Gem/Jewellery',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'General Physician','General Physician','General Physician',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Guard/Security Ser','Guard/Security Ser','Guard/Security Ser',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Hardware & Carpenter','Hardware & Carpenter','Hardware & Carpenter',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Healthcare/Med/R&D','Healthcare/Med/R&D','Healthcare/Med/R&D',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Home and Kitchen','Home and Kitchen','Home and Kitchen',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Homoeopathic Clinic','Homoeopathic Clinic','Homoeopathic Clinic',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Hotel/Rest/Air/Trave','Hotel/Rest/Air/Trave','Hotel/Rest/Air/Trave',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'HR/Admin/IR','HR/Admin/IR','HR/Admin/IR',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Ice Cream Parlour','Ice Cream Parlour','Ice Cream Parlour',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Ind Pro/Hevy Machine','Ind Pro/Hevy Machine','Ind Pro/Hevy Machine',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Insurance','Insurance','Insurance',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'IT Hardware &Network','IT Hardware &Network','IT Hardware &Network',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'IT Software/Soft Ser','IT Software/Soft Ser','IT Software/Soft Ser',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Jewellery','Jewellery','Jewellery',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Ladies Beauy Parlour','Ladies Beauy Parlour','Ladies Beauy Parlour',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Ladies Corner','Ladies Corner','Ladies Corner',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Ladies Garment Shop','Ladies Garment Shop','Ladies Garment Shop',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Legal','Legal','Legal',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Market/Advt/MR/PR','Market/Advt/MR/PR','Market/Advt/MR/PR',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Med/Health/Hospital','Med/Health/Hospital','Med/Health/Hospital',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Media/Dotcom/Entert','Media/Dotcom/Entert','Media/Dotcom/Entert',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Mobile Store','Mobile Store','Mobile Store',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'NGO/Social Services','NGO/Social Services','NGO/Social Services',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Office Eqp/Automat','Office Eqp/Automat','Office Eqp/Automat',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Office Space','Office Space','Office Space',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'One Stop Shop','One Stop Shop','One Stop Shop',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Optical Shop','Optical Shop','Optical Shop',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Others','Others','Others',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Packaging','Packaging','Packaging',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Pan / Soda Center','Pan / Soda Center','Pan / Soda Center',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Pathology','Pathology','Pathology',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Pharma/Biotech','Pharma/Biotech','Pharma/Biotech',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Photo Studio','Photo Studio','Photo Studio',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Prod/Main/Quality','Prod/Main/Quality','Prod/Main/Quality',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Purch/Logis/Supply','Purch/Logis/Supply','Purch/Logis/Supply',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'RO Service & Distrib','RO Service & Distrib','RO Service & Distrib',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Sales/BD','Sales/BD','Sales/BD',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Saloon','Saloon','Saloon',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Sports,Toys & Gift','Sports,Toys & Gift','Sports,Toys & Gift',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Tailor','Tailor','Tailor',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Tapesttry/ Home Furn','Tapesttry/ Home Furn','Tapesttry/ Home Furn',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Telecom/ISP','Telecom/ISP','Telecom/ISP',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Telecom outlet','Telecom outlet','Telecom outlet',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Travel Agent','Travel Agent','Travel Agent',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Vegetable Shop','Vegetable Shop','Vegetable Shop',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Video Library/ Cyber','Video Library/ Cyber','Video Library/ Cyber',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Watches','Watches','Watches',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Xerox/ Books/ Statio','Xerox/ Books/ Statio','Xerox/ Books/ Statio',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Acct/Taxation/Fin','Acct/Taxation/Fin','Acct/Taxation/Fin',8,'Y');
insert into  salesforce.m_reference_list(m_reference_list_id,name,value,description,m_reference_id,isactive) values(NEXTVAL('salesforce."m_reference_list_seq"'),'Advt/PR/MR/Events','Advt/PR/MR/Events','Advt/PR/MR/Events',8,'Y');



INSERT INTO salesforce.M_REFERENCE_LIST(M_REFERENCE_LIST_ID,NAME,VALUE,DESCRIPTION,M_REFERENCE_ID,ISACTIVE)values(NEXTVAL('salesforce."m_reference_list_seq"'),'4 BHK','4 BHK','4 BHK',43,'Y');
INSERT INTO salesforce.M_REFERENCE_LIST(M_REFERENCE_LIST_ID,NAME,VALUE,DESCRIPTION,M_REFERENCE_ID,ISACTIVE)values(NEXTVAL('salesforce."m_reference_list_seq"'),'Villa','Villa','Villa',43,'Y');






