package com.nixsolutions.dao;

import com.nixsolutions.entity.User;
import com.nixsolutions.utils.HibernateUtils;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateUserDao extends AbstractDao implements UserDao {



    public HibernateUserDao() {
        HibernateUtils.getSessionFactory();
    }

    /**
     * Add new user.
     *
     * @param user new user.
     */
    @Override
    public void create(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * Update info about user.
     *
     * @param user updated user.
     */
    @Override
    public void update(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * Remove user.
     *
     * @param user removed user.
     */
    @Override
    public void remove(User user) {        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * Finds all available users.
     *
     * @return list of users.
     */
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        User user = null;
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            users = session.createQuery("SELECT u FROM User u", User.class).getResultList();
            transaction.commit();
        } catch (NoResultException e) {
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return users;
    }

    /**
     * Finds user by given login.
     *
     * @param login login by which searching user.
     * @return searched user.
     */
    @Override
    public User findByLogin(String login) {
        User user = null;
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
                .setParameter("login", login).getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }

    public User findById(Long id) {
        User user = null;
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id).getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }


    /**
     * Finds user by given email.
     *
     * @param email email by which searching user.
     * @return searched user.
     */
    @Override
    public User findByEmail(String email) {
        User user = null;
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email).getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }
}
