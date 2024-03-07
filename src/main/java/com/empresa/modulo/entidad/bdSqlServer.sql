/***** BASE DE DATOS SQLSERVER ****/

 --***********TABLA USERS*************--
DROP SCHEMA desarrollo;
CREATE SCHEMA desarrollo;

DROP SEQUENCE desarrollo.UserSequence;
CREATE SEQUENCE desarrollo.UserSequence
START WITH 1
INCREMENT BY 1;


drop TABLE  desarrollo.usuarios;
CREATE TABLE desarrollo.usuarios (
    id INT PRIMARY KEY DEFAULT NEXT VALUE FOR desarrollo.UserSequence,
    usuario VARCHAR(50),
    password VARCHAR(50),
	estado VARCHAR(1)
);

INSERT INTO desarrollo.usuarios (usuario, password, estado) VALUES ('LVILLAGRAN', '123456789', 'A');
select * from desarrollo.usuarios;


--*********TABLA TIPO DE PRODUCTO***********--
DROP SEQUENCE desarrollo.TipoProductoSequence;
CREATE SEQUENCE desarrollo.TipoProductoSequence
START WITH 1
INCREMENT BY 1;

CREATE TABLE desarrollo.tipo_producto (
			  id_tipo_producto INT PRIMARY KEY DEFAULT NEXT VALUE FOR desarrollo.TipoProductoSequence,
			  nombre VARCHAR(100)
			);

INSERT INTO desarrollo.tipo_producto (id_tipo_producto, nombre) VALUES (1, 'LACTEOS');
INSERT INTO desarrollo.tipo_producto (id_tipo_producto, nombre) VALUES (2, 'GRAMINEAS');
INSERT INTO desarrollo.tipo_producto (id_tipo_producto, nombre) VALUES (3, 'GRASAS');
select * from desarrollo.tipo_producto;

--********** TABLA PRODUCTO ************--
DROP SEQUENCE desarrollo.ProductoSequence;
CREATE SEQUENCE desarrollo.ProductoSequence
START WITH 1
INCREMENT BY 1;

drop table desarrollo.producto;
CREATE TABLE desarrollo.producto (
			   id_producto INT PRIMARY KEY DEFAULT NEXT VALUE FOR desarrollo.ProductoSequence,
			   nombre VARCHAR(100),
			   descripcion VARCHAR(100),
			   precio int,
			   estado VARCHAR(1),
			   fecha_registro DATETIME,
			   tipo_producto_id int,
			   CONSTRAINT FK_Tipo_Producto FOREIGN KEY (tipo_producto_id) REFERENCES desarrollo.tipo_producto(id_tipo_producto)
			);

--DELETE FROM desarrollo.producto;

--INSERT INTO desarrollo.Producto (id_producto, nombre, descripcion,precio, estado,fecha_registro, tipo_producto_id) VALUES (1, 'ARROZ','ARROZ', 12, 'A', '2024-02-28 10:30:00', 1 );
--INSERT INTO desarrollo.Producto (id_producto, nombre, descripcion,precio, estado,fecha_registro, tipo_producto_id) VALUES (2, 'PAPATAS','PAPATAS', 20, 'A', '2024-02-28 10:30:00', 2);
--INSERT INTO desarrollo. Producto (id_producto, nombre, descripcion, precio, estado, fecha_registro, tipo_producto_id) VALUES (3, 'FRIJOLES','FRIJOLES', 15, 'A', '2024-02-28 10:30:00', 3);

select * from desarrollo.producto;

*/