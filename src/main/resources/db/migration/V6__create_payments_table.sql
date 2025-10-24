CREATE TABLE payments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    appointment_id BIGINT,
    status VARCHAR(50),
    payment_method VARCHAR(50),
    payment_date DATETIME
);