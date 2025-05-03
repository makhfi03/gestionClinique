package dao;

import entities.Patient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class PatientDao implements IDao<Patient> {

    @Override
    public boolean create(Patient patient) {
        return executeTransaction(session -> session.save(patient));
    }

    @Override
    public boolean delete(Patient patient) {
        return executeTransaction(session -> session.delete(patient));
    }

    @Override
    public boolean update(Patient patient) {
        return executeTransaction(session -> session.update(patient));
    }

    @Override
    public List<Patient> findAll() {
        Session session = null;
        Transaction tx = null;
        List<Patient> patients = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            patients = session.createQuery("from Patient", Patient.class).getResultList();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            if (session != null) session.close();
        }
        return patients;
    }

    @Override
    public Patient findById(int id) {
        Session session = null;
        Transaction tx = null;
        Patient patient = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            patient = (Patient) session.get(Patient.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            if (session != null) session.close();
        }
        return patient;
    }

    // Méthode générique pour éviter la duplication
    private boolean executeTransaction(HibernateAction action) {
        Session session = null;
        Transaction tx = null;
        boolean success = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            action.execute(session);
            tx.commit();
            success = true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            if (session != null) session.close();
        }
        return success;
    }

    public List<Patient> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FunctionalInterface
    private interface HibernateAction {
        void execute(Session session);
    }
}
