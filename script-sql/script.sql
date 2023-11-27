CREATE
    DATABASE `seg_info` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION = 'N' */;

use
    seg_info;

CREATE TABLE `medicos`
(
    `id`            int          NOT NULL AUTO_INCREMENT,
    `nome`          varchar(255) NOT NULL,
    `cpf`           varchar(11)  NOT NULL,
    `especialidade` varchar(255) DEFAULT NULL,
    `login`         varchar(255) NOT NULL,
    `senha_hash`    varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `pacientes`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `nome`       varchar(255) NOT NULL,
    `cpf`        varchar(11)  NOT NULL,
    `login`      varchar(255) NOT NULL,
    `senha_hash` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `senhas`
(
    `id`            int          NOT NULL,
    `chave_secreta` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `valorespadroes`
(
    `id`              int         NOT NULL,
    `descricao`       varchar(255) DEFAULT NULL,
    `limite_superior` int         NOT NULL,
    `limite_inferior` int         NOT NULL,
    `unidade`         varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `vitamina_d`
(
    `id`    int          NOT NULL,
    `ng_mL` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `seg_info`.`valorespadroes`
(`id`,
 `descricao`,
 `limite_superior`,
 `limite_inferior`,
 `unidade`)
VALUES (1,
        'Normal',
        100,
        50,
        'ng/mL'),
       (2,
        'Insuficiente',
        49,
        30,
        'ng/mL'),
       (3,
        'Deficiente',
        29,
        20,
        'ng/mL')








