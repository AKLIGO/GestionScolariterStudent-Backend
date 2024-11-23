package com.example.GestionSc.dtos;

import com.example.GestionSc.entites.PayementType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class NewPayement {
    private double amount;
    private PayementType type;

    private LocalDate date;
    private String studentCode;
}
