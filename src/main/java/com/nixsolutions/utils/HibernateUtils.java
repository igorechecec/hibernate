package com.nixsolutions.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtils {

    private static final Logger logger = LoggerFactory.getLogger(HibernateUtils.class);
    private static SessionFactory sessionFactory;

    private HibernateUtils() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                logger.error("Hibernate session factory didn't create", e);
                throw new HibernateException(e);
            }
        }
        return sessionFactory;
    }
}
