package ru.ifmo.ctddev.gerasimov.webpagetester;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 9/1/12
 * Time: 2:16 PM
 */
public class Config extends PropertiesConfiguration {
    private static Config instance;

    private Config() {
        super();
    }

    private Config(String path) throws ConfigurationException {
        super(path);
    }

    public static void init(String path) throws ConfigurationException {
        if (instance != null) {
            throw new RuntimeException("Config has already been initialized");
        }
        instance = new Config(path);
    }

    public static Config getInstance(){
        if (instance == null) {
            throw new RuntimeException("Config has not been initialized");
        }
        return instance;
    }
}