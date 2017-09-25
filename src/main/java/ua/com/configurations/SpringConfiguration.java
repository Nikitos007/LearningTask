package ua.com.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

//import org.hibernate.jpa.HibernatePersistenceProvider;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"ua.com", "net.sf.oval.integration.spring"})
@EnableTransactionManagement
@EnableJpaRepositories("ua.com.repository")
public class SpringConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "ua.com.models" });
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");//"com.mysql.jdbc.Driver"
        dataSource.setUrl("jdbc:mysql://localhost:3306/db_department");
        dataSource.setUsername( "root" );
        dataSource.setPassword( "root" );
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");//create-drop
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        properties.setProperty("connection.useUnicode", "true");
        properties.setProperty("connection.characterEncoding", "utf8");
        properties.setProperty("connection.CharSet", "utf8");
        return properties;
    }
























//    @Autowired
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactory.setDataSource(dataSource);
//        Properties jpaProperties = new Properties();
//        jpaProperties.put("hbm2ddl.auto", "update");
//        jpaProperties.put("hibernate.show_sql", "true");
//
////        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
////        jpaProperties.put("hibernate.enable_lazy_load_no_trans", "true");
////        jpaProperties.put("connection.useUnicode", "true");
////        jpaProperties.put("connection.characterEncoding", "utf8");
////        jpaProperties.put("connection.CharSet", "utf8");
//
//        entityManagerFactory.setJpaProperties(jpaProperties);
//        entityManagerFactory.setPackagesToScan("ua.com.models");
//        entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
//        return entityManagerFactory;
//    }
//
//    @Autowired
//    @Bean
//    public JpaTransactionManager transactionManager(DataSource dataSource, EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager((entityManagerFactory));
//        transactionManager.setDataSource(dataSource);
//        return transactionManager;
//    }
//
//
//
//    @Bean
//    public DataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/db_department");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        return dataSource;
//    }







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

