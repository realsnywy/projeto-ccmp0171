CREATE TABLE payments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    appointment_id BIGINT NOT NULL UNIQUE,
    amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) NULL,
    payment_method VARCHAR(50),
    payment_date DATETIME
);