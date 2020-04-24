package org.example.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesModule extends AbstractModule {
    private Properties properties;

    protected void configure() {
        Names.bindProperties(binder(),loadConfig("settings"));
    }

    private Properties loadConfig(String propertiesFileName) {
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(propertiesFileName + ".properties")) {
            properties = new Properties();
            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }
}
