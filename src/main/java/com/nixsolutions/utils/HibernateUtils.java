package com.nixsolutions.utils;

import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by ADMIN on 20.10.2019.
 */
public class HibernateUtils {

    private static SessionFactory sessionFactory;
    private static DataSource dataSource = initDataSource();

    private HibernateUtils() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Role.class);
                StandardServiceRegistryBuilder builder
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    private static DataSource initDataSource() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {

        }
        ConnectionFactory connectionFactory =
            new DriverManagerConnectionFactory("jdbc:h2:mem:testdb", "sa", "sa");
        PoolableConnectionFactory poolableConnectionFactory =
            new PoolableConnectionFactory(connectionFactory, null);
        ObjectPool<PoolableConnection> connectionPool =
            new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        return new PoolingDataSource<>(connectionPool);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
