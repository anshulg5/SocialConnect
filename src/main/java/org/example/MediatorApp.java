package org.example;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.RequestScoped;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.example.Servlet.WelcomeServlet;
import org.example.db.BotDetailDao;
import org.example.db.ConnectionDetailDao;

import org.example.model.AppMessage;
import org.example.model.BotDetail;
import org.example.model.ConnectionDetail;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;


import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Singleton
public class MediatorApp{

    private SenderApp MessageSender;
    private ConnectionDetailDao dao = null;
    private Map<String,String> channelDetails;
    static private Server server;

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
    public boolean sendMessage(AppMessage msg) {
        if(channelDetails.containsKey(msg.getChannelId())) return MessageSender.send(channelDetails.get(msg.getChannelId()), msg);
        return false;
    }
    public static void main(String[] args)throws Exception{

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        AppModule appModule = new AppModule();
        AppServletModule appServletModule = new AppServletModule();
        Injector injector = Guice.createInjector(appModule,appServletModule);


        BotDetailDao dao = injector.getInstance(BotDetailDao.class);
        List<BotDetail> bots = dao.getAllBots();
        Iterator<BotDetail> iterator = bots.iterator();
        while (iterator.hasNext()) {
            TelegramBot bot = injector.getInstance(TelegramBot.class);
            BotDetail botDetail= iterator.next();
            bot.setBotUserName(botDetail.getBotUserName());
            bot.setBotToken(botDetail.getBotToken());
            botsApi.registerBot(bot);
        }

        server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        handler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
        handler.addServlet(WelcomeServlet.class, "/");
        server.start();
        server.join();
    }
}
