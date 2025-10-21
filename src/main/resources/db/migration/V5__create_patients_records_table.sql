CREATE TABLE patients_records(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    appointment_id BIGINT UNIQUE,
    appointment_summary TEXT,
    record_date DATETIME,
    file MEDIUMBLOB,
    file_type VARCHAR(30)
);