SELECT * FROM usuarios;

-- User table
CREATE TABLE Usuario (
    id INT PRIMARY KEY,
    login VARCHAR2(255),
    contrasena VARCHAR2(255),
    tipoDocumento VARCHAR2(255),
    numeroDocumento VARCHAR2(255),
    nombre VARCHAR2(255),
    nacionalidad VARCHAR2(255),
    direccion VARCHAR2(255),
    email VARCHAR2(255),
    telefono VARCHAR2(255),
    ciudad VARCHAR2(255),
    departamento VARCHAR2(255),
    codigoPostal VARCHAR2(255)
);

-- Employee table (extends User)
CREATE TABLE Empleado (
    id INT PRIMARY KEY,
    rolEmpleado INT,
    FOREIGN KEY (id) REFERENCES Usuario(id)
);

-- Customer table (extends User)
CREATE TABLE Cliente (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES Usuario(id)
);

-- CustomerEmployee table
CREATE TABLE ClienteEmpleado (
    id_empleado INT,
    id_cliente INT,
    PRIMARY KEY (id_empleado, id_cliente),
    FOREIGN KEY (id_empleado) REFERENCES Empleado(id),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id)
);

-- Account table
CREATE TABLE Cuenta (
    id INT PRIMARY KEY,
    saldo INT,
    tipo VARCHAR2(255),
    estado VARCHAR2(255)
);

-- Operation table
CREATE TABLE Operacion (
    id INT PRIMARY KEY,
    fechaYHora DATE,
    tipoOperacion VARCHAR2(255)
);

-- Specific operation on an account (extends Operation)
CREATE TABLE OperacionCuenta (
    id INT PRIMARY KEY,
    numeroOrigen INT,
    numeroDestino INT,
    monto INT,
    FOREIGN KEY (id) REFERENCES Operacion(id)
);

-- Specific operation on a loan (extends Operation)
CREATE TABLE OperacionPrestamo (
    id INT PRIMARY KEY,
    numPrestamo INT,
    montoPago INT,
    FOREIGN KEY (id) REFERENCES Operacion(id)
);

-- Role table
CREATE TABLE Rol (
    id INT PRIMARY KEY,
    tipo VARCHAR2(255)
);

-- Loan table
CREATE TABLE Prestamo (
    id INT PRIMARY KEY,
    estado VARCHAR2(255),
    tipo VARCHAR2(255),
    monto INT,
    interes FLOAT,
    numCuotas INT,
    numMes INT,
    valorCuota INT
);

-- AttentionPoint table
CREATE TABLE PuntoDeAtencion (
    id INT PRIMARY KEY,
    tipo VARCHAR2(255)
);

-- Office table
CREATE TABLE Oficina (
    id INT PRIMARY KEY,
    nombre VARCHAR2(255),
    direccion VARCHAR2(255),
    numPuntosPosibles VARCHAR2(255)
);

-- Creating sequences for each table
CREATE SEQUENCE usuario_seq START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE empleado_seq START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE cliente_seq START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE cuenta_seq START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE operacion_seq START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE operacioncuenta_seq START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE operacionprestamo_seq START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE rol_seq START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE prestamo_seq START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE puntoatencion_seq START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE oficina_seq START WITH 1 INCREMENT BY 1 NOCACHE;

--Creating random data

-- Drop the existing trigger
DROP TRIGGER usuario_bi;

-- Recreate the trigger
CREATE OR REPLACE TRIGGER usuario_bi
BEFORE INSERT ON Usuario
FOR EACH ROW
BEGIN
  SELECT usuario_seq.NEXTVAL INTO :new.id FROM dual;
END;
/

-- Step 1: Drop the current sequence
DROP SEQUENCE usuario_seq;

-- Step 2: Recreate the sequence with the correct increment value
CREATE SEQUENCE usuario_seq
START WITH 1
INCREMENT BY 1
NOCACHE;

-- Step 3: Delete the existing data from the Usuario table
DELETE FROM Usuario;

-- Step 4: Re-insert the data using the new sequence
BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO Usuario (login, contrasena, tipoDocumento, numeroDocumento, nombre, nacionalidad, direccion, email, telefono, ciudad, departamento, codigoPostal)
    VALUES ('user'||i, DBMS_RANDOM.STRING('x', 10), 'CC', LPAD(TO_CHAR(DBMS_RANDOM.VALUE(10000000, 99999999), 'FM99999999'), 8, '0'), 'Nombre'||i, 'Colombiana', 'Direccion'||i, 'email'||i||'@example.com', LPAD(TO_CHAR(DBMS_RANDOM.VALUE(1000000, 9999999), 'FM9999999'), 7, '0'), 'Ciudad'||i, 'Departamento'||i, LPAD(TO_CHAR(DBMS_RANDOM.VALUE(100000, 999999), 'FM999999'), 6, '0'));
  END LOOP;
  COMMIT;
END;
/



-- Inserting sample data into the Usuario table
BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO Usuario (id, login, contrasena, tipoDocumento, numeroDocumento, nombre, nacionalidad, direccion, email, telefono, ciudad, departamento, codigoPostal)
    VALUES (usuario_seq.NEXTVAL, 'user'||i, DBMS_RANDOM.STRING('x', 10), 'CC', LPAD(TO_CHAR(DBMS_RANDOM.VALUE(10000000, 99999999), 'FM99999999'), 8, '0'), 'Nombre'||i, 'Colombiana', 'Direccion'||i, 'email'||i||'@example.com', LPAD(TO_CHAR(DBMS_RANDOM.VALUE(1000000, 9999999), 'FM9999999'), 7, '0'), 'Ciudad'||i, 'Departamento'||i, LPAD(TO_CHAR(DBMS_RANDOM.VALUE(100000, 999999), 'FM999999'), 6, '0'));
  END LOOP;
END;
/

SELECT * FROM USUARIO;


BEGIN
  FOR i IN 1..5 LOOP
    INSERT INTO Cliente (id) VALUES (i);
    -- Ensure the 'id' matches an existing 'Usuario' ID
    INSERT INTO Cliente (id, rolCliente) VALUES (i, DBMS_RANDOM.VALUE(1, 5));
  END LOOP;
  COMMIT;
END;
/

BEGIN
  FOR i IN 6..10 LOOP
    INSERT INTO Empleado (id) VALUES (i);
    -- Ensure the 'id' matches an existing 'Usuario' ID
    INSERT INTO Empleado (id, rolEmpleado) VALUES (i, DBMS_RANDOM.VALUE(6, 10));
  END LOOP;
  COMMIT;
END;
/



BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO ClienteEmpleado (id_empleado, id_cliente) VALUES (i + 10, i);
  END LOOP;
END;
/

-- Inserting sample data into Cuenta table
BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO Cuenta (id, saldo, tipo, estado) VALUES (cuenta_seq.NEXTVAL, DBMS_RANDOM.VALUE(1000, 10000), 'Ahorro', 'Activa');
  END LOOP;
END;
/

-- Inserting sample data into Operacion table
BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO Operacion (id, fechaYHora, tipoOperacion) VALUES (operacion_seq.NEXTVAL, CURRENT_DATE, 'Deposito');
  END LOOP;
END;
/

-- Inserting sample data into OperacionCuenta table
BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO OperacionCuenta (id, numeroOrigen, numeroDestino, monto) VALUES (i, i, i+1, DBMS_RANDOM.VALUE(100, 500));
  END LOOP;
END;
/

-- Inserting sample data into OperacionPrestamo table
BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO OperacionPrestamo (id, numPrestamo, montoPago) VALUES (i, i, DBMS_RANDOM.VALUE(200, 1000));
  END LOOP;
END;
/

-- Inserting sample data into Rol table
BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO Rol (id, tipo) VALUES (rol_seq.NEXTVAL, 'Tipo'||i);
  END LOOP;
END;
/

-- Inserting sample data into Prestamo table
BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO Prestamo (id, estado, tipo, monto, interes, numCuotas, numMes, valorCuota) VALUES (prestamo_seq.NEXTVAL, 'Activo', 'Hipotecario', DBMS_RANDOM.VALUE(10000, 50000), DBMS_RANDOM.VALUE(1, 5), DBMS_RANDOM.VALUE(12, 60), i, DBMS_RANDOM.VALUE(200, 1500));
  END LOOP;
END;
/

-- Inserting sample data into PuntoDeAtencion table
BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO PuntoDeAtencion (id, tipo) VALUES (puntoatencion_seq.NEXTVAL, 'Tipo'||i);
  END LOOP;
END;
/

-- Inserting sample data into Oficina table
BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO Oficina (id, nombre, direccion, numPuntosPosibles) VALUES (oficina_seq.NEXTVAL, 'Oficina'||i, 'Direccion'||i, LPAD(TO_CHAR(DBMS_RANDOM.VALUE(1, 10), 'FM9'), 1, '0'));
  END LOOP;
END;
/


