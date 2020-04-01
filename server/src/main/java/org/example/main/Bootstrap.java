package org.example.main;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.DispatcherType;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.EnumSet;

@Singleton
public class Bootstrap{
    Server server;

    @Inject
    Bootstrap(){
        server = new Server();
        configureServer();
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addFilter(GuiceFilter.class,"/*", EnumSet.allOf(DispatcherType.class));
//        context.addServlet(DefaultServlet.class,"/");
    }

    void configureServer(){
        // HTTP Configuration
        HttpConfiguration http = new HttpConfiguration();
        http.addCustomizer(new SecureRequestCustomizer());

        // Configuration for HTTPS redirect
        http.setSecurePort(443);
        http.setSecureScheme("https");
        ServerConnector connector = new ServerConnector(server);
        connector.addConnectionFactory(new HttpConnectionFactory(http));
        connector.setPort(80);

        // HTTPS configuration
        HttpConfiguration https = new HttpConfiguration();
        https.addCustomizer(new SecureRequestCustomizer());

        //SSL Configuration
        SslContextFactory sslContextFactory = new SslContextFactory.Server();
        String keyStorePath = getClass().getClassLoader().getResource("keystore.jks").toExternalForm();
        System.out.println("uri "+keyStorePath);
//        String keyStorePath = null;
//        try {
//            File file = Paths.get(res.toURI()).toFile();
//            keyStorePath = file.getAbsolutePath();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        System.out.println("\n"+keyStorePath.substring(1)+"\n");
        sslContextFactory.setKeyStorePath(keyStorePath);
        sslContextFactory.setKeyStorePassword("123456");
        sslContextFactory.setKeyManagerPassword("123456");

        // Configuring SSL connector
        ServerConnector sslConnector = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, "http/1.1"),
                new HttpConnectionFactory(https));
        sslConnector.setPort(443);

        // Setting HTTP and HTTPS connectors
        server.setConnectors(new Connector[] { connector, sslConnector });
    }


    public void start(){
        try {
            server.start();
            System.out.println("Started Server");
//            database.addConfig();
//            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
