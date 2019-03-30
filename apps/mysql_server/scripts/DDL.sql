CREATE DATABASE IF NOT EXISTS geoserviciosbd CHARACTER SET UTF8 collate utf8_bin;

USE geoserviciosbd;

SET foreign_key_checks = 0;

DROP TABLE IF EXISTS servicios;
DROP TABLE IF EXISTS sedes;
DROP TABLE IF EXISTS negocios;
DROP TABLE IF EXISTS tipos_negocios;
DROP TABLE IF EXISTS tipos_servicios;
DROP TABLE IF EXISTS feedbacks;
DROP TABLE IF EXISTS problemas;

SET foreign_key_checks = 1;

CREATE TABLE tipos_negocios(
   	id INT(3) NOT NULL AUTO_INCREMENT,
	descripcion VARCHAR(40) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE negocios(
   	id INT(4) NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
    imagen_url VARCHAR(200) NULL,
    id_tipo_negocio INT(3) NOT NULL,
    
    INDEX (id_tipo_negocio),
	FOREIGN KEY (id_tipo_negocio) REFERENCES tipos_negocios(id),
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE sedes(
   	id INT(4) NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL,
    latitud DOUBLE NULL,
    longitud DOUBLE NULL,
    id_negocio INT(4) NOT NULL,
    
    INDEX (id_negocio),
	FOREIGN KEY (id_negocio) REFERENCES negocios(id),
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE tipos_servicios(
	id INT(4) NOT NULL AUTO_INCREMENT,
	descripcion VARCHAR(100) NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE servicios(
   	id_tipo_servicio INT(4) NOT NULL AUTO_INCREMENT,
    id_sede INT(4) NOT NULL,
    
    INDEX (id_sede),
    INDEX (id_sede, id_tipo_servicio),
	FOREIGN KEY (id_sede) REFERENCES sedes(id),
    FOREIGN KEY (id_tipo_servicio) REFERENCES tipos_servicios(id),
    PRIMARY KEY (`id_tipo_servicio`,`id_sede`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE problemas(
   	id INT(2) NOT NULL AUTO_INCREMENT,
	descripcion VARCHAR(80) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE feedbacks(
   	id INT(5) NOT NULL AUTO_INCREMENT,
	mensaje TEXT NOT NULL,
    fecha DATETIME NULL,
    id_problema INT(2) NOT NULL,
    id_usuario VARCHAR(50) NULL,
    
    INDEX (id_problema),
	FOREIGN KEY (id_problema) REFERENCES problemas(id),
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
