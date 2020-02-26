package org.example;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.example.db.BotDetailDao;
import org.example.model.BotDetail;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotSession;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class TelegramBotManager {
    private MediatorApp app;
    private TelegramBotsApi botsApi;
    private BotDetailDao dao;
    private ConcurrentHashMap<String,TelegramBot> botList;
    private ConcurrentHashMap<String,BotSession> botSessionList;

    @Inject
    TelegramBotManager(TelegramBotsApi botsApi, MediatorApp app, BotDetailDao dao) throws TelegramApiRequestException {
        this.botsApi = botsApi;
        this.app = app;
        this.dao = dao;
        this.botList = new ConcurrentHashMap<String, TelegramBot>();
        this.botSessionList = new ConcurrentHashMap<String, BotSession>();
        //Load bots from database
        loadBots();
    }

    private void loadBots() throws TelegramApiRequestException {
        List<BotDetail> bots = dao.getAllBots();
        Iterator<BotDetail> iterator = bots.iterator();
        while (iterator.hasNext()) {
            BotDetail botDetail= iterator.next();
            addBot(botDetail.getBotUserName(),botDetail.getBotToken(),botDetail.getMsgText());
        }
    }

    private TelegramBot createNewBot() throws TelegramApiRequestException {
        TelegramBot bot = new TelegramBot(this.app);
        return bot;
    }

    public void addBot(String botUserName,String botToken,String msgText) throws TelegramApiRequestException {
        TelegramBot bot = createNewBot();
        bot.setBotUserName(botUserName);
        bot.setBotToken(botToken);
        bot.setBotMsgText(msgText);
        BotSession session = this.botsApi.registerBot(bot);
        botList.put(botUserName,bot);
        botSessionList.put(botUserName,session);
        // add to database if success.
        //dao.addBotDetail(botUserName,botToken,msgText);

    }
    public void removeBot(String botUserName,String botToken){
        //Cases can be : If no user throw No such user Exception, not valid Token and success.
        System.out.println(botUserName);
        if(botList.containsKey(botUserName)) {
            TelegramBot bot = this.botList.get(botUserName);
            BotSession session = this.botSessionList.get(botUserName);
            if(session.isRunning()) {
                bot.onClosing();
                //This will take time.
                session.stop();
                botSessionList.remove(botUserName);
                botList.remove(botUserName);
                dao.removeBotDetail(botUserName);
            }
            // remove from database if success here.
        }

    }
    public void setBotMsgText(String botUserName, String botToken, String botMsgText) {
        if(botList.containsKey(botUserName)) {

            // edit to database if success here.
        }
    }
}
