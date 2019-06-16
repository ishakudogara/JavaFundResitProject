package fr.epita.cardgame.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    /** The properties. */
    private Properties properties;

    /** The instance. */
    private static Configuration instance;
    


    /**
     * Instantiates a new configuration.
     */
    private Configuration() {
        if(this.properties == null) {
            properties = new Properties();
            try(InputStream is = new FileInputStream(new File("app.properties"))){
                    properties.load(is);
            }catch(IOException e) {
                    e.printStackTrace();
            }
        }
    }

    /**
     * Gets the single instance of Configuration.
     *
     * @return single instance of Configuration
     */
    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    /**
     * Gets the configuration value.
     *
     * @param configurationKey the configuration key
     * @return the configuration value
     */
    public String getConfigurationValue(String configurationKey) {
        return properties.getProperty(configurationKey);
    }

}
