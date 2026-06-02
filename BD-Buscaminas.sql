use buscaminas;

create table usuario(
	idJugador int not null primary key auto_increment, 
	nombre varchar(40) not null,
	puntos int not NULL,
	dificultad INT NOT null
);

INSERT INTO usuario (idJugador, nombre, puntos, dificultad) 
VALUES
(1, 'Nico', 3, 2),
(2, 'Mario', 5, 1),
(3, 'Victor', 2, 3);