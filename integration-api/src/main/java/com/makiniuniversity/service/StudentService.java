package com.makiniuniversity.service;

// import com.makiniuniversity.entity.Student;
import com.makiniuniversity.exception.ResourceNotFoundException;
import com.makiniuniversity.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public boolean validateStudent(String studentNumber) {
        logger.debug("Validating student with number: {}", studentNumber);
        return studentRepository.findByStudentNumber(studentNumber)
                .map(student -> {
                    logger.info("Student found: {}", student.getName());
                    return true;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with number: " + studentNumber));
    }
}