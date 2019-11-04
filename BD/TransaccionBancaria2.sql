/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     4/11/2019 13:55:46                           */
/*==============================================================*/


drop table if exists LOCAL;

drop table if exists POS;

drop table if exists SESION;

drop table if exists TRANSACCION;

/*==============================================================*/
/* Table: LOCAL                                                 */
/*==============================================================*/
create table LOCAL
(
   COD_LOCAL            int not null auto_increment,
   NOMBRE               varchar(50) not null,
   LATITUD              double not null,
   LONGITUD             double not null,
   primary key (COD_LOCAL)
);

/*==============================================================*/
/* Table: POS                                                   */
/*==============================================================*/
create table POS
(
   COD_POS              int not null auto_increment,
   COD_LOCAL            INT not null,
   PIN                  varchar(4) not null,
   ESTADO               varchar(10) not null,
   primary key (COD_POS)
);

/*==============================================================*/
/* Table: SESION                                                */
/*==============================================================*/
create table SESION
(
   COD_SESION           INT not null auto_increment,
   FEHCA_CREACION       datetime not null,
   FECHA_FIN            datetime not null,
   primary key (COD_SESION)
);

/*==============================================================*/
/* Table: TRANSACCION                                           */
/*==============================================================*/
create table TRANSACCION
(
   COD_TRANSACCION      int not null auto_increment,
   NUM_TARJETA          varchar(16) not null,
   COD_LOCAL            INT not null,
   TIPO                 varchar(3) not null default 'COM',
   VALOR_COMPRA         numeric(12,2) not null,
   IMPUESTO             numeric(12,2) not null,
   MONTO                numeric(12,2),
   FECHA                datetime not null,
   ESTADO               varchar(3) not null default 'CON',
   primary key (COD_TRANSACCION)
);

alter table POS add constraint FK_REFERENCE_2 foreign key (COD_LOCAL)
      references LOCAL (COD_LOCAL) on delete restrict on update restrict;

alter table TRANSACCION add constraint FK_REFERENCE_1 foreign key (COD_LOCAL)
      references LOCAL (COD_LOCAL) on delete restrict on update restrict;

