--liquibase formatted sql
--changeset litvak:1
INSERT INTO PERSON(PERSON_ID, EMAIL, FIRST_NAME, LAST_NAME, MIDDLE_NAME, PHONE)
values (PERSON_SEQ.NEXTVAL, 'litvak.alexey@gmail.com', 'Алексей', 'Литвак', 'Александрович', '+79262777650');
--rollback delete from person;