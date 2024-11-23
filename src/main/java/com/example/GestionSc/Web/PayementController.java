package com.example.GestionSc.Web;

import com.example.GestionSc.dtos.NewPayement;
import com.example.GestionSc.entites.Payement;
import com.example.GestionSc.entites.PayementStatus;
import com.example.GestionSc.entites.PayementType;
import com.example.GestionSc.entites.Student;
import com.example.GestionSc.repository.PayementRepository;
import com.example.GestionSc.repository.StudentRepository;
import com.example.GestionSc.services.PayementService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@RestController
@CrossOrigin(origins = "*")

public class PayementController {

    private StudentRepository studentRepository;
    private PayementRepository payementRepository;

    private PayementService payementService;

    public PayementController(StudentRepository studentRepository, PayementRepository payementRepository, PayementService payementService) {
        this.studentRepository = studentRepository;
        this.payementRepository = payementRepository;
        this.payementService = payementService;
    }


    @GetMapping(path = "/payements")
    public List<Payement> allPayement(){
        return payementRepository.findAll();

    }


    @GetMapping(path = "/students/{code}/payements")
    public List<Payement> payementByStudent(@PathVariable String code){
        return payementRepository.findByStudentCode(code);
    }

    @GetMapping(path = "/payements/byStatus")
    public List<Payement> payementByStatus(@RequestParam PayementStatus status){
        return payementRepository.findByStatus(status);
    }

    @GetMapping(path = "/payements/byType")
    public List<Payement> payementByTypes(@RequestParam PayementType type){
        return payementRepository.findByType(type);
    }

    @GetMapping(path = "/payements/{id}")
    public Payement getPayement(Long id){
        return payementRepository.findById(id).get();
    }


    @GetMapping(path = "/students")
    public List<Student> allStudents(){
        return studentRepository.findAll();
    }


    @GetMapping(path = "/students/{code}")
    public Student getStudentByCode(@PathVariable String code){
        return studentRepository.findByCode(code);
    }


    @GetMapping(path = "/studentsProgramId")
    public List<Student> getStudentByProgram(@RequestParam String programId){
        return studentRepository.findByprogramId(programId);
    }



    @PostMapping(path = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payement savePayement(@RequestParam("file") MultipartFile file, NewPayement newPayement) throws IOException{

        return this.payementService.savePayement(file, newPayement);


    }

    //consulter le fichier

//    @GetMapping(path = "/paymentFile/{payementId}", produces = MediaType.APPLICATION_PDF_VALUE)
//    public byte[] getPayementFile(@PathVariable Long paymentId) throws IOException{
//        Payement payement =payementRepository.findById(paymentId).get();
//        return Files.readAllBytes(path.of(payement.getFile()));
//    }
@GetMapping(path = "/paymentFile/{paymentId}", produces = MediaType.APPLICATION_PDF_VALUE)
public byte[] getPayementFile(@PathVariable Long paymentId) throws IOException {
    // Récupérer le paiement à partir du repository
    Payement payement = payementRepository.findById(paymentId).orElseThrow(() -> new FileNotFoundException("Payement not found"));

    // Récupérer le chemin du fichier
    Path filePath = Paths.get(payement.getFile());

    // Vérifier si le fichier existe
    if (!Files.exists(filePath)) {
        throw new FileNotFoundException("File not found: " + filePath);
    }

    // Retourner le contenu du fichier sous forme de byte array
    return Files.readAllBytes(filePath);
}

}
