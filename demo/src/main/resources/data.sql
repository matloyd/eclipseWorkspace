insert into course(id,name,created_date,last_updated_date) 
values (10001,'JPA in 100 steps!',sysdate(),sysdate());
insert into course(id,name,created_date,last_updated_date) 
values (10002,'Modern JAVA',sysdate(),sysdate());
insert into course(id,name,created_date,last_updated_date) 
values (10003,'SQL Methods',sysdate(),sysdate());
insert into course(id,name,created_date,last_updated_date) 
values (10004,'Hibernate Master Class',sysdate(),sysdate());

insert into passport(id,number) values (40001,'R55849');
insert into passport(id,number) values (40002,'A09890');
insert into passport(id,number) values (40003,'J20458');

insert into student(id,name,PASSPORT_ID) values (20001,'Ranga',40001);
insert into student(id,name,PASSPORT_ID) values (20002,'Adam',40002);
insert into student(id,name,PASSPORT_ID) values (20003,'Jane',40003);

insert into review(id,rating,description,course_id) values (50001,'5','Great course',10001);
insert into review(id,rating,description,course_id) values (50002,'2','Bad coverage',10001);
insert into review(id,rating,description,course_id) values (50003,'4','Nice topics',10003);