
CREATE TABLE CONSOLE_CAT_PROFILE (
    PROFILE_ID NUMBER(3) NOT NULL,
    PROFILE_NAME VARCHAR2(25) NOT NULL,
    CREATION_DATE TIMESTAMP (6), 
    LAST_UPDATE TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP,
    CREATION_USER VARCHAR2(50),
    ACTIVE CHAR(1) DEFAULT('Y'),	
    CONSTRAINT CONSOLE_CAT_PROFILE_NAME UNIQUE(PROFILE_NAME),
	CONSTRAINT CONSOLE_CAT_PROFILE_ACTIVE CHECK (ACTIVE in ('N','Y')),
    CONSTRAINT CONSOLE_CAT_PROFILE_PK PRIMARY KEY ("PROFILE_ID")
);

CREATE TABLE CONSOLE_CAT_USER (
    USER_ID NUMBER(3) NOT NULL,
    USERNAME VARCHAR2(50) NOT NULL,
    PROFILE_ID NUMBER(3) NOT NULL,
    CREATION_DATE TIMESTAMP (6), 
    CREATION_USER VARCHAR2(50), 
    LAST_UPDATE TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP,
	ACTIVE CHAR(1) DEFAULT('Y'),
    CONSTRAINT CONSOLE_CAT_USER_NAME UNIQUE(USERNAME),
	CONSTRAINT CONSOLE_CAT_USER_ACTIVE CHECK (ACTIVE in ('N','Y')),
    CONSTRAINT CONSOLE_CAT_USER_PK PRIMARY KEY ("USER_ID")
);

CREATE TABLE CONSOLE_CAT_MENU_ITEM (
    MENU_ITEM_ID NUMBER(3) NOT NULL,
    MENU_ITEM_NAME VARCHAR2(256), 
    MENU_ACTION VARCHAR2(256), 
    CREATION_DATE TIMESTAMP (6), 
    CREATION_USER VARCHAR2(50),
    CONSTRAINT CONSOLE_CAT_MENU_ITEM_NAME UNIQUE(MENU_ITEM_NAME),
    CONSTRAINT CONSOLE_CAT_MENU_ITEM_PK PRIMARY KEY (MENU_ITEM_ID)    
);


CREATE TABLE CONSOLE_PROFILE_MENU (
    PROFILE_ID NUMBER(3) NOT NULL,
    MENU_PARENT_ID NUMBER(3) NULL,
    ORDER_ID NUMBER(3) NOT NULL,
    MENU_ID NUMBER(3) NOT NULL,
    USER_UPDATE VARCHAR2(50 BYTE),
    CREATION_DATE TIMESTAMP (6), 
    LAST_UPDATE TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP
);

insert into CONSOLE_CAT_PROFILE select 1, 'ROLE_ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'initial', 'Y' from dual;
insert into CONSOLE_CAT_PROFILE select MAX(PROFILE_ID) + 1, 'ROLE_SUPPORT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'initial', 'Y' from CONSOLE_CAT_PROFILE;

insert into CONSOLE_CAT_USER select 1, 'rafael.briones.ext', 1, sysdate, 'initial', sysdate, 'Y' from dual;
insert into CONSOLE_CAT_USER select MAX(USER_ID)+1, 'p-rbriones', 1, sysdate, 'initial', sysdate, 'Y' from CONSOLE_CAT_USER;
insert into CONSOLE_CAT_USER select MAX(USER_ID)+1, 'p-tmoreno', 1, sysdate, 'initial', sysdate, 'Y' from CONSOLE_CAT_USER;
insert into CONSOLE_CAT_USER select MAX(USER_ID)+1, 'laura.hernandez.ext', 1, sysdate, 'initial', sysdate, 'Y' from CONSOLE_CAT_USER;


--delete from CONSOLE_CAT_MENU_ITEM;
insert into CONSOLE_CAT_MENU_ITEM select 1, 'pantalla-1', null, sysdate, 'initial' from dual;
insert into CONSOLE_CAT_MENU_ITEM select 2, 'pantalla-1-1', 'action 1-1', sysdate, 'initial' from dual;
insert into CONSOLE_CAT_MENU_ITEM select 3, 'pantalla-2', null, sysdate, 'initial' from dual;
insert into CONSOLE_CAT_MENU_ITEM select 4, 'pantalla-2-1', 'action 2-1', sysdate, 'initial' from dual;
insert into CONSOLE_CAT_MENU_ITEM select 5, 'pantalla-2-2', null, sysdate, 'initial' from dual;
insert into CONSOLE_CAT_MENU_ITEM select 6, 'pantalla-2-2-1', 'action-2-2-1', sysdate, 'initial' from dual;

insert into CONSOLE_PROFILE_MENU select 1, null, 1, 1, 'initial',sysdate, sysdate from dual;
insert into CONSOLE_PROFILE_MENU select 1, 1, 1, 2, 'initial',sysdate, sysdate from dual;
insert into CONSOLE_PROFILE_MENU select 1, null, 2, 3, 'initial',sysdate, sysdate from dual;
insert into CONSOLE_PROFILE_MENU select 1, 3, 2, 4, 'initial',sysdate, sysdate from dual;
insert into CONSOLE_PROFILE_MENU select 1, 3, 1, 5, 'initial',sysdate, sysdate from dual;
insert into CONSOLE_PROFILE_MENU select 1, 5, 1, 6, 'initial',sysdate, sysdate from dual;

GRANT SELECT,UPDATE, INSERT, DELETE ON "CONSOLE_CAT_PROFILE" TO "INTFMW_USR";
GRANT SELECT,UPDATE, INSERT, DELETE ON "CONSOLE_CAT_USER" TO "INTFMW_USR";
GRANT SELECT,UPDATE, INSERT, DELETE ON "CONSOLE_CAT_MENU_ITEM" TO "INTFMW_USR";
GRANT SELECT,UPDATE, INSERT, DELETE ON "CONSOLE_PROFILE_MENU" TO "INTFMW_USR";

-----------------------------------------------------------------------------------------------------------------------------------
--SEQUENCES------------------------------------------------------------------------------------------------------------------------
CREATE SEQUENCE CONSOLE_SEQ_PROFILE
START WITH 1
MAXVALUE 1000000000000
MINVALUE 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

REM UPDATING sequence CONSOLE_SEQ_PROFILE
SELECT max_x - CONSOLE_SEQ_PROFILE.NEXTVAL + 1 inc
FROM (
    SELECT MAX(PROFILE_ID) max_x 
    FROM CONSOLE_CAT_PROFILE);

ALTER SEQUENCE CONSOLE_SEQ_PROFILE INCREMENT BY &inc;
SELECT CONSOLE_SEQ_PROFILE.NEXTVAL FROM DUAL;
ALTER SEQUENCE CONSOLE_SEQ_PROFILE INCREMENT BY 1;


CREATE SEQUENCE CONSOLE_SEQ_USER
START WITH 1
MAXVALUE 1000000000000
MINVALUE 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

REM UPDATING sequence CONSOLE_SEQ_USER
SELECT max_x - CONSOLE_SEQ_USER.NEXTVAL + 1 inc
FROM (
    SELECT MAX(USER_ID) max_x 
    FROM CONSOLE_CAT_USER);

ALTER SEQUENCE CONSOLE_SEQ_USER INCREMENT BY &inc;
SELECT CONSOLE_SEQ_USER.NEXTVAL FROM DUAL;
ALTER SEQUENCE CONSOLE_SEQ_USER INCREMENT BY 1;


CREATE SEQUENCE CONSOLE_SEQ_MENU
START WITH 1
MAXVALUE 1000000000000
MINVALUE 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

REM UPDATING sequence CONSOLE_SEQ_MENU
SELECT max_x - CONSOLE_SEQ_MENU.NEXTVAL + 1 inc
FROM (
    SELECT MAX(MENU_ITEM_ID) max_x 
    FROM CONSOLE_CAT_MENU_ITEM);

ALTER SEQUENCE CONSOLE_SEQ_MENU INCREMENT BY &inc;
SELECT CONSOLE_SEQ_MENU.NEXTVAL FROM DUAL;
ALTER SEQUENCE CONSOLE_SEQ_MENU INCREMENT BY 1;

-----------------------------------------------------------------------------------------------------------------------------------

