package ua.com.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created on 09.08.17.
 */
public class AppInitializer implements WebApplicationInitializer {

    private static Logger LOG = LoggerFactory.getLogger(AppInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appWebContext = new AnnotationConfigWebApplicationContext();
        appWebContext.register(AppBeenContext.class);
        appWebContext.refresh();
        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(appWebContext);
        servletContext.addListener(contextLoaderListener);
    }
}
