/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     3/11/2019 18:40:34                           */
/*==============================================================*/


drop table if exists CLIENTE;

drop table if exists CUENTA;

drop table if exists SALDO;

drop table if exists TARJETA;

drop table if exists TIPO_TARJETA;

drop table if exists TRANSACCION;

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE
(
   COD_CLIENTE          int not null auto_increment,
   NOMBRE_CLIENTE       varchar(50) not null,
   APELLIDO_CLIENTE     varchar(50) not null,
   CEDULA_CLIENTE       varchar(10) not null,
   TELEFONO_CLIENTE     varchar(12) not null,
   CORREO_ELECTRONICO   varchar(128) not null,
   primary key (COD_CLIENTE)
);

/*==============================================================*/
/* Table: CUENTA                                                */
/*==============================================================*/
create table CUENTA
(
   COD_CUETA            varchar(20) not null,
   COD_CLIENTE          int not null,
   CUPO_TOTAL           numeric(12,2) not null,
   FECHA_CREACION       date not null,
   primary key (COD_CUETA)
);

/*==============================================================*/
/* Table: SALDO                                                 */
/*==============================================================*/
create table SALDO
(
   COD_CUETA            varchar(20) not null,
   SALDO_DISPONIBLE     numeric(12,2) not null,
   SALDO_BLOQUEADO      numeric(12,2) not null,
   SALDO_REAL           numeric(12,2) not null,
   SALDO_SOBREGIRO      numeric(12,2) not null,
   FECHA_UPDATE         datetime,
   primary key (COD_CUETA)
);

/*==============================================================*/
/* Table: TARJETA                                               */
/*==============================================================*/
create table TARJETA
(
   NUM_TARJETA          varchar(16) not null,
   COD_CUETA            varchar(10) not null,
   COD_TIPO_TARJETA     int not null,
   SALDO_TOTAL          numeric(12,2) not null,
   SALDO_DISPONIBLE     numeric(12,2) not null,
   ESTADO               varchar(3) not null default 'ACT',
   CVV                  varchar(4) not null,
   FECHA_EXP            varchar(10) not null,
   primary key (NUM_TARJETA)
);

/*==============================================================*/
/* Table: TIPO_TARJETA                                          */
/*==============================================================*/
create table TIPO_TARJETA
(
   COD_TIPO_TARJETA     int not null auto_increment,
   NOMBRE_TIPO          varchar(50) not null,
   primary key (COD_TIPO_TARJETA)
);

/*==============================================================*/
/* Table: TRANSACCION                                           */
/*==============================================================*/
create table TRANSACCION
(
   COD_TRANSACCION      int not null auto_increment,
   NUM_TARJETA          varchar(16) not null,
   TIPO                 varchar(3) not null default 'COM',
   VALOR_COMPRA         numeric(12,2) not null,
   IMPUESTO             numeric(12,2) not null,
   MONTO                numeric(12,2) not null,
   FECHA                datetime not null,
   ESTADO               varchar(3) not null default 'CON',
   primary key (COD_TRANSACCION)
);

alter table CUENTA add constraint FK_FK_CUENTA_A_CLIENTE foreign key (COD_CLIENTE)
      references CLIENTE (COD_CLIENTE) on delete restrict on update restrict;

alter table SALDO add constraint FK_FK_SALDO_A_CUENTA foreign key (COD_CUETA)
      references CUENTA (COD_CUETA) on delete restrict on update restrict;

alter table TARJETA add constraint FK_FK_TARJETA_A_TIPO foreign key (COD_TIPO_TARJETA)
      references TIPO_TARJETA (COD_TIPO_TARJETA) on delete restrict on update restrict;

alter table TARJETA add constraint FK_REFERENCE_3 foreign key (COD_CUETA)
      references CUENTA (COD_CUETA) on delete restrict on update restrict;

alter table TRANSACCION add constraint FK_FK_TRANSACCION_A_TARJETA foreign key (NUM_TARJETA)
      references TARJETA (NUM_TARJETA) on delete restrict on update restrict;

