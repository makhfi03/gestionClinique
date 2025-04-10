package test;

import dao.ConsultationDao;
import dao.MedecinDao;
import dao.PatientDao;
import entities.Consultation;
import entities.Medecin;
import entities.Patient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {
    public static void main(String[] args) {
        try {
           
            Patient patient = new Patient();
            patient.setNom("Ghita");
            patient.setAge(28);
            patient.setSexe("Femme");

            Medecin medecin = new Medecin();
            medecin.setNom("Dr. Benani");
            medecin.setSpecialite("Cardiologie");
            
            PatientDao patientDao = new PatientDao();
            boolean patientAjoute = patientDao.create(patient);
            if (patientAjoute) {
                System.out.println("Patient ajouté avec succès !");
            }

            MedecinDao medecinDao = new MedecinDao();
            boolean medecinAjoute = medecinDao.create(medecin);
            if (medecinAjoute) {
                System.out.println("Médecin ajouté avec succès !");
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateConsultation = null;
            try {
                dateConsultation = sdf.parse("09/03/2025");
            } catch (ParseException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }

            Consultation consultation = new Consultation();
            consultation.setPatient(patient);
            consultation.setMedecin(medecin);
            consultation.setDateConsultation(dateConsultation);
            consultation.setDiagnostic("Cardio");

            ConsultationDao cd = new ConsultationDao();

            cd.createEntities(patient, medecin);

            boolean ajout = cd.create(consultation);
            if (ajout) {
                System.out.println("Consultation enregistrée avec succès !");
            }

            Consultation toDelete = cd.findById(1);
            if (toDelete != null) {
                boolean deleted = cd.delete(toDelete);
                System.out.println(deleted ? "Consultation supprimée." : "Échec de suppression.");
            } else {
                System.out.println("Consultation avec ID 1 introuvable.");
            }

            List<Consultation> consultations = cd.findAll();
            for (Consultation c : consultations) {
                System.out.println("ID: " + c.getId() +
                                   ", Patient: " + c.getPatient().getNom() +
                                   ", Médecin: " + c.getMedecin().getNom() +
                                   ", Date: " + sdf.format(c.getDateConsultation()) +
                                   ", Diagnostic: " + c.getDiagnostic());
            }

        } catch (Exception e) {
        }
    }
}
