create table test(
    id int not null comment 'id',
    name varchar(50) comment '名称',
    password varchar(50) comment '密码',
    primary key (id)
) engine=innodb default charset =utf8mb4 comment = '测试';

drop table if exists demo;
create table demo(
                     id int not null comment 'id',
                     name varchar(50) comment '名称',
                     primary key (id)
) engine=innodb default charset =utf8mb4 comment = '测试';

drop table if exists ebook;
create table ebook(
                     id bigint not null comment 'id',
                     name varchar(50) comment '名称',
                     category1_id bigint comment '分类1',
                     category2_id bigint comment '分类2',
                     description varchar(200) comment '名称',
                     cover varchar(200) comment '封面',
                     doc_count int comment '文档数',
                     view_count int comment '阅读数',
                     vote_count int comment '点赞数',
                     primary key (id)
) engine=innodb default charset =utf8mb4 comment = '电子书';

insert into ebook(id,name,description) values (1,'Springboot 入门','零基础学习Java开发');
insert into ebook(id,name,description) values (2,'Vue 入门','零基础学习Vue开发');
insert into ebook(id,name,description) values (3,'Linux 入门','零基础学习Linux开发');
insert into ebook(id,name,description) values (4,'Java 入门','零基础学习Java开发');
insert into ebook(id,name,description) values (5,'Js 入门','零基础学习Vue开发');
insert into ebook(id,name,description) values (6,' Tomcat入门','零基础学习Linux开发');






