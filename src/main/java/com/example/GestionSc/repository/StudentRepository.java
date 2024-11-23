package com.example.GestionSc.repository;

import com.example.GestionSc.entites.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByCode(String code);

    List<Student> findByprogramId(String programId);
}
