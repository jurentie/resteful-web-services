INSERT INTO user_details(id,birth_date,name)
VALUES (10001, current_date(),'Justin');

INSERT INTO user_details(id,birth_date,name)
VALUES (10002, current_date(),'Steve');

INSERT INTO user_details(id,birth_date,name)
VALUES (10003, current_date(),'Sally');

INSERT INTO post(id,posted_date,description,user_id)
values(20001,current_date(),'I want to learn AWS', 10001);

INSERT INTO post(id,posted_date,description,user_id)
values(20002,current_date(),'I want to learn DevOps', 10001);

INSERT INTO post(id,posted_date,description,user_id)
values(20003,current_date(),'I want to learn DevOps', 10002);

INSERT INTO post(id,posted_date,description,user_id)
values(20004,current_date(),'I want to learn DevOps', 10002);