package ua.com.utils;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created on 09.08.17.
 */

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"ua.com", "net.sf.oval.integration.spring"})
public class AppBeenContext {

    @Bean
    public SessionFactory initSessionFactory() {
        return HibernateSessionFactory.getSessionFactory();
    }

    @Bean
    public ViewResolver initViewResplver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
