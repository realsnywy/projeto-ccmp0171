CREATE TABLE appointments(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    patient_id BIGINT,
    professional_id BIGINT,
    appointment_date DATETIME,
    issue_date DATETIME,
    amount DECIMAL(10,2)

)