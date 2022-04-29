--Script to preload database with organizations
INSERT INTO organization (id,name,company_address,company_registration_number,bank_id,bank_account_number,org_uid,created_at) VALUES (10,'Allied Insurance','3,Glover Road, Ikoyi',1204304,12353498,23439084 ,'06364cbc-9468-4bfe-a917-1ab610bd49f1',CURRENT_TIMESTAMP);
INSERT INTO organization (id,name,company_address,company_registration_number,bank_id,bank_account_number,org_uid,created_at) VALUES (11,'Prest Insurance','3,Glover Road, Ikoyi',1200304,12353298,23439984 ,'06364cbc-9468-4bfe-a917-1ab610bd49a1',CURRENT_TIMESTAMP);
INSERT INTO organization (id,name,company_address,company_registration_number,bank_id,bank_account_number,org_uid,created_at) VALUES (12,'Unique Insurance','3,Glover Road, Ikoyi',1204104,12353499,21439084 ,'06364cbc-9468-4bfe-a917-1ab610bda9f1',CURRENT_TIMESTAMP);


--Script to preload database with Product Category
--INSERT INTO product_category (id,product_cat_uid,product_cat_name,created_at )  VALUES (1,'06324cbc-9468-4bfe-a917-1ab610bd49f1','HOUSEHOLD_INSURANCE', CURRENT_TIMESTAMP);
INSERT INTO product_category (id,product_cat_uid,product_cat_name,created_at )  VALUES (2,'06322cbc-9468-4bfe-a917-1ab610bd49f1','VEHICLE_INSURANCE', CURRENT_TIMESTAMP);


--Script to preload database with Products
--INSERT INTO product (id,product_uid,product_type,product_cat_id,created_at) VALUES(1,'06324cbc-9462-5bfe-a917-1ab610bd49f1','FIRE_PROTECTION',1, CURRENT_TIMESTAMP);
INSERT INTO product (id,product_uid,product_type,product_cat_id,created_at) VALUES(2,'06324cbc-9462-2bfe-a917-1ab610bd49f1','SEDAN',2, CURRENT_TIMESTAMP);
INSERT INTO product (id,product_uid,product_type,product_cat_id,created_at) VALUES(3,'06324cbc-9462-1bfe-a917-1ab610bd49f1','SUV',2, CURRENT_TIMESTAMP);
INSERT INTO product (id,product_uid,product_type,product_cat_id,created_at) VALUES(4,'06124cbc-9462-4bfe-a917-1ab610bd49f1','MOTOR_BIKE',2, CURRENT_TIMESTAMP);



--Script to preload with a user
INSERT INTO users (id, user_uid,first_name, last_name, email, password, created_at) VALUES(10, '03324cbc-9462-5bfe-a917-1ab610bd49f1','Emeka','Vin', 'emeka.vin@meedra.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', CURRENT_TIMESTAMP);


--Script to preload authority
INSERT INTO authority (id, name, users) VALUES (6,'READ', 10);


--Script to preload Insurance Factor
--INSERT INTO insurance_factor(id,organization_id, product_id,factor, created_at) VALUES(1,10,1,1.5,CURRENT_TIMESTAMP);
INSERT INTO insurance_factor(id,organization_id, product_id,factor, created_at) VALUES(2,10,2,1.4,CURRENT_TIMESTAMP);
INSERT INTO insurance_factor(id,organization_id, product_id,factor, created_at) VALUES(3,10,3,2.5,CURRENT_TIMESTAMP);
INSERT INTO insurance_factor(id,organization_id, product_id,factor, created_at) VALUES(4,10,4,1.8,CURRENT_TIMESTAMP);

--INSERT INTO insurance_factor(id,organization_id, product_id,factor, created_at) VALUES(5,11,1,1.4,CURRENT_TIMESTAMP);
INSERT INTO insurance_factor(id,organization_id, product_id,factor, created_at) VALUES(6,11,2,1.6,CURRENT_TIMESTAMP);
INSERT INTO insurance_factor(id,organization_id, product_id,factor, created_at) VALUES(7,11,3,1.3,CURRENT_TIMESTAMP);
INSERT INTO insurance_factor(id,organization_id, product_id,factor, created_at) VALUES(8,11,4,1.6,CURRENT_TIMESTAMP);

--INSERT INTO insurance_factor(id,organization_id, product_id,factor, created_at) VALUES(9,12,1,1.2,CURRENT_TIMESTAMP);
INSERT INTO insurance_factor(id,organization_id, product_id,factor, created_at) VALUES(10,12,2,1.7,CURRENT_TIMESTAMP);
INSERT INTO insurance_factor(id,organization_id, product_id,factor, created_at) VALUES(11,12,3,2.3,CURRENT_TIMESTAMP);
INSERT INTO insurance_factor(id,organization_id, product_id,factor, created_at) VALUES(12,12,4,1.9,CURRENT_TIMESTAMP);


--Script to create an organization customer
INSERT INTO org_customer(id, customer_uid, organization_id, first_name, last_name, bvn, gender, email,created_at )VALUES(1,'03324cbc-9462-5bfe-a917-1ab610bd42f1',10,'John', 'Bello','203493343', 'm','john.bello@gmail.com',CURRENT_TIMESTAMP);


--Script to create an insured item
INSERT INTO insured_item(id,item_uid,insurance_premium,organization_id,customer_id,insurance_status,payment_date,next_payment_date,created_at)VALUES(1,'02324cbc-9462-5bfe-a917-1ab610bd42f1',10000,10,1,'INSURED',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO insured_item(id,item_uid,insurance_premium,organization_id,customer_id,insurance_status,payment_date,next_payment_date,created_at)VALUES(2,'03324cbc-9162-5bfe-a917-1ab610bd42f1',1000,10,1,'INSURED',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)