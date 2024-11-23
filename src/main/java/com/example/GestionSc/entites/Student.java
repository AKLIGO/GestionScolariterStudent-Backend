package com.example.GestionSc.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Student {
    @Id
    private String id;

    private String firstName;

    private String LastName;
    @Column(unique = true)
    private String code;

    private String programId;

    private String photo;
//    @OneToMany(mappedBy = "student")
//    private List<Payement> payements;
}
