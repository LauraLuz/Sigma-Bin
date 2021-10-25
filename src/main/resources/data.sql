CREATE TABLE tb_endereco (
    id_endereco bigint PRIMARY KEY ,
    rua    VARCHAR(50) NOT NULL,
    numero    VARCHAR(50) NOT NULL,
    cep    VARCHAR(50) NOT NULL,
    cidade    VARCHAR(50) NOT NULL,
    estado    VARCHAR(50) NOT NULL,
    pais    VARCHAR(50) NOT NULL
);
CREATE TABLE tb_complexo (
    id_complexo bigint PRIMARY KEY ,
    id_endereco  bigint NOT NULL
        REFERENCES tb_endereco,
    nome    VARCHAR(50) NOT NULL
);
CREATE TABLE tb_usuario (
    id_usuario   bigint PRIMARY KEY ,
    id_complexo  bigint NOT NULL
        REFERENCES tb_complexo,
    nome    VARCHAR(50) NOT NULL,
    email   VARCHAR(50) NOT NULL,
    telefone   VARCHAR(20) NOT NULL,
    senha  VARCHAR(30)
);
CREATE TABLE tb_admin (
    id_admin   bigint PRIMARY KEY ,
    id_complexo  bigint NOT NULL
        REFERENCES tb_complexo,
    nome    VARCHAR(50) NOT NULL,
    email   VARCHAR(50) NOT NULL,
    telefone   bigint(11) NOT NULL,
    senha  VARCHAR(100)
);
CREATE TABLE tb_lixeira (
    id_lixeira   bigint PRIMARY KEY ,
    id_endereco  bigint NOT NULL
        REFERENCES tb_endereco,
    status   VARCHAR(50)   CHECK( status IN ('CHEIA','VAZIA','MEIO_CHEIO', 'MANUTENCAO') ),
    id_complexo  bigint NOT NULL
        REFERENCES tb_complexo,
    capacidade   VARCHAR(50) NOT NULL,
    nivel_residuo  bigint NOT NULL,
    tipoResiduo    VARCHAR(50)  CHECK( tipoResiduo IN ('ORGANICO', 'RECICLAVEL'))
);
-- INSERTS
-- tb_endereco
INSERT INTO tb_endereco (id_endereco, rua, numero, cep, cidade, estado, pais) VALUES (
    1,
    'Rua das alpineias',
    '21',
    '75-887-091',
    'Goiânia',
    'GO',
    'Brasil'
);
INSERT INTO tb_endereco (id_endereco, rua, numero, cep, cidade, estado, pais) VALUES (
    2,
    'Rua das rosas',
    '11',
    '75-887-000',
    'Goiânia',
    'GO',
    'Brasil'
);
INSERT INTO tb_endereco (id_endereco, rua, numero, cep, cidade, estado, pais) VALUES (
    3,
    'Rua dos pinheiros',
    '13',
    '05-887-000',
    'São Paulo',
    'SP',
    'Brasil'
);
-- tb_complexo
INSERT INTO tb_complexo (id_complexo, id_endereco, nome) VALUES (
    1,
    1,
    'Parque da Lagoa'
);
INSERT INTO tb_complexo (id_complexo, id_endereco, nome) VALUES (
    2,
    2,
    'Parque das Rosas'
);
INSERT INTO tb_complexo (id_complexo, id_endereco, nome) VALUES (
    3,
    3,
    ' Parque Ibirapuera'
);
-- tb_usuario
INSERT INTO tb_usuario (id_usuario, id_complexo, nome, email, telefone, senha) VALUES (
    1,
    1,
    'Laura Luz',
    'lauraluza@gmail.com',
    '62999996767',
    'senha1'
);
INSERT INTO tb_usuario (id_usuario, id_complexo, nome, email, telefone, senha) VALUES (
    2,
    1,
    'Lucas Castro',
    'lucass@gmail.com',
    '62999992222',
    'senha2'
);
INSERT INTO tb_usuario (id_usuario, id_complexo, nome, email, telefone, senha) VALUES (
    3,
    2,
    'Diogo',
    'diogo@gmail.com',
    '62999994444',
    'senha3'
);
-- tb admin
INSERT INTO tb_admin (id_admin, id_complexo, nome, email, telefone, senha) VALUES (
    18,
    1,
    'Laura Boss',
    'lauraBoss@gmail.com',
    '62999996565',
    'senha1'
);
INSERT INTO tb_admin (id_admin, id_complexo, nome, email, telefone, senha) VALUES (
    2,
    1,
    'Lucas Boss',
    'lucassBoss@gmail.com',
    '62999992332',
    'senha2'
);
INSERT INTO tb_admin (id_admin, id_complexo, nome, email, telefone, senha) VALUES (
    3,
    2,
    'Diogo Boss',
    'diogoBoss@gmail.com',
    '62999994114',
    'senha3'
);
-- tb lixeira
INSERT INTO tb_lixeira (id_lixeira, id_endereco, status, id_complexo, capacidade, nivel_residuo, tipoResiduo) VALUES (
    1,
    1,
    'CHEIA',
    1,
    '50L',
    25,
    'ORGANICO'
);
INSERT INTO tb_lixeira (id_lixeira, id_endereco, status, id_complexo, capacidade, nivel_residuo, tipoResiduo) VALUES (
    2,
    1,
    'MEIO_CHEIO',
    1,
    '60L',
    85,
    'ORGANICO'
);
INSERT INTO tb_lixeira (id_lixeira, id_endereco, status, id_complexo, capacidade, nivel_residuo, tipoResiduo) VALUES (
    3,
    1,
    'MANUTENCAO',
    1,
    '60L',
    85,
    'RECICLAVEL'
);
