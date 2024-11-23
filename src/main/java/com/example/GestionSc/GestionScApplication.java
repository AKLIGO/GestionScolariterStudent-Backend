package com.example.GestionSc;

import com.example.GestionSc.entites.Payement;
import com.example.GestionSc.entites.PayementStatus;
import com.example.GestionSc.entites.PayementType;
import com.example.GestionSc.entites.Student;
import com.example.GestionSc.repository.PayementRepository;
import com.example.GestionSc.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class GestionScApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionScApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository, PayementRepository payementRepository){
		return args -> {
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
							.firstName("Mohamed").code("112332").LastName("Abalo").programId("sde")
					.build());

			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Achiraf").code("12332").LastName("Koffi").programId("sda")
					.build());


			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Moha").code("1123325").LastName("Aba").programId("sde")
					.build());

			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("APPOH").code("142332").LastName("Etonam").programId("sde")
					.build());




			PayementType[] payementTypes=PayementType.values();

			Random random=new Random();
			studentRepository.findAll().forEach(st->{
				for (int i=0; i<10; i++){
					int index=random.nextInt(payementTypes.length);
					Payement payement=Payement.builder()
							.amount(1000+(int)(Math.random()*20000))
							.type(payementTypes[index])
							.status(PayementStatus.CREATED)
							.date(LocalDate.now())
							.student(st)
							.build();
					payementRepository.save(payement);
				}
			});
		};
	};

}
