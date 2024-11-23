package com.example.GestionSc.repository;

import com.example.GestionSc.entites.Payement;
import com.example.GestionSc.entites.PayementStatus;
import com.example.GestionSc.entites.PayementType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayementRepository extends JpaRepository<Payement, Long> {
    List<Payement> findByStudentCode(String code);

    List<Payement> findByStatus(PayementStatus status);

    List<Payement> findByType(PayementType type);

}
