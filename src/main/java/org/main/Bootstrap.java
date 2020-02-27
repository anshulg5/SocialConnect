package org.main;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

@Singleton
public class Bootstrap{
    Server server;

    @Inject
    Bootstrap(){
        server = new Server(8070);
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addFilter(GuiceFilter.class,"/*", EnumSet.allOf(DispatcherType.class));
//        context.addServlet(DefaultServlet.class,"/");
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
