CREATE TABLE usuarios(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    senha VARCHAR(100),
    CPF VARCHAR(25) UNIQUE,
    RG VARCHAR(25) UNIQUE,
    telefone VARCHAR(25) UNIQUE,
    especialidade VARCHAR(25),
    registro_profissional VARCHAR(25) UNIQUE,
    tipo_usuario VARCHAR(25)
)