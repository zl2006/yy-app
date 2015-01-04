drop table if exists user_admin.TB_USERADMIN_SYSTEM;

/*==============================================================*/
/* Table: TB_USERADMIN_SYSTEM                                   */
/*==============================================================*/
create table user_admin.TB_USERADMIN_SYSTEM
(
   SYSTEM_ID            int(11) not null auto_increment comment '应用系统ID',
   SYSTEM_CODE          char(32) not null comment '应用系统编号',
   NAME                 char(64) not null comment '应用名称',
   ICON                 char(128) comment '应用图标',
   URL                  char(128) comment '应用URL',
   DESCRIPTION          char(255) comment '描述',
   STATUS               int(11) not null default 1 comment '状态，0表示无效，1表示有效',
   CREATE_TIME          timestamp not null comment '创建时间',
   CREATE_PERSON        int(11) comment '创建人',
   UPDATE_TIME          timestamp not null comment '更新时间',
   UPDATE_PERSON        int(11) comment '更新人',
   primary key (SYSTEM_ID),
   key AK_TB_USERADMIN_SYSTEM (SYSTEM_CODE)
);

alter table user_admin.TB_USERADMIN_SYSTEM comment '应用系统';



drop table if exists user_admin.TB_USERADMIN_LOG;

/*==============================================================*/
/* Table: TB_USERADMIN_LOG                                      */
/*==============================================================*/
create table user_admin.TB_USERADMIN_LOG
(
   LOG_ID               int(11) not null auto_increment comment '日志ID',
   OPERATOR_ID          int(11) not null comment '操作者ID',
   OPER_NAME            char(32) not null comment '操作者姓名',
   OPER_TYPE            char(8) not null comment '操作类型：增、删、改、查、审核等操作',
   BUSI_DATA_TYPE       char(32) comment '业务数据类型:需求要字典中定义，格式一般为：系统编号_模块编号_业务编号组成',
   BUIS_DATA_ID         int(11) comment '业务数据ID',
   REMARK               char(255) comment '描述',
   CREATE_TIME          timestamp not null comment '创建时间',
   UPDATE_TIME          timestamp not null comment '更新时间',
   primary key (LOG_ID)
);

alter table user_admin.TB_USERADMIN_LOG comment '日志表';



drop table if exists user_admin.TB_USERADMIN_DICTIONARY;

/*==============================================================*/
/* Table: TB_USERADMIN_DICTIONARY                               */
/*==============================================================*/
create table user_admin.TB_USERADMIN_DICTIONARY
(
   DIC_ID               int(11) not null auto_increment comment '字典ID',
   DIC_CODE             char(32) not null comment '字典编码',
   NAME                 char(64) not null comment '字典名称',
   VALUE                char(255) not null comment '字典值',
   ORDER_NO             int(11) not null default 0 comment '序号',
   TYPE                 char(16) not null comment '字典类型',
   STATUS               int(11) not null default 1 comment '状态',
   DESCRIPTION          char(255) comment '描述',
   CREATE_TIME          timestamp not null comment '创建时间',
   UPDATE_TIME          timestamp not null comment '更新时间',
   primary key (DIC_ID),
   key AK_TB_USERADMIN_DICTIONARY (DIC_CODE, TYPE)
);

alter table user_admin.TB_USERADMIN_DICTIONARY comment '字典表';


drop table if exists user_admin.TB_USERADMIN_ORGAN;

/*==============================================================*/
/* Table: TB_USERADMIN_ORGAN                                    */
/*==============================================================*/
create table user_admin.TB_USERADMIN_ORGAN
(
   ORGAN_ID             int(11) not null auto_increment comment '组织机构ID',
   ORGAN_CODE           char(32) not null comment '组织机构编号',
   PARENT_ORGAN_CODE    char(32) not null default '-1' comment '父组织机构编号,-1表示顶级组织机构',
   NAME                 char(128) not null comment '机构名称',
   SNAME                char(128) comment '机构简称',
   PRINCIPAL            char(64) comment '负责人',
   SECOND_PRINCIPAL     char(64) comment '第二负责人',
   TEL                  char(32) comment '电话',
   FAX                  char(32) comment '传真',
   POST_CODE            char(32) comment '邮编',
   STATUS               int(11) not null default 1 comment '状态 ： 1表示有效，0表示无效',
   HAS_CHILD            int default 0 comment '是否有子节点, 1表示有，0表示没有',
   DESCRIPTION          char(255) comment '机构描述',
   CREATE_TIME          timestamp not null comment '创建时间',
   CREATE_PERSON        int(11) comment '创建人',
   UPDATE_TIME          timestamp not null comment '更新时间',
   UPDATE_PERSON        int(11) comment '更新人',
   primary key (ORGAN_ID),
   key AK_TB_USERADMIN_ORGAN (ORGAN_CODE)
);

alter table user_admin.TB_USERADMIN_ORGAN comment '组织机构';




drop table if exists user_admin.TB_USERADMIN_USER;

/*==============================================================*/
/* Table: TB_USERADMIN_USER                                     */
/*==============================================================*/
create table user_admin.TB_USERADMIN_USER
(
   USER_ID              int(11) not null auto_increment comment '用户ID',
   LOGIN_ID             char(64) not null comment '登录ID',
   USERNAME             char(64) not null comment '用户姓名',
   PASSWORD             char(64) not null comment '密码',
   EMAIL                char(128) comment '邮箱',
   TEL                  char(32) comment '电话',
   SEX                  char(1) comment '性别:M男,F女',
   STATUS               int(11) not null default 1 comment '状态:1表示有效，0表示无效',
   ADDRESS              char(255) comment '地址',
   BIRTHDAY             timestamp default CURRENT_TIMESTAMP comment '生日',
   LATNNO               char(16) comment '所属地市',
   LOGIN_NUMBER         int(11) comment '登录次数',
   TOKEN                char(64) comment '访问身份',
   EXPIRE_DATE          timestamp comment '身份失效时间',
   LAST_LOGIN_TIME      timestamp comment '最后登录时间',
   CREATE_TIME          timestamp not null comment '创建时间',
   CREATE_PERSON        int(11) comment '创建人',
   UPDATE_TIME          timestamp not null comment '更新时间',
   UPDATE_PERSON        int(11) comment '更新人',
   primary key (USER_ID),
   key AK_TB_USERADMIN_USER (LOGIN_ID)
);

alter table user_admin.TB_USERADMIN_USER comment '用户信息';



drop table if exists user_admin.TB_USERADMIN_USERORGANREL;

/*==============================================================*/
/* Table: TB_USERADMIN_USERORGANREL                             */
/*==============================================================*/
create table user_admin.TB_USERADMIN_USERORGANREL
(
   LOGIN_ID             char(64) not null comment '登录ID',
   ORGAN_CODE           char(32) not null comment '组织机构编号',
   CREATE_TIME          timestamp not null comment '创建时间',
   UPDATE_TIME          timestamp not null comment '更新时间',
   primary key (ORGAN_CODE, LOGIN_ID)
);

alter table user_admin.TB_USERADMIN_USERORGANREL comment '用户组织机构关系';

alter table user_admin.TB_USERADMIN_USERORGANREL add constraint FK_REF_ORGAN_USER foreign key (LOGIN_ID)
      references user_admin.TB_USERADMIN_USER (LOGIN_ID);

alter table user_admin.TB_USERADMIN_USERORGANREL add constraint FK_REF_USER_ORGAN foreign key (ORGAN_CODE)
      references user_admin.TB_USERADMIN_ORGAN (ORGAN_CODE);

      
      
drop table if exists user_admin.TB_USERADMIN_USERGROUP;

/*==============================================================*/
/* Table: TB_USERADMIN_USERGROUP                                */
/*==============================================================*/
create table user_admin.TB_USERADMIN_USERGROUP
(
   USERGROUP_ID         int(11) not null auto_increment comment '用户组ID',
   GROUP_NAME           char(64) not null comment '用户组名称',
   DESCRIPTION          char(255),
   STATUS               int(11) not null default 1 comment '用户组状态:1为有效，0为无效',
   CREATE_TIME          timestamp not null comment '创建时间',
   CREATE_PERSON        int(11) comment '创建人',
   UPDATE_TIME          timestamp not null comment '更新时间',
   UPDATE_PERSON        int(11) comment '更新人',
   primary key (USERGROUP_ID)
);

alter table user_admin.TB_USERADMIN_USERGROUP comment '用户组';


drop table if exists user_admin.TB_USERADMIN_ROLE;

/*==============================================================*/
/* Table: TB_USERADMIN_ROLE                                     */
/*==============================================================*/
create table user_admin.TB_USERADMIN_ROLE
(
   ROLE_ID              int(11) not null auto_increment comment '角色ID',
   SYSTEM_CODE          char(32) comment '应用系统编号',
   ROLE_CODE            char(32) not null comment '角色编号',
   NAME                 char(64) not null comment '角色名称',
   DESCRIPTION          char(255) comment '角色描述',
   STATUS               int(11) not null default 1 comment '状态:0表示无效，1表示有效',
   CREATE_TIME          timestamp not null comment '创建时间',
   CREATE_PERSON        int(11) comment '创建人',
   UPDATE_TIME          timestamp not null comment '更新时间',
   UPDATE_PERSON        int(11) comment '更新人',
   primary key (ROLE_ID),
   key AK_TB_USERADMIN_ROLE (ROLE_CODE)
);

alter table user_admin.TB_USERADMIN_ROLE comment '角色';

alter table user_admin.TB_USERADMIN_ROLE add constraint FK_REF_ROLE_SYSTEM foreign key (SYSTEM_CODE)
      references user_admin.TB_USERADMIN_SYSTEM (SYSTEM_CODE) on delete restrict on update restrict;

      
drop table if exists user_admin.TB_USERADMIN_USERGROUPREL;

/*==============================================================*/
/* Table: TB_USERADMIN_USERGROUPREL                             */
/*==============================================================*/
create table user_admin.TB_USERADMIN_USERGROUPREL
(
   LOGIN_ID             national char(64) not null comment '登录ID',
   USERGROUP_ID         int(11) not null comment '用户组ID',
   CREATE_TIME          timestamp not null,
   UPDATE_TIME          timestamp not null,
   primary key (LOGIN_ID, USERGROUP_ID)
);

alter table user_admin.TB_USERADMIN_USERGROUPREL comment '用户组与用户关系';

alter table user_admin.TB_USERADMIN_USERGROUPREL add constraint FK_REF_GROUP_USER foreign key (LOGIN_ID)
      references user_admin.TB_USERADMIN_USER (LOGIN_ID);

alter table user_admin.TB_USERADMIN_USERGROUPREL add constraint FK_REF_USER_USERGROUP foreign key (USERGROUP_ID)
      references user_admin.TB_USERADMIN_USERGROUP (USERGROUP_ID);

      
drop table if exists user_admin.TB_USERADMIN_GROUPROLEREL;

/*==============================================================*/
/* Table: TB_USERADMIN_GROUPROLEREL                             */
/*==============================================================*/
create table user_admin.TB_USERADMIN_GROUPROLEREL
(
   ROLE_CODE            char(32) not null comment '角色编号',
   USERGROUP_ID         int(11) not null comment '用户组ID',
   CREATE_TIME          timestamp not null,
   UPDATE_TIME          timestamp not null,
   primary key (ROLE_CODE, USERGROUP_ID)
);

alter table user_admin.TB_USERADMIN_GROUPROLEREL comment '用户组角色关系';

alter table user_admin.TB_USERADMIN_GROUPROLEREL add constraint FK_GROUPROLEREL_TO_ROLE foreign key (ROLE_CODE)
      references user_admin.TB_USERADMIN_ROLE (ROLE_CODE);

alter table user_admin.TB_USERADMIN_GROUPROLEREL add constraint FK_REF_ROLE_USERGROUP foreign key (USERGROUP_ID)
      references user_admin.TB_USERADMIN_USERGROUP (USERGROUP_ID);

      
drop table if exists TB_USERADMIN_USERROLEREL;

/*==============================================================*/
/* Table: TB_USERADMIN_USERROLEREL                              */
/*==============================================================*/
create table TB_USERADMIN_USERROLEREL
(
   LOGIN_ID             char(64) not null comment '登录ID',
   ROLE_CODE            char(32) not null comment '角色编号',
   CREATE_TIME          timestamp not null,
   UPDATE_TIME          timestamp not null,
   primary key (LOGIN_ID, ROLE_CODE)
);

alter table TB_USERADMIN_USERROLEREL comment '用户与角色关系';

alter table TB_USERADMIN_USERROLEREL add constraint FK_REF_ROLE_USER foreign key (LOGIN_ID)
      references user_admin.TB_USERADMIN_USER (LOGIN_ID) on delete restrict on update restrict;

alter table TB_USERADMIN_USERROLEREL add constraint FK_REF_USER_ROLE foreign key (ROLE_CODE)
      references user_admin.TB_USERADMIN_ROLE (ROLE_CODE) on delete restrict on update restrict;


      
      
drop table if exists user_admin.TB_USERADMIN_RESOURCE;

/*==============================================================*/
/* Table: TB_USERADMIN_RESOURCE                                 */
/*==============================================================*/
create table user_admin.TB_USERADMIN_RESOURCE
(
   RES_ID               int(11) not null auto_increment comment '资源ID',
   SYSTEM_CODE          char(32) not null comment '应用系统编号',
   PARENT_RES_ID        int(11) not null comment '父资源ID,-1表示顶级系统资源',
   NAME                 char(64) not null comment '资源名称',
   ENAME				varchar(64) DEFAULT NULL comment '资源英文名称',
   URL                  char(128) comment '资源位置',
   TYPE                 char(2) not null comment '资源类型：0表示菜单（模块 ），1表示功能, 2表示数据列表中的权限。其中功能与数据列表中的权限都是挂在菜单下。',
   ICON                 char(128) comment '资源图标',
   ORDER_NO             int(11) not null default 0 comment '资源序号',
   DESCRIPTION          char(255) comment '备注',
   STATUS               int(11) not null default 1 comment '状态, 0表示无效，1表示有效',
   STYLE                char(128) comment '样式',
   CREATE_TIME          timestamp not null comment '创建时间',
   CREATE_PERSON        int(11) comment '创建人',
   UPDATE_TIME          timestamp not null comment '更新时间',
   UPDATE_PERSON        int(11) comment '更新人',
   primary key (RES_ID)
);

alter table user_admin.TB_USERADMIN_RESOURCE comment '资源模块，主要指菜单资源、功能资源、数据列表操作资源';

alter table user_admin.TB_USERADMIN_RESOURCE add constraint FK_REF_RESOURCE_SYSTEM foreign key (SYSTEM_CODE)
      references user_admin.TB_USERADMIN_SYSTEM (SYSTEM_CODE);


      
      
drop table if exists user_admin.TB_USERADMIN_POPEDOM;

/*==============================================================*/
/* Table: TB_USERADMIN_POPEDOM                                  */
/*==============================================================*/
create table user_admin.TB_USERADMIN_POPEDOM
(
   ROLE_CODE            char(32) not null comment '角色编码',
   RES_ID               int(11) not null comment '资源ID',
   CREATE_TIME          timestamp not null,
   UPDATE_TIME          timestamp not null,
   primary key (ROLE_CODE, RES_ID)
);

alter table user_admin.TB_USERADMIN_POPEDOM comment '角色与资源权限关系';

alter table user_admin.TB_USERADMIN_POPEDOM add constraint FK_REF_RES_ROLE foreign key (ROLE_CODE)
      references user_admin.TB_USERADMIN_ROLE (ROLE_CODE);

alter table user_admin.TB_USERADMIN_POPEDOM add constraint FK_REF_ROLE_RES foreign key (RES_ID)
      references user_admin.TB_USERADMIN_RESOURCE (RES_ID);
      
      
      
ALTER TABLE `user_admin`.`TB_USERADMIN_RESOURCE`  ADD COLUMN `HAS_CHILD` INT(1) NULL AFTER `STYLE`;


