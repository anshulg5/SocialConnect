package org.example;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.internal.cglib.proxy.$UndeclaredThrowableException;
import com.google.inject.internal.util.$AsynchronousComputationException;
import com.google.inject.servlet.GuiceFilter;
import org.apache.commons.codec.language.bm.Rule;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.example.configRule.Node;
import org.example.configRule.Operator;
import org.example.configRule.RuleApp;
import org.example.db.ConnectionDetailDao;

import org.example.model.AppMessage;
import org.example.model.ConnectionDetail;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import sun.awt.windows.ThemeReader;

import javax.servlet.DispatcherType;
import javax.swing.plaf.TableHeaderUI;
import javax.xml.soap.Text;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Singleton
public class MediatorApp {

    private SenderApp MessageSender;
    private ConnectionDetailDao dao = null;
    private Map<String, String> channelDetails;
    private AppMessage currAppmsg = null;
    private Node<Boolean> rule = null;

    public AppMessage getCurrAppmsg() {
        return currAppmsg;
    }

    public void setCurrAppmsg(AppMessage currAppmsg) {
        this.currAppmsg = currAppmsg;
    }

    @Inject
    public MediatorApp(SenderApp MessageSender, ConnectionDetailDao dao) {
        this.dao = dao;
        this.MessageSender = MessageSender;
        channelDetails = new ConcurrentHashMap();
        List<ConnectionDetail> data = dao.getAllConnections();
        Iterator<ConnectionDetail> iterator = data.iterator();


        while (iterator.hasNext()) {
            ConnectionDetail detail = iterator.next();
            channelDetails.put(detail.getSourceID(), detail.getTargetID());
        }
//        for (Map.Entry<String,String> entry : channelDetails.entrySet())
//            System.out.println("Key = " + entry.getKey() +
//                    ", Value = " + entry.getValue());

        RuleApp.setApp(this);
        setRule();
//        AppMessage msg = new AppMessage();
//        setCurrAppmsg(msg);
    }

    private void setRule() {

//        input : { 'AND' :[ { 'EQ' : [ { 'GETMSG' : ""},{ 'STR' : "BYE!"} ,
//                           { 'EQ' : [ { 'PATH' : ["msg"]},{ 'STR' : "Hello!"} ]
//                }
//        doc : {"msg" : "Hello!"}
//
        Map<Operator, Object> input = ImmutableMap.<Operator, Object>builder()
                .put(Operator.AND, ImmutableList.of(
                        ImmutableMap.builder()
                                .put(Operator.EQ, ImmutableList.of(
                                        ImmutableMap.of(Operator.GETMSG, ""),
                                        ImmutableMap.of(Operator.STR, "BYE!")
                                )).build(),
                        ImmutableMap.builder()
                                .put(Operator.EQ, ImmutableList.of(
                                        ImmutableMap.of(Operator.PATH, ImmutableMap.of(Operator.STRLST, Lists.newArrayList("msg"))),
                                        ImmutableMap.of(Operator.STR, "Hello!")
                                )).build()
                ))
                .build();
        if (input.size() == 1) {
            Map<String, Object> map = new HashMap<>();
            Operator key = input.keySet().iterator().next();
            rule = RuleApp.createNode(key, input.get(key), map);
        }
    }

    public void addConfig(String source, String target) {
        if (source == null || target == null) return;
        channelDetails.put(source, target);
        for (Map.Entry<String, String> entry : channelDetails.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        dao.saveDetails(new ConnectionDetail(source, target));
    }

    public boolean sendMessage(AppMessage msg, String template) {
        setCurrAppmsg(msg);
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("msg", msg.getText());
        if(rule.apply(objectMap)){
            System.out.println(msg + "passed Rule");
        }
        else {
            System.out.println(msg + "failed Rule");
        }
        return true;
        //   return MessageSender.send(msg.getChannelId(),textmsg);
    }

    private String processMessage(AppMessage msg, String template) {
        //Here convert text from given params.
        if (template == null)
            return msg.toString();
        //regex
        template = template.replaceAll("\\$\\(json\\.provider\\)", msg.getProvider());
        template = template.replaceAll("\\$\\(json\\.channelId\\)", msg.getChannelId());
        template = template.replaceAll("\\$\\(json\\.channelName\\)", msg.getChannelName());
        template = template.replaceAll("\\$\\(json\\.sentby\\)", msg.getSentBy());
        template = template.replaceAll("\\$\\(json\\.text\\)", msg.getText());
        return template;
    }


    public static void main(String[] args) throws Exception {
//
//        String url = "/Users/hiren.va/ok.txt";
//        String string = "";
//        try {
//            File myObj = new File(url);
//            Scanner myReader = new Scanner(myObj);
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                string += data;
//            }
//            myReader.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//        System.out.println(string);
        //get constructor that takes a String as argument


        ApiContextInitializer.init();
        // TelegramBotsApi botsApi = new TelegramBotsApi();
        AppModule appModule = new AppModule();
        AppServletModule appServletModule = new AppServletModule();
        Injector injector = Guice.createInjector(appModule, appServletModule);
        // on run bot shutdown and add bot and remove
        // new web-hook as receiver


        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        handler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
        server.start();
        server.join();
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
