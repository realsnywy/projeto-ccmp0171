CREATE TABLE patients(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    sex CHAR,
    birth_date DATE,
    CPF VARCHAR(25) UNIQUE,
    RG VARCHAR(25) UNIQUE,
    telephone VARCHAR(25) UNIQUE,
    email VARCHAR(100) UNIQUE,
    status VARCHAR(20),
    is_dependent BOOLEAN,
    guardian_id BIGINT,
    FOREIGN KEY (guardian_id) REFERENCES patient_guardians(id)
)