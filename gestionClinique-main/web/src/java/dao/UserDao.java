package dao;

import entities.User;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class UserDao extends AbstractDao<User> {
    public UserDao() {
        super(User.class);
    }

    public List<User> findByEmail(String email) {
        Session session = null;
        Transaction tx = null;
        List<User> users = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            users = session.createQuery("FROM User WHERE email = :email", User.class)
                           .setParameter("email", email)
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
        return users;
    }

    public List<User> findByNom(String nom) {
        Session session = null;
        Transaction tx = null;
        List<User> users = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            users = session.createQuery("FROM User WHERE nom = :nom", User.class)
                           .setParameter("nom", nom)
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
        return users;
    }
}