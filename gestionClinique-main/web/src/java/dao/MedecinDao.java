/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Medecin;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author AMINE
 */
public class MedecinDao extends AbstractDao<Medecin> {

    public MedecinDao() {
        super(Medecin.class);
    }

    public List<Medecin> findBySpecialite(String specialite) {
        Session session = null;
        Transaction tx = null;
        List<Medecin> medecins = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            medecins = session.createQuery("FROM Medecin WHERE specialite = :specialite", Medecin.class);                            .setParameter("specialite", specialite)
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
        return medecins;
    }
}