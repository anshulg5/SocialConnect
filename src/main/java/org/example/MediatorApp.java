package org.example;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.internal.util.$AsynchronousComputationException;
import org.example.configRule.Node;
import org.example.db.ConnectionDetailDao;

import org.example.model.AppMessage;
import org.example.model.ConnectionDetail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.*;


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

        String url = "/Users/hiren.va/ok.txt";
        String string = "";
        try {
            File myObj = new File(url);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                string += data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println(string);
        //get constructor that takes a String as argument


//        ApiContextInitializer.init();
//    //    TelegramBotsApi botsApi = new TelegramBotsApi();
//        AppModule appModule = new AppModule();
//        AppServletModule appServletModule = new AppServletModule();
//        Injector injector = Guice.createInjector(appModule,appServletModule);
//
//        // on run bot shutdown and add bot and remove
//        // new web-hook as receiver
//
//
//
//        Server server = new Server(8080);
//        ServletContextHandler handler = new ServletContextHandler(server, "/");
//        handler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
//        server.start();
//        server.join();
//
//        JSONObject msg = new JSONObject();
//        msg.put("id" , "123");
//        msg.put("name" , "Hiren");
//        Collection< Node<String> >  myStrings = new ArrayList< Node<String> > ();
//        Node<String> event = new MyString("name");
//        myStrings.add(event);
//        Collection<Node<String>> cool = new ArrayList<Node<String>>();
//
//        PathNode get = new PathNode(cool);
//
//
//        Node<String> left = new MyString();
//        Node<String> right = new MyString("Hiren");
//        Isequal<String> isequal = new Isequal<String>(left,right);
//        System.out.println(isequal.apply(null));

    }
}
