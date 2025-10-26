package com.makiniuniversity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.makiniuniversity.repository.PaymentRepository;
import com.makiniuniversity.repository.StudentRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        return args -> {
            System.out.println("Seeded Students: " + studentRepository.findAll());
            System.out.println("Seeded Payments: " + paymentRepository.findAll());
        };
    }
}
