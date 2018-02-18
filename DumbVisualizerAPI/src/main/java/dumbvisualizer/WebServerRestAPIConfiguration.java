package dumbvisualizer;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;



public class WebServerRestAPIConfiguration extends ResourceConfig {
    
    public WebServerRestAPIConfiguration() {
        register(RequestContextFilter.class);
        register(WebHandler.class);

    }
    
}