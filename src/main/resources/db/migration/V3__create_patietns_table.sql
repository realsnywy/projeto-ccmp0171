CREATE TABLE patients(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    sex CHAR,
    birth_date DATE,
    CPF VARCHAR(255) UNIQUE,
    RG VARCHAR(255) UNIQUE,
    telephone VARCHAR(255) UNIQUE,
    email VARCHAR(255) UNIQUE,
    status VARCHAR(20),
    is_dependent BOOLEAN,
    guardian_id BIGINT,
    FOREIGN KEY (guardian_id) REFERENCES patient_guardians(id)
)