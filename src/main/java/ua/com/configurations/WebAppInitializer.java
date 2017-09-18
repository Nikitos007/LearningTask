package ua.com.configurations;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appWebContext = new AnnotationConfigWebApplicationContext();
        appWebContext.register(SpringConfiguration.class);
        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(appWebContext);
        servletContext.addListener(contextLoaderListener);
    }

}
