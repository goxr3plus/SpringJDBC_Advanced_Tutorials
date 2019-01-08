CREATE TABLE PERSON
(
  id integer not null,
  name varchar(255) not null,
  location varchar(255),
  birth_date timestamp,
  primary key(id)
);

insert into person(id,name,location,birth_date) values
(1001,'Ranga','Hyderbad',sysdate()),
(1002,'Maria','New York',sysdate()),
(1003,'Vaggelis','Australia',sysdate()),
(1004,'Alex','New York',sysdate());