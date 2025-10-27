

-- -- Seed Students
-- INSERT INTO student (id, student_number, name, email, enrollment_date, created_at, updated_at) 
-- VALUES (1, 'STU001', 'John Doe', 'john@example.com', '2023-01-01T00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- INSERT INTO student (id, student_number, name, email, enrollment_date, created_at, updated_at) 
-- VALUES (2, 'STU002', 'Jane Smith', 'jane.smith@edgecase.com', '2023-02-01T00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- INSERT INTO student (id, student_number, name, email, enrollment_date, created_at, updated_at) 
-- VALUES (3, 'STU003', 'Bob Johnson', 'bob.johnson@makini.edu', '2023-03-01T00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- -- Seed Payments
-- INSERT INTO payment (id, transaction_id, student_number, amount, payment_method, payment_date, status, created_at, updated_at, version) 
-- VALUES (1,'TX001', 'STU001', 100.00, 'MPESA', '2024-10-01T10:00:00', 'SUCCESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
-- INSERT INTO payment (id, transaction_id, student_number, amount, payment_method, payment_date, status, created_at, updated_at, version) 
-- VALUES (2, 'TX002', 'STU001', 50.00, 'CARD', '2024-10-02T11:00:00', 'FAILED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
-- INSERT INTO payment (id, transaction_id, student_number, amount, payment_method, payment_date, status, created_at, updated_at, version) 
-- VALUES (3,'TX003', 'STU002', 0.00, 'BANK_TRANSFER', '2024-10-03T12:00:00', 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
-- INSERT INTO payment (id, transaction_id, student_number, amount, payment_method, payment_date, status, created_at, updated_at, version) 
-- VALUES (4,'TX004', 'STU002', 200.00, 'MPESA', '2024-10-04T13:00:00', 'SUCCESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

-- Seed Students
INSERT INTO student (student_number, name, email, enrollment_date, created_at, updated_at) 
VALUES ('STU001', 'John Doe', 'john.doe@makini.edu', '2023-01-01 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO student (student_number, name, email, enrollment_date, created_at, updated_at) 
VALUES ('STU002', 'Jane Smith', 'jane.smith@makini.edu', '2023-02-01 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO student (student_number, name, email, enrollment_date, created_at, updated_at) 
VALUES ('STU003', 'Bob Johnson', 'bob.johnson@makini.edu', '2023-03-01 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Seed Payments
INSERT INTO payment (transaction_id, student_number, amount, payment_method, payment_date, status, created_at, updated_at, version) 
VALUES ('TX001', 'STU001', 100.00, 'MPESA', '2024-10-01 10:00:00', 'SUCCESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

INSERT INTO payment (transaction_id, student_number, amount, payment_method, payment_date, status, created_at, updated_at, version) 
VALUES ('TX002', 'STU001', 50.00, 'CARD', '2024-10-02 11:00:00', 'FAILED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

INSERT INTO payment (transaction_id, student_number, amount, payment_method, payment_date, status, created_at, updated_at, version) 
VALUES ('TX003', 'STU002', 75.00, 'BANK_TRANSFER', '2024-10-03 12:00:00', 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

INSERT INTO payment (transaction_id, student_number, amount, payment_method, payment_date, status, created_at, updated_at, version) 
VALUES ('TX004', 'STU003', 200.00, 'MPESA', '2024-10-04 13:00:00', 'SUCCESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);