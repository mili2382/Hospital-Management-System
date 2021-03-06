DROP TABLE IF EXISTS PATIENTS;
DROP TABLE IF EXISTS DOCTORS;

CREATE TABLE DOCTORS
(
	DOCTORID SERIAL primary key,
	USERNAME VARCHAR(100) not null unique,
	FIRSTNAME VARCHAR(100) not null, 
	LASTNAME VARCHAR(100) not null,
	PASSWORD VARCHAR(20) not null
);

Insert into DOCTORS (USERNAME,FIRSTNAME,LASTNAME,PASSWORD) values ('-','not','assigned','@#$%');
Insert into DOCTORS (USERNAME,FIRSTNAME,LASTNAME,PASSWORD) values ('Alex','Alexander','Ryan','234');
Insert into DOCTORS (USERNAME,FIRSTNAME,LASTNAME,PASSWORD) values ('Dave','David','Beckham','567');
Insert into DOCTORS (USERNAME,FIRSTNAME,LASTNAME,PASSWORD) values ('Hugh','Hugh','Jackman','890');


CREATE TABLE PATIENTS 
(
	PATIENTID SERIAL primary key,
	NAME VARCHAR(100),
	AGE INTEGER,
	SYMPTOMS VARCHAR(1000),
	ASSIGNED_DOCTOR INTEGER not null REFERENCES DOCTORS, 
	ADVICE VARCHAR(200) 
);

Insert into PATIENTS (NAME,AGE,SYMPTOMS,ASSIGNED_DOCTOR,ADVICE) values ('Steve Smith','30','Headache, fatigue and persistent cough',1,'Take Nurofen thrice a day');
Insert into PATIENTS (NAME,AGE,SYMPTOMS,ASSIGNED_DOCTOR,ADVICE) values ('Matthew Wade','32','Fever, dry cough and tiredness',2,'Take prescribed drug twice a day');
Insert into PATIENTS (NAME,AGE,SYMPTOMS,ASSIGNED_DOCTOR,ADVICE) values ('Kirsten Jones','52','Flu, cold, insomnia',3,'Take prescribed drug four times a day');
Insert into PATIENTS (NAME,AGE,SYMPTOMS,ASSIGNED_DOCTOR,ADVICE) values ('James Anderson','36','Shortness of breath, nausea, and sudden dizziness',3,'Stop smoking');

COMMIT;