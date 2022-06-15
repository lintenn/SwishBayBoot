-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-06-2022 a las 21:43:56
-- Versión del servidor: 10.5.12-MariaDB-cll-lve
-- Versión de PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `u325099778_SwishBay2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DESCRIPCION` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`ID`, `NOMBRE`, `DESCRIPCION`) VALUES
(1, 'Gaming Setup', 'Productos para mejorar tu experiencia de juego'),
(2, 'Comics', 'Productos de lectura relacionaados con super héroes, mangas...'),
(3, 'Deportes', 'Productos orientados a facilitarte practicar deportes como pesas, baloncesto, ciclismo, boxeo, pesca...');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favorito`
--

CREATE TABLE `favorito` (
  `COMPRADOR` int(11) NOT NULL,
  `PRODUCTO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `favorito`
--

INSERT INTO `favorito` (`COMPRADOR`, `PRODUCTO`) VALUES
(5, 77),
(5, 96),
(58, 96),
(59, 7),
(59, 77),
(59, 96);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupo`
--

CREATE TABLE `grupo` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `MARKETING` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `grupo`
--

INSERT INTO `grupo` (`ID`, `NOMBRE`, `MARKETING`) VALUES
(66, 'Grupo_7', 62),
(67, 'Grupo_77', 62),
(68, 'Grupo_95', 62),
(69, 'Grupo_96', 62),
(70, 'Grupo_98', 62);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupocomprador`
--

CREATE TABLE `grupocomprador` (
  `COMPRADOR` int(11) NOT NULL,
  `GRUPO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `grupocomprador`
--

INSERT INTO `grupocomprador` (`COMPRADOR`, `GRUPO`) VALUES
(5, 67),
(5, 68),
(5, 69),
(58, 69),
(59, 66),
(59, 67),
(59, 69);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje`
--

CREATE TABLE `mensaje` (
  `ID` int(11) NOT NULL,
  `MARKETING` int(11) NOT NULL,
  `GRUPO` int(11) NOT NULL,
  `ASUNTO` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CONTENIDO` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FECHA` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `mensaje`
--

INSERT INTO `mensaje` (`ID`, `MARKETING`, `GRUPO`, `ASUNTO`, `CONTENIDO`, `FECHA`) VALUES
(115, 62, 69, 'Inicio de puja de Producto Prueba 2', 'Se ha iniciado una nueva puja del producto Producto Prueba 2.', '2022-06-15'),
(116, 62, 69, 'Fin de puja de Producto Prueba 2', 'Se ha terminado la puja del producto Producto Prueba 2. Mary Smith ha sido el ganador de la puja.', '2022-06-15');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajecomprador`
--

CREATE TABLE `mensajecomprador` (
  `COMPRADOR` int(11) NOT NULL,
  `MENSAJE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `mensajecomprador`
--

INSERT INTO `mensajecomprador` (`COMPRADOR`, `MENSAJE`) VALUES
(5, 115),
(5, 116),
(58, 115),
(58, 116),
(59, 116);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preferencia`
--

CREATE TABLE `preferencia` (
  `COMPRADOR` int(11) NOT NULL,
  `CATEGORIA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `preferencia`
--

INSERT INTO `preferencia` (`COMPRADOR`, `CATEGORIA`) VALUES
(7, 1),
(58, 1),
(58, 3),
(59, 1),
(59, 3),
(60, 2),
(65, 2),
(68, 1),
(68, 3),
(69, 2),
(70, 3),
(71, 2),
(72, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `ID` int(11) NOT NULL,
  `TITULO` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DESCRIPCION` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PRECIO_SALIDA` double NOT NULL,
  `FIN_PUJA` date DEFAULT NULL,
  `FOTO` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `VENDEDOR` int(11) NOT NULL,
  `COMPRADOR` int(11) DEFAULT NULL,
  `CATEGORIA` int(11) NOT NULL,
  `EN_PUJA` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`ID`, `TITULO`, `DESCRIPCION`, `PRECIO_SALIDA`, `FIN_PUJA`, `FOTO`, `VENDEDOR`, `COMPRADOR`, `CATEGORIA`, `EN_PUJA`) VALUES
(2, 'Teclado Gaming', 'Teclado gaming retroiluminacion RGB con 3 modos y modo de iluminacion breathi...', 40, '2022-07-01', 'https://media.game.es/COVERV2/3D_L/187/187066.png', 5, 65, 2, 0),
(3, 'Monitor gaming LG', 'Monitor color negro con frecuencia de 144 Hz y tecnologia AMD Freesync Premium.', 169, '2022-06-27', 'https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_82431698/fee_786_587_png', 4, 5, 1, 0),
(7, 'Alfombrilla SteelSeries', 'Una alfombrilla suave, con tela de alta calidad que ofrezca un buen control.', 20, '2022-07-02', 'https://img.pccomponentes.com/articles/3/31643/steelseries-steelpad-qck-mini.jpg', 4, NULL, 1, 1),
(20, 'Batman Edicion n1', 'Primera edicion del famoso comic Batman de 1939.', 1400, '2022-07-01', 'https://tse4.mm.bing.net/th?id=OIP.dFC_pejDowda9OPp9hzpoAHaK4&pid=Api&P=0&w=137&h=202', 4, 5, 2, 0),
(21, 'SSDD', 'Monitor color negro con frecuencia de 144 Hz y tecnologia AMD Freesync Premium.', 50, '2022-05-28', 'https://th.bing.com/th/id/OIP.KeKY2Y3R0HRBkPEmGWU3FwHaHa?pid=ImgDet&rs=1', 4, 3, 1, 0),
(71, 'Taza', 'Taza para tomar el cafe para el desayuno', 7, '2022-07-01', 'http://assets.stickpng.com/images/599e9ad7eb380faa1fd1ced7.png', 4, 5, 3, 0),
(72, 'Cuchara', 'Cuchara para tomar sopa calentia despues de un duro dia en la universidad', 10, '2022-07-01', 'https://i.pinimg.com/originals/d2/57/a6/d257a62b574beee823e4ef9d5ac491e0.png', 4, 5, 3, 0),
(75, 'Tenedor', 'Tenedor robado de la cafeteria, ideal para comerte los macarrones con queso', 5, '2022-07-01', 'http://pngimg.com/uploads/fork/fork_PNG3067.png', 4, 5, 3, 0),
(76, 'Aero 15', 'Portatil Gigabyte Aero 15, con Intel i7 y grafica Nvidia 2060 RTX', 1500, '2022-07-01', 'https://elchapuzasinformatico.com/wp-content/uploads/2019/02/Gigabyte-Aero-15-X9-Oficial.png', 4, 5, 1, 0),
(77, 'Logitech g403', 'Raton gaming logitech, con alta precision para shooters y luces led RGB.', 50, '2022-07-01', 'https://resource.logitechg.com/d_transparent.gif/content/dam/gaming/en/non-braid/g403/g403-wired-gallery-1-nb.png', 4, NULL, 1, 1),
(96, 'Producto Prueba 2', '', 200, '2022-07-01', 'https://th.bing.com/th/id/OIP.KeKY2Y3R0HRBkPEmGWU3FwHaHa?pid=ImgDet&rs=1', 4, 59, 1, 0),
(98, '12121', 'wqewqewq', 21, '2022-07-01', 'wqeq', 4, NULL, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puja`
--

CREATE TABLE `puja` (
  `COMPRADOR` int(11) NOT NULL,
  `PRODUCTO` int(11) NOT NULL,
  `PRECIO` double NOT NULL,
  `FECHA` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `puja`
--

INSERT INTO `puja` (`COMPRADOR`, `PRODUCTO`, `PRECIO`, `FECHA`) VALUES
(3, 3, 200, '2022-04-10'),
(3, 21, 50, '2022-04-10'),
(5, 3, 2200, '2022-06-05'),
(5, 7, 2465, '2022-06-05'),
(5, 20, 1400, '2022-05-08'),
(5, 71, 8, '2022-05-15'),
(5, 72, 1000, '2022-05-15'),
(5, 75, 15, '2022-05-16'),
(5, 76, 1700, '2022-05-16'),
(5, 77, 50, '2022-06-05'),
(5, 96, 203, '2022-06-15'),
(58, 72, 150, '2022-05-15'),
(58, 75, 11, '2022-05-16'),
(58, 76, 1600, '2022-05-16'),
(58, 96, 202, '2022-06-15'),
(59, 2, 55, '2022-05-16'),
(59, 3, 2100, '2022-06-05'),
(59, 7, 25, '2022-06-05'),
(59, 72, 901, '2022-05-15'),
(59, 75, 10, '2022-05-16'),
(59, 76, 1650, '2022-05-16'),
(59, 96, 250, '2022-06-15'),
(65, 2, 60, '2022-05-16');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol_usuario`
--

CREATE TABLE `rol_usuario` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `rol_usuario`
--

INSERT INTO `rol_usuario` (`ID`, `NOMBRE`) VALUES
(1, 'administrador'),
(2, 'compradorvendedor'),
(3, 'marketing');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `ID` int(11) NOT NULL,
  `CORREO` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `PASSWORD` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `NOMBRE` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `APELLIDOS` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DOMICILIO` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FECHA_NACIMIENTO` date NOT NULL DEFAULT current_timestamp(),
  `SEXO` enum('masc','fem') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CIUDAD` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `SALDO` double NOT NULL DEFAULT 0,
  `ROL` int(11) NOT NULL DEFAULT 2
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`ID`, `CORREO`, `PASSWORD`, `NOMBRE`, `APELLIDOS`, `DOMICILIO`, `FECHA_NACIMIENTO`, `SEXO`, `CIUDAD`, `SALDO`, `ROL`) VALUES
(1, 'linten42@gmail.com', '123456', 'Luis', 'Garcia', 'Mi casa', '2001-08-13', 'masc', 'Malaga', 100, 1),
(3, 'manolo@gmail.com', '123456', 'manolo', 'Perez Gutierrez', 'calle manolo', '1999-02-11', 'masc', 'Malaga', 2423.5, 2),
(4, 'galopg2001@hotmail.com', '123456', 'Galo', 'Pérez', 'Mercedes Formica', '2001-01-10', 'masc', 'Málaga', 2172, 2),
(5, 'miguelog@uma.es', '123456', 'Miguel', 'Oña', 'Mauricio Moro, 3', '2001-01-19', 'masc', 'Málaga', 215, 2),
(7, 'juan@gmail.com', '123456', 'juana', 'antonio', 'calle juan', '1991-02-11', 'fem', 'Malaga', 0, 1),
(8, 'francisco@gmail.com', '123456', 'Francisco', 'Antonio', 'calle francisco', '1999-01-04', 'masc', 'Malaga', 0, 2),
(58, 'ricardo@gmail.com', '123456', 'Ricardo', 'Mozo Castaño', 'Calle Roca', '1989-09-01', 'masc', 'Pontevedra', 2895, 2),
(59, 'mary@uma.es', '123456', 'Mary', 'Smith', 'Avenida', '1979-04-01', 'fem', 'Sevilla', 1741, 2),
(60, 'angelrodriguezmercado@gmail.com', '123456', 'angelComprador', 'Vendedor', 'Torrox', '1999-01-15', 'masc', 'Málaga', 1500, 2),
(62, 'angelrodriguezmercado95@gmail.com', '123456', 'Ángel Joaquí­n', 'Rodrí­guez Mercado', 'Torrox', '2001-09-27', 'masc', 'Málaga', 100, 3),
(65, 'prueba120@uma.es', '123456', 'prueba120', 'prueba120', 'prueba120', '1999-10-10', 'masc', 'prueba120', 40, 2),
(68, 'prueba001@gmail.com', '123456', 'prueba0010', 'prueba001', 'prueba001', '1999-10-10', 'masc', 'prueba001', 10, 1),
(69, 'prueba002@gmail.com', '123456', 'prueba002', 'prueba0020', 'prueba002', '1999-10-08', 'fem', 'prueba002', 20, 1),
(70, 'prueba003@gmail.com', '123456', 'prueba003', 'prueba003', 'prueba003', '1999-10-10', 'masc', 'prueba003', 100, 3),
(71, 'prueba004@gmail.com', '123456', 'prueba004', 'prueba004', 'prueba004', '1999-10-10', 'masc', 'prueba004', 0, 2),
(72, 'prueba005@gmail.com', '123456', 'prueba005', 'prueba005', 'prueba005', '1999-10-10', 'fem', 'prueba005', 0, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `favorito`
--
ALTER TABLE `favorito`
  ADD PRIMARY KEY (`COMPRADOR`,`PRODUCTO`),
  ADD KEY `FAVORITO_PRODUCTO_idx` (`PRODUCTO`),
  ADD KEY `FAVORITO_COMPRADOR_idx` (`COMPRADOR`);

--
-- Indices de la tabla `grupo`
--
ALTER TABLE `grupo`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `GRUPO_MARKETING_idx` (`MARKETING`);

--
-- Indices de la tabla `grupocomprador`
--
ALTER TABLE `grupocomprador`
  ADD PRIMARY KEY (`COMPRADOR`,`GRUPO`),
  ADD KEY `GRUPO_idx` (`GRUPO`),
  ADD KEY `GC_COMPRADOR_idx` (`COMPRADOR`);

--
-- Indices de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `MENSAJE_GRUPO_idx` (`GRUPO`),
  ADD KEY `MENSAJE_MARKETING_idx` (`MARKETING`);

--
-- Indices de la tabla `mensajecomprador`
--
ALTER TABLE `mensajecomprador`
  ADD PRIMARY KEY (`COMPRADOR`,`MENSAJE`),
  ADD KEY `MENSAJE_FK` (`MENSAJE`);

--
-- Indices de la tabla `preferencia`
--
ALTER TABLE `preferencia`
  ADD PRIMARY KEY (`COMPRADOR`,`CATEGORIA`),
  ADD KEY `PREFERENCIA_CATEGORIA_idx` (`CATEGORIA`),
  ADD KEY `PREFERENCIA_COMPRADOR_idx` (`COMPRADOR`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `CATEGORIA_idx` (`CATEGORIA`),
  ADD KEY `PRODUCTO_COMPRADOR_idx` (`COMPRADOR`),
  ADD KEY `PRODUCTO_VENDEDOR_idx` (`VENDEDOR`);

--
-- Indices de la tabla `puja`
--
ALTER TABLE `puja`
  ADD PRIMARY KEY (`COMPRADOR`,`PRODUCTO`,`PRECIO`),
  ADD KEY `PUJA_PRODUCTO_idx` (`PRODUCTO`),
  ADD KEY `PUJA_COMPRADOR_idx` (`COMPRADOR`);

--
-- Indices de la tabla `rol_usuario`
--
ALTER TABLE `rol_usuario`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NOMBRE_UQ` (`NOMBRE`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `CORREO_UNIQUE` (`CORREO`),
  ADD KEY `ROL_USUARIO_FK` (`ROL`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `grupo`
--
ALTER TABLE `grupo`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=117;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- AUTO_INCREMENT de la tabla `rol_usuario`
--
ALTER TABLE `rol_usuario`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `favorito`
--
ALTER TABLE `favorito`
  ADD CONSTRAINT `FAVORITO_COMPRADOR` FOREIGN KEY (`COMPRADOR`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FAVORITO_PRODUCTO` FOREIGN KEY (`PRODUCTO`) REFERENCES `producto` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `grupo`
--
ALTER TABLE `grupo`
  ADD CONSTRAINT `GRUPO_MARKETING` FOREIGN KEY (`MARKETING`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `grupocomprador`
--
ALTER TABLE `grupocomprador`
  ADD CONSTRAINT `GC_COMPRADOR` FOREIGN KEY (`COMPRADOR`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `GC_GRUPO` FOREIGN KEY (`GRUPO`) REFERENCES `grupo` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD CONSTRAINT `MENSAJE_GRUPO` FOREIGN KEY (`GRUPO`) REFERENCES `grupo` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `MENSAJE_MARKETING` FOREIGN KEY (`MARKETING`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `mensajecomprador`
--
ALTER TABLE `mensajecomprador`
  ADD CONSTRAINT `COMPRADOR_FK` FOREIGN KEY (`COMPRADOR`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `MENSAJE_FK` FOREIGN KEY (`MENSAJE`) REFERENCES `mensaje` (`ID`);

--
-- Filtros para la tabla `preferencia`
--
ALTER TABLE `preferencia`
  ADD CONSTRAINT `PREFERENCIA_CATEGORIA` FOREIGN KEY (`CATEGORIA`) REFERENCES `categoria` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PREFERENCIA_COMPRADOR` FOREIGN KEY (`COMPRADOR`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `PRODUCTO_CATEGORIA` FOREIGN KEY (`CATEGORIA`) REFERENCES `categoria` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PRODUCTO_COMPRADOR` FOREIGN KEY (`COMPRADOR`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PRODUCTO_VENDEDOR` FOREIGN KEY (`VENDEDOR`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `puja`
--
ALTER TABLE `puja`
  ADD CONSTRAINT `PUJA_COMPRADOR` FOREIGN KEY (`COMPRADOR`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PUJA_PRODUCTO` FOREIGN KEY (`PRODUCTO`) REFERENCES `producto` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `ROL_USUARIO_FK` FOREIGN KEY (`ROL`) REFERENCES `rol_usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
