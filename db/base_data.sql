insert into survey (survey_id, name) values (1, 'satisfaction survey');

insert into question (question_id, question, survey_id) values (1, 'rate your experience',1);
insert into question (question_id, question, survey_id) values (2, 'how did you think the food',1);
insert into question (question_id, question, survey_id) values (3, 'would you come back',1);

insert into challenge.answer (answer_id, valor, question_id) values (1, 'bad', 1);
insert into challenge.answer (answer_id, valor, question_id) values (2, 'normal', 1);
insert into challenge.answer (answer_id, valor, question_id) values (3, 'great', 1);

insert into challenge.answer (answer_id, valor, question_id) values (4, 'delicious', 2);
insert into challenge.answer (answer_id, valor, question_id) values (5, 'ordinary', 2);
insert into challenge.answer (answer_id, valor, question_id) values (6, 'it could get better', 2);

insert into challenge.answer (answer_id, valor, question_id) values (7, 'Not', 3);
insert into challenge.answer (answer_id, valor, question_id) values (8, 'Yes', 3);