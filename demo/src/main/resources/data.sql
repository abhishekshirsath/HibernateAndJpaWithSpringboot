insert into course(id, name,is_deleted) values(10001,'JPA in 50 steps',false);
insert into course(id, name,is_deleted) values(10002,'Spring boot in 50 steps',false);
insert into course(id, name,is_deleted) values(10003,'Jenkins in 50 steps',false);	


insert into passport(id, number) values(40001,'E12345');
insert into passport(id, number) values(40002,'B12347');
insert into passport(id, number) values(40003,'J56789');

insert into student(id, name,passport_id) values(20001,'Ranga',40001);
insert into student(id, name,passport_id) values(20002,'Adam',40002);
insert into student(id, name,passport_id) values(20003,'Jane',40003);


insert into review(id,rating, description,course_id) values(50001,'FIVE','Great Course',10001);
insert into review(id,rating, description,course_id) values(50002,'FOUR','Wonderful',10001);
insert into review(id,rating, description,course_id) values(50003,'FIVE','good',10003);

insert into student_course(student_id, course_id) values(20001,10001);
insert into student_course(student_id, course_id) values(20002,10001);
insert into student_course(student_id, course_id) values(20003,10001);
insert into student_course(student_id, course_id) values(20001,10003);