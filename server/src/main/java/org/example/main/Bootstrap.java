package org.example.main;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

@Singleton
class Bootstrap{
    private final Server server;
    private final int plainPort;
    private final int sslPort;

    @Inject
    Bootstrap(@Named("web.http.port") int plainPort,
              @Named("web.https.port") int sslPort){
        this.plainPort = plainPort;
        this.sslPort = sslPort;
        server = new Server();
        configureServer();
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addFilter(GuiceFilter.class,"/*", EnumSet.allOf(DispatcherType.class));
//        context.addServlet(DefaultServlet.class,"/");
    }

    void configureServer(){
        SslContextFactory sslContextFactory = new SslContextFactory.Server();
        String keyStorePath = getClass().getClassLoader().getResource("keystore.jks").toExternalForm();
      //  System.out.println("uri "+keyStorePath);
        sslContextFactory.setKeyStorePath(keyStorePath);
        sslContextFactory.setKeyStorePassword("123456");
        sslContextFactory.setKeyManagerPassword("123456");

//         Configuring plain connector (optional)
//        ServerConnector plainConnector = new ServerConnector(server, new HttpConnectionFactory());
//        plainConnector.setPort(plainPort);

        // Configuring SSL connector
        HttpConfiguration https = new HttpConfiguration();
        https.addCustomizer(new SecureRequestCustomizer());
        ServerConnector sslConnector = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, "http/1.1"),
                new HttpConnectionFactory(https));
        sslConnector.setPort(sslPort);
        // Setting HTTP and HTTPS connectors
        server.setConnectors(new Connector[] {
                sslConnector
//                , plainConnector
        });
    }


    public void start(){
        try {
            server.start();
            System.out.println("Started Server");
//            database.addConfig();
//            server.join();
        } catch (Exception e) {
      //      e.printStackTrace();
        }
    }
}
