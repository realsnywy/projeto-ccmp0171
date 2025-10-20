CREATE TABLE appointments(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    issue_date DATETIME,
    appointment_date DATETIME,
    patient_id BIGINT,
    professional_id BIGINT
)