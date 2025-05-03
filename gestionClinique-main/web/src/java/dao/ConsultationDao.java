package dao;

import entities.Consultation;
import entities.Medecin;
import entities.Patient;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class ConsultationDao extends AbstractDao<Consultation> {

    public ConsultationDao() {
        super(Consultation.class);
    }

    public List<Consultation> findByDiagnostic(String diagnostic) {
        Session session = null;
        Transaction tx = null;
        List<Consultation> consultations = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            consultations = session.createQuery("FROM Consultation WHERE diagnostic = :diagnostic")
                                .setParameter("diagnostic", diagnostic)
                                .list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return consultations;
    }

    public List<Consultation> findByDate(Date date) {
        Session session = null;
        Transaction tx = null;
        List<Consultation> consultations = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            consultations = session.createQuery("FROM Consultation WHERE date = :date")
                                .setParameter("date", date)
                                .list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return consultations;
    }

    public Consultation findByIdWithDetails(int id) {
        Session session = null;
        Transaction tx = null;
        Consultation consultation = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            consultation = (Consultation) session.get(Consultation.class, id);
            // Initialiser les relations si nécessaire
            if (consultation != null) {
                consultation.getPatient().toString(); // Charge le patient
                consultation.getMedecin().toString(); // Charge le médecin
            }
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return consultation;
    }

    public void createEntities(Patient patient, Medecin medecin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}