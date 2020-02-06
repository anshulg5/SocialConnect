package org.example;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.example.db.ConnectionDetailDao;

import org.example.model.AppMessage;
import org.example.model.ConnectionDetail;
import org.telegram.telegrambots.ApiContextInitializer;

import javax.servlet.DispatcherType;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Singleton
public class MediatorApp{

    private SenderApp MessageSender;
    private ConnectionDetailDao dao = null;
    private Map<String,String> channelDetails;

    @Inject
    public MediatorApp(SenderApp MessageSender, ConnectionDetailDao dao){
        this.dao = dao;
        this.MessageSender = MessageSender;
        channelDetails = new ConcurrentHashMap();

        List<ConnectionDetail> data = dao.getAllConnections();
        Iterator<ConnectionDetail> iterator = data.iterator();


        while (iterator.hasNext()) {
            ConnectionDetail detail = iterator.next();
            channelDetails.put(detail.getSourceID(),detail.getTargetID());
        }
//        for (Map.Entry<String,String> entry : channelDetails.entrySet())
//            System.out.println("Key = " + entry.getKey() +
//                    ", Value = " + entry.getValue());
    }

    public void addConfig(String source, String target) {
        if(source == null || target == null)return;
        channelDetails.put(source,target);
                for (Map.Entry<String,String> entry : channelDetails.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        dao.saveDetails(new ConnectionDetail(source,target));
    }
    public boolean sendMessage(AppMessage msg,String template) {
        String textmsg = processMessage(msg,template);
        return MessageSender.send(msg.getChannelId(),textmsg);
    }

    private String processMessage(AppMessage msg, String template) {
        //Here convert text from given params.
        if(template == null)
            return msg.toString();
        //regex
        template = template.replaceAll("\\$\\(json\\.provider\\)",msg.getProvider());
        template = template.replaceAll("\\$\\(json\\.channelId\\)",msg.getChannelId());
        template = template.replaceAll("\\$\\(json\\.channelName\\)",msg.getChannelName());
        template = template.replaceAll("\\$\\(json\\.sentby\\)",msg.getSentBy());
        template = template.replaceAll("\\$\\(json\\.text\\)",msg.getText());
        return template;
    }

    public static void main(String[] args)throws Exception{

        ApiContextInitializer.init();
    //    TelegramBotsApi botsApi = new TelegramBotsApi();
        AppModule appModule = new AppModule();
        AppServletModule appServletModule = new AppServletModule();
        Injector injector = Guice.createInjector(appModule,appServletModule);

        // on run bot shutdown and add bot and remove
        // new web-hook as receiver



        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        handler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
        server.start();
        server.join();
    }
}
