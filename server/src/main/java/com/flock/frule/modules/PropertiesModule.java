package com.flock.frule.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Properties;

public class PropertiesModule extends AbstractModule {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void configure() {
        Names.bindProperties(binder(),loadConfig("settings"));
    }

    private Properties loadConfig(String propertiesFileName) {
        FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
                new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                    .configure(new Parameters().properties()
//                    .setListDelimiterHandler(new DefaultListDelimiterHandler(','))
                    .setFileName(propertiesFileName + ".properties"));
        try {
            Properties properties = new Properties();
            Configuration config = builder.getConfiguration();
            Iterator<String> iterator = config.getKeys();
            while(iterator.hasNext()) {
                String key = iterator.next();
                properties.put(key,config.getString(key));
                log.info(key + ": " + config.getString(key));
            }
            return properties;

        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
