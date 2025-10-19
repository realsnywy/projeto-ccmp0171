CREATE TABLE users(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    CPF VARCHAR(255) UNIQUE,
    RG VARCHAR(255) UNIQUE,
    telephone VARCHAR(255) UNIQUE,
    speciality VARCHAR(25),
    professional_register VARCHAR(255) UNIQUE,
    user_type VARCHAR(25)
)