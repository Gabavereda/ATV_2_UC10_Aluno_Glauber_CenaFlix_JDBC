
CREATE DATABASE ATIVIDADE1_UC10;


USE atividade1_uc10;

SELECT * FROM filmes;

CREATE TABLE `filmes` (
  `id` int NOT NULL  PRIMARY KEY,
  `nome` varchar(150) NOT NULL,
  `datalancamento` date NOT NULL,
  `categoria` varchar(100) NOT NULL
);



