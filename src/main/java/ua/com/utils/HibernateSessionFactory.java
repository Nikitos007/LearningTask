package ua.com.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created on 21.08.17.
 */

@Component
public class HibernateSessionFactory {

    private SessionFactory sessionFactory;

    {
//        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
//        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//        sessionFactory = configuration.buildSessionFactory(ssrb.build());


        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}

