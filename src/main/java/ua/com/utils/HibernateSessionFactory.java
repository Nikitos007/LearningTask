package ua.com.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created on 07.08.17.
 */
public class HibernateSessionFactory {

    private static SessionFactory sessionFactory;

    public static SessionFactory initSessionFactory() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        if (sessionFactory == null) {
            sessionFactory = configuration.buildSessionFactory(ssrb.build());
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            return initSessionFactory();
        }
        return sessionFactory;
    }
}
