//package org.example;
//
//import com.google.inject.internal.cglib.proxy.$UndeclaredThrowableException;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
//import org.telegram.telegrambots.meta.generics.BotSession;
//
//import java.util.concurrent.ConcurrentHashMap;
//
//public class FlockWebHookManager {
//    private MediatorApp app;
//    private ConcurrentHashMap<String,FlockWebHook> flockWebHookList;
//
//    private FlockWebHook createFlockWebHook() throws TelegramApiRequestException {
//        FlockWebHook flockWebHook = new FlockWebHook(this.app);
//        return flockWebHook;
//    }
//    public void addFlockWebHook(String userName,String token,String msgText) throws TelegramApiRequestException {
//        FlockWebHook flockWebHook = createFlockWebHook();
//        flockWebHook.setUserName(userName);
//        flockWebHook.setToken(token);
//        flockWebHook.setTemplate(msgText);
//        flockWebHookList.put(userName,flockWebHook);
//    }
//}
