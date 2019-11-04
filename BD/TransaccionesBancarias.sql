-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-11-2019 a las 19:43:02
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectodistri`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `COD_CLIENTE` int(11) NOT NULL,
  `NOMBRE_CLIENTE` varchar(50) NOT NULL,
  `APELLIDO_CLIENTE` varchar(50) NOT NULL,
  `CEDULA_CLIENTE` varchar(10) NOT NULL,
  `TELEFONO_CLIENTE` varchar(12) NOT NULL,
  `CORREO_ELECTRONICO` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`COD_CLIENTE`, `NOMBRE_CLIENTE`, `APELLIDO_CLIENTE`, `CEDULA_CLIENTE`, `TELEFONO_CLIENTE`, `CORREO_ELECTRONICO`) VALUES
(1, ' Michelle Matusalén', 'RUIZ LOZANO', '0925421620', '0982456520', 'rumic01@gmail.com'),
(2, ' Renato Morfeo', 'ROJAS IBAÑEZ', '2263666303', '0982456521', 'roren02@gmail.com'),
(3, ' ROSARIO ANNA', 'SANTOS DOMINGUEZ', '1468535347', '0982456522', 'saros03@gmail.com'),
(4, ' MONTSERRAT FATIMA', 'VELASCO LOPEZ', '1841338815', '0982456523', 'vemon04@gmail.com'),
(5, ' Gunter Liborio', 'GOMEZ ORTEGA', '0581876554', '0982456524', 'gogun05@gmail.com'),
(6, ' NATALIA NEREA', 'BENITEZ RUIZ', '0218276251', '0982456525', 'benat06@gmail.com'),
(7, ' Pigmalión Reynaldo', 'IBAÑEZ RAMOS', '0921446019', '0982456526', 'ibpig07@gmail.com'),
(8, ' JUANA BELEN', 'CASTILLO GIMENEZ', '2184065577', '0982456527', 'cajua08@gmail.com'),
(9, ' ?MARIA MANUELA', 'MORALES SANZ', '2011706781', '0982456528', 'mo ma09@gmail.com'),
(10, ' Perseo Robinson', 'ALONSO MARTINEZ', '0515424265', '0982456529', 'alper10@gmail.com'),
(11, ' MONTSERRAT VICTORIA', 'IGLESIAS HERNANDEZ', '1435662612', '0982456530', 'igmon11@gmail.com'),
(12, ' LUZ GLORIA', 'MARTIN SAEZ', '0544184005', '0982456531', 'maluz12@gmail.com'),
(13, ' LORENA MONICA', 'FERNANDEZ MOYA', '1082752476', '0982456532', 'felor13@gmail.com'),
(14, ' VICTORIA AURORA', 'MORENO MUÑOZ', '0526243787', '0982456533', 'movic14@gmail.com'),
(15, ' OLGA MILAGROS', 'PEREZ ORTIZ', '1844353225', '0982456534', 'peolg15@gmail.com'),
(16, ' ALBA CARLA', 'PASCUAL BLANCO', '2176831317', '0982456535', 'paalb16@gmail.com'),
(17, ' ESTHER BEGOÑA', 'RAMIREZ MARQUEZ', '1858702044', '0982456536', 'raest17@gmail.com'),
(18, ' Pedro Rainero', 'DIAZ MARTINEZ', '0283741452', '0982456537', 'diped18@gmail.com'),
(19, ' ESPERANZA JOSEFINA', 'CABALLERO CAMPOS', '1402827669', '0982456538', 'caesp19@gmail.com'),
(20, ' Hamlet Mardoqueo', 'PARRA CARMONA', '1335120158', '0982456539', 'paham20@gmail.com'),
(21, ' VANESA JULIA', 'CALVO ALONSO', '1565616818', '0982456540', 'cavan21@gmail.com'),
(22, ' Petronila Roberto', 'ALVAREZ CANO', '0213358849', '0982456541', 'alpet22@gmail.com'),
(23, ' Rogelio Hércules', 'MONTERO VIDAL', '0968705582', '0982456542', 'morog23@gmail.com'),
(24, ' Casiano Gonzalo', 'RUIZ VEGA', '0343213336', '0982456543', 'rucas24@gmail.com'),
(25, ' Jordan Indalecio', 'LORENZO ESTEBAN', '0157283086', '0982456544', 'lojor25@gmail.com'),
(26, ' Reinaldo Martino', 'BENITEZ SANTIAGO', '0948247317', '0982456545', 'berei26@gmail.com'),
(27, ' Romano Rogelio', 'SERRANO NIETO', '1457074142', '0982456546', 'serom27@gmail.com'),
(28, ' Herculano Carlo', 'MARQUEZ LEON', '2075644480', '0982456547', 'maher28@gmail.com'),
(29, ' VERONICA ANTONIA', 'MUÑOZ LEON', '2330236841', '0982456548', 'muver29@gmail.com'),
(30, ' ALEJANDRA SUSANA', 'MOLINA MONTERO', '1984637759', '0982456549', 'moale30@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `COD_CUENTA` varchar(20) NOT NULL,
  `COD_CLIENTE` int(11) NOT NULL,
  `CUPO_TOTAL` decimal(12,2) NOT NULL,
  `FECHA_CREACION` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`COD_CUENTA`, `COD_CLIENTE`, `CUPO_TOTAL`, `FECHA_CREACION`) VALUES
('03548754404030930987', 8, '4589.33', '2018-09-01'),
('05790803399822514614', 6, '1033.75', '2017-02-06'),
('14407674390938572580', 9, '1452.33', '2018-05-10'),
('14465602554938046435', 26, '72598.22', '2017-12-18'),
('14646623545356381490', 15, '20185.22', '2019-02-23'),
('21205687899861956962', 24, '18963.03', '2018-11-09'),
('22637709332141204452', 5, '10455.19', '2018-01-09'),
('24289355957364952773', 11, '1024.26', '2018-04-30'),
('25688099813722130982', 16, '21436.22', '2018-10-07'),
('26292572088910651544', 18, '89563.20', '2018-05-02'),
('32919858328900692581', 13, '1478.20', '2017-04-17'),
('33764803190524866795', 3, '67260.53', '2019-04-16'),
('40617056612041232899', 12, '1036.21', '2018-05-10'),
('43198736725389614392', 27, '7589.21', '2018-11-11'),
('45258968247189933532', 7, '4569.55', '2018-12-10'),
('47806046698090078287', 4, '46033.09', '2017-06-10'),
('53141460179250354990', 23, '14236.33', '2018-10-10'),
('54808124812152786391', 30, '50236.44', '2016-05-12'),
('62165653655565226379', 17, '12458.69', '2019-01-01'),
('64539647979810016387', 14, '1248.33', '2017-03-01'),
('75122872940564841491', 22, '47563.22', '2017-03-14'),
('81387397913343948236', 19, '14896.26', '2019-04-14'),
('81874962714172182042', 28, '6985.33', '2017-10-10'),
('82418206962112722719', 29, '1245.36', '2018-01-16'),
('87255319470317033971', 1, '67004.47', '2017-09-13'),
('88117363058621213982', 2, '88362.25', '2019-07-24'),
('89836956100231063446', 21, '4589.21', '2018-05-19'),
('92509381029759309377', 25, '63548.45', '2018-04-04'),
('93539891137761166659', 20, '80562.33', '2017-02-16'),
('95077235271156960785', 10, '999.12', '2017-12-31');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `saldo`
--

CREATE TABLE `saldo` (
  `COD_CUETA` varchar(20) NOT NULL,
  `SALDO_DISPONIBLE` decimal(12,2) NOT NULL,
  `SALDO_BLOQUEADO` decimal(12,2) NOT NULL,
  `SALDO_REAL` decimal(12,2) NOT NULL,
  `SALDO_SOBREGIRO` decimal(12,2) NOT NULL,
  `FECHA_UPDATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `saldo`
--

INSERT INTO `saldo` (`COD_CUETA`, `SALDO_DISPONIBLE`, `SALDO_BLOQUEADO`, `SALDO_REAL`, `SALDO_SOBREGIRO`, `FECHA_UPDATE`) VALUES
('03548754404030930987', '4589.33', '0.00', '4589.33', '0.00', NULL),
('05790803399822514614', '1033.75', '0.00', '1033.75', '0.00', NULL),
('14407674390938572580', '1452.33', '0.00', '1452.33', '0.00', NULL),
('14465602554938046435', '72598.22', '0.00', '72598.22', '0.00', NULL),
('14646623545356381490', '20185.22', '0.00', '20185.22', '0.00', NULL),
('21205687899861956962', '18963.03', '0.00', '18963.03', '0.00', NULL),
('22637709332141204452', '10455.19', '0.00', '10455.19', '0.00', NULL),
('24289355957364952773', '1024.26', '0.00', '1024.26', '0.00', NULL),
('25688099813722130982', '21436.22', '0.00', '21436.22', '0.00', NULL),
('26292572088910651544', '89563.20', '0.00', '89563.20', '0.00', NULL),
('32919858328900692581', '1478.20', '0.00', '1478.20', '0.00', NULL),
('33764803190524866795', '67260.53', '0.00', '67260.53', '0.00', NULL),
('40617056612041232899', '1036.21', '0.00', '1036.21', '0.00', NULL),
('43198736725389614392', '7589.21', '0.00', '7589.21', '0.00', NULL),
('45258968247189933532', '4569.55', '0.00', '4569.55', '0.00', NULL),
('47806046698090078287', '46033.09', '0.00', '46033.09', '0.00', NULL),
('53141460179250354990', '14236.33', '0.00', '14236.33', '0.00', NULL),
('54808124812152786391', '50236.44', '0.00', '50236.44', '0.00', NULL),
('62165653655565226379', '12458.69', '0.00', '12458.69', '0.00', NULL),
('64539647979810016387', '1248.33', '0.00', '1248.33', '0.00', NULL),
('75122872940564841491', '47563.22', '0.00', '47563.22', '0.00', NULL),
('81387397913343948236', '14896.26', '0.00', '14896.26', '0.00', NULL),
('81874962714172182042', '6985.33', '0.00', '6985.33', '0.00', NULL),
('82418206962112722719', '1245.36', '0.00', '1245.36', '0.00', NULL),
('87255319470317033971', '67004.47', '0.00', '67004.47', '0.00', NULL),
('88117363058621213982', '88362.25', '0.00', '88362.25', '0.00', NULL),
('89836956100231063446', '4589.21', '0.00', '4589.21', '0.00', NULL),
('92509381029759309377', '63548.45', '0.00', '63548.45', '0.00', NULL),
('93539891137761166659', '80562.33', '0.00', '80562.33', '0.00', NULL),
('95077235271156960785', '999.12', '0.00', '999.12', '0.00', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjeta`
--

CREATE TABLE `tarjeta` (
  `NUM_TARJETA` varchar(16) NOT NULL,
  `COD_CUENTA` varchar(20) NOT NULL,
  `COD_TIPO_TARJETA` int(11) NOT NULL,
  `SALDO_TOTAL` decimal(12,2) NOT NULL,
  `SALDO_DISPONIBLE` decimal(12,2) NOT NULL,
  `ESTADO` varchar(3) NOT NULL DEFAULT 'ACT',
  `CVV` varchar(4) NOT NULL,
  `FECHA_EXP` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tarjeta`
--

INSERT INTO `tarjeta` (`NUM_TARJETA`, `COD_CUENTA`, `COD_TIPO_TARJETA`, `SALDO_TOTAL`, `SALDO_DISPONIBLE`, `ESTADO`, `CVV`, `FECHA_EXP`) VALUES
('4010238084736772', '05790803399822514614', 1, '5000.00', '5000.00', 'ACT', '707', '09/20'),
('4026108030637089', '88117363058621213982', 1, '4000.00', '4000.00', 'ACT', '746', '09/25'),
('4049771977040840', '54808124812152786391', 1, '1000.00', '1000.00', 'CAN', '897', '09/20'),
('4086114587599307', '81874962714172182042', 1, '4000.00', '4000.00', 'ACT', '929', '08/20'),
('4134973410017189', '25688099813722130982', 1, '4600.00', '4600.00', 'ACT', '133', '05/20'),
('4202922083209505', '40617056612041232899', 1, '3200.00', '3200.00', 'ACT', '992', '04/25'),
('4218141448677783', '22637709332141204452', 1, '3200.00', '3200.00', 'SUS', '673', '08/24'),
('4244297327035937', '03548754404030930987', 1, '2000.00', '2000.00', 'ACT', '563', '03/23'),
('4260264924762403', '92509381029759309377', 1, '3200.00', '3200.00', 'SUS', '510', '10/20'),
('4262113129343352', '43198736725389614392', 1, '6000.00', '6000.00', 'ACT', '535', '10/23'),
('4274855196837692', '47806046698090078287', 1, '4600.00', '4600.00', 'CAD', '157', '11/21'),
('4294666519511476', '81387397913343948236', 1, '1500.00', '1500.00', 'CAD', '946', '04/20'),
('4340331523954725', '45258968247189933532', 1, '1500.00', '1500.00', 'ROB', '711', '11/21'),
('4361100782654233', '14407674390938572580', 1, '3500.00', '3500.00', 'CAN', '667', '04/25'),
('4423203739363639', '75122872940564841491', 1, '4000.00', '4000.00', 'ACT', '693', '07/21'),
('4434072148859439', '89836956100231063446', 1, '3000.00', '3000.00', 'ACT', '961', '04/25'),
('4491922121320496', '14646623545356381490', 1, '2000.00', '2000.00', 'ACT', '942', '03/23'),
('4492179311221385', '93539891137761166659', 1, '2000.00', '2000.00', 'ACT', '538', '12/24'),
('4492179311221386', '26292572088910651544', 1, '5000.00', '5000.00', 'SUS', '613', '06/25'),
('4492634544972758', '24289355957364952773', 1, '6000.00', '6000.00', 'ACT', '755', '05/22'),
('4495998406062710', '33764803190524866795', 1, '2500.00', '2500.00', 'ACT', '660', '11/23'),
('4502481859152511', '95077235271156960785', 1, '1500.00', '1500.00', 'ROB', '914', '07/24'),
('4565615766185288', '21205687899861956962', 1, '4600.00', '4600.00', 'ACT', '657', '09/24'),
('4675429610350396', '53141460179250354990', 1, '2500.00', '2500.00', 'CAN', '851', '01/23'),
('4692674377431553', '62165653655565226379', 1, '3200.00', '3200.00', 'ROB', '590', '11/25'),
('4729224075258630', '32919858328900692581', 1, '1600.00', '1600.00', 'ACT', '431', '06/23'),
('4731565033672452', '87255319470317033971', 1, '3000.00', '3000.00', 'ACT', '745', '11/22'),
('4853680993153179', '64539647979810016387', 1, '1800.00', '1800.00', 'ACT', '992', '10/21'),
('4872558840781110', '82418206962112722719', 1, '3200.00', '3200.00', 'ACT', '420', '08/21'),
('4923486023399519', '14465602554938046435', 1, '5000.00', '5000.00', 'ACT', '773', '12/22');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_tarjeta`
--

CREATE TABLE `tipo_tarjeta` (
  `COD_TIPO_TARJETA` int(11) NOT NULL,
  `NOMBRE_TIPO` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo_tarjeta`
--

INSERT INTO `tipo_tarjeta` (`COD_TIPO_TARJETA`, `NOMBRE_TIPO`) VALUES
(1, 'Visa'),
(2, 'MasterCard');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transaccion`
--

CREATE TABLE `transaccion` (
  `COD_TRANSACCION` int(11) NOT NULL,
  `NUM_TARJETA` varchar(16) NOT NULL,
  `TIPO` varchar(3) NOT NULL DEFAULT 'COM',
  `VALOR_COMPRA` decimal(12,2) NOT NULL,
  `IMPUESTO` decimal(12,2) NOT NULL,
  `MONTO` decimal(12,2) NOT NULL,
  `MESES` int(11) NOT NULL,
  `FECHA` datetime NOT NULL,
  `ESTADO` varchar(3) NOT NULL DEFAULT 'CON'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`COD_CLIENTE`);

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`COD_CUENTA`),
  ADD KEY `FK_FK_CUENTA_A_CLIENTE` (`COD_CLIENTE`);

--
-- Indices de la tabla `saldo`
--
ALTER TABLE `saldo`
  ADD PRIMARY KEY (`COD_CUETA`);

--
-- Indices de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD PRIMARY KEY (`NUM_TARJETA`),
  ADD KEY `FK_FK_TARJETA_A_TIPO` (`COD_TIPO_TARJETA`),
  ADD KEY `FK_REFERENCE_3` (`COD_CUENTA`);

--
-- Indices de la tabla `tipo_tarjeta`
--
ALTER TABLE `tipo_tarjeta`
  ADD PRIMARY KEY (`COD_TIPO_TARJETA`);

--
-- Indices de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD PRIMARY KEY (`COD_TRANSACCION`),
  ADD KEY `FK_FK_TRANSACCION_A_TARJETA` (`NUM_TARJETA`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `COD_CLIENTE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `tipo_tarjeta`
--
ALTER TABLE `tipo_tarjeta`
  MODIFY `COD_TIPO_TARJETA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  MODIFY `COD_TRANSACCION` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `FK_FK_CUENTA_A_CLIENTE` FOREIGN KEY (`COD_CLIENTE`) REFERENCES `cliente` (`COD_CLIENTE`);

--
-- Filtros para la tabla `saldo`
--
ALTER TABLE `saldo`
  ADD CONSTRAINT `FK_FK_SALDO_A_CUENTA` FOREIGN KEY (`COD_CUETA`) REFERENCES `cuenta` (`COD_CUENTA`);

--
-- Filtros para la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD CONSTRAINT `FK_FK_TARJETA_A_TIPO` FOREIGN KEY (`COD_TIPO_TARJETA`) REFERENCES `tipo_tarjeta` (`COD_TIPO_TARJETA`),
  ADD CONSTRAINT `FK_REFERENCE_3` FOREIGN KEY (`COD_CUENTA`) REFERENCES `cuenta` (`COD_CUENTA`);

--
-- Filtros para la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD CONSTRAINT `FK_FK_TRANSACCION_A_TARJETA` FOREIGN KEY (`NUM_TARJETA`) REFERENCES `tarjeta` (`NUM_TARJETA`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
