package ua.com.utils;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 09.08.17.
 */

@Configuration
@ComponentScan(basePackages = {"ua.com", "net.sf.oval.integration.spring"})
public class AppBeenContext {

    @Bean
    public SessionFactory initSessionFactory() {
        return HibernateSessionFactory.getSessionFactory();
    }

}
