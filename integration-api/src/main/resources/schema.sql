DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS student;

CREATE TABLE student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_number VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    enrollment_date TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_id VARCHAR(255) UNIQUE,
    student_number VARCHAR(255),
    amount DECIMAL(19,2),
    payment_method VARCHAR(50),
    payment_date TIMESTAMP,
    status VARCHAR(50),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version BIGINT
);