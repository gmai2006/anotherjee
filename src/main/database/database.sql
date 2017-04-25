GRANT ALL PRIVILEGES ON snow.* TO 'snow_owner'@'127.0.0.1' IDENTIFIED BY 'snow2015$$' WITH GRANT OPTION;

create database snow;

GRANT ALL PRIVILEGES ON snow.* TO 'snow_owner'@'localhost' IDENTIFIED BY 'snow2015$$' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON snow.* TO 'snow_owner'@'192.168.1.%' IDENTIFIED BY 'snow2015$$' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON snow.* TO 'snow_owner'@'127.0.0.1' IDENTIFIED BY 'snow2015$$' WITH GRANT OPTION;


DROP TABLE IF EXISTS USERS ;
CREATE TABLE USERS (
  user_id VARCHAR(256) NOT NULL,
  password VARCHAR(64) NOT NULL,
  email_address VARCHAR(256) NOT NULL,
  first_name VARCHAR(128) NOT NULL,
  last_name VARCHAR(128) NOT NULL,
  company_id INTEGER NOT NULL,
  user_role int NOT NULL,
  activation_code VARCHAR(64) NULL,
  status INTEGER NOT NULL,
  invalid_login INTEGER NOT NULL,
  signup_time TIMESTAMP NULL,
  PRIMARY KEY(user_id)
);
CREATE TABLE COMPANY (
  id INTEGER NOT NULL,
  ein INTEGER NOT NULL,
  name VARCHAR(128) NOT NULL,
  address VARCHAR(1024) NULL,
  email_address VARCHAR(64) NOT NULL,
  city VARCHAR(1024) NULL,
  state VARCHAR(1024) NULL,
  phone VARCHAR(256) NULL,
  country VARCHAR(1024) NULL,
  zipcode INTEGER NOT NULL,
  sic_code INTEGER NULL,
  status INTEGER NOT NULL,
  consumer_rating INTEGER NULL,
  company_type INTEGER NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE VENDOR_CLIENT (
  vendor_id INTEGER NOT NULL,
  client_id INTEGER NOT NULL,
  assignment_date DATE NOT NULL,
  job_type VARCHAR(64) NULL,
  comment VARCHAR(1024) NULL,
  PRIMARY KEY(vendor_id, client_id)
  );
  
DROP TABLE IF EXISTS VENDOR_LOCATION;
CREATE TABLE VENDOR_LOCATION (
  user_id VARCHAR(256) NOT NULL,
  address VARCHAR(1024) NULL,
  city VARCHAR(1024) NULL,
  state VARCHAR(1024) NULL,
  phone VARCHAR(256) NULL,
  country VARCHAR(1024) NULL,
  zipcode INTEGER NULL,
  PRIMARY KEY(user_id, zipcode)
);

CREATE TABLE ZIPCODEGEO (
  zipcode INTEGER NOT NULL,
  lat FLOAT(10,6) NOT NULL,
  lng FLOAT(10,6) NOT NULL,
  address VARCHAR(1024) NULL,
  PRIMARY KEY(zipcode)
);

CREATE OR REPLACE VIEW COMPANY_VIEW AS
select c.*, z.lat, z.lng from COMPANY c
left outer join ZIPCODEGEO z on c.zipcode=z.zipcode;

CREATE OR REPLACE VIEW ASSIGNED_COMPANY_VIEW AS
select c.*, z.*  from COMPANY c, VENDOR_CLIENT z
where c.id=z.vendor_id or c.id = client_id;

  CREATE OR REPLACE VIEW ASSIGNED_CLIENT_VIEW AS
select c.*, z.job_type, z.comment  from COMPANY c
join VENDOR_CLIENT z on c.id=z.client_id;


