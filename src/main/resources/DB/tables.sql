CREATE TABLE PERSON (
        ID NUMBER(12) NOT NULL ,
        FIRST_NAME VARCHAR2(45),
        LAST_NAME VARCHAR(45),
        EMAIL VARCHAR2(45),
        PRIMARY KEY ( ID )
        );

create sequence seq_person;