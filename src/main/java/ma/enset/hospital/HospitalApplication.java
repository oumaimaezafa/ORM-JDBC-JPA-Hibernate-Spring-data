package ma.enset.hospital;

import ma.enset.hospital.entities.*;
import ma.enset.hospital.repositories.ConsultationRepository;
import ma.enset.hospital.repositories.MedecinRepository;
import ma.enset.hospital.repositories.PatientRepository;
import ma.enset.hospital.repositories.RendzVousRepository;
import ma.enset.hospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication  {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}


	@Bean // pour executer la methode dans le demarrage  au meme temps il va retourner un objet ou il devient un composant Spring
 	CommandLineRunner start(IHospitalService iHospitalService,PatientRepository patientRepository,RendzVousRepository rendzVousRepository,ConsultationRepository consultationRepository,MedecinRepository medecinRepository){
      return args -> {
		  Stream.of("Oumaima","Meryem","Hassan")
				  .forEach(name->{
					  Patient patient=new Patient();
					  patient.setNom(name);
					  patient.setMalade(false);
					  patient.setDateNaissance(new Date());
					  iHospitalService.savePatient(patient);
				  });

		  Stream.of("med1","med2","med3")
				  .forEach(name->{
					  Medecin medecin=new Medecin();
					  medecin.setNom(name);
					  medecin.setSpecialite(Math.random()> 0.5 ? "Cardio":"General");
					  medecin.setMail(name+"@gmail.com");
					  iHospitalService.saveMedecin(medecin);
				  });
			  Patient patient=patientRepository.findById(1L).orElse(null);
			  Medecin medecin=medecinRepository.findByNom("med1");

		  RendezVous rendezVous=new RendezVous();
		  rendezVous.setDate(new Date());
		  rendezVous.setStatusRNDV(StatusRNDV.PENDING);
		  rendezVous.setMedecin(medecin);
		  rendezVous.setPatient(patient);
		  iHospitalService.saveRDV(rendezVous);


		  RendezVous rendezVous1=rendzVousRepository.findAll().get(0);
		  Consultation consultation =new Consultation();
		  consultation.setDateConsultation(new Date());
		  consultation.setRendezVous(rendezVous1);
		  consultation.setRapport("Rapport de consultation");
		  iHospitalService.saveConsultation(consultation);


	  };
	}
}
