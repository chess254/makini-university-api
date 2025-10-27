# Makini University REST API

This project provides a REST API for Makini University to integrate with Cellulant's Payment Gateway. It supports:
- Validating students by student number.
- Receiving and processing payment notifications with idempotency.
- Querying payments by student number, payment method, or both, with pagination.
- File-based H2 database with automatic schema and data initialization on each startup.

The API uses Spring Boot 3.1.0, Java 17, H2 database, and Lombok, with best practices for error handling, logging, and data validation.

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Dependencies](#dependencies)
3. [Installation](#installation)
4. [Project Structure](#project-structure)
5. [Cloning the Repository](#cloning-the-repository)
6. [Running the Application](#running-the-application)
7. [Testing Endpoints](#testing-endpoints)

## Prerequisites
Ensure you have the following installed:
- **Java 17 (OpenJDK)**: Download from [Adoptium](https://adoptium.net/). Set `JAVA_HOME` environment variable.
- **Maven**: Download from [Maven](https://maven.apache.org/) and add to PATH.
- **Git**: For cloning the repository.
- **curl** (or any prefered means): For testing APIs.

## Dependencies
The project uses the following Maven dependencies (defined in `pom.xml`):
- `spring-boot-starter-web`: For REST API support.
- `spring-boot-starter-data-jpa`: For database operations with Hibernate.
- `h2`: File-based H2 database for staging.
- `spring-boot-starter-validation`: For request validation.
- `lombok`: To reduce boilerplate code.
- `spring-boot-starter-test`: For testing (scope: test).

See `pom.xml` for full details.

## Installation
1. **Clone repo**:
   - `Ctrl+Shift+X`
   

2. **Verify Java and Maven**:
   - Open a terminal in VS Code (`Ctrl+``).
   - Run:
     ```bash
     java -version
     mvn -version

Ensure Java 17 and Maven are listed.


## Project Structure
```
src/main/java/com/makiniuniversity/
    ├── MakiniUniversityApplication.java
        ├── DataInitializer.java
        ├── config/
        │   └── AuditConfig.java
        ├── controller/
        │   └── IntegrationController.java
        ├── dto/
        │   ├── PaymentNotificationRequestDTO.java
        │   ├── PaymentNotificationResponseDTO.java
        │   ├── PaymentResponseDTO.java
        │   ├── StudentValidationRequestDTO.java
        │   └── StudentValidationResponseDTO.java
        ├── entity/
        │   ├── Payment.java
        │   ├── PaymentMethod.java
        │   ├── PaymentStatus.java
        │   └── Student.java
        ├── exception/
        │   ├── CustomExceptionHandler.java
        │   └── ResourceNotFoundException.java
        ├── repository/
        │   ├── PaymentRepository.java
        │   └── StudentRepository.java
        ├── service/
        │   ├── PaymentService.java
        │   └── StudentService.java
src/main/resources/
    ├── application.properties
    ├── data.sql
```

## Cloning the Repository

Clone the repository:
``` 
git clone <repository-url>  # Replace with your repo URL
cd makini-university-api 
```

## Running the Application

1. **In Terminal run:**

    ```
    mvn clean install
    mvn spring-boot:run
    ```

    - Logs should show:
        - Schema drop/create: `drop table if exists payment, create table payment.`
        - Script execution: `DEBUG ... Executing SQL script from resource [data.sql].`
        - INSERT statements for `student` and `payment`.
        - `Seeded Students: [Student(id=1, ...), ...]`, `Seeded Payments: [Payment(id=1, ...), ...]`.


2. **Verify Data**:

    - Open H2 console: http://localhost:8080/h2-console 
    - Ensure:
        - JDBC URL: `jdbc:h2:mem:testdb`
        - Username: `sa`
        - Password: 

    - To view sample seeded Student and Payment data, run:
        ``` 
        SELECT * FROM PUBLIC.student;
        SELECT * FROM PUBLIC.payment;
        ```


## Testing Endpoints

### Validate Student
-    POST `http://localhost:8080/api/students/validate`

-       {"studentNumber": "STU001"}

### Invalid Student
-   POST `http://localhost:8080/api/students/validate`

-       {"studentNumber": "STU999"}

### Empty Student Number
-   POST `http://localhost:8080/api/students/validate`

-       {"studentNumber": ""}

### New Payment
-   POST `http://localhost:8080/api/payments/notification`
-       {
            "transactionId": "TX005",
            "studentNumber": "STU001",
            "amount": 150.00,
            "paymentMethod": "CARD",
            "status": "SUCCESS" 
        }

### Duplicate Payment
-   POST `http://localhost:8080/api/payments/notification`  


-       {
            "transactionId": "TX001",
            "studentNumber": "STU001",
            "amount": 100.00,
            "paymentMethod": "MPESA",
            "status": "SUCCESS"
        }

### Invalid Payment Method
-   POST `http://localhost:8080/api/payments/notification`

-       {
            "transactionId": "TX006",
            "studentNumber": "STU001",
            "amount": 100.00,
            "paymentMethod": "INVALID",
            "status": "SUCCESS"
        }

### Negative Amount
-   POST `http://localhost:8080/api/payments/notification`

-       {
            "transactionId": "TX006",
            "studentNumber": "STU001",
            "amount": -100.00,
            "paymentMethod": "MPESA",
            "status": "SUCCESS"
        }

### Query Payments by Student
-     GET http://localhost:8080/api/payments?studentNumber=STU001

### Query Payments by Method
 -     GET http://localhost:8080/api/payments?paymentMethod=MPESA

### Query No Payments
-       GET http://localhost:8080/api/payments?studentNumber=STU003

### Query with Pagination
-       GET http://localhost:8080/api/payments?studentNumber=STU001&page=0&size=1

### Query No Filters
-       GET http://localhost:8080/api/payments
