create database peluqueria;
use peluqueria;
create table servicios(
cod_servicio int primary key auto_increment,
nombre varchar(50),
descripcion varchar(500),
precio double,
categoria varchar(50)
);

create table clientes(
cod_cliente int primary key auto_increment,
dni varchar(9),
nombre_cli varchar(100),
apellido1 varchar(50),
apellido2 varchar(50)
);

create table compra(
cod_compra int primary key auto_increment,
cod_servicio int,
cod_cliente int,
cantidad int,
tipo_pago varchar(50)
);

alter table compra
add foreign key(cod_servicio)
references servicios(cod_servicio)
on delete cascade
on update cascade,
add foreign key (cod_cliente)
references clientes(cod_cliente);

