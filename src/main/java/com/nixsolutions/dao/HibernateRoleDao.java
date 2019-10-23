package com.nixsolutions.dao;

import com.nixsolutions.entity.Role;
import com.nixsolutions.utils.HibernateUtils;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateRoleDao implements RoleDao {

    private SessionFactory sessionFactory;

    public HibernateRoleDao() {
        sessionFactory = HibernateUtils.getSessionFactory();
    }
    /**
     * Creates new role.
     *
     * @param role new role.
     */
    @Override
    public void create(Role role) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Updates role info.
     *
     * @param role updated role.
     */
    @Override
    public void update(Role role) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Removes role.
     *
     * @param role removed role.
     */
    @Override
    public void remove(Role role) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.remove(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Search role by given name.
     *
     * @param name name of role.
     * @return searched role.
     */
    @Override
    public Role findByName(String name) {
        Role role = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            role = session.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                .setParameter("name", name).getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return role;
    }

    public Role findById(Long id) {
        Role role = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            role = session.createQuery("SELECT r FROM Role r WHERE r.id = :id", Role.class)
                .setParameter("id", id).getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return role;
    }
}
