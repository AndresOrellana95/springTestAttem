
CREATE USER demouser WITH ENCRYPTED PASSWORD 'Rand0mStr1ng';

DROP SCHEMA IF EXISTS donations CASCADE;
CREATE SCHEMA donations;
GRANT ALL PRIVILEGES ON SCHEMA donations to demouser;

-->UserLevel
CREATE SEQUENCE donations.user_level_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 10000
    CACHE 1;

GRANT USAGE, SELECT ON SEQUENCE donations.user_level_seq TO demouser;

CREATE TABLE donations.user_level(
    id_level integer NOT NULL DEFAULT nextval('donations.user_level_seq'::regclass),
    code CHARACTER VARYING(2) NOT NULL,
    name CHARACTER VARYING(50) NOT NULL,
    CONSTRAINT "UserLevelPkey" PRIMARY KEY (id_level)
);

GRANT ALL ON TABLE donations.user_level TO demouser;

-->Country
CREATE SEQUENCE donations.country_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 10000
    CACHE 1;

GRANT USAGE, SELECT ON SEQUENCE donations.country_seq TO demouser;

CREATE TABLE donations.country(
    id_country integer NOT NULL DEFAULT nextval('donations.country_seq'::regclass),
    code CHARACTER VARYING(2) NOT NULL,
    name CHARACTER VARYING(50) NOT NULL,
    CONSTRAINT "CountryPkey" PRIMARY KEY (id_country)
);

GRANT ALL ON TABLE donations.country TO demouser;

-->Country
CREATE SEQUENCE donations.company_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 10000
    CACHE 1;
	
GRANT USAGE, SELECT ON SEQUENCE donations.company_seq TO demouser;

CREATE TABLE donations.company(
	id_company integer NOT NULL DEFAULT nextval('donations.company_seq'::regclass),
	name CHARACTER VARYING(100) NOT NULL,
	id_country integer NOT NULL,
	CONSTRAINT "CopanyPkey" PRIMARY KEY (id_company),
	CONSTRAINT "Company_Country_fkey" FOREIGN KEY (id_country) REFERENCES donations.country (id_country)
);

GRANT ALL ON TABLE donations.company TO demouser;

-->User
CREATE SEQUENCE donations.user_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
	
GRANT USAGE, SELECT ON SEQUENCE donations.user_seq TO demouser;

-->User
CREATE TABLE donations.user(
	id_user integer NOT NULL DEFAULT nextval('donations.user_seq'::regclass),
	document CHARACTER VARYING(8) UNIQUE NOT NULL,
	name CHARACTER VARYING(100) NOT NULL,
	lastname CHARACTER VARYING(100) NOT NULL,
	password CHARACTER VARYING(100) NOT NULL,
	email CHARACTER VARYING(100) NOT NULL,
	id_level integer NOT NULL,
	CONSTRAINT "UserPkey" PRIMARY KEY (id_user),
	CONSTRAINT "User_UserLevel_fkey" FOREIGN KEY (id_level) REFERENCES donations.user_level (id_level)
);

GRANT ALL ON TABLE donations.user TO demouser;

-->Operations
CREATE SEQUENCE donations.operation_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
	
GRANT USAGE, SELECT ON SEQUENCE donations.operation_seq TO demouser;

CREATE TABLE donations.operation(
	id_operation integer NOT NULL DEFAULT nextval('donations.operation_seq'::regclass),
	transaction_uuid CHARACTER VARYING(50) NOT NULL,
	credit_card CHARACTER(16) NOT NULL,
	amount double precision NOT NULL,
	month_year CHARACTER VARYING(6) NOT NULL,
	execution timestamp with time zone,
	id_user integer NOT NULL,
	id_country integer NOT NULL,
	id_company integer NOT NULL,
	CONSTRAINT "OperationPkey" PRIMARY KEY (id_operation),
	CONSTRAINT "Operation_User_fkey" FOREIGN KEY (id_user) REFERENCES donations.user (id_user),
	CONSTRAINT "Operation_Country_fkey" FOREIGN KEY (id_country) REFERENCES donations.country (id_country),
	CONSTRAINT "Operation_Company_fkey" FOREIGN KEY (id_company) REFERENCES donations.company (id_company)
);

GRANT ALL ON TABLE donations.operation TO demouser;

-->Operations
CREATE SEQUENCE donations.binnacle_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
	
GRANT USAGE, SELECT ON SEQUENCE donations.binnacle_seq TO demouser;

CREATE TABLE donations.binnacle(
	id_binnacle integer NOT NULL DEFAULT nextval('donations.binnacle_seq'::regclass),
	id_user integer NOT NULL,
	action VARCHAR(50) NOT NULL,
	execution timestamp with time zone,
	CONSTRAINT "BinnaclePkey" PRIMARY KEY (id_binnacle)
);

GRANT ALL ON TABLE donations.binnacle TO demouser;


INSERT INTO donations.user_level VALUES(nextval('donations.user_level_seq'),'AD','Administrador');
INSERT INTO donations.user_level VALUES(nextval('donations.user_level_seq'),'US','Usuario');

-->Password admin: s3cr3tK3y4adm1nUs3r
INSERT INTO donations.user VALUES(nextval('donations.user_seq'),'00000000','Admin','Prueba','$2a$10$TgDnLpMGqZrk.dIt8qFimODPqTgTsaGRF61f2Y0uodHkAcHUYvYWa','prueba@gmail.com',1);

INSERT INTO donations.country VALUES(nextval('donations.country_seq'), 'SV','El Salvador');
INSERT INTO donations.country VALUES(nextval('donations.country_seq'), 'HN','Honduras');
INSERT INTO donations.country VALUES(nextval('donations.country_seq'), 'CR','Costa Rica');

INSERT INTO donations.company VALUES(nextval('donations.company_seq'), 'Empresa 1 El Salvador', 1);
INSERT INTO donations.company VALUES(nextval('donations.company_seq'), 'Empresa 2 El Salvador', 1);
INSERT INTO donations.company VALUES(nextval('donations.company_seq'), 'Empresa 3 El Salvador', 1);
INSERT INTO donations.company VALUES(nextval('donations.company_seq'), 'Empresa 1 Honduras', 2);
INSERT INTO donations.company VALUES(nextval('donations.company_seq'), 'Empresa 2 Honduras', 2);
INSERT INTO donations.company VALUES(nextval('donations.company_seq'), 'Empresa 3 Honduras', 2);
INSERT INTO donations.company VALUES(nextval('donations.company_seq'), 'Empresa 1 Costa Rica', 3);
INSERT INTO donations.company VALUES(nextval('donations.company_seq'), 'Empresa 2 Costa Rica', 3);
INSERT INTO donations.company VALUES(nextval('donations.company_seq'), 'Empresa 3 Costa Rica', 3);
