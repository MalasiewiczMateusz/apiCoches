INSERT into marca(nombre) values ('cordoba');
INSERT into marca(nombre) values ('ibiza');
INSERT into marca(nombre) values ('leon');


INSERT INTO MODELO(nombre) values ('seat');
INSERT INTO MODELO(nombre) values ('seat');
INSERT INTO MODELO(nombre) values ('seat');



INSERT INTO coches (cilindrada,color,matricula,velocidad,marca_id,modelo_id) values (213,'azul','asdsad',120,1,1);
INSERT INTO coches (cilindrada,color,matricula,velocidad,marca_id,modelo_id) values (213,'verde','afsafa',240,2,2);
INSERT INTO coches (cilindrada,color,matricula,velocidad,marca_id,modelo_id) values (214,'rojo','fsafsaf',200,3,3);



INSERT INTO usuarios (username,password,enabled) VALUES ('pedro','',1);
INSERT INTO usuarios (username,password,enabled) VALUES ('rolando','',1);

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (2,1);