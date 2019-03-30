USE geoserviciosbd;

INSERT INTO tipos_negocios VALUES(1,"Banca");
INSERT INTO tipos_negocios VALUES(2,"SuperMercados");

INSERT INTO negocios VALUES(1,"BBVA Continental", "", 1);
INSERT INTO negocios VALUES(2,"Tottus", "", 2);

INSERT INTO sedes VALUES(1, "Sede Colonial", -12.0534252, -77.0958897,1);
INSERT INTO sedes VALUES(2, "Sede Mall Aventura Plaza", -12.055706, -77.1011375,1);
INSERT INTO sedes VALUES(3, "Sede Central", -12.093250758751712, -77.02152561667174,1);
INSERT INTO sedes VALUES(4, "Sede Navarrete", -12.095496, 77.0262046,1);
INSERT INTO sedes VALUES(5, "Sede Corpac", -12.0967431, -77.0235814,1);
INSERT INTO sedes VALUES(6, "Sede Navarrete", -12.0960258, -77.0254737,2);
INSERT INTO sedes VALUES(7, "Sede Mall Aventura", -12.0562096, -77.1012823,2);
INSERT INTO sedes VALUES(8, "Sede San Miguel", -12.0795229, -77.0886297,2);

INSERT INTO tipos_servicios VALUES(1, "Prestamo Libre Disponibilidad");
INSERT INTO tipos_servicios VALUES(2, "Prestamo Vehicular");
INSERT INTO tipos_servicios VALUES(3, "Prestamo Hipotecario");
INSERT INTO tipos_servicios VALUES(4, "Prestamo Comercial");
INSERT INTO tipos_servicios VALUES(5, "Productos comestibles");
INSERT INTO tipos_servicios VALUES(6, "Productos del hogar");
INSERT INTO tipos_servicios VALUES(7, "Productos cosméticos");

INSERT INTO servicios VALUES(1,1);
INSERT INTO servicios VALUES(1,2);
INSERT INTO servicios VALUES(3,2);
INSERT INTO servicios VALUES(1,3);
INSERT INTO servicios VALUES(2,3);
INSERT INTO servicios VALUES(3,3);
INSERT INTO servicios VALUES(4,3);
INSERT INTO servicios VALUES(1,4);
INSERT INTO servicios VALUES(2,4);
INSERT INTO servicios VALUES(1,5);
INSERT INTO servicios VALUES(2,5);
INSERT INTO servicios VALUES(5,6);
INSERT INTO servicios VALUES(6,6);
INSERT INTO servicios VALUES(5,7);
INSERT INTO servicios VALUES(6,7);
INSERT INTO servicios VALUES(5,8);
INSERT INTO servicios VALUES(6,8);
INSERT INTO servicios VALUES(7,8);

INSERT INTO problemas VALUES(1, "Poca usabilidad");
INSERT INTO problemas VALUES(2, "Lentitud en la carga de datos");
INSERT INTO problemas VALUES(3, "Necesita mejorar la autenticación");
INSERT INTO problemas VALUES(4, "Otro");

INSERT INTO feedbacks VALUES(1, "Ojala se incorpore la autenticación con google", "2018-07-15",3,'william-bbva');
INSERT INTO feedbacks VALUES(2, "Ojala se incorpore la autenticación con facebook","2018-07-15",3,'william-bbva');
INSERT INTO feedbacks VALUES(3, "Se necesita no usar el botón de geolocalizar a cada rato","2018-07-15",1,'william-bbva');
INSERT INTO feedbacks VALUES(4, "Se necesita refrescar la sesión","2018-07-15",3,'william-bbva');
INSERT INTO feedbacks VALUES(5, "Se debe tener cacheado los tipos de problemas","2018-07-15",2,'william-bbva');
INSERT INTO feedbacks VALUES(6, "Espero que se empaquete en apk","2018-07-15",4,'william-bbva');

COMMIT;