--liquibase formatted sql

--changeset litvak:1
CREATE TABLE PERSON
(
  PERSON_ID   INTEGER PRIMARY KEY NOT NULL,
  EMAIL       VARCHAR(255),
  FIRST_NAME  VARCHAR(255),
  LAST_NAME   VARCHAR(255),
  MIDDLE_NAME VARCHAR(255),
  PHONE       VARCHAR(255)
);
--rollback DROP TABLE PERSON;

--changeset litvak:2
CREATE SEQUENCE PERSON_SEQ start with 1 increment by 1;
--rollback DROP SEQUENCE PERSON_SEQ;

--changeset litvak:3
INSERT INTO PERSON(PERSON_ID, EMAIL, FIRST_NAME, LAST_NAME, MIDDLE_NAME, PHONE)
values (PERSON_SEQ.NEXTVAL, 'litvak.alexey@gmail.com', 'Алексей', 'Литвак', 'Александрович', '+79262777650');
--rollback delete from person;