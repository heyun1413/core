
drop table if exists t_module;

create table t_module(
  id int primary key auto_increment,
  name varchar(50) not null,
  create_time datetime not null default now(),
  update_time datetime not null default now(),
  table_name varchar(30) not null,
  status enum('ENABLE', 'DISABLE') default 'ENABLE',
  table_id int not null,
  foreign key(table_id) references t_table(id)
);

drop table if exists t_column;
drop table if exists t_table;

create table t_table(
  id int primary key auto_increment,
  name varchar(50) not null unique
);

drop table if exists t_column;

create table t_column(
  id int primary key auto_increment,
  name varchar(50) not null,
  nullable bool not null default false ,
  column_type enum('INTEGER', 'DOUBLE', 'TEXT', 'LONGTEXT') default 'TEXT',
  table_id int not null,
  foreign_key_name char(32),
  relation_table_id int,
  foreign key(table_id) references t_table(id)
);

drop table if exists t_input_type;
create table t_input_type(
  id int primary key auto_increment,
  label varchar(50) not null,
  value varchar(50) not null
);

insert into t_input_type values(default, '文本框', 'input');
insert into t_input_type values(default, '下拉框', 'select');
insert into t_input_type values(default, '单选框', 'radio');
insert into t_input_type values(default, '复选框', 'checkbox');
insert into t_input_type values(default, '日期选择器', 'date-picker');
insert into t_input_type values(default, '开关', 'switch');
insert into t_input_type values(default, '文本域', 'textarea');

