CREATE TABLE users(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    CPF VARCHAR(25) UNIQUE,
    RG VARCHAR(25) UNIQUE,
    telephone VARCHAR(25) UNIQUE,
    speciality VARCHAR(25),
    professional_register VARCHAR(25) UNIQUE,
    user_type VARCHAR(25)
)