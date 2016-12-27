/********************************************
Create ERS User
********************************************/
create user ers identified by password;
grant dba to ers;
commit;


/********************************************
*********************************************
Create Reimbursement Tables
*********************************************
********************************************/

create table ers_reimbursement (
    reimb_id number primary key,
    reimb_amount number not null,
    reimb_submitted timestamp not null,
    reimb_resolved timestamp,
    reimb_decription varchar2(250),
    reimb_receipt blob,
    reimb_author number not null,
    reimb_resolver number not null,
    reimb_status_id number not null,
    reimb_type_id number not null,
    constraint ers_users_fk_auth foreign key (reimb_author) references ers_users(ers_users_id),
    constraint ers_users_fk_reslvr foreign key (reimb_resolver) references ers_users(ers_users_id),
    constraint ers_reimbursement_status_fk foreign key (reimb_status_id) references ers_reimbursement_status(reimb_status_id),
    constraint ers_reimbursement_type_fk foreign key (reimb_type_id) references ers_reimbursement_type(reimb_type_id)
);
commit;

ALTER TABLE ers_reimbursement
RENAME COLUMN reimb_decription TO reimb_description;
commit;

ALTER TABLE ers_reimbursement
  modify (reimb_resolver number);
commit;

create table ers_reimbursement_status(
    reimb_status_id number primary key,
    reimb_status varchar2(10) not null
);
 
create table ers_reimbursement_type(
    reimb_type_id number primary key,
    reimb_type varchar2(10) not null
);


/********************************************
*********************************************
Create User Tables
*********************************************
********************************************/

create table ers_users(
    ers_users_id number primary key,
    ers_username varchar2(50) unique not null,
    ers_password varchar2(50) not null,
    user_first_name varchar2(100) not null,
    user_last_name varchar2(100) not null,
    user_email varchar2(150) unique not null,
    user_role_id number not null,
    constraint user_roles_fk foreign key (user_role_id) references ers_user_roles(ers_user_role_id)
);
commit;

alter table ers_users
  modify (ers_password varchar2(60));
commit;

create table ers_user_roles(
    ers_user_role_id number primary key,
    user_role varchar2(10) not null
);
commit;


/********************************************
*********************************************
Insert Reimbursement Status & Type Values
        & User Role Values
*********************************************
********************************************/

insert into ers_reimbursement_status values(1, 'Pending');
insert into ers_reimbursement_status values(2, 'Approved');
insert into ers_reimbursement_status values(3, 'Denied');
commit;

insert into ers_reimbursement_type values(1, 'Lodging');
insert into ers_reimbursement_type values(2, 'Travel');
insert into ers_reimbursement_type values(3, 'Food');
insert into ers_reimbursement_type values(4, 'Other');
commit;

insert into ers_user_roles values(1, 'Manager');
insert into ers_user_roles values(2, 'Employee');
commit;


/********************************************
*********************************************
Insert Users into the User Table
*********************************************
********************************************/

insert into ers_users values(1, 'spiderman', 'spidey1', 'Peter', 'Parker', 'peterp@dailybugle.com', 2);
update ers_users 
  set ers_password = '$2a$06$l36iDioZrZXgj4UkwquM2u4YuHinXcnT5XPRn8bOFJeJXZ5boR2Wu'
  where ers_users_id=1;
commit;
insert into ers_users values(2, 'ironman', 'jarvis', 'Tony', 'Stark', 'ceo@starkindustries.com', 1);
update ers_users 
  set ers_password = '$2a$06$/YXI.ipwOsoQEmEp6y3IouK/s0/CjENon/Fye.Z2kbihRm0D6wFiW'
  where ers_users_id=2;
commit;
insert into ers_users values(3, 'hulk', 'hulksmash', 'Bruce', 'Banner', 'bruceb@harvard.edu', 1);
update ers_users 
  set ers_password = '$2a$06$NJ6E3iE2rhupQHtdDk0P.OzFovfGQlKZTOML8fHSsOIhii8OZFl/6'
  where ers_users_id=3;
commit;
insert into ers_users values(4, 'cap', 'murica', 'Steve', 'Rogers', 'captain.america@usa.com', 2);
insert into ers_users values(5, 'jjones', 'killgrave', 'Jessica', 'Jones', 'jjones.pi@gmail.com', 2);
insert into ers_users values(6, 'blackwidow', 'vision', 'Natasha', 'Romanoff', 'bwidow@avengers.com', 1);
commit;

insert into ers_users values(7, 'john', 'smith', 'John', 'Smith', 'jsmith@hotmail.com', 2);
update ers_users 
  set ers_password = '$2a$06$6DUkBpqIy.CHP42m2DfAl.atcspPkSVRfFawwvPX5NZZGmmS/jvpu'
  where ers_users_id=7;
commit;
insert into ers_users values(8, 'jane', '$2a$06$1RYeJilQUPbEuhKz0mb/2ORPM2H4jXo3NDqbH5/n4wWtmJjW1T0dq', 'Jane', 'Doe', 'jdoe@gmail.com', 2);
commit;


/********************************************
*********************************************
Insert Test Reimbs into the Reimbs Table
*********************************************
********************************************/

insert into ers_reimbursement values(5, 100.00,'21-OCT-13',null,null,null,1,2,2,1);
insert into ers_reimbursement values(8, 50.00,'21-OCT-13',null,null,null,1,2,1,2);
insert into ers_reimbursement values(1, 50.00,'21-OCT-13',null,null,null,8,2,1,2);
commit;

INSERT INTO ERS_REIMBURSEMENT(REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED,
REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID)
VALUES(9, 5.0, '12-NOV-16', 'pizza', 8, 1, 1);


/********************************************
*********************************************
Testing the Database- Reimbursements
*********************************************
********************************************/

-- Select all Reimbs
select * from ers_reimbursement;

-- Select all Types
select reimb_type from ers_reimbursement_type;

---- Select all Statuses
select reimb_status from ers_reimbursement_status;

--Select columns from Reimbs
select 
  reimb_id, reimb_amount, reimb_submitted,
  reimb_resolved, reimb_decription, reimb_receipt,
	reimb_author, reimb_resolver, reimb_status_id, reimb_type_id
from ers_reimbursement;

--Select columns from Reimbs joined with type and status
select r.reimb_id,  r.reimb_author, r.reimb_submitted, 
    t.reimb_type, r.reimb_amount, r.reimb_decription,
    r.reimb_resolver, s.reimb_status, r.reimb_resolved
from ers_reimbursement r
  inner join ers_reimbursement_status s
  on   s.reimb_status_id =r.reimb_status_id 
    inner join ers_reimbursement_type t
    on t.reimb_type_id = r.reimb_type_id
where reimb_status = 'Approved';

-- Join Reimbs and Users, Show Reimb ID
select reimb.reimb_id
from ers_reimbursement reimb
  inner join ers_users
  on ers_users.ers_users_id = reimb.reimb_author;
commit;

-- Get Reimb by ID
select REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_RESOLVED,
REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_RESOLVER, REIMB_STATUS_ID, REIMB_TYPE_ID
from ers_reimbursement
where reimb_id=8;

/********************************************
*********************************************
Testing the Database- Users
*********************************************
********************************************/
-- Select all Users
select * from ers_users;

-- Select User Full Name for ID=1
SELECT USER_FIRST_NAME, USER_LAST_NAME
FROM ERS_USERS
WHERE ERS_USERS_ID =1;

/*******************************************
Test getUserLoginInfo() in UserDAO
********************************************/
SELECT u.ers_users_id, u.user_first_name, u.user_last_name,
  u.user_email, r.ers_user_role_id, r.user_role
FROM ers_users u
		INNER JOIN ers_user_roles r
		ON r.ers_user_role_id = u.user_role_id
WHERE ers_username = 'jane'
AND ers_password = '$2a$06$1RYeJilQUPbEuhKz0mb/2ORPM2H4jXo3NDqbH5/n4wWtmJjW1T0dq';

/*******************************************
Test getReimbByAuthor() in ReimbDAO
********************************************/
insert into ers_reimbursement(reimb_amount, reimb_submitted, reimb_description, reimb_author,
reimb_type_id, reimb_status_id) values(5.0, '26-DEC-2016', 'testing db', 8, 1, 1);
commit;
select * from ers_reimbursement where reimb_author=8;

SELECT REIMB_ID, REIMB_AMOUNT,
s.REIMB_STATUS_ID, s.REIMB_STATUS, 
t.REIMB_TYPE_ID, t.REIMB_TYPE,
REIMB_DESCRIPTION, REIMB_SUBMITTED, REIMB_RESOLVED,
u.ERS_USERS_ID AS AUTHOR_ID, 
u.ERS_USERNAME AS AUTHOR_USERNAME, 
u.USER_FIRST_NAME AS AUTHOR_FIRST_NAME, 
u.USER_LAST_NAME AS AUTHOR_LAST_NAME, 
u.USER_EMAIL AS AUTHOR_EMAIL
FROM ERS_REIMBURSEMENT r
  JOIN ERS_REIMBURSEMENT_TYPE t
  ON r.REIMB_TYPE_ID = t.REIMB_TYPE_ID
    JOIN ERS_REIMBURSEMENT_STATUS s
    ON r.REIMB_STATUS_ID = s.REIMB_STATUS_ID
      Left JOIN ERS_USERS u
      ON r.REIMB_AUTHOR = u.ERS_USERS_ID
WHERE r.REIMB_AUTHOR = 8;
   
/*******************************************
Test getAllReimbs() in ReimbDAO
********************************************/

insert into ers_reimbursement(reimb_amount, reimb_submitted, reimb_description, reimb_author,
reimb_type_id, reimb_status_id) values(5.0, '26-DEC-2016', 'testing db for all reimbs', 1, 1, 1);
commit;
select * from ers_reimbursement;

update ers_reimbursement
set reimb_resolver=2
where reimb_id=9;
commit;

SELECT REIMB_ID, REIMB_AMOUNT,
s.REIMB_STATUS_ID, s.REIMB_STATUS, 
t.REIMB_TYPE_ID, t.REIMB_TYPE,
REIMB_DESCRIPTION, REIMB_SUBMITTED, REIMB_RESOLVED,
auth.ERS_USERS_ID AS AUTHOR_ID, 
auth.ERS_USERNAME AS AUTHOR_USERNAME, 
auth.USER_FIRST_NAME AS AUTHOR_FIRST_NAME, 
auth.USER_LAST_NAME AS AUTHOR_LAST_NAME, 
auth.USER_EMAIL AS AUTHOR_EMAIL,
  res.ERS_USERS_ID AS RESOLVER_ID, 
  res.ERS_USERNAME AS RESOLVER_USERNAME, 
  res.USER_FIRST_NAME AS RESOLVER_FIRST_NAME, 
  res.USER_LAST_NAME AS RESOLVER_LAST_NAME, 
  res.USER_EMAIL AS RESOLVER_EMAIL
FROM ERS_REIMBURSEMENT r
  JOIN ERS_REIMBURSEMENT_TYPE t
  ON r.REIMB_TYPE_ID = t.REIMB_TYPE_ID
    JOIN ERS_REIMBURSEMENT_STATUS s
    ON r.REIMB_STATUS_ID = s.REIMB_STATUS_ID
      Left JOIN ERS_USERS auth
      ON r.REIMB_AUTHOR = auth.ERS_USERS_ID
        Left join ers_users res
        on r.reimb_resolver = res.ERS_USERS_ID;

/*******************************************
Sequences & Triggers
********************************************/     

CREATE SEQUENCE REIMB_SEQ 
 START WITH 1
 INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER ERS_REIMB_TRIGGER
 BEFORE INSERT ON ERS_REIMBURSEMENT
 FOR EACH ROW
 DECLARE 
   TEMP NUMBER;
 BEGIN
   SELECT REIMB_SEQ.NEXTVAL
   INTO   TEMP
   FROM   DUAL;
   :NEW.REIMB_ID := TEMP;
END;
/
