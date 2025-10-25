package com.makiniuniversity.repository;

import com.makiniuniversity.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByTransactionId(String transactionId);

    Page<Payment> findByStudentNumber(String studentNumber, Pageable pageable);

    Page<Payment> findByPaymentMethod(String paymentMethod, Pageable pageable);

    Page<Payment> findByStudentNumberAndPaymentMethod(String studentNumber, String paymentMethod, Pageable pageable);
}