
package dao;

import entities.Consultation;
import entities.Medecin;
import entities.Patient;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class ConsultationDao implements IDao<Consultation>{

    @Override
    public boolean create(Consultation o) {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            etat = true;
           
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        }finally{
            if (session != null) 
                session.close();
        }
        return etat;
    }

    @Override
    public boolean delete(Consultation o) {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            etat = true;
           
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        }finally{
            if (session != null) 
                session.close();
        }
        return etat;
    }

    @Override
    public boolean update(Consultation o) {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            etat = true;
           
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        }finally{
            if (session != null) 
                session.close();
        }
        return etat;    }

    @Override
    public List<Consultation> findAll() {
        Session session = null;
        Transaction tx = null;
        List<Consultation> consultations = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            consultations = session.createQuery("from Consultation").list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        }finally{
            if (session != null) 
                session.close();
        }
        return consultations;    
    }

    @Override
    public Consultation findById(int id) {
        Session session = null;
        Transaction tx = null;
        Consultation consultation = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            consultation = (Consultation) session.get(Consultation.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        }finally{
            if (session != null) 
                session.close();
        }
        return consultation;    
    }
    
     public void createEntities(Patient patient, Medecin medecin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(patient);
        session.save(medecin);
        tx.commit();
        session.close();
    }
    
}
