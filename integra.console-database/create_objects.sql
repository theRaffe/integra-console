
CREATE TABLE CONSOLE_CAT_PROFILE (
    PROFILE_ID NUMBER(3) NOT NULL,
    PROFILE_NAME VARCHAR2(25) NOT NULL,
    CREATION_DATE TIMESTAMP (6), 
    LAST_UPDATE TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP,
    CREATION_USER VARCHAR2(50), 
    CONSTRAINT CONSOLE_CAT_PROFILE_NAME UNIQUE(PROFILE_NAME),
    CONSTRAINT CONSOLE_CAT_PROFILE_PK PRIMARY KEY ("PROFILE_ID")
);

CREATE TABLE CONSOLE_CAT_USER (
    USER_ID NUMBER(3) NOT NULL,
    USERNAME VARCHAR2(50) NOT NULL,
    PROFILE_ID NUMBER(3) NOT NULL,
    CREATION_DATE TIMESTAMP (6), 
    CREATION_USER VARCHAR2(50), 
    LAST_UPDATE TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP, 
    CONSTRAINT CONSOLE_CAT_USER_NAME UNIQUE(USERNAME),
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
    MENU_ID NUMBER(3) NOT NULL,
    USER_UPDATE VARCHAR2(50 BYTE),
    CREATION_DATE TIMESTAMP (6), 
    LAST_UPDATE TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP
);


insert into CONSOLE_CAT_PROFILE select 1, 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'initial' from dual;

insert into CONSOLE_CAT_USER select 1, 'rafael.briones.ext', 1, sysdate, 'initial', sysdate from dual;
--delete from CONSOLE_CAT_MENU_ITEM;
insert into CONSOLE_CAT_MENU_ITEM select 1, 'pantalla-1', null, sysdate, 'initial' from dual;
insert into CONSOLE_CAT_MENU_ITEM select 2, 'pantalla-1-1', 'action 1-1', sysdate, 'initial' from dual;
insert into CONSOLE_CAT_MENU_ITEM select 3, 'pantalla-2', null, sysdate, 'initial' from dual;
insert into CONSOLE_CAT_MENU_ITEM select 4, 'pantalla-2-1', 'action 2-1', sysdate, 'initial' from dual;
insert into CONSOLE_CAT_MENU_ITEM select 5, 'pantalla-2-2', null, sysdate, 'initial' from dual;
insert into CONSOLE_CAT_MENU_ITEM select 6, 'pantalla-2-2-1', 'action-2-2-1', sysdate, 'initial' from dual;

insert into CONSOLE_PROFILE_MENU select 1, null, 1, 'initial',sysdate, sysdate from dual;
insert into CONSOLE_PROFILE_MENU select 1, 1, 2, 'initial',sysdate, sysdate from dual;
insert into CONSOLE_PROFILE_MENU select 1, null, 3, 'initial',sysdate, sysdate from dual;
insert into CONSOLE_PROFILE_MENU select 1, 3, 4, 'initial',sysdate, sysdate from dual;
insert into CONSOLE_PROFILE_MENU select 1, 3, 5, 'initial',sysdate, sysdate from dual;
insert into CONSOLE_PROFILE_MENU select 1, 5, 6, 'initial',sysdate, sysdate from dual;


GRANT SELECT,UPDATE, INSERT, DELETE ON "CONSOLE_CAT_PROFILE" TO "INTFMW_USR";
GRANT SELECT,UPDATE, INSERT, DELETE ON "CONSOLE_CAT_USER" TO "INTFMW_USR";
GRANT SELECT,UPDATE, INSERT, DELETE ON "CONSOLE_CAT_MENU_ITEM" TO "INTFMW_USR";
GRANT SELECT,UPDATE, INSERT, DELETE ON "CONSOLE_PROFILE_MENU" TO "INTFMW_USR";
