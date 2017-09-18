package ua.com.configurations;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.com.models.Department;
import ua.com.models.Employee;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"ua.com", "net.sf.oval.integration.spring"})
@EnableTransactionManagement
@EnableJpaRepositories
public class SpringConfiguration {

    @Autowired
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        Properties jpaProperties = new Properties();
        jpaProperties.put("hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.show_sql", "true");

        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProperties.put("hibernate.enable_lazy_load_no_trans", "true");
        jpaProperties.put("connection.useUnicode", "true");
        jpaProperties.put("connection.characterEncoding", "utf8");
        jpaProperties.put("connection.CharSet", "utf8");

        entityManagerFactory.setJpaProperties(jpaProperties);
        entityManagerFactory.setPackagesToScan("ua.com.models");
        entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
        return entityManagerFactory;
    }

    @Autowired
    @Bean
    public JpaTransactionManager transactionManager(DataSource dataSource, EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager((entityManagerFactory));
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/db_department");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }







//    @Autowired
//    @Bean
//    public SessionFactory sessionFactory(DataSource dataSource) {
//        LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource);
//
////       sessionFactoryBuilder.addPackages(ua.com.models);
//        sessionFactoryBuilder.addAnnotatedClasses(Employee.class, Department.class);
//        sessionFactoryBuilder.addProperties(getHibernateProperties());
//        return sessionFactoryBuilder.buildSessionFactory();
//    }
//
//
//    @Autowired
//    @Bean
//    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
//        return transactionManager;
//    }

}

