CREATE TABLE monthly_settlements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    professional_id BIGINT NOT NULL,
    year INT NOT NULL,
    month INT NOT NULL,
    total_received DECIMAL(10,4) NOT NULL,
    settlement_amount DECIMAL(10,4) NOT NULL,
    status VARCHAR(20),
    payment_date DATETIME,
    CONSTRAINT fk_monthly_settlement_professional FOREIGN KEY (professional_id) REFERENCES users(id)
);
