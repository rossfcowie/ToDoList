drop table if exists step CASCADE;
drop table if exists task CASCADE;
create table step (step_id integer, complete boolean not null, name varchar(255), fk_task_id integer, primary key (step_id));
create table task (id integer, descrition varchar(255), name varchar(255), primary key (id));
insert into step(step_id,complete,name) values(0,false,`Remove Trash`);