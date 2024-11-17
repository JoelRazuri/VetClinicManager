-- Creación de la BDD --
CREATE DATABASE vetclinic_manager;
SHOW DATABASES;

USE vetclinic_manager;

-- CREACIÓN DE TABLAS --
-- Tabla 'cliente' --
CREATE TABLE IF NOT EXISTS `client` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(32) NOT NULL,
  `emaill` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `emaill_UNIQUE` (`emaill`)
);

-- Tabla 'mascota' -- 
CREATE TABLE IF NOT EXISTS `pet` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `species` VARCHAR(32) NOT NULL,
  `race` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `id_client` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_client_idx` (`id_client`),
  CONSTRAINT `id_client`
    FOREIGN KEY (`id_client`)
    REFERENCES `client` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
);

-- Tabla 'turno_medico' -- 
CREATE TABLE IF NOT EXISTS `medical_shift` (
  `id` INT NOT NULL,
  `reason` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `status` VARCHAR(20) NOT NULL,
  `id_pet` INT NOT NULL,
  `id_client` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_pet_idx` (`id_pet`),
  INDEX `id_client_idx` (`id_client`),
  CONSTRAINT `id_pet`
    FOREIGN KEY (`id_pet`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_client_medical_shift`
    FOREIGN KEY (`id_client`)
    REFERENCES `client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- Tabla 'historial_medico' -- 
CREATE TABLE IF NOT EXISTS `medical_history` (
  `id` INT NOT NULL,
  `date` DATE NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `treatment` VARCHAR(45) NOT NULL,
  `vaccines` VARCHAR(45) NULL,
  `id_pet` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_pet_idx` (`id_pet`),
  CONSTRAINT `id_pet_history`
    FOREIGN KEY (`id_pet`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- Tabla 'medicamento' -- 
CREATE TABLE IF NOT EXISTS `medicament` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `amount` INT NOT NULL,
  `expirate_date` DATE NOT NULL,
  `price` DECIMAL NOT NULL,
  PRIMARY KEY (`id`)
);

-- Tabla 'factura' -- 
CREATE TABLE IF NOT EXISTS `bill` (
  `id` INT NOT NULL,
  `date` DATE NOT NULL,
  `total` DECIMAL NOT NULL,
  `details` VARCHAR(45) NOT NULL,
  `id_client` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_client_idx` (`id_client`),
  CONSTRAINT `id_client_bill`
    FOREIGN KEY (`id_client`)
    REFERENCES `client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- Tabla 'insumo' -- 
CREATE TABLE IF NOT EXISTS `supply` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `amount` INT NOT NULL,
  `price` DECIMAL NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
);

-- INSERCIÓN DE DATOS DE PRUEBA --
-- Inserción de datos en la tabla 'cliente' --
INSERT INTO `client` (`id`, `name`, `phone`, `emaill`) 
VALUES 
	(1, 'Juan Pérez', '1234567890', 'juan.perez@email.com'),
	(2, 'María López', '0987654321', 'maria.lopez@email.com');

-- Inserción de datos en la tabla 'mascota' --
INSERT INTO `pet` (`id`, `name`, `species`, `race`, `gender`, `age`, `id_client`) 
VALUES 
	(1, 'Bobby', 'Perro', 'Labrador', 'Macho', 3, 1),
	(2, 'Mia', 'Gato', 'Siames', 'Hembra', 2, 2);

-- Inserción de datos en la tabla 'turno_medico' --
INSERT INTO `medical_shift` (`id`, `reason`, `date`, `price`, `status`,`id_pet`, `id_client`) 
VALUES 
	(1, 'Vacunación', '2024-11-18', 200.50, 'Confirmado', 1, 1),
	(2, 'Chequeo general', '2024-11-19', 150.00, 'Confirmado', 2, 2);

-- Inserción de datos en la tabla 'insumo' --
INSERT INTO `supply` (`id`, `name`, `amount`, `price`, `description`) 
VALUES 
	(1, 'Guantes quirúrgicos', 100, 25.00, 'Guantes de látex para procedimientos médicos'),
	(2, 'Jeringas', 50, 15.00, 'Jeringas para administración de medicamentos');

-- CONSULTA DE DATOS DE PRUEBA --
SELECT 
    ms.`id` AS 'ID Turno', 
    c.`name` AS 'Nombre Cliente',
    p.`name` AS 'Nombre Mascota',
    ms.`reason` AS 'Motivo Turno', 
    ms.`date` AS 'Fecha Turno', 
    ms.`price` AS 'Precio Turno',
    ms.`status` AS 'Estado'
FROM 
    `medical_shift` ms
JOIN 
    `pet` p ON ms.`id_pet` = p.`id`
JOIN 
    `client` c ON p.`id_client` = c.`id`;


-- BORRADO DE DATOS DE PRUEBA --
-- Desactivar el "safe update mode" --
SET SQL_SAFE_UPDATES = 0;

DELETE FROM `medical_shift`;
DELETE FROM `pet`;  
DELETE FROM `supply`;
DELETE FROM `client`;

-- Reactivar el "safe update mode" --
SET SQL_SAFE_UPDATES = 1;

SELECT * FROM `client`;
SELECT * FROM `pet`;
SELECT * FROM `medical_shift`;
SELECT * FROM `supply`;





    
    
