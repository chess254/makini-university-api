package com.makiniuniversity.service;

import com.makiniuniversity.dto.PaymentNotificationRequestDTO;
import com.makiniuniversity.dto.PaymentResponseDTO;
import com.makiniuniversity.entity.Payment;
import com.makiniuniversity.entity.PaymentMethod;
import com.makiniuniversity.entity.PaymentStatus;
import com.makiniuniversity.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StudentService studentService;

    @Transactional
    public Payment processPaymentNotification(PaymentNotificationRequestDTO dto) {
        logger.debug("Processing payment notification for transactionId: {}", dto.getTransactionId());

        return paymentRepository.findByTransactionId(dto.getTransactionId())
                .map(existingPayment -> {
                    logger.info("Duplicate notification for transactionId: {}. Skipping creation.",
                            dto.getTransactionId());
                    return existingPayment;
                })
                .orElseGet(() -> {
                    studentService.validateStudent(dto.getStudentNumber());

                    Payment payment = new Payment();
                    payment.setTransactionId(dto.getTransactionId());
                    payment.setStudentNumber(dto.getStudentNumber());
                    payment.setAmount(dto.getAmount());
                    try {
                        payment.setPaymentMethod(PaymentMethod.valueOf(dto.getPaymentMethod().toUpperCase()));
                        payment.setStatus(PaymentStatus.valueOf(dto.getStatus().toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Invalid payment method or status");
                    }
                    payment.setPaymentDate(dto.getPaymentDate());

                    logger.info("Creating new payment for student: {}", dto.getStudentNumber());
                    return paymentRepository.save(payment);
                });
    }

    public Page<PaymentResponseDTO> getPaymentsByStudent(String studentNumber, Pageable pageable) {
        return paymentRepository.findByStudentNumber(studentNumber, pageable)
                .map(this::mapToDTO);
    }

    public Page<PaymentResponseDTO> getPaymentsByMethod(String paymentMethod, Pageable pageable) {
        return paymentRepository.findByPaymentMethod(paymentMethod, pageable)
                .map(this::mapToDTO);
    }

    public Page<PaymentResponseDTO> getPaymentsByStudentAndMethod(String studentNumber, String paymentMethod,
            Pageable pageable) {
        return paymentRepository.findByStudentNumberAndPaymentMethod(studentNumber, paymentMethod, pageable)
                .map(this::mapToDTO);
    }

    private PaymentResponseDTO mapToDTO(Payment payment) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setTransactionId(payment.getTransactionId());
        dto.setStudentNumber(payment.getStudentNumber());
        dto.setAmount(payment.getAmount());
        dto.setPaymentMethod(payment.getPaymentMethod().name());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setStatus(payment.getStatus().name());
        return dto;
    }
}