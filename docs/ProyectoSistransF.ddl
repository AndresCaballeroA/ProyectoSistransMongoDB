-- Generado por Oracle SQL Developer Data Modeler 23.1.0.087.0806
--   en:        2024-03-18 12:54:01 COT
--   sitio:      Oracle Database 21c
--   tipo:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE SEQUENCE cuenta_cuenta_id_seq START WITH 1 NOCACHE ORDER;

CREATE SEQUENCE oficina_oficina_id_seq START WITH 1 NOCACHE ORDER;

CREATE SEQUENCE operacion_operacion_id_seq START WITH 1 NOCACHE ORDER;

CREATE SEQUENCE operacionbancaria_operacionban START WITH 1 NOCACHE ORDER;

CREATE SEQUENCE prestamo_prestamo_id_seq START WITH 1 NOCACHE ORDER;

CREATE SEQUENCE prestamov1_prestamov1_id_seq START WITH 1 NOCACHE ORDER;

CREATE SEQUENCE puntodeatencion_puntodeatencio START WITH 1 NOCACHE ORDER;

CREATE SEQUENCE rol_rol_id_seq START WITH 1 NOCACHE ORDER;

CREATE SEQUENCE usuario_usuario_id_seq START WITH 1 NOCACHE ORDER;

CREATE SEQUENCE usuario_usuario_id1_seq START WITH 1 NOCACHE ORDER;

CREATE TABLE cliente (
    usuario_usuario_id1                 NUMBER NOT NULL,
    idcliente                           NUMBER NOT NULL,
    clienteempleado_usuario_usuario_id1 NUMBER NOT NULL
)
LOGGING;

CREATE UNIQUE INDEX cliente__idx ON
    cliente (
        clienteempleado_usuario_usuario_id1
    ASC );

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( usuario_usuario_id1 );

ALTER TABLE cliente ADD CONSTRAINT cliente_idcliente_un UNIQUE ( idcliente );

CREATE TABLE clienteempleado (
    usuario_usuario_id1 NUMBER NOT NULL,
    idclienteemple      NUMBER NOT NULL,
    cliente             NVARCHAR2(1) NOT NULL,
    empleado            NVARCHAR2(1) NOT NULL
)
LOGGING;

ALTER TABLE clienteempleado ADD CONSTRAINT clienteempleado_pk PRIMARY KEY ( usuario_usuario_id1 );

CREATE TABLE cuenta (
    saldo              NVARCHAR2(1) NOT NULL,
    tipo               NVARCHAR2(1) NOT NULL,
    estado             NVARCHAR2(1) NOT NULL,
    cuenta_id          NUMBER NOT NULL,
    usuario_usuario_id NUMBER NOT NULL,
    cliente_idcliente  NUMBER NOT NULL,
    fechacreacion      DATE NOT NULL
)
LOGGING;

ALTER TABLE cuenta ADD CONSTRAINT cuenta_pkv1 PRIMARY KEY ( cliente_idcliente );

ALTER TABLE cuenta ADD CONSTRAINT cuenta_pk UNIQUE ( cuenta_id );

CREATE TABLE empleado (
    usuario_usuario_id1                 NUMBER NOT NULL,
    idempleado                          NUMBER NOT NULL,
    tipoempleado                        NVARCHAR2(1) NOT NULL,
    clienteempleado_usuario_usuario_id1 NUMBER NOT NULL,
    oficina_oficina_id                  NUMBER NOT NULL
)
LOGGING;

CREATE UNIQUE INDEX empleado__idx ON
    empleado (
        clienteempleado_usuario_usuario_id1
    ASC );

ALTER TABLE empleado ADD CONSTRAINT empleado_pk PRIMARY KEY ( usuario_usuario_id1 );

ALTER TABLE empleado ADD CONSTRAINT empleado_idempleado_un UNIQUE ( idempleado );

CREATE TABLE hace (
    cuenta_cuenta_id                       NUMBER NOT NULL,
    operacionbancaria_operacionbancaria_id NUMBER NOT NULL
)
LOGGING;

ALTER TABLE hace ADD CONSTRAINT hace_pk PRIMARY KEY ( cuenta_cuenta_id,
                                                      operacionbancaria_operacionbancaria_id );

CREATE TABLE oficina (
    nombre            NVARCHAR2(1),
    direccion         NVARCHAR2(1),
    numpuntosposibles INTEGER,
    oficina_id        NUMBER NOT NULL
)
LOGGING;

ALTER TABLE oficina ADD CONSTRAINT oficina_pk PRIMARY KEY ( oficina_id );

CREATE TABLE operacion (
    operacion_id  NUMBER NOT NULL,
    id            NUMBER NOT NULL,
    producto      NVARCHAR2(1) NOT NULL,
    valor         NVARCHAR2(1) NOT NULL,
    hora          DATE NOT NULL,
    tipooperacion NVARCHAR2(1) NOT NULL
)
LOGGING;

ALTER TABLE operacion ADD CONSTRAINT operacion_pk PRIMARY KEY ( operacion_id );

CREATE TABLE operacionbancaria (
    operacion_operacion_id              NUMBER NOT NULL,
    tipo                                NVARCHAR2(1) NOT NULL,
    valor                               NVARCHAR2(1) NOT NULL,
    hora                                DATE NOT NULL,
    fecha                               DATE NOT NULL,
    operacionbancaria_id                NUMBER NOT NULL,
    prestamo_prestamo_id                NUMBER NOT NULL,
    puntodeatencion_puntodeatencion_id  NUMBER NOT NULL,
    numorigen                           NUMBER NOT NULL,
    numdestino                          NUMBER,
    monto                               NUMBER,
    puntodeatencion_puntodeatencion_id2 NUMBER NOT NULL
)
LOGGING;

ALTER TABLE operacionbancaria ADD CONSTRAINT operacionbancaria_pk PRIMARY KEY ( operacion_operacion_id );

ALTER TABLE operacionbancaria ADD CONSTRAINT operacionbancaria_operacionbancaria_id_un UNIQUE ( operacionbancaria_id );

CREATE TABLE prestamo (
    operacion_operacion_id   NUMBER NOT NULL,
    tipo                     NVARCHAR2(1) NOT NULL,
    monto                    NVARCHAR2(1) NOT NULL,
    estado                   NVARCHAR2(1) NOT NULL,
    cuotas                   INTEGER NOT NULL,
    prestamo_id              NUMBER NOT NULL,
    usuario_usuario_id       NUMBER NOT NULL,
    numorigen                NUMBER NOT NULL,
    numdestino               NUMBER NOT NULL,
    montopago                NUMBER NOT NULL,
    fecha                    DATE NOT NULL,
    prestamov1_prestamov1_id NUMBER NOT NULL
)
LOGGING;

ALTER TABLE prestamo ADD CONSTRAINT prestamo_pk PRIMARY KEY ( operacion_operacion_id );

ALTER TABLE prestamo ADD CONSTRAINT prestamo_prestamo_id_un UNIQUE ( prestamo_id );

CREATE TABLE prestamov1 (
    idprestamo          NUMBER,
    estado              NVARCHAR2(1),
    tipo                NVARCHAR2(1),
    monto               NUMBER,
    interes             NUMBER,
    numcuotas           NUMBER,
    nummes              NUMBER,
    valorcuota          NUMBER,
    prestamov1_id       NUMBER NOT NULL,
    usuario_usuario_id1 NUMBER
)
LOGGING;

CREATE UNIQUE INDEX prestamov1__idx ON
    prestamov1 (
        usuario_usuario_id1
    ASC );

ALTER TABLE prestamov1 ADD CONSTRAINT prestamov1_pk PRIMARY KEY ( prestamov1_id );

CREATE TABLE puntodeatencion (
    tipo               NVARCHAR2(1),
    puntodeatencion_id NUMBER NOT NULL,
    oficina_oficina_id NUMBER NOT NULL
)
LOGGING;

ALTER TABLE puntodeatencion ADD CONSTRAINT puntodeatencion_pk PRIMARY KEY ( puntodeatencion_id );

CREATE TABLE realizav1 (
    operacionbancaria_operacionbancaria_id NUMBER NOT NULL,
    usuario_usuario_id                     NUMBER NOT NULL
)
LOGGING;

ALTER TABLE realizav1 ADD CONSTRAINT realizav1_pk PRIMARY KEY ( operacionbancaria_operacionbancaria_id,
                                                                usuario_usuario_id );

CREATE TABLE rol (
    tipo                NVARCHAR2(1) NOT NULL,
    rol_id              NUMBER NOT NULL,
    usuario_usuario_id1 NUMBER NOT NULL
)
LOGGING;

ALTER TABLE rol ADD CONSTRAINT rol_pk PRIMARY KEY ( rol_id );

CREATE TABLE tiene1o2 (
    operacionbancaria_operacion_operacion_id NUMBER NOT NULL,
    cuenta_cliente_idcliente                 NUMBER NOT NULL
)
LOGGING;

ALTER TABLE tiene1o2 ADD CONSTRAINT tiene1o2_pk PRIMARY KEY ( operacionbancaria_operacion_operacion_id,
                                                              cuenta_cliente_idcliente );

CREATE TABLE tiene1o2v1 (
    operacionbancaria_operacion_operacion_id NUMBER NOT NULL,
    usuario_usuario_id1                      NUMBER NOT NULL
)
LOGGING;

ALTER TABLE tiene1o2v1 ADD CONSTRAINT tiene1o2v1_pk PRIMARY KEY ( operacionbancaria_operacion_operacion_id,
                                                                  usuario_usuario_id1 );

CREATE TABLE usuario (
    usuario_id1              NUMBER NOT NULL,
    login                    NVARCHAR2(1) NOT NULL,
    contrasena               NVARCHAR2(1) NOT NULL,
    tipodocumento            NVARCHAR2(1) NOT NULL,
    numerodocumento          NVARCHAR2(1) NOT NULL,
    nombre                   NVARCHAR2(1) NOT NULL,
    nacionalidad             NVARCHAR2(1),
    direccion                NVARCHAR2(1) NOT NULL,
    email                    NVARCHAR2(1) NOT NULL,
    telefono                 NVARCHAR2(1) NOT NULL,
    ciudad                   NVARCHAR2(1) NOT NULL,
    departamento             NVARCHAR2(1),
    codigopostal             NVARCHAR2(1),
    usuario_id               NUMBER NOT NULL,
    rol_rol_id               NUMBER NOT NULL,
    oficina_oficina_id       NUMBER NOT NULL,
    prestamov1_prestamov1_id NUMBER
)
LOGGING;

CREATE UNIQUE INDEX usuario__idx ON
    usuario (
        prestamov1_prestamov1_id
    ASC );

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( usuario_id1 );

ALTER TABLE usuario ADD CONSTRAINT usuario_usuario_id_un UNIQUE ( usuario_id );

ALTER TABLE cliente
    ADD CONSTRAINT cliente_clienteempleado_fk FOREIGN KEY ( clienteempleado_usuario_usuario_id1 )
        REFERENCES clienteempleado ( usuario_usuario_id1 )
    NOT DEFERRABLE;

ALTER TABLE cliente
    ADD CONSTRAINT cliente_usuario_fk FOREIGN KEY ( usuario_usuario_id1 )
        REFERENCES usuario ( usuario_id1 )
    NOT DEFERRABLE;

ALTER TABLE clienteempleado
    ADD CONSTRAINT clienteempleado_usuario_fk FOREIGN KEY ( usuario_usuario_id1 )
        REFERENCES usuario ( usuario_id1 )
    NOT DEFERRABLE;

ALTER TABLE cuenta
    ADD CONSTRAINT cuenta_cliente_fk FOREIGN KEY ( cliente_idcliente )
        REFERENCES cliente ( idcliente )
    NOT DEFERRABLE;

ALTER TABLE cuenta
    ADD CONSTRAINT cuenta_usuario_fk FOREIGN KEY ( usuario_usuario_id )
        REFERENCES usuario ( usuario_id )
    NOT DEFERRABLE;

ALTER TABLE empleado
    ADD CONSTRAINT empleado_clienteempleado_fk FOREIGN KEY ( clienteempleado_usuario_usuario_id1 )
        REFERENCES clienteempleado ( usuario_usuario_id1 )
    NOT DEFERRABLE;

ALTER TABLE empleado
    ADD CONSTRAINT empleado_oficina_fk FOREIGN KEY ( oficina_oficina_id )
        REFERENCES oficina ( oficina_id )
    NOT DEFERRABLE;

ALTER TABLE empleado
    ADD CONSTRAINT empleado_usuario_fk FOREIGN KEY ( usuario_usuario_id1 )
        REFERENCES usuario ( usuario_id1 )
    NOT DEFERRABLE;

ALTER TABLE hace
    ADD CONSTRAINT hace_cuenta_fk FOREIGN KEY ( cuenta_cuenta_id )
        REFERENCES cuenta ( cuenta_id )
    NOT DEFERRABLE;

ALTER TABLE hace
    ADD CONSTRAINT hace_operacionbancaria_fk FOREIGN KEY ( operacionbancaria_operacionbancaria_id )
        REFERENCES operacionbancaria ( operacionbancaria_id )
    NOT DEFERRABLE;

ALTER TABLE operacionbancaria
    ADD CONSTRAINT operacionbancaria_operacion_fk FOREIGN KEY ( operacion_operacion_id )
        REFERENCES operacion ( operacion_id )
    NOT DEFERRABLE;

ALTER TABLE operacionbancaria
    ADD CONSTRAINT operacionbancaria_prestamo_fk FOREIGN KEY ( prestamo_prestamo_id )
        REFERENCES prestamo ( prestamo_id )
    NOT DEFERRABLE;

ALTER TABLE operacionbancaria
    ADD CONSTRAINT operacionbancaria_puntodeatencion_fk FOREIGN KEY ( puntodeatencion_puntodeatencion_id )
        REFERENCES puntodeatencion ( puntodeatencion_id )
    NOT DEFERRABLE;

ALTER TABLE operacionbancaria
    ADD CONSTRAINT operacionbancaria_puntodeatencion_fkv2 FOREIGN KEY ( puntodeatencion_puntodeatencion_id2 )
        REFERENCES puntodeatencion ( puntodeatencion_id )
    NOT DEFERRABLE;

ALTER TABLE prestamo
    ADD CONSTRAINT prestamo_operacion_fk FOREIGN KEY ( operacion_operacion_id )
        REFERENCES operacion ( operacion_id )
    NOT DEFERRABLE;

ALTER TABLE prestamo
    ADD CONSTRAINT prestamo_prestamov1_fk FOREIGN KEY ( prestamov1_prestamov1_id )
        REFERENCES prestamov1 ( prestamov1_id )
    NOT DEFERRABLE;

ALTER TABLE prestamo
    ADD CONSTRAINT prestamo_usuario_fk FOREIGN KEY ( usuario_usuario_id )
        REFERENCES usuario ( usuario_id )
    NOT DEFERRABLE;

ALTER TABLE prestamov1
    ADD CONSTRAINT prestamov1_usuario_fk FOREIGN KEY ( usuario_usuario_id1 )
        REFERENCES usuario ( usuario_id1 )
    NOT DEFERRABLE;

ALTER TABLE puntodeatencion
    ADD CONSTRAINT puntodeatencion_oficina_fk FOREIGN KEY ( oficina_oficina_id )
        REFERENCES oficina ( oficina_id )
    NOT DEFERRABLE;

ALTER TABLE realizav1
    ADD CONSTRAINT realizav1_operacionbancaria_fk FOREIGN KEY ( operacionbancaria_operacionbancaria_id )
        REFERENCES operacionbancaria ( operacionbancaria_id )
    NOT DEFERRABLE;

ALTER TABLE realizav1
    ADD CONSTRAINT realizav1_usuario_fk FOREIGN KEY ( usuario_usuario_id )
        REFERENCES usuario ( usuario_id )
    NOT DEFERRABLE;

ALTER TABLE rol
    ADD CONSTRAINT rol_usuario_fk FOREIGN KEY ( usuario_usuario_id1 )
        REFERENCES usuario ( usuario_id1 )
    NOT DEFERRABLE;

ALTER TABLE tiene1o2
    ADD CONSTRAINT tiene1o2_cuenta_fk FOREIGN KEY ( cuenta_cliente_idcliente )
        REFERENCES cuenta ( cliente_idcliente )
    NOT DEFERRABLE;

ALTER TABLE tiene1o2
    ADD CONSTRAINT tiene1o2_operacionbancaria_fk FOREIGN KEY ( operacionbancaria_operacion_operacion_id )
        REFERENCES operacionbancaria ( operacion_operacion_id )
    NOT DEFERRABLE;

ALTER TABLE tiene1o2v1
    ADD CONSTRAINT tiene1o2v1_operacionbancaria_fk FOREIGN KEY ( operacionbancaria_operacion_operacion_id )
        REFERENCES operacionbancaria ( operacion_operacion_id )
    NOT DEFERRABLE;

ALTER TABLE tiene1o2v1
    ADD CONSTRAINT tiene1o2v1_usuario_fk FOREIGN KEY ( usuario_usuario_id1 )
        REFERENCES usuario ( usuario_id1 )
    NOT DEFERRABLE;

ALTER TABLE usuario
    ADD CONSTRAINT usuario_oficina_fk FOREIGN KEY ( oficina_oficina_id )
        REFERENCES oficina ( oficina_id )
    NOT DEFERRABLE;

ALTER TABLE usuario
    ADD CONSTRAINT usuario_prestamov1_fk FOREIGN KEY ( prestamov1_prestamov1_id )
        REFERENCES prestamov1 ( prestamov1_id )
    NOT DEFERRABLE;

ALTER TABLE usuario
    ADD CONSTRAINT usuario_rol_fk FOREIGN KEY ( rol_rol_id )
        REFERENCES rol ( rol_id )
    NOT DEFERRABLE;

CREATE OR REPLACE TRIGGER cuenta_cuenta_id_trg BEFORE
    INSERT ON cuenta
    FOR EACH ROW
    WHEN ( new.cuenta_id IS NULL )
BEGIN
    :new.cuenta_id := cuenta_cuenta_id_seq.nextval;
END;
/

CREATE OR REPLACE TRIGGER oficina_oficina_id_trg BEFORE
    INSERT ON oficina
    FOR EACH ROW
    WHEN ( new.oficina_id IS NULL )
BEGIN
    :new.oficina_id := oficina_oficina_id_seq.nextval;
END;
/

CREATE OR REPLACE TRIGGER operacion_operacion_id_trg BEFORE
    INSERT ON operacion
    FOR EACH ROW
    WHEN ( new.operacion_id IS NULL )
BEGIN
    :new.operacion_id := operacion_operacion_id_seq.nextval;
END;
/

CREATE OR REPLACE TRIGGER operacionbancaria_operacionban BEFORE
    INSERT ON operacionbancaria
    FOR EACH ROW
    WHEN ( new.operacionbancaria_id IS NULL )
BEGIN
    :new.operacionbancaria_id := operacionbancaria_operacionban.nextval;
END;
/

CREATE OR REPLACE TRIGGER prestamo_prestamo_id_trg BEFORE
    INSERT ON prestamo
    FOR EACH ROW
    WHEN ( new.prestamo_id IS NULL )
BEGIN
    :new.prestamo_id := prestamo_prestamo_id_seq.nextval;
END;
/

CREATE OR REPLACE TRIGGER prestamov1_prestamov1_id_trg BEFORE
    INSERT ON prestamov1
    FOR EACH ROW
    WHEN ( new.prestamov1_id IS NULL )
BEGIN
    :new.prestamov1_id := prestamov1_prestamov1_id_seq.nextval;
END;
/

CREATE OR REPLACE TRIGGER puntodeatencion_puntodeatencio BEFORE
    INSERT ON puntodeatencion
    FOR EACH ROW
    WHEN ( new.puntodeatencion_id IS NULL )
BEGIN
    :new.puntodeatencion_id := puntodeatencion_puntodeatencio.nextval;
END;
/

CREATE OR REPLACE TRIGGER rol_rol_id_trg BEFORE
    INSERT ON rol
    FOR EACH ROW
    WHEN ( new.rol_id IS NULL )
BEGIN
    :new.rol_id := rol_rol_id_seq.nextval;
END;
/

CREATE OR REPLACE TRIGGER usuario_usuario_id1_trg BEFORE
    INSERT ON usuario
    FOR EACH ROW
    WHEN ( new.usuario_id1 IS NULL )
BEGIN
    :new.usuario_id1 := usuario_usuario_id1_seq.nextval;
END;
/

CREATE OR REPLACE TRIGGER usuario_usuario_id_trg BEFORE
    INSERT ON usuario
    FOR EACH ROW
    WHEN ( new.usuario_id IS NULL )
BEGIN
    :new.usuario_id := usuario_usuario_id_seq.nextval;
END;
/



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            16
-- CREATE INDEX                             4
-- ALTER TABLE                             51
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                          10
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                         10
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
