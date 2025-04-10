package test;

import dao.ConsultationDao;
import entities.Consultation;
import entities.Medecin;
import entities.Patient;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        try {
            // Création du patient
            Patient patient = new Patient();
            patient.setNom("Ghita Makhfi");
            patient.setAge(30);
            patient.setSexe("Femme");

            // Création du médecin
            Medecin medecin = new Medecin();
            medecin.setNom("Dr Leila Benani");
            medecin.setSpecialite("Cardiologie");

            // Conversion de la date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateConsultation = sdf.parse("09/04/2025");

            // Création de la consultation
            Consultation consultation = new Consultation();
            consultation.setPatient(patient);
            consultation.setMedecin(medecin);
            consultation.setDateConsultation(dateConsultation);
            consultation.setDiagnostic("Hypertension artérielle");

            // Sauvegarde via DAO
            ConsultationDao cd = new ConsultationDao();

            // Tu dois d'abord sauvegarder patient et médecin s'ils ne le sont pas
            cd.createEntities(patient, medecin); // <-- tu dois ajouter cette méthode si nécessaire

            cd.create(consultation);

            System.out.println("Consultation enregistrée avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
