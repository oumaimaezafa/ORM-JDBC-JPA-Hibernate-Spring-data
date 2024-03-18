package ma.enset.hospital.web;

import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientRestController {
    @Autowired //injection de depandances
    private PatientRepository patientRepository;


   @GetMapping("/patients")
    public List<Patient> patientList(){
        return patientRepository.findAll();
    }

    @GetMapping("/patients/{id}")
    public Patient findPatient(@PathVariable Long id){
       Patient patient=patientRepository.findById(id).orElse(null);
       return patient;
    }

    @DeleteMapping("/patients/{id}")
    public Patient deleePatient(@PathVariable Long id ){
        Patient patient=patientRepository.findById(id).orElse(null);
        return patient;
    }

}
