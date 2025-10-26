package com.makiniuniversity.repository;

import com.makiniuniversity.entity.Payment;
import com.makiniuniversity.entity.PaymentMethod;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByTransactionId(String transactionId);

    Page<Payment> findByStudentNumber(String studentNumber, Pageable pageable);

    Page<Payment> findByPaymentMethod(PaymentMethod paymentMethod, Pageable pageable);

    Page<Payment> findByStudentNumberAndPaymentMethod(String studentNumber, PaymentMethod paymentMethod, Pageable pageable);
}