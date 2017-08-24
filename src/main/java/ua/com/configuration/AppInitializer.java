//package ua.com.configuration;
//
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//
///**
// * Created on 09.08.17.
// */
//public class AppInitializer implements WebApplicationInitializer {
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext appWebContext = new AnnotationConfigWebApplicationContext();
//        appWebContext.register(AppBeenContext.class);
//        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(appWebContext);
//        servletContext.addListener(contextLoaderListener);
//    }
//}
