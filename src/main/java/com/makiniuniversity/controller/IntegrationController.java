package com.makiniuniversity.controller;

import com.makiniuniversity.dto.*;
import com.makiniuniversity.entity.Payment;
import com.makiniuniversity.service.PaymentService;
import com.makiniuniversity.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class IntegrationController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/students/validate")
    public ResponseEntity<StudentValidationResponseDTO> validateStudent(
            @Valid @RequestBody StudentValidationRequestDTO request) {
        boolean isValid = studentService.validateStudent(request.getStudentNumber());
        StudentValidationResponseDTO response = new StudentValidationResponseDTO();
        response.setValid(isValid);
        response.setMessage(isValid ? "Student validated successfully" : "Student not found");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/payments/notification")
    public ResponseEntity<PaymentNotificationResponseDTO> receivePaymentNotification(
            @Valid @RequestBody PaymentNotificationRequestDTO request) {
        Payment payment = paymentService.processPaymentNotification(request);
        PaymentNotificationResponseDTO response = new PaymentNotificationResponseDTO();
        response.setTransactionId(payment.getTransactionId());
        response.setMessage("Payment notification processed successfully");
        return payment.getId() != null ? ResponseEntity.status(HttpStatus.CREATED).body(response)
                : ResponseEntity.ok(response);
    }

    @GetMapping("/payments")
    public ResponseEntity<Page<PaymentResponseDTO>> getPayments(
            @RequestParam(required = false) String studentNumber,
            @RequestParam(required = false) String paymentMethod,
            Pageable pageable) {
        Page<PaymentResponseDTO> payments;
        if (studentNumber != null && paymentMethod != null) {
            payments = paymentService.getPaymentsByStudentAndMethod(studentNumber, paymentMethod, pageable);
        } else if (studentNumber != null) {
            payments = paymentService.getPaymentsByStudent(studentNumber, pageable);
        } else if (paymentMethod != null) {
            payments = paymentService.getPaymentsByMethod(paymentMethod, pageable);
        } else {
            throw new IllegalArgumentException("At least one filter (studentNumber or paymentMethod) is required");
        }
        return ResponseEntity.ok(payments);
    }
}