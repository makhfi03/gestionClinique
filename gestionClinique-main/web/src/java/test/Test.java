package test;

import dao.ConsultationDao;
import dao.IDao;
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
    private static final Logger LOGGER = Logger.getLogger(Test.class.getName());
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        try {
            // 1. Création et enregistrement d'un patient
            Patient patient = createPatient("Ghita", 28, "Femme");
            PatientDao patientDao = new PatientDao();
            saveEntity(patientDao, patient, "Patient");

            // 2. Création et enregistrement d'un médecin
            Medecin medecin = createMedecin("Dr. Benani", "Cardiologie");
            MedecinDao medecinDao = new MedecinDao();
            saveEntity(medecinDao, medecin, "Médecin");

            // 3. Création d'une consultation
            Date dateConsultation = parseDate("09/03/2025");
            Consultation consultation = createConsultation(patient, medecin, dateConsultation, "Cardio");

            // 4. Enregistrement de la consultation
            ConsultationDao consultationDao = new ConsultationDao();
            consultationDao.createEntities(patient, medecin);
            saveEntity(consultationDao, consultation, "Consultation");

            // 5. Test de suppression
            testDeleteConsultation(consultationDao, 1);

            // 6. Affichage de toutes les consultations
            displayAllConsultations(consultationDao);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Une erreur critique est survenue", e);
        }
    }

    private static Patient createPatient(String nom, int age, String sexe) {
        Patient patient = new Patient();
        patient.setNom(nom);
        patient.setAge(age);
        patient.setSexe(sexe);
        return patient;
    }

    private static Medecin createMedecin(String nom, String specialite) {
        Medecin medecin = new Medecin();
        medecin.setNom(nom);
        medecin.setSpecialite(specialite);
        return medecin;
    }

    private static Consultation createConsultation(Patient patient, Medecin medecin, 
                                                 Date date, String diagnostic) {
        Consultation consultation = new Consultation();
        consultation.setPatient(patient);
        consultation.setMedecin(medecin);
        consultation.setDateConsultation(date);
        consultation.setDiagnostic(diagnostic);
        return consultation;
    }

    private static Date parseDate(String dateStr) {
        try {
            return SDF.parse(dateStr);
        } catch (ParseException ex) {
            LOGGER.log(Level.SEVERE, "Erreur de parsing de date", ex);
            return new Date(); // Retourne la date actuelle par défaut
        }
    }

    private static <T> void saveEntity(IDao<T> dao, T entity, String entityName) {
        boolean success = dao.create(entity);
        System.out.println(success 
                ? entityName + " ajouté avec succès !" 
                : "Échec de l'ajout du " + entityName);
    }

    private static void testDeleteConsultation(ConsultationDao dao, int id) {
        Consultation toDelete = dao.findById(id);
        if (toDelete != null) {
            boolean deleted = dao.delete(toDelete);
            System.out.println(deleted 
                    ? "Consultation supprimée." 
                    : "Échec de suppression.");
        } else {
            System.out.println("Consultation avec ID " + id + " introuvable.");
        }
    }

    private static void displayAllConsultations(ConsultationDao dao) {
        List<Consultation> consultations = dao.findAll();
        if (consultations.isEmpty()) {
            System.out.println("Aucune consultation trouvée.");
            return;
        }

        System.out.println("\nListe des consultations:");
        for (Consultation c : consultations) {
            System.out.printf(" Patient: %s | Médecin: %s | Date: %s | Diagnostic: %s%n",
                    c.getPatient().getNom(),
                    c.getMedecin().getNom(),
                    SDF.format(c.getDateConsultation()),
                    c.getDiagnostic());
        }
    }
}