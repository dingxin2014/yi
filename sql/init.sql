
drop table if exists t_yi_ktick;

/*==============================================================*/
/* Table: T_YI_KTICK                                            */
/*==============================================================*/
create table t_yi_ktick
(
   id                   bigint(20) auto_increment  not null comment 'id',
   kline_id              bigint(20),
   open                 numeric(30,20),
   close                numeric(30,20),
   low                  numeric(30,20),
   high                 numeric(30,20),
   vol                  numeric(30,20),
   count                bigint(20),
   primary key (id)
);
