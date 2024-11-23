package com.example.GestionSc.services;

import com.example.GestionSc.dtos.NewPayement;
import com.example.GestionSc.entites.Payement;
import com.example.GestionSc.entites.PayementStatus;
import com.example.GestionSc.entites.Student;
import com.example.GestionSc.repository.PayementRepository;
import com.example.GestionSc.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Transactional
public class PayementService {

    private StudentRepository studentRepository;


    private PayementRepository payementRepository;

    public PayementService(StudentRepository studentRepository, PayementRepository payementRepository) {
        this.studentRepository = studentRepository;

        this.payementRepository = payementRepository;
    }

    public Payement savePayement( MultipartFile file, NewPayement newPayement) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"), "enset-data", "payments");
        if (!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }

        String fileName= UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"), "enset-data", "payments",fileName+".pdf");

        Files.copy(file.getInputStream(), filePath);

        Student student =studentRepository.findByCode(newPayement.getStudentCode());

        Payement payement =Payement.builder()
                .date(newPayement.getDate())
                .type(newPayement.getType())
                .student(student)
                .amount(newPayement.getAmount())
                .file(filePath.toUri().toString())
                .status(PayementStatus.CREATED)
                .build();
        return payementRepository.save(payement);

    }
}
