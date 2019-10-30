package pl.codinglab.dragdrop.config;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.util.ResourceBundle;

@Singleton
public class ResourceProvider {

    @Produces
    private ResourceBundle defaultResourceBundle = ResourceBundle.getBundle("default");
}
