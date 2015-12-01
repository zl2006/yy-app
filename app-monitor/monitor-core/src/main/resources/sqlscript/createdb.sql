--grant all privileges on *.* to root@'%';

--创建数据库
create database if not exists monitor default charset utf8 collate utf8_general_ci;

--创建用户
CREATE USER monitor  IDENTIFIED  BY 'monitor' ;
 
--授权
grant all privileges on monitor.* to monitor@"%" identified  by 'monitor';
grant all privileges on monitor.* to monitor@"127.0.0.1" identified  by 'monitor';
grant all privileges on monitor.* to monitor@"localhost" identified  by 'monitor';
flush privileges;
