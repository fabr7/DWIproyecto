CREATE DATABASE IF NOT EXISTS railway;
USE railway;

CREATE TABLE Area (
    Id_Area INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Responsable VARCHAR(100)
);

CREATE TABLE Cargo (
    Id_Cargo INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Sueldo DECIMAL(10,2) NOT NULL
);

-- Tabla principal
CREATE TABLE Trabajador (
    Id_Trabajador INT AUTO_INCREMENT PRIMARY KEY,
    Dni VARCHAR(8) UNIQUE NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    Apellido_Paterno VARCHAR(50) NOT NULL,
    Apellido_Materno VARCHAR(50),
    Fecha_Nacimiento DATE NOT NULL,
    Telefono VARCHAR(15), 
    Email VARCHAR(100), 
    Id_Area INT NOT NULL,
    Id_Cargo INT NOT NULL,
    FOREIGN KEY (Id_Area) REFERENCES Area(Id_Area),
    FOREIGN KEY (Id_Cargo) REFERENCES Cargo(Id_Cargo)
);


CREATE TABLE Contrato (
    Id_Contrato INT AUTO_INCREMENT PRIMARY KEY,
    Tipo ENUM('CAS', 'Nombrado', 'Contratado') NOT NULL,
    Fecha_Inicio DATE NOT NULL,
    Fecha_Fin DATE,
    Id_Trabajador INT NOT NULL,
    FOREIGN KEY (Id_Trabajador) REFERENCES Trabajador(Id_Trabajador)
);

CREATE TABLE Certificado (
    Id_Certificado INT AUTO_INCREMENT PRIMARY KEY,
    Id_Trabajador INT NOT NULL,
    Fecha_Emision DATE NOT NULL,
    Motivo VARCHAR(100) NOT NULL,
    Codigo VARCHAR(20) UNIQUE,
    FOREIGN KEY (Id_Trabajador) REFERENCES Trabajador(Id_Trabajador)
);

CREATE TABLE Usuario (
    Id_Usuario INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Rol ENUM('Admin', 'RRHH', 'Empleado') NOT NULL,
    Id_Trabajador INT UNIQUE,
    FOREIGN KEY (Id_Trabajador) REFERENCES Trabajador(Id_Trabajador)
);
