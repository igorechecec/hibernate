package com.nixsolutions.dao;

import com.nixsolutions.entity.Role;
import com.nixsolutions.utils.HibernateUtils;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateRoleDao extends AbstractDao implements RoleDao {

    public HibernateRoleDao() {
        HibernateUtils.getSessionFactory();
    }
    /**
     * Creates new role.
     *
     * @param role new role.
     */
    @Override
    public void create(Role role) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
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
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
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
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.remove(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
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
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
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
        }
        return role;
    }

    public Role findById(Long id) {
        Role role = null;
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            role = session.createQuery("SELECT r FROM Role r WHERE r.id = :id", Role.class)
                .setParameter("id", id).getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return role;
    }
}
