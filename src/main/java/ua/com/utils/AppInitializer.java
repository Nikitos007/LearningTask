package ua.com.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created on 09.08.17.
 */
@Component
public class AppInitializer implements WebApplicationInitializer {

    private static Logger LOG = LoggerFactory.getLogger(AppInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appWebContext = new AnnotationConfigWebApplicationContext();
        appWebContext.register(AppBeenContext.class);
        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(appWebContext);
        servletContext.addListener(contextLoaderListener);

        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(appWebContext));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");

//        ServletRegistration.Dynamic s = servletContext.a
    }
}
