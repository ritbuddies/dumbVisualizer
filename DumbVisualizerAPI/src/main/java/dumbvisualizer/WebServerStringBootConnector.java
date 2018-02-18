package dumbvisualizer;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebServerStringBootConnector extends SpringBootServletInitializer {

    public static void initServer() {
        SpringApplication.run(WebServerStringBootConnector.class);
//    	new SpringApplicationBuilder(Application.class).
//    	properties(Collections.singletonMap("server.port", 8080)).
//    	registerShutdownHook(true).run(args);
    }
    @Bean
    public ServletRegistrationBean jerseyServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/dumbVisualizer/*");
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, WebServerRestAPIConfiguration.class.getName());
        return registration;
    }
}