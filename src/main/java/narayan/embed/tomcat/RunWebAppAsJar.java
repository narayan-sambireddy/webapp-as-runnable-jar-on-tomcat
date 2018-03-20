package narayan.embed.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;

/**
 * The Main Configuration of the App
 *
 * @author narayana
 */
@EnableWebMvc
@Configuration
@ComponentScan
public interface RunWebAppAsJar {

    String CONTEXT_PATH = "/app";
    String BASE_DIR = new File("").getAbsolutePath();
    String DISPATCHER_SERVLET = "dispatcherServlet";
    String URL_MAPPING = "/";
    Integer LOAD_ON_STARTUP = 1;
    Integer SERVER_PORT = 8080;


    static void main(String[] args) throws Exception {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        context.register(RunWebAppAsJar.class);


        Tomcat tomcat = new Tomcat();
        tomcat.setPort(SERVER_PORT);

        Context servletContext = tomcat.addContext(CONTEXT_PATH, BASE_DIR);
        Wrapper dispatcher = Tomcat.addServlet(servletContext, DISPATCHER_SERVLET, new DispatcherServlet(context));
        dispatcher.addMapping(URL_MAPPING);
        dispatcher.setLoadOnStartup(LOAD_ON_STARTUP);

        tomcat.start();
        tomcat.getServer().await();

    }
}
